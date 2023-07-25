package com.example.mockmvcintframework.controller;

import com.example.mockmvcintframework.testsupport.MockMvcTestBase;
import com.example.mockmvcintframework.testsupport.MvcRequest;
import com.example.mockmvcintframework.testsupport.StringResource;
import org.junit.jupiter.api.Test;

class GreetingControllerIntegrationTest extends MockMvcTestBase {

    @Test
    void greeting_whenNoParam_returnsDefaultResponse(@StringResource("mock/default-greet.json") String json) {
        MvcRequest request = MvcRequest.builder().httpGet("/api/greeting").build();

        String result = getResultWithStatus(request, 200);

        assertJsonEquals(json, result);
    }
}