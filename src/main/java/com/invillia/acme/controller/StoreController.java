package com.invillia.acme.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.invilla.acme.dto.StoreDto;
import com.invillia.acme.models.Store;
import com.invillia.acme.services.IStoreService;

/*
 * All The methods here should be separated in its own project, and each project will be a separated lambda function. I did
 * this way just for showing what can be done, and we have just one repository.
 * But as a micro service serverless app, we should separate each of the REST methods in its own project, we could also create an GenericApiHandler
 * that could be used as a dependency on our project, this would have all the necessary code to handle the Api Gateway request
 * */

@RestController
@EnableWebMvc
public class StoreController {

  private static Logger logger = LoggerFactory.getLogger(StoreController.class);

  @Resource private IStoreService service;

  @PostMapping(path = "/store")
  public ResponseEntity<Store> createStore(@RequestBody StoreDto dto) {
    logger.info("Inserting new store");
    return new ResponseEntity<>(service.createStore(dto.toEntity()), HttpStatus.CREATED);
  }

  @GetMapping(path = "/store/{id}")
  public ResponseEntity<Map<String, Object>> getStoreById(@PathVariable String id) {
    logger.info("Getting store information with id");
    return new ResponseEntity<>(service.getStoreById(id), HttpStatus.OK);
  }

  @PatchMapping(path = "/store/{id}")
  public ResponseEntity<Map<String, Object>> updateStore(
      @PathVariable String id, @RequestBody StoreDto dto) {
    logger.info("Updating store info");
    return new ResponseEntity<>(service.updateStore(dto, id), HttpStatus.OK);
  }
}
