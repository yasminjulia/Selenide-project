package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import models.MovieModel;
import org.openqa.selenium.Keys;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MoviePage {
    public MoviePage add(){
        $(".movie-add").click();
        return this;
    }
    public MoviePage search(String value){
        $("input[placeholder^=Pesquisar]").setValue(value);
        $("#search-movie").click();
        return this;
    }
    public MoviePage create(MovieModel movie) {
        $("input[name=title]").setValue(movie.title);
        this.selectStatus(movie.status);
        $("input[name=year]").setValue(movie.year.toString());
        $("input[name=release_date]").setValue(movie.releaseDate);
        this.inputCast(movie.cast);
        $("textarea[name=overview]").setValue(movie.plot);
        this.upload(movie.cover);
        $("#create-movie").click();

        return this;
    }
    public ElementsCollection items(){
        return $$("table tbody tr");
    }
    private void upload(File cover){
        //código que executa comando em javascript para remover uma classe que oculta o elemento
        String js = "document.getElementById('upcover').classList.remove('el-upload-__input');";
        executeJavaScript(js);
        $("#upcover").uploadFile(cover);
    }
    private void inputCast(List<String> atores){
        SelenideElement element = $(".cast");
        for(String actor : atores){
            element.setValue(actor);
            element.sendKeys(Keys.TAB);
        }
    }
    private void selectStatus(String status){
        $("input[placeholder=Status]").click();
        $$("ul li span").findBy(text(status)).click();
    }
}
