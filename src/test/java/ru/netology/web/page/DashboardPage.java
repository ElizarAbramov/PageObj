package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");

    private final SelenideElement depositButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] [class = 'button__text']");
    private final SelenideElement depositField = $("[data-test-id='amount'] [class = 'input__control']");
    private final SelenideElement fromField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public CheckCardBalance moneyTransfer(DataHelper.TransferAmount transferAmount, DataHelper.SecondCardInfo secondCardInfo) {
        depositButton.click();
        depositField.setValue((transferAmount.getAmount()));
        fromField.setValue(secondCardInfo.getSecondNum());
        transferButton.click();
        return new CheckCardBalance();
    }


    public void emptyFields() {
        transferButton.click();
        $("[data-test-id='error-notification']").shouldBe(visible).shouldHave(text("Ошибка"));
    }

    public void pushFirstButton() {
        depositButton.click();
    }
}