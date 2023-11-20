package es.albarregas.module;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

/**
 *
 * @author tomlu
 */
public class FechaConverter implements Converter {
    private final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object convert(Class type, Object o) {
        
          if(o == null || o.toString().isEmpty()) {
           return null;
       } else { // parse your date format with date formatter
           try {
               return formato.parse((String) o);
           } catch (ParseException e) {
               throw new RuntimeException(e);
           }
        }


    
        }
}