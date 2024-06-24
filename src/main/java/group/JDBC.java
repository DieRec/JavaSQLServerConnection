package group;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    public class SQLServerConnection {

        // Cambia estos valores según tu configuración
        private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=TuBaseDeDatos";
        private static final String USER = "tuUsuario";
        private static final String PASSWORD = "tuContraseña";

        public static void main(String[] args) {
            Connection connection = null;

            try {
                // Registrar el controlador JDBC
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Establecer la conexión
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                if (connection != null) {
                    System.out.println("Conexión exitosa a la base de datos.");
                }

            } catch (ClassNotFoundException e) {
                System.err.println("Error al registrar el controlador JDBC: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Error al establecer la conexión: " + e.getMessage());
            } finally {
                // Cerrar la conexión
                if (connection != null) {
                    try {
                        connection.close();
                        System.out.println("Conexión cerrada.");
                    } catch (SQLException e) {
                        System.err.println("Error al cerrar la conexión: " + e.getMessage());
                    }
                }
            }
        }
    }

}
