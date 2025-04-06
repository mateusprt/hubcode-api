package com.hubcode.entities.articles;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryInMemory implements IArticleRepository {
    private final List<Article> articles = new ArrayList<>();

    @Override
    public Article pesquisar(int id) {
        return articles.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Article salvar(Article article) {
        articles.add(article);
        return article;
    }
}
