package com.example.be.issue.dto;

public class CountDTO {

    private int openedIssuesCount;
    private int closedIssuesCount;
    private int labelsCount;
    private int milestoneCount;

    public CountDTO() {
    }

    public CountDTO(int openedIssuesCount, int closedIssuesCount, int labelsCount, int milestoneCount) {
        this.openedIssuesCount = openedIssuesCount;
        this.closedIssuesCount = closedIssuesCount;
        this.labelsCount = labelsCount;
        this.milestoneCount = milestoneCount;
    }

    public int getOpenedIssuesCount() {
        return openedIssuesCount;
    }

    public void setOpenedIssuesCount(int openedIssuesCount) {
        this.openedIssuesCount = openedIssuesCount;
    }

    public int getClosedIssuesCount() {
        return closedIssuesCount;
    }

    public void setClosedIssuesCount(int closedIssuesCount) {
        this.closedIssuesCount = closedIssuesCount;
    }

    public int getLabelsCount() {
        return labelsCount;
    }

    public void setLabelsCount(int labelsCount) {
        this.labelsCount = labelsCount;
    }

    public int getMilestoneCount() {
        return milestoneCount;
    }

    public void setMilestoneCount(int milestoneCount) {
        this.milestoneCount = milestoneCount;
    }
}
