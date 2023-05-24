package com.example.be.milestone;

import com.example.be.label.Label;
import com.example.be.milestone.dto.MilestoneCreateFormDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MilestoneRepositoryTest {

    @Autowired
    private MilestoneRepository milestoneRepository;

    MilestoneCreateFormDTO milestoneCreateFormDTO;

    @BeforeEach
    private void beforeEach() {
        this.milestoneCreateFormDTO= new MilestoneCreateFormDTO();
        milestoneCreateFormDTO.setName("test1");
    }

    @Test
    @DisplayName("마일스톤 이름을 파라미터로 받아, 해당하는 마일스톤을 반환한다.")
    void findByName() {
        String milestoneName1 = milestoneRepository.validateName("BE STEP1");
        assertThat(milestoneName1).isEqualTo("BE STEP1");

        String milestoneName2 = milestoneRepository.validateName("BE STEP2");
        assertThat(milestoneName2).isNull();
    }

    @Test
    @DisplayName("마일스톤 엔티티를 파라미터로 받아, 마일스톤을 저장해야한다.")
    void saveTest() {

        Milestone milestone = new Milestone(
                milestoneCreateFormDTO.getName(),
                milestoneCreateFormDTO.getScheduledCompletionDate(),
                milestoneCreateFormDTO.getDescriptionForLabel());
        milestoneRepository.save(milestone.getEntityForInsert());

        Milestone actualMilestone = milestoneRepository.findById("test1").get();

        assertThat(milestone).usingRecursiveComparison().isEqualTo(actualMilestone);
    }

}
