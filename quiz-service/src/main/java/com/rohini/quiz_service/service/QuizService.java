package com.rohini.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rohini.quiz_service.model.QuestionWrapper;
import com.rohini.quiz_service.model.Quiz;
import com.rohini.quiz_service.model.Response;
import com.rohini.quiz_service.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {
	
	private final QuizRepository quizRepository;
//	private final QuestionRepository questionRepository;
	
	public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
		try {
			
//			List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);
//			System.out.println(questions);
//			Quiz quiz = new Quiz();
//			quiz.setTitle(title);
//			quiz.setQuestion(questions);
//			quizRepository.save(quiz);
			
			return new ResponseEntity<>("success",HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		try {
//			Optional<Quiz> quiz = quizRepository.findById(id);
//			
//			List<Question> questionsList = quiz.get().getQuestion(); 
			
			List<QuestionWrapper> questionWrappers = new ArrayList<>();
			
//			for (Question q : questionsList) {
//				QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//				questionWrappers.add(qw);
//			}
			
			
			return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
//		Quiz quiz = quizRepository.findById(id).get();
//		List<Question> questions = quiz.getQuestion();
		int right = 0;
//		int i = 0;
		
//		for (Response response : responses) {
//			
//			if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
//				right++;
//			}
//			i++;
//			
//		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
