package jdbc.persistencia;

import java.sql.*;

public class DAO {

    protected Connection conexion = null; // Administra la conexión entre el programa Java y la base de datos
    protected Statement sentencia = null; // Instrucción de consulta
    protected ResultSet resultado = null; // Maneja los resultados

    private final String USUARIO = "root";
    private final String CLAVE = "root";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/egg_ejemplo?useSSL=false";

    public void conectarBase() throws Exception {
        try {
            Class.forName(DRIVER); // Carga el controlador o driver
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE); // Establece la conexión
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Error al conectarse");
        }
    }

    public void desconectarBase() throws Exception {
        try {
            if (conexion != null) {
                conexion.close();
            }
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
        } catch (SQLException e) {
            throw new Exception("Error al desconectarse");
        }
    }

    public void insertarModificarEliminar(String sql) throws Exception {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                throw new Exception("Error al ejecutar rollback");
            }
            throw new Exception("Error al ejecutar sentencia");
        } finally {
            desconectarBase();
        }
    }

    public void consultarBase(String sql) throws Exception {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException e) {
            throw new Exception("Error al consultar");
        }
    }
}
