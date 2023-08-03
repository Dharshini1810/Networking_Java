package com.example.redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SchedularPoolConfig{

    public static void main(String[] args){
    	JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(3);
        poolConfig.setMaxIdle(2);
        ExecutorService executorService = Executors.newScheduledThreadPool(8);
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(new InsertTask(poolConfig), 2, 3, TimeUnit.SECONDS);
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(new InsertTask(poolConfig), 2, 3, TimeUnit.SECONDS);
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(new InsertTask(poolConfig), 2, 3, TimeUnit.SECONDS);
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(new InsertTask(poolConfig), 2, 3, TimeUnit.SECONDS);
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(new InsertTask(poolConfig), 2, 3, TimeUnit.SECONDS);
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(new InsertTask(poolConfig), 2, 3, TimeUnit.SECONDS);
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(new InsertTask(poolConfig), 2, 3, TimeUnit.SECONDS);
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(new InsertTask(poolConfig), 2, 3, TimeUnit.SECONDS);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown(); 
        
    }
   
	private static class InsertTask implements Runnable {
		JedisPoolConfig poolConfig;
		public InsertTask(JedisPoolConfig poolConfig2) {
			this.poolConfig = poolConfig2;
		}
        @Override
        public void run() { 
            try (JedisPool jedisPool = new JedisPool(poolConfig, "100.70.10.34", 6379)) {
        	 try (Jedis jedis = jedisPool.getResource()) {
                 System.out.println(Thread.currentThread().getName() + " is using a Jedis connection.");
                 String value = jedis.get("SampleThread");
                 System.out.println("Value of " + Thread.currentThread().getName() + ": " + value);
             } catch (Exception e) {
                 e.printStackTrace();
             }
            }
        }
    }
}
