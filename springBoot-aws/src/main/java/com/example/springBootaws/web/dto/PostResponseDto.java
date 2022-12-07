package com.example.springBootaws.web.dto;

import com.example.springBootaws.domain.post.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    private PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

    public static PostResponseDto of(Post entity) {
        return new PostResponseDto(entity);
    }
}
