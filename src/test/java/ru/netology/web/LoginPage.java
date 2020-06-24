package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.cssSelector;

public class LoginPage {

    private SelenideElement form;


    LoginPage() {
        open("http://localhost:9999");
        form = $("[action]");
    }


    public void setLogin(String login) {

        form.$(cssSelector("[data-test-id=login] input")).sendKeys(login);
    }

    public void setPassword(String password) {

        form.$(cssSelector("[data-test-id=password] input")).sendKeys(password);
    }


    public void continueButton() {

        form.$(cssSelector("[data-test-id=action-login] ")).click();
    }

    public void verify(String text) {

        $(byText(text)).waitUntil(Condition.visible, 15000);

    }


}