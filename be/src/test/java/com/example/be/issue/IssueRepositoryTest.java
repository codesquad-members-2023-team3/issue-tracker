package com.example.be.issue;


import com.example.be.BeApplication;
import com.example.be.issue.dto.CountDTO;
import com.example.be.issue.dto.IssueNumberWithLabelDTO;
import com.example.be.issue.dto.IssueSearchCondition;
import com.example.be.label.Label;
import com.example.be.milestone.Milestone;
import com.example.be.user.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(classes = BeApplication.class)
class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;

    @Nested
    @DisplayName("List<Labels> 를 채우지 않고 DB로 부터 데이터를 불러와 List<Issue> 목록을 필터링해 반환한다.")
    class FindAllIssuesWithoutLabels {

        @Test
        @DisplayName("필터 조건이 없는 경우, 모든 이슈 목록들을 반환한다.")
        void allIssues() {
            Issue simpleOpenedIssue1 = new Issue(1,
                    "제목 1",
                    "첫 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            Issue simpleClosedIssue2 = new Issue(2,
                    "제목 2",
                    "두 번째 이슈 내용",
                    false,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));


            Issue simpleOpenedIssue3 = new Issue(3,
                    "제목 3",
                    "세 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            List<Issue> issuesWithoutLabels = issueRepository.findIssuesWithoutLabelsBy(new IssueSearchCondition());
            assertThat(issuesWithoutLabels).usingRecursiveFieldByFieldElementComparator().containsExactly(simpleOpenedIssue1, simpleClosedIssue2, simpleOpenedIssue3);
        }

        @Test
        @DisplayName("열린 이슈들로만 이루어진 목록을 반환한다.")
        void openIssues() {
            Issue simpleOpenedIssue1 = new Issue(1,
                    "제목 1",
                    "첫 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            Issue simpleOpenedIssue3 = new Issue(3,
                    "제목 3",
                    "세 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            IssueSearchCondition issueSearchCondition = new IssueSearchCondition();
            issueSearchCondition.setState(true);

            List<Issue> issuesWithoutLabels = issueRepository.findIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels).usingRecursiveFieldByFieldElementComparator().containsExactly(simpleOpenedIssue1, simpleOpenedIssue3);
        }

        @Test
        @DisplayName("닫힌 이슈들로만 이루어진 목록을 반환한다.")
        void closeIssues() {
            Issue simpleClosedIssue = new Issue(2,
                    "제목 2",
                    "두 번째 이슈 내용",
                    false,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));


            IssueSearchCondition issueSearchCondition = new IssueSearchCondition();
            issueSearchCondition.setState(false);

            List<Issue> issuesWithoutLabels = issueRepository.findIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels).usingRecursiveFieldByFieldElementComparator().containsExactly(simpleClosedIssue);
        }

        @Test
        @DisplayName("글 작성자 (닉네임)를 기준으로 필터링한 목록을 반환한다.")
        void issuesFilteredByAuthor() {

            Issue simpleOpenedIssue1 = new Issue(1,
                    "제목 1",
                    "첫 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            Issue simpleClosedIssue2 = new Issue(2,
                    "제목 2",
                    "두 번째 이슈 내용",
                    false,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            Issue simpleOpenedIssue3 = new Issue(3,
                    "제목 3",
                    "세 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            IssueSearchCondition issueSearchCondition = new IssueSearchCondition();
            issueSearchCondition.setAuthor("1234");

            List<Issue> issuesWithoutLabels1 = issueRepository.findIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels1).usingRecursiveFieldByFieldElementComparator().containsExactly(simpleOpenedIssue1, simpleClosedIssue2, simpleOpenedIssue3);

            issueSearchCondition.setAuthor("4321");
            List<Issue> issuesWithoutLabels2 = issueRepository.findIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels2).isEmpty();
        }

        @Test
        @DisplayName("마일스톤 이름울 기준으로 필터링한 목록을 반환한다.")
        void issuesFilteredByMilestone() {
            Issue simpleOpenedIssue1 = new Issue(1,
                    "제목 1",
                    "첫 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            Issue simpleClosedIssue2 = new Issue(2,
                    "제목 2",
                    "두 번째 이슈 내용",
                    false,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));


            Issue simpleOpenedIssue3 = new Issue(3,
                    "제목 3",
                    "세 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            IssueSearchCondition issueSearchCondition = new IssueSearchCondition();
            issueSearchCondition.setMilestoneName("BE STEP1");

            List<Issue> issuesWithoutLabels1 = issueRepository.findIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels1).usingRecursiveFieldByFieldElementComparator().containsExactly(simpleOpenedIssue1, simpleClosedIssue2, simpleOpenedIssue3);

            issueSearchCondition.setMilestoneName("FE STEP1");
            List<Issue> issuesWithoutLabels2 = issueRepository.findIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels2).isEmpty();
        }

        @Test
        @DisplayName("이슈에 담당된 유저의 아이디를 기준으로 필터링한 목록을 반환한다.")
        void issuesFilteredByAssignee() {
            Issue simpleClosedIssue = new Issue(2,
                    "제목 2",
                    "두 번째 이슈 내용",
                    false,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            IssueSearchCondition issueSearchCondition = new IssueSearchCondition();
            issueSearchCondition.setState(false);
            issueSearchCondition.setAssignee("cire");

            List<Issue> issuesWithoutLabels1 = issueRepository.findIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels1).usingRecursiveFieldByFieldElementComparator().containsExactly(simpleClosedIssue);
        }

        @Test
        @DisplayName("이슈에 붙은 라벨 이름을 기준으로 필터링한 목록을 반환한다.")
        void issuesFilteredByLabelNames() {
            Issue simpleOpenedIssue = new Issue(1,
                    "제목 1",
                    "첫 번째 이슈 내용",
                    true,
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    LocalDateTime.of(2023, 5, 15, 19, 37, 47),
                    new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                    new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

            IssueSearchCondition issueSearchCondition = new IssueSearchCondition();
            issueSearchCondition.setLabelNames(List.of("feature", "fix"));

            List<Issue> issuesWithoutLabels1 = issueRepository.findIssuesWithoutLabelsBy(issueSearchCondition);
            assertThat(issuesWithoutLabels1).usingRecursiveFieldByFieldElementComparator().containsExactly(simpleOpenedIssue);
        }
    }

    @Test
    @DisplayName("Issue의 PK값과 대응되는 Label 객체를 가지는 List<IssueLabelMap> 목록을 반환한다.")
    void findAllIssueLabelMaps() {
        IssueNumberWithLabelDTO issueNumberWithLabelDTO1 = new IssueNumberWithLabelDTO(1, new Label("feature", "기능을 만들었슴둥", "#000000", "#004DE3"));
        IssueNumberWithLabelDTO issueNumberWithLabelDTO2 = new IssueNumberWithLabelDTO(1, new Label("fix", "버그를 고쳤음", "#123456", "#654321"));
        IssueNumberWithLabelDTO issueNumberWithLabelDTO3 = new IssueNumberWithLabelDTO(2, new Label("fix", "버그를 고쳤음", "#123456", "#654321"));

        List<IssueNumberWithLabelDTO> issueNumberWithLabelDTOS = issueRepository.findIssueLabelMapsBy(Set.of(1, 2));

        assertThat(issueNumberWithLabelDTOS).usingRecursiveFieldByFieldElementComparator().containsExactly(issueNumberWithLabelDTO1, issueNumberWithLabelDTO2, issueNumberWithLabelDTO3);
    }

    @Test
    @DisplayName("열린 Issue 개수, 닫힌 Issue 개수, Label 개수, Milestone 개수를 Count 클래스에 담아 반환한다.")
    void calculateCount() {

        CountDTO countDTO = issueRepository.countEntities();

        assertThat(countDTO.getOpenedIssuesCount()).isEqualTo(2);
        assertThat(countDTO.getClosedIssuesCount()).isEqualTo(1);
    }

}
