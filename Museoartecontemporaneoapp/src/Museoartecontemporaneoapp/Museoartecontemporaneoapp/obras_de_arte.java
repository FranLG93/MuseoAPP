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


package Museoartecontemporaneoapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class obras_de_arte {

	private String id_obra;
	private String nombre;
	private String autor;
	private String tipo;
	private String estilo;
	private String fecha;

	public obras_de_arte(String id_obra, String nombre, String autor, String tipo, String estilo, String fecha) {
		super();
		this.id_obra = id_obra;
		this.nombre = nombre;
		this.autor = autor;
		this.tipo = tipo;
		this.estilo = estilo;
		this.fecha = fecha;
	}

	public String getId_obra() {
		return id_obra;
	}

	public void setId_obra(String id_obra) {
		this.id_obra = id_obra;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	// METODO DE AGREGAR OBRAS DE ARTE
	

	static void agregarobrasdearte() {

		String id_obra, nombre, autor, tipo, estilo, fecha;

		/*
		 * El bucle comienza el while true lo que significa que se repetira tantas veces
		 * hasta que se ingrese los datos o se le de la opcion de cancelar o no se meta
		 * los valores que pedimos lo cual se rompe con un break y return para volver a
		 * nuestro menu de inicio
		 */

		// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
		// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

		while (true) {
			id_obra = JOptionPane.showInputDialog(null, "Ingresa el id de la obra");

			if (id_obra == null || id_obra.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El ID de la obra es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;

				// Si todo esta correcto hara la sentencia deseada y rompera el bucle con un
				// break

			} else {
				break;
			}
		}

	
			while (true) {
				nombre = JOptionPane.showInputDialog(null, "Ingresa el nombre de la obra ");

				// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
				// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

				if (nombre == null || nombre.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre de la obra es obligatorio", "Error",
							JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
					return;
				} else {
					break;
				}
			}

			while (true) {
				autor = JOptionPane.showInputDialog(null, "Ingresa el autor de la obra ");

				// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
				// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

				if (autor == null || autor.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El autor de la obra es obligatorio", "Error",
							JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
					return;
				} else {
					break;
				}
			}

			while (true) {
				tipo = JOptionPane.showInputDialog(null, "Ingresa la tecnica de la obra ");

				// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
				// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

				if (tipo == null || tipo.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El tipo de la obra es obligatorio", "Error",
							JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
					return;
				} else {
					break;
				}
			}
			
		
			
		while (true) {
			estilo = JOptionPane.showInputDialog(null, "Ingresa el estilo de la obra ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (estilo == null || estilo.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El estilo de la obra es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			fecha = JOptionPane.showInputDialog(null, "Ingresa la fecha de creacion de la obra (DD-MM-YYYY)");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (fecha == null || fecha.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "La fecha de creación de la obra es obligatoria", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}
	
		try {
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
	        String fecha3 = outputFormat.format(date);
			obras_de_arte nuevaObras_de_arte = new obras_de_arte(id_obra, nombre, autor, tipo, estilo, fecha3);

			  // CONECTAMOS LA BASE DE DATOS
	        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/museo", "museoadmin", "museo1234");
	        String sql = "INSERT INTO obras_de_arte (ID_Obra, Nombre, Autor, Tipo, Estilo, Fecha) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement consulta = conexion.prepareStatement(sql);
	        consulta.setString(1, id_obra);
	        consulta.setString(2, nombre);
	        consulta.setString(3, autor);
	        consulta.setString(4, tipo);
	        consulta.setString(5, estilo);
	        consulta.setString(6, fecha3);

	        consulta.executeUpdate();
			conexion.close();
			JOptionPane.showMessageDialog(null, "Obra ingresada correctamente");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al ingresar la obra " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
			}
	
	
	
	
	
	
	// METODO PARA EDITRAR OBRAS
	static void editarobrasdearte(Connection conexion) {
		String id_obra, nuevonombre, nuevoautor, nuevotipo, nuevoestilo, nuevafecha;

		/*
		 * El bucle comienza el while true lo que significa que se repetira tantas veces
		 * hasta que se ingrese los datos o se le de la opcion de cancelar o no se meta
		 * los valores que pedimos lo cual se rompe con un break y return para volver a
		 * nuestro menu de inicio
		 */

		while (true) {
			id_obra = JOptionPane.showInputDialog(null, "Ingresa id de la obra a editar ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU

			if (id_obra == null || id_obra.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El id de la obra es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevonombre = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre de la obra  ");

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
			nuevoautor = JOptionPane.showInputDialog(null, "Ingresa el nuevo autor de la obra ");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevoautor == null || nuevoautor.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nuevo autor es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevotipo = JOptionPane.showInputDialog(null, "Ingresa la nueva tecnica de la obra");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevotipo == null || nuevotipo.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "La nueva tecnica es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevoestilo = JOptionPane.showInputDialog(null, "Ingresa el nuevo estilo de la obraa");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevoestilo == null || nuevoestilo.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nuevo estilo es obligatorio", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		while (true) {
			nuevafecha = JOptionPane.showInputDialog(null, "Ingresea la nueva fecha de la obra");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (nuevafecha == null || nuevafecha.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "La nueva fecha es obligatoriad", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		try {
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("UPDATE obras_de_arte SET Nombre = '" + nuevonombre + "', Autor = '" + nuevoautor
					+ "', Tipo = '" + nuevotipo + "', Estilo = '" + nuevoestilo + "', Fecha = '" + nuevafecha
					+ "' WHERE ID_Obra = '" + id_obra + "'");
			JOptionPane.showMessageDialog(null, "Obra actualizada correctamente");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al actualizar la obra" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// BORRAR OBRA DE ARTE

	static void borrarobradearte(Connection conexion) {
		String id_obra;

		while (true) {
			id_obra = JOptionPane.showInputDialog(null, "Ingresa id de la obra a borrar");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (id_obra == null || id_obra.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El id de la obra es obligatorio ", "Error",
						JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Cancelando... Regresando al menu principal");
				return;
			} else {
				break;
			}
		}

		try {
			Statement consulta = conexion.createStatement();
			int filasAfectadas = consulta.executeUpdate("DELETE FROM obras_de_arte WHERE ID_Obra = '" + id_obra + "'");

			// QUITAMOS ERRORES AÑADIENTE UN IF CON QUE SEA NULO Y EL TRIM LE DECIMOS QUE
			// ESTA VACIO Y LE DAMOS UN RETURN PARA VOLVER AL MENU
			if (filasAfectadas > 0) {
				JOptionPane.showMessageDialog(null, "Obra eliminada correctamente");
			} else {
				JOptionPane.showMessageDialog(null, "No se encontro la obra de con id: " + id_obra, "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar la obra " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}

