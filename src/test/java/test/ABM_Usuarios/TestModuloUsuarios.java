package test.ABM_Usuarios;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LeftMenu;
import pages.Login;
import pages.Usuarios;
import utils.*;

import java.util.concurrent.TimeUnit;

public class TestModuloUsuarios {

    WebDriver driver;
    CambiarFrame objCambiarFrame;
    Login objLogin;
    LeftMenu objLeftMenu;
    Usuarios objUsuarios;
    Waits objWaits;
    ImprPant objImprPant;
    Log objLog;

    //Datos para Menu y submenu
    String menu = "On-Line";
    String submenu = "Usuarios";
    String modulo = "Usuarios";

    @BeforeTest
    public void setup_y_ruta()throws Exception{

        Excel.setExcelFile(Constantes.Path_TestData + Constantes.File_TestData, "usuarios");
        System.setProperty(Constantes.DriverWeb, Constantes.Path_DriverWeb);

        driver = new FirefoxDriver();
        objCambiarFrame = new CambiarFrame(driver);
        objLogin = new Login(driver);
        objLeftMenu = new LeftMenu(driver);
        objUsuarios = new Usuarios(driver);
        //objWaits = new Waits();
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
        objLeftMenu.ingresarPath(menu,submenu,modulo);

    }

    @Test(priority=1)
    public void test_crear_usuario() throws Exception{

        //Loguear inicio de pruebas
        String Metodo = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+Metodo);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+Metodo+" en "+Constantes.File_TestData);
        String usuario = Excel.getCellData(1, 1);
        String contraseña = Excel.getCellData(1, 2);
        String nombre = Excel.getCellData(1, 3);
        String apellido = Excel.getCellData(1, 4);
        String legajo = Excel.getCellData(1, 5);
        String telefono = Excel.getCellData(1, 6);
        String domicilio = Excel.getCellData(1, 7);
        String tarjeta = Excel.getCellData(1, 8);
        String tipo_usuario = Excel.getCellData(1, 9);
        String ttr = Excel.getCellData(1, 10);
        String sala = Excel.getCellData(1, 11);
        String perfil = Excel.getCellData(1, 12);
        String idioma = Excel.getCellData(1, 13);

        //modulo crear usuario
        objCambiarFrame.framePrincipal();
        objUsuarios.crearUsuario(usuario,
                contraseña,
                nombre,
                apellido,
                legajo,
                telefono,
                domicilio,
                tarjeta,
                tipo_usuario,
                ttr,
                sala,
                perfil,
                idioma);

        //validacion
        //objWaits.waitSecs(10);
        objImprPant.TomarPrint(Metodo,driver);
        objUsuarios.validaUsuario(usuario);

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+Metodo+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", 1, 15);

    }

    //este test depende que el usuario almacenada en la variable este creada en el modulo
    @Test(priority=2)
    public void test_asignar_permisos_asignacion() throws Exception{

        //Loguear inicio de pruebas
        String Metodo = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+Metodo);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+Metodo+" en "+Constantes.File_TestData);
        String usuario = Excel.getCellData(2, 1);

        //modulo editar usuario
        objCambiarFrame.framePrincipal();
        objUsuarios.permisosAsigna(usuario);
        objCambiarFrame.cambiarPag();
        objUsuarios.otorgarPermisos();
        objCambiarFrame.volverPag();

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+Metodo+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", 2, 15);

    }



    //este test depende que la empresa almacenada en la variable este creada en el modulo
    @Test(priority=3)
    public void test_editar_usuario() throws Exception{

        //Loguear inicio de pruebas
        String Metodo = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+Metodo);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+Metodo+" en "+Constantes.File_TestData);
        String usuario_edit = Excel.getCellData(3, 1);
        String contraseña_edit = Excel.getCellData(3, 2);
        String nombre_edit = Excel.getCellData(3, 3);
        String apellido_edit = Excel.getCellData(3, 4);
        String legajo_edit = Excel.getCellData(3, 5);
        String telefono_edit = Excel.getCellData(3, 6);
        String domicilio_edit = Excel.getCellData(3, 7);
        String tarjeta_edit = Excel.getCellData(3, 8);
        String tipo_usuario_edit = Excel.getCellData(3, 9);
        String ttr_edit = Excel.getCellData(3, 10);
        String sala_edit = Excel.getCellData(3, 11);
        String perfil_edit = Excel.getCellData(3, 12);
        String idioma_edit = Excel.getCellData(3, 13);
        String usuario_a_editar = Excel.getCellData(3, 14);

        //modulo editar usuario
        objCambiarFrame.framePrincipal();
        objUsuarios.modifUsuario(usuario_a_editar,
                usuario_edit,
                contraseña_edit,
                nombre_edit,
                apellido_edit,
                legajo_edit,
                telefono_edit,
                domicilio_edit,
                tarjeta_edit,
                tipo_usuario_edit,
                ttr_edit,
                sala_edit,
                perfil_edit,
                idioma_edit);

        //validacion
        //objWaits.waitSecs(10);
        objImprPant.TomarPrint(Metodo,driver);
        objUsuarios.validaUsuario(usuario_edit);

        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+Metodo+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", 3, 15);

    }

    //este test depende que la empresa almacenada en la variable este creada en el modulo
    @Test(priority=4)
    public void test_borrar_usuario() throws Exception{

        //Loguear inicio de pruebas
        String Metodo = new String (Thread.currentThread().getStackTrace()[1].getMethodName());
        Log.doLogging("Inicializando "+Metodo);

        //Consultar datos para el test del excel
        Log.doLogging("Consultando datos para "+Metodo+" en "+Constantes.File_TestData);
        String usuario = Excel.getCellData(4, 1);

        //modulo borrar usuario
        objCambiarFrame.framePrincipal();
        objUsuarios.borrarUsuario(usuario);
        objImprPant.TomarPrint(Metodo,driver);


        //imprimir resultado del Test
        Log.doLogging("Imprimiendo resultado: "+Metodo+" 'Passed' en "+Constantes.File_TestData);
        Excel.setCellData("Passed", 4, 15);
    }

    @AfterTest
    public void cerrar_ventana(){

        Log.doLogging("Cerrando Ventana");
        objCambiarFrame.frameClose();
    }

}
