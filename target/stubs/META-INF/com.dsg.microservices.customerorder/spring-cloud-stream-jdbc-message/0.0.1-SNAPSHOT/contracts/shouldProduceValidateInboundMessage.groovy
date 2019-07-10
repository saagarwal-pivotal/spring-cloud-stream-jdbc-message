package contracts

org.springframework.cloud.contract.spec.Contract.make {
    // Human readable description
    description 'Should produce valid inbound message'
    // Label by means of which the output message can be triggered
    label 'sensor1'
    // input to the contract
    input {
        // the contract will be triggered by a method
        triggeredBy('stubbedProduceMessage()')
    }
    // output message of the contract
    outputMessage {
        // destination to which the output message will be sent
        sentTo 'x_inboundmsg'
//        headers {
//            header('contentType': 'application/json')
//        }
        // the body of the output message
        body([
                xInboundId: anyAlphaNumeric(),
                storeId   : anyInteger(),
                orderId   : anyInteger()
        ])
    }
}
