package com.wimyteam.wimy.controller;

import com.wimyteam.wimy.domain.Question;
import com.wimyteam.wimy.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class QuestionController {

  private final QuestionService questionService;

  @Autowired
  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @GetMapping("/question")
  public Question getQuestion(
      @RequestParam(defaultValue = "4") int options
  ) throws IOException {
    return questionService.generateQuestion(options);
  }
}
