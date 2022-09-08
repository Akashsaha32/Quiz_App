package com.example.main.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.main.model.QuestionForm;
import com.example.main.model.Result;
import com.example.main.service.QuizService;

@Controller
public class MainController {
	
	@Autowired
	Result results;
	@Autowired
	QuizService qs;
	
	@ModelAttribute("results")
	public Result getResult() {
		return results;
	}
	
	boolean submitted = false;
	
	@GetMapping("/")
	public String home() {
		return "index.html";
	}
	
	@PostMapping("/quiz")
	public String quiz(@RequestParam String username, Model m, RedirectAttributes re) {
		if(username.trim().equals("")) {
			re.addFlashAttribute("warning", "You Must Enter Your Name...");
			return "redirect:/";
		}
		submitted = false;
		results.setUsername(username);
		
		QuestionForm qf = qs.getQuestions();
		m.addAttribute("qf", qf);
		
		return "quiz.html";
	}
	
	@PostMapping("/submit")
	public String submit(@ModelAttribute QuestionForm qf, Model m) {
		if(!submitted) {
			results.setTotalCorrect(qs.getResult(qf));
			System.out.println(results.getTotalCorrect());
			qs.saveScore(results);
			submitted = true;
		}
		return "result.html";
	}
	
	@GetMapping("/score")
	public String scoreBoard(Model m) {
		ArrayList<Result> allR = qs.scoreBoard();
		m.addAttribute("allR",allR);
		return "scoreBoard.html";
	}
}





