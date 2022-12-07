package com.example.springBootaws.web;


import com.example.springBootaws.service.post.PostService;
import com.example.springBootaws.web.dto.PostResponseDto;
import com.example.springBootaws.web.dto.PostUpdateRequestDto;
import com.example.springBootaws.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostApiController {
    private final PostService postService;

    @PostMapping("/post")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PatchMapping("/post/{id}")
    public Long update(@PathVariable Long id,
                                       @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @GetMapping("/post/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }
}
