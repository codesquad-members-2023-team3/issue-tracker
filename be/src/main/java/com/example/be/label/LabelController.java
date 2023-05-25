package com.example.be.label;

import com.example.be.label.dto.LabelCreateFormDTO;
import com.example.be.label.dto.LabelUpdateFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LabelController {

    public final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @PostMapping("/api/label")
    public String createLabel(@RequestBody LabelCreateFormDTO labelCreateFormDTO) {
        if (labelService.createLabel(labelCreateFormDTO)) {
            return "ok";
        }
        return "fail";
    }

    @PutMapping("/api/label")
    public String updateLabel(@RequestBody LabelUpdateFormDTO labelUpdateFormDTO) {
        if (labelService.updateLabel(labelUpdateFormDTO)) {
            return "ok";
        }
        return "fail";
    }

    @DeleteMapping("/api/label/{labelName}")
    public String deleteMilestone(@PathVariable String labelName ) {
        if (labelService.deleteLabel(labelName)) {
            return "ok";
        }
        return "fail";
    }

}
