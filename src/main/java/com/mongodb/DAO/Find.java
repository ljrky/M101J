package com.mongodb.DAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 */
public class Find {
    public static void main(String[] args) {

        //Find
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase db = client.getDatabase("course");

        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document("x", new Random().nextInt(2)).
                    append("y", new Random().nextInt(100)).
                    append("i", i));
        }

//        Bson filter = new Document("x", 0).append("y", new Document("$gt", 10));

//        Bson filter = Filters.and(Filters.eq("x",0), Filters.gt("y", 10), Filters.lt("y", 90));

        Bson filter = Filters.or(Filters.eq("x", 0), Filters.gt("y", 10), Filters.lt("y", 90));

        //Projection
//        Bson projection = new Document("y", 1).append("i", 1).append("_id", 0);

        Bson projection = Projections.exclude("x", "_id");

        List<Document> all = collection.find(filter)
                                        .projection(projection)
                                        .into(new ArrayList<Document>());

        for (Document cur:all
             ) {
            System.out.println(cur);
        }

    }
}
