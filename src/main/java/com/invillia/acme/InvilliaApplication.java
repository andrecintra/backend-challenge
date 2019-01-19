package com.invillia.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.invillia.acme.controller.StoreController;

@SpringBootApplication
@Import({StoreController.class})
public class InvilliaApplication extends SpringBootServletInitializer {

  /*
   * Create required HandlerMapping, to avoid several default HandlerMapping instances being created
   */
  @Bean
  public HandlerMapping handlerMapping() {
    return new RequestMappingHandlerMapping();
  }

  /*
   * Create required HandlerAdapter, to avoid several default HandlerAdapter instances being created
   */
  @Bean
  public HandlerAdapter handlerAdapter() {
    return new RequestMappingHandlerAdapter();
  }

  public static void main(String[] args) {
    SpringApplication.run(InvilliaApplication.class, args);
  }
}
