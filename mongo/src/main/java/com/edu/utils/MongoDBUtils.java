package com.edu.utils;

import com.mongodb.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取mongoClient工具类
 * @author Alex.Xiao
 * @since 2019-08-02
 */
public class MongoDBUtils {
    public static MongoClient initMongo() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = MongoDBUtils.class.getClass().getResourceAsStream("/mongo-config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            WriteConcern writeConcern = new WriteConcern(Integer.valueOf(properties.getProperty("write")),
                    Integer.valueOf(properties.getProperty("writeTimeout")));
            writeConcern.withJournal(Boolean.valueOf(properties.getProperty("journal")));
            MongoClientOptions.Builder builder = MongoClientOptions.builder()
                    .writeConcern(writeConcern)
                    .connectionsPerHost(Integer.valueOf(properties.getProperty("connectionsPerHost")))
                    .connectTimeout(Integer.valueOf(properties.getProperty("connectTimeout")))
                    .cursorFinalizerEnabled(Boolean.valueOf(properties.getProperty("cursorFinalizerEnabled")))
                    .maxWaitTime(Integer.valueOf(properties.getProperty("maxWaitTime")))
                    .threadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(properties.getProperty("threadsAllowedToBlockForConnectionMultiplier")))
                    .socketTimeout(Integer.valueOf(properties.getProperty("socketTimeout")))
                    .socketKeepAlive(Boolean.valueOf(properties.getProperty("socketKeepAlive")));
            if (Boolean.valueOf("readSecondary")) {
                ReadPreference readPreference = ReadPreference.secondaryPreferred();
                builder.readPreference(readPreference);
            }
            String[] url = properties.getProperty("hostConfString").split(":");
            ServerAddress serverAddress = new ServerAddress(url[0], Integer.valueOf(url[1]));
            return new MongoClient(serverAddress, builder.build());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
