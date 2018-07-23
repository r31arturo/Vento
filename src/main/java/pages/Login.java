package pages;

import logica.LoginLogic;
import org.openqa.selenium.WebDriver;

public class Login extends LoginLogic {

    public Login(WebDriver driver){
        super(driver);
    }

    public void openPage(String url){
        this.abrirPagina(url);
    }

    public void loginTo(String sUserName,String sPassword){
        this.pasarFrameLogin();
        this.escribirLog(sUserName);
        this.setUserName(sUserName);
        this.setPassword(sPassword);
        this.clickLogin();
    }

    public void closePage(){
        this.cerrarVentana();
    }
}
