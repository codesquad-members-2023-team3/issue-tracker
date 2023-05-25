package com.example.be.label;

import com.example.be.label.dto.LabelCreateFormDTO;
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

}
