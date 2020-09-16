package components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SideBar {
    public SelenideElement loggUser(){
        return $(".user .info span");
    }
}
