package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.ConfigDB;
import utils.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Usuarios {

    WebDriver driver;

    ConfigDB ConectBD;

    ResultSet rs = null;
    Integer find_error = 0;


    By usuario = By.id("OV_uslogin");
    By pass = By.id("NV_usp");
    By nombre = By.id("OV_usnombre");
    By apellido = By.id("OV_usapellido");
    By legajo = By.id("NI_legajo");
    By telefono = By.id("NV_ustelefono");
    By domicilio = By.id("NV_usdomicilio");
    By tarjeta = By.id("NV_usnrotarjeta");
    By tipo_usuario = By.id("NI_ustipo");
    By ttr = By.id("NI_ttr_id");
    By salaid = By.id("filtersala");
    By perfil = By.cssSelector("form#formulario > table > tbody > tr.tablaABM > td > div#div_modelo > select.InputsTablaABM");
    By idioma = By.id("NI_id_language");
    By agregar = By.cssSelector("tr.tablaABM > td > input.InputsTablaABM[type='button']");
    By tabla = By.cssSelector(".ListadoABM");
    By linea = By.cssSelector("tr");
    By cargados = By.cssSelector("td");
    By tabla_per_asigna = By.cssSelector("table.InputsTablaABM");
    By linea_per_asigna = By.cssSelector("tr.encabezadoListadoABM > td.InputsTablaABM");
    By acept_permisos = By.id("submit");

    public Usuarios(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsuario(String strUsuario) {

        if (find_error.equals(0)) {
            driver.findElement(usuario).clear();
            driver.findElement(usuario).sendKeys(strUsuario);
        } else {
            Log.doLogging("Usuario no Existente para modificar");
        }
    }

    public void setPass(String strPass){
        driver.findElement(pass).clear();
        driver.findElement(pass).sendKeys(strPass);
    }

    public void setNombre(String strNombre){
        driver.findElement(nombre).clear();
        driver.findElement(nombre).sendKeys(strNombre);
    }

    public void setApellido(String strApellido){
        driver.findElement(apellido).clear();
        driver.findElement(apellido).sendKeys(strApellido);
    }

    public void setLegajo(String strLegajo){
        driver.findElement(legajo).clear();
        driver.findElement(legajo).sendKeys(strLegajo);
    }

    public void setTelefono(String strTelefono){
        driver.findElement(telefono).clear();
        driver.findElement(telefono).sendKeys(strTelefono);
    }

    public void setDomicilio(String strDomicilio){
        driver.findElement(domicilio).clear();
        driver.findElement(domicilio).sendKeys(strDomicilio);
    }

    public void setTarjeta(String strTarjeta){
        driver.findElement(tarjeta).clear();
        driver.findElement(tarjeta).sendKeys(strTarjeta);
    }

    public void setTipo_usuario(String strTipo){
        Select tipo = new Select (driver.findElement(tipo_usuario));
        tipo.selectByVisibleText(strTipo);
    }

    public void setTTR(String strTTR){
        Select perfil_ttr = new Select (driver.findElement(ttr));
        perfil_ttr.selectByVisibleText(strTTR);
    }

    public void setSala(String strSala){
        Select sala = new Select (driver.findElement(salaid));
        sala.selectByVisibleText(strSala);
    }

    public void setPerfil(String strPerfil){
        Select perfil_usuario = new Select (driver.findElement(perfil));
        perfil_usuario.selectByVisibleText(strPerfil);
    }

    public void setIdioma(String strIdioma){

        Select combo_idioma = new Select (driver.findElement(idioma));
        combo_idioma.selectByVisibleText(strIdioma);
    }

    public void aceptarAlerta(){
        driver.switchTo().alert().accept();
    }

    public void clickAgregar(){

        driver.findElement(agregar).click();
    }

    public void mensajeLogCrear(String strUsuario, String strNombre, String strApellido){
        Log.doLogging("Registro creado: "+strUsuario+" - "+strNombre+" - "+strApellido);
    }

    public void mensajeLogEditar(String strUsuario, String strUsuario_edit){
        Log.doLogging("Registro editado: "+strUsuario+" / por: "+strUsuario_edit);
    }

    public void mensajeLogBorrar(String strUsuario){
        Log.doLogging("Registro borrado: "+strUsuario);
    }

    public void crearUsuario(String strUsuario,
                             String strPass,
                             String strNombre,
                             String strApellido,
                             String strLegajo,
                             String strTelefono,
                             String strDomicilio,
                             String strTarjeta,
                             String strTipo,
                             String strTTR,
                             String strSala,
                             String strPerfil,
                             String strIdioma){
        this.setUsuario(strUsuario);
        this.setPass(strPass);
        this.setNombre(strNombre);
        this.setApellido(strApellido);
        this.setLegajo(strLegajo);
        this.setTelefono(strTelefono);
        this.setDomicilio(strDomicilio);
        this.setTarjeta(strTarjeta);
        this.setTipo_usuario(strTipo);
        this.setTTR(strTTR);
        this.setSala(strSala);
        this.setPerfil(strPerfil);
        this.setIdioma(strIdioma);
        this.clickAgregar();
        this.mensajeLogCrear(strUsuario,strNombre,strApellido);
    }

    public void clickModUsuario(String strUsuario){
        Integer finalizar = 0;
        WebElement tabla_mod = driver.findElement(tabla);
        List<WebElement> linea_mod = tabla_mod.findElements(linea);
        for(WebElement line_mod : linea_mod){
            List<WebElement> elem_mod = line_mod.findElements(cargados);
            for(WebElement row_mod : elem_mod){
                String buscar_mod = row_mod.getText();
                if ( buscar_mod.equals(strUsuario)){
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
            Log.doLogging("No fue encontrado el registro: "+strUsuario);
        }
    }

    public void modifUsuario(String strUsuario,
                             String strUsuario_edit,
                             String strPass_edit,
                             String strNombre_edit,
                             String strApellido_edit,
                             String strLegajo_edit,
                             String strTelefono_edit,
                             String strDomicilio_edit,
                             String strTarjeta_edit,
                             String strTipo_edit,
                             String strTTR_edit,
                             String strSala_edit,
                             String strPerfil_edit,
                             String strIdioma_edit){
        this.clickModUsuario(strUsuario);
        this.setUsuario(strUsuario_edit);
        this.setPass(strPass_edit);
        this.setNombre(strNombre_edit);
        this.setApellido(strApellido_edit);
        this.setLegajo(strLegajo_edit);
        this.setTelefono(strTelefono_edit);
        this.setDomicilio(strDomicilio_edit);
        this.setTarjeta(strTarjeta_edit);
        this.setTipo_usuario(strTipo_edit);
        this.setTTR(strTTR_edit);
        this.setSala(strSala_edit);
        this.setPerfil(strPerfil_edit);
        this.setIdioma(strIdioma_edit);
        this.clickAgregar();
        this.mensajeLogEditar(strUsuario,strUsuario_edit);
    }


    public void clickElimUsuario(String strUsuario){
        Integer finalizar = 0;
        WebElement tabla_bor = driver.findElement(tabla);
        List<WebElement> linea_bor = tabla_bor.findElements(linea);
        for(WebElement line_bor : linea_bor){
            List<WebElement> elem_bor = line_bor.findElements(cargados);
            for(WebElement row_bor : elem_bor){
                String buscar_bor = row_bor.getText();
                if ( buscar_bor.equals(strUsuario)){
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
            Log.doLogging("No fue encontrado el registro: "+strUsuario);
        }
    }

    public void borrarUsuario(String strUsuario){
        this.clickElimUsuario(strUsuario);
        this.aceptarAlerta();
        this.mensajeLogBorrar(strUsuario);
    }


    public void permisosAsigna(String strUsuario){
        Integer finalizar = 0;
        WebElement tabla_bus = driver.findElement(tabla);
        List<WebElement> linea_bus = tabla_bus.findElements(linea);
        for(WebElement line_bus : linea_bus){
            List<WebElement> elem_bus = line_bus.findElements(cargados);
            for(WebElement row_bus : elem_bus){
                String buscar_bus = row_bus.getText();
                if ( buscar_bus.equals(strUsuario)){
                    line_bus.findElement(By.linkText("Permisos de asignación")).click();
                    finalizar = 1;
                    Log.doLogging("Asignando permisos de asignacion al usuario: "+strUsuario);
                    break;
                }
            }
            if (finalizar.equals(1)){
                break;
            }
        }
    }

    public void buscarPermisos(){

        //String cate_permiso = "Backup";  //Habilitar esto en caso que se necesite un permiso en espesifico
        WebElement tabla_per = driver.findElement(tabla_per_asigna);
        List<WebElement> linea_per = tabla_per.findElements(linea_per_asigna);
        for(WebElement line_per : linea_per) {
            WebElement busca_str = line_per.findElement(By.cssSelector("b"));
            String buscar_per = busca_str.getText();
            Log.doLogging("Se le asigno permisos de "+buscar_per);
            //if (buscar_per.equals(cate_permiso)) {        //Habilitar esto en caso que se necesite un permiso en espesifico
                line_per.findElement(By.cssSelector("input[type='checkbox']")).click();
                //break;    //Habilitar esto en caso que se necesite un permiso en espesifico
            //}             //Habilitar esto en caso que se necesite un permiso en espesifico

        }

    }

    public void aceptarPermisos(){

        driver.findElement(acept_permisos).click();
        WebElement close_perm = driver.findElement(tabla_per_asigna);
        close_perm.findElement(By.cssSelector("tbody > tr > td > a[href='javascript:window.close();']")).click();

    }

    public void otorgarPermisos(){

        this.buscarPermisos();
        this.aceptarPermisos();
    }

    public void validaUsuario(String strUsuario) throws SQLException {
        //Validacion en el Vento
        String encontrado = "";
        WebElement elem = driver.findElement(tabla);
        List<WebElement> coleccion = elem.findElements(cargados);
        for(WebElement elem_row : coleccion){
            String buscar = elem_row.getText();
            if ( buscar.equals(strUsuario)){
                encontrado = buscar;
                Log.doLogging("Registro encontrado en Vento: "+strUsuario);
                break;
            }
        }
        Assert.assertTrue(encontrado.equals(strUsuario));

        //Validar en Base de Datos
        ConectBD = new ConfigDB();
        ConectBD.conectarBD();
        rs = ConectBD.st.executeQuery("select * from public.usuarios where uslogin = '"+strUsuario+"'");
        String conseguido = "";
        while(rs.next()){
            String buscando = rs.getString(2);
            if (buscando.equals(strUsuario)){
                conseguido = buscando;
                Log.doLogging("Registro encontrado en Base de Datos: "+strUsuario);
                break;
            }
        }

        Assert.assertTrue(conseguido.equals(strUsuario));
        ConectBD.CerrarDB();

    }

}
