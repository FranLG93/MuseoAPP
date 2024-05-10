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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class trabajadores extends persona {

	private String posicion;
	private String departamento;
	private String fecha;

	public trabajadores(String id_dni, String nombre, int telefono, String posicion, String departamento,
			String fecha) {
		super(id_dni, nombre, telefono);
		this.posicion = posicion;
		this.departamento = departamento;
		this.fecha = fecha;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	// METODO DE AGREGAR TRABAJADORES

	public void agregartrabajador() {
		String dni, nombre, telefono, cargo, departamento;

		/*
		 * El bucle comienza el while true lo que significa que se repetira tantas veces
		 * hasta que se ingrese los datos o se le de la opcion de cancelar o no se meta
		 * los valores que pedimos lo cual se rompe con un break y return para volver a
		 * nuestro menu de inicio
		 */

		while (true) {
			dni = JOptionPane.showInputDialog(null, "Ingresa el dni del trabajador");

			// QUITAMOS LOS ERRORES AÑADIENTO UN IF EN NULL Y EL TRIM EMPTY(VACIO) LE
			// DECIMOS QUE ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
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
			nombre = JOptionPane.showInputDialog(null, "Ingresa el nombre del trabajador");

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
			telefono = JOptionPane.showInputDialog(null, "Ingresa telefono del trabajador");

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
			cargo = JOptionPane.showInputDialog(null, "Ingresa el cargo del trabajador ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (cargo == null || posicion.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El cargo es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			departamento = JOptionPane.showInputDialog(null, "Ingresa el departamento que pertenece el trabajador");
			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (telefono == null || telefono.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El departamento es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		try {
			int telefonoint = Integer.parseInt(telefono);
			trabajadores nuevotrabajador = new trabajadores(dni, nombre, telefonoint, posicion, departamento, null);

			// CONECTAMOS LA BAASE DE DATOS

			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/museo", "museoadmin",
					"museo1234");
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate(
					"INSERT INTO trabajadores (ID_Trabajador, Nombre, Posicion, Departamento, Contacto) VALUES ('" + dni
							+ "','" + nombre + "','" + posicion + "','" + departamento + "','" + telefono + "')");

			// CERRAMOS LA CONEXION CON LA BASE DE DATOS
			conexion.close();
			JOptionPane.showMessageDialog(null, "Trabajador ingresado");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El telefono tiene que ser un numero", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al ingresar el trabajador " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// METODO PARA EDITAR TRABAJADOR

	public void editartrabajador(Connection conexion) {
		String dnioriginal, nuevodni, nuevonombre, nuevaposicion, nuevodepartamento, nuevocontacto;

		/*
		 * El bucle comienza el while true lo que significa que se repetira tantas veces
		 * hasta que se ingrese los datos o se le de la opcion de cancelar o no se meta
		 * los valores que pedimos lo cual se rompe con un break y return para volver a
		 * nuestro menu de inicio
		 */

		while (true) {
			dnioriginal = JOptionPane.showInputDialog(null, "Ingresa el dni del trabajador a editar ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

			if (dnioriginal == null || dnioriginal.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El dni original es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevodni = JOptionPane.showInputDialog(null, "Ingresa el nuevo dni del trabajador ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevodni == null || nuevodni.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nuevo dni es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevonombre = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del trabajador ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
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
			nuevaposicion = JOptionPane.showInputDialog(null, "Ingresa la nueva posicion del trabajador ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevaposicion == null || nuevaposicion.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "La nueva posicion es obligatoria", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevodepartamento = JOptionPane.showInputDialog(null, "Ingresa el nuevo departamento del trabajador ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevodepartamento == null || nuevodepartamento.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nuevo departamento es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevocontacto = JOptionPane.showInputDialog(null, "Ingresa el nuevo telefono del trabajador ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevocontacto == null || nuevocontacto.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nuevo telefono es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		try {
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("UPDATE trabajadores SET ID_Trabajador = '" + nuevodni + "', Nombre = '"
					+ nuevonombre + "', Posicion = '" + nuevaposicion + "', Departamento = '" + nuevodepartamento
					+ "', Contacto = '" + nuevocontacto + "' WHERE ID_Trabajador = '" + dnioriginal + "'");
			JOptionPane.showMessageDialog(null, "Trabajador actualizardo correctamente");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al actualizar al trabajador: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// BORRAR TRABAJADOR
	public void borrartrabajador(Connection conexion) {
		String id_dni;

		/*
		 * El bucle comienza el while true lo que significa que se repetira tantas veces
		 * hasta que se ingrese los datos o se le de la opcion de cancelar o no se meta
		 * los valores que pedimos lo cual se rompe con un break y return para volver a
		 * nuestro menu de inicio
		 */

		while (true) {
			id_dni = JOptionPane.showInputDialog(null, "Ingresea dni del trabajador a borrar");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (id_dni == null || id_dni.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El dni del trabajador es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal\"");
				return;
			} else {
				break;
			}
		}

		try {
			Statement consulta = conexion.createStatement();
			int filasAfectadas = consulta
					.executeUpdate("DELETE FROM trabajadores WHERE ID_Trabajador = '" + id_dni + "'");
			if (filasAfectadas > 0) {
				JOptionPane.showMessageDialog(null, "Trabajador eliminado correctamente");
			} else {
				JOptionPane.showMessageDialog(null, "No se encontro el trabajador con DNI: " + id_dni, "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el trabajador: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
