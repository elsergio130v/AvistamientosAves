/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.albarregas.module;

import es.albarregas.bean.Ave;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author elser
 */
public class Modelo {
    
    public static ArrayList<Ave> rellenaLista(ResultSet resultado) throws SQLException{
    Ave ave = null;
    ArrayList<Ave>lista = new ArrayList<>();
      while (resultado.next()) {
                        ave = new Ave();
                        ave.setAnilla(resultado.getString(1));
                        ave.setEspecie(resultado.getString(2));
                        ave.setLugar(resultado.getString(3));
                        ave.setFecha(resultado.getDate(4));
                        lista.add(ave);
                    }
        return lista;
    }
    
        public static ArrayList<Ave> rellenaListaAniEsp(ResultSet resultado) throws SQLException{
    Ave ave = null;
    ArrayList<Ave>lista = new ArrayList<>();
      while (resultado.next()) {
                        ave = new Ave();
                        ave.setAnilla(resultado.getString(1));
                        ave.setEspecie(resultado.getString(2));
                        lista.add(ave);
                    }
        return lista;
    }

    public static boolean sonIguales(Ave ave1, Ave ave) {
        boolean bandera;
        if (ave.getAnilla().equals(ave1.getAnilla()) &&
            ave.getEspecie().equals(ave1.getEspecie())&&
                ave.getLugar().equals(ave1.getLugar())&&
                ave.getFecha().equals(ave1.getFecha())) {
            bandera = true;
        }else{ bandera = false;}
        return bandera;
    }
    
    public static boolean existeAnilla(ArrayList<Ave>lista,String url, StringBuilder error, Ave ave){
        boolean bandera;
                    for (Ave ave1 : lista) {
                    if (ave1.getAnilla().equals(ave.getAnilla())) {
                        url = "jspCrear/vistaCrear.jsp";
                        error.append("anilla ya contenida en la lista, introduce una distinta <->");
                        return true;
                    }
                }
                    return false;
    }
    
    public static String formatDate(Date date) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(date);
    }
    
    
    
}
