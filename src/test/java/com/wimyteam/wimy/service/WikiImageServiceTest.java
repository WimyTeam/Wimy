package com.wimyteam.wimy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WikiImageServiceTest {

  @Autowired
  private WikiImageService wikiImageService;

  private Logger log = LoggerFactory.getLogger(WikiImageServiceTest.class);

  @Test
  public void returnsImageUsingTitle() {
    ResponseEntity<String> response = wikiImageService.getPageImageByTitle("Albert Einstein");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotSame("", response.getBody());
    log.info("Response body:\n" + response.getBody());
  }

  @Test
  public void returnsImageUrlUsingTitle() throws IOException {
    String url = wikiImageService.getPageImageUrlByTitle("Albert Einstein");
    log.info(url);
    assertNotSame("", url);
    assertNotNull(url);
  }

  @Test
  public void returnsImageUsingPageId() {
    ResponseEntity<String> response = wikiImageService.getPageImageById(736);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotSame("", response.getBody());
    log.info("Response body:\n" + response.getBody());
  }

  @Test
  public void returnsImageUrlUsingPageId() throws IOException {
    String url = wikiImageService.getPageImageUrlById(736);
    log.info(url);
    assertNotSame("", url);
    assertNotNull(url);
  }
}
