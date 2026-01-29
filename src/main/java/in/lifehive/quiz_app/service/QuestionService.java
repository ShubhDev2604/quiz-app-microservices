package in.lifehive.quiz_app.service;

import in.lifehive.quiz_app.dao.QuestionDao;
import in.lifehive.quiz_app.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    public QuestionService(QuestionDao dao) {
        this.dao = dao;
    }

    private QuestionDao dao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        if(dao.count() != 0) {
            return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
        if(dao.count() != 0) {
            return new ResponseEntity<>(dao.findAllQuestionsByCategory(category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            dao.save(question);
            return new ResponseEntity<>("Addition Done!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateQuestion(Long id,Question question) {
        if(dao.existsById(id)) {
            dao.save(question);
            return new ResponseEntity<>("Updation done!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found!", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteQuestion(Long id) {
        if(dao.existsById(id)) {
            dao.deleteById(id);
            return new ResponseEntity<>("deletion done!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found!", HttpStatus.BAD_REQUEST);
        }
    }
}
