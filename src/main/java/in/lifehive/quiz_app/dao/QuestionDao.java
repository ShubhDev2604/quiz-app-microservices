package in.lifehive.quiz_app.dao;

import in.lifehive.quiz_app.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {

    List<Question> findAllQuestionsByCategory(String category);
}
