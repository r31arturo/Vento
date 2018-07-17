package test.Administrativos_dia_cerrado;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.GananciaAlDiaPorEGM;
import pages.LeftMenu;
import pages.Login;
import utils.*;

import java.util.concurrent.TimeUnit;

public class TestModuloGananciaAlDiaPorEGM {

    WebDriver driver;
    CambiarFrame objCambiarFrame;
    Login objLogin;
    LeftMenu objLeftMenu;
    GananciaAlDiaPorEGM objGananciaDiaEGM;
    Waits objWaits;
    ConfigDB objConectarBD;
    ImprPant objImprPant;
    Log objLog;

    //Datos para Menu y submenu
    String menu = "On-Line";
    String submenu = "Administrativos a día cerrado";
    String modulo = "Ganancia al día por EGM";

    @BeforeTest
    public void setup_y_ruta()throws Exception{

        Excel.setExcelFile(Constantes.Path_TestData + Constantes.File_TestData, "reportes");
        System.setProperty(Constantes.DriverWeb, Constantes.Path_DriverWeb);

        driver = new FirefoxDriver();
        objCambiarFrame = new CambiarFrame(driver);
        objLogin = new Login(driver);
        objLeftMenu = new LeftMenu(driver);
        objGananciaDiaEGM = new GananciaAlDiaPorEGM(driver);
        //objWaits = new Waits();
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
        objCambiarFrame.frameLogin();
        Log.doLogging("Ingresando a Vento con el usuario: "+Constantes.Username);
        objLogin.loginTo(Constantes.Username,Constantes.Password);

        //menu y submenu
        objCambiarFrame.frameMenu();
        Log.doLogging("Ingresando al Path: "+menu+"/"+submenu+"/"+modulo);
        objLeftMenu.introMenu(menu);
        objLeftMenu.introSubMenu(submenu);
        objLeftMenu.introModulo(modulo);

    }

    @Test(priority=1)
    public void test_consulta_reporte_fecha_aceptada() throws Exception {

        Integer secuencia = 1; //es lo mismo que proridad de ejecucion del test

        //Loguear inicio de pruebas
        String Metodo = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+Metodo);
        Excel.setCellData(Metodo, secuencia, 0);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+Metodo+" en "+Constantes.File_TestData);
        String fecha_desde = Excel.getCellData(secuencia, 1);
        String fecha_hasta = Excel.getCellData(secuencia, 2);

        //modulo consultar ganancia al dia por EGM
        objCambiarFrame.framePrincipal();
        objGananciaDiaEGM.consulta(fecha_desde,fecha_hasta);

        //validacion
        //objWaits.waitSecs(10);
        objImprPant.TomarPrint(Metodo,driver);
        //objGananciaDiaEGM.validareporte(); colocar una validacion que compruebe que fue exitosa

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+Metodo+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", secuencia, 6);

        //volver al formulario despues de consultar un reporte
        //objWaits.waitSecs(10);
        objGananciaDiaEGM.volverFormulario();

    }

    @Test(priority=2)
    public void test_consulta_reporte_filtrando_por_EGM() throws Exception {

        Integer secuencia = 2; //es lo mismo que proridad de ejecucion del test

        //Loguear inicio de pruebas
        String Metodo = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+Metodo);
        Excel.setCellData(Metodo, secuencia, 0);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+Metodo+" en "+Constantes.File_TestData);
        String fecha_desde = Excel.getCellData(secuencia, 1);
        String fecha_hasta = Excel.getCellData(secuencia, 2);
        String egm = Excel.getCellData(secuencia,3);

        //modulo consultar ganancia al dia por EGM
        objCambiarFrame.framePrincipal();
        objGananciaDiaEGM.consultarPorEGM(fecha_desde,fecha_hasta,egm);

        //validacion
        //objWaits.waitSecs(10);
        objImprPant.TomarPrint(Metodo,driver);
        //objGananciaDiaEGM.validareporte(); colocar una validacion que compruebe que fue exitosa
        //objImprPant.TomarPrintError(Metodo,driver);  // No funciona bien

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+Metodo+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", secuencia, 6);

        //volver al formulario despues de consultar un reporte
        //objWaits.waitSecs(10);
        objGananciaDiaEGM.volverFormulario();

    }

    @Test(priority=3)
    public void test_consulta_reporte_filtrando_por_un_grupo() throws Exception {

        Integer secuencia = 3; //es lo mismo que proridad de ejecucion del test

        //Loguear inicio de pruebas
        String Metodo = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+Metodo);
        Excel.setCellData(Metodo, secuencia, 0);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+Metodo+" en "+Constantes.File_TestData);
        String fecha_desde = Excel.getCellData(secuencia, 1);
        String fecha_hasta = Excel.getCellData(secuencia, 2);
        String grupo = Excel.getCellData(secuencia,4);

        //modulo consultar ganancia al dia por EGM
        objCambiarFrame.framePrincipal();
        objGananciaDiaEGM.consultarPorGrupo(fecha_desde,fecha_hasta,grupo);

        //validacion
        //objWaits.waitSecs(10);
        objImprPant.TomarPrint(Metodo,driver);
        //objGananciaDiaEGM.validareporte(); colocar una validacion que compruebe que fue exitosa
        //objImprPant.TomarPrintError(Metodo,driver);  // No funciona bien

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+Metodo+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", secuencia, 6);

        //volver al formulario despues de consultar un reporte
        //objWaits.waitSecs(10);
        objGananciaDiaEGM.volverFormulario();

    }

    @Test(priority=4)
    public void test_consulta_reporte_fecha_desde_rechazada() throws Exception {

        Integer secuencia = 4; //es lo mismo que proridad de ejecucion del test

        //Loguear inicio de pruebas
        String Metodo = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+Metodo);
        Excel.setCellData(Metodo, secuencia, 0);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+Metodo+" en "+Constantes.File_TestData);
        String fecha_desde = Excel.getCellData(secuencia, 1);
        String fecha_hasta = Excel.getCellData(secuencia, 2);
        String msgerror = Excel.getCellData(secuencia,5);

        //modulo crear empresa
        objCambiarFrame.framePrincipal();
        objGananciaDiaEGM.consulta(fecha_desde,fecha_hasta);

        //validacion
        //objWaits.waitSecs(10);
        objImprPant.TomarPrint(Metodo,driver);
        objGananciaDiaEGM.validarError(msgerror);
        //objImprPant.TomarPrintError(Metodo,driver);  // No funciona bien

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+Metodo+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", secuencia, 6);

    }

    @Test(priority=5)
    public void test_consulta_reporte_fecha_hasta_rechazada_letras() throws Exception {

        Integer secuencia = 5; //secuencia del test es lo mismo que priority de ejecucion del @test

        //Loguear inicio de pruebas
        String Metodo = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+Metodo);
        Excel.setCellData(Metodo, secuencia, 0);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+Metodo+" en "+Constantes.File_TestData);
        String fecha_desde = Excel.getCellData(secuencia, 1);
        String fecha_hasta = Excel.getCellData(secuencia, 2);
        String msgerror = Excel.getCellData(secuencia,5);

        //modulo crear empresa
        objCambiarFrame.framePrincipal();
        objGananciaDiaEGM.consulta(fecha_desde,fecha_hasta);

        //validacion
        //objWaits.waitSecs(10);
        objImprPant.TomarPrint(Metodo,driver);
        objGananciaDiaEGM.validarError(msgerror);
        //objImprPant.TomarPrintError(Metodo,driver);  // No funciona bien

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+Metodo+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", secuencia, 6);

    }


    @AfterTest
    public void cerrar_ventana(){

        Log.doLogging("Cerrando Ventana");
        objCambiarFrame.frameClose();
    }

}