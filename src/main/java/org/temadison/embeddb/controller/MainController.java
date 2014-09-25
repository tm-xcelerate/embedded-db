package org.temadison.embeddb.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.temadison.embeddb.service.UserService;

@Controller
public class MainController {

	private final UserService userService;

	@Value("${name}")
	private String name;

	@Inject
	public MainController(final UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name1", this.name);
		model.addAttribute("name2", name);
		model.addAttribute("name3", this.userService.findUserById(1L).getName());
		return "greeting";
	}
}
