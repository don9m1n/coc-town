package com.dmk.coctown.domain.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleType {

    COMMUNITY("커뮤니티"), BASE_LINK("배치링크");

    private final String type;
}
