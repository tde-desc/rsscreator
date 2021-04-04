package de.dsms.rsscreator.infrastructure.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Protocol;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RedisConfiguration {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() throws URISyntaxException {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        String redisUrl = System.getenv("REDISTOGO_URL");
        if (StringUtils.isNotBlank(redisUrl)) {
            URI redisURI = new URI(redisUrl);
            String pw = redisURI.getUserInfo().split(":", 2)[1];
            RedisStandaloneConfiguration config = jedisConnectionFactory.getStandaloneConfiguration();
            config.setPort(redisURI.getPort());
            config.setPassword(pw);
            config.setHostName(redisURI.getHost());
            jedisConnectionFactory.setTimeout(Protocol.DEFAULT_TIMEOUT);
        }
        return jedisConnectionFactory;
    }

}
