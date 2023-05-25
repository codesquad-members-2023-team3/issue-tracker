package com.example.be.label;

import com.example.be.label.dto.LabelCreateFormDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class LabelRepositoryTest {

    @Autowired
    private LabelRepository labelRepository;

    private LabelCreateFormDTO labelCreateFormDTO;

    @BeforeEach
    private void beforeEach() {
        this.labelCreateFormDTO = new LabelCreateFormDTO();
        labelCreateFormDTO.setName("test");
    }

    @Test
    @DisplayName("라벨 이름들을 파라미터로 받아, 해당하는 라벨 목록들을 반환한다.")
    void getLabelsByNames() {
        List<String> labelNames1 = labelRepository.validateNames(List.of("feature", "fix"));
        assertThat(labelNames1).containsExactly("feature", "fix");

        List<String> labelNames2 = labelRepository.validateNames(List.of("feat", "fix"));
        assertThat(labelNames2).containsExactly("fix");
    }

    @Test
    @DisplayName("라벨 엔티티를 파라미터로 받아, 라벨을 저장해야한다.")
    void saveTest() {
        Label label = new Label(
                labelCreateFormDTO.getName(),
                labelCreateFormDTO.getDescription(),
                labelCreateFormDTO.getBackgroundColor(),
                labelCreateFormDTO.getTextColor());
        labelRepository.save(label.createEntityForInsert());

        Label actualLabel = labelRepository.findById("test").get();

        assertThat(label).usingRecursiveComparison().isEqualTo(actualLabel);
    }

}
