package Peliculas;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;

public class Ej4 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		//boolean continuar = true;
		int orden;
		
		System.out.println("Accediendo a la base de datos");
		@SuppressWarnings("deprecation")
		Configuration config = Db4o.configure();
		@SuppressWarnings("deprecation")
		ObjectContainer db = Db4o.openFile(config,"Peliculas.db4o");
		
		//ObjectSet<Peliculas> conjuntoPeliculas = db.queryByExample(Peliculas.class);
		
		
		System.out.println("Sistema de gestion de base de datos de Peliculas");
		System.out.println("Escriba la orden que quiera");
		while(true) {
			System.out.println("1 - Agregar pelicula");
			System.out.println("2 - Eliminar pelicula");
			System.out.println("3 - Editar pelicula");
			System.out.println("4 - Ver listado de peliculas");
			System.out.println("5 - SALIR");
			System.out.println("¿Opcion?");
			
			orden = sc.nextInt();
			
			switch(orden) {
				case 1: {
					agregar(db, sc);
					break;
				}
				
				case 2: {
					eliminar(db, sc);
					break;
				}
				
				case 3: {
					editar(db, sc);
					break;
				}
				
				case 4: {
					ver(db);
					break;
				}
				
				case 5: {
					db.close();
					System.out.println("");
					System.out.println("Ha salido");
					return;
				}
				
				default: {
					System.out.println("Opción inválida");
					break;
				}
			}
		}
	}
	
	public static void agregar(ObjectContainer db, Scanner sc) {
		System.out.println("Bienvenido al asistente para agregar Pelicula");
		System.out.println("Por favor siga los pasos");
		System.out.println();
		System.out.println("Escriba el ID de la pelicula: ");
		int id = sc.nextInt();
		System.out.println("Escriba el titulo de la película: ");
		String titulo = sc.next();
		System.out.println("Escriba la nacionalidad de la pelíucla: ");
		String pais = sc.next();
		System.out.println("Escriba el interés de la película: ");
		float interes = Float.parseFloat(sc.next());
		
		db.store(new Peliculas(id, titulo, pais, interes));
		
		System.out.println("Se ha agregado la película correctamente.");
	}
	
	public static void eliminar(ObjectContainer db, Scanner sc) {
		System.out.println("Bienvenido al asistente para eliminar Películas");
		System.out.println("Por favor siga los pasos");
		
		System.out.println("Introduzca el ID de la películas que quiera eliminar: ");
		int id = Integer.parseInt(sc.next());
		
		ObjectSet<Peliculas> conjuntoPeliculas = db.queryByExample(new Peliculas(id, null, null, 0));
		
		if(conjuntoPeliculas.isEmpty()) {
			System.out.println("No se ha encontrado ninguna película con ese ID");
			return;
		} else {
			Iterator<Peliculas> iterador = conjuntoPeliculas.iterator();
			while (iterador.hasNext()) {
				Peliculas pelicula = iterador.next();
				System.out.println("Va a borrar la película: " +  pelicula.toString());
				db.delete(pelicula);
			}
		}
	}
	
	public static void editar(ObjectContainer db, Scanner sc) {
		System.out.println("Bienvenido al asistente para editar películas");
		System.out.println("Por favor siga los pasos:");
		
		System.out.println("Introduzca el ID de la películas que quiera editar: ");
		int id = Integer.parseInt(sc.next());
		
		ObjectSet<Peliculas> conjuntoPeliculas = db.queryByExample(new Peliculas(id, null, null, 0));
		
		if(conjuntoPeliculas.isEmpty()) {
			System.out.println("No se ha encontrado ninguna película con ese ID");
			return;
		} else {
			System.out.println("Introduzca los datos. Si no quere cambiar un dato pulse enter");
			
			System.out.println("Escriba el Titulo: ");
			String titulo = sc.next();
			if(titulo.trim().equals("")) titulo = null;
			
			System.out.println("Escriba la nacionalidad: ");
			String pais = sc.next();
			if(pais.trim().equals("")) pais = null;
			
			System.out.println("Escriba el interés (número): ");
			String cadenaInteres = sc.next();
			if(cadenaInteres.trim().equals("")) cadenaInteres = "0";
			float interes = Float.parseFloat(cadenaInteres);
			
			Iterator<Peliculas> iterador = conjuntoPeliculas.iterator();
			while (iterador.hasNext()) {
				Peliculas pelicula = iterador.next();
				if (titulo != null) pelicula.setTituto(titulo);
				if (pais != null) pelicula.setNacionalidad(pais);
				if (interes != 0) pelicula.setInteres(interes);
				db.store(pelicula);
				System.out.println("Se ha modificado la película con los siguientes datos: " +  pelicula.toString());
			}
		}
	}
	
	public static void ver(ObjectContainer db) {
		ObjectSet<Peliculas> conjuntoPeliculas = db.queryByExample(Peliculas.class);
		
		Iterator<Peliculas> iterator = conjuntoPeliculas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}

