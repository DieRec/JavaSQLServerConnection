package group;

import java.sql.*;

public class notes {

    Connection conn = null;
    /*
            try {
            // Paso 1: Registrar el controlador JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Paso 2: Establecer la conexión con la base de datos
            // Especifica la URL de conexión, nombre de usuario, y contraseña

            //String url = "jdbc:sqlserver://serverName\\instanceName:portNumber;databaseName=yourDatabase";

            String url = "jdbc:sqlserver://NINA\\SQLEXPRESS:1433;databaseName=databaseLaboop;integratedSecurity=true;encrypt=false;";

            //String url = "jdbc:sqlserver://serverName\\instanceName:portNumber;databaseName=yourDatabase;integratedSecurity=true;encrypt=false;";
            //String url = "jdbc:sqlserver://Nina\\SQLEXPRESS\\instanceName:portNumber;databaseName=databaseLaboop";

            String username = "orion";
            String password = "orion";
            String database = "databaseLaboop";
            String ip = "localhost";
            String portNumber = "1433";

            String url2 = "jdbc:sqlserver://"+ip+":"+portNumber+"/"+database;

            // Crea la conexión
            Connection conn = DriverManager.getConnection(url, username, password);

            // Paso 3: Crear un objeto Statement para enviar consultas SQL a la base de datos
            Statement stmt = conn.createStatement();

            // Paso 4: Ejecutar una consulta SQL y procesar el objeto ResultSet devuelto
            ResultSet rs = stmt.executeQuery("SELECT * FROM mytable");

            while (rs.next()) {
                System.out.println(rs.getString("columnname"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Paso 5: Cerrar la conexión
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    */


}
