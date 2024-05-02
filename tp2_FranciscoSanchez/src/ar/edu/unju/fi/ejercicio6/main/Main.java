package ar.edu.unju.fi.ejercicio6.main;

import ar.edu.unju.fi.ejercicio6.interfaces.funcionales.Converter;
import ar.edu.unju.fi.ejercicio6.model.FelinoDomestico;
import ar.edu.unju.fi.ejercicio6.model.FelinoSalvaje;

public class Main {

	public static void main(String[] args) {
		//Convertir de felino domestico a felino salvaje
		System.out.println("De domestico a salvaje: ");
		FelinoDomestico gato = new FelinoDomestico("Garfield", (byte)45, 12f);
		Converter<FelinoDomestico, FelinoSalvaje> converter = x -> new FelinoSalvaje(x.getNombre(), x.getEdad(), x.getPeso());
		FelinoSalvaje felino1 = converter.convert(gato);
		converter.mostrarObjeto(felino1);
		
		//Convertir de felino salvaje a felino domestico
		System.out.println("De salvaje a domestico: ");
		//para ver que funciona el isNotNull, FelinoSalvaje gato2 = null;
		FelinoSalvaje gato2 = new FelinoSalvaje("Tanner", (byte)20, 186f);
		boolean validar = Converter.isNotNull(gato2);
		if(validar == true) {
			Converter<FelinoSalvaje, FelinoDomestico> converter2 = y -> new FelinoDomestico(y.getNombre(), y.getEdad(), y.getPeso());
			FelinoDomestico felino2 = converter2.convert(gato2);
			converter2.mostrarObjeto(felino2);
		}else
			System.out.println("El objeto a convertir es nulo");
	}

}
