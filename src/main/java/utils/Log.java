package utils;

import java.util.Date;
import java.util.logging.*;

public class Log {

    private static final Logger LOGGER = Logger.getLogger(Log.class.getName());

    private static Date dat = new Date();

    public static void crearLog(String Path_Log) {

        try {
            LOGGER.setUseParentHandlers(false);
            Handler fileHandler = new FileHandler(Path_Log + "Test.log", true);

            fileHandler.setFormatter(new Formatter() {

                public String format(LogRecord record) {

                    dat.setTime(record.getMillis());
                    return
                            dat + " - "
                            + record.getLevel() + " : "
                           // + record.getSourceClassName() + ":"
                           // + record.getSourceMethodName() +  " -:- "
                            + record.getMessage() + "\n";

                }
            });
            LOGGER.addHandler(fileHandler);
            LOGGER.info("LOG INICIALIZADO: Test.log");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doLogging(String Desc) {
        LOGGER.info(Desc);
    }

    /*
    private final static Logger LOGGER = Logger.getLogger("log");

    public static void crearLog(String Path_Log) {
        try {
            Handler fileHandler = new FileHandler(Path_Log + "Test.log", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);
            //LOGGER.log(Level.INFO, "LOG INICIALIZADO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

}
