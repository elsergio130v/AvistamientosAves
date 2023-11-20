/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import es.albarregas.bean.Ave;
import es.albarregas.connections.Conexion;
import es.albarregas.module.Modelo;
import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author elser
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //desarrollar vista para que se sepa que esamos en get
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        HttpSession sesion = request.getSession();
        //PreparedStatement preparada = null;
        Ave ave;
        ArrayList<Ave> lista = new ArrayList<Ave>();
        String url = "";

        if (request.getParameter("enviar") != null) {
            //dependiendo de lo que quiera el usuario entramos en uno de los siguientes casos.

            switch (request.getParameter("enviar")) {
                case "crear":
                    try {
                    conexion = Conexion.getConnection();
                    sentencia = conexion.createStatement();
                    resultado = sentencia.executeQuery("SELECT * FROM pruebasjava.aves; ");
                    //llamamos al metodo que recorre el resultado y va rellenando la lista
                    lista = Modelo.rellenaLista(resultado);
                    sesion.setAttribute("creacion", lista);
                    Conexion.closeConexion();
                    sentencia.close();
                    resultado.close();
                    url = "jspCrear/vistaCrear.jsp";
                } catch (SQLException ex) {
                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                }         
                    break;
                case "leer":
                try {
                    conexion = Conexion.getConnection();
                    sentencia = conexion.createStatement();
                    resultado = sentencia.executeQuery("SELECT * FROM pruebasjava.aves; ");
                    //llamamos al metodo que recorre el resultado y va rellenando la lista
                    lista = Modelo.rellenaLista(resultado);
                    request.setAttribute("aves", lista);
                    url = "jspLeer/vistaLectura.jsp";
                    Conexion.closeConexion();
                    sentencia.close();
                    resultado.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

                case "actualizar":
                    try {
                    conexion = Conexion.getConnection();
                    sentencia = conexion.createStatement();
                    resultado = sentencia.executeQuery("SELECT * FROM pruebasjava.aves; ");  
                    lista = Modelo.rellenaLista(resultado);
                    sesion.setAttribute("actualizar", lista);
                    url = "jspActualizar/vistaActualizar.jsp";
                    Conexion.closeConexion();
                    sentencia.close();
                    resultado.close();
                    
                } catch (SQLException e) {
                }
                    

                    break;
                case "eliminar":
                           try {
                    conexion = Conexion.getConnection();
                    sentencia = conexion.createStatement();
                    resultado = sentencia.executeQuery("SELECT * FROM pruebasjava.aves; ");  
                    lista = Modelo.rellenaLista(resultado);
                    sesion.setAttribute("eliminacion", lista);
                    url = "jspEliminar/vistaEliminar.jsp";
                    Conexion.closeConexion();
                    sentencia.close();
                    resultado.close();
                    
                } catch (SQLException e) {
                }
                        
                    

                    break;
                default:
                    throw new AssertionError();
            }

        }
        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
