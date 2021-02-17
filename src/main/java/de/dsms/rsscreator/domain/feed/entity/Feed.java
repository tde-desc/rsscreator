package de.dsms.rsscreator.domain.feed.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Feed {
    @Id
    private String id;
    private String rss;
}
