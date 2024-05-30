package com.dmk.coctown.domain.article.service;

import com.dmk.coctown.domain.article.dto.ArticleWriteRequest;
import com.dmk.coctown.domain.article.model.Article;
import com.dmk.coctown.domain.article.model.ArticleType;
import com.dmk.coctown.domain.article.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@DisplayName("게시판 서비스 로직 테스트")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 정보를 입력하면, 커뮤니티 게시글을 저장한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSaveCommunityArticle() {

        // given
        ArticleWriteRequest request = ArticleWriteRequest.of("title1", "content1", ArticleType.COMMUNITY);
        given(articleRepository.save(any(Article.class))).willReturn(mock(Article.class));

        // when
        sut.saveArticle(request);

        // then
        then(articleRepository).should().save(any(Article.class));
    }

}