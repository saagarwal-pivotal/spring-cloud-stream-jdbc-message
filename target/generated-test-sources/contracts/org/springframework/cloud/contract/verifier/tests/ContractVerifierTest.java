package org.springframework.cloud.contract.verifier.tests;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pivotal.microservices.customerorder.messagesource.MessageSourceTestBase;
import java.io.StringReader;
import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.messaging.util.ContractVerifierMessagingUtil.headers;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.fileToBytes;

public class ContractVerifierTest extends MessageSourceTestBase {

	@Inject ContractVerifierMessaging contractVerifierMessaging;
	@Inject ContractVerifierObjectMapper contractVerifierObjectMapper;

	@Test
	public void validate_shouldProduceValidateInboundMessage() throws Exception {
		// when:
			stubbedProduceMessage();

		// then:
			ContractVerifierMessage response = contractVerifierMessaging.receive("x_inboundmsg");
			assertThat(response).isNotNull();
			DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response.getPayload()));
			assertThatJson(parsedJson).field("['orderId']").matches("-?(\\d+)");
			assertThatJson(parsedJson).field("['storeId']").matches("-?(\\d+)");
			assertThatJson(parsedJson).field("['xInboundId']").matches("[a-zA-Z0-9]+");
	}

}
