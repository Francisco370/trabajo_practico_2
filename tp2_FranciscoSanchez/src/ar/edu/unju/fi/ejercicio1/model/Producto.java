package ar.edu.unju.fi.ejercicio1.model;

public class Producto {
	private int codigo;
	private String descripcion;
	private double precioUnit;
	private origenFab fab;
	private categoria cat;
	
	public enum origenFab{
		ARGENTINA, CHINA, BRASIL, URUGUAY
	}
	public enum categoria{
		TELEFONIA, INFORMATICA, ELECTROHOGAR, HERRAMIENTAS
	}
	
	public Producto() {
		
	}
	
	
	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", descripcion=" + descripcion + ", precioUnit=" + precioUnit + ", fab="
				+ fab + ", cat=" + cat + "]";
	}


	public Producto(int codigo, String descripcion, double precioUnit, origenFab fab, categoria cat) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precioUnit = precioUnit;
		this.fab = fab;
		this.cat = cat;
	}


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioUnit() {
		return precioUnit;
	}

	public void setPrecioUnit(double precioUnit) {
		this.precioUnit = precioUnit;
	}

	public origenFab getFab() {
		return fab;
	}

	public void setFab(origenFab fab) {
		this.fab = fab;
	}

	public categoria getCat() {
		return cat;
	}

	public void setCat(categoria cat) {
		this.cat = cat;
	}
	
	
}
