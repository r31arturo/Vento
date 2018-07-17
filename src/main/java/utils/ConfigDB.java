package utils;

import java.sql.*;

public class ConfigDB {

    public Connection con = null;
    public Statement st = null;
    public ResultSet rs = null;



    public void conectarBD() {

        try {
            //Conectar
            Class.forName("org.postgresql.Driver");
            Log.doLogging("Conectando con la Base de Datos URL = " +Constantes.URLDB);
            con = DriverManager.getConnection("jdbc:postgresql://" + Constantes.URLDB + "/db_sol", "postgres", "qwerty123");
            st = con.createStatement();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void CerrarDB() {

        // Code to close each and all Object related to Database connection
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {

            }

            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }
        Log.doLogging("Desconectando con la Base de Datos URL = " +Constantes.URLDB);


    }

}
