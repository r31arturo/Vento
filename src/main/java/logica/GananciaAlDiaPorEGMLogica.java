package logica;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.CambiarFrame;
import utils.ConfigDB;
import utils.Log;
import utils.Waits;

import java.sql.ResultSet;

public class GananciaAlDiaPorEGMLogica {

    CambiarFrame cambiarFrame;
    WebDriver driver;
    Waits espera;
    ConfigDB ConectBD;

    ResultSet rs = null;
    Integer fing_error = 0;

    By fecha_desde = By.id("FechaDesde");
    By fecha_hasta = By.id("FechaHasta");
    By gerencia = By.id("div_gerencia");
    By sala = By.id("div_sala");
    By fabricante = By.id("div_fabricante");
    By modelo = By.id("div_modelo");
    By juego = By.id("div_juego");
    By token = By.id("div_token");
    By egm_todas = By.name("vlts_todos");
    By egm = By.id("fmaquina_vlts");
    By grupos = By.id("idgrupovlt");
    By reporte = By.name("salidaReporte");
    By consultar = By.id("btnConsultar");

    public GananciaAlDiaPorEGMLogica(WebDriver driver) {
        this.driver = driver;
    }

    public void setFecha_desde(String strFecha_desde) {
        Waits.waitTo(driver,fecha_desde);
        driver.findElement(fecha_desde).clear();
        driver.findElement(fecha_desde).sendKeys(strFecha_desde);
    }

    public void setFecha_hasta(String strFecha_hasta){
        Waits.waitToInvisibility(driver,fecha_hasta);
        driver.findElement(fecha_hasta).clear();
        WebElement cli = driver.findElement(fecha_hasta);
        cli.sendKeys(strFecha_hasta);
        cli.click();
    }


    public void clickConsultar(){
        Waits.waitTo(driver,consultar);
        driver.findElement(consultar).click();
    }

    public void mensajeLogConsultar(String strFecha_desde, String strFecha_hasta){
        Log.doLogging("Consulta realizada con fechas desde: "+strFecha_desde+" - fecha hasta "+strFecha_hasta);
    }


    public void mensajeError(String msgerror){
        String mensaje = driver.switchTo().alert().getText();
        if (mensaje.equals(msgerror)){
            Log.doLogging("Mensaje de error correcto: "+msgerror);
        }else{
            Log.doLogging("El mensaje de error no es el esperado: "+mensaje);
        }
        driver.switchTo().alert().accept();
        Assert.assertTrue(mensaje.equals(msgerror));
    }

    public void volverFormulario(){
        driver.navigate().back();
    }

    public void seleccionar_grupo(String opcionGrupo){
        Waits.waitTo(driver,grupos);
        Select opgrup = new Select(driver.findElement(grupos));
        opgrup.deselectAll();
        opgrup.selectByVisibleText(opcionGrupo);
    }


    public void seleccionar_egm(String opcionEgm){
        Waits.waitTo(driver,egm);
        Select opegm = new Select(driver.findElement(egm));
        opegm.deselectAll();
        opegm.selectByValue(opcionEgm);
    }

    public void pasarFramePrincipal(){
        cambiarFrame = new CambiarFrame(driver);
        cambiarFrame.framePrincipal();
    }

}

