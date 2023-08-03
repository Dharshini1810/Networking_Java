package com.example.redis;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;

public class RedisPractise {
	public static void main(String[] args) throws InterruptedException {
		Jedis jedis = new Jedis("100.70.10.34", 6379);
		System.out.println("Connected to Redis server: " + jedis.ping());
		
		//String operation
		//set,get,strlen,incr,incrby,decr,decrby,getrange,mset,mget,setex,expire
		System.out.println("String Operations: ");
		
		jedis.set("Name","Dharshini");
		System.out.println("Name: "+jedis.get("Name"));
		
		System.out.println("Get by range: "+jedis.getrange("Name", 0, 6));
		
		jedis.mset("Id","01","Email","dharshu1810@gmail.com");
		System.out.println("Multiple set: "+jedis.mget("Id","Email"));
		
		jedis.set("i", "1");
		jedis.incrBy("i", 3);
		System.out.println("Iterator: "+jedis.get("i"));
		
		System.out.println("Length fn: "+jedis.strlen("Name"));
		
		jedis.setex("Pswd", 5, "12345678");
		System.out.println("TTL variable Pswd: "+jedis.get("Pswd"));
		
		Thread.sleep(7000);
		System.out.println("TTL variable Pswd: "+jedis.get("Pswd"));
		System.out.println();
		
		//List operation
		//lpush,rpush,lpop,rpop,del,lindex,lrange,lpushx,rpushx,lset
		System.out.println("List Operations: ");
		jedis.del("countryList");
		jedis.lpush("countryList", "UAE");
		jedis.lpush("countryList", "USA");
		jedis.lpush("countryList","UK");
		jedis.lpush("countryList", "London");
		
		//Push element to bottom of list
		jedis.rpush("countryList", "India");
		
		System.out.println("Display operation: ");
		long len = jedis.llen("countryList");
		for(long i=0;i<len;i++) {
			System.out.println(jedis.lindex("countryList", i));
		}
		
		//lrange operation
		System.out.println(jedis.lrange("countryList", 0, -1));
		
		//pop operation
		jedis.lpop("countryList");
		jedis.rpop("countryList");
		
		System.out.println(jedis.lrange("countryList", 0, -1));
		
		//Lpushx
		System.out.println("List exist: "+jedis.lpushx("countryList", "Belgium"));
		System.out.println("List doesn't exist: "+jedis.lpushx("Country", "Paris"));
	
		jedis.close();
	}
}
