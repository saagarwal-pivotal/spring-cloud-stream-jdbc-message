package com.pivotal.microservices.customerorder.messagesource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.app.jdbc.source.JdbcSourceConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Import({JdbcSourceConfiguration.class})
@PropertySource({"classpath:jdbc-source-properties.yml"})
public class MessageSourceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MessageSourceApplication.class, args);
  }

}
