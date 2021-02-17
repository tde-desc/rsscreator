package de.dsms.rsscreator.domain.feed.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@ToString
@RedisHash
@EqualsAndHashCode
public class Feed {
    @Id
    private String id;
    private String rss;
}
