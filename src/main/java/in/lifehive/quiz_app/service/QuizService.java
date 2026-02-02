package in.lifehive.quiz_app.service;

import in.lifehive.quiz_app.dao.QuestionDao;
import in.lifehive.quiz_app.dao.QuizDao;
import in.lifehive.quiz_app.model.Question;
import in.lifehive.quiz_app.model.QuestionWrapper;
import in.lifehive.quiz_app.model.Quiz;
import in.lifehive.quiz_app.model.QuizResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    QuizDao dao;
    QuestionDao questionDao;

    public QuizService(QuizDao dao, QuestionDao questionDao) {
        this.dao = dao;
        this.questionDao = questionDao;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsList(questions);
        dao.save(quiz);
        return ResponseEntity.ok().body("Success");
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id) {
        Optional<Quiz> quiz = dao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestionsList();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q: questionsFromDB) {
            questionsForUser.add(
                    new QuestionWrapper(
                            q.getId(),
                            q.getQuestionTitle(),
                            q.getOption1(),
                            q.getOption2(),
                            q.getOption3(),
                            q.getOption4()
                    )
            );
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Long id, List<QuizResponse> quizResponse) {
         Optional<Quiz> quiz = dao.findById(id);
         List<Question> questionsFromDB = quiz.get().getQuestionsList();
         int right = 0;
         int i = 0;
         for(QuizResponse response: quizResponse) {
             if(response.getResponse().equals(questionsFromDB.get(i).getRightAnswer()))
                 right++;
             i++;
         }
         return ResponseEntity.ok().body(right);

    }
}
