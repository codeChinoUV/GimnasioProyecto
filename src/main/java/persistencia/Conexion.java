/*
 *Crea una conexion a una base de datos
 * @author Jose Miguel Quiroz Benitez
 * @since 24/02/2019
 * @porpuse Crear una conexion a una base de datos 
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author chino
 * 
 */
public class Conexion {
    private Connection conexion;
    private String host = "localhost";
    private String db = "gimnasio";
    private String username = "miguel";
    private String password = "joseMiguel13";
    private String url = "jdbc:mysql://"+host+"/"+db+"?useSSL=false";
    String error;
    
    private static Conexion con;
    
    /**
     * El constructor crea una instancia de la clase "com.mysql.cj.jdbc.Driver" y se conecta a traves de la 
     * url especificada en el atributo, un usuario que se encuentra en el atributo username y la contraseña de
     * la base de datos que se encuentra en el atributo password. El constructor crea una "copia" que es asignada al
     * atributo con.
     */
    public Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Conectando a la base de datos...");
            try{
                conexion = DriverManager.getConnection(url, username, password);
                if(conexion != null){
                    System.out.println("Conexion establecida");
                }
            }catch(SQLException mysqlError){
                JOptionPane.showMessageDialog(null,"Error de la base de datos");
                mysqlError.printStackTrace();
            }
        }catch(ClassNotFoundException claseNoEncontrada){
            JOptionPane.showMessageDialog(null,"Error de la base de datos");
            claseNoEncontrada.printStackTrace();
        }catch(Exception excepcionGeneral){
            JOptionPane.showMessageDialog(null,"Error de la base de datos");
            excepcionGeneral.printStackTrace();
        }
        con = this;
    }
 
    /**
     * El constructor crea una instancia de la clase "com.mysql.cj.jdbc.Driver" y se conecta a traves de la 
     * url especificada en el parametro url, un usuario que se encuentra en el parametro username y la contraseña de
     * la base de datos que se encuentra en el parametro password. El constructor crea una "copia" que es asignada al
     * atributo con.
     * @param host Dirrecion en la cual se encuentra alojado el servidor de la base de datos a la cual se quiere acceder
     * @param db Nombre de la base de datos especifica a la cual se quiere acceder
     * @param username Nombre del usurio con el cual se va a loggear en la base de datos
     * @param  password Contraseña perteneciente a la cuenta de usuario.  
     */
    
    public Conexion(String host, String db, String username, String password) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        this.host = host;
        this.db = db;
        this.username = username;
        this.password = password;
        conexion = DriverManager.getConnection ("jdbc:mysql://" + host +"/"+db,username,password);
        con = this;
    }
     
    public Connection getCon(){
        return conexion;
    }
    
    
    /**
     * Cierra la conexion que se genera hacia la base de datos
     */
    public void close(){
 	try{
 	   conexion.close();
 	}catch(SQLException e){
 	    System.err.println ("Error: " + e.getMessage () + "\n" + e.getErrorCode ());
 	}    	   
    }
}


/*
*Error 1: Error en que no se encuentra la clase;
*/