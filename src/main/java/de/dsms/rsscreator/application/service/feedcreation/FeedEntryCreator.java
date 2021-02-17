package de.dsms.rsscreator.application.service.feedcreation;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import de.dsms.rsscreator.domain.feed.entity.FeedConfig;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedEntryCreator {

    List<SyndEntry> createEntries(FeedConfig feedConfig, Date publishDate, Document document) {
        List<SyndEntry> syndEntries = new ArrayList<>();
        Elements elements = selectElement(document, feedConfig.getEntryIdentifier());
        for (Element element : elements) {
            SyndEntry syndEntry = createSyndEntry(feedConfig, element, getBaseUrl(feedConfig.getUrl()));
            syndEntry.setPublishedDate(publishDate);
            syndEntry.setUpdatedDate(publishDate);
            syndEntries.add(syndEntry);
        }
        return syndEntries;
    }

    private String getBaseUrl(String uri) {
        URL url = null;
        try {
            url = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String path = url.getFile().substring(0, url.getFile().lastIndexOf('/'));
        return url.getProtocol() + "://" + url.getHost();
    }

    private SyndEntry createSyndEntry(FeedConfig feedConfig, Element element, String baseUrl) {
        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle(getElementText(element, feedConfig.getTitleIdentifier()));
        String entryUrl = baseUrl + getElementHref(element, feedConfig.getUrlIdentifier());
        entry.setUri(entryUrl);
        entry.setLink(entryUrl);
        entry.setAuthor(getElementText(element, feedConfig.getAuthorIdentifier()));
        entry.setDescription(createDescription(feedConfig, element));
        return entry;
    }

    private SyndContent createDescription(FeedConfig feedConfig, Element element) {
        SyndContent syndContent = new SyndContentImpl();
        syndContent.setType("text/html");
        syndContent.setValue(getElementHtml(element, feedConfig.getDescriptionIdentifier()));
        return syndContent;
    }

    private String getElementText(Element element, String cssClass) {
        return selectElement(element, cssClass).text();
    }

    private String getElementHtml(Element element, String cssClass) {
        return selectElement(element, cssClass).html();
    }
    private String getElementHref(Element element, String cssClass) {
        return selectElement(element, cssClass).attr("href");
    }

    private Elements selectElement(Element element, String cssSelector) {
        return element.select(cssSelector);
    }
}
