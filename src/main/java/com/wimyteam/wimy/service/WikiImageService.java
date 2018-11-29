package com.wimyteam.wimy.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WikiImageService {
  // Singleton pattern
  private static final WikiImageService INSTANCE = new WikiImageService();
  public static WikiImageService getInstance() {
    return INSTANCE;
  }

  private Logger log = LoggerFactory.getLogger(WikiImageService.class);

  private static final int IMAGE_SIZE = 600;

  private static final String URL_TITLE =
      "https://en.wikipedia.org/w/api.php?action=query&prop=pageimages" +
          "&titles={titles}&indexpageids&pithumbsize=" + IMAGE_SIZE + "&format=json";

  private final RestTemplate template;

  public WikiImageService() {
    this.template = new RestTemplate();
  }

  public ResponseEntity<String> getPageImageByTitle(String pageTitle) {
    String pageTitleEncoded = URLEncoder.encode(pageTitle, StandardCharsets.UTF_8);
    log.info("pageTitleEncoded: " + pageTitleEncoded);
    return template.getForEntity(URL_TITLE, String.class, pageTitleEncoded);
  }

  public String getPageImageUrlByTitle(String pageTitle) throws IOException {
    ResponseEntity<String> response = getPageImageByTitle(pageTitle);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(response.getBody());
    JsonNode pages = root.path("query").path("pages");
    JsonNode pageIds = root.path("query").path("pageids");

    return pages.get(pageIds.get(0).textValue()).path("thumbnail").path("source").textValue();
  }
}
