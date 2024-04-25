package ar.edu.unju.fi.ejercicio4.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio4.constantes.Posicion;
import ar.edu.unju.fi.ejercicio4.model.Jugador;

public class Main {
	
	private static Scanner scanner;
	private static List<Jugador> jugadores;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		jugadores = new ArrayList<>();
		
		int opcion = 0;
		do {
			menuOpcion();
			opcion = excepcionEnteros();
			switch(opcion) {
			case 1: 
				agregarJugador();
				break;
			case 2:
				if(jugadores.size()>0)
					mostrarJugador();
				else
					System.out.println("No se agregaron jugadores");
				break;
			case 3: 
				if(jugadores.size()>0)
					modificarJugador();
				else
					System.out.println("No se agregaron jugadores");
				break;
			case 4:
				if(jugadores.size()>0)
					borrarJugador();
				else
					System.out.println("No se agregaron jugadores");
				break;
			case 5:
				System.out.println("Fin del programa...");
				break;
			default:System.out.println("Opcion Incorrecta!!!");
			}
		}while(opcion!=5);
	}
	
	/**
	 * Excepcion de numeros enteros
	 * @return valor entero
	 */
	public static int excepcionEnteros() {
		boolean band=false;
		int valor=0;
		do{
			band=false;
			try{
				valor=scanner.nextInt();
				band=true;
			}catch(InputMismatchException ex){
				System.out.println("Error, valor incorrecto");
				scanner.next();
			}
		}while(band==false);
		return valor;
	}
	
	/**
	 * Excepcion de numeros reales
	 * @return valor real
	 */
	public static float excepcionFloat() {
		boolean band=false;
		float valor=0;
		do{
			band=false;
			try{
				valor=scanner.nextFloat();
				band=true;
			}catch(InputMismatchException ex){
				System.out.println("Error, valor incorrecto");
				scanner.next();
			}
		}while(band==false);
		return valor;
	}
	
	/**
	 * Controla que la palabra ingresada sea correcta
	 * @return cadena palabra
	 */
	public static String validarPalabra() {
		boolean band=false;
		String palabra;
		do {
			palabra=scanner.next();
			if(palabra.length()<3 || palabra.matches(".*[^a-zA-Z].*")==true)
				System.out.println("El valor ingresado no corresponde a lo pedido");
			else
				band=true;
		}while(band==false);
		return palabra;
	}
	
	/*
	 * Menu de opciones
	 */
	public static void menuOpcion() {
		System.out.println("==========================");
		System.out.println("1-Alta de Jugador");
		System.out.println("2-Mostrar jugadores");
		System.out.println("3-Modificar posicion de un jugador");
		System.out.println("4-Eliminar un jugador");;
		System.out.println("5-Salir");
		System.out.println("==========================");
		System.out.println("Ingrese una opcion: ");
	}
	
	/*
	 * Agregar un objeto jugador al ArrayList Jugador
	 */
	public static void agregarJugador() {
		Jugador jugador = new Jugador();
		System.out.print("Ingrese el nombre del jugador: ");
		jugador.setNombre(validarPalabra());
		System.out.print("Ingrese el apellido del jugador: ");
		jugador.setApellido(validarPalabra());
		
		boolean band=false;
		do {
			System.out.print("Ingrese la fecha de nacimiento (dd-MM-yyyy): ");
			String fechaNac = scanner.next();
			DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			band=false;
			try {
				LocalDate fechaNacimiento = LocalDate.parse(fechaNac,fecha);
				jugador.setFechaNacimiento(fechaNacimiento);
				band=true;
			} catch (Exception e) {
				System.out.println("Error, valor incorrecto");
			}
		}while(band==false);
		
		System.out.print("Ingrese el nacionalidad del jugador: ");
		jugador.setNacionalidad(validarPalabra());
		System.out.print("Ingrese la estatura del jugador: ");
		jugador.setEstatura(excepcionFloat());
		System.out.print("Ingrese el peso del jugador: ");
		jugador.setPeso(excepcionFloat());
		jugador.setPosicion(menuPosicion());
		
		jugadores.add(jugador);
	}
	
	/**
	 * agrega la posicion al jugador
	 * @return posicion aux
	 */
	public static Posicion menuPosicion() {
		Posicion[] posiciones = Posicion.values();
		Posicion aux = null;
		boolean band = false;
		do {
			System.out.println("----- Posicion -----");
			System.out.println("1-Delantero");
			System.out.println("2-Medio");
			System.out.println("3-Defensa");
			System.out.println("4_Arquero");
			System.out.println("---------------------------------");
			System.out.println("Ingrese una opcion: ");
			int opcion = excepcionEnteros();
			for(Posicion posicion:posiciones) {
				if(posicion.ordinal() == opcion-1) {
					aux = posicion;
	            	band = true;
				}
			}
			if(band == false)
				System.out.println("Opcion Incorrecta!!!");
		}while(band == false);
		return aux;
	}
	
	/*
	 * Muestra todos los jugadores guardador en el arraylist Jugador
	 */
	public static void mostrarJugador() {
		System.out.println("---Lista de Jugadores---");
		jugadores.forEach(j->System.out.println(j));
	}
	
	/*
	 * Modificar datos de un objeto Jugador
	 */
	public static void modificarJugador() {
		System.out.println("---Modificar un Jugador---");
		boolean encontrado = false;
		System.out.println("Ingrese el nombre del jugador: ");
		String nombre = validarPalabra();
		System.out.println("Ingrese el apellido del jugador: ");
		String apellido = validarPalabra();
		for (Jugador jugador : jugadores) {
			if (nombre.equals(jugador.getNombre()) && apellido.equals(jugador.getApellido())) {
				encontrado = true;
				jugador.setPosicion(menuPosicion());
			}
		}
		if (!encontrado) 
            System.out.println("No se encontro el jugador");
	}
	
	/*
	 * Eliminar un objeto jugador del arrayList Jugador
	 */
	public static void borrarJugador() {
		System.out.println("---Borrar un Jugador---");
		System.out.println("Ingrese el nombre del jugador: ");
		String nombre = validarPalabra();
		System.out.println("Ingrese el apellido del jugador: ");
		String apellido = validarPalabra();
		boolean band = false;
		Iterator<Jugador> iterator = jugadores.iterator();
		if(!jugadores.isEmpty()) {
			while(iterator.hasNext()) {
				Jugador aux = iterator.next();
				if(aux.getNombre().equals(nombre) && aux.getApellido().equals(apellido)) {
					band = true;
					iterator.remove();
					System.out.println("Se elimino el jugador "+nombre+" "+apellido);
				}
			}
			if(band==false)
				System.out.println("El jugador no existe!!!!");
		}
	}
}
