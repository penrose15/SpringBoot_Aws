package com.example.springBootaws.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsRepositoryCustom {
    Page<Posts> findAllDesc(Pageable pageable);

    Page<Posts> findAllByTitleOrContent(String search, Pageable pageable);
}
