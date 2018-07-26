package logica;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CambiarFrame;
import utils.Constantes;
import utils.Log;

public class LoginLogic {

    WebDriver driver;
    CambiarFrame cambiarFrame;
    Log log = new Log();
    By user = By.name("usuario");
    By pass = By.name("clave");
    By login = By.id("login");

    public LoginLogic(WebDriver driver){
        this.driver = driver;
    }

    protected void setUserName (String sUserName){
        driver.findElement(user).sendKeys(sUserName);
    }

    protected void setPassword (String sPassword){
        driver.findElement(pass).sendKeys(sPassword);
    }

    protected void clickLogin(){
        driver.findElement(login).click();
    }

    protected void pasarFrameLogin(){
        cambiarFrame = new CambiarFrame(driver);
        cambiarFrame.frameLogin();
    }

    protected void escribirLog(String user){
        Log.doLogging("Ingresando a Vento con el usuario: "+ user);
    }

    protected void abrirPagina(String url){
        log.crearLog(Constantes.Path_Log);
        Log.doLogging("Ingresando a la Maqueta: "+ url);
        driver.get(url);
    }

    protected void cerrarVentana(){
        Log.doLogging("Cerrando Ventana");
        driver.close();
    }

}
