/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import es.albarregas.bean.Ave;
import es.albarregas.connections.Conexion;
import java.io.IOException;
import java.sql.Connection;
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

/**
 *
 * @author elser
 */
@WebServlet(name = "ControllerEliminar", urlPatterns = {"/ControllerEliminar"})
public class ControllerEliminar extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Ave> lista = new ArrayList<>();
        ArrayList<Ave> listaNueva = new ArrayList<>();
        String url="";
        StringBuilder error = new StringBuilder();
        
        if (request.getParameter("enviar")!=null) {
            //comprobamos que el usuario seleccion algun ave para eliminar,
            //si no le da el fallo y le devulve a la vista de selccion.
            String[]valoresCasillas = request.getParameterValues("elige");
            if (valoresCasillas!=null ) {
            lista = (ArrayList<Ave>)request.getSession().getAttribute("eliminacion");
            url = "jspEliminar/confirmarEliminar.jsp";
            for (String valoresCasilla : valoresCasillas) {
                for (Ave ave : lista) {
                    if (valoresCasilla.equals(ave.getAnilla())) {
                        listaNueva.add(ave);
                        break;
                    }
                }
            }       
            }else{
                            error.append("no seleccionaste ningun ave");
                url = "jspEliminar/vistaEliminar.jsp";
            }
        }
        if (request.getParameter("enviarConf")!=null) {
            try {
                listaNueva = (ArrayList<Ave>) request.getSession().getAttribute("borrar");
                StringBuilder sql = new StringBuilder();
                String sql1 = "delete from aves where anilla = ";
                sql.append("delete from aves where anilla in ( ");
                if (listaNueva.size()== 1) {
                    
                    //sql1.append(listaNueva.get(0).getAnilla()+";");
                }
                if (listaNueva.size()>1) {
                for (int i = 0; i < listaNueva.size(); i++) {
                    if ((i) == listaNueva.size()-1) {
                        sql.append(listaNueva.get(i).getAnilla()+");");
                    }else{
                        sql.append(listaNueva.get(i).getAnilla()+", ");
                    }
                }
                    
                }
                Connection conexion = Conexion.getConnection();                
                Statement sentencia = conexion.createStatement();
                int resul = sentencia.executeUpdate(sql.toString());
                url = "jspEliminar/avisoEliminar.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(ControllerEliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        request.setAttribute("error", error);
        request.getSession().setAttribute("borrar", listaNueva);
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
