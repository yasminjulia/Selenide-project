package common;

import com.codeborne.selenide.Configuration;
import components.SideBar;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.MoviePage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.codeborne.selenide.Selenide.screenshot;

public class BaseTest {

    protected static LoginPage login;
    protected static SideBar side;
    protected static MoviePage movie;

    @BeforeMethod
    public void start() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://ninjaplus-web:5000";
        Configuration.timeout = 30000;
        login = new LoginPage();
        side = new SideBar();
        movie = new MoviePage();
    }

    @AfterMethod
    public void finish() {
        String tempshot = screenshot("temp_shot");
        try {
            BufferedImage bimg = ImageIO.read(new File(tempshot));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimg, "png", baos);
            byte[] finalshot = baos.toByteArray();
            io.qameta.allure.Allure.addAttachment("Evidencia", new ByteArrayInputStream(finalshot));

        } catch (Exception e) {
            System.out.println("Deu erro ao anexar o screenshot: " + e.getMessage());
        }

    }
}
