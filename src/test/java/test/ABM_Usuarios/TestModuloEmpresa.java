package test.ABM_Usuarios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.EmpresasPage;
import pages.LeftMenu;
import pages.Login;
import test.creationals.entities.Empresas;
import utils.Constantes;
import utils.Excel;
import utils.Log;

public class TestModuloEmpresa {

    WebDriver driver;
    Login login;
    LeftMenu leftMenu;
    EmpresasPage empresas;
    Log log;
   // ExcelReader excelReader;

    //Datos para Menu y submenu
    String menu = "On-Line";
    String submenu = "Usuarios";
    String modulo = "Empresas";

    @BeforeTest
    public void setup_y_ruta()throws Exception{

        System.setProperty(Constantes.DriverWeb, Constantes.Path_DriverWeb);
        driver = new FirefoxDriver();
        login = new Login(driver);
        leftMenu = new LeftMenu(driver);
        empresas = new EmpresasPage(driver);
        log = new Log();

        //ingreso a la maqueta
        login.openPage(Constantes.URL);
        //login en la maqueta
        login.loginTo(Constantes.Username,Constantes.Password);
        //ingresar al menu y submenu
        leftMenu.ingresarPath(menu,submenu,modulo);
    }

    @Test(priority=1)
    public void test_crear_empresa() throws Exception {

        //Loguear inicio de pruebas
        String testName = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+testName);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+testName+" en "+Constantes.File_TestData);
        Empresas empresa = (Empresas) Excel.excelReader.getExcelTable().get(testName);

        //modulo crear empresa
        empresas.crearEmpresa(empresa.getEmpresa(), empresa.getDireccion(), testName);
        //validacion
        empresas.validaEmpresa(empresa.getEmpresa());

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+testName+" 'Passed' en "+Constantes.File_TestData);
    }

    //este test depende que la empresa almacenada en la variable este creada en el modulo
    @Test(priority=2)
    public void test_editar_empresa() throws Exception {

        //Loguear inicio de pruebas
        String testName = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+testName);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+testName+" en "+Constantes.File_TestData);
        Empresas empresa = (Empresas) Excel.excelReader.getExcelTable().get(testName);

        //modulo editar empresa
        empresas.modifEmpresa(empresa.getEmpresaAEditar(),empresa.getEmpresa(),empresa.getDireccion(),testName);
        //validacion
        empresas.validaEmpresa(empresa.getEmpresa());

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+testName+" 'Passed' en "+Constantes.File_TestData);
    }

    //este test depende que la empresa almacenada en la variable este creada en el modulo
    @Test(priority=3)
    public void test_borrar_empresa() throws Exception {

        //Loguear inicio de pruebas
        String testName = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+testName);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+testName+" en "+Constantes.File_TestData);
        Empresas empresa = (Empresas) Excel.excelReader.getExcelTable().get(testName);

        //modulo borrar empresa
        empresas.borrarEmpresa(empresa.getEmpresa(),testName);

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+testName+" 'Passed' en "+Constantes.File_TestData);
    }

    @AfterTest
    public void cerrar_ventana(){
        login.closePage();
    }
}
