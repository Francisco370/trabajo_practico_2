package ar.edu.unju.fi.ejercicio5.model;

public class Producto {
	private int codigo;
	private String descripcion;
	private double precioUnit;
	private boolean estado;
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

	public Producto(int codigo, String descripcion, double precioUnit, boolean estado, origenFab fab, categoria cat) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precioUnit = precioUnit;
		this.estado = estado;
		this.fab = fab;
		this.cat = cat;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", descripcion=" + descripcion + ", precioUnit=" + precioUnit
				+ ", estado=" + estado + ", fab=" + fab + ", cat=" + cat + "]";
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
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
