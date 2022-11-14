package tn.esprit.spring;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.controllers.JeuController;
import tn.esprit.spring.serviceInterface.IJeuService;

//@WebMvcTest(controllers = JeuController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JeuControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    //private IJeuService ijeuService;

    @Test
    public void testGetJeux() throws Exception{
        mockMvc.perform(get("/jeux"))
                .andExpect(status().isOk());
    }
}
