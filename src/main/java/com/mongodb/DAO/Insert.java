package com.mongodb.DAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

/**
 * Hello world!
 */
public class Insert {
    public static void main(String[] args) {
        //Insert
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase db = client.getDatabase("course");

        MongoCollection<Document> collection = db.getCollection("insertTest");

        collection.drop();

        Document smith = new Document("name", "Smith")
                .append("age", 30)
                .append("profession", "programmer");

        Document john = new Document("name", "john")
                .append("age", 25)
                .append("profession", "hacker");

//        collection.insertOne(smith);
        collection.insertMany(Arrays.asList(smith, john));

    }
}
