package test.ABM_Usuarios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Empresas;
import pages.LeftMenu;
import pages.Login;
import utils.Constantes;
import utils.Excel;
import utils.Log;

public class TestModuloEmpresa {

    WebDriver driver;
    Login login;
    LeftMenu leftMenu;
    Empresas empresas;
    Log log;

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
        empresas = new Empresas(driver);
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
        String empresa = Excel.getCellData(1, 1);
        String direccion = Excel.getCellData(1, 2);

        //modulo crear empresa
        empresas.crearEmpresa(empresa, direccion, testName);
        //validacion
        empresas.validaEmpresa(empresa);

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+testName+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", 1, 4);

    }

    //este test depende que la empresa almacenada en la variable este creada en el modulo
    @Test(priority=2)
    public void test_editar_empresa() throws Exception {

        //Loguear inicio de pruebas
        String testName = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+testName);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+testName+" en "+Constantes.File_TestData);
        String empresa_edit = Excel.getCellData(2, 1);
        String direccion_edit = Excel.getCellData(2, 2);
        String empresa = Excel.getCellData(2, 3);

        //modulo editar empresa
        empresas.modifEmpresa(empresa,empresa_edit,direccion_edit,testName);
        //validacion
        empresas.validaEmpresa(empresa_edit);

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+testName+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", 2, 4);
    }

    //este test depende que la empresa almacenada en la variable este creada en el modulo
    @Test(priority=3)
    public void test_borrar_empresa() throws Exception {

        //Loguear inicio de pruebas
        String testName = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+testName);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+testName+" en "+Constantes.File_TestData);
        String empresa = Excel.getCellData(3, 1);

        //modulo borrar empresa
        empresas.borrarEmpresa(empresa,testName);

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+testName+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", 3, 4);
    }

    @AfterTest
    public void cerrar_ventana(){
        login.closePage();
    }
}
