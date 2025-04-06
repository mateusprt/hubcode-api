package com.hubcode.entities.comments;

public interface ICommentRepository {
    Comment pesquisar(int id);
    Comment salvar(Comment comment);
}
