package com.example.be.label;

import com.example.be.label.dto.LabelCreateFormDTO;
import com.example.be.label.dto.LabelUpdateFormDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LabelService {
    private final LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public boolean createLabel(LabelCreateFormDTO labelCreateFromDTO) {
        Optional<Label> optionalLabel = labelRepository.findById(labelCreateFromDTO.getName());
        if (optionalLabel.isPresent()) {
            return false;
        }
        Label label = new Label(
                labelCreateFromDTO.getName(),
                labelCreateFromDTO.getDescription(),
                labelCreateFromDTO.getBackgroundColor(),
                labelCreateFromDTO.getTextColor());
        labelRepository.save(label.createEntityForInsert());
        return true;
    }

    public boolean updateLabel(LabelUpdateFormDTO labelUpdateFormDTO) {
        Optional<Label> optionalLabel = labelRepository.findById(labelUpdateFormDTO.getName());
        if (optionalLabel.isEmpty()) {
            return false;
        }
        Label label = new Label(
                labelUpdateFormDTO.getName(),
                labelUpdateFormDTO.getDescription(),
                labelUpdateFormDTO.getBackgroundColor(),
                labelUpdateFormDTO.getTextColor());
        labelRepository.save(label.createEntityForUpdate());
        return true;
    }

    public boolean deleteLabel(String labelName) {
        // TODO : 권한을 가진 사용자만 마일스톤을 삭제할 수 있다.
        Optional<Label> optionalLabel = labelRepository.findById(labelName);
        if (optionalLabel.isEmpty()) {
            return false;
        }
        Label label = optionalLabel.get();
        labelRepository.delete(label);
        return true;
    }

}
