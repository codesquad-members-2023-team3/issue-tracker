package com.example.be.label;

import com.example.be.label.dto.LabelCreateFormDTO;
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
class LabelServiceTest {

    @Autowired
    private LabelService labelService;

    private LabelCreateFormDTO labelCreateFormDTO;

    @BeforeEach
    private void beforeEach() {
        this.labelCreateFormDTO = new LabelCreateFormDTO();
        labelCreateFormDTO.setName("test");
    }


    @Test
    @DisplayName("라벨 생성 DTO를 파라미터로 받아, 라벨을 저장해야한다.")
    void saveTest() {
        // given
        Label expected = new Label(
                labelCreateFormDTO.getName(),
                labelCreateFormDTO.getDescription(),
                labelCreateFormDTO.getBackgroundColor(),
                labelCreateFormDTO.getTextColor());

        assertThat(labelService.createLabel(labelCreateFormDTO)).isTrue();
        assertThat(labelService.createLabel(labelCreateFormDTO)).isFalse();

    }
}