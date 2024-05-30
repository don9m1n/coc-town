package com.dmk.coctown.domain.article.service;

import com.dmk.coctown.domain.article.dto.ArticleWriteRequest;
import com.dmk.coctown.domain.article.model.Article;
import com.dmk.coctown.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void saveArticle(ArticleWriteRequest request) {

        articleRepository.save(Article.of(request));
    }
}
