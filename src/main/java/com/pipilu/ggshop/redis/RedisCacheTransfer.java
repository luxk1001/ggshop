package com.pipilu.ggshop.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author pipilu
 * @version 创建时间：2019年10月25日 上午9:54:13
 */
// @Component是一个通用的Spring容器管理的单例bean组件。
@Component
public class RedisCacheTransfer {
	@Autowired
	public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		MybatisRedisCache.setJedisConnectionFactory(jedisConnectionFactory);
	}
}
