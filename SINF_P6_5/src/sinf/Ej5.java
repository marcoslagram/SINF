package sinf;

import java.util.Vector;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;

public class Ej5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		Configuration config = Db4o.configure();
		@SuppressWarnings("deprecation")
		ObjectContainer db = Db4o.openFile(config,"Cine.db4o");
		System.out.println("generando la db");
		
		Directores director = new Directores(1, "Ron Howard", 64, "Estadounidense");
		db.store(director);
		
		Vector<Actores> listaactores = new Vector<Actores>();
		
		Actores actor = new Actores(1, "Daniel Brühl", "Español", 39);
		db.store(actor);
		listaactores.add(actor);
		
		actor = new Actores(2, "Chris Hemsworth", "Australiano", 34);
		db.store(actor);
		listaactores.add(actor);
		
		actor = new Actores(3, "Olivia Wilde", "Estadounidense", 24);
		db.store(actor);
		listaactores.add(actor);
		
		Peliculas pelicula = new Peliculas (1, "Rush", "Estadounidense", director, listaactores);
		db.store(pelicula);
		
		director = new Directores(2, "Stanley Kubric", 70, "Estadounidense");
		db.store(director);
		
		actor = new Actores(4, "Keir Dullea", "Estadounidense", 81);
		db.store(actor);
		
		pelicula = new Peliculas (2, "2001: A Space Odyssey", "Estadounidense", director, actor);
		db.store(pelicula);
		
		actor = new Actores(5, "Dennis Weaver", "Estadounidense", 81);
		db.store(actor);
		
		director = new Directores (3, "Seteven Spielberg", 71, "Estadounidense");
		db.store(director);
		
		pelicula = new Peliculas (3, "Duel", "Estadounidense", director, actor);
		db.store(pelicula);
		
		db.close();
		System.out.println("Generacion terminada");
		return;
		
	}

}
