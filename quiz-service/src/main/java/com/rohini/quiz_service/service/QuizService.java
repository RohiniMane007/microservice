package com.rohini.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohini.quiz_service.feign.QuizInterface;
import com.rohini.quiz_service.model.QuestionWrapper;
import com.rohini.quiz_service.model.Quiz;
import com.rohini.quiz_service.model.Response;
import com.rohini.quiz_service.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {
	
	private final QuizRepository quizRepository;
	private final QuizInterface quizInterface;
	
	public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
		try {
			
			List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
			
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestionIds(questions);
			quizRepository.save(quiz);
			
			return new ResponseEntity<>("success",HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		try {
			Optional<Quiz> quiz = quizRepository.findById(id);
		
			List<Integer> questionsList = quiz.get().getQuestionIds(); 
			
			List<QuestionWrapper> questionWrappers = quizInterface.getQuestionsFromId(questionsList).getBody();
									
			return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Integer score = quizInterface.getScore(responses).getBody();
		return new ResponseEntity<>(score,HttpStatus.OK);
	}

}
