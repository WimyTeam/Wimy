package com.wimyteam.wimy.service;

import com.wimyteam.wimy.domain.Question;
import com.wimyteam.wimy.domain.wiki.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * A service for creating {@link Question} objects from randomly
 * selected Wikipedia articles.
 * Uses {@link WikiImageService} and {@link WikiRandomService}.
 */
@Service
public class QuestionService {

  private final WikiImageService imageService;

  private final WikiRandomService randomService;

  private final Random random = new Random();

  @Autowired
  public QuestionService(WikiImageService imageService, WikiRandomService randomService) {
    this.imageService = imageService;
    this.randomService = randomService;
  }

  public Question generateQuestion(int numOptions) throws IOException {
    Article[] options;
    String correctOptionImage = "";
    int correctOptionId = 0;

    do {
      List<Article> articles = randomService.getRandomArticles(numOptions);
      options = articles.toArray(new Article[0]);
      Integer[] order = random.ints(4, 0, 4)
          .distinct()
          .limit(4)
          .boxed()
          .toArray(Integer[]::new);

      for (int i : order) {
        correctOptionId = options[i].getId();
        correctOptionImage = imageService.getPageImageUrlById(correctOptionId);
        if (correctOptionImage != null) {
          break;
        }
      }

    } while (correctOptionImage == null || correctOptionImage.equals(""));

    return new Question(correctOptionImage, options, correctOptionId);
  }

  public Question generateQuestion() throws IOException {
    return generateQuestion(4);
  }
}
