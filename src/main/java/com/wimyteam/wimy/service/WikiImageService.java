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

  Logger logger = LoggerFactory.getLogger(WikiImageService.class);

  private static final int imageSize = 600;

  private static final String url =
      "https://en.wikipedia.org/w/api.php?action=query&prop=pageimages" +
          "&titles={titles}&indexpageids&pithumbsize=" + imageSize + "&format=json";

  private final RestTemplate template;

  public WikiImageService() {
    this.template = new RestTemplate();
  }

  public ResponseEntity<String> getPageImage(String pageTitle) {
    String pageTitleEncoded = URLEncoder.encode(pageTitle, StandardCharsets.UTF_8);
    logger.info("pageTitleEncoded: " + pageTitleEncoded);

    return template.getForEntity(url, String.class, pageTitleEncoded);
  }

  public String getPageImageUrl(String pageTitle) throws IOException {
    ResponseEntity<String> response = getPageImage(pageTitle);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(response.getBody());
    JsonNode pages = root.path("query").path("pages");
    JsonNode pageIds = root.path("query").path("pageids");

    return pages.get(pageIds.get(0).textValue()).path("thumbnail").path("source").textValue();
  }
}
