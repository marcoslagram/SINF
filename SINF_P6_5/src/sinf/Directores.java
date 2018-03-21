package sinf;

import java.util.Vector;

public class Directores implements Comparable<Directores>{
	
	private int ID, edad;
	private String nombre, nacionalidad;
	private Vector<Peliculas> dirige = new Vector<Peliculas>();
	
	public Directores (int ID, String nombre, int edad, String nacionalidad) {
		this.ID = ID;
		this.edad = edad;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setPelicula(Peliculas pelicula) {
		this.dirige.add(pelicula);
	}
	
	public String toString() {
		String stringId = Integer.toString(ID);
		String stringEdad = Integer.toString(edad);
		String director = "Descripción del Director con el ID: " + stringId + "\n"
				+ "Nombre: " + this.nombre + "\n"
				+ "Edad: " + stringEdad + "\n"
				+ "Nacionalidad: " + this.nacionalidad + "\n";
		return director;
	}
	
	@Override
	public int compareTo(Directores o) {
		if(getID() < o.getID()) {
			return -1;
		} else {
			return 1;
		}
	}

}
