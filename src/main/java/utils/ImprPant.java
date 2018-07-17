package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;

;

public class ImprPant {

    WebDriver driver;

    public ImprPant(WebDriver driver) {

        this.driver = driver;
    }


    public void TomarPrint(String Test,WebDriver driver) throws IOException {

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Constantes.Path_Captura+Test+".png"));
        Log.doLogging("Captura de Pantalla: "+Test+".png");
    }

    public void TomarPrintError(String Test,WebDriver driver) throws IOException, AWTException {

        //BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        //ImageIO.write(image, "png", new File(Constantes.Path_Captura+Test+" - Error.png"));


        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Constantes.Path_Captura+Test+" - Error.png"));
        Log.doLogging("Captura de Pantalla: "+Test+" - Error.png");
    }
/*
    public static void main(String[] args) throws Exception {

        // set the geckodriver.exe property
        System.setProperty("webdriver.gecko.driver", "C:/~/geckodriver.exe");
        // open firefox
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        // open webpage
        driver.get("http://chercher.tech/java/practice-pop-ups-selenium-webdriver");
        // create object to robt class
        Robot robot = new Robot();
        // create rectangle for full screenshot
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        // capture screen of the desktop
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
        // save the screenshot to local system
        ImageIO.write(screenFullImage, "png", new File("D:\\FullScreenshotRobot.png"));

        System.out.println("Full Desktop screenshot saved!");
    }*/
}