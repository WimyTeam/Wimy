package com.wimyteam.wimy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wimyteam.wimy.domain.wiki.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class WikiRandomService {

  private static final String URL =
      "https://en.wikipedia.org/w/api.php?action=query" +
          "&list=random&rnlimit={limit}&rnnamespace=0&format=json";

  private final RestTemplate template;

  public WikiRandomService() {
    this.template = new RestTemplate();
  }

  public List<Article> getRandomArticles(int numArticles) throws IOException {
    ResponseEntity<String> response = template.getForEntity(URL, String.class, numArticles);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode randomArticles = mapper.readTree(response.getBody()).path("query").path("random");
    ObjectReader reader = mapper.readerFor(new TypeReference<List<Article>>(){});
    return reader.readValue(randomArticles);
  }
}
