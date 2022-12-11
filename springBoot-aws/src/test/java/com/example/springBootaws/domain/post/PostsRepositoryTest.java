//package com.example.springBootaws.domain.post;
//
//import com.example.springBootaws.TestConfig;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@Import(TestConfig.class)
//public class PostsRepositoryTest {
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @AfterEach
//    void clean() {
//        postsRepository.deleteAll();
//    }
//
//    @Test
//    public void 게시글_저장_불러오기 () {
//        //given
//        String title = "title";
//        String content = "content";
//        String author = "hsj";
//
//        postsRepository.save(Posts.builder()
//                        .title(title)
//                        .content(content)
//                        .author(author)
//                        .build());
//        //when
//        List<Posts> postsList = postsRepository.findAll();
//
//        //then
//        Posts posts = postsList.get(0);
//        assertThat(posts.getTitle()).isEqualTo(title);
//        assertThat(posts.getContent()).isEqualTo(content);
//        assertThat(posts.getAuthor()).isEqualTo(author);
//    }
//
//    @Test
//    public void BaseTimeEntity_등록() {
//        //given
//        LocalDateTime now = LocalDateTime.of(2020, 12,7,0,0,0);
//        postsRepository.save(Posts.builder()
//                        .title("title")
//                        .content("content")
//                        .author("author")
//                .build());
//        //when
//        List<Posts> postsList = postsRepository.findAll();
//        //then
//        Posts posts = postsList.get(0);
//
//        System.out.println("createDate ="+posts.getCreateDate());
//        assertThat(posts.getCreateDate()).isAfter(now);
//        assertThat(posts.getModifiedDate()).isAfter(now);
//    }
//}
