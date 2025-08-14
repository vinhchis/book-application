package vinhchis.controller;

import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import vinhchis.BookApplication;
import vinhchis.builder.BookBuilder;
import vinhchis.context.DatabaseContext;
import vinhchis.request.AddBookRequest;
import vinhchis.response.AddBookResponse;
import vinhchis.entity.Book;
import vinhchis.factory.EntityFactory;
import vinhchis.handler.ChainOfResponsibility;
import vinhchis.repository.BookRepository;

import java.util.HashMap;
import java.util.Map;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

@Controller("/api/v1")
public class ApiBookController {
    final BookApplication application = BookApplication.getInstance();
    final DatabaseContext databaseContext = application.getDatabaseContext();
    final EntityFactory entityFactory = application.getEntityFactory();

    final BookRepository bookRepository = databaseContext.newRepository(Book.class);

    @DoPost("/books/add")
    public ResponseEntity addBook(@RequestBody AddBookRequest request) throws Exception {
        return new ChainOfResponsibility()
                .addFirstVoidHandler(()->{
                    final Map<String, String> errors = new HashMap<>();
                    if (isBlank(request.getBookName())) {
                        errors.put("bookName", "required");
                    }

                    if(request.getCategoryId() <= 0){
                        errors.put("categoryId", "required");
                    }

                    if(request.getAuthorId() <= 0){
                        errors.put("authorId", "required");
                    }

                    if (!errors.isEmpty()) {
                        throw new HttpBadRequestException(errors);
                    }
                })
                .addFirstHandler(()->{
                    Book book = entityFactory.newEntityBuilder(BookBuilder.class)
                            .name(request.getBookName())
                            .categoryId(request.getCategoryId())
                            .authorId(request.getAuthorId())
                            .build();
                    bookRepository.save(book);
                    return new AddBookResponse(book.getId());
                })
                .handle();
    }
}
