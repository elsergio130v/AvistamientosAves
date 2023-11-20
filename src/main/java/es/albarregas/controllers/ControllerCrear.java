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
@WebServlet(name = "ControllerCrear", urlPatterns = {"/ControllerCrear"})
public class ControllerCrear extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //decir que estamos en metodo get.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        Connection conexion = null;
        Statement sentencia = null;
        ArrayList<Ave> lista = new ArrayList<Ave>();
        Boolean encontrado = false;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "";
        StringBuilder error = new StringBuilder();

        if (request.getParameter("enviar") != null) {
            String anilla = request.getParameter("anilla");
            String especie = request.getParameter("especie");
            String lugar = request.getParameter("lugar");
            String fecha = request.getParameter("fecha");
            //comprobamos que los parametros introducidos son los correctos, si no son entra aqui y guarda los valores que sean correctos.
            if (anilla.isEmpty() || anilla.length() > 3 || especie.isEmpty() || lugar.isEmpty() || fecha.isEmpty()) {
                if (anilla.isEmpty()) {
                    error.append("debes de rellenar el campo anilla <->");
                }
                if (anilla.length() > 3) {
                    error.append("el valor de la casilla es superior al permitido, como maximo 3 caracteres <->");
                }
                if (especie.isEmpty()) {
                    error.append("debes de rellenar el campo especie <->");
                }
                if (lugar.isEmpty()) {
                    error.append("debes de rellenar el campo lugar <->");
                }
                if (fecha.isEmpty()) {
                    error.append("debes de rellenar el campo fecha <->");
                }
                try {
                    //le metemos un convertidor personalizado para el tipo de dato Date en BeanUtils,
                    //de modo que cuando BeanUtils copie propiedades y encuentre una propiedad de tipo Date, 
                    //utilizará este convertidor personalizado para convertir el valor
                    Ave ave = new Ave();
                    FechaConverter converter = new FechaConverter();
                    BeanUtilsBean.getInstance().getConvertUtils().register((Converter) converter, Date.class);
                    BeanUtils.populate(ave, request.getParameterMap());
                    request.setAttribute("objetoCrear", ave);
                    url = "jspCrear/vistaCrear.jsp";
                } catch (InvocationTargetException | IllegalAccessException ex) {
                    Logger.getLogger(ControllerCrear.class.getName()).log(Level.SEVERE, null, ex);
                    Logger.getLogger(ControllerCrear.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                    Ave ave = new Ave();
                try {
                    //convertimos
                    FechaConverter converter = new FechaConverter();
                    BeanUtilsBean.getInstance().getConvertUtils().register((Converter) converter, Date.class);
                    BeanUtils.populate(ave, request.getParameterMap());
                    request.setAttribute("objetoCrear", ave);
                    url = "jspCrear/vistaCrear.jsp";
                } catch (InvocationTargetException | IllegalAccessException ex) {
                    Logger.getLogger(ControllerCrear.class.getName()).log(Level.SEVERE, null, ex);
                    Logger.getLogger(ControllerCrear.class.getName()).log(Level.SEVERE, null, ex);
                }

                lista = (ArrayList<Ave>) request.getSession().getAttribute("creacion");
                encontrado = Modelo.existeAnilla(lista,url,error,ave);
                /*for (Ave ave1 : lista) {
                    if (ave1.getAnilla().equals(ave.getAnilla())) {
                        url = "jspCrear/vistaCrear.jsp";
                        error.append("anilla ya contenida en la lista, introduce una distinta <->");
                        encontrado = true;
                    }
                }*/
                if (!encontrado) {
                    try {
                        
                    
                    conexion = Conexion.getConnection();
                    sql = " INSERT INTO pruebasjava.aves (anilla, especie, lugar, fecha) VALUES "
                            + "(\"" + ave.getAnilla() + "\", \"" + ave.getEspecie() + "\", \"" + ave.getLugar() + "\", \"" + Modelo.formatDate(ave.getFecha()) + "\");";
                    sentencia = conexion.createStatement();
                    int resul = sentencia.executeUpdate(sql);
                    url = "jspCrear/avisoCrear.jsp";
                    Conexion.closeConexion();
                    sentencia.close();
                    }catch (Exception e) {
                        }
                }
        }
    }

    request.setAttribute ("error", error);
    request.getRequestDispatcher (url).forward(request, response);
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
