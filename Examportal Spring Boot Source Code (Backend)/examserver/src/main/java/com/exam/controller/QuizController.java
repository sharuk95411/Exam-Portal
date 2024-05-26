package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //add quiz service
    @PostMapping("/")
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update quiz
    @PutMapping("/")
    public ResponseEntity<Map<String,String>> update(@RequestBody Quiz quiz) {

        Long id= quiz.getqId();
        Map<String, String> response = new HashMap<>(); /* iska use hum data ko as A JSON bhjene k liye kr rhe h jo ki
        frotend me iska use ho rha h return type check krne me   */
        try {
            Long quizId = quizService.getQuizId(id);
        }
        catch (Exception e) {
            response.put("message", "QUIZ ID NOT FOUND");
        }

        this.quizService.updateQuiz(quiz);
        response.put("message","Quiz data updated successfully");
        return ResponseEntity.ok(response);

    }

    //get quiz
    @GetMapping("/")
    public ResponseEntity<?> quizzes() {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //get single quiz
    @GetMapping("/{qid}")
    public Quiz quiz(@PathVariable("qid") Long qid) {
        return this.quizService.getQuiz(qid);
    }

    //delete the quiz
    @DeleteMapping("/{qid}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("qid") Long qid) {

        Map<String, String> response = new HashMap<>(); /* iska use hum data ko as A JSON bhjene k liye kr rhe h jo ki
        frotend me iska use ho rha h return type check krne me   */
        try {
            Long quizId = quizService.getQuizId(qid);
        }
        catch (Exception e) {

            response.put("message", "Quiz Id not found");
            return ResponseEntity.ok(response);
        }

        this.quizService.deleteQuiz(qid);
        response.put("message", "Quiz data Deleted successfully");
        return ResponseEntity.ok(response);
//        return ResponseEntity.ok(new Res("Quiz data Deleted successfully"));

    }

    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid) {
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }

    //get active quizzes
    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes() {
        return this.quizService.getActiveQuizzes();
    }

    //Get active quizzes of this category
    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzes(@PathVariable("cid") Long cid) {
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }




}
