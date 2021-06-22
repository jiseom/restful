package edu.bit.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.bit.ex.service.BoardService;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board/**")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		log.info("list().....");
		
		model.addAttribute("list", boardService.getList());
		
		return "list";
		
	}
	
	
}
