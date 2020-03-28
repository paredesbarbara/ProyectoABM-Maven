package baseDeDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AppBD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conexion = null;

		try {

			conexion = AdminApp.obtenerConexion();
			Scanner scan = new Scanner(System.in);
			int opcionesMenu = 0;

			opcionesMenu = mostrarMenu(scan);

			while (opcionesMenu != 0) {

				switch (opcionesMenu) {
				case 1:
					listado(conexion);
					break;
				case 2:
					alta(conexion, scan);
					break;
				case 3:
					modificar(conexion, scan);
					break;
				case 4:
					baja(conexion, scan);
					break;

				case 5:
					buscar(conexion, scan);
					break;

				case 6:
					venta(conexion, scan);
					break;

				case 7:
					registroVenta(conexion, scan);
					break;

				case 0:
					salir(scan);
					break;
				}
				opcionesMenu = mostrarMenu(scan);
			}
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void salir(Scanner scan) {
		// TODO Auto-generated method stub

	}

	private static void registroVenta(Connection conexion, Scanner scan) {
		// TODO Auto-generated method stub

		Statement stmt;

		try {

			stmt = conexion.createStatement();

			System.out.println("SISTEMA DE REGISTRO DE VENTAS");
			System.out.println("-----------------------------");

			System.out.println("Ingrese numero ID de cliente");
			int idCliente = scan.nextInt();

			ResultSet rs = stmt.executeQuery("SELECT * FROM VENTA WHERE IDPERSONA = " + idCliente + "");

			boolean noHayRegistro = true;

			if (rs.next()) {
				noHayRegistro = false;

				System.out.println("IDVENTAS|FECHA|IMPORTE|IDPERSONA");

				System.out.println(rs.getInt(1) + "  " + rs.getDate(2) + "  " + rs.getFloat(3) + " " + +rs.getInt(4));

			} else {
				System.out.println("No se encontro el ID del cliente");
				System.out.println("-----------------------------------------------");
			}

			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void venta(Connection conexion, Scanner scan) {
		// TODO Auto-generated method stub

		Statement stmt;

		try {

			stmt = conexion.createStatement();

			System.out.println("SISTEMA DE VENTAS");
			System.out.println("--------------------");

			System.out.println("Ingrese numero ID de cliente");
			int idPersona = scan.nextInt();

			ResultSet rs = stmt.executeQuery("SELECT * FROM PERSONA WHERE IDPERSONA = " + idPersona + "");

			boolean noHayRegistro = true;

			if (rs.next()) {
				noHayRegistro = false;
				System.out.println(rs.getInt(1) + "  " + rs.getString(2));

				System.out.println("Ingrese importe de venta");
				float importe = scan.nextFloat();

				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date fecha = new Date();
				String fechaActual = formato.format(fecha);

				String insertarVenta = "INSERT INTO VENTA (FECHA, IMPORTE, IDPERSONA) VALUES ('" + fechaActual + "', '"
						+ importe + "','" + idPersona + "')";
				stmt.executeUpdate(insertarVenta);

				System.out.println("Se cargo la venta exitosamente");

			} else {
				System.out.println("No se encontro el ID");
				System.out.println("-----------------------------------------------");
			}

			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void buscar(Connection conexion, Scanner scan) {
		// TODO Auto-generated method stub

		Statement stmt;

		try {

			stmt = conexion.createStatement();

			System.out.println("BUSQUEDA DE CLIENTE");
			System.out.println("--------------------");

			System.out.println("Ingrese el nombre que desea buscar");
			String nombre = scan.next();

			ResultSet rs = stmt.executeQuery("SELECT * FROM PERSONA WHERE NOMBRE LIKE '" + nombre + "%';");

			while (rs.next()) {
				Date fechaNa = rs.getDate(4);
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + fechaNa);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se encontro registro");
			System.out.println("-----------------------------------------------");
		}

	}

	private static void listado(Connection conexion) {
		// TODO Auto-generated method stub

		Statement stmt;

		System.out.println("LISTADO DE CLIENTES");
		System.out.println("--------------------");

		try {
			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PERSONA");

			while (rs.next()) {
				Date fechaNa = rs.getDate(4);
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + fechaNa);
			}
			System.out.println("-----------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void alta(Connection conexion, Scanner scan) {
		// TODO Auto-generated method stub

		System.out.println("ALTA DE CLIENTES");
		System.out.println("----------------");

		Statement stmt;

		try {

			stmt = conexion.createStatement();

			System.out.println("Ingrese nombre");
			String nombre = scan.next();

			System.out.println("Ingrese edad");
			int edad = scan.nextInt();

			System.out.println("Ingrese fecha de naciemiento");
			String fechaNacimiento = scan.next();
			stmt = conexion.createStatement();

			String insert = "INSERT INTO PERSONA (NOMBRE, EDAD, FECHA_NACIMIENTO) VALUES ('" + nombre + "', '" + edad
					+ "','" + fechaNacimiento + "')";
			stmt.executeUpdate(insert);

			System.out.println("-----------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void modificar(Connection conexion, Scanner scan) {
		// TODO Auto-generated method stub

		System.out.println("MODIFICACION DEL REGISTRO DE CLIENTES");
		System.out.println("-------------------------------------");

		Statement stmt;

		try {
			System.out.println("Ingrese ID ");
			int id = scan.nextInt();

			stmt = conexion.createStatement();

			ResultSet rs = stmt.executeQuery(" SELECT * FROM PERSONA WHERE IDPERSONA = " + id + " ; ");
			System.out.println("Seleccione la opcion que desea modificar");
			System.out.println("Opcion 1 : Nombre");
			System.out.println("Opcion 2 : Edad ");
			System.out.println("Opcion 3 : Fecha de Nacimiento ");
			int opcion = 0;
			opcion = scan.nextInt();

			switch (opcion) {

			case 1:
				System.out.println("Ingrese nuevo nombre");
				String nombreNuevo = scan.next();
				String update = "UPDATE PERSONA SET NOMBRE = '" + nombreNuevo + "' WHERE IDPERSONA = " + id + " ";
				stmt.executeUpdate(update);
				System.out.println("El nombre ha sido modificado");
				break;

			case 2:
				System.out.println("Ingrese nueva edad");
				String edadNueva = scan.next();
				String update1 = "UPDATE PERSONA SET EDAD = '" + edadNueva + "' WHERE IDPERSONA =  " + id + " ";
				stmt.executeUpdate(update1);
				System.out.println("La edad ha sido modificada");
				break;

			case 3:
				System.out.println("Ingrese nueva fecha de nacimiento");
				String fechaNueva = scan.next();
				String update2 = "UPDATE PERSONA SET FECHA_NACIMIENTO = '" + fechaNueva + "' WHERE IDPERSONA = " + id
						+ " ";
				stmt.executeUpdate(update2);
				System.out.println("La fecha ha sido modificada");
				break;

			default:
				break;
			}
			opcion = mostrarMenu(scan);

			System.out.println("-----------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void baja(Connection conexion, Scanner scan) {
		// TODO Auto-generated method stub

		System.out.println("BAJA DE CLIENTES");
		System.out.println("----------------");

		Statement stmt;
		try {

			stmt = conexion.createStatement();

			System.out.println("Ingrese ID");
			int id = scan.nextInt();

			String delete = "DELETE FROM PERSONA WHERE IDPERSONA = '" + id + "' ";
			stmt.executeUpdate(delete);

			System.out.println("El registro ha sido eliminado");
			System.out.println("-----------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static int mostrarMenu(Scanner scan) {
		// TODO Auto-generated method stub
		System.out.println("BIENVENIDO AL SISTEMA DE BASE DE DATOS");
		System.out.println("---------------------------------------------");
		System.out.println();
		System.out.println("1) Ver Listado");
		System.out.println("2) Dar de alta");
		System.out.println("3) Modificar usuario");
		System.out.println("4) Dar de baja");
		System.out.println("5) Buscar");
		System.out.println("6) Ventas");
		System.out.println("7) Registro de Ventas");
		System.out.println("0) Salir");
		int opMenu = 0;
		opMenu = scan.nextInt();
		return opMenu;
	}

}
