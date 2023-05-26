package com.example.be.comment;

import com.example.be.issue.IssueRepository;
import com.example.be.issue.dto.IssueCreateFormDTO;
import com.example.be.user.User;
import com.example.be.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("이슈 번호와, 유저 아이디, 댓글 내용을 파라미터로 받아 댓글을 저장할 수 있다.")
    void save() {
        // given
        User user = userRepository.save(new User("hyun", "codesquad", "hyun", "mock.img"));
        IssueCreateFormDTO issueCreateFormDTO = new IssueCreateFormDTO("테스트", "내용", null, null, null);
        issueCreateFormDTO.setUserId(user.getId());

        int issueNumber = issueRepository.save(issueCreateFormDTO);

        String contents = "댓글 내용이당";

        // when
        commentRepository.save(issueNumber, user.getId(), contents);

        // then
        List<Comment> comments = StreamSupport.stream(commentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertThat(comments.size()).isEqualTo(1);

        assertThat(comments.get(0).getContents()).isEqualTo(contents);
    }
}
