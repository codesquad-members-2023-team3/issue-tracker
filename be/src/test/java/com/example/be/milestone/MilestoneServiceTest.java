package com.example.be.milestone;

import com.example.be.milestone.dto.MilestoneCreateFormDTO;
import com.example.be.milestone.dto.MilestoneUpdateFormDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MilestoneServiceTest {

    @Autowired
    private MilestoneService milestoneService;

    MilestoneCreateFormDTO milestoneCreateFormDTO;
    MilestoneUpdateFormDTO milestoneUpdateFormDTO;

    @BeforeEach
    private void beforeEach() {
        this.milestoneCreateFormDTO= new MilestoneCreateFormDTO();
        milestoneCreateFormDTO.setName("test1234");
        milestoneCreateFormDTO.setDescriptionForLabel("before test");

        this.milestoneUpdateFormDTO= new MilestoneUpdateFormDTO();
        milestoneUpdateFormDTO.setName("test1234");
        milestoneUpdateFormDTO.setDescriptionForLabel("before test");
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

    @Test
    @DisplayName("마일스톤 생성 DTO를 파라미터로 받아, 마일스톤을 수정 결과를 반환해야한다.")
    void updateMilestoneTest() {
        assertThat(milestoneService.updateMilestone(milestoneUpdateFormDTO)).isFalse();
        assertThat(milestoneService.createMilestone(milestoneCreateFormDTO)).isTrue();
        assertThat(milestoneService.updateMilestone(milestoneUpdateFormDTO)).isTrue();
    }

    @Test
    @DisplayName("마일스톤 이름을 파라미터로 받아, 마일스톤을 삭제해야한다.")
    void deleteMilestoneTest() {
        assertThat(milestoneService.deleteMilestone(milestoneCreateFormDTO.getName())).isFalse();
        assertThat(milestoneService.createMilestone(milestoneCreateFormDTO)).isTrue();
        assertThat(milestoneService.deleteMilestone(milestoneCreateFormDTO.getName())).isTrue();
    }

}