package com.example.be.milestone;

import com.example.be.milestone.dto.MilestoneCreateFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    public boolean createMilestone(MilestoneCreateFormDTO milestoneCreateFormDTO) {
        Optional<Milestone> optionalMilestone = milestoneRepository.findById(milestoneCreateFormDTO.getName());
        if (optionalMilestone.isPresent()) {
            return false;
        }
        Milestone milestone = new Milestone(
                milestoneCreateFormDTO.getName(),
                milestoneCreateFormDTO.getScheduledCompletionDate(),
                milestoneCreateFormDTO.getDescriptionForLabel());
        milestoneRepository.save(milestone.createEntityForInsert());
        return true;
    }

}
