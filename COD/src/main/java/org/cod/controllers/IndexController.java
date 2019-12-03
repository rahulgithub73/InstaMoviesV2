package org.cod.controllers;

import javax.servlet.http.HttpSession;

import org.cod.entity.UserEntity;
import org.cod.repository.MovieRepository;
import org.cod.repository.MoviesCategoryRepository;
import org.cod.repository.RecentlyActivityRepository;
import org.cod.repository.SubSeriesRepository;
import org.cod.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Homepage controller.
 */
@Controller
public class IndexController {

	@Autowired
	Utility utility;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	SubSeriesRepository subSeriesRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RecentlyActivityRepository recentlyActivityRepository;

	@Autowired
	MoviesCategoryRepository moviesCategoryRepository;

	@Value("${banner.path}")
	private String bannerPath;

	@GetMapping("/")
	String login(UserEntity userEntity) {

		return "login";
	}

	@GetMapping("/img/favicon")
	String favicon() {
		return "forward:/img/favicon.png";
	}

	@GetMapping("/games")
	String ticTacToe() {
		return "games";
	}

	@GetMapping("/index")
	public String getEmployees(Model model, HttpSession session) {
		model.addAttribute("movies", moviesCategoryRepository.findAll());
		model.addAttribute("bannerPath", bannerPath);

		if (session != null && session.getAttribute("mobile") != null) {
			String mobile = (String) session.getAttribute("mobile");
			UserEntity userExits = userRepository.findByPhoneNo(mobile);
			if (userExits != null && userExits.getId() != null) {
				model.addAttribute("recentActivites", utility.findByUserId(userExits.getId()));
			}

		} else {
			model.addAttribute("recentActivites", null);
		}

		return "index";

	}

}
