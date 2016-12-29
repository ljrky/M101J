package com.mongodb.DAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class Update {
    public static void main(String[] args) {
        //Update
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase db = client.getDatabase("course");

        MongoCollection<Document> collection = db.getCollection("test");

        collection.drop();

        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document("_id", i)
                    .append("x", i)
                    .append("y", true));
        }

        //Replace
//        collection.replaceOne(Filters.eq("x", 5),new Document("x", 20).append("updated", true));

        //Update
//        collection.updateOne(Filters.eq("x",5), new Document("$set",
//                new Document("x", 20).append("updated", true)));

        //Update with updates method
        collection.updateOne(Filters.eq("x", 5), Updates.combine(Updates.set("x", 20), Updates.set("updated", true)));

//        collection.updateMany(Filters.gte("x", 5), Updates.inc("x", 1));

        List<Document> all = collection.find().into(new ArrayList<Document>());

        for (Document cur : all) {
            System.out.println(cur);
//        }

        }
    }
}
