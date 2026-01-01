package com.rohini.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohini.questionservice.model.Question;
import com.rohini.questionservice.model.QuestionWrapper;
import com.rohini.questionservice.model.Response;
import com.rohini.questionservice.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	final private QuestionRepository questionRepository;
	
	public List<Question> getAllQuestion() {
		try {
			return questionRepository.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
		
	}
	
	public List<Question> getQuestionByCategory(String category) {
		try {
			return questionRepository.findByCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
		
	}
	
	
	public String addQuestion(Question question) {
		try {
			questionRepository.save(question);
			return "success";
		} catch (Exception e) {
			return "fail";
		}
		
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		try {
			List<Integer> questions = questionRepository.findRandomQuestionsByCategory(categoryName, numQuestions);
			return new ResponseEntity<>(questions,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionId) {
		try {
			List<QuestionWrapper> wrappers = new ArrayList<>();
			List<Question> questions = new ArrayList<>();
			
			for (Integer id : questionId) {
				questions.add(questionRepository.findById(id).get());
			}
			
			for (Question q : questions) {
//				QuestionWrapper qw = new QuestionWrapper(q.getId(), 
//														q.getQuestionTitle(), 
//														q.getOption1(), 
//														q.getOption2(), 
//														q.getOption3(), 
//														q.getOption4());
				
				QuestionWrapper qw = new QuestionWrapper();
				qw.setId(q.getId());
				qw.setQuestionTitle(q.getQuestionTitle());
				qw.setOption1(q.getOption1());
				qw.setOption2(q.getOption2());
				qw.setOption3(q.getOption3());
				qw.setOption4(q.getOption4());
				wrappers.add(qw);
			}
			
			return new ResponseEntity<>(wrappers,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	public ResponseEntity<Integer> calculateResult(List<Response> responses) {
		int right = 0;
		
		
		for (Response response : responses) {
			Question question = questionRepository.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer())) {
				right++;
			}
			
			
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}


}
