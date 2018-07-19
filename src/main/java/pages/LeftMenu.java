package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CambiarFrame;
import utils.Log;

import java.util.List;

public class LeftMenu {

    WebDriver driver;
    CambiarFrame cambiarFrame;
    By principal = By.cssSelector("#list");
    By subprincipal = By.cssSelector("#list > .title");
    By menu = By.cssSelector("#list3");
    By submenu = By.cssSelector("#list3 > .title");
    By modulo = By.cssSelector("#list3 > .submenu");

    public LeftMenu(WebDriver driver){
        this.driver = driver;
    }

    public void introMenu(String name_menu){
        WebElement elem_menu = driver.findElement(principal);
        List<WebElement> coleccion_menu = elem_menu.findElements(subprincipal);
        for(WebElement elem_row_menu : coleccion_menu){
            String buscar_menu = elem_row_menu.getText();
            if ( buscar_menu.equals(name_menu)){
                WebElement find_menu = elem_row_menu;
                find_menu.findElement(By.linkText(name_menu)).click();
                break;
            }
        }
    }

    public void introSubMenu(String name_submenu){
        WebElement elem_sub = driver.findElement(menu);
        List<WebElement> coleccion_sub = elem_sub.findElements(submenu);
        for(WebElement elem_row_sub : coleccion_sub){
            String buscar_sub = elem_row_sub.getText();
            if ( buscar_sub.equals(name_submenu)){
                WebElement find_sub = elem_row_sub;
                find_sub.click();
                break;
            }
        }
    }

    public void introModulo(String name_modulo){
        WebElement elem_mod = driver.findElement(menu);
        List<WebElement> coleccion_mod = elem_mod.findElements(modulo);
        for(WebElement elem_row_mod : coleccion_mod){
            String buscar_mod = elem_row_mod.getText();
            if ( buscar_mod.contains(name_modulo)){
                WebElement find_mod = elem_row_mod;
                find_mod.findElement(By.linkText(name_modulo)).click();
                break;
            }
        }
    }

    public void pasarFrameMenu(){
        cambiarFrame = new CambiarFrame(driver);
        cambiarFrame.frameMenu();
    }

    public void escribirLog(String menu, String submenu, String modulo){
        Log.doLogging("Ingresando al Path: "+menu+"/"+submenu+"/"+modulo);
    }

    public void ingresarPath(String menu, String submenu, String modulo){
        this.pasarFrameMenu();
        this.escribirLog(menu,submenu,modulo);
        this.introMenu(menu);
        this.introSubMenu(submenu);
        this.introModulo(modulo);

    }
}
