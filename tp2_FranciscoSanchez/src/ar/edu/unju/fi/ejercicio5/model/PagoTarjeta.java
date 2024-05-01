package ar.edu.unju.fi.ejercicio5.model;

import java.time.LocalDate;

import ar.edu.unju.fi.interfaces.Pago;

public class PagoTarjeta implements Pago {
	private String numeroTarjeta;
	private LocalDate fechaPago;
	private double montoPagado;
	
	public PagoTarjeta() {
		
	}
	
	public PagoTarjeta(String numeroTarjeta, LocalDate fechaPago, double montoPagado) {
		this.numeroTarjeta = numeroTarjeta;
		this.fechaPago = fechaPago;
		this.montoPagado = montoPagado;
	}
	
	
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	public double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}

	
	@Override
	public void realizarPago(double monto) {
		double descuento=15,desc=0;
		desc = (descuento / 100)*monto;
		monto = monto - desc;
		this.montoPagado = monto;
	}

	@Override
	public void imprimirRecibo() {
		System.out.println("---Tarjeta---");
		System.out.println("Numero Tarjeta: "+getNumeroTarjeta());
		System.out.println("Fecha de pago: "+getFechaPago());
		System.out.println("Monto Pagado: "+getMontoPagado());
	}

}
