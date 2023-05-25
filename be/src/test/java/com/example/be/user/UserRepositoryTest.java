package com.example.be.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("담당자 이름들을 파라미터로 받아, 해당하는 유저 목록을 반환한다.")
    void findByNames() {
        User user1 = new User("cire", "codesquad2", "cire", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/img.jpg");
        User user2 = new User("ghkdgus29", "codesquad1", "hyun", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cute_cat.jpg");

        List<String> assignees1 = userRepository.validateNames(List.of("cire", "ghkdgus29"));
        assertThat(assignees1).containsExactly("cire", "ghkdgus29");

        List<String> assignees2 = userRepository.validateNames(List.of("cir", "ghkdgus29"));
        assertThat(assignees2).containsExactly("ghkdgus29");
    }
}
