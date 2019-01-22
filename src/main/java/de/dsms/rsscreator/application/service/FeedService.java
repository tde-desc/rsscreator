package de.dsms.rsscreator.application.service;

import de.dsms.rsscreator.application.model.Feed;
import de.dsms.rsscreator.application.model.FeedConfig;
import de.dsms.rsscreator.application.repository.FeedConfigRepository;
import de.dsms.rsscreator.application.repository.FeedRepository;
import de.dsms.rsscreator.application.service.feedcreation.FeedCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedConfigRepository feedConfigRepository;
    private final FeedRepository feedRepository;
    private final FeedCreator feedCreator;

    //@Scheduled(fixedDelay = 3600000L)
    public void schedule() {
        feedRepository.deleteAll();
        feedConfigRepository.findAll().forEach(this::createFeed);
    }

    private void createFeed(FeedConfig feedConfig) {
        Optional<String> feedString = feedCreator.createFeed(feedConfig);
        if (!feedString.isPresent()) {
            return;
        }
        Feed feed = new Feed();
        feed.setRss(feedString.get());
        feed.setId(feedConfig.getId());
        feedRepository.save(feed);
    }
}
