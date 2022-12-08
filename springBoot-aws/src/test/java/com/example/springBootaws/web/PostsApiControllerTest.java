//package com.example.springBootaws.web;
//
//import com.example.springBootaws.TestConfig;
//import com.example.springBootaws.domain.post.Posts;
//import com.example.springBootaws.domain.post.PostsRepository;
//import com.example.springBootaws.web.dto.PostUpdateRequestDto;
//import com.example.springBootaws.web.dto.PostsSaveRequestDto;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//@Import(TestConfig.class)
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PostsApiControllerTest {
//
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @BeforeEach
//    void setHttpMethod() {
//        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//    }
//
//    @AfterEach
//    void clean() {
//        postsRepository.deleteAll();
//    }
//
//    @Test
//    void postsApiControllerTest() {
//        //given
//        String title = "post title";
//        String content = "post content";
//        String author = "author";
//
//        PostsSaveRequestDto requestDto = PostsSaveRequestDto
//                .builder()
//                .title(title)
//                .content(content)
//                .author(author)
//                .build();
//        String url = "http://localhost:" + port + "/api/v1/post";
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//        List<Posts> postsList = postsRepository.findAll();
//        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
//        assertThat(postsList.get(0).getContent()).isEqualTo(content);
//        assertThat(postsList.get(0).getAuthor()).isEqualTo(author);
//    }
//
//    @Test
//    public void Posts_수정하다() throws Exception{
//        //given
//        Posts savePosts = postsRepository.save(Posts.builder()
//                        .title("title")
//                        .content("content")
//                        .author("author")
//                .build());
//        Long updateId = savePosts.getId();
//        String expectTitle = "title2";
//        String expectContent = "content2";
//
//        PostUpdateRequestDto requestDto =
//                PostUpdateRequestDto.builder()
//                        .title(expectTitle)
//                        .content(expectContent)
//                        .build();
//
//        String url = "http://localhost:" + port + "/api/v1/post" + String.valueOf(updateId);
//        HttpEntity<PostUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(requestDto);
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH,requestDtoHttpEntity, Long.class);
//        //then
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//        List<Posts> postsList = postsRepository.findAll();
//
//        assertThat(postsList.get(0).getTitle()).isEqualTo(expectTitle);
//        assertThat(postsList.get(0).getContent()).isEqualTo(expectContent);
//    }
//}
