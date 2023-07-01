package com.example.Web.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequestMapping
public class PathConfig {
	@GetMapping("/home")
	public String getHome(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		return "index";
	}
}