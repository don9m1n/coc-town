package com.dmk.coctown.domain.article.repository;

import com.dmk.coctown.domain.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
