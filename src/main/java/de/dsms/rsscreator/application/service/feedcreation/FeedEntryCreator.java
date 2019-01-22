package de.dsms.rsscreator.application.service.feedcreation;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import de.dsms.rsscreator.application.model.FeedConfig;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedEntryCreator {

    List<SyndEntry> createEntries(FeedConfig feedConfig, Date publishDate, Document document) {
        List<SyndEntry> syndEntries = new ArrayList<>();
        Elements elements = selectElement(document, feedConfig.getEntryIdentifier());
        for (Element element : elements) {
            SyndEntry syndEntry = createSyndEntry(feedConfig, element, publishDate);
            syndEntries.add(syndEntry);
        }
        return syndEntries;
    }

    private SyndEntry createSyndEntry(FeedConfig feedConfig, Element element, Date publishDate) {
        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle(getElementText(element, feedConfig.getTitleIdentifier()));
        entry.setUri(getElementText(element, feedConfig.getUrlIdentifier()));
        entry.setLink(getElementText(element, feedConfig.getUrlIdentifier()));
        entry.setAuthor(getElementText(element, feedConfig.getAuthorIdentifier()));
        entry.setDescription(createDescription(feedConfig, element));
        entry.setUpdatedDate(publishDate);
        entry.setPublishedDate(publishDate);
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

    private Elements selectElement(Element element, String cssClass) {
        return element.select("." + cssClass);
    }
}
