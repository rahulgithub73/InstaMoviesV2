package org.cod.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.cod.entity.RecentlyActivityEntity;
import org.cod.entity.UserEntity;
import org.cod.repository.RecentlyActivityRepository;
import org.cod.repository.UserRepository;
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

	@Autowired
	UserRepository userRepository;

	@PostMapping(value = "/saveRecentlyActivity")

	public ResponseEntity<Map<String, String>> saveRecentlyActivity(
			@RequestBody RecentlyActivityEntity recentlyActivityEntity, HttpServletRequest request,
			HttpSession session) {

		Map<String, String> m = new HashMap<String, String>();
		if (session != null && session.getAttribute("mobile") != null) {
			String mobile = (String) session.getAttribute("mobile");
			UserEntity userExits = userRepository.findByPhoneNo(mobile);
			if (userExits != null && userExits.getId() != null) {
				if (recentlyActivityEntity != null) {
					recentlyActivityEntity.setUserId(userExits.getId());
					RecentlyActivityEntity recentlyActivityEntityDb = recentlyActivityRepository
							.findByActivityIdAndTypeAndUserId(recentlyActivityEntity.getActivityId(),
									recentlyActivityEntity.getType(),recentlyActivityEntity.getUserId());
					if (recentlyActivityEntityDb != null) {
						recentlyActivityEntityDb.setStartTime(recentlyActivityEntity.getStartTime());
						recentlyActivityRepository.save(recentlyActivityEntityDb);
					} else {
						recentlyActivityRepository.save(recentlyActivityEntity);
					}
				}
				m.put("status", "ok");
			} else {
				m.put("error", "user is null");
			}

		} else {
			m.put("error", "session is null");
		}

		return new ResponseEntity<>(m, HttpStatus.OK);
	}

}
