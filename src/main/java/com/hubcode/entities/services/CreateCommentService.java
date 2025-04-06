package com.hubcode.entities.services;

import com.hubcode.entities.articles.Article;
import com.hubcode.entities.articles.IArticleRepository;
import com.hubcode.entities.comments.Comment;
import com.hubcode.entities.comments.ICommentRepository;
import com.hubcode.exceptions.ArticleNotFoundException;
import com.hubcode.exceptions.InvalidCommentException;

public class CreateCommentService {
    private final IArticleRepository articleRepository;
    private final ICommentRepository commentsRepository;

    public CreateCommentService(IArticleRepository articleRepository, ICommentRepository commentsRepository) {
        this.articleRepository = articleRepository;
        this.commentsRepository = commentsRepository;
    }

    public Comment create(int articleId, Comment comment) throws ArticleNotFoundException, InvalidCommentException {
        Article articleFound = this.articleRepository.pesquisar(articleId);
        if(articleFound == null) {
            throw new ArticleNotFoundException("Artigo não localizado.");
        }
        if(comment.getComment().isEmpty()) {
            throw new InvalidCommentException("Comentário não pode ser vazio.");
        }
        return this.commentsRepository.salvar(comment);
    }
}
