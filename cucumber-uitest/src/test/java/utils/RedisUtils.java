package utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisUtils {

	private static Jedis jedis;// 非切片额客户端连接
	private static JedisPool jedisPool;// 非切片连接池
	private static ShardedJedis shardedJedis;// 切片额客户端连接
	private static ShardedJedisPool shardedJedisPool;// 切片连接池

	
	public  static String getValidateCode(String host,int port,String key){
		initialPool(host,port);
//		System.out.println("initialPool success!");
		initialShardedPool(host,port);
		shardedJedis = shardedJedisPool.getResource();
		
		jedis = jedisPool.getResource();
		
		System.out.println(jedis.get(key));
		return jedis.get(key);
	}
	
	@Test
	public void test() {
		System.out.println(getValidateCode("10.10.29.33", 6379,"ValidateCode2_"+"15889668346l"));
	}

	/**
	 * 初始化非切片池
	 */
	private static void initialPool(String host,int port) {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);
		jedisPool = new JedisPool(config, host, port);
	}

	/**
	 * 初始化切片池
	 */
	private static void initialShardedPool(String host,int port) {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);
		// slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo(host, port, "master"));

		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

}