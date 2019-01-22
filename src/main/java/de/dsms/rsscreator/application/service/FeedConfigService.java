package de.dsms.rsscreator.application.service;

import de.dsms.rsscreator.application.model.FeedConfig;
import de.dsms.rsscreator.application.repository.FeedConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedConfigService {

    private final FeedConfigRepository feedConfigRepository;

    public List<FeedConfig> getAll() {
        return feedConfigRepository.findAll();
    }

    public FeedConfig getOne(String id) {
        return feedConfigRepository.findById(id).get();
    }

    public FeedConfig add(FeedConfig feedConfig) {
        return feedConfigRepository.save(feedConfig);
    }

    public FeedConfig update(FeedConfig feedConfig) {
        if (!feedConfigRepository.existsById(feedConfig.getId())) {
            throw new IllegalArgumentException("Die Id " + feedConfig.getId() + " ist unbekannt.");
        }
        return feedConfigRepository.save(feedConfig);
    }
}
