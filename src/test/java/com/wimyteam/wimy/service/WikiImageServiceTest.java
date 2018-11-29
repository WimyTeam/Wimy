package com.wimyteam.wimy.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.Assert.*;

public class WikiImageServiceTest {
  private WikiImageService wikiImageService = WikiImageService.getInstance();
  private Logger log = LoggerFactory.getLogger(WikiImageServiceTest.class);

  @Test
  public void returnsImage() {
    ResponseEntity<String> response = wikiImageService.getPageImageByTitle("Albert Einstein");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotSame("", response.getBody());
    log.info("Response body:\n" + response.getBody());
  }

  @Test
  public void returnsImageUrl() throws IOException {
    String url = wikiImageService.getPageImageUrlByTitle("Albert Einstein");
    log.info(url);
    assertNotSame("", url);
    assertNotNull(url);
  }
}
