//import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Conexion {
	private static Connection conexion; // Abstrae una conexion a la base de datos
	private static String usuario = "root"; // usuario con permisos para conectarse a Base de datos
	private String password = "98MSwin"; // contrasena del usuario que se puede conectar a la base de datos
	private String driver = "com.mysql.jdbc.Driver"; // Clase del Driver de jConnector
	private String baseDatos="jdbc:mysql://localhost:3306/Practica2?useSSL=false";//cadena de coneccion
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
		String[] sql = new String[8];
		sql[0]= "use misPeliculas;";
        sql[1]="drop table if exists Peliculas;";
        sql[2]="drop table if exists Actores;";
        sql[3]="drop table if exists Directores;";

        sql[4]="CREATE TABLE IF NOT EXISTS Actores(" +
                "ID_Actor int not null PRIMARY KEY auto_increment, " +
                "NombreActor varchar(50) not null, " +
                "IMDB int unique, " +
                "Edad int not null, "+
                "CONSTRAINT chk_edad CHECK (Edad > -1 AND Edad < 121)"+
                ");";

        sql[5]="CREATE TABLE IF NOT EXISTS Directores(" +
                "ID_Director int not null PRIMARY KEY auto_increment, " +
                "NombreDirector varchar(50) not null, " +
                "IMDB int unique, " +
                "Edad int not null, " +
                "CONSTRAINT chk_edad CHECK (Edad > -1 AND Edad < 121)"+
                ");";

        sql[6]="CREATE TABLE IF NOT EXISTS Peliculas(" +
                "ID_Pelicula int not null PRIMARY KEY auto_increment, " +
                "Titulo varchar(50) not null, " +
                "ID_Director int not null, "+
                "IMDB int unique, " +
                "FOREIGN KEY (ID_Director) REFERENCES Directores(ID_Director)" +
                ");";
        
        sql[7]="CREATE TABLE ActuaEn (" +
                "ID_Actor int not null, " +
                "ID_Pelicula int not null, " +
                "FOREIGN KEY (ID_Actor) REFERENCES Actores(ID_Actor), "+
                "FOREIGN KEY (ID_Pelicula) REFERENCES Peliculas(ID_Pelicula) " +
                ");";
		
		// Hago la consulta metiendo las String de SQL
		for (int i = 0; i <sql.length ; i++) {
		   System.out.println(sql[i]);
		   stmt.executeUpdate(sql[i]);
		 }
		
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
