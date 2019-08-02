package com.edu.log;

import com.edu.model.Log;
import com.edu.utils.JsonUtils;
import com.edu.utils.MongoDBUtils;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class LogTest {
    private MongoClient mongoClient;

    @Before
    public void initMongoClient() throws IOException {
        mongoClient = MongoDBUtils.initMongo();
    }

    @Test
    public void saveLogTest() {
        Log log = initLog();
        mongoClient.getDatabase("LogTest").getCollection("log").insertOne(Document.parse(JsonUtils.objectToJson(log)));
    }

    @Test
    public void queryLogTest() {
        FindIterable<Document> iterable = mongoClient.getDatabase("LogTest").getCollection("log").find();
        iterable.forEach((Block<? super Document>) document -> System.out.println(document.toJson()));
    }

    @After
    public void closeMongoClient() {
        mongoClient.close();
    }

    private Log initLog(){
        NullPointerException exception = new NullPointerException("----------test--------------");
        Log log = new Log();
        log.setCreateDate(new Date());
        log.setLevel("Error");
        log.setMessage(exception.getMessage());
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        log.setStacTrace(sw.getBuffer().toString());
        return log;
    }
}
