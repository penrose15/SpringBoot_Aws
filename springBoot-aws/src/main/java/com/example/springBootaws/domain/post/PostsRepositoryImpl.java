package com.example.springBootaws.domain.post;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.springBootaws.domain.post.QPosts.posts;

@RequiredArgsConstructor
@Repository
public class PostsRepositoryImpl implements PostsRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @Override
    public Page<Posts> findAllDesc(Pageable pageable) {
        List<Posts> postList = queryFactory
                .select(posts)
                .from(posts)
                .orderBy(posts.id.desc())
                .offset(pageable.getOffset()) //start
                .limit(pageable.getPageSize()) //pageSize
                .fetch();


        int count = queryFactory.selectFrom(posts)
                .fetch().size();
        return new PageImpl<>(postList, pageable, count);
//        return PageableExecutionUtils.getPage(postList, pageable, count::fetchCount);
    }

    @Override
    public Page<Posts> findAllByTitleOrContent(String search, Pageable pageable) {
        List<Posts> postsList = queryFactory
                .select(posts)
                .distinct()
                .from(posts)
                .where(isTitleContains(search), isContentContains(search))
                .offset(pageable.getOffset()) //start
                .limit(pageable.getPageSize()) //pageSize
                .fetch();

        int count = queryFactory
                .selectFrom(posts)
                .where(isTitleContains(search), isContentContains(search))
                .fetch().size();
        return new PageImpl<>(postsList, pageable, count);
    }

    private BooleanExpression isTitleContains(String search) {
        return search != null ? posts.title.contains(search) : null;
    }
    private BooleanExpression isContentContains(String search) {
        return search != null ? posts.content.contains(search) : null;
    }
}
