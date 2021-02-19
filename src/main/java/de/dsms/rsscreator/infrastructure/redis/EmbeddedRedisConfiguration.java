package de.dsms.rsscreator.infrastructure.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Configuration
@Profile("embedded")
public class EmbeddedRedisConfiguration {

    private RedisServer redisServer;

    public EmbeddedRedisConfiguration(RedisProperties properties) {
        this.redisServer = new RedisServer(properties.getPort());
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
        log.info("Embedded Redis server started");
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
