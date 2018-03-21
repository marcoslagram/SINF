package Peliculas;

import java.util.Iterator;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;

public class Ej2 {
	public static void main(String args[]) {
		@SuppressWarnings("deprecation")
		Configuration config = Db4o.configure();
		@SuppressWarnings("deprecation")
		ObjectContainer db = Db4o.openFile(config,"Peliculas.db4o");
		
		ObjectSet<Peliculas> conjuntoPeliculas = db.queryByExample(Peliculas.class);
		
		System.out.println("Consultando la base de datos...");
		
		Iterator<Peliculas> iterator = conjuntoPeliculas.iterator();
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		//System.out.println(conjuntoPeliculas.toString());
		
		db.close();
		System.out.println("");
		System.out.println("Consulta terminada");
		return;
	}
}
