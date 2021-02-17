package de.dsms.rsscreator.application.feed.service;

import de.dsms.rsscreator.application.service.feedcreation.FeedCreator;
import de.dsms.rsscreator.domain.feed.entity.Feed;
import de.dsms.rsscreator.domain.feed.entity.FeedConfig;
import de.dsms.rsscreator.domain.feed.repository.FeedConfigRepository;
import de.dsms.rsscreator.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedRequester {

    private final FeedConfigRepository feedConfigRepository;
    private final FeedRepository feedRepository;
    private final FeedCreator feedCreator;

    @Scheduled(fixedDelay = 3600000L)
    public void request() {
        feedRepository.deleteAll();
        log.info("Refreshing feeds");
        feedConfigRepository.findAll().forEach(this::createFeed);
    }

    private void createFeed(FeedConfig feedConfig) {
        Optional<String> feedString = feedCreator.createFeed(feedConfig);
        if (feedString.isEmpty()) {
            return;
        }
        Feed feed = new Feed();
        feed.setRss(feedString.get());
        feed.setId(feedConfig.getId());
        feedRepository.save(feed);
    }
}
