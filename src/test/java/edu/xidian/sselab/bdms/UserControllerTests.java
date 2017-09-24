package edu.xidian.sselab.bdms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTests {
    
    @Autowired
    private MockMvc mvc;

    private final String apiUrl = "/api/users";

    @Test
    public void queryByPageTest() throws Exception {
        mvc.perform(get(apiUrl + "?page=0&size=1").accept(MediaType.APPLICATION_JSON)).
            andDo(print()).
            andExpect(status().isOk()).
            andExpect(content().contentType("application/json;charset=UTF-8")).
            andExpect(jsonPath("content").isArray()).
            andExpect(jsonPath("number").value(0)).
            andExpect(jsonPath("size").value(1)).
            andExpect(jsonPath("numberOfElements").isNumber());
    }

    @Test
    public void saveNewUserTest() throws Exception {
        mvc.perform(
            put(apiUrl).
                param("username", "test").
                param("password", "123").
                contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).
                accept(MediaType.APPLICATION_JSON)).
            andDo(print()).
            andExpect(status().isOk()).
            andExpect(content().contentType("application/json;charset=UTF-8")).
            andExpect(jsonPath("id").isNumber()).
            andExpect(jsonPath("username").value("test"));
    }
}
