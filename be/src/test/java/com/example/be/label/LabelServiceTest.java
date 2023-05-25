package com.example.be.label;

import com.example.be.label.dto.LabelCreateFormDTO;
import com.example.be.label.dto.LabelUpdateFormDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class LabelServiceTest {

    @Autowired
    LabelService labelService;

    private LabelCreateFormDTO labelCreateFormDTO;
    private LabelUpdateFormDTO labelUpdateFormDTO;

    @BeforeEach
    private void beforeEach() {
        this.labelCreateFormDTO = new LabelCreateFormDTO();
        labelCreateFormDTO.setName("test");
        labelCreateFormDTO.setDescription("before update");

        this.labelUpdateFormDTO = new LabelUpdateFormDTO();
        labelUpdateFormDTO.setName("test");
        labelUpdateFormDTO.setDescription("after update");
    }

    @Test
    @DisplayName("라벨 수정 요청 DTO를 파라미터로 받아, 라벨을 수정 성공 여부를 반환해야한다.")
    public void updateLabelTest() {
        assertThat(labelService.updateLabel(labelUpdateFormDTO)).isFalse();
        assertThat(labelService.createLabel(labelCreateFormDTO)).isTrue();
        assertThat(labelService.updateLabel(labelUpdateFormDTO)).isTrue();
    }

    @Test
    @DisplayName("라벨 이름을 파라미터로 받아, 라벨을 삭제해야한다.")
    void deleteMilestoneTest() {
        assertThat(labelService.deleteLabel(labelCreateFormDTO.getName())).isFalse();
        assertThat(labelService.createLabel(labelCreateFormDTO)).isTrue();
        assertThat(labelService.deleteLabel(labelCreateFormDTO.getName())).isTrue();
    }

}
