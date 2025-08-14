package vinhchis.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookRequest {
    private String bookName;
    private long authorId;
    private long categoryId;
}
