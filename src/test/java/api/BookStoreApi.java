package api;

import io.qameta.allure.Step;
import models.AddListOfBooksBodyModel;

import java.util.List;

import static api.AuthorizationApi.*;
import static io.restassured.RestAssured.given;
import static specs.BookStoreApiSpec.*;

public class BookStoreApi {

    @Step("Удалить все книги из коллекции пользователя")
    public void deleteBooksFromCollectionStep() {
        given(authorisedRequestSpec(token))
                .queryParams("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(successful204ResponseSpec);
    }

    @Step("Добавить книгу в коллекцию")
    public void addBookToCollectionStep(String isbnData) {
        AddListOfBooksBodyModel addBookData = new AddListOfBooksBodyModel();
        addBookData.setUserId(userId);
        AddListOfBooksBodyModel.Isbn isbn = new AddListOfBooksBodyModel.Isbn();
        isbn.setIsbn(isbnData);
        addBookData.setCollectionOfIsbns(List.of(isbn));

        given(authorisedRequestSpec(token))
                .body(addBookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(successful201ResponseSpec);
    }
}
