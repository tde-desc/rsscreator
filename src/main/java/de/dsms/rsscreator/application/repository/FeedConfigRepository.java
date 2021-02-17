package de.dsms.rsscreator.application.repository;

import de.dsms.rsscreator.application.model.FeedConfig;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FeedConfigRepository extends PagingAndSortingRepository<FeedConfig, String> {
}
