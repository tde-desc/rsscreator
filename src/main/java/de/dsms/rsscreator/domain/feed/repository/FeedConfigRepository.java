package de.dsms.rsscreator.domain.feed.repository;

import de.dsms.rsscreator.domain.feed.entity.FeedConfig;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedConfigRepository extends PagingAndSortingRepository<FeedConfig, String> {
}
