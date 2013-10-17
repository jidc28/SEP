/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistemas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author jidc28
 */
public class SINAI extends org.apache.struts.action.ActionForm{
    private String usbid;
    private String nombre;
    private String fecha_inic;
    private String fecha_fin;

    public String getUsbid() {
        return usbid;
    }

    public void setUsbid(String usbid) {
        this.usbid = usbid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_inic() {
        return fecha_inic;
    }

    public void setFecha_inic(String fecha_inic) {
        this.fecha_inic = fecha_inic;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    
}
