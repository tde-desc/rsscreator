package de.dsms.rsscreator.application.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Feed {
    @Id
    private String id;
    private String rss;
}
