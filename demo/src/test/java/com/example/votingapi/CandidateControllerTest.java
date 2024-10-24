package com.example.votingapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEnterCandidate() throws Exception {
        mockMvc.perform(post("/api/entercandidate?name=ajay"))
                .andExpect(status().isOk())
                .andExpect(content().string("Candidate ajay added successfully."));
    }

    @Test
    public void testCastVote() throws Exception {
        mockMvc.perform(post("/api/entercandidate?name=ajay"));
        mockMvc.perform(post("/api/castvote?name=ajay"))
                .andExpect(status().isOk())
                .andExpect(content().string("Vote cast for ajay. Current vote count: 1"));
    }

    @Test
    public void testCountVote() throws Exception {
        mockMvc.perform(post("/api/entercandidate?name=ajay"));
        mockMvc.perform(post("/api/castvote?name=ajay"));
        mockMvc.perform(get("/api/countvote?name=ajay"))
                .andExpect(status().isOk())
                .andExpect(content().string("ajay has 1 votes."));
    }

    @Test
    public void testListVotes() throws Exception {
        mockMvc.perform(post("/api/entercandidate?name=ajay"));
        mockMvc.perform(get("/api/listvote"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetWinner() throws Exception {
        mockMvc.perform(post("/api/entercandidate?name=ajay"));
        mockMvc.perform(post("/api/castvote?name=ajay"));
        mockMvc.perform(get("/api/getwinner"))
                .andExpect(status().isOk())
                .andExpect(content().string("The winner is ajay"));
    }
}
