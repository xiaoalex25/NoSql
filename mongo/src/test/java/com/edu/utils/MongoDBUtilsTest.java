package com.edu.utils;

import com.mongodb.MongoClient;
import org.junit.Test;

import java.io.IOException;

public class MongoDBUtilsTest {

    @Test
    public void initMongo() throws IOException {
        MongoClient mongoClient = MongoDBUtils.initMongo();
        assert mongoClient != null;
    }
}
