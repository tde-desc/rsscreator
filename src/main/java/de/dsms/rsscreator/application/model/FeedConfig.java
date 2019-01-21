package de.dsms.rsscreator.application.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
public class FeedConfig {
    @Id
    private String id;
    private String title;
    private String description;
    private String link;
}
