package com.edu.comment;

import com.edu.model.Comment;
import com.edu.model.CommentLabel;
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
import java.util.ArrayList;
import java.util.Date;

public class CommentTest {
    private MongoClient mongoClient;

    @Before
    public void initMongo() throws IOException {
        mongoClient = MongoDBUtils.initMongo();
    }

    @Test
    public void saveComment() {
        Comment comment = initComment();
        Document document = Document.parse(JsonUtils.objectToJson(comment));
        mongoClient.getDatabase("commentTest").getCollection("comment").insertOne(document);
    }

    @Test
    public void queryComment() {
        FindIterable<Document> iterable = mongoClient.getDatabase("commentTest").getCollection("comment").find().skip(0).limit(2);
        iterable.forEach((Block<? super Document>) doc -> System.out.println(doc.toString()));
    }

    @After
    public void close() {
        mongoClient.close();
    }


    private Comment initComment() {
        Comment comment = new Comment();
        ArrayList<CommentLabel> commentLabels = new ArrayList<>();
        CommentLabel commentLabel1 = new CommentLabel();
        commentLabel1.setContent("商品不错");
        CommentLabel commentLabel2 = new CommentLabel();
        commentLabel2.setContent("非常耐用");
        commentLabels.add(commentLabel1);
        commentLabels.add(commentLabel2);
        comment.setCommentLabels(JsonUtils.objectToJson(commentLabels));
        comment.setStar(4);
        comment.setContent("快递非常快，下次还会买");
        comment.setCreatedTime(new Date());
        comment.setPid(0123654);
        comment.setUsername("admin");
        return comment;
    }
}
