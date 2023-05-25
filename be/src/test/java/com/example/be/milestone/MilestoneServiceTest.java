package com.example.be.milestone;

import com.example.be.milestone.dto.MilestoneCreateFormDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MilestoneServiceTest {

    @Autowired
    private MilestoneService milestoneService;

    MilestoneCreateFormDTO milestoneCreateFormDTO;

    @BeforeEach
    private void beforeEach() {
        this.milestoneCreateFormDTO= new MilestoneCreateFormDTO();
        milestoneCreateFormDTO.setName("test1234");
    }

    @Test
    @DisplayName("마일스톤 생성 DTO를 파라미터로 받아, 마일스톤을 저장해야한다.")
    void createMilestoneTest() {
        // given
        Milestone expected = new Milestone(
                milestoneCreateFormDTO.getName(),
                milestoneCreateFormDTO.getScheduledCompletionDate(),
                milestoneCreateFormDTO.getDescriptionForLabel());

        assertThat(milestoneService.createMilestone(milestoneCreateFormDTO)).isTrue();
        assertThat(milestoneService.createMilestone(milestoneCreateFormDTO)).isFalse();

    }




}