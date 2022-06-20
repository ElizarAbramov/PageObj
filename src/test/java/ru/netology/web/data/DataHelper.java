package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("123456");
    }

    @Value
    public static class CardInfo {
        String CardNumber;
    }

    public static CardInfo getFirstCardNumber() {
        return new CardInfo("5559 0000 0000 0001");
    }


    public static CardInfo getSecondCardNumber() {
        return new CardInfo("5559 0000 0000 0002");
    }

    @Value
    public static class TransferAmount {
        String amount;
    }

    public static TransferAmount getTransferAmount() {
        return new TransferAmount("1500");
    }

    public static TransferAmount getExceedingTransferAmount() {
        return new TransferAmount("21000");
    }

}