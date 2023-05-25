package com.example.be.issue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IssueController.class)
class IssueControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    IssueService issueService;

    @Test
    @DisplayName("/api-fe/issues 로 GET 요청 시, FE 용 API 를 반환한다.")
    void createFeIssueResponse() throws Exception {
        mvc.perform(get("/api-fe/issues"))
                .andExpect(status().isOk());

    }
}