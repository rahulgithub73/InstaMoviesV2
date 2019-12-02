package org.cod.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cod.entity.RecentlyActivityEntity;
import org.cod.repository.RecentlyActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecentlyActivityController {

	@Autowired
	RecentlyActivityRepository recentlyActivityRepository;

	@PostMapping(value = "/saveRecentlyActivity")

	public ResponseEntity<Map<String, String>> saveRecentlyActivity(
			@RequestBody RecentlyActivityEntity recentlyActivityEntity, HttpServletRequest request) {

		if (recentlyActivityEntity != null) {
			RecentlyActivityEntity recentlyActivityEntityDb = recentlyActivityRepository
					.findByActivityIdAndType(recentlyActivityEntity.getActivityId(), recentlyActivityEntity.getType());
			if (recentlyActivityEntityDb != null) {
				recentlyActivityEntityDb.setStartTime(recentlyActivityEntity.getStartTime());
				recentlyActivityRepository.save(recentlyActivityEntityDb);
			} else {
				recentlyActivityRepository.save(recentlyActivityEntity);
			}
		}
		Map<String, String> m = new HashMap<String, String>();
		m.put("ok", "ok");

		return new ResponseEntity<>(m, HttpStatus.OK);
	}

}
