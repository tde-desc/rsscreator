package de.dsms.rsscreator.application.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

@Getter
@Setter
public class FeedConfig {
    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.PRIVATE)
    private String id;
    private String name;
    private String description;
    private String url;
    private String entryIdentifier;
    private String titleIdentifier;
    private String pictureIdentifier;
    private String authorIdentifier;
    private String descriptionIdentifier;
    private String urlIdentifier;
}
