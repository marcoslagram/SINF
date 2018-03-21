//import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.*;

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
		String sql = "use Practica3;"; //O usar Practica2
		int ok = stmt.executeUpdate(sql);
		System.out.println(sql);
        System.out.println("Query OK, "+ok+" row afected");
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String cadena = "", tabla, id, imagen;
		
		while (!cadena.equalsIgnoreCase("3")) {
			System.out.println("\n Desea guardar o abrir una imagen?");
			System.out.println("1 - Guardar");
			System.out.println("2 - Abrir");
			System.out.println("3 - SALIR");
			cadena = br.readLine();
			switch (Integer.parseInt(cadena)) {
				case 1: 
					System.out.println("Escoja una de las tablas:");
					System.out.println("Actores o Directores?");
					tabla = cadena = br.readLine();
					System.out.println("Escriba el ID_Actor/ID_Director que desee:");
					id = cadena = br.readLine();
					System.out.println("Nombre del archivo de imagen? (*.jpg)");
					imagen = cadena = "/home/marcos/" + br.readLine();
					GuardarImagen(conexion, tabla, id, imagen);
					break;
				case 2:
					System.out.println("Abrir de Actores o Directores?");
					tabla = cadena = br.readLine();
					System.out.println("Escoja uno de los ID:");
					System.out.println("ID_Actor o ID_Director");
					id = cadena = br.readLine();
					System.out.println("Con qué nombre desea guardar la imagen? (nombre.jpg)");
					imagen = cadena = br.readLine();
					AbrirImagen(stmt, tabla, id, imagen);
					break;
				case 3:
					//return;
					break;
					
			}
		}

        //conexion.rollback();
		
		stmt.close();
		conexion.close();
	
	}
	
	

	/** Metodo que guarda el archivo de imagen JPEG */
	private static void GuardarImagen(Connection conexion, String tabla, String id, String imagen) throws SQLException, IOException {
		String sql = "UPDATE "+tabla+" set foto = (?) where ID_Actor = " + id;
		File archivo = new File(imagen);
		FileInputStream fst = new FileInputStream(archivo);
		PreparedStatement ps = conexion.prepareStatement(sql);
		ps.setBinaryStream(1, fst, (int) archivo.length());
		int ok = ps.executeUpdate();
		System.out.println("Query OK, " + ok + " row affected");
		fst.close();
		ps.close();		
	}
	
	/** Metodo que abre el archivo de imagen JPEG */
	private static void AbrirImagen(java.sql.Statement stmt, String tabla, String id, String imagen) throws SQLException, IOException {
		ResultSet rs = stmt.executeQuery("SELECT foto from " + tabla + " where ID_Actor = " + id + ";");
		
//		int i = 1;
		
		while(rs.next()) {
			InputStream is = rs.getBinaryStream(1);
			OutputStream os = new FileOutputStream(new File(imagen));
//			i++;
			int c = 0;
			while((c = is.read()) > -1) {
				os.write(c);
			}
			System.out.println("Imagen recuperada correctamente");
			os.close();
			is.close();
		}
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
