package de.dsms.rsscreator.application.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import de.dsms.rsscreator.application.model.Feed;
import de.dsms.rsscreator.application.model.FeedConfig;
import de.dsms.rsscreator.application.repository.FeedConfigRepository;
import de.dsms.rsscreator.application.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedConfigRepository feedConfigRepository;
    private final FeedRepository feedRepository;
    private final FeedCreationService feedCreationService;

    //@Scheduled(fixedDelay = 3600000L)
    public void schedule() {
        feedRepository.deleteAll();
        feedConfigRepository.findAll().forEach(this::createFeed);
    }

    private void createFeed(FeedConfig feedConfig) {
        Optional<String> feedString = feedCreationService.createFeed(feedConfig);
        if (!feedString.isPresent()) {
            return;
        }
        Feed feed = new Feed();
        feed.setRss(feedString.get());
        feedRepository.save(feed);
    }
}
