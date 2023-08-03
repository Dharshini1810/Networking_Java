package com.example.redis;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;

public class RedisPractiseSet {
	public static void main(String[] args) throws InterruptedException {
		Jedis jedis = new Jedis("100.70.10.34", 6379);
		System.out.println("Connected to Redis server: " + jedis.ping());
		
		//Set operation
		//sadd,srem,smembers,scard,sismember,sunion,sinter,sunionstore,sdiff,sinterstore,spop
		System.out.println("Set Operation: ");
		System.out.println("Add operation: "+jedis.sadd("SampleSet", "Hello"));
		System.out.println("Add operation: "+jedis.sadd("SampleSet", "Hello"));
		
		System.out.println("Add operation: "+jedis.sadd("SampleSet", "Welcome"));
		
		System.out.println(jedis.smembers("SampleSet"));
		
		//remove operator
		System.out.println("Remove operation: "+jedis.srem("SampleSet", "Hello"));
		
		System.out.println(jedis.smembers("SampleSet"));
		
		//Is Member
		System.out.println("Is member operation: "+jedis.sismember("SampleSet","Welcome"));
		System.out.println("Is member operation: "+jedis.sismember("SampleSet","Hello"));
		
		
		//No of members in set
		System.out.println("No of members in set: "+jedis.scard("SampleSet"));
		
		//Sunion and Sinter
		jedis.sadd("SET1", "1");
		jedis.sadd("SET1", "Welcome"); 
		jedis.sadd("SET1", "2");
		
		System.out.println("Union operation: "+jedis.sunion("SampleSet","SET1")); 
		System.out.println("Intersection operation: "+jedis.sinter("SampleSet","SET1"));
		
		
		//Randomly pops element
		System.out.println("Pop operation: "+jedis.spop("SET1"));
		System.out.println(jedis.smembers("SET1"));
		System.out.println();
		
		
		//SortedSet Operation: 
		//zadd,zcard,zrange,zscore,zrank,zrevrank,zrem
		System.out.println("Sorted Set Operation: ");
		System.out.println("Add operation: "+jedis.zadd("SampleSortedSet", 1, "mem1"));
		System.out.println("Add operation: "+jedis.zadd("SampleSortedSet", 2, "mem2"));
		System.out.println("Add operation: "+jedis.zadd("SampleSortedSet", 3, "mem3"));
		
		//display all values
		System.out.println(jedis.zrange("SampleSortedSet", 0, -1));
		
		//return no of values
		System.out.println("No of elements in set: "+jedis.zcard("SampleSortedSet"));
		
		
		//To get score of a member 
		System.out.println("Score of a member: "+jedis.zscore("SampleSortedSet","mem2"));
		
		//To get rank/index of particular member
		System.out.println("Index of member: "+jedis.zrank("SampleSortedSet", "mem3"));
		
		//To get rank/index of particular member
		System.out.println("Index of member: "+jedis.zrevrank("SampleSortedSet", "mem3"));
		
		//To remove particular member of set 
		System.out.println("Remove operation: "+jedis.zrem("SampleSortedSet", "mem2"));
		
		
		//display all values
		System.out.println(jedis.zrange("SampleSortedSet", 0, -1));
		jedis.close();
	}
}
