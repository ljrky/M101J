package com.mongodb.DAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.List;

/**
 * Hello world!
 */
public class Delete {
    public static void main(String[] args) {
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


//        //Complicate Delete
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//
//        MongoDatabase db = client.getDatabase("students");
//
//        MongoCollection<Document> collection = db.getCollection("grades");
//
//        collection.deleteOne(Filters.gt("_id", 4));
//
//        Bson filter = Filters.eq("type", "homework");
//        Bson sort = Sorts.orderBy(Sorts.ascending("student_id"), Sorts.ascending("score"));
//        MongoCursor<Document> cursor = collection.find(filter).sort(sort).iterator();
//
//        int student_id = -1;
//
//        try {
//            while (cursor.hasNext()) {
//                Document cur = cursor.next();
//                if (student_id != cur.getInteger("student_id")) {
//                    System.out.println(cur);
//                    collection.deleteOne(cur);
//                }
//                student_id = cur.getInteger("student_id");
//            }
//
//        } finally {
//            cursor.close();
//        }

        //Complicate Delete
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase db = client.getDatabase("school");

        MongoCollection<Document> collection = db.getCollection("students");

        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document student = cursor.next();
                List<Document> scores = (List<Document>) student.get("scores");
                System.out.println(student.getString("name") + " 's score is : " + scores);
                Document minScoreObject = null;
                Double minScore = Double.MAX_VALUE;

                for (Document scoreDocument : scores
                        ) {
                    String type = scoreDocument.getString("type");
                    double score = scoreDocument.getDouble("score");
                    if (type.equals("homework") && score < minScore) {
                        minScore = score;
                        minScoreObject = scoreDocument;
                    }
                }

                if (minScore != null) {
                    scores.remove(minScoreObject);
                }

                collection.updateOne(Filters.eq("_id", student.get("_id")), new Document("$set", new Document("scores", scores)));
            }

        } finally {
            cursor.close();
        }
    }
}
