package com.example.springBootaws.domain.post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글_저장_불러오기 () {
        //given
        String title = "title";
        String content = "content";
        String author = "hsj";

        postRepository.save(Post.builder()
                        .title(title)
                        .content(content)
                        .author(author)
                        .build());
        //when
        List<Post> postList = postRepository.findAll();

        //then
        Post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getAuthor()).isEqualTo(author);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2020, 12,7,0,0,0);
        postRepository.save(Post.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                .build());
        //when
        List<Post> postList = postRepository.findAll();
        //then
        Post post = postList.get(0);

        System.out.println("createDate ="+post.getCreateDate());
        assertThat(post.getCreateDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}
