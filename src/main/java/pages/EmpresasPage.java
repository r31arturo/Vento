package pages;

import org.openqa.selenium.WebDriver;
import logica.EmpresaLogic;
import java.io.IOException;
import java.sql.SQLException;

public class EmpresasPage extends EmpresaLogic {

    public EmpresasPage(WebDriver driver){ super(driver); }

    public void crearEmpresa(String strEmpresa, String strDomicilio, String testName)throws IOException{

        this.pasarFramePrincipal();
        this.setEmpresa(strEmpresa);
        this.setDomicilio(strDomicilio);
        this.clickAgregar();
        this.aceptarAlerta();
        this.mensajeLogCrear(strEmpresa,strDomicilio);
        this.capturaPantalla(testName);
    }

    public void modifEmpresa(String strEmpresa, String strEmpresa_edit, String strDomicilio_edit, String testName)throws IOException{
        this.pasarFramePrincipal();
        this.clickModEmpresa(strEmpresa);
        this.setEmpresa(strEmpresa_edit);
        this.setDomicilio(strDomicilio_edit);
        this.clickAgregar();
        this.aceptarAlerta();
        this.mensajeLogEditar(strEmpresa,strEmpresa_edit);
        this.capturaPantalla(testName);
    }

    public void borrarEmpresa(String strEmpresa, String testName)throws IOException{
        this.pasarFramePrincipal();
        this.clickElimEmpresa(strEmpresa);
        this.aceptarAlerta();
        this.mensajeLogBorrar(strEmpresa);
        this.capturaPantalla(testName);
    }

    public void validaEmpresa(String strEmpresa) throws SQLException {
        this.validacionEmpresa(strEmpresa);
    }

}
