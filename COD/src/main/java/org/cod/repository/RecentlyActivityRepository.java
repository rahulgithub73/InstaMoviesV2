package org.cod.repository;

import org.cod.entity.RecentlyActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentlyActivityRepository extends JpaRepository<RecentlyActivityEntity, Long> {

	RecentlyActivityEntity findByActivityIdAndType(Long activityId, String type);

}
