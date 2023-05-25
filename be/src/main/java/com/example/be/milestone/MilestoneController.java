package com.example.be.milestone;

import com.example.be.milestone.dto.MilestoneCreateFormDTO;
import com.example.be.milestone.dto.MilestoneUpdateFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MilestoneController {
    private final MilestoneService milestoneService;

    @Autowired
    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @PostMapping("/api/milestone")
    public String createMilestone(@RequestBody MilestoneCreateFormDTO milestoneCreateFormDTO) {
        if (milestoneService.createMilestone(milestoneCreateFormDTO)) {
            return "ok";
        }
        return "fail";
    }

    @PutMapping("/api/milestone")
    public String updateMilestone(@RequestBody MilestoneUpdateFormDTO milestoneUpdateFormDTO) {
        if (milestoneService.updateMilestone(milestoneUpdateFormDTO)) {
            return "ok";
        }
        return "fail";
    }

    @DeleteMapping("/api/milestone/{milestoneName}")
    public String deleteMilestone(@PathVariable String milestoneName ) {
        if (milestoneService.deleteMilestone(milestoneName)) {
            return "ok";
        }
        return "fail";
    }

}
