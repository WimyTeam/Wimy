package com.wimyteam.wimy.domain.wiki;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Article {
  private int id;

  @JsonAlias({"ns"})
  private int namespace;
  private String title;

  public Article() {
    this.id = 0;
    this.namespace = 0;
    this.title = "";
  }

  public Article(int id, int namespace, String title) {
    this.id = id;
    this.namespace = namespace;
    this.title = title;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getNamespace() {
    return namespace;
  }

  public void setNamespace(int namespace) {
    this.namespace = namespace;
  }

  @Override
  public String toString() {
    return String.format("{id=%d,%n namespace=%d,%n title=%s}", id, namespace, title);
  }
}
