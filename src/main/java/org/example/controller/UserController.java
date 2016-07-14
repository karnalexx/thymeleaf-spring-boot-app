package org.example.controller;

import java.util.Collections;
import java.util.List;

import org.example.domain.Role;
import org.example.domain.User;
import org.example.repository.UserRepository;
import org.example.repository.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> register(@RequestBody User user) {
		User existedUser = userRepository.findByUsername(user.getUsername());
		if (existedUser != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"User already exists!\"}");
		}
		user.setRole(Role.USER);
		userRepository.save(user);
		return ResponseEntity.ok("{\"message\":\"User successfuly registered!\"}");
	}
		
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search() {
		return "search";
	}
		
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<User> search(@RequestBody User user) {	
		List<User> users = userRepository.findAll(new UserSpecification(user).matchUserFields());		
		return users;
	}
}
