package com.dmk.coctown.domain.article.dto;

import com.dmk.coctown.domain.article.model.ArticleType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleWriteRequest {

    private String title;
    private String content;
    private ArticleType articleType;

    public static ArticleWriteRequest of(String title, String content, ArticleType articleType) {
        return ArticleWriteRequest.builder()
                .title(title)
                .content(content)
                .articleType(articleType)
                .build();
    }
}
