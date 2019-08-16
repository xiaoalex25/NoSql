package com.edu.pipe;

import com.edu.model.AdContent;
import com.edu.model.Advertisement;
import com.edu.model.Goods;
import com.edu.model.Template;
import com.edu.util.JsonUtils;
import com.edu.util.RedisUtil;
import com.edu.util.TransCoderUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.util.SafeEncoder;
/**
 * 广告内容数据集
 */
import java.io.IOException;
import java.util.ArrayList;

public class RedisTest {
    private Jedis jedis;

    @Before
    public void initJedis() throws IOException {
        this.jedis = RedisUtil.initPool().getResource();
    }

    @Test
    public void commandTest(){
        this.jedis.flushDB();
        long t3 = System.currentTimeMillis();
        noPileLine(this.jedis);
        long t4 = System.currentTimeMillis();
        System.out.println("no pile line costs " + (t4 - t3) + "ms");

        this.jedis.flushDB();
        long t1 = System.currentTimeMillis();
        usePileLine(this.jedis);
        long t2 = System.currentTimeMillis();
        System.out.println("use pile line costs " + (t2 - t1) + "ms");

    }

    @Test(timeout = 10000)
    public void saveAdTest() {
        Pipeline pipelined = this.jedis.pipelined();
        pipelined.setex(SafeEncoder.encode("test"), 10 * 60, TransCoderUtils.encodeObject(initAdvertisement()));
        System.out.println(pipelined.syncAndReturnAll());
    }

    @Test(timeout = 10000)
    public void getAdTest() {
        Advertisement advertisement = (Advertisement) TransCoderUtils.decodeObject(this.jedis.get(SafeEncoder.encode("test")));
        System.out.println(advertisement.getId());
    }

    @Test(timeout = 10000)
    public void saveGoodsRecommandTest() {
        Pipeline pipelined = this.jedis.pipelined();
        pipelined.lpush("goods-recommand", initGoods());
        pipelined.expire("goods-recommand", 10 *60);
        System.out.println(pipelined.syncAndReturnAll());
    }

    @Test(timeout = 10000)
    public void queryGoodsRecommandTest() {
        System.out.println(this.jedis.lrange("goods-recommand", 0, 10));
    }


    @After
    public void closeJedis() {
        this.jedis.close();
    }

    /**
     * 未使用管道插入命令
     * @param jedis
     */
    private void noPileLine(Jedis jedis) {
        for (int i = 0; i < 10000; i++) {
            jedis.sadd("SetAdd", String.valueOf(i));
        }
    }

    /**
     * 使用管道插入命令
     * @param jedis
     */
    private void usePileLine(Jedis jedis) {
        Pipeline pl = jedis.pipelined();
        for (int i = 0; i < 10000; i++) {
            pl.sadd("SetAdd", String.valueOf(i));
        }
        pl.sync();
    }


    /**
     * 初始化广告
     * @return
     */
    private Advertisement initAdvertisement() {
        Template template = new Template();
        template.setId(20);
        template.setName("轮播模板");
        template.setScript("alert('轮播')");

        AdContent adContent1 = new AdContent();
        adContent1.setId(1);
        adContent1.setName("新年图书大促");
        adContent1.setSequence(1);
        adContent1.setUrl("https://books.Atest.com/");
        adContent1.setImageUrl("https://books.Aimage.com/test.jpg");
        AdContent adContent2 = new AdContent();

        adContent2.setId(1);
        adContent2.setName("手机专场满1000反50");
        adContent2.setSequence(1);
        adContent2.setUrl("https://books.Atest.com/");
        adContent2.setImageUrl("https://books.Aimage.com/test.jpg");

        ArrayList<AdContent> adContents = new ArrayList<AdContent>();
        adContents.add(adContent1);
        adContents.add(adContent2);

        Advertisement advertisement = new Advertisement();
        advertisement.setId(10001);
        advertisement.setPositionCode("home-01");
        advertisement.setTid(template.getId());
        advertisement.setAdContents(adContents);

        return advertisement;
    }

    /**
     * 初始化推荐商品信息
     * @return
     */
    private String[] initGoods() {
        Goods goods = new Goods();
        goods.setId(4380878);
        goods.setAdInfo("<html></html>");
        goods.setGoodsInfo("商品名称：华硕FX53VD编号：4380878毛重：4.19kg商品产地：中国大陆");
        goods.setSpecificationsInfo("主体系列飞行堡垒型号FX53VD颜色红黑平台Intel操作系统window10");
        String[] goodsArrays = new String[10];
        for (int i = 0; i < 10; i++) {
            goodsArrays[i] = JsonUtils.objectToJson(goods);
        }
        return goodsArrays;
    }
}
