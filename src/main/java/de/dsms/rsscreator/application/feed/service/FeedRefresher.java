package de.dsms.rsscreator.application.feed.service;

import de.dsms.rsscreator.domain.feed.entity.Feed;
import de.dsms.rsscreator.domain.feed.entity.FeedConfig;
import de.dsms.rsscreator.domain.feed.event.FeedConfigCreatedEvent;
import de.dsms.rsscreator.domain.feed.event.FeedCreatedEvent;
import de.dsms.rsscreator.domain.feed.event.FeedRefreshStartedEvent;
import de.dsms.rsscreator.domain.feed.repository.FeedConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedRefresher {

    private final FeedConfigRepository feedConfigRepository;
    private final FeedFactory feedFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(fixedDelay = 3600000L)
    public void request() {
        feedConfigRepository.deleteAll();
        eventPublisher.publishEvent(new FeedRefreshStartedEvent());
        feedConfigRepository.findAll().forEach(this::createFeed);
    }

    @EventListener
    public void onFeedConfigCreated(FeedConfigCreatedEvent event) {
        createFeed(event.getObject());
    }

    private void createFeed(FeedConfig feedConfig) {
        Optional<String> feedString = feedFactory.createFeed(feedConfig);
        if (feedString.isEmpty()) {
            return;
        }
        var feed = new Feed(feedConfig.getId(), feedString.get());
        eventPublisher.publishEvent(new FeedCreatedEvent(feed));
    }
}
