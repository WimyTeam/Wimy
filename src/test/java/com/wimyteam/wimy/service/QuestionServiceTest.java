package com.wimyteam.wimy.service;

import com.wimyteam.wimy.domain.Question;
import com.wimyteam.wimy.domain.wiki.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class QuestionServiceTest {

  private Logger log = LoggerFactory.getLogger(QuestionServiceTest.class);

  @Autowired
  QuestionService questionService;

  @Test
  public void generateQuestion() throws IOException {
    Question question = questionService.generateQuestion(4);
    log.info(question.toString());
    assertNotNull(question);
    assertNotNull(question.getImageUrl());
    assertNotEquals("", question.getImageUrl());
    assertEquals(4, question.getOptions().length);
    assertThat(question.getOptions()[0], allOf(notNullValue(), instanceOf(Article.class)));
    assertNotEquals(0, question.getCorrectOptionArticleId());
  }


}