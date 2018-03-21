//import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Random;

import com.mysql.jdbc.Statement;

public class Conexion {
	private static Connection conexion; // Abstrae una conexion a la base de datos
	private static String usuario = "root"; // usuario con permisos para conectarse a Base de datos
	private String password = "98MSwin"; // contrasena del usuario que se puede conectar a la base de datos
	private String driver = "com.mysql.jdbc.Driver"; // Clase del Driver de jConnector
	private String baseDatos="jdbc:mysql://localhost:3306/P5EJ12?useSSL=false";//cadena de coneccion
    private static Conexion instancia;
    
    
	
    /** Crea a new instancia de  Conexion */
    public static Conexion getInstancia (){
        if(Conexion.instancia==null){
        Conexion.instancia=new Conexion();
        }
        return instancia;
        }

       /** METODO MAIN*/ 
	public static void main(String[] args) throws Exception{
		java.sql.Statement stmt = null;
	
	
	
		System.out.println("Conectando a la BD...");
		Conexion.getInstancia().conectar();
		
		System.out.println("Creando Statement...");
		stmt = conexion.createStatement();
		String[] sql = new String[2];
		sql[0]="use P5EJ12;";
		sql[1]="CREATE TABLE IF NOT EXISTS Datos (Indice int not null auto_increment, Enteros INT UNSIGNED, Caracteres char(50), PRIMARY KEY(Indice));";
//		sql[2]="INSERT Peliculas VALUES (NULL, 'La Cabina', 8, 0065513);";
//		sql[2]="INSERT Peliculas VALUES (NULL, 'Ninette', 8, 0442371);";
//		sql[3]="DELETE from Peliculas where Titulo = 'Ninette'";
		
		// Hago la consulta metiendo las String de SQL
		for (int i = 0; i <sql.length ; i++) {
            int ok = stmt.executeUpdate(sql[i]);
            System.out.println(sql[i]);
            System.out.println("Query OK, "+ok+" row afected ("+i+")");
        }
		
		//Meto 5000 datos generados aleatoriamente
		int numero_datos = 5000;
		for (int j = 0; j < numero_datos; j++) {
			String cadenaaleatoria = "";
			cadenaaleatoria = aleatorio();
			String comando = "INSERT Datos (Enteros, Caracteres) VALUES (" + (j + 10000)  + ", '"+ cadenaaleatoria + "');";
			//System.out.println(comando);
			int ok = stmt.executeUpdate(comando);
		}
		System.out.println("Se han introducido " + numero_datos + " entradas en la tabla");

        //conexion.rollback();
		
		stmt.close();
		conexion.close();
	
	}
	
	/** Metodo que se encarga de conectar a la base de datos*/
	public void conectar() throws Exception {
		//si la conecion es null nos conectamos
        if(this.getConexion()!=null){
        	System.out.println("Ya hay una conexion a la BD");
            return;
        } else if(this.getConexion() == null) {
        	try {
        		Class.forName(this.getDriver()) ; // obtine una istancia de la clase Diver
        		// establece la conexion con el Diver jconector y este a su vez con la base de datos
        		this.setConexion(DriverManager.getConnection(this.getBeseDatos(), this.getUsuario(), this.getPassword()));
        		System.out.println("Base de datos conectada");
        	} catch (SQLException ex) {
        		throw new Exception("ERROR AL CONECTARCE CON LA BASE DE DATOS");
        	} catch (ClassNotFoundException ex) {
                throw new Exception("Clase no encontrada");
           }
        }
	}
	
	public static String aleatorio() {
        char[] caracteres;
        caracteres = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y'};
        String cadena = "";
        for (int i = 0; i < 10; i++) {
            cadena += caracteres[new Random().nextInt(40)];
        }
        return cadena;
    }
	
	/** desconecta de la base de datos */
	public void desconectar()throws Exception{
	    if(!this.getConexion().isClosed())
	        this.setConexion(null);
	    System.out.println("Se ha desconectado de la BD");
	    }
		
		public Conexion() {
	    }
		
		/*Metodos getter y setter*/
		public Connection getConexion() {
	        return conexion;
	    }

	    public void setConexion(Connection conexion) {
	        this.conexion = conexion;
	    }

	    public String getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(String usuario) {
	        this.usuario = usuario;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getDriver() {
	        return driver;
	    }

	    public void setDriver(String driver) {
	        this.driver = driver;
	    }

	    public String getBeseDatos() {
	        return baseDatos;
	    }

	    public void setBeseDatos(String beseDatos) {
	        this.baseDatos = beseDatos;
	    }
}
