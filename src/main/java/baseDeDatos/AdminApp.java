package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminApp {

	// Metodo para obtener conexion
	public static Connection obtenerConexion() {
		Connection con = null;
		// Intenta conectar si esto sucede accede a id, usuario y contraseña
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/basededatos", "root", "");
			System.out.println("Se encuentra conectado");
			// Si no puede acceder a la conexion tira una excepcion y muestra este mensaje
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Hubo un problema con la conexion");
		}
		return con;
	}
}
