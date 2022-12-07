package com.example.springBootaws.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
