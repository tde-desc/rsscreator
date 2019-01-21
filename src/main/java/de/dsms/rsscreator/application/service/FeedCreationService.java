package de.dsms.rsscreator.application.service;

import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import de.dsms.rsscreator.application.model.FeedConfig;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class FeedCreationService {

    private static final String FEED_TYPE_ATOM = "atom_1.0";
    private static final String AUTHOR_DENNIS = "Dennis";

    public Optional<String> createFeed(FeedConfig feedConfig) {
        Optional<Document> documentOptional = getDocument(feedConfig);
        if (!documentOptional.isPresent()) {
            return Optional.empty();
        }

        Date publishDate = new Date();
        SyndFeed syndFeed = createSyndFeed(feedConfig, publishDate);
        List<SyndEntry> syndEntries = new ArrayList<>();
        SyndEntry syndEntry = createSyndEntry(feedConfig, documentOptional.get(), publishDate);
        syndEntries.add(syndEntry);
        syndFeed.setEntries(syndEntries);
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

    private SyndEntry createSyndEntry(FeedConfig feedConfig, Document document, Date publishDate) {
        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle(getElementText(document, feedConfig.getTitleIdentifier()));
        entry.setUri(getElementText(document, feedConfig.getUrlIdentifier()));
        entry.setLink(getElementText(document, feedConfig.getUrlIdentifier()));
        entry.setAuthor(getElementText(document, feedConfig.getAuthorIdentifier()));
        entry.setDescription(createDescription(feedConfig, document));
        entry.setUpdatedDate(publishDate);
        entry.setPublishedDate(publishDate);
        return entry;
    }

    private SyndContent createDescription(FeedConfig feedConfig, Document document) {
        SyndContent syndContent = new SyndContentImpl();
        syndContent.setType("text/html");
        syndContent.setValue(getElementText(document, feedConfig.getDescriptionIdentifier()));
        return syndContent;
    }

    private String getElementText(Document document, String cssClass) {
        return document.select("."+cssClass).text();
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
        syndFeed.setPublishedDate(publishDate);
        return syndFeed;
    }
}
