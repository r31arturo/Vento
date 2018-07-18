package test.ABM_Usuarios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Empresas;
import pages.LeftMenu;
import pages.Login;
import utils.*;

import java.util.concurrent.TimeUnit;

public class TestModuloEmpresa {

    WebDriver driver;
    Login objLogin;
    LeftMenu objLeftMenu;
    Empresas objEmpresas;
    Waits objWaits;
    ConfigDB objConectarBD;
    ImprPant objImprPant;
    Log objLog;

    //Datos para Menu y submenu
    String menu = "On-Line";
    String submenu = "Usuarios";
    String modulo = "Empresas";

    @BeforeTest
    public void setup_y_ruta()throws Exception{

        Excel.setExcelFile(Constantes.Path_TestData + Constantes.File_TestData, "empresa");
        System.setProperty(Constantes.DriverWeb, Constantes.Path_DriverWeb);

        driver = new FirefoxDriver();
        objLogin = new Login(driver);
        objLeftMenu = new LeftMenu(driver);
        objEmpresas = new Empresas(driver);
        objWaits = new Waits();
        objConectarBD = new ConfigDB();
        objImprPant = new ImprPant(driver);
        objLog = new Log();

        //crea el log
        objLog.crearLog(Constantes.Path_Log);

        //tiempo implicito de espera
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //ingreso a la maqueta
        Log.doLogging("Ingresando a la Maqueta: "+Constantes.URL);
        driver.get(Constantes.URL);

        //login
        Log.doLogging("Ingresando a Vento con el usuario: "+Constantes.Username);
        objLogin.loginTo(Constantes.Username,Constantes.Password);

        //menu y submenu
        Log.doLogging("Ingresando al Path: "+menu+"/"+submenu+"/"+modulo);
        objLeftMenu.ingresarPath(menu,submenu,modulo);
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
        objEmpresas.crearEmpresa(empresa, direccion, testName);

        //validacion
        objEmpresas.validaEmpresa(empresa);

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
        objEmpresas.modifEmpresa(empresa,empresa_edit,direccion_edit,testName);

        //validacion
        objEmpresas.validaEmpresa(empresa_edit);

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
        objEmpresas.borrarEmpresa(empresa,testName);

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+testName+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", 3, 4);
    }

    @AfterTest
    public void cerrar_ventana(){

        Log.doLogging("Cerrando Ventana");
        driver.close();
    }
}
