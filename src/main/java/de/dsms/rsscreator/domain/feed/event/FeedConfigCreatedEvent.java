package de.dsms.rsscreator.domain.feed.event;

import de.dsms.rsscreator.domain.event.GenericApplicationEvent;
import de.dsms.rsscreator.domain.feed.entity.FeedConfig;

public class FeedConfigCreatedEvent extends GenericApplicationEvent<FeedConfig> {
    public FeedConfigCreatedEvent(FeedConfig object) {
        super(object);
    }
}
