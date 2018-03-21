package Peliculas;

public class Peliculas {
	
	private int ID;
	private String titulo, nacionalidad;
	private float interes;
	
	public Peliculas(int ID, String Titulo, String Nacionalidad, float Interes) {
		if ((Interes < 0) || (Interes > 5)) {
			System.out.println("La valoración debe ser un número entre 0.0 y 5.0");
			return;
		} else {
			this.ID = ID;
			this.titulo = Titulo;
			this.nacionalidad = Nacionalidad;
			this.interes = Interes;
		}
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTituto(String Titulo) {
		this.titulo = Titulo;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public void setNacionalidad(String Nacionalidad) {
		this.nacionalidad = Nacionalidad;
	}
	
	public float getInteres() {
		return interes;
	}
	
	public void setInteres(float Interes) {
		this.interes = Interes;
	}
	
	public String toString() {
		String stringID = Integer.toString(this.ID);
		String stringInteres = Float.toString(this.interes);
		String descripcion = "Descripcion de la Pelicula con el ID: " + stringID + "\n" + 
		"Titulo: " + this.titulo + "\n" + "Nacionalidad: " + this.nacionalidad + "\n" 
		+ "Interes: " + stringInteres + "\n";
		
		return descripcion;
	}

}
