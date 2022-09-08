package com.example.main.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.main.Repository.QuestionRepo;
import com.example.main.Repository.ResultRepo;
import com.example.main.model.Question;
import com.example.main.model.QuestionForm;
import com.example.main.model.Result;

@Service
public class QuizService {
	@Autowired
	Question q;
	@Autowired
	QuestionForm qf;
	@Autowired
	QuestionRepo qr;
	@Autowired
	Result r;
	@Autowired
	ResultRepo rr;
	
	public QuestionForm getQuestions() {
		ArrayList<Question> allq = (ArrayList<Question>) qr.findAll();
		ArrayList<Question> rq = new ArrayList<>();
		
		Random random = new Random();
		for(int i=0; i<5; i++) {
			int rn = random.nextInt(allq.size());
			rq.add(allq.get(rn));
			allq.remove(rn);
		}
		qf.setQuestions(rq);
		return qf;
	}
	
	public int getResult(QuestionForm qf) {
		int correct = 0;
		for(Question q : qf.getQuestions()) {
			if(q.getAns() == q.getChose()) {
				correct++;
			}
		}
		return correct;
	}
	
	public void saveScore(Result result) {
		Result r = new Result();
		r.setUsername(result.getUsername());
		r.setTotalCorrect(result.getTotalCorrect());
		rr.save(r);
	}
	
	public ArrayList<Result> scoreBoard(){
		ArrayList<Result> sResult = (ArrayList<Result>) rr.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
		return sResult;
	}
}
