package com.tichalo.dogapi;

import com.tichalo.dogapi.service.DogService;
import com.tichalo.dogapi.web.DogController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@WebMvcTest(DogController.class)
@WithMockUser(username = "admin", password = "password")
public class DogControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @MockBean
    DogService dogService;

    private MockHttpServletRequestBuilder get(String url){
        return MockMvcRequestBuilders.get(url);
    }

    private MockHttpServletRequestBuilder post(String url){
        return MockMvcRequestBuilders.post(url);
    }

    private MockHttpServletRequestBuilder put(String url){
        return MockMvcRequestBuilders.put(url);
    }

    @Before
    public void setup(){

    }

    private MockHttpServletRequestBuilder login(){
        Map<String, String> payload = new HashMap<>();
        payload.put("username", "admin");
        payload.put("password", "password");
        return MockMvcRequestBuilders.post("/login", payload);
    }

    @Test
    public void getAllDogs() throws Exception{
        mockMvc.perform(get("/dogs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json("[]"));
        Mockito.verify(dogService, Mockito.times(1)).retrieveDogs();

    }

}
