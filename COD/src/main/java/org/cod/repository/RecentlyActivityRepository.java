package org.cod.repository;

import java.util.List;

import org.cod.entity.RecentlyActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentlyActivityRepository extends JpaRepository<RecentlyActivityEntity, Long> {

	RecentlyActivityEntity findByActivityIdAndTypeAndUserId(Long activityId, String type, Long userId);
	
	List<RecentlyActivityEntity> findByUserId(Long userId);

}
