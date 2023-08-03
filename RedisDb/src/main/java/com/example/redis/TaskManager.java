package com.example.redis;

import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class TaskManager {
	public static void main(String[] args) throws InterruptedException {
		Jedis jedis = new Jedis("100.70.10.34", 6379);
		System.out.println("Connected to Redis server: " + jedis.ping());
		
		jedis.set("counter", jedis.get("counter"));
		Scanner sc = new Scanner(System.in);
		int ch=1;
		while(ch!=0) {
			System.out.print("Enter Task: ");
			String s = sc.nextLine();
			System.out.print("Enter Deadline: ");
			String time = sc.nextLine(); 
			int cnt = (Integer.parseInt(jedis.get("counter"))+1);
			jedis.hset("taskList"+cnt, "Id", Integer.toString(cnt));
			jedis.hset("taskList"+cnt, "Task", s);
			jedis.hset("taskList"+cnt, "Target", time);
			jedis.set("counter", Integer.toString(cnt)); 
			System.out.print("Press 1 to continue 0 to Exit: ");
			ch=sc.nextInt();
			sc.nextLine();
		}
		System.out.println("Displaying all task:");
		int cnt=Integer.parseInt(jedis.get("counter"));
		int j=1;
		while(j<=cnt) {
			System.out.println(jedis.hgetAll("taskList"+j));
			j++;
		}
		
		System.out.println("Enter Id to delete task:");
		int id=sc.nextInt();
		jedis.del("taskList"+id);
		int k=1;
		while(k<=cnt) {
			System.out.println(jedis.hgetAll("taskList"+k));
			k++;
		}
		jedis.close();
	}
}
