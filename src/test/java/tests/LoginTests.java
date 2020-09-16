package tests;

import common.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class LoginTests extends BaseTest {

    @DataProvider(name = "login-provider")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"julia@ninjaplus.com", "pwd12", "Usuário e/ou senha inválidos"},
                {"404@gmail.com", "pwd123", "Usuário e/ou senha inválidos"},
                {"", "pwd123", "Opps. Cadê o email?"},
                {"julia@ninjaplus.com", "", "Opps. Cadê a senha?"}
        };
    }

    @Test
    public void loggedUser() {
        login
                .open()
                .with("julia@ninjaplus.com", "132902");

        side.loggUser().shouldHave(text("julia"));
    }

    @Test(dataProvider = "login-provider")
    public void LoginAlerts(String email, String pass, String expectAlert) {


        login
                .open()
                .with(email, pass)
                .alert().shouldHave(text(expectAlert));
    }
    @AfterMethod
    public void cleanup(){
        login.clearSession();
    }
}
