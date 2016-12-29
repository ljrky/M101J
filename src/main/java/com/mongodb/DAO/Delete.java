package com.mongodb.DAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
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
