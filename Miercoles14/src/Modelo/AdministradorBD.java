
package Modelo;
import java.sql.*;
import javax.swing.JOptionPane;




public class AdministradorBD {
    Connection conexion = null;
    Statement sentencia = null;
   
    
    
    
    public void crear() 
    {
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:pyme.sqlite");
            sentencia = (Statement) conexion.createStatement();
            
            String SQL = "CREATE TABLE PRODUCTO" + 
                    "(ID        INT     PRIMARY KEY NOT NULL,"+ 
                    " NOMBRE    TEXT    NOT NULL," +
                    " PRECIO  INT     NOT NULL)";
            
            sentencia.executeUpdate(SQL);
            sentencia.close();
            conexion.close();
            
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.err.println("Error: " + e.getMessage());
            System.exit(0);
            //Termina la ejecución de la JVM    
        }
        
        System.out.println("Base de datos creada!");
        
    }

    public void insertar(int id, String nombre, int precio)
    {
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:pyme.sqlite");
            conexion.setAutoCommit(false);
            
            sentencia = conexion.createStatement();
            
            String SQL = "INSERT INTO PRODUCTO (ID, NOMBRE,PRECIO) " +
                    "VALUES ('"+id+"','"+nombre+"','"+precio+"')";
            
            sentencia.executeUpdate(SQL);
            sentencia.close();
            conexion.commit();
            conexion.close();
            
        }
        catch(ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null,"error:"+ e.getMessage(),"error", JOptionPane.ERROR_MESSAGE);
          
            System.exit(0);
        }
        JOptionPane.showInternalMessageDialog(null,"Datos insertados de forma correcta!");
        
    }

    public void mostrarDatos()
    {
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:pyme.sqlite");
            conexion.setAutoCommit(false);
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM PRODUCTO");
            
            while(resultado.next())
            {
                int id = resultado.getInt("ID");
                String nombre = resultado.getString("NOMBRE");
                int precio = resultado.getInt("PRECIO");
                
                System.out.println("ID          : " + id 
                        + "\nNOMBRE      : " + nombre 
                        + "\nPRECIO        : " + precio + "\n");  
            }
            
            resultado.close();
            sentencia.close();
            conexion.close();
             
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.err.println("Error : " + e.getMessage());
        }
        
    }
    public static void main(String []Args) throws SQLException{
        
        AdministradorBD abd =new AdministradorBD();
        abd.crear();
    }
}
