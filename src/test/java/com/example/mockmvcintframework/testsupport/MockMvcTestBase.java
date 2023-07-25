package com.example.mockmvcintframework.testsupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class MockMvcTestBase extends SpringTestBase {
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @SneakyThrows
    public String getResultWithStatus(MvcRequest mvcRequest, int statusCode) {
        return getResultWithStatus(mvcRequest, statusCode, false);
    }

    @SneakyThrows
    public String getResultWithStatus(MvcRequest mvcRequest, int statusCode, boolean isPrinted) {
        ResultActions resultActions = perform(mvcRequest).andExpect(status().is(statusCode));

        if (isPrinted) {
            resultActions.andDo(MockMvcResultHandlers.print());
        }

        return resultActions.andReturn().getResponse().getContentAsString();
    }

    @SneakyThrows
    public void assertJsonEquals(String json, String result) {
        JSONAssert.assertEquals(json, result, true);
    }

    @SneakyThrows
    private ResultActions perform(MvcRequest r) {
        return mockMvc.perform(request(r.getMethod(), r.getPath()).contentType(r.getContentType()));
    }
}
