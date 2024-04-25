package ar.edu.unju.fi.ejercicio4.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import ar.edu.unju.fi.ejercicio4.constantes.Posicion;

public class Jugador {
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private float estatura;
	private float peso;
	private Posicion posicion;
	
	public Jugador() {
		
	}
	
	public int edadPersona(LocalDate fechaNacimiento) {
		LocalDate fechaActual = LocalDate.now();
		Period period = Period.between(fechaNacimiento, fechaActual);
		int edad = period.getYears();
		return edad;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		int edad = edadPersona(this.getFechaNacimiento());
		return "nombre y apellido=" + nombre + " " + apellido + ", fechaNacimiento=" + formatter.format(fechaNacimiento)
				+ ", edad=" + edad +", nacionalidad=" + nacionalidad + ", estatura=" + estatura + ", peso=" + peso + ", posicion="
				+ posicion;
	}


	public Jugador(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, float estatura,
			float peso, Posicion posicion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.estatura = estatura;
		this.peso = peso;
		this.posicion = posicion;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

}
