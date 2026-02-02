package in.lifehive.quiz_app.controller;

import in.lifehive.quiz_app.model.QuestionWrapper;
import in.lifehive.quiz_app.model.QuizResponse;
import in.lifehive.quiz_app.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return service.createQuiz(category, numQ, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Long id) {
        return service.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id, @RequestBody List<QuizResponse> quizResponse) {
        return service.calculateResult(id, quizResponse);
    }
}
