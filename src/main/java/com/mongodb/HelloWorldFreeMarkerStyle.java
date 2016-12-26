package com.mongodb;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ljrky on 2016/12/3.
 */
public class HelloWorldFreeMarkerStyle {
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration();
        try {
            configuration.setDirectoryForTemplateLoading(new File( "./src/main/resource"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Template template = configuration.getTemplate("hello.txt");
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "FreeMarker");
            template.process(helloMap, writer);
            System.out.println(writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
