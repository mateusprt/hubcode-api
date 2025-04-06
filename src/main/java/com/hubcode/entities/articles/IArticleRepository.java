package com.hubcode.entities.articles;

public interface IArticleRepository {

    Article pesquisar(int id);
    Article salvar(Article article);
}
