package models;

import lombok.Data;

import java.util.List;

@Data
public class AddListOfBooksBodyModel {
    String userId;
    List<Isbn> collectionOfIsbns;

    @Data
    public static class Isbn {
        private String isbn;
    }
}
