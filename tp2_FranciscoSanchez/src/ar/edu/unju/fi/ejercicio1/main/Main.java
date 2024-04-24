package ar.edu.unju.fi.ejercicio1.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio1.model.Producto;
import ar.edu.unju.fi.ejercicio1.model.Producto.categoria;
import ar.edu.unju.fi.ejercicio1.model.Producto.origenFab;

public class Main {
	
	private static Scanner scanner;
	private static List<Producto> productos;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		productos = new ArrayList<>();
		
		int opcion = 0;
		do {
			menuOpcion();
			opcion = excepcionEnteros();
			switch(opcion) {
			case 1: 
				agregarProducto();
				break;
			case 2:
				if(productos.size()>0)
					mostrarProductos();
				else
					System.out.println("No se agregaron productos");
				break;
			case 3:
				if(productos.size()>0)
					modificarProducto();
				else
					System.out.println("No se agregaron productos");
				break;
			case 4: 
				System.out.println("Fin del programa...");
				break;
			default:System.out.println("Opcion Incorrecta!!!");
			}
		}while(opcion!=4);
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
	 * Excepcion de numero double
	 * @return valor double
	 */
	public static double excepcionDouble() {
		boolean band = false;
		double valor = 0;
		do{
			band=false;
			try{
				valor = scanner.nextDouble();
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
		System.out.println("1-Crear Producto");
		System.out.println("2-Mostrar Productos");
		System.out.println("3-Modificar Producto");
		System.out.println("4_Salir");
		System.out.println("=========================");
		System.out.println("Ingrese una opcion: ");
	}
	
	/*
	 * Agregar un objeto al ArrayList Producto
	 */
	public static void agregarProducto() {
		Producto producto = new Producto();
		System.out.println("Ingrese el codigo del producto: ");
		producto.setCodigo(excepcionEnteros());
		System.out.println("Ingrese descripcion del producto: ");
		producto.setDescripcion(validarPalabra());
		System.out.println("Ingrese el precio unitario del producto: ");
		producto.setPrecioUnit(excepcionDouble());
		producto.setFab(menuFabricacion());
		producto.setCat(menuCategoria());
		
		productos.add(producto);
	}
	
	/**
	 * agrega el origen de fabricacion al producto
	 * @return origen de fabricacion, aux
	 */
	public static origenFab menuFabricacion() {
		origenFab[] origenes = origenFab.values();
		origenFab aux = null;
		boolean bandOrigen = false;
		do {
			System.out.println("----- Origen de fabricación -----");
			System.out.println("1-Argentina");
			System.out.println("2-China");
			System.out.println("3-Brasil");
			System.out.println("4_Uruguay");
			System.out.println("---------------------------------");
			System.out.println("Ingrese una opcion: ");
			int opcion = excepcionEnteros();
			for (origenFab origen : origenes) {
				if (origen.ordinal() == opcion - 1) {
	            	aux = origen;
	            	bandOrigen = true;
	            }
			}
			if(bandOrigen == false)
				System.out.println("Opcion Incorrecta!!!");
		}while(bandOrigen == false);
		return aux;
	}
	
	/**
	 * agrega la categpria al producto
	 * @return categoria, aux2
	 */
	public static categoria menuCategoria() {
		categoria[] categorias = categoria.values();
		categoria aux2 = null;
		boolean bandCategoria = false;
		do {
			System.out.println("----- Categoria -----");
			System.out.println("1-Telefonía");
			System.out.println("2-Informática");
			System.out.println("3-Electro hogar");
			System.out.println("4_Herramientas");
			System.out.println("---------------------");
			System.out.println("Ingrese una opcion: ");
			int opcion = excepcionEnteros();
			for (categoria cat : categorias) {
				if (cat.ordinal() == opcion - 1) {
	            	aux2 = cat;
	            	bandCategoria = true;
	            }
			}
			if(bandCategoria == false)
				System.out.println("Opcion Incorrecta!!!");
			
		}while(bandCategoria == false);
		return aux2;
	}
	
	/*
	 * Muestra todos los productos guardador en el arraylist de Productos
	 */
	public static void mostrarProductos() {
		System.out.println("---Lista de Productos---");
		productos.forEach(p->System.out.println(p));
	}
	
	/*
	 * Modifica datos del producto
	 */
	public static void modificarProducto() {
		System.out.println("---Modificar Producto---");
		System.out.println("Ingrese el codigo del producto: ");
		int cod = excepcionEnteros();
		boolean band = false;
		for(Producto prod:productos) {
			if(prod.getCodigo() == cod) {
				band = true;
				System.out.println("Ingrese nueva descripcion: ");
				prod.setDescripcion(validarPalabra());
				System.out.println("Ingrese nuevo precio unitario: ");
				prod.setPrecioUnit(excepcionDouble());
				prod.setFab(menuFabricacion());
				prod.setCat(menuCategoria());
			}
		}
		if(band==false)
			System.out.println("El codigo ingresado no es valido!!!");
	}
	
}
