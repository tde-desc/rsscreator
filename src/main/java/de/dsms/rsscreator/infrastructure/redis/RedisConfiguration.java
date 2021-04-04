package de.dsms.rsscreator.infrastructure.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RedisConfiguration {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() throws URISyntaxException {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        String redisUrl = System.getenv("STACKHERO_REDIS_URL_TLS");
        if (StringUtils.isNotBlank(redisUrl)) {
            URI redisURI = new URI(redisUrl);
            RedisStandaloneConfiguration config = jedisConnectionFactory.getStandaloneConfiguration();
            config.setHostName(redisURI.getHost());
        }
        return jedisConnectionFactory;
    }

}
