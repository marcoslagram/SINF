package sinf;

import java.util.Vector;

public class Actores implements Comparable<Actores>{
	private int ID, edad;
	private String nombre, nacionalidad;
	private Vector<Peliculas> actuaEn = new Vector<Peliculas>();
	
	public Actores(int ID, String nombre, String nacionalidad, int edad) {
		this.ID = ID;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
	}
	
	public void setActor(Peliculas pelicula) {
		this.actuaEn.add(pelicula);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	public String toString() {
		String stringID = Integer.toString(ID);
		String stringEdad = Integer.toString(edad);
		String actor = "Información sobre el Actor con el ID " + stringID + "\n"
				+ "Nombre: " + this.nombre + "\n" + "Edad: " + stringEdad + "\n"
				+ "Nacionalidad: " + this.nacionalidad;
		return actor;
	}
	
	@Override
	public int compareTo(Actores o) {
		if(getID() < o.getID()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	
}
