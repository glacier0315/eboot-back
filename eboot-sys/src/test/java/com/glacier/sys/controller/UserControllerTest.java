package com.glacier.sys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glacier.EbootSysApplication;
import com.glacier.common.core.page.PageRequest;
import com.glacier.sys.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-01 11:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EbootSysApplication.class})
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //@Test
    public void findPage() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PageRequest<User> pageRequest = new PageRequest<>();
        pageRequest.setCurrent(1);
        pageRequest.setSize(15);
        String params = objectMapper.writeValueAsString(pageRequest);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.patch("/user/findPage")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(params)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println(mvcResult);
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }
}
