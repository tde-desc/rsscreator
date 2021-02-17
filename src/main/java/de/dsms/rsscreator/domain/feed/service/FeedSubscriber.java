package de.dsms.rsscreator.domain.feed.service;

import de.dsms.rsscreator.domain.feed.event.FeedCreatedEvent;
import de.dsms.rsscreator.domain.feed.event.FeedRefreshStartedEvent;
import de.dsms.rsscreator.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedSubscriber {
    private final FeedRepository feedRepository;

    @EventListener
    void onFeedRefreshStarted(FeedRefreshStartedEvent event) {
        log.info("Feed refresh started");
        feedRepository.deleteAll();
    }

    @EventListener
    void onFeedCreated(FeedCreatedEvent event) {
        feedRepository.save(event.getObject());
    }
}
