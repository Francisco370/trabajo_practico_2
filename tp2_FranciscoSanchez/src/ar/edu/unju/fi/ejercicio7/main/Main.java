package ar.edu.unju.fi.ejercicio7.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ar.edu.unju.fi.ejercicio5.model.Producto;
import ar.edu.unju.fi.ejercicio5.model.Producto.categoria;
import ar.edu.unju.fi.ejercicio5.model.Producto.origenFab;

public class Main {
	
	private static Scanner scanner;
	private static List<Producto> productos;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		precargaProducto();
		
		int opcion = 0;
		do {
			menuOpcion();
			opcion = excepcionEnteros();
			switch(opcion) {
			case 1:
				mostrarProductos();
				break;
			case 2:
				mostrarProductosFaltantes();
				break;
			case 3:
				incrementarPrecio();
				break;
			case 4:
				mostrarProductosElectrohogar();
				break;
			case 5:
				ordenarPrecioDescendiente();
				break;
			case 6:
				mostrarProductoMayuscula();
				break;
			case 7:
				System.out.println("Fin del Programa");
				break;
			default: System.out.println("Opcion Incorrecta!!!!");
			}
		}while(opcion != 7);
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
	
	/*
	 * Se agregan objetos al ArrayList de productos
	 */
	public static void precargaProducto() {
		if(productos == null) {
			productos = new ArrayList<>();
		}
		productos.add(new Producto(10,"Televisor Smart tv",650000.0,true,origenFab.ARGENTINA,categoria.ELECTROHOGAR));
		productos.add(new Producto(11,"Celular Samnsung A01",450000.0,true,origenFab.ARGENTINA,categoria.TELEFONIA));
		productos.add(new Producto(12,"Notebook Dell",550000.0,true,origenFab.ARGENTINA,categoria.INFORMATICA));
		productos.add(new Producto(13,"Lavarropa",850000.0,false,origenFab.ARGENTINA,categoria.ELECTROHOGAR));
		productos.add(new Producto(14,"Monitor Samnsung",90000.0,true,origenFab.ARGENTINA,categoria.INFORMATICA));
		productos.add(new Producto(15,"Destornilladores",350000.0,true,origenFab.ARGENTINA,categoria.HERRAMIENTAS));
		productos.add(new Producto(16,"Gabinete Depcool",88000.0,true,origenFab.ARGENTINA,categoria.INFORMATICA));
		productos.add(new Producto(17,"Licuadora",500000.0,true,origenFab.ARGENTINA,categoria.ELECTROHOGAR));
		productos.add(new Producto(18,"Procesador Ryzen 3 3500g",350000.0,true,origenFab.URUGUAY,categoria.INFORMATICA));
		productos.add(new Producto(19,"Llaves Inglesas",250000.0,false,origenFab.URUGUAY,categoria.HERRAMIENTAS));
		productos.add(new Producto(20,"Pendrive 32gb",5000.0,true,origenFab.ARGENTINA,categoria.INFORMATICA));
		productos.add(new Producto(21,"Microonda",580000.0,true,origenFab.ARGENTINA,categoria.ELECTROHOGAR));
		productos.add(new Producto(22,"Teclado Iluminado",250000.0,true,origenFab.ARGENTINA,categoria.INFORMATICA));
		productos.add(new Producto(23,"Cargador para telefono",150000.0,true,origenFab.ARGENTINA,categoria.TELEFONIA));
		productos.add(new Producto(24,"Motherboard Asus A-320K",350000.0,true,origenFab.BRASIL,categoria.INFORMATICA));
	}
	
	/*
	 * Menu de opciones
	 */
	public static void menuOpcion() {
		System.out.println("===================================");
		System.out.println("1-Mostrar productos");
		System.out.println("2-Mostrar los productos faltantes");
		System.out.println("3-Incrementar los precios de los productos en un %20");
		System.out.println("4-Mostrar productos Electrohogar disponibles");
		System.out.println("5-Ordenar los productos por precio de forma descendente");
		System.out.println("6- Mostrar los productos con los nombres en mayúsculas");
		System.out.println("7_Salir");
		System.out.println("===================================");
		System.out.println("Ingrese una opcion: ");
	}
	
	/*
	 * Mostrar Productos disponibles
	 */
	public static void mostrarProductos() {
		Consumer<Producto> imprimirProducto = p -> System.out.println(p);
		productos.stream().filter(Producto::isEstado).forEach(imprimirProducto);
        System.out.println();
	}
	
	/*
	 * Mostrar productos que no esten disponibles
	 */
	public static void mostrarProductosFaltantes() {
		Predicate<Producto> filterEstadoFalse = p -> !p.isEstado();
		productos.stream().filter(filterEstadoFalse).forEach(System.out::println);
	}
	
	/*
	 * Incementae un 20% a todos los productos
	 */
	public static void incrementarPrecio() {
		Function<Producto, Producto> funcionIncrementar = (p)->{
			double resultado = p.getPrecioUnit() + (p.getPrecioUnit() * 0.2);
			p.setPrecioUnit(resultado);
			return p;
		};
		List<Producto> productosIncrementados = new ArrayList<>();
		productosIncrementados = productos.stream().map(funcionIncrementar).collect(Collectors.toList());
		productosIncrementados.forEach(System.out::println);
	}
	
	/*
	 * Mostrar solo los productos con la categoria ELECTROHOGAR
	 */
	public static void mostrarProductosElectrohogar() {
		Predicate<Producto> productosElectrohogar = p -> p.getCat().name().equals("ELECTROHOGAR") && p.isEstado();
		productos.stream().filter(productosElectrohogar).forEach(System.out::println);
	}
	
	/*
	 * Ordenar los productos por precio descendiente
	 */
	public static void ordenarPrecioDescendiente() {
		productos.sort(Comparator.comparing(Producto::getPrecioUnit).reversed());
		productos.forEach(p->System.out.println(p));
	}
	
	/*
	 * Mostrar el nombre del producto en Mayuscula
	 */
	public static void mostrarProductoMayuscula() {
		Function<Producto, Producto> pasarMayuscula = (p)->{
			String nombreMayusculas = p.getDescripcion().toUpperCase();
			p.setDescripcion(nombreMayusculas);
			return p;
		};
		productos.stream().map(pasarMayuscula).forEach(System.out::println);
	}
}
