package com.demo.restapi;

import com.demo.restapi.controller.UserController;
import com.demo.restapi.dto.User;
import com.demo.restapi.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(new UserController(service)).build();
    }

    @Test
    public void whenInvokeGetQueryString_thenReturnTheOriginQueryString() throws Exception {
        List<User> list = new ArrayList<>();
        User user = new User(1, "Ram", 32);
        list.add(user);
        when(service.getUserByName(any())).thenReturn(list);

        MvcResult result = this.mockMvc.perform(get("/user/search?name=ram"))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        List<User> list1 = mapper.readValue(response, new TypeReference<List<User>>() {});
        assertEquals("User name matched", user.getName(), list1.get(0).getName());
    }

    @Test
    public void whenInvokeGetPathVariable_thenReturnUser() throws Exception {

        User user = new User(1, "Ram", 32);
        when(service.getUserById(anyInt())).thenReturn(user);

        MvcResult result = this.mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        User user1 = mapper.readValue(response, User.class);
        assertEquals("User name matched", user.getName(), user1.getName());
    }
}
