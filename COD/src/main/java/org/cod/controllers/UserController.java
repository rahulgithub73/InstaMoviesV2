package org.cod.controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.cod.entity.UserEntity;
import org.cod.repository.MovieRepository;
import org.cod.repository.MoviesCategoryRepository;
import org.cod.repository.RecentlyActivityRepository;
import org.cod.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	Utility utility;

	@Autowired
	RecentlyActivityRepository recentlyActivityRepository;

	@Autowired
	MoviesCategoryRepository moviesCategoryRepository;
	
	@Value("${banner.path}")
	private String bannerPath;

	@PostMapping(value = "login")
	public String login(UserEntity userEntity, Model model, @PageableDefault(size = 10) Pageable pageable,
			HttpSession session) {

		UserEntity userExits = userRepository.findByPhoneNo(userEntity.getPhoneNo());

		if (userExits == null) {
			
			userEntity.setLogin(true);
			userEntity.setCreatedTimestamp(LocalDateTime.now());
			userExits = userRepository.save(userEntity);
		}

		model.addAttribute("movies", moviesCategoryRepository.findAll());
		model.addAttribute("recentActivites", utility.findByUserId(userExits.getId()));
		model.addAttribute("bannerPath", bannerPath);
		session.setAttribute("mobile", userExits.getPhoneNo());
		
		return "index";
	}

	@GetMapping(value = "/logout")
	public String logout(Model model,HttpSession session) {
		session.removeAttribute("mobile");
		return "redirect:/";
	}

	@GetMapping(value = "/userInfo")
	public String getUserInfo(Model model) {
		return "logout";
	}

}
