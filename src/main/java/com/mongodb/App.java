package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Insert
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("course");
//
//        MongoCollection<Document> collection = db.getCollection("insertTest");
//
//        collection.drop();;
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
//        collection.insertMany(Arrays.asList(smith,john));


        //Query
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("course");
//
//        MongoCollection<Document> collection = db.getCollection("findTest");
//
//        collection.drop();;
//
//        for (int i = 0; i < 10; i++) {
//            collection.insertOne(new Document("x", i));
//        }

//        Document document = collection.find().first();
//
//        List<Document> result = collection.find().into(new ArrayList<Document>());
//
//        for (Document cur:result
//             ) {
//            System.out.print(cur);
//        }

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

        //Find
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("course");
//
//        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");
//
//        collection.drop();;
//
//        for (int i = 0; i < 10; i++) {
//            collection.insertOne(new Document("x", new Random().nextInt(2)).
//                    append("y", new Random().nextInt(100)).
//                    append("i", i));
//        }

////        Bson filter = new Document("x", 0).append("y", new Document("$gt", 10));
//
//        Bson filter = Filters.and(Filters.eq("x",0), Filters.gt("y", 10), Filters.lt("y", 90));
//
////        Bson projection = new Document("y", 1).append("i", 1).append("_id", 0);
//
//        Bson projection = Projections.exclude("x", "_id");
//
//        List<Document> all = collection.find(filter)
//                                        .projection(projection)
//                                        .into(new ArrayList<Document>());

//        for (Document cur:all
//             ) {
//            System.out.println(cur);
//        }
//
//        long count = collection.count(filter);
//        System.out.print("Count is " + count);



        //sort
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("course");
//
//        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");
//
//        collection.drop();;
//
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                collection.insertOne(new Document()
//                .append("i", i)
//                .append("j", j));
//            }
//        }

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
//        collection.drop();;
//
//        for (int i = 0; i < 8; i++) {
//            collection.insertOne(new Document("_id", i)
//                    .append("x", i)
//                    .append("y", true));
//        }
//
////        collection.replaceOne(Filters.eq("x", 5),new Document("x", 20).append("updated", true));
//
////        collection.updateOne(Filters.eq("x",5), new Document("$set",
////                new Document("x", 20).append("updated", true)));
//
////        collection.updateOne(Filters.eq("x", 5), Updates.combine(Updates.set("x", 20), Updates.set("updated", true)));
//
//        collection.updateMany(Filters.gte("x", 5), Updates.inc("x", 1));
//
//        List<Document> all = collection.find().into(new ArrayList<Document>());
//
//        for (Document cur:all) {
//            System.out.println(cur);
//        }

        //Delete
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase db = client.getDatabase("course");

        MongoCollection<Document> collection = db.getCollection("test");

        collection.drop();;

        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document("_id", i));
        }

        collection.deleteOne(Filters.gt("_id", 4));
//        collection.deleteMany(Filters.gt("_id", 4));

        List<Document> all = collection.find().into(new ArrayList<Document>());

        for (Document cur:all) {
            System.out.println(cur);
        }

    }
}
