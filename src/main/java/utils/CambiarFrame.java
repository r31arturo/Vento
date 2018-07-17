package utils;

import org.openqa.selenium.WebDriver;
import java.util.Iterator;
import java.util.Set;

public class CambiarFrame {

    WebDriver driver;

    public CambiarFrame(WebDriver driver) {

        this.driver = driver;
    }

    public void frameLogin() {

        driver.switchTo().defaultContent();
        driver.switchTo().frame("login");
    }

    public void frameMenu() {

        driver.switchTo().defaultContent();
        driver.switchTo().frame("login");
        driver.switchTo().frame("leftFrame");
    }

    public void framePrincipal() {

        driver.switchTo().defaultContent();
        driver.switchTo().frame("login");
        driver.switchTo().frame("mainFrame");
    }

    public void cambiarPag(){

        Set<String> handles =driver.getWindowHandles();
        Iterator<String> it = handles.iterator();

        while (it.hasNext()) {
            //String parent = it.next();
            String newwin = it.next();
            driver.switchTo().window(newwin);

        }
    }

    public void volverPag(){

        Set<String> handles =driver.getWindowHandles();
        Iterator<String> it = handles.iterator();

        while (it.hasNext()) {
            String parent = it.next();
            driver.switchTo().window(parent);

        }

    }

    public void frameClose(){

        driver.close();
    }
}
