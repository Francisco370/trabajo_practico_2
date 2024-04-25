package ar.edu.unju.fi.ejercicio3.constantes;

public enum Provincia {
	JUJUY(811611, 53291.0), SALTA(1441351,155488.0), TUCUMAN(46044703,22524.0), CATAMARCA(429562,102602.0), LARIOJA(324226,5045.0), SANTIAGODELESTERO(1060906,136351.0);
	
	private int cantPoblacion;
	private double superficie;
	
	private Provincia(int cantPoblacion, double superficie) {
		this.cantPoblacion = cantPoblacion;
		this.superficie = superficie;
	}

	public int getCantPoblacion() {
		return cantPoblacion;
	}

	public void setCantPoblacion(int cantPoblacion) {
		this.cantPoblacion = cantPoblacion;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	
	public Double densidadPoblacion() {
		return this.cantPoblacion/this.superficie;
	}
}
