package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        //Insert
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("course");
//
//        MongoCollection<Document> collection = db.getCollection("insertTest");
//
//        collection.drop();
//
//        Document smith = new Document("name", "Smith")
//                .append("age", 30)
//                .append("profession", "programmer");
//
//        Document john = new Document("name", "john")
//                .append("age", 25)
//                .append("profession", "hacker");
//
//        collection.insertOne(smith);
//        collection.insertMany(Arrays.asList(smith, john));


//        //Query
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("course");
//
//        MongoCollection<Document> collection = db.getCollection("findTest");
//
//        collection.drop();
//
//        for (int i = 0; i < 10; i++) {
//            collection.insertOne(new Document("x", i));
//        }
//
//        //Use List
//        List<Document> result = collection.find().into(new ArrayList<Document>());
//
//        for (Document cur:result
//             ) {
//            System.out.println(cur);
//        }
//
//        //Use cursor
//        MongoCursor<Document> cursor = collection.find().iterator();
//
//        try{
//            while (cursor.hasNext()){
//                Document cur = cursor.next();
//                System.out.println(cur);
//            }
//
//        }finally {
//            cursor.close();
//        }

//        //Find
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("course");
//
//        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");
//
//        collection.drop();
//
//        for (int i = 0; i < 10; i++) {
//            collection.insertOne(new Document("x", new Random().nextInt(2)).
//                    append("y", new Random().nextInt(100)).
//                    append("i", i));
//        }
//
////        Bson filter = new Document("x", 0).append("y", new Document("$gt", 10));
//
////        Bson filter = Filters.and(Filters.eq("x",0), Filters.gt("y", 10), Filters.lt("y", 90));
//
//        Bson filter = Filters.or(Filters.eq("x", 0), Filters.gt("y", 10), Filters.lt("y", 90));
//
//        //Projection
////        Bson projection = new Document("y", 1).append("i", 1).append("_id", 0);
//
//        Bson projection = Projections.exclude("x", "_id");
//
//        List<Document> all = collection.find(filter)
//                                        .projection(projection)
//                                        .into(new ArrayList<Document>());
//
//        for (Document cur:all
//             ) {
//            System.out.println(cur);
//        }


//        //Sort
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("course");
//
//        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");
//
//        collection.drop();
//
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                collection.insertOne(new Document()
//                .append("i", i)
//                .append("j", j));
//            }
//        }
//
//        Bson projection = Projections.fields(Projections.include("i", "j"), Projections.excludeId());
//
////        Bson sort = new Document("i", 1).append("j", -1);
//
//        Bson sort = Sorts.orderBy(Sorts.ascending("i"), Sorts.descending("j"));
//
//        List<Document> all = collection.find()
//                .projection(projection)
//                .sort(sort)
//                .limit(50)
//                .into(new ArrayList<Document>());
//
//        for (Document cur:all
//                ) {
//            System.out.println(cur);
//        }


//        //Update
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("course");
//
//        MongoCollection<Document> collection = db.getCollection("test");
//
//        collection.drop();
//
//        for (int i = 0; i < 8; i++) {
//            collection.insertOne(new Document("_id", i)
//                    .append("x", i)
//                    .append("y", true));
//        }
//
//        //Replace
////        collection.replaceOne(Filters.eq("x", 5),new Document("x", 20).append("updated", true));
//
//        //Update
////        collection.updateOne(Filters.eq("x",5), new Document("$set",
////                new Document("x", 20).append("updated", true)));
//
//        //Update with updates method
//        collection.updateOne(Filters.eq("x", 5), Updates.combine(Updates.set("x", 20), Updates.set("updated", true)));
//
////        collection.updateMany(Filters.gte("x", 5), Updates.inc("x", 1));
//
//        List<Document> all = collection.find().into(new ArrayList<Document>());
//
//        for (Document cur:all) {
//            System.out.println(cur);
//        }

//        //Delete
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("course");
//
//        MongoCollection<Document> collection = db.getCollection("test");
//
//        collection.drop();
//
//        for (int i = 0; i < 8; i++) {
//            collection.insertOne(new Document("_id", i));
//        }
//
//        collection.deleteOne(Filters.gt("_id", 4));
//        collection.deleteMany(Filters.gt("_id", 4));
//
//        List<Document> all = collection.find().into(new ArrayList<Document>());
//
//        for (Document cur:all) {
//            System.out.println(cur);
//        }


        //Complicate Delete
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase db = client.getDatabase("students");

        MongoCollection<Document> collection = db.getCollection("grades");

        collection.deleteOne(Filters.gt("_id", 4));

        Bson filter = Filters.eq("type", "homework");
        Bson sort = Sorts.orderBy(Sorts.ascending("student_id"), Sorts.ascending("score"));
        MongoCursor<Document> cursor = collection.find(filter).sort(sort).iterator();

        int student_id = -1;

        try {
            while (cursor.hasNext()) {
                Document cur = cursor.next();
                if (student_id != cur.getInteger("student_id")) {
                    System.out.println(cur);
                    collection.deleteOne(cur);
                }
                student_id = cur.getInteger("student_id");
            }

        } finally {
            cursor.close();
        }

    }
}
