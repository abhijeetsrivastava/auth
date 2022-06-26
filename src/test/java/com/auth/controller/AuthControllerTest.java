package com.auth.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.auth.model.AuthToken;
import com.auth.model.AuthTokenResponse;
import com.auth.util.JacksonMapper;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthController authController;

    @BeforeEach
    public void setUp() {

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
        // this is mocking the values
        MvcResult mvcResult = mockMvc.perform(get("/auth/get-token")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);
        String json = JacksonMapper.mapToString(new AuthTokenResponse(HttpStatus.OK, new AuthToken()));
        System.out.println(json);
        mockMvc.perform(get("/auth/get-token")
            .content(json)) 
            .andExpect(status().isOk());

        assertEquals(response, "asdfsf");
    }

    // @Test
    // public void authShouldHaveValidatePostMethod() throws Exception {
    //     MvcResult mvcResult = mockMvc.perform(post("/auth/validate-token").accept(MediaType.APPLICATION_JSON))
    //             .andReturn();

    //     String response = mvcResult.getResponse().getContentAsString();
    //     System.out.println(response);
    //     AuthTokenResponse tokenResponse = JacksonMapper.mapFromJson(response, AuthTokenResponse.class);
    //     assertEquals(tokenResponse, new AuthTokenResponse());

    // }

}
