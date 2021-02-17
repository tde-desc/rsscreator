package de.dsms.rsscreator.infrastructure.feed;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import de.dsms.rsscreator.application.feed.service.FeedFactory;
import de.dsms.rsscreator.domain.feed.entity.FeedConfig;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SyndFeedFactory implements FeedFactory {

    private static final String FEED_TYPE_ATOM = "atom_1.0";
    private static final String AUTHOR_DENNIS = "Dennis";
    private final SyndFeedEntryFactory syndFeedEntryFactory;

    public Optional<String> createFeed(FeedConfig feedConfig) {
        Optional<Document> documentOptional = getDocument(feedConfig);
        if (documentOptional.isEmpty()) {
            return Optional.empty();
        }

        Date publishDate = new Date();
        SyndFeed syndFeed = createSyndFeed(feedConfig, publishDate);
        syndFeed.setEntries(syndFeedEntryFactory.createEntries(feedConfig, publishDate, documentOptional.get()));
        return createString(syndFeed);
    }

    private Optional<String> createString(SyndFeed syndFeed) {
        try {
            return Optional.of(new SyndFeedOutput().outputString(syndFeed));
        } catch (FeedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Optional<Document> getDocument(FeedConfig feedConfig) {
        try {
            return Optional.of(Jsoup.connect(feedConfig.getUrl()).get());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private SyndFeed createSyndFeed(FeedConfig feedConfig, Date publishDate) {
        SyndFeed syndFeed = new SyndFeedImpl();
        syndFeed.setFeedType(FEED_TYPE_ATOM);
        syndFeed.setTitle(feedConfig.getName());
        syndFeed.setLink(feedConfig.getUrl());
        syndFeed.setAuthor(AUTHOR_DENNIS);
        syndFeed.setUri(feedConfig.getUrl());
        syndFeed.setDescription(feedConfig.getId());
        syndFeed.setPublishedDate(publishDate);
        return syndFeed;
    }
}
