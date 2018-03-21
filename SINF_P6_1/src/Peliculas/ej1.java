package Peliculas;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;

public class ej1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		Configuration config = Db4o.configure();
		@SuppressWarnings("deprecation")
		ObjectContainer db = Db4o.openFile(config,"Peliculas.db4o");
		System.out.println("generando la db");
		Peliculas pelicula = new Peliculas(1, "Star Wars", "Estados Unidos", (float) 3.9);
		db.store(pelicula);
		Peliculas pelicula1 = new Peliculas(2, "La vida es bella", "Italia", (float) 4.6);
		db.store(pelicula1);
		Peliculas pelicula2 = new Peliculas(3, "Taxi", "Francia", (float) 4.0);
		db.store(pelicula2);
		Peliculas pelicula3 = new Peliculas(4, "Las vacaciones de Mr Bean", "Inglaterra", (float) 4.5);
		db.store(pelicula3);
		db.close();
		System.out.println("Generacion terminada");
		return;
	}

}
