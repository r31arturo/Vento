package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CambiarFrame;
import utils.Log;

public class Login {

    WebDriver driver;
    CambiarFrame cambiarFrame;
    By user = By.name("usuario");
    By pass = By.name("clave");
    By login = By.id("login");

    public Login(WebDriver driver){
        this.driver = driver;
    }

    public void setUserName (String sUserName){
        driver.findElement(user).sendKeys(sUserName);
    }

    public void setPassword (String sPassword){
        driver.findElement(pass).sendKeys(sPassword);
    }

    public void clickLogin(){
        driver.findElement(login).click();
    }

    public void pasarFrameLogin(){
        cambiarFrame = new CambiarFrame(driver);
        cambiarFrame.frameLogin();
    }

    public void escribirLog(String user){
        Log.doLogging("Ingresando a Vento con el usuario: "+ user);
    }



    public void loginTo(String sUserName,String sPassword){
        this.pasarFrameLogin();
        this.escribirLog(sUserName);
        this.setUserName(sUserName);
        this.setPassword(sPassword);
        this.clickLogin();
    }
}
