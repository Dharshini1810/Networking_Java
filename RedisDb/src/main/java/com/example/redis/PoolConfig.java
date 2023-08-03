package com.example.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class PoolConfig {

	public static String getConnectionStatus(Jedis jedis) {
        if (jedis.getClient().isConnected()) {
            if (jedis.getClient().isBroken()) {
                return "Broken";
            } else {
                return "Active";
            }
        } else {
            return "Idle/Disconnected";
        }
    }
    public static void main(String[] args) throws InterruptedException {
        // Create the connection pool configuration
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5); // Maximum number of connections in the pool
        poolConfig.setMaxIdle(2); // Maximum number of idle connections in the pool
        poolConfig.setMinIdle(1); 

        // Create the Jedis connection pool
        try (JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379)) {
            // Multiple threads will share the same connection pool
            for (int i = 1; i <= 10; i++) {
                Thread thread = new Thread(() -> {
                	try (Jedis jedis = jedisPool.getResource()) {
                        System.out.println("Connection: "+getConnectionStatus(jedis)+" "+Thread.currentThread().getName()+" ClientId: "+jedis.getClient().toString());
                        String value = jedis.get("Name");
                        System.out.println("No of active connections: "+jedisPool.getNumActive());
                    	System.out.println("No of Idle connections: "+jedisPool.getNumIdle());
                        System.out.println("Connection: "+getConnectionStatus(jedis)+" "+Thread.currentThread().getName()+" ClientId: "+jedis.getClient().toString());

                        System.out.println("Value of " + Thread.currentThread().getName() + ": " + value);

                        System.out.println(Thread.currentThread().getName() + " is now returning the Jedis connection to the pool.");
                        
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, "Thread-" + i);

                thread.start();
                thread.join();
                if(jedisPool.getNumActive()==0) {
                	System.out.println("Connection: Inactive");
                	System.out.println("No of active connections: "+jedisPool.getNumActive());
                	System.out.println("No of Idle connections: "+jedisPool.getNumIdle());
                }
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
