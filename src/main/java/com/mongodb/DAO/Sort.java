package com.mongodb.DAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class Sort {
    public static void main(String[] args) {
        //Sort
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase db = client.getDatabase("course");

        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                collection.insertOne(new Document()
                .append("i", i)
                .append("j", j));
            }
        }

        Bson projection = Projections.fields(Projections.include("i", "j"), Projections.excludeId());

//        Bson sort = new Document("i", 1).append("j", -1);

        Bson sort = Sorts.orderBy(Sorts.ascending("i"), Sorts.descending("j"));

        List<Document> all = collection.find()
                .projection(projection)
                .sort(sort)
                .limit(50)
                .into(new ArrayList<Document>());

        for (Document cur:all
                ) {
            System.out.println(cur);
        }

    }
}
