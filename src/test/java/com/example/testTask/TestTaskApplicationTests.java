package com.example.testTask;

import com.example.testTask.domain.Client;
import com.example.testTask.repo.ClientJpaRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test"})
@SpringBootTest
@AutoConfigureMockMvc
class TestTaskApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ClientJpaRepository clientJpaRepository;

    @SneakyThrows
    @Test
    void commonTest() {
        mockMvc.perform(get("/v1/app/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        // подготовим данные

        mockMvc.perform(post("/v1/app/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-Source", "mail")
                        .content(readFile("/test-user1.json")))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/v1/app/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-Source", "bank")
                        .content(readFile("/test-user2.json")))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/v1/app/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-Source", "mobile")
                        .content(readFile("/test-user3.json")))
                .andExpect(status().isCreated());
        // не пройдет валидацию
        mockMvc.perform(post("/v1/app/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-Source", "gosuslugi")
                        .content(readFile("/test-user4.json")))
                .andExpect(status().is4xxClientError());

        List<Client> clientList = clientJpaRepository.findAll();

        Assertions.assertEquals(3, clientList.size());

        Client client = clientList.get(0);
        mockMvc.perform(get("/v1/app/client/" + client.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(readFile("/test-user1.json")));

        mockMvc.perform(get("/v1/app/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", client.getFirstName())
                        .param("phone", client.getPhone()))
                .andExpect(status().isOk())
                .andExpect(content().json(readFile("/expectedAnswerListOfUser.json")));

        mockMvc.perform(get("/v1/app/client")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    protected static String readFile(String fileName) {
        try {
            URL resource = TestTaskApplicationTests.class.getResource(fileName);
            assert resource != null;
            return Files.readString(Paths.get(resource.toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
