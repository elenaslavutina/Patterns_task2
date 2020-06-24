package ru.netology.web;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import lombok.Data;
import java.util.Locale;
import static io.restassured.RestAssured.given;

@Data
public class CreateUser {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static RegistrationDto generateCorrectUser() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationDto validUser = new RegistrationDto(
                faker.internet().emailAddress(),
                faker.internet().password(),
                "active");
        makeRegistration(validUser);
        return validUser;
    }

    public static RegistrationDto generateCorrectBlockedUser() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.internet().emailAddress();
        String password = faker.internet().password();
        makeRegistration(new RegistrationDto(login,password,"blocked"));
        return new RegistrationDto(login,password,"blocked");
    }

    public static RegistrationDto generateIncorrectLogin() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.internet().emailAddress();
        String password = faker.internet().password();
        String status = "active";
        makeRegistration(new RegistrationDto(login,password,status));
        return new RegistrationDto(login.toUpperCase(),password,status);
    }

    public static RegistrationDto generateIncorrectPassword() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.internet().emailAddress();
        String status = "active";
        String password = faker.internet().password();
        String incorrectPassword = faker.internet().password();
        makeRegistration(new RegistrationDto(login,password,status));
        return new RegistrationDto(login,incorrectPassword,status);
    }

    static void makeRegistration(RegistrationDto registrationDto) {
        given()
                .spec(requestSpec)
                .body(registrationDto)
        .when()
                .post("/api/system/users")
        .then()
                .statusCode(200);
    }
}