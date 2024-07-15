package tests;

import api.AuthorizationApi;
import api.BookStoreApi;
import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class BookStoreTests extends TestBase {
    String isbn = "9781449365035";
    String bookName = "Speaking JavaScript";
    @Test
    @WithLogin
    @DisplayName("Удаление книги из коллекции")
    void deleteBookFromCollectionTest() {

        BookStoreApi apiSteps = new BookStoreApi();

        apiSteps.deleteBooksFromCollectionStep();
        apiSteps.addBookToCollectionStep(isbn);

        step("Открыть страницу профиля", () -> {
            open("/profile");
        });
        step("Проверить, что пользователь авторизован", () -> {
            assertThat($("#userName-value").getText()).isEqualTo(AuthorizationApi.config.userName());
        });
        step("Проверить, что книга есть в коллекции", () -> {
            assertThat($(".ReactTable").getText()).contains(bookName);

        });
        step("Удалить книгу из коллекции", () -> {
            $("#delete-record-undefined").click();
            $("#closeSmallModal-ok").click();
            switchTo().alert().accept();
        });
        step("Проверить, что книга удалена из коллекции", () -> {
            assertThat($(".ReactTable").getText()).contains("No rows found");
        });
    }
}
