package com.example.be.issue.dto;

public class CountDTO {

    private int openedIssuesCount;
    private int closedIssuesCount;

    public CountDTO() {
    }

    public CountDTO(int openedIssuesCount, int closedIssuesCount) {
        this.openedIssuesCount = openedIssuesCount;
        this.closedIssuesCount = closedIssuesCount;
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
}
