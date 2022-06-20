package ru.netology.web;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CheckCardBalance;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    @BeforeEach
    void setUpPage() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var firstCardBalance = new CheckCardBalance().getFirstCardBalance();
        var secondCardBalance = new CheckCardBalance().getSecondCardBalance();
        var transferAmount = DataHelper.getTransferAmount();
        var secondCardInfo = DataHelper.getSecondCardNumber();
        var chooseCard = new CheckCardBalance().click();
        var checkCardBalanceAfter = dashboardPage.moneyTransfer(transferAmount, secondCardInfo);
        var firstCardAfter = checkCardBalanceAfter.getFirstCardBalance();
        var secondCardAfter = checkCardBalanceAfter.getSecondCardBalance();
        int transfer = 1500;
        assertEquals(firstCardBalance + transfer, firstCardAfter);
        assertEquals(secondCardBalance - transfer, secondCardAfter);
    }

    @Test
    void shouldShowNotificationWhenFieldsEmpty() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var chooseCard = new CheckCardBalance().click();
        dashboardPage.emptyFields();
    }

    @Test
    void shouldShowNotificationWhenTransferIncorrect() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        var firstCardBalance = new CheckCardBalance().getFirstCardBalance();
        var secondCardBalance = new CheckCardBalance().getSecondCardBalance();
        var transferAmount = DataHelper.getExceedingTransferAmount();
        var secondCardInfo = DataHelper.getSecondCardNumber();
        var chooseCard = new CheckCardBalance().click();
        var checkCardBalanceAfter = dashboardPage.moneyTransfer(transferAmount, secondCardInfo);
        var firstCardAfter = checkCardBalanceAfter.getFirstCardBalance();
        var secondCardAfter = checkCardBalanceAfter.getSecondCardBalance();
        assertEquals(secondCardBalance, secondCardAfter);
        assertEquals(firstCardBalance, firstCardAfter);
    }
}