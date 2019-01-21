package de.dsms.rsscreator.application.service;

import de.dsms.rsscreator.application.model.FeedConfig;
import de.dsms.rsscreator.application.repository.FeedConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedConfigService {

    private final FeedConfigRepository feedConfigRepository;

    public FeedConfig add(FeedConfig feedConfig) {
        return feedConfigRepository.save(feedConfig);
    }
}
