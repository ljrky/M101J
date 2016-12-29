package com.mongodb.DAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class Query {
    public static void main(String[] args) {

        //Query
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase db = client.getDatabase("course");

        MongoCollection<Document> collection = db.getCollection("findTest");

        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document("x", i));
        }

        //Use List
        List<Document> result = collection.find().into(new ArrayList<Document>());

        for (Document cur : result
                ) {
            System.out.println(cur);
        }

        //Use cursor
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document cur = cursor.next();
                System.out.println(cur);
            }

        } finally {
            cursor.close();
        }

    }
}
