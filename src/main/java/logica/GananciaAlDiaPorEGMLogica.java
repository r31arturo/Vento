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
import java.util.concurrent.TimeUnit;

public class GananciaAlDiaPorEGMLogica {

    CambiarFrame cambiarFrame;
    WebDriver driver;
    Waits espera = new Waits();
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
    By report = By.name("salidaReporte");
    By consultar = By.id("btnConsultar");

    public GananciaAlDiaPorEGMLogica(WebDriver driver) {
        this.driver = driver;
    }

    protected void setFecha_desde(String strFecha_desde) {
        Waits.waitTo(driver,fecha_desde);
        driver.findElement(fecha_desde).clear();
        driver.findElement(fecha_desde).sendKeys(strFecha_desde);
    }

    protected void setFecha_hasta(String strFecha_hasta){
        Waits.waitTo(driver,fecha_hasta);
        driver.findElement(fecha_hasta).clear();
        espera.waitSecs(2);
        WebElement completeDate = driver.findElement(fecha_hasta);
        completeDate.sendKeys(strFecha_hasta);
        completeDate.click();
    }


    protected void clickConsultar(){
        Waits.waitTo(driver,consultar);
        driver.findElement(consultar).click();
    }

    protected void mensajeLogConsultar(String strFecha_desde, String strFecha_hasta){
        Log.doLogging("Consulta realizada con fechas desde: "+strFecha_desde+" - fecha hasta "+strFecha_hasta);
    }


    protected void mensajeError(String msgerror){
        //espera.waitSecs(2);
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

    protected void seleccionar_grupo(String opcionGrupo){
        Waits.waitTo(driver,grupos);
        Select opgrup = new Select(driver.findElement(grupos));
        opgrup.deselectAll();
        Waits espera = new Waits();
        espera.waitSecs(2);
        opgrup.selectByVisibleText(opcionGrupo);
    }


    protected void seleccionar_egm(String opcionEgm){
        Waits.waitTo(driver,egm);
        Select opegm = new Select(driver.findElement(egm));
        opegm.deselectAll();
        Waits espera = new Waits();
        espera.waitSecs(2);
        opegm.selectByValue(opcionEgm);
    }

    protected void pasarFramePrincipal(){
        cambiarFrame = new CambiarFrame(driver);
        cambiarFrame.framePrincipal();
    }

    protected void selectReport (String vReport){
        Waits.waitTo(driver,report);
        Select oSelector = new Select(driver.findElement(report));
        oSelector.selectByVisibleText(vReport);
    }

}

