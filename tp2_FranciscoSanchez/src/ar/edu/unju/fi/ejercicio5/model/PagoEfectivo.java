package ar.edu.unju.fi.ejercicio5.model;

import java.time.LocalDate;

import ar.edu.unju.fi.interfaces.Pago;

public class PagoEfectivo implements Pago {
	private double montoPagado;
	private LocalDate fechaPago;
	
	public PagoEfectivo() {
		
	}
	
	
	public PagoEfectivo(double montoPagado, LocalDate fechaPago) {
		this.montoPagado = montoPagado;
		this.fechaPago = fechaPago;
	}

	
	
	public double getMontoPagado() {
		return montoPagado;
	}


	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}


	public LocalDate getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}


	@Override
	public void realizarPago(double monto) {
		double descuento=10,desc=0;
		desc = (descuento / 100)*monto;
		monto = monto - desc;
		this.montoPagado = monto;
	}

	@Override
	public void imprimirRecibo() {
		System.out.println("---Efectivo---");
		System.out.println("Fecha de pago: "+getFechaPago());
		System.out.println("Monto Pagado: "+getMontoPagado());
	}

}
