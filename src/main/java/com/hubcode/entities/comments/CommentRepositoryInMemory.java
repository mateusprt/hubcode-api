package com.hubcode.entities.comments;

import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryInMemory implements ICommentRepository {

    private List<Comment> comments = new ArrayList<>();

    @Override
    public Comment pesquisar(int id) {
        return comments.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Comment salvar(Comment comment) {
        comments.add(comment);
        return comment;
    }
}
