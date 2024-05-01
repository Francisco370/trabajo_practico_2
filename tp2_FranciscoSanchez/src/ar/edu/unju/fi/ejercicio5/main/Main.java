package ar.edu.unju.fi.ejercicio5.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio5.model.Producto.origenFab;
import ar.edu.unju.fi.ejercicio5.model.PagoEfectivo;
import ar.edu.unju.fi.ejercicio5.model.PagoTarjeta;
import ar.edu.unju.fi.ejercicio5.model.Producto;
import ar.edu.unju.fi.ejercicio5.model.Producto.categoria;

public class Main {
	
	private static Scanner scanner;
	private static List<Producto> productos;
	private static List<Producto> compras;
	
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
				realizarCompra();
				break;
			case 3:
				System.out.println("Fin del Programa");
				break;
			default: System.out.println("Opcion Incorrecta!!!!");
			}
		}while(opcion != 3);
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
	 * Controla que la cadena ingresada sea de numeros
	 * @return cadena numTarj
	 */
	public static String validarPalabra() {
		boolean band=false;
		String numTarj;
		do {
			numTarj=scanner.next();
			if((numTarj.length()!=16) || (numTarj.matches("[0-9]+")!=true))
				System.out.println("Numero de tarjeta invalido, debe contener numeros y tener 16 digitos!!!");
			else
				band=true;
		}while(band==false);
		return numTarj;
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
		System.out.println("=======================");
		System.out.println("1-Mostrar productos");
		System.out.println("2-Realizar compra");
		System.out.println("3_Salir");
		System.out.println("=======================");
		System.out.println("Ingrese una opcion: ");
	}
	
	/*
	 * Muestra todos los objetos guardador en el arraylist de Productos
	 */
	public static void mostrarProductos() {
		System.out.println("-----Listas de productos-----");
		productos.forEach(p->System.out.println(p));
	}
	
	/*
	 * Realiza la compra del producto, ingresando el codigo del producto
	 * lo busca en el arraylist productos y lo agrega al arraylist compras
	 *  si el producto estubiera disponible
	 */
	public static void realizarCompra() {
		compras = new ArrayList<>();
		boolean bandExiste = false, bandStock = false;
		String resp = null;
		do {
			System.out.println("Ingrese el codigo del producto a comprar");
			int cod = excepcionEnteros();
			for(Producto prod:productos) {
				if(prod.getCodigo() == cod) {
					bandExiste = true;
					if((prod.isEstado() == true) && (bandExiste == true)) {
						bandStock = true;
						compras.add(prod);
					}
				}
			}
			if((bandExiste == false && bandStock ==false) || (bandExiste == false))
				System.out.println("El codigo ingresado no corresponde a un producto registrado!!!");
			if(bandExiste == true && bandStock ==false)
				System.out.println("El producto no tiene stock!!!");
			boolean bandvald = false;
			while(bandvald == false){
				System.out.println("Desea realizar otra compra? <s/n>");
				resp = scanner.next();
				if(resp.toUpperCase().equals("S")==false && resp.toUpperCase().equals("N")==false) { //
					System.out.println("Respuesta Incorrecta!!!");
				}else
					bandvald = true;
			}
		}while(resp.toUpperCase().equals("S"));
		
		opcionPago();
		
	}
	
	/*
	 * Finaliza la compra, efectuando el pago en efectivo o tarjeta
	 */
	public static void opcionPago() {
		boolean band = false;
		do {
			System.out.println("---Finalizar compra---");
			System.out.println("1-Pago Efectivo");
			System.out.println("2-Pago con Tarjeta");
			int op = excepcionEnteros();
			switch(op) {
			case 1: 
				PagoEfectivo pe = new PagoEfectivo(montoTotal(),LocalDate.now());
				pe.realizarPago(montoTotal());
				pe.imprimirRecibo();
				band = true;
				break;
			case 2:
				System.out.println("Ingrese el numero de la tarjeta: ");
				String num = validarPalabra(); 
				PagoTarjeta pt = new PagoTarjeta(num,LocalDate.now(),montoTotal());
				pt.realizarPago(montoTotal());
				pt.imprimirRecibo();
				band=true;
				break;
			default: System.out.println("Opcion Incorrecta!!!");
			}
		}while(band == false);
	}
	
	/**
	 * sumatoria de los precios de los productos que se seleccionaron para la compra
	 * @return double precio
	 */
	public static double montoTotal() {
		double precio = 0;
		for(Producto prod:compras) {
			precio += prod.getPrecioUnit();
		}
		return precio;
	}
}
