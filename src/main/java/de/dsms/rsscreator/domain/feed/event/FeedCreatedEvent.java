package de.dsms.rsscreator.domain.feed.event;

import de.dsms.rsscreator.domain.event.GenericApplicationEvent;
import de.dsms.rsscreator.domain.feed.entity.Feed;

public class FeedCreatedEvent extends GenericApplicationEvent<Feed> {
    public FeedCreatedEvent(Feed object) {
        super(object);
    }
}
