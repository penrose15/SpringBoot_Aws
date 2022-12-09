package com.example.springBootaws.service.post;

import com.example.springBootaws.domain.post.Posts;
import com.example.springBootaws.domain.post.PostsRepository;
import com.example.springBootaws.domain.post.PostsRepositoryCustom;
import com.example.springBootaws.web.dto.PostResponseDto;
import com.example.springBootaws.web.dto.PostUpdateRequestDto;
import com.example.springBootaws.web.dto.PostsListResponseDto;
import com.example.springBootaws.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostsRepository postsRepository;
    private final PostsRepositoryCustom postsRepositoryImpl;

    public Long save(PostsSaveRequestDto requestDto) {
        Posts savedPosts = postsRepository.save(requestDto.toEntity());

        return savedPosts.getId();
    }

    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. : id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. : id = " + id));
        return PostResponseDto.of(entity);
    }

    public List<PostsListResponseDto> findAllDesc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        return postsRepositoryImpl.findAllDesc(pageable).getContent()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. + id : " + id));
        postsRepository.delete(posts);
    }

    public List<PostsListResponseDto> findAllByTitleOrContent(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        return postsRepositoryImpl.findAllByTitleOrContent(search, pageable)
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }


}
