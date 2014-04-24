/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Langtech
 * 
 * Archivo que extrae, utiliza y modifica elementos del código escrito por
 * Gustavo Ortega en el Sistema de Intercambio de SIGCOC.
 */
public class FileUpload extends org.apache.struts.action.ActionForm {
    private FormFile archivo;
    private ArrayList listArchivos;
    private Integer index;
    private String usbid;
    
    public FileUpload() {
        listArchivos = new ArrayList();
        this.index = 0;
    }

    public FormFile getArchivo(int indice){
        return this.archivo;
    }
    
    public void setArchivo(int indice, FormFile archivo){
        this.archivo = archivo;
        setListArchivos(archivo);
        this.index++;
    }
    
    public ArrayList getListArchivos() {
        return this.listArchivos;
    }
    
    public void setListArchivos(FormFile archivo){
        this.listArchivos.add(archivo);
    }

    public String getUsbid() {
        return usbid;
    }

    public void setUsbid(String usbid) {
        this.usbid = usbid;
    }
    
}
