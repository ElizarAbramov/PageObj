package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ReplenishmentPage {
    private final SelenideElement depositField = $("[data-test-id='amount'] [class = 'input__control']");
    private final SelenideElement fromField = $("[placeholder='0000 0000 0000 0000']");

    private final SelenideElement heading = $("[class='heading heading_size_xl heading_theme_alfa-on-white']");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");


    public ReplenishmentPage() {
        heading.shouldBe(visible).shouldHave(text("Пополнение"));
    }

    public DashboardPage moneyTransfer(DataHelper.TransferAmount transferAmount) {
        depositField.setValue((transferAmount.getAmount()));
        fromField.setValue(String.valueOf(DataHelper.getSecondCardNumber()));
        transferButton.click();
        return new DashboardPage();
    }

    public void emptyFields() {
        transferButton.click();
        $("[data-test-id='error-notification']").shouldBe(visible).shouldHave(text("Ошибка"));
    }
}