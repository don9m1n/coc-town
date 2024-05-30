package com.dmk.coctown.domain.article.model;

import com.dmk.coctown.common.model.BaseEntity;
import com.dmk.coctown.domain.article.dto.ArticleWriteRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleType articleType;

    @Builder
    private Article(String title, String content, ArticleType articleType) {
        this.title = title;
        this.content = content;
        this.articleType = articleType;
    }

    public static Article of(ArticleWriteRequest request) {
        return Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .articleType(request.getArticleType())
                .build();
    }


}
