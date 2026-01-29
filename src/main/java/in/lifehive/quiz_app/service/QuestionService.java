package in.lifehive.quiz_app.service;

import in.lifehive.quiz_app.dao.QuestionDao;
import in.lifehive.quiz_app.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    public QuestionService(QuestionDao dao) {
        this.dao = dao;
    }

    private QuestionDao dao;

    public List<Question> getAllQuestions() {
        return dao.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return dao.findAllQuestionsByCategory(category);
    }

    public String addQuestion(Question question) {
        dao.save(question);
        return "Addition done!";
    }

    public String updateQuestion(Long id,Question question) {
        if(dao.existsById(id)) {
            dao.save(question);
            return "Updation done!";
        } else {
            return "not found!";
        }

    }

    public String deleteQuestion(Long id) {
        if(dao.existsById(id)) {
            dao.deleteById(id);
            return "deletion done!";
        } else {
            return "not found!";
        }

    }
}
