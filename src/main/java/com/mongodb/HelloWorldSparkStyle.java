package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ljrky on 2016/12/3.
 */
public class HelloWorldSparkStyle {
    public static void main( String[] args )
    {
        final Configuration configuration = new Configuration();
        try {
            configuration.setDirectoryForTemplateLoading(new File( "./src/main/resource"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoDatabase db = client.getDatabase("course");

        final MongoCollection<Document> collection = db.getCollection("hello");

        collection.drop();

        collection.insertOne(new Document("name", "MongoDB"));

        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();

                try {
                    Template template = configuration.getTemplate("hello.ftl");
                    Map<String, Object> helloMap = new HashMap<String, Object>();
//                    helloMap.put("name", "FreeMarker!!!!!!!!!!!");
//                    template.process(helloMap, writer);

                    //Use MongoDB data
                    Document document = collection.find().first();
                    template.process(document, writer);


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
                return writer;
            }
        });

        Spark.get("/test/:things", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return request.params("things");
            }
        });

        Spark.get("/fruit", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();

                try {
                    Template template = configuration.getTemplate("Fruit.ftl");
                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("fruits", Arrays.asList("apple", "orange", "banana"));
                    template.process(helloMap, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
                return writer;
            }
        });

        Spark.post("/favorite_fruit", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                final String fruit = request.queryParams("fruit");
                if(fruit == null){
                    return "Why do not you pick on?";
                }
                else {
                    return "you favorite fruit is " + fruit;
                }
            }
        });
    }
}
