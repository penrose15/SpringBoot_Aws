//package com.example.springBootaws.web;
//
//import com.example.springBootaws.TestConfig;
//import com.example.springBootaws.domain.post.Posts;
//import com.example.springBootaws.domain.post.PostsRepository;
//import com.example.springBootaws.web.dto.PostUpdateRequestDto;
//import com.example.springBootaws.web.dto.PostsSaveRequestDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.*;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
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
//    private WebApplicationContext context;
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    private MockMvc mvc;
//
//    @BeforeEach
//    void setHttpMethod() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                        .apply(springSecurity())
//                                .build();
//    }
//
//    @AfterEach
//    void clean() {
//        postsRepository.deleteAll();
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void postsApiControllerTest() throws Exception {
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
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(new ObjectMapper().writeValueAsString(requestDto)))
//                                .andExpect(status().isOk());
//        //then
//        List<Posts> postsList = postsRepository.findAll();
//        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
//        assertThat(postsList.get(0).getContent()).isEqualTo(content);
//        assertThat(postsList.get(0).getAuthor()).isEqualTo(author);
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
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
//        mvc.perform(patch(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//        //then
//        List<Posts> postsList = postsRepository.findAll();
//
//        assertThat(postsList.get(0).getTitle()).isEqualTo(expectTitle);
//        assertThat(postsList.get(0).getContent()).isEqualTo(expectContent);
//    }
//}
