package com.mateuszjanczak.demo

import com.mateuszjanczak.demo.mock.TestMock
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification

import static com.mateuszjanczak.demo.configuration.WireMock.wireMockServer

abstract class IntegrationTestBase extends Specification {

    @Shared
    TestMock testMock
    RestTemplate restTemplate

    def setup() {
        restTemplate = new RestTemplate()
    }

    def setupSpec() {
        testMock = new TestMock(wireMockServer)
        wireMockServer.start()
    }

    def cleanup() {
        wireMockServer.resetAll()
    }

    def cleanupSpec() {
        wireMockServer.stop()
    }
}
