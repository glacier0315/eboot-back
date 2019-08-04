package com.glacier.sys;

import static org.hamcrest.Matchers.equalTo;

import com.glacier.SysApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author hebin
 * @version 1.0
 * @description hell控制层单元测试
 * @date 2019-04-24 20:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SysApplication.class })
public class HelloControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;
	private MockHttpSession session;

	@Before
	public void setupMockMvc() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build(); // 初始化MockMvc对象
		session = new MockHttpSession();
		// User user =new User("root","root");
		// session.setAttribute("user",user); //拦截器那边会判断用户是否登录，所以这里注入一个用户
	}

	@Test
	public void hello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(equalTo("hello world")))
				.andDo(MockMvcResultHandlers.print());
	}
}
