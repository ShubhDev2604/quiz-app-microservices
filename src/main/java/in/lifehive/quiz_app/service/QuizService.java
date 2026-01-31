package in.lifehive.quiz_app.service;

import in.lifehive.quiz_app.dao.QuestionDao;
import in.lifehive.quiz_app.dao.QuizDao;
import in.lifehive.quiz_app.model.Question;
import in.lifehive.quiz_app.model.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    QuizDao dao;
    QuestionDao questionDao;

    public QuizService(QuizDao dao) {
        this.dao = dao;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsList(questions);
        dao.save(quiz);
        return ResponseEntity.ok().body("Success");
    }
}
