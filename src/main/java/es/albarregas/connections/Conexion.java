/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.albarregas.connections;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author elser
 */
public class Conexion {
    /*
    * Declaramos el pool de conexiones
    */
    static DataSource dataSource = null;
    static Connection conexion = null;

    public static Connection getConnection() {
        
        
        try {
            /*
            * Para buscar y acceder a un recurso defnido en el Servidor de Aplicaciones
            *   - Creamos el contexto de búsqueda mediante la clase InitialContext.
            *   - Realizamos la búsqueda del recurso haciendo el casting correspondiente con la sentecia lookup
            */
            Context contextoInicial = new InitialContext();
            dataSource = (DataSource) contextoInicial.lookup("java:comp/env/jdbc/sergio");
            conexion = dataSource.getConnection();
        } catch (NamingException | SQLException ex) {
            /*
            * Existe un error al intentar crear el pool de conexiones. Escribimos el logger y se visualiza error500.jsp
            */
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return conexion;
    }
    
    
    public static void closeConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
