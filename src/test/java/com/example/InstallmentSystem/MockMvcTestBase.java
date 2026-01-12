package com.example.InstallmentSystem;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;


@ActiveProfiles("test")
@SpringJUnitConfig(classes = {InstallmentSystemApplication.class})
@ImportAutoConfiguration({FeignAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public abstract class MockMvcTestBase {

    @Autowired
    protected MockMvc mockMvc;

    @BeforeAll
    static void setUpBeforeClass() {
        RestAssuredMockMvc.reset();
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssuredMockMvc.requestSpecification = new MockMvcRequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    @BeforeEach
    protected void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

}
