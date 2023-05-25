package com.example.be.milestone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MilestoneController.class)
class MilestoneControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MilestoneService milestoneService;

    @Test
    void createMilestone() throws Exception{
        mvc.perform(put("/api/milestone")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"test\", \"scheduledCompletionDate\":\"2019-12-14T06:34:20\",\"descriptionForLabel\":\"testcontent\"}"))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

}