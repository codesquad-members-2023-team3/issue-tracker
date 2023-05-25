package com.example.be.milestone;

import com.example.be.milestone.dto.MilestoneCreateFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MilestoneController {
    private final MilestoneService milestoneService;

    @Autowired
    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @PostMapping("/api/milestone")
    public String createMilestone(@ModelAttribute MilestoneCreateFormDTO milestoneCreateFormDTO) {
        if (milestoneService.createMilestone(milestoneCreateFormDTO)) {
            return "ok";
        }
        return "fail";
    }

}
