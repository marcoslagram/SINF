package sinf;

import java.util.Vector;

public class Peliculas implements Comparable<Peliculas>{

	private Directores director;
	private Vector<Actores> actores = new Vector<Actores>();
	private String titulo, nacionalidad;
	private int ID;

	public Peliculas (int ID, String Titulo, String Nacionalidad, Directores director, Vector<Actores> actores) {
		this.ID = ID;
		this.titulo = Titulo;
		this.nacionalidad = Nacionalidad;
		this.director = director;
		this.actores = actores;
	}
	
	public Peliculas (int ID, String Titulo, String Nacionalidad, Directores director, Actores actor) {
		this.ID = ID;
		this.titulo = Titulo;
		this.nacionalidad = Nacionalidad;
		this.director = director;
		this.actores.add(actor);
	}

	public Directores getDirector() {
		return director;
	}

	public void setDirector(Directores director) {
		this.director = director;
	}

	public Vector<Actores> getActores() {
		return actores;
	}

	public void setActores(Vector<Actores> actores) {
		this.actores = actores;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}
	
	public String toString() {
		String id = Integer.toString(ID);
		String pelicula = "Descripción de la película con el ID: " + id + "\n"
				+ "Título: " + this.titulo + "\n"
				+ "Nacionalidad: " + this.nacionalidad + "\n"
				+ "Director: " + this.director.getNombre() + "\n"
				+ "Actores: " + this.actores.size() + "\n";
		return pelicula;
	}
	
	@Override
	public int compareTo(Peliculas pelicula) {
		if(getID() < pelicula.getID()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	
	
	
}
