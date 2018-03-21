package sinf;

import java.util.Iterator;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.query.Predicate;

public class Ej6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		Configuration config = Db4o.configure();
		@SuppressWarnings("deprecation")
		ObjectContainer db = Db4o.openFile(config,"Cine.db4o");
		
		@SuppressWarnings("serial")
		ObjectSet<Peliculas> resultadoBusqueda = db.query(new Predicate<Peliculas>(){
			
			public boolean match(Peliculas elementos){
				//Cambio las caracteristicas de la busqueda para tener un resultado
				if(elementos.getActores().size() > 2 && elementos.getTitulo().length() > 3) {
					return true;
				} else {
					return false;
				}
			}
		});
			
		Iterator<Peliculas> iterador = resultadoBusqueda.iterator();
		while(iterador.hasNext()){
			System.out.println(iterador.next().toString());
		}
		
		db.close();
		System.out.println("");
		System.out.println("Consulta terminada");
		return;
	}
}
