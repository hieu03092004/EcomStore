package com.fit.ecommerce.services;

import java.time.LocalDate;
import java.util.List;

import com.fit.ecommerce.dtos.request.article.ArticleAddRequest;
import com.fit.ecommerce.dtos.response.article.ArticleResponse;
import com.fit.ecommerce.dtos.response.base.PageResponse;

public interface ArticleService {

    ArticleResponse createArticle(ArticleAddRequest articleRequest);

    ArticleResponse getArticleBySlug(String slug);

    ArticleResponse getArticleById(Long id);

//    ArticleResponse updateArticle(String slug, ArticleAddRequest articleAddRequest);

    ArticleResponse updateArticle(Long id, ArticleAddRequest articleAddRequest);

    PageResponse<ArticleResponse> getAllArticlesForCustomer(int page, int limit,
                                                                  String title,
                                                                  Long categoryId,
                                                                  LocalDate createdDate);

    PageResponse<ArticleResponse> getAllArticlesForAdmin(int page, int limit,
                                                               Boolean status,
                                                               String title,
                                                               Long categoryId,
                                                               LocalDate createdDate);

    void changeStatusArticle(Long id);
}