package com.pivotal.microservices.customerorder.messagesource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = MessageSourceApplication.class,
    properties = "spring.datasource.url=jdbc:h2:mem:test",
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@DirtiesContext
public class MessageSourceApplicationTests {

  @Autowired
  protected Source source;

  @Autowired
  protected MessageCollector messageCollector;

  @Autowired
  protected ObjectMapper objectMapper;

//  public static class OrderEventTests extends MessageSourceApplicationTests {

  @Test
  public void testExtraction() throws InterruptedException, IOException {
    for (int i = 0; i < 2; i++) {
      Message<?> received = messageCollector.forChannel(source.output())
          .poll(2, TimeUnit.SECONDS);
      assertNotNull(received);
      assertThat(received.getPayload(), Matchers.instanceOf(String.class));
      Map<?, ?> payload = this.objectMapper.readValue((String) received.getPayload(), Map.class);
      assertNotNull(payload.get("xInboundId"));
    }
    Message<?> received = messageCollector.forChannel(source.output()).poll(2, TimeUnit.SECONDS);
    assertNull(received);
  }
//  }
}
