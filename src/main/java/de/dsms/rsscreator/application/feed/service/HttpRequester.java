package de.dsms.rsscreator.application.feed.service;

import java.util.Optional;

public interface HttpRequester {
    Optional<String> request(String url);
}
