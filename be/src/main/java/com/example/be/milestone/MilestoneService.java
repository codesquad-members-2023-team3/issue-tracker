package com.example.be.milestone;

import com.example.be.milestone.dto.MilestoneCreateFormDTO;
import com.example.be.milestone.dto.MilestoneUpdateFormDTO;
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

    public boolean updateMilestone(MilestoneUpdateFormDTO milestoneUpdateFormDTO) {
        Optional<Milestone> optionalMilestone = milestoneRepository.findById(milestoneUpdateFormDTO.getName());
        if (optionalMilestone.isEmpty()) {
            return false;
        }
        Milestone milestone = new Milestone(
                milestoneUpdateFormDTO.getName(),
                milestoneUpdateFormDTO.getScheduledCompletionDate(),
                milestoneUpdateFormDTO.getDescriptionForLabel());
        milestoneRepository.save(milestone.createEntityForUpdate());
        return true;
    }

    public boolean deleteMilestone(String milestoneName) {
        // TODO : 권한을 가진 사용자만 마일스톤을 삭제할 수 있다.
        Optional<Milestone> optionalMilestone = milestoneRepository.findById(milestoneName);
        if (optionalMilestone.isEmpty()) {
            return false;
        }
        Milestone milestone = optionalMilestone.get();
        milestoneRepository.delete(milestone);
        return true;
    }

}
