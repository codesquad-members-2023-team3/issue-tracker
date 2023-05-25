
package com.example.be.milestone.dto;


import java.time.LocalDateTime;

public class MilestoneUpdateFormDTO {
    private String name;

    private LocalDateTime scheduledCompletionDate;
    private String descriptionForLabel;

    public MilestoneUpdateFormDTO() {}

    public String getName() {
        return name;
    }

    public MilestoneUpdateFormDTO(String name, LocalDateTime scheduledCompletionDate, String descriptionForLabel) {
        this.name = name;
        this.scheduledCompletionDate = scheduledCompletionDate;
        this.descriptionForLabel = descriptionForLabel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getScheduledCompletionDate() {
        return scheduledCompletionDate;
    }

    public void setScheduledCompletionDate(LocalDateTime scheduledCompletionDate) {
        this.scheduledCompletionDate = scheduledCompletionDate;
    }

    public String getDescriptionForLabel() {
        return descriptionForLabel;
    }

    public void setDescriptionForLabel(String descriptionForLabel) {
        this.descriptionForLabel = descriptionForLabel;
    }
}
