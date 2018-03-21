package sinf;

import java.util.Iterator;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.query.Query;

public class Ej7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		Configuration config = Db4o.configure();
		@SuppressWarnings("deprecation")
		ObjectContainer db = Db4o.openFile(config,"Cine.db4o");
		
		System.out.println("Consultando la base de datos...");
		
		Query consulta = db.query();
		
		consulta.descend("nacionalidad").constrain("Estadounidense").not().and(consulta.constrain(Actores.class).or(consulta.constrain(Directores.class)));
		
		ObjectSet<Object> resultado = consulta.execute();
		Iterator<Object> iterador = resultado.iterator();
		while(iterador.hasNext()) {
			System.out.println(iterador.next().toString());
			System.out.println("");
		}
		
		db.close();
		System.out.println("");
		System.out.println("Consulta terminada");
		return;
	}

}
