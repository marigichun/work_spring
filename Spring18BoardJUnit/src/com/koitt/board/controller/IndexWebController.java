package com.koitt.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koitt.board.service.UsersService;

@Controller
public class IndexWebController {
	
	@RequestMapping(value= {"/", "/index.do"}, method=RequestMethod.GET)
	public String index() {
		return "index";
	}
}