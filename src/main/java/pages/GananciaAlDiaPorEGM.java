package pages;

import logica.GananciaAlDiaPorEGMLogica;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.ConfigDB;
import utils.Log;
import utils.Waits;

import java.sql.ResultSet;

public class GananciaAlDiaPorEGM extends GananciaAlDiaPorEGMLogica{

    public GananciaAlDiaPorEGM(WebDriver driver){ super(driver);
    }


    public void consulta(String strFecha_desde,
                         String strFecha_hasta,
                         String strReport) {

        this.pasarFramePrincipal();
        this.setFecha_desde(strFecha_desde);
        this.setFecha_hasta(strFecha_hasta);
        this.selectReport(strReport);
        this.mensajeLogConsultar(strFecha_desde,strFecha_hasta);
        this.clickConsultar();
    }


    public void validarError(String errorMensage){
    this.mensajeError(errorMensage);
    }


    public void consultarPorGrupo(String strFecha_desde,
                                  String strFecha_hasta,
                                  String opcionGrupo) {

        this.pasarFramePrincipal();
        //this.setFecha_desde(strFecha_desde);
        //this.setFecha_hasta(strFecha_hasta);
        this.seleccionar_grupo(opcionGrupo);
        this.mensajeLogConsultar(strFecha_desde,strFecha_hasta);
        this.clickConsultar();
    }


    public void consultarPorEGM(String strFecha_desde,
                                  String strFecha_hasta,
                                  String opcionEGM) {

        this.pasarFramePrincipal();
        //this.setFecha_desde(strFecha_desde);
        this.seleccionar_egm(opcionEGM);
        this.mensajeLogConsultar(strFecha_desde,strFecha_hasta);
        this.clickConsultar();
    }
}
