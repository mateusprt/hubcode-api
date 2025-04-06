import com.hubcode.entities.articles.Article;
import com.hubcode.entities.articles.ArticleRepositoryInMemory;
import com.hubcode.entities.articles.IArticleRepository;
import com.hubcode.entities.comments.Comment;
import com.hubcode.entities.comments.CommentRepositoryInMemory;
import com.hubcode.entities.comments.ICommentRepository;
import com.hubcode.entities.services.CreateCommentService;
import com.hubcode.exceptions.ArticleNotFoundException;
import com.hubcode.exceptions.InvalidCommentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateServiceTest {

    private IArticleRepository articleRepository;
    private ICommentRepository commentRepository;
    private CreateCommentService service;

    @BeforeEach
    void setup() {
        articleRepository = new ArticleRepositoryInMemory();
        Article article1 = new Article(1, "some title 1", "some description 1");
        Article article2 = new Article(2, "some title 2", "some description 2");

        articleRepository.salvar(article1);
        articleRepository.salvar(article2);

        commentRepository = new CommentRepositoryInMemory();
        service = new CreateCommentService(articleRepository, commentRepository);
    }

    @Test
    void shouldThrowArticleNotFoundExceptionWhenArticleDoesNotExist() throws ArticleNotFoundException, InvalidCommentException {
        int nonExistentArticleId = 3;
        Comment comment = new Comment(1, nonExistentArticleId, "some comment");
        assertThrows(ArticleNotFoundException.class, () -> {
            service.create(nonExistentArticleId, comment);
        });
    }

    @Test
    void shouldCreateCommentWhenArticleExists() throws ArticleNotFoundException, InvalidCommentException {
        int article1Id = 1;
        Comment comment = new Comment(1, article1Id, "some comment");
        Comment commentCreated = service.create(article1Id, comment);

        assertEquals(comment.getId(), commentCreated.getId());
    }

    @Test
    void shouldThrowInvalidCommentExceptionWhenCommentIsEmpty() throws ArticleNotFoundException, InvalidCommentException {
        int validArticleId = 1;
        Comment comment = new Comment(1, validArticleId, "");
        assertThrows(InvalidCommentException.class, () -> {
            service.create(validArticleId, comment);
        });
    }
}
