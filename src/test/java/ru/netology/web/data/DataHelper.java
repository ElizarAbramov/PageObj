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
    public static class FirstCardInfo {
        String firstNum;
    }

    public static FirstCardInfo getFirstCardNumber(AuthInfo authInfo) {
        return new FirstCardInfo("5559 0000 0000 0001");
    }

    @Value
    public static class SecondCardInfo {
        String secondNum;
    }

    public static SecondCardInfo getSecondCardNumber(AuthInfo authInfo) {
        return new SecondCardInfo("5559 0000 0000 0002");
    }

    @Value
    public static class TransferAmount {
        String amount;
    }

    public static TransferAmount getTransferAmount(AuthInfo authInfo) {
        return new TransferAmount("1500");
    }

}