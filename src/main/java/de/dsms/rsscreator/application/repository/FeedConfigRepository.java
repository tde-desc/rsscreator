package de.dsms.rsscreator.application.repository;

import de.dsms.rsscreator.application.model.FeedConfig;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedConfigRepository extends PagingAndSortingRepository<FeedConfig, String> {
}
