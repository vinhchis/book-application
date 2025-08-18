package vinhchis.controller;

import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import vinhchis.BookApplication;
import vinhchis.builder.CategoryBuilder;
import vinhchis.request.AddCategoryRequest;
import vinhchis.response.AddCategoryResponse;
import vinhchis.entity.Category;
import vinhchis.factory.EntityFactory;
import vinhchis.handler.ChainOfResponsibility;
import vinhchis.repository.CategoryRepository;

import java.util.HashMap;
import java.util.Map;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

@Controller("/api/v1")
public class ApiCategoryController {
    private final BookApplication bookApplication = BookApplication.getInstance();
    private final EntityFactory entityFactory = bookApplication.getEntityFactory();

    private final CategoryRepository categoryRepository = bookApplication
            .getDatabaseContext()
            .newRepository(Category.class);

    @DoPost("/categories/add")
    public ResponseEntity addCategory(@RequestBody AddCategoryRequest request) throws Exception {
        return new ChainOfResponsibility()
                .addPreProcessor(() -> {
                    final Map<String, String> errors = new HashMap<>();
                    if (isBlank(request.getCategoryName())) {
                        errors.put("categoryName", "required");
                    }


                    if (!errors.isEmpty()) {
                        throw new HttpBadRequestException(errors);
                    }
                })
                .addDataCreator(() -> {
                    final Category category = entityFactory.newEntityBuilder(CategoryBuilder.class).name(request.getCategoryName()).build();
                    categoryRepository.save(category);
                    return new AddCategoryResponse(category.getId());
                })
                .handle();
    }
}
