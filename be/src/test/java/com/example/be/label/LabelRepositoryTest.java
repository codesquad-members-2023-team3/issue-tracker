package com.example.be.label;

import com.example.be.label.dto.LabelCreateFormDTO;
import com.example.be.milestone.Milestone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Test
    @DisplayName("라벨 엔티티를 파라미터로 받아, 라벨을 수정해야한다.")
    void updateTest() {
        // given
        Label label = new Label(
                labelCreateFormDTO.getName(),
                labelCreateFormDTO.getDescription(),
                labelCreateFormDTO.getBackgroundColor(),
                labelCreateFormDTO.getTextColor());
        Label expected = new Label(
                labelCreateFormDTO.getName(),
                "updated description",
                "#123456",
                "#654321");

        labelRepository.save(label.createEntityForInsert());

        // when
        labelRepository.save(expected.createEntityForUpdate());
        Optional<Label> optionalLabel = labelRepository.findById(labelCreateFormDTO.getName());
        Label actual = optionalLabel.get();

        // then
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        assertThat(actual.getBackgroundColor()).isEqualTo(expected.getBackgroundColor());
        assertThat(actual.getTextColor()).isEqualTo(expected.getTextColor());
    }

    @Test
    @DisplayName("라벨 이름을 파라미터로 받아, 라벨을 제거해야한다.")
    void deleteTest() {
        // when
        Label label = new Label(
                "delete test",
                labelCreateFormDTO.getDescription(),
                labelCreateFormDTO.getBackgroundColor(),
                labelCreateFormDTO.getTextColor());

        // given
        labelRepository.delete(label);

        Optional<Label> test = labelRepository.findById("delete test");

        assertThat(test.isEmpty()).isTrue();
    }

}
