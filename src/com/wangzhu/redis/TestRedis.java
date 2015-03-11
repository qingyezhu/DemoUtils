package com.wangzhu.redis;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedis {

    Jedis jedis;
    JedisPool pool;

    @Before
    public void init() {
	pool = new JedisPool(new JedisPoolConfig(), "localhost");
	jedis = pool.getResource();
    }

    @Test
    public void testPing() {
	System.out.println("Servier is runnning: " + jedis.ping());
	// Servier is runnning: PONG
    }

    @After
    public void destroy() {
	jedis.disconnect();
    }

    @Test
    public void testString() {
	// 获取
	System.out.println("get===" + jedis.get("strKey"));

	// 设置
	System.out.println("set===" + jedis.set("strKey", "qingyezhu"));

	// 获取
	System.out.println("get===" + jedis.get("strKey"));

	// 删除
	System.out.println("del===" + jedis.del("strKey"));

	// get===null
	// set===OK
	// get===qingyezhu
	// del===1

    }

    @Test
    public void testList() {
	String listKey = "listKey";
	System.out.println("lpush==="
		+ jedis.lpush(listKey, "Redis", "MongoDb", "MySql", "Oracle",
			"Db2"));
	long len = jedis.llen(listKey);
	System.out.println("size===" + len);
	List<String> list = jedis.lrange(listKey, 0, len);
	System.out.println("list====" + list);
	for (int i = 0; i < len; i++) {
	    System.out.println(i + "===lindex===" + jedis.lindex(listKey, i));
	}

	System.out.println(jedis.llen(listKey));
    }
}
