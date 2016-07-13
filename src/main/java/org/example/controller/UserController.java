package org.example.controller;

import java.util.List;
import org.example.domain.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/users")
	public ModelAndView getAllUsers() {
		ModelAndView modelAndView = new ModelAndView("users");
		List<User> users = userRepository.findAll();
		modelAndView.addObject("users", users);
		return modelAndView;
	}

}
