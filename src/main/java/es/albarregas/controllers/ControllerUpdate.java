/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controllers;

import es.albarregas.bean.Ave;
import es.albarregas.connections.Conexion;
import es.albarregas.module.FechaConverter;
import es.albarregas.module.Modelo;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author elser
 */
@WebServlet(name = "ControllerUpdate", urlPatterns = {"/ControllerUpdate"})
public class ControllerUpdate extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        Ave ave = new Ave();
        String url = "";
        ArrayList<Ave> lista = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        //en caso de cancelar
        if (request.getParameter("volver") != null) {
            url = "index.jsp";
        }
        //aqui recibimos los datos de la vistaActualizar
        if (request.getParameter("enviar") != null) {
            String radioButton = request.getParameter("elige");
            //recogemos el valor del input radio, y recorremos la lista en busca de dicha anilla
            //para obtener el objeto entero. y pasarselo a la vista del form.
            lista = (ArrayList<Ave>) request.getSession().getAttribute("actualizar");
            for (Ave ave1 : lista) {
                if (ave1.getAnilla().equals(radioButton)) {
                    ave = ave1;
                    url = "jspActualizar/formActualizar.jsp";
                }
            }
            request.setAttribute("aveActualizar", ave);
        }
        //aqui gestionamos la respuesta del formActualizar
        if (request.getParameter("enviarForm") != null) {
            try {
                //le metemos un convertidor personalizado para el tipo de dato Date en BeanUtils,
                //de modo que cuando BeanUtils copie propiedades y encuentre una propiedad de tipo Date, 
                //utilizará este convertidor personalizado para convertir el valor
                FechaConverter converter = new FechaConverter();
                BeanUtilsBean.getInstance().getConvertUtils().register((Converter) converter, Date.class);
                BeanUtils.populate(ave, request.getParameterMap());
                request.setAttribute("objetoCrear", ave);
                url = "jspCrear/vistaCrear.jsp";
            } catch (InvocationTargetException | IllegalAccessException ex) {
                Logger.getLogger(ControllerCrear.class.getName()).log(Level.SEVERE, null, ex);
                Logger.getLogger(ControllerCrear.class.getName()).log(Level.SEVERE, null, ex);
            }

            lista = (ArrayList<Ave>) request.getSession().getAttribute("actualizar");
            //recorremos la lista que tenemos en sesion, buscamos que coincidan las anillas con el objeto del formulario y la lista
            // en ese caso comparamos y comprobamos si los dos objetos son iguales o ha habido cierta variacion, dependiendo de esto,
            //lanza a la siguiente vista aviso si se ha realizado el cambio o no.
            for (Ave ave1 : lista) {
                if (ave1.getAnilla().equals(ave.getAnilla())) {

                    if (Modelo.sonIguales(ave1, ave)) {
                        url = "jspActualizar/avisoActualizar.jsp";
                        request.setAttribute("actualizado", "No se ha realizado cambios, ya que el objeto era el mismo");
                    } else {
                        url = "jspActualizar/avisoActualizar.jsp";
                        request.setAttribute("actualizado", "se ha actualizado el ave de anilla " + ave.getAnilla());
                        
                        String sql = "Update aves set especie = \"" + ave.getEspecie() + "\", lugar = \"" + ave.getLugar() + "\", fecha = \"" + Modelo.formatDate(ave.getFecha()) + "\" where anilla = \"" + ave.getAnilla() + "\";";
                        //Update aves set especie = "ciguenaa", lugar = "lol", fecha = "2023-11-25" where anilla = "001";
                        conexion = Conexion.getConnection();
                        try {
                            sentencia = conexion.createStatement();
                            int resul = sentencia.executeUpdate(sql);
                        } catch (SQLException ex) {
                            Logger.getLogger(ControllerUpdate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
