package com.wimyteam.wimy.domain;

import com.wimyteam.wimy.domain.wiki.Article;

import java.util.Arrays;

public class Question {
  private Article[] options;
  private int correctOptionArticleId;
  private String imageUrl;

  public Question() {
    this.options = new Article[4];
  }

  public Question(String imageUrl, Article[] options, int correctOptionArticleId) {
    this.imageUrl = imageUrl;
    this.options = options;
    this.correctOptionArticleId = correctOptionArticleId;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setCorrectOptionArticleId(int correctOptionArticleId) {
    this.correctOptionArticleId = correctOptionArticleId;
  }

  public int getCorrectOptionArticleId() {
    return correctOptionArticleId;
  }

  public void setOptions(Article[] options) {
    this.options = options;
  }

  public Article[] getOptions() {
    return options;
  }

  @Override
  public String toString() {
    return String.format("{%nimageUrl=%s%noptions=%s%ncorrect=%d%n}",
        imageUrl, Arrays.toString(options), correctOptionArticleId);
  }
}
