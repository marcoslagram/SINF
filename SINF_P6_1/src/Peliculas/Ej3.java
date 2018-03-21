package Peliculas;

import java.util.Iterator;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;

public class Ej3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		Configuration config = Db4o.configure();
		@SuppressWarnings("deprecation")
		ObjectContainer db = Db4o.openFile(config,"Peliculas.db4o");
		
		ObjectSet<Peliculas> conjuntoPeliculas = db.queryByExample(new Peliculas(0, null, "Italia", 0));
		
		//System.out.println(conjuntoPeliculas);
		
		System.out.println("Consultando la base de datos...");
		System.out.println("Mostrando peliculas españolas...");
		System.out.println();
		
		Iterator<Peliculas> iterator = conjuntoPeliculas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		db.close();
		System.out.println("");
		System.out.println("Consulta terminada");
		return;
	}

}
