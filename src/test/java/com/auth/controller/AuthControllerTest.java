package com.auth.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.auth.model.AuthToken;
import com.auth.model.AuthTokenResponse;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthController authController;

    @BeforeEach
    public void setUp() {

    }

    // TODO move to a generic test class
    protected <T> T mapFromJson(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    // TODO move to a generic test class
    protected <T> String mapToString(T obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void authShouldHaveHealthCheck() throws Exception {
        mockMvc.perform(get("/auth/health"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void authShouldHaveTokenMethod() throws Exception {
        mockMvc.perform(get("/auth/get-token"))
                .andExpect(status().isOk());
    }

    @Test
    public void authShouldReturnAuthTokenResponseOnSuccess() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/auth/get-token")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        // AuthTokenResponse tokenResponse = mapFromJson(response, AuthTokenResponse.class);
        // assertEquals(tokenResponse, new AuthTokenResponse());
        String json = mapToString(new AuthTokenResponse(HttpStatus.OK, new AuthToken()));
        System.out.println("ABHIJEET");
        System.out.println(json);
        mockMvc.perform(get("/auth/get-token")
            .content(json)) 
            .andExpect(status().isOk());

        assertTrue(false);
    }

    // @Test
    // public void authShouldHaveValidatePostMethod() throws Exception {
    //     MvcResult mvcResult = mockMvc.perform(post("/auth/validate-token").accept(MediaType.APPLICATION_JSON))
    //             .andReturn();

    //     String response = mvcResult.getResponse().getContentAsString();
    //     System.out.println(response);
    //     AuthTokenResponse tokenResponse = mapFromJson(response, AuthTokenResponse.class);
    //     assertEquals(tokenResponse, new AuthTokenResponse());

    // }

}
