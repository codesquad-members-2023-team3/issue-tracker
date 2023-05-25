package com.example.be.issue;

import com.example.be.issue.dto.CountDTO;
import com.example.be.issue.dto.FeIssueResponseDTO;
import com.example.be.issue.dto.IssueNumberWithLabelDTO;
import com.example.be.label.Label;
import com.example.be.milestone.Milestone;
import com.example.be.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueService issueService;

    @Test
    @DisplayName("labels 를 채우지 않은 Issue 목록과 IssueLabelMap 목록을 가지고 조립하여 완전한 Issue 목록을 만들고, 여러 통계를 나타내는 Count 클래스와 wrapping 하여 FE용 DTO객체를 반환한다.")
    void makeFeIssueResponse() {

        Milestone milestone = new Milestone("마일1", LocalDateTime.now(), "마일1내용");
        User user = new User("hyun", "1234", "hyun", "/cat.jpg");
        Issue issueWithoutLabels1 = new Issue(1, "제목1", "내용1", true, LocalDateTime.now(), LocalDateTime.now(), milestone, user);
        Issue issueWithoutLabels2 = new Issue(2, "제목2", "내용2", true, LocalDateTime.now(), LocalDateTime.now(), milestone, user);
        Issue issueWithoutLabels3 = new Issue(3, "제목3", "내용3", false, LocalDateTime.now(), LocalDateTime.now(), milestone, user);

        BDDMockito.given(issueRepository.findIssuesWithoutLabelsBy(ArgumentMatchers.any()))
                .willReturn(List.of(issueWithoutLabels1, issueWithoutLabels2, issueWithoutLabels3));

        Label label1 = new Label("라벨1", "라벨내용1", "#000000", "#000000");
        Label label2 = new Label("라벨2", "라벨내용2", "#111111", "#111111");
        IssueNumberWithLabelDTO issueNumberWithLabelDTO1 = new IssueNumberWithLabelDTO(1, label1);
        IssueNumberWithLabelDTO issueNumberWithLabelDTO2 = new IssueNumberWithLabelDTO(1, label2);
        IssueNumberWithLabelDTO issueNumberWithLabelDTO3 = new IssueNumberWithLabelDTO(2, label1);


        BDDMockito.given(issueRepository.findIssueLabelMapsBy(Set.of(1, 2, 3)))
                .willReturn(List.of(issueNumberWithLabelDTO1, issueNumberWithLabelDTO2, issueNumberWithLabelDTO3));

        CountDTO countDTO = new CountDTO(2, 1);

        BDDMockito.given(issueRepository.countEntities())
                .willReturn(countDTO);

        FeIssueResponseDTO feIssueResponseDTO = issueService.makeFeIssueResponse(ArgumentMatchers.any());

        issueWithoutLabels1.add(issueNumberWithLabelDTO1);
        issueWithoutLabels1.add(issueNumberWithLabelDTO2);
        issueWithoutLabels2.add(issueNumberWithLabelDTO3);

        Collection<Issue> issues = Map.of(1, issueWithoutLabels1, 2, issueWithoutLabels2, 3, issueWithoutLabels3).values();
        FeIssueResponseDTO expectedFeIssueResponseDTO = new FeIssueResponseDTO(issues, countDTO);


        assertThat(feIssueResponseDTO).usingRecursiveComparison().isEqualTo(expectedFeIssueResponseDTO);
    }
}
