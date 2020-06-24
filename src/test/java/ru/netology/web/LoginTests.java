package ru.netology.web;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTests {
    LoginPage loginPage = new LoginPage();

    @Test
    void shouldSubmitRequestForCorrectUser() {
        RegistrationDto user = CreateUser.generateCorrectUser();
        loginPage.setLogin(user.getLogin());
        loginPage.setPassword(user.getPassword());
        loginPage.continueButton();
        loginPage.verify("Личный кабинет");
    }

    @Test
    void shouldNotSubmitRequestForBlockedUser() {
        RegistrationDto user = CreateUser.generateCorrectBlockedUser();
        loginPage.setLogin(user.getLogin());
        loginPage.setPassword(user.getPassword());
        loginPage.continueButton();
        loginPage.verify("Ошибка");
    }


    @Test
    void shouldNotSubmitRequestForIncorrectPassword() {
        RegistrationDto user = CreateUser.generateIncorrectPassword();
        loginPage.setLogin(user.getLogin());
        loginPage.setPassword(user.getPassword());
        loginPage.continueButton();
        loginPage.verify("Ошибка");
    }

    @Test
    void shouldNotSubmitRequestForIncorrectLogin() {
        RegistrationDto user = CreateUser.generateIncorrectLogin();
        loginPage.setLogin(user.getLogin());
        loginPage.setPassword(user.getPassword());
        loginPage.continueButton();
        loginPage.verify("Ошибка");
    }
}