package com.example.be.issue.dto;

import com.example.be.issue.Issue;

import java.util.Collection;

public class FeIssueResponseDTO {

    private Collection<Issue> issues;
    private CountDTO counts;

    public FeIssueResponseDTO(Collection<Issue> issues, CountDTO counts) {
        this.issues = issues;
        this.counts = counts;
    }

    public Collection<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Collection<Issue> issues) {
        this.issues = issues;
    }

    public CountDTO getCounts() {
        return counts;
    }

    public void setCounts(CountDTO counts) {
        this.counts = counts;
    }
}
