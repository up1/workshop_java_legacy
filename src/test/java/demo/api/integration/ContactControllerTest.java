package demo.api.integration;

import demo.Application;
import demo.api.ContactController;
import demo.domain.Contact;
import demo.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ContactControllerTest {

    @Autowired
    ContactRepository repository;

    @Autowired
    ContactController controller;

    @Before
    public void setup() {
        repository.deleteAll();
        mockMvc = buildMockMvc(controller);
    }

    protected MockMvc mockMvc;

    protected MockMvc buildMockMvc(Object... controllers) {
        return MockMvcBuilders
                .standaloneSetup(controllers)
                .build();
    }

    @Test
    public void should_save_contact() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/api/contacts").contentType(APPLICATION_JSON)
                .content("{\"name\":\"Somkiat\",\"fullName\":\"Somkiat Puisungnoen\"}"))
                .andExpect(status().isCreated())
                .andReturn();

        Contact contact = repository.findOne(result.getResponse().getContentAsString());
        assertThat(contact.getName()).isEqualTo("Somkiat");
        assertThat(contact.getFullName()).isEqualTo("Somkiat Puisungnoen");
    }

}