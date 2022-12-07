package com.example.springBootaws.service.post;

import com.example.springBootaws.domain.post.Post;
import com.example.springBootaws.domain.post.PostRepository;
import com.example.springBootaws.web.dto.PostResponseDto;
import com.example.springBootaws.web.dto.PostUpdateRequestDto;
import com.example.springBootaws.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        Post savedPost = postRepository.save(requestDto.toEntity());

        return savedPost.getId();
    }

    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. : id=" + id));

        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다. : id = " + id));
        return PostResponseDto.of(entity);
    }


}
