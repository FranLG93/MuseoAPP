package Museoartecontemporaneoapp;

import java.awt.Desktop;

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

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.text.SimpleDateFormat;
public class mainn {

	// CREAMOS EL OBJETO Y METEMOS LA IMAGEN PARA INTRODUCIRLO EN EL JOPTION

	private static String imagen = "C:\\Users\\VORPC\\Desktop\\Museo-proyecto-final-Francisco-Luengo-Gomez\\\\Nuevacarpeta\\\\QRlogo.png";
	private static ImageIcon icono = new ImageIcon(imagen);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// CAMBIAMOS LA FUENTE Y EL TAMAÑO DE NUESTRA APP
		Font font = new Font("Arial", Font.PLAIN, 20);
		UIManager.put("OptionPane.messageFont", font);
		
		// LLAMAMOS A NUESTRO REGISTRO PARA PODER INICIAR SESION EN NUESTRA APP
		
		boolean iniciar = true;
		while (iniciar) {

			String usuario = JOptionPane.showInputDialog(null, "            Usuario ", "",
					JOptionPane.INFORMATION_MESSAGE, icono, null, null).toString();
			String contraseña = JOptionPane.showInputDialog(null, "         Contraseña ", "",
					JOptionPane.INFORMATION_MESSAGE, icono, null, null).toString();
if (usuario ==null||contraseña == null){
	JOptionPane.showMessageDialog(null, "No es correcto, Volviendo al menu de inicio, ");
	return;
	
}
			
			
			if ("museoadmin".equals(usuario))
				if ("museo1234".equals(contraseña)) {

					Font font1 = new Font("Arial", Font.PLAIN, 15);
					UIManager.put("OptionPane.messageFont", font1);
					
					JOptionPane.showMessageDialog(null, "Bienvenido al museo de arte contemporaneo");
					mainn mainn = new mainn();
					mainn.menuprincipal();
					break;
				} else {
					JOptionPane.showMessageDialog(null, "No puedes entrar, intentalo de  nuevo");

				}
		}

	}

	public static void menuprincipal() {
		
		// METEMOS UN TRY Y CATCH PARA EVITAR ERRORES Y PODER ENLAZARLO CORRECTAMENTE
		// CON NUESTRA BASE DE DATOS

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/museo", "museoadmin",
					"museo1234");
			System.out.println("Conexion OK");
			Statement consulta = conexion.createStatement();

			int opcion = 0;
			
			Font font = new Font("Arial", Font.PLAIN, 15);
			UIManager.put("OptionPane.messageFont", font);


			do {
				try {
					
					//introducimos localdate para meter la fecha actual que estamos y la hora de inicio
					LocalDate dia = LocalDate.now();

					LocalTime hora = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
					System.out.println();
					
					//menu de nuestra app de gestion de museo
					
					opcion = Integer.parseInt(JOptionPane.showInputDialog(null,

							"Bienvenido al sistema de gestion del Museo:  \n                         " + dia
									+ "                  \n                               " + hora
									+ "\n         Que es lo que deseas hacer\n" + "\n 1. Agregar trabajador\n"
									+ "\n 2. Agregar visitante\n" + "\n 3. Agregar obra de arte\n"  + "\n 4. Agregar una reserva\n"
									+ "\n 5. Editar datos\n" + "\n 6. Borrar datos\n"
									+ "\n 7. Ver datos guardados en la base de datos\n" + "\n 8.Ir a la pagina web\n" + "\n 9. Salir\n",
							"\n                                                                             Museo de arte contemporaneo \n"

							, JOptionPane.INFORMATION_MESSAGE, icono, null, null).toString());
					switch (opcion) {
					case 1:
						trabajadores t1 = new trabajadores(null, null, 0, null, null, null);
						t1.agregartrabajador();
						break;
					case 2:
						visitantes v1 = new visitantes(null, null, 0, null, 0, null);
						v1.agregarvisitante();
						break;
					case 3:
						obras_de_arte O1 = new obras_de_arte(null, null, null, null, null, null);
						O1.agregarobrasdearte();
						break;
					case 4:
						reserva r1 = new reserva(null, null, 0, null, null, 0, null);
						r1.agregarunareserva();
						break;
					case 5:
						editardatos(conexion);
						break;
					case 6:
						borrardatos(conexion);
						break;

					case 7:
						verdatos(conexion);
					case 8 :
						
						//VINCULACION A PAG WEB
						  File web = new File("C:\\Users\\VORPC\\Desktop\\Museo-proyecto-final-Francisco-Luengo-Gomez\\WEB MUSEO\\museo.html");
					        if (web.exists()) {
					            try {
					                Desktop.getDesktop().open(web);
					            } catch (IOException e) {
					                e.printStackTrace();
					            } catch (IllegalArgumentException e) {
					                System.err.println("El archivo no es válido o no puede ser abierto.");
					                e.printStackTrace();
					            }
					        } else {
					            System.err.println("El archivo no existe.");
					        }
					    
					
					
					case 9:
						JOptionPane.showMessageDialog(null,
								"Gracias por usar el sistema del museo, \n            Saliendo del sistema...\n",
								"Museo de arte contemporaneo", JOptionPane.INFORMATION_MESSAGE, icono);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Por favor, introduce un numero del menu", "Error",
								JOptionPane.ERROR_MESSAGE);
						break;
					}

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Por favor, introduce un numero del menu" + "", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} while (opcion != 8);

			// CERRAMOS LA CONEXION CON LA BASE DE DATOS
			conexion.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectarse con la base de datos: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void editardatos(Connection conexion) {
		int editar = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Seleccione el tipo de dato que desea editar:\n" + "1. Trabajador\n" + "2. Visitante\n"
						+ "3. Obra de arte\n",
				"Edicion de datos", JOptionPane.QUESTION_MESSAGE, icono, null, null).toString());

		switch (editar) {
		case 1:
			trabajadores t2 = new trabajadores(null, null, editar, null, null, null);
			((trabajadores) t2).editartrabajador(conexion);
			break;

		case 2:
			visitantes v2 = new visitantes(null, null, editar, null, editar, null);
			((visitantes) v2).editarvisitante(conexion);
			break;
		case 3:
			obras_de_arte O2 = new obras_de_arte(null, null, null, null, null, null);
			((obras_de_arte) O2).editarobrasdearte(conexion);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Esto no se puede hacer", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}

	private static void borrardatos(Connection conexion) {
		int borr = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Seleccione el tipo de dato que desea borrar\n" + "1. Trabajadores\n" + "2. Visitantes\n"
						+ "3. Obras de arte\n"+ "4. Reservas\n",
				"Eliminar datos", JOptionPane.QUESTION_MESSAGE, icono, null, null).toString());

		switch (borr) {
		case 1:
			trabajadores b1 = new trabajadores(null, null, 0, null, null, null);
			((trabajadores) b1).borrartrabajador(conexion);
			break;
		case 2:
			visitantes b2 = new visitantes(null, null, 0, null, 0, null);
			((visitantes) b2).borrarvisitante(conexion);
			break;
		case 3:
			obras_de_arte b3 = new obras_de_arte(null, null, null, null, null, null);
			((obras_de_arte) b3).borrarobradearte(conexion);
			break;
		case 4:
			reserva b4 = new reserva(null, null, 0, null, null, 0, null);
			((reserva)b4).borrarreserva(conexion);
		default:
			JOptionPane.showMessageDialog(null, "Esto no se puede hacer", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}

	private static void verdatos(Connection conexion) {
		int tipoConsulta = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Que datos que quieres ver:\n" + "1. Trabajadores\n" + "2. Visitantes\n" + "3. Obras de Arte\n" + "4. Reservas\n",
				"Consulta de datos", JOptionPane.QUESTION_MESSAGE, icono, null, null).toString());

		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado;
			String datos = "";
			switch (tipoConsulta) {
			case 1:
				resultado = consulta.executeQuery("SELECT * FROM trabajadores");
				while (resultado.next()) {
					datos += "DNI: " + resultado.getString("ID_Trabajador") + ", Nombre: "
							+ resultado.getString("Nombre") + ", Posición: " + resultado.getString("Posicion")
							+ ", Departamento: " + resultado.getString("Departamento") + ", Contacto: "
							+ resultado.getString("Contacto") + "\n";
				}
				break;
			case 2:
				resultado = consulta.executeQuery("SELECT * FROM visitantes");
				while (resultado.next()) {
					datos += "DNI: " + resultado.getString("ID_Visitante") + ", Nombre: "
							+ resultado.getString("Nombre") + ", Correo: " + resultado.getString("Correo")
							+ ", Contacto: " + resultado.getString("Contacto") + ", Fecha Visita: "
							+ resultado.getString("fecha_visita") + "\n";
				}
				break;
			case 3:
				resultado = consulta.executeQuery("SELECT * FROM obras_de_arte");
				while (resultado.next()) {
					datos += "ID Obra: " + resultado.getString("ID_Obra") + ", Nombre: " + resultado.getString("Nombre")
							+ ", Autor: " + resultado.getString("Autor") + ", Tipo: " + resultado.getString("Tipo")
							+ ", Estilo: " + resultado.getString("Estilo") + ", Fecha: " + resultado.getString("Fecha")
							+ "\n";
				}
				break;
			
			
		case 4:
			resultado = consulta.executeQuery("SELECT * FROM reservas");
			while (resultado.next()) {
				datos += "id: " + resultado.getString("id") + ", Nombre: " + resultado.getString("nombre")
						+ ", Email: " + resultado.getString("Telefono") + ", Telefono: " + resultado.getString("Telefono")
						+ ", Fecha: " + resultado.getString("Fecha") + ", Hora: " + resultado.getString("Hora") + ", Personas: " + resultado.getString("Personas")
						+ ", Comentarios: " + resultado.getString("Comentarios")
						+ "\n";
			}
			break;
		}
			JOptionPane.showMessageDialog(null, datos.isEmpty() ? "No nada que mostrar." : datos);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al consultar los datos: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE, icono);
		}
	}
}
	
	

	