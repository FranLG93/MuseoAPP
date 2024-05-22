package Museoartecontemporaneoapp;

/*
 * MIT License
 * 
 * Copyright (c) 2024 [Francisco Luengo Gomez]
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class visitantes extends persona {
	private String correo;
	private int telefono;
	private String fecha;

	public visitantes(String id_dni, String nombre, int telefono, String correo, int telefono2, String fecha) {
		super(id_dni, nombre, telefono);
		this.correo = correo;
		telefono = telefono2;
		this.fecha = fecha;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	// METODO PARA AÑADIR VISITANTES
	public static void agregarvisitante() {

		String dni, nombre, telefono, correo, fecha;

		/*
		 * El bucle comienza el while true lo que significa que se repetira tantas veces
		 * hasta que se ingrese los datos o se le de la opcion de cancelar o no se meta
		 * los valores que pedimos lo cual se rompe con un break y return para volver a
		 * nuestro menu de inicio
		 */

		while (true) {
			dni = JOptionPane.showInputDialog(null, "Ingresa el dni del visitante ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (dni == null || dni.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El dni es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;

				// Si todo esta correcto hara la sentencia deseada y rompera el bucle con un
				// break
			} else {
				break;
			}
		}

		while (true) {
			nombre = JOptionPane.showInputDialog(null, "Ingrea el nombre del visitante");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

			if (nombre == null || nombre.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			telefono = JOptionPane.showInputDialog(null, "Ingresea el telefono del visitante");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

			if (telefono == null || telefono.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El telefono es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			correo = JOptionPane.showInputDialog(null, "Ingresa el correo del visitante");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

			if (correo == null || correo.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El correo es obligatorio ", "Error", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}

		}

		while (true) {
			fecha = JOptionPane.showInputDialog(null, "Ingresa la fecha de visita (DD-MM-YYYY)");
			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

			if (fecha == null || fecha.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "La fecha de visita es obligatoria", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		try {
			int telefonoInt = Integer.parseInt(telefono);
			
			//AQUI PONEMOS Y DECALARAMOS ESTOS OBJETOS PARA QUE INTRODUZCAMOS  DE LA MANERA NORMAL LA FECHA Y EN LA BASE DE DATOS QUEDE EN FORMATO DATE DE AÑO,MES,DIA
	        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
	        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date date = null;
			try {
				date = inputFormat.parse(fecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String fecha2 = outputFormat.format(date);
			visitantes nuevovisitante = new visitantes(dni, nombre, telefonoInt, fecha2, 0, null);

			 // CONECTAMOS LA BASE DE DATOS
	        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/museo", "museoadmin", "museo1234");
	        String sql = "INSERT INTO visitantes (ID_Visitante, Nombre, Correo, Contacto, fecha_visita) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement consulta = conexion.prepareStatement(sql);
	        consulta.setString(1, dni);
	        consulta.setString(2, nombre);
	        consulta.setString(3, correo);
	        consulta.setInt(4, telefonoInt);
	        consulta.setString(5, fecha2);

	        consulta.executeUpdate();
			// CERRAMOS LA CONEXION
			conexion.close();
			JOptionPane.showMessageDialog(null, "Visitante añadido correctamente");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El telefono debe ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al ingresar el visitante " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// METODO PARA EDITAR VISITANTES
	static void editarvisitante(Connection conexion) {
		String dni, nuevonombre, nuevocorreo, nuevotelefono, nuevafechavisita;

		/*
		 * El bucle comienza el while true lo que significa que se repetira tantas veces
		 * hasta que se ingrese los datos o se le de la opcion de cancelar o no se meta
		 * los valores que pedimos lo cual se rompe con un break y return para volver a
		 * nuestro menu de inicio
		 */

		while (true) {
			dni = JOptionPane.showInputDialog(null, "Ingresa el dni del visitante a editar ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

			if (dni == null || dni.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El dni del visitante es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevonombre = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del visitante ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENUV
			if (nuevonombre == null || nuevonombre.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nuevo nombre es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevocorreo = JOptionPane.showInputDialog(null, "Ingresa el nuevo correo del visitante ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevocorreo == null || nuevocorreo.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nuevo correo del visitante es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevotelefono = JOptionPane.showInputDialog(null, "Ingresa el nuevo telefono del visitante ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevotelefono == null || nuevotelefono.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nuevo telefono del visitante es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevafechavisita = JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de visita del visitante ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevafechavisita == null || nuevafechavisita.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "La nueva fecha de visita es obligatoria", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		try {
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("UPDATE visitantes SET Nombre = '" + nuevonombre + "', Correo = '" + nuevocorreo
					+ "', Contacto = '" + nuevotelefono + "', fecha_visita = '" + nuevafechavisita
					+ "' WHERE ID_Visitante = '" + dni + "'");
			JOptionPane.showMessageDialog(null, "Visitante actualizado correctamente");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al actualizar el visitante: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// BORRAR VISITANTE
	static void borrarvisitante(Connection conexion) {
		String id_dni;

		while (true) {
			id_dni = JOptionPane.showInputDialog(null, "Ingresa dni del visitante a borrar");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (id_dni == null || id_dni.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El dni del visitante es obligatorio ", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		try {
			Statement consulta = conexion.createStatement();
			int filasAfectadas = consulta.executeUpdate("DELETE FROM visitantes WHERE ID_Visitante = '" + id_dni + "'");
			if (filasAfectadas > 0) {
				JOptionPane.showMessageDialog(null, "Visitante eliminado correctamente");
			} else {
				JOptionPane.showMessageDialog(null, "No se encontro el visitante con DNI: " + id_dni, "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el visitante: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
