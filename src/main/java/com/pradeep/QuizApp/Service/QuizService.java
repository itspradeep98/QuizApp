package com.pradeep.QuizApp.Service;

import com.pradeep.QuizApp.Entities.QuestionWrapper;
import com.pradeep.QuizApp.Entities.Questions;
import com.pradeep.QuizApp.Entities.Quiz;
import com.pradeep.QuizApp.Entities.QuizResponse;
import com.pradeep.QuizApp.Repositories.QuestionRepo;
import com.pradeep.QuizApp.Repositories.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Questions> questions = questionRepo.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Questions> questionsList = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();
        for(Questions q : questionsList){
            QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionWrapperList.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrapperList, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Questions> questions = quiz.getQuestions();

        int i = 0;
        int right = 0;
        for(QuizResponse response: responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
