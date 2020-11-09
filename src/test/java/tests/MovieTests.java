package tests;

import common.BaseTest;
import libs.Database;
import models.MovieModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MovieTests extends BaseTest {
    private Database db;

    @BeforeMethod
    public void setup() {
        login
                .open()
                .with("julia@ninjaplus.com", "132902");

    }

    @BeforeSuite
    public void delorean() {
        db = new Database();
        db.insertMovies();
    }

    @Test
    public void shouldRegisterNewMovie() throws SQLException {
        String title = "Jumanji";
        String status = "Pré-venda";
        String ano = "2020";
        String datalançamento = "16/01/2020";
        List<String> atores = Arrays.asList("The Rock", "Jack Black", "Kevin Hart", "Karen Gillan", "Danny DeVito");
        String sinops = "Tentando a revisitar o mundo de Jumanji, Spencer decide consertar o bug " +
                "no jogo do game que permite que sejam transportados ao local. ";

        MovieModel movieData = new MovieModel(
                "Jumanji",
                "Pré-venda",
                2020,
                "16/01/2020",
                Arrays.asList("The Rock", "Jack Black", "Kevin Hart", "Karen Gillan", "Danny DeVito"),
                "Tentando a revisitar o mundo de Jumanji, Spencer decide consertar o bug " +
                        "no jogo do game que permite que sejam transportados ao local. ",
                "jumanji.png"
        );

        movie
                .add()
                .create(movieData)
                .items().findBy(text(movieData.title)).shouldBe(visible);
    }

    @Test
    public void searchOneMovie() {
        movie.search("Batman").items().shouldHaveSize(2);
    }

    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }
}
