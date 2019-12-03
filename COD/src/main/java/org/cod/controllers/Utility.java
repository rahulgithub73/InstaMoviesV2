package org.cod.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.cod.entity.MoviesEntity;
import org.cod.entity.RecentlyActivityEntity;
import org.cod.entity.SubSeriesEntity;
import org.cod.repository.MovieRepository;
import org.cod.repository.MoviesCategoryRepository;
import org.cod.repository.RecentlyActivityRepository;
import org.cod.repository.SubSeriesRepository;
import org.cod.vo.RecentlyActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Utility {

	@Autowired
	SubSeriesRepository subSeriesRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	RecentlyActivityRepository recentlyActivityRepository;

	@Autowired
	MoviesCategoryRepository moviesCategoryRepository;

	public List<RecentlyActivityVO> findByUserId(Long userId) {

		List<RecentlyActivityEntity> list = recentlyActivityRepository.findByUserId(userId);
		if (list == null || list.size() == 0) {
			return null;
		}

		List<RecentlyActivityVO> finalList = new ArrayList<RecentlyActivityVO>();
		for (RecentlyActivityEntity ra : list) {

			if (ra != null && ra.getType() != null && ra.getActivityId() != null) {
				RecentlyActivityVO recentlyActivityVO = new RecentlyActivityVO();
				recentlyActivityVO.setStartTime(ra.getStartTime());
				recentlyActivityVO.setType(ra.getType());

				if (ra.getType().equals("movie")) {
					Optional<MoviesEntity> dbObject = movieRepository.findById(ra.getActivityId());
					if (dbObject.isPresent()) {
						MoviesEntity ob = dbObject.get();
						if (ob != null) {
							recentlyActivityVO.setId(ob.getId());
							recentlyActivityVO.setThumpnailPath(ob.getThumpnailPath());
							recentlyActivityVO.setRuntimeMinutes(ob.getRuntimeMinutes());
							recentlyActivityVO.setName(ob.getName());
							recentlyActivityVO.setImdbRating(ob.getImdbRating());
							recentlyActivityVO.setReleasaeYear(ob.getReleasaeYear());

						}

					}

				} else if (ra.getType().equals("series")) {

					Optional<SubSeriesEntity> dbObject = subSeriesRepository.findById(ra.getActivityId());
					if (dbObject.isPresent()) {
						SubSeriesEntity ob = dbObject.get();
						if (ob != null) {
							recentlyActivityVO.setId(ob.getId());
							recentlyActivityVO.setThumpnailPath(ob.getThumpnailPath());
							recentlyActivityVO.setRuntimeMinutes(ob.getRuntimeMinutes());
							recentlyActivityVO.setName(ob.getName());
							recentlyActivityVO.setImdbRating(ob.getImdbRating());
							recentlyActivityVO.setReleasaeYear(ob.getReleasaeYear());

						}
					}

				} else {
					continue;
				}

				finalList.add(recentlyActivityVO);

			}

		}

		return finalList;

	}

}
