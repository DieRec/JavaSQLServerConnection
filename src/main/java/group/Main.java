package group;

import java.sql.*;

//  Crea una
//  aplicación Java que utilice JDBC para conectarse a tu base de datos SQL Server.
//  Una vez establecida la conexión, tu aplicación debe cumplir con las siguientes tareas:

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {


        String username = "user";
        String password = "password";
        String database = "databaseLaboop";
        String ip = "localhost";
        String portNumber = "1433";


        /*URL actually worked*/
        //--> private static final String URL2 = "jdbc:sqlserver://localhost:1433;databaseName=databaseLaboop;integratedSecurity=true;encrypt=false";

        String url2 = "jdbc:sqlserver://"+ip+":"+portNumber+";"+"databaseName="+database+";integratedSecurity=true;encrypt=false";


        CrearTabla(url2, username, password, "estudiante");

        Insertar2(url2, username, password,"ricardo", 1, 20, "UCA");
        Insertar2(url2, username, password,"maria", 2, 18, "Oxford");
        Insertar2(url2, username, password,"nelson", 3, 19, "UES");
        Insertar2(url2, username, password,"luis", 4, 18, "UCA");
        Insertar2(url2, username, password,"jose", 5, 22, "Monterey");

        System.out.println();

        listarRegistros(url2, username, password);

        System.out.println();
        System.out.println();

        modificarUniversidad(url2, username, password, "luis", "Harvard" );
        System.out.println();
        System.out.println("\tUniversidad de estudiante modificado: " + "luis");
        System.out.println();
        listarRegistros(url2, username, password);

        System.out.println();

        eliminarEstudiante(url2, username, password , "maria");
        System.out.println();
        System.out.println("\testudiante eliminada del registro: " + "maria");
        System.out.println();
        listarRegistros(url2, username, password);
    }


    public static void Insertar2(String url, String username, String password, String nombre, int id, int edad, String universidad) {
            // La URL sigue el siguiente formato:
            // <driver>:<gestor de BD>://<servidor>:<puerto>/<nombre de la BD>
            Connection conn = null;
            PreparedStatement st = null;
            try {
                // Paso 1: Registrar el controlador JDBC
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                conn = DriverManager.getConnection(url, username, password);

                // Paso 2: Preparar la instrucción SQL
                String sql = "INSERT INTO estudiante (id, nombre, edad, universidad) VALUES (?, ?, ?, ?)";
                st = conn.prepareStatement(sql);
                st.setInt(1, id);
                st.setString(2, nombre);
                st.setInt(3, edad);
                st.setString(4, universidad);

                // Paso 3: Ejecutar la instrucción
                int results = st.executeUpdate();
                System.out.println(results + " fila(s) afectada(s)");
            } catch (SQLException e) {
                System.out.println("Error al insertar datos: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Fallo al conectar la Base de Datos");
                e.printStackTrace();
            } finally {
                // Cerrar recursos
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Error al cerrar recursos: " + e.getMessage());
                }
            }
    }


    public static void CrearTabla(String url, String username, String password, String tableName) {
        // La URL sigue el siguiente formato:
        // <driver>:<gestor de BD>://<servidor>:<puerto>/<nombre de la BD>

        String createTableSQL = "CREATE TABLE " + tableName + " ("
                + "id INT PRIMARY KEY, "
                + "nombre VARCHAR(100), "
                + "edad INT, "
                + "universidad VARCHAR(100)"
                + ");";

        try {
            // Paso 1: Registrar el controlador JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            stmt.execute(createTableSQL);
            System.out.println("Tabla " + tableName + " creada exitosamente");

            conn.close();
        } catch (Exception e) {
            System.out.println("Fallo al conectar la Base de Datos o crear la tabla");
            e.printStackTrace();
        }
    }

    private static void listarRegistros(String url, String username, String password)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn = DriverManager.getConnection(url, username, password);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM estudiante");

            System.out.println("Registros:");
            while(rs.next()){
                System.out.println("Nombre: "+ rs.getString("nombre")+" |id: "+rs.getInt("id") +" |edad: "+rs.getInt("edad") +" |universidad: "+rs.getString("universidad"));
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Fallo al conectar la Base de Datos");
        }
    }


    private static void modificarUniversidad(String url, String username, String password,String estudiante, String modificar) {
        // La URL sigue el siguiente formato:
        // <driver>:<gestor de BD>://<servidor>:<puerto>/<nombre de la BD>
        try {
            // Paso 1: Registrar el controlador JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement st = conn.prepareStatement("UPDATE estudiante SET universidad=? WHERE nombre=?");
            st.setString(1, modificar);
            st.setString(2, estudiante);

            try{
                int results = st.executeUpdate();
                System.out.println(results+" fila(s) afectada(s)");
            } catch (SQLException e){
                System.out.println("Error al actualizar");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Fallo al conectar la Base de Datos");
        }
    }

    private static void eliminarEstudiante(String url, String username, String password, String nombre) {
        // La URL sigue el siguiente formato:
        // <driver>:<gestor de BD>://<servidor>:<puerto>/<nombre de la BD>
        try {
            // Paso 1: Registrar el controlador JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement st = conn.prepareStatement("DELETE FROM estudiante WHERE nombre=?");
            st.setString(1, nombre);

            try{
                int results = st.executeUpdate();
                System.out.println(results+" fila(s) afectada(s)");
            } catch (SQLException e){
                System.out.println("Error al eliminar");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Fallo al conectar la Base de Datos");
        }
    }

}
