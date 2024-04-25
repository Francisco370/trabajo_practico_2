package ar.edu.unju.fi.ejercicio2.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio2.constantes.Mes;
import ar.edu.unju.fi.ejercicio2.model.Efemeride;

public class Main {
	
	private static Scanner scanner;
	private static List<Efemeride> efemerides;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		efemerides = new ArrayList<>();
		
		int opcion = 0;
		do {
			menuOpcion();
			opcion = excepcionEnteros();
			switch(opcion) {
			case 1: 
				crearEfemeride();
				break;
			case 2:
				if(efemerides.size()>0)
					mostrarEfemeride();
				else
					System.out.println("No se agregaron efemerides");
				break;
			case 3:
				if(efemerides.size()>0)
					eliminarEfimeride();
				else
					System.out.println("No se agregaron efemerides");
				break;
			case 4:
				if(efemerides.size()>0)
					modificarEfemeride();
				else
					System.out.println("No se agregaron efemerides");
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
		boolean band = false;
		int valor = 0;
		do{
			band = false;
			try{
				valor = scanner.nextInt();
				band = true;
			}catch(InputMismatchException ex){
				System.out.println("Error, valor incorrecto");
				scanner.next();
			}
		}while(band == false);
		return valor;
	}
	
	/**
	 * Controla que la palabra ingresada sea correcta
	 * @return cadena palabra
	 */
	public static String validarPalabra() {
		boolean band = false;
		String palabra;
		do {
			palabra = scanner.next();
			if(palabra.length()<3 || palabra.matches(".*[^a-zA-Z].*") == true)
				System.out.println("El valor ingresado no corresponde a lo pedido");
			else
				band = true;
		}while(band == false);
		return palabra;
	}
	
	/*
	 * menu de opciones
	 */
	public static void menuOpcion() {
		System.out.println("=========================");
		System.out.println("1-Crear Efemeride");
		System.out.println("2-Mostrar Efemeride");
		System.out.println("3-Eliminar Efemeride");
		System.out.println("4-Modificar Efemeride");
		System.out.println("5_Salir");
		System.out.println("=========================");
		System.out.println("Ingrese una opcion: ");
	}
	
	/*
	 * Agregar un objeto al ArrayList Efemeride
	 */
	public static void crearEfemeride() {
		Efemeride efe = new Efemeride();
		System.out.println("Ingrese el codigo del Efemeride: ");
		efe.setCodigo(excepcionEnteros());
		efe.setMes(ingresarMes());
		Mes aux = efe.getMes();
		boolean validar = false;
		do {
			System.out.println("Ingrese el dia: ");
			int dia = excepcionEnteros();
			validar = validarDia(aux,dia);
			if(validar == true)
				efe.setDia(dia);
			else
				System.out.println("El dia ingresado no es valido!!!");
		}while(validar == false);
		System.out.println("Ingrese detalle del efemeride: ");
		efe.setDetalle(validarPalabra());
		
		efemerides.add(efe);
		
	}
	
	/**
	 * vaildar el mes ingresador
	 * @return enum Mes, aux
	 */
	public static Mes ingresarMes() {
		Mes[] meses = Mes.values();
		Mes aux = null;
		boolean band = false;
		do {
			System.out.println("Ingrese el mes <del 1 a 12>: ");
			int m = excepcionEnteros();
			if(m>=1 && m<=12) {
				band = true;
				for(Mes mes:meses) {
					if(mes.ordinal() == m-1)
						aux = mes;
				}
			}else
				System.out.println("EL mes ingresado es incorrecto!!!");
		}while(band == false);
		return aux;
	}
	
	/**
	 * validar el dia ingresado
	 * @param aux
	 * @param dia
	 * @return boolean band
	 */
	public static boolean validarDia(Mes aux, int dia) {
		int m = aux.ordinal();
		boolean band = false;
		if( (m==3 || m==5 || m==8 || m==10) && (dia>=1 && dia<=30))
			band = true;
		if( (m==0 || m==2 || m==4 || m==6 || m==7 || m==9 || m==11) && (dia>=1 && dia<=31))
			band = true;
		if( (m==1) && (dia>=1 && dia<=29))
			band = true;
		return band;
	}
	
	/*
	 * Muestra todos los efemeride guardados en el arraylist Efemeride
	 */
	public static void mostrarEfemeride() {
		System.out.println("---Lista de Efemerides---");
		efemerides.forEach(e->System.out.println(e));
	}
	
	/*
	 * Eliminar un objeto efemeride del arrayList Efemeride
	 */
	public static void eliminarEfimeride() {
		System.out.println("---Eliminar Efemeride---");
		System.out.println("Ingrese el codigo del efemeride a eliminar: ");
		int cod = excepcionEnteros();
		boolean band = false;
		Iterator<Efemeride> iterator = efemerides.iterator();
		if(!efemerides.isEmpty()) {
			while(iterator.hasNext()) {
				Efemeride aux = iterator.next();
				if(aux.getCodigo() == cod) {
					band = true;
					iterator.remove();
					System.out.println("Se elimino el efemeride con el codigo "+cod);
				}
			}
			if(band==false)
				System.out.println("El codigo ingresado no existe!!!!");
		}
	}
	
	/*
	 * Modificar datos del efemeride
	 */
	public static void modificarEfemeride() {
		System.out.println("---Modificar Efemeride---");
		System.out.println("Ingrese el codigo del efemeride: ");
		int cod = excepcionEnteros();
		boolean band = false;
		for(Efemeride efe:efemerides) {
			if(efe.getCodigo() == cod) {
				band = true;
				efe.setMes(ingresarMes());
				boolean validar = false;
				do {
					System.out.println("Ingrese el dia: ");
					int dia = excepcionEnteros();
					validar = validarDia(efe.getMes(),dia);
					if(validar == true)
						efe.setDia(dia);
					else
						System.out.println("El dia ingresado no es valido!!!");
				}while(validar == false);
				System.out.println("Ingrese nuevo detalle del efemeride: ");
				efe.setDetalle(validarPalabra());
			}
		}
		if(band==false)
			System.out.println("El codigo ingresado no es valido!!!");
	}
}
