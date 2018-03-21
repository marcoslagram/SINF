//import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
		String[] sql = new String[31];
		sql[0]= "DROP DATABASE Practica2;";
        sql[1]="CREATE DATABASE Practica2;";
        sql[2]="USE Practica2;";

        sql[3]="CREATE TABLE IF NOT EXISTS Actores(" +
                "ID_Actor int not null PRIMARY KEY auto_increment, " +
                "NombreActor varchar(50) not null, " +
                "IMDB int unique, " +
                "Edad int not null, "+
                "CONSTRAINT chk_edad CHECK (Edad > -1 AND Edad < 121)"+
                ");";

        sql[4]="CREATE TABLE IF NOT EXISTS Directores(" +
                "ID_Director int not null PRIMARY KEY auto_increment, " +
                "NombreDirector varchar(50) not null, " +
                "IMDB int unique, " +
                "Edad int not null, " +
                "CONSTRAINT chk_edad CHECK (Edad > -1 AND Edad < 121)"+
                ");";

        sql[5]="CREATE TABLE IF NOT EXISTS Peliculas(" +
                "ID_Pelicula int not null PRIMARY KEY auto_increment, " +
                "Titulo varchar(50) not null, " +
                "ID_Director int not null, "+
                "IMDB int unique, " +
                "FOREIGN KEY (ID_Director) REFERENCES Directores(ID_Director)" +
                ");";
        
        sql[6]="CREATE TABLE ActuaEn (" +
                "ID_Actor int not null, " +
                "ID_Pelicula int not null, " +
                "FOREIGN KEY (ID_Actor) REFERENCES Actores(ID_Actor), "+
                "FOREIGN KEY (ID_Pelicula) REFERENCES Peliculas(ID_Pelicula) " +
                ");";
        
        sql[7]="INSERT Actores VALUES (NULL, 'José Mota', 1239566, 52);";
        sql[8]=	"INSERT Actores VALUES (NULL, 'Luis Tosar', 0869088, 46);";
        sql[9]="INSERT Actores VALUES (NULL, 'Dani Rovira', 3657043, 37);";
        sql[10]="INSERT Actores VALUES (NULL, 'Gracita Morales', 0602615, 66);";
        sql[11]="INSERT Actores VALUES (NULL, 'Goyo Jiménez', 1631846, 48);";
        
        sql[12]="INSERT Directores VALUES (NULL, 'Alex de la Iglesia', 0407067, 52);";
        sql[13]="INSERT Directores VALUES (NULL, 'Juanma Bajo Ulloa', 0048056, 51);";
        sql[14]="INSERT Directores VALUES (NULL, 'Fernando León de Aranoa', 0508208, 49);";
        sql[15]="INSERT Directores VALUES (NULL, 'Pedro Lazaga', 0002815, 61);";
        sql[16]="INSERT Directores VALUES (NULL, 'Daniel Monzón', 0600409, 49);";
        sql[17]="INSERT Directores VALUES (NULL, 'Santiago Segura', 0782213, 52);";
        sql[18]="INSERT Directores VALUES (NULL, 'Emilio Martínez-Lázaro', 0554880, 73);";
        
        sql[19]="INSERT Peliculas VALUES (NULL, 'El día de la bestia', 1, 0112922);";
        sql[20]="INSERT Peliculas VALUES (NULL, 'Celda 211', 5, 1242422);";
        sql[21]="INSERT Peliculas VALUES (NULL, 'Sor Citroën', 4, 0062290);";
        sql[22]="INSERT Peliculas VALUES (NULL, 'Torrente', 6, 0120868);";
        sql[23]="INSERT Peliculas VALUES (NULL, 'Ocho Apellidos Vascos', 7, 2955316);";
        sql[24]="INSERT Peliculas VALUES (NULL, 'Los lunes al sol', 3, 0319769);";
        sql[25]="INSERT Peliculas VALUES (NULL, 'Airbag', 2, 0115487);";
        
        sql[26]="INSERT ActuaEn VALUES(1,4);";
        sql[27]="INSERT ActuaEn VALUES(2,2);";
        sql[28]="INSERT ActuaEn VALUES(3,5);";
        sql[29]="INSERT ActuaEn VALUES(4,3);";
        sql[30]="INSERT ActuaEn VALUES(5,4);";
		
		// Hago la consulta metiendo las String de SQL
		for (int i = 0; i <sql.length ; i++) {
		   System.out.println(sql[i]);
		   int ok = stmt.executeUpdate(sql[i]);
		   System.out.println("Query OK, "+ok+" row afected ("+i+")");
		 }
		
		//Creo un lector de teclado para introducir las órdenes
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String cadena = "";
		
        //Hago un menu con las opciones
        while(!cadena.equalsIgnoreCase("0")){
            System.out.println("\nSeleccione una de las siguientes opciones:");
            System.out.println("  1.- Insertar en Directores.\n  2.- Insertar en Peliculas.\n  3.- Insertar en Actores.\n  0.- Salir");

            cadena = br.readLine();
            String sqlDinamica = null;
            String[] parametros;
            int ok;
            switch (Integer.parseInt(cadena)){

                case 1:
                    System.out.println("Introduzca los parametros a introducir de la siguiente manera:\nNombreDirector, imdb, Edad");
                    cadena=br.readLine();
                    parametros=cadena.split(",");

                    String nombreDir=parametros[0];
                    String imdbDir = parametros[1];
                    String edadDir = parametros[2];
                    sqlDinamica = "INSERT Directores VALUES (NULL,'"+nombreDir+"',"+imdbDir+","+edadDir+");";
                    System.out.println(sqlDinamica);
                    ok = stmt.executeUpdate(sqlDinamica);
                    System.out.println("Insert en Directores Válido Query OK, "+ok+" row afected");
                    break;

                case 2:
                    System.out.println("Introduzca los parametros a introducir de la siguiente manera:\nTítulo, ID_Director, IMDB");
                    cadena=br.readLine();
                    parametros=cadena.split(",");

                    String nombrePeli=parametros[0];
                    String ID_Director = parametros[1];
                    String imdbPel = parametros[2];
                    sqlDinamica="INSERT Peliculas VALUES (NULL, '"+nombrePeli+"', "+ID_Director+", "+imdbPel+");";
                    System.out.println(sqlDinamica);
                    ok = stmt.executeUpdate(sqlDinamica);
                    System.out.println("Insert en Peliculas Válido Query OK, "+ok+" row afected");
                    break;

                case 3:
                    System.out.println("Introduzca los parametros a introducir de la siguiente manera:\nNombreActor, imdb, Edad");
                    cadena=br.readLine();
                    parametros=cadena.split(",");

                    String nombreActor=parametros[0];
                    String imdbActor = parametros[1];
                    String edadAct = parametros[2];
                    //System.out.println(nombreActor+"', "+imdbActor+", "+ edadAct +);
                    sqlDinamica="INSERT Actores VALUES (NULL, '"+nombreActor+"', "+imdbActor+", "+edadAct+");";
                    System.out.println(sqlDinamica);
                    ok = stmt.executeUpdate(sqlDinamica);
                    System.out.println("Insert en Actores Válido Query OK, "+ok+" row afected");
                    break;

                default:

                    break;
            }
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
