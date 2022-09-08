package com.example.main.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class QuestionForm {
	private ArrayList<Question> questions;

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
}
