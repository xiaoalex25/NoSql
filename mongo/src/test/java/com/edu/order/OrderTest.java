package com.edu.order;

import com.edu.model.Goods;
import com.edu.model.Order;
import com.edu.utils.JsonUtils;
import com.edu.utils.MongoDBUtils;
import com.mongodb.MongoClient;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class OrderTest {
    private MongoClient mongoClient;

    @Before
    public void initMongo() throws IOException {
        mongoClient = MongoDBUtils.initMongo();
    }

    @Test
    public void saveOrder() {
        Order order = initOrder();
        mongoClient.getDatabase("OrderTest").getCollection("order").insertOne(Document.parse(JsonUtils.objectToJson(order)));
    }

    @After
    public void close() {
        mongoClient.close();
    }


    private Order initOrder() {
        Goods goods = new Goods();
        goods.setId(4380878);
        goods.setAdInfo("<html></html>");
        goods.setGoodsInfo("商品名称：华硕FX53VD商品编号：4380878商品毛重：4.19kg商品场地：中国大陆");
        goods.setSpecificationInfos("主体系列飞行堡垒型号FX53VD颜色红黑平台Intel操作系统windows10家庭版处理器CPU类型Intel第七代酷睿CPU速度2.5GHz三级缓存6M其他说明I5-7300HQ芯片组");
        goods.setNum(10);
        goods.setPrice(1978.0);
        Order order = new Order();
        order.setCreateDate(new Date());
        order.setOrderId(123654);
        order.setUserName("zhangsan");
        order.setGoodsList(JsonUtils.objectToJson(goods));
        return order;
    }
}
