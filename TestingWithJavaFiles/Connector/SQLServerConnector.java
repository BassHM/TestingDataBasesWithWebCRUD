//Misma historia, al tenerlo en el proyecto final, hay que actualizar la declaración de paquetes.
package TestingWithJavaFiles.Connector;

import java.sql.*;

public class SQLServerConnector {
    private static final String URL = "jdbc:sqlserver://DESKTOP-U1NQ44F\\TESTDB.database.widows.net:1433;"
            + "database = L_III_Gestion_Alumnos;" // nombre de la database
            + "user = sa;" // nombre del usuario
            + "password = @ThisIsThePassword1;" // contrase;a
            + "encrypt = true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=30;";

    public static Connection getConnection() throws SQLException {

        try (Connection con = DriverManager.getConnection(URL)) {
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return DriverManager.getConnection(URL);
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(PreparedStatement stm) throws SQLException {
        stm.close();
    }

    public static void close(Connection con) throws SQLException {
        con.close();
    }
}
