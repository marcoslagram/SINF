import java.sql.*;
//import javax.swing.JOptionPane;

public class Conexion {
	private Connection conexion; // Abstrae una conexion a la base de datos
	private String usuario = "root"; // usuario con permisos para conectarse a Base de datos
	private String password = "98MSwin"; // contrasena del usuario que se puede conectar a la base de datos
	private String driver = "com.mysql.jdbc.Driver"; // Clase del Driver de jConnector
	private String baseDatos="jdbc:mysql://localhost:3306/Practica2";//cadena de coneccion
    private static Conexion instancia;
	
    /** Crea a new instancia de  Conexion */
    public static Conexion getInstancia (){
        if(Conexion.instancia==null){
        Conexion.instancia=new Conexion();
        }
        return instancia;
        }

        
	public static void main(String[] args) throws Exception{
		Conexion.getInstancia().conectar();
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
