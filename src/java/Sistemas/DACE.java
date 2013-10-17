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
public class DACE extends org.apache.struts.action.ActionForm{
    private String usbid;
    private String codigo;
    private String nombre;
    private String trimestre;
    private int ano;
    private int uno;
    private int dos;
    private int tres;
    private int cuatro;
    private int cinco;
    private int retirados;

    public String getUsbid() {
        return usbid;
    }

    public void setUsbid(String usbid) {
        this.usbid = usbid;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getUno() {
        return uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public int getDos() {
        return dos;
    }

    public void setDos(int dos) {
        this.dos = dos;
    }

    public int getTres() {
        return tres;
    }

    public void setTres(int tres) {
        this.tres = tres;
    }

    public int getCuatro() {
        return cuatro;
    }

    public void setCuatro(int cuatro) {
        this.cuatro = cuatro;
    }

    public int getCinco() {
        return cinco;
    }

    public void setCinco(int cinco) {
        this.cinco = cinco;
    }

    public int getRetirados() {
        return retirados;
    }

    public void setRetirados(int retirados) {
        this.retirados = retirados;
    }
    
    
}
