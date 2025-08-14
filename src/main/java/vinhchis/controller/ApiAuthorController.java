package vinhchis.controller;

import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import vinhchis.BookApplication;
import vinhchis.builder.AuthorBuilder;
import vinhchis.context.DatabaseContext;
import vinhchis.request.AddAuthorRequest;
import vinhchis.response.AddAuthorResponse;
import vinhchis.entity.Author;
import vinhchis.factory.EntityFactory;
import vinhchis.handler.ChainOfResponsibility;
import vinhchis.repository.AuthorRepository;

import java.util.HashMap;
import java.util.Map;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;


@Controller("/api/v1")
public class ApiAuthorController {
    final BookApplication application = BookApplication.getInstance();
    final DatabaseContext databaseContext = application.getDatabaseContext();
    final EntityFactory entityFactory = application.getEntityFactory();

    final AuthorRepository authorRepository = databaseContext.newRepository(Author.class);

    @DoPost("/authors/add")
    public ResponseEntity addAuthor(@RequestBody AddAuthorRequest request) throws Exception {
        return new ChainOfResponsibility()
                .addFirstVoidHandler(() -> {
                    final Map<String, String> errors = new HashMap<>();
                    if (isBlank(request.getAuthorName())) {
                        errors.put("authorName", "required");
                    }

                    if (!errors.isEmpty()) {
                        throw new HttpBadRequestException(errors);
                    }
                })
                .addFirstHandler(() -> {
                    final Author author = entityFactory.newEntityBuilder(AuthorBuilder.class).name(request.getAuthorName()).build();
                    authorRepository.save(author);
                    return new AddAuthorResponse(author.getId());
                })
                .handle();
    }


}
