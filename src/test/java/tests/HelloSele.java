package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.isChrome;

public class HelloSele{

    @Test
    public void OnAir(){
        isChrome();
        open("http://ninjaplus-web:5000/login");
        Assert.assertEquals(title(), "Ninja+");
    }
}
