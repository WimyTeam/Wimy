package com.wimyteam.wimy.service;

import com.wimyteam.wimy.domain.wiki.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WikiRandomServiceTest {

  private Logger log = LoggerFactory.getLogger(WikiRandomServiceTest.class);

  @Autowired
  WikiRandomService randomService;

  @Test
  public void getRandomArticles_returnsFourArticles_nsMain() throws IOException {
    List<Article> articles = randomService.getRandomArticles(4);
    assertThat(articles, instanceOf(List.class));
    assertThat(articles.get(0), instanceOf(Article.class));
    assertEquals(4, articles.size());
    assertNotNull(articles.get(0).getTitle());
    assertEquals(0, articles.get(0).getNamespace());
    for (Article article : articles) {
      log.info(article.toString());
    }
  }
}