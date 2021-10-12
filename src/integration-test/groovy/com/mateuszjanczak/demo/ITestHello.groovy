package com.mateuszjanczak.demo

import org.springframework.web.client.HttpClientErrorException

import static com.mateuszjanczak.demo.configuration.WireMock.wireMockServer

class ITestHello extends IntegrationTestBase {

    def "Should return 200"() {
        given:
        testMock.success()

        when:
        def response = restTemplate.getForEntity("${wireMockServer.baseUrl()}/hello", String.class)

        then:
        response.statusCode.value() == 200
    }

    def "Should throw exception"() {
        given:
        testMock.fail()

        when:
        restTemplate.getForEntity("${wireMockServer.baseUrl()}/hello", String.class)

        then:
        thrown(HttpClientErrorException)
    }
}
