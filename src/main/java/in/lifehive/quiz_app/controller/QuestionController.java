package in.lifehive.quiz_app.controller;

import in.lifehive.quiz_app.model.Question;
import in.lifehive.quiz_app.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/all-questions")
    public List<Question> getAllQuestions() {
        return service.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public List<Question> getAllQuestionsByCategory(@PathVariable String category) {
        return service.getAllQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public String addQuestion(@RequestBody Question question) {
        return service.addQuestion(question);
    }

    @PutMapping("/update/{id}")
    public String updateQuestion(@PathVariable Long id,@RequestBody Question question) {
        return service.updateQuestion(id, question);
    }

    @DeleteMapping("/delete/{id}")
    public String updateQuestion(@PathVariable Long id) {
        return service.deleteQuestion(id);
    }
}
