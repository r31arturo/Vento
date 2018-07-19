package logica;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpresaLogic {

    WebDriver driver;
    CambiarFrame cambiarFrame;
    ConfigDB ConectBD;
    ResultSet rs = null;
    Integer find_error = 0;
    By empresa = By.id("OV_emdesc");
    By domicilio = By.id("OV_emdirec");
    By agregar = By.cssSelector("tr.tablaABM > td > input.InputsTablaABM[type='button']");
    By tabla = By.cssSelector(".ListadoABM");
    By linea = By.cssSelector("tr");
    By cargados = By.cssSelector("td");
    By mensajesABM = By.cssSelector("p.mensajesABM");

    public EmpresaLogic(WebDriver driver){ this.driver = driver; }

    protected void setEmpresa(String strEmpresa){
        if (find_error.equals(0)){
            Waits.waitTo(driver,empresa);
            driver.findElement(empresa).clear();
            driver.findElement(empresa).sendKeys(strEmpresa);
        } else{
            Log.doLogging("Empresa no Existente para modificar");
        }
    }

    protected void setDomicilio(String strDomicilio){
        Waits.waitTo(driver,domicilio);
        driver.findElement(domicilio).clear();
        driver.findElement(domicilio).sendKeys(strDomicilio);
    }

    protected void clickAgregar(){
        Waits.waitTo(driver,agregar);
        driver.findElement(agregar).click();
    }

    protected void aceptarAlerta(){
        driver.switchTo().alert().accept();
    }

    protected void capturaPantalla(String testName)throws IOException {
        Waits.waitTo(driver, mensajesABM);
        ImprPant.TomarPrint(testName, driver);
    }

    protected void mensajeLogCrear(String strEmpresa, String strDomicilio){
        Log.doLogging("Registro Creado: "+strEmpresa+" - "+strDomicilio);
    }

    protected void mensajeLogEditar(String strEmpresa, String strEmpresa_edit){
        Log.doLogging("Registro Editado: "+strEmpresa+" / por: "+strEmpresa_edit);
    }

    protected void mensajeLogBorrar(String strEmpresa){
        Log.doLogging("Registro Borrado: "+strEmpresa);
    }


    protected void clickModEmpresa(String strEmpresa){
        Integer finalizar = 0;
        Waits.waitTo(driver,tabla);
        WebElement tabla_mod = driver.findElement(tabla);
        List<WebElement> linea_mod = tabla_mod.findElements(linea);
        for(WebElement line_mod : linea_mod){
            List<WebElement> elem_mod = line_mod.findElements(cargados);
            for(WebElement row_mod : elem_mod){
                String buscar_mod = row_mod.getText();
                if ( buscar_mod.equals(strEmpresa)){
                    line_mod.findElement(By.linkText("Modificar")).click();
                    finalizar = 1;
                    break;
                }
            }
            if (finalizar.equals(1)){
                break;
            }
        }
        if (finalizar.equals(0)){
            find_error=1;
            Log.doLogging("No fue encontrado el registro: "+strEmpresa);
        }
    }

    protected void clickElimEmpresa(String strEmpresa){
        Integer finalizar = 0;
        Waits.waitTo(driver,tabla);
        WebElement tabla_bor = driver.findElement(tabla);
        List<WebElement> linea_bor = tabla_bor.findElements(linea);
        for(WebElement line_bor : linea_bor){
            List<WebElement> elem_bor = line_bor.findElements(cargados);
            for(WebElement row_bor : elem_bor){
                String buscar_bor = row_bor.getText();
                if ( buscar_bor.equals(strEmpresa)){
                    line_bor.findElement(By.linkText("Borrar")).click();
                    finalizar = 1;
                    break;
                }
            }
            if (finalizar.equals(1)){
                break;
            }
        }
        if (finalizar.equals(0)){
            find_error=1;
            Log.doLogging("No fue encontrado el registro: "+strEmpresa);
        }

    }

    protected void validacionEmpresa(String strEmpresa) throws SQLException {

        //Validacion en el Vento
        String encontrado = "";
        Waits.waitTo(driver,tabla);
        WebElement elem = driver.findElement(tabla);
        List<WebElement> coleccion = elem.findElements(cargados);
        for(WebElement elem_row : coleccion){
            String buscar = elem_row.getText();
            if ( buscar.equals(strEmpresa)){
                encontrado = buscar;
                Log.doLogging("Registro encontrado en Vento: "+strEmpresa);
                break;
            }
        }
        Assert.assertTrue(encontrado.equals(strEmpresa));

        //Validacion en la Base de Datos
        ConectBD = new ConfigDB();
        ConectBD.conectarBD();
        rs = ConectBD.st.executeQuery("select * from public.empresas where emdesc = '"+strEmpresa+"'");
        String conseguido = "";
        while(rs.next()){
            String buscando = rs.getString(2);
            if (buscando.equals(strEmpresa)){
                conseguido = buscando;
                Log.doLogging("Registro encontrado en Base de Datos: "+strEmpresa);
                break;
            }
        }
        Assert.assertTrue(conseguido.equals(strEmpresa));
        ConectBD.CerrarDB();
    }

    protected void pasarFramePrincipal(){
        cambiarFrame = new CambiarFrame(driver);
        cambiarFrame.framePrincipal();
    }
}
