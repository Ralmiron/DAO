package Main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import DAO.DAODisfraz;
import DAO.DAONene;
import DAO.DAOPieza;
import DAO.DAOVecino;
import DAO.DAOVisita;
import POJO.Disfraz;
import POJO.Nene;
import POJO.Pieza;
import POJO.Vecino;
import POJO.Visita;

public class Main {

	public static void main(String[] args) {

		boolean terminar = false;
		int opcion2 = 0;
		do {
			int opcion = presentarMenu();

			switch (opcion) {
				case 1:
					opcion2 = presentarMenu2();
					switch (opcion2) {
						case 1:
							Metodos.SelectAllNenes();
							break;
						case 2:
							Metodos.SelectAllPiezas();
							break;
						case 3:
							Metodos.SelectAllVecinos();
							break;
						case 4:
							Metodos.SelectAllVisitas();
							break;
						case 5:
							Metodos.SelectAllDisfraces();
							break;
					}
				break;
				case 2:
					opcion2 = presentarMenu2();
					switch (opcion2) {
						case 1:
							Metodos.InsertNene();
							break;
						case 2:
							Metodos.InsertPiezas();
							break;
						case 3:
							Metodos.InsertVecinos();
							break;
						case 4:
							Metodos.InsertVisitas();
							break;
						case 5:
							Metodos.InsertDisfraces();
							break;
					}
				break;
				case 3:
					opcion2 = presentarMenu2();
					switch (opcion2) {
						case 1:
							Metodos.UpdateNene();
							break;
						case 2:
							Metodos.UpdatePiezas();
							break;
						case 3:
							Metodos.UpdateVecinos();
							break;
						case 4:
							Metodos.UpdateVisitas();
							break;
						case 5:
							Metodos.UpdateDisfraces();
							break;
					}
				break;
				case 4:
					opcion2 = presentarMenu2();
					switch (opcion2) {
						case 1:
							Metodos.DeleteNene();
							break;
						case 2:
							Metodos.DeletePiezas();
							break;
						case 3:
							Metodos.DeleteVecinos();
							break;
						case 4:
							Metodos.DeleteVisitas();
							break;
						case 5:
							Metodos.DeleteDisfraces();
							break;
					}
				break;
			}
			//limpiarConsola();
		} while (!terminar);
	}

	private static void limpiarConsola() {
		for (int i = 0; i < 10; i++) {
			System.out.println();
		}
	}
	
	private static int presentarMenu() {
		Scanner scint = new Scanner(System.in);
		System.out.println("---------- MANTENIMIENTO ----------");
		System.out.println("----- Escoge una opción -----");
		System.out.println("Opción 1: Ver los datos.");
		System.out.println("Opción 2: Insertar datos.");
		System.out.println("Opción 3: Actualizar datos.");
		System.out.println("Opción 4: Borrar datos.");
		System.out.println("Opción 5: Salir");
		return scint.nextInt();
	}
		
	private static int presentarMenu2() {
		Scanner scint = new Scanner(System.in);
		System.out.println("----- ¿De qué tabla? -----");
		System.out.println("Opción 1: Tabla Nene.");
		System.out.println("Opción 2: Tabla Pieza.");
		System.out.println("Opción 3: Tabla Vecino.");
		System.out.println("Opción 4: Tabla Visita.");
		System.out.println("Opción 5: Tabla Disfraz");
		System.out.println("Opción 6: Salir");
		return scint.nextInt();
	}
	}
