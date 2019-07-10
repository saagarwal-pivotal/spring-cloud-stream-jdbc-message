package com.pivotal.microservices.customerorder.messagesource;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = MessageSourceApplication.class,
    properties = "spring.datasource.url=jdbc:h2:mem:test",
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@AutoConfigureMessageVerifier
@DirtiesContext
public class MessageSourceTestBase {

  //This method is required by spring cloud contract
  public void stubbedProduceMessage() {
    //NOOP
  }
}
