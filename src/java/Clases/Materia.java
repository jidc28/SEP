/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

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
public class Materia extends org.apache.struts.action.ActionForm {
    
    private String codigo;
    private String viejoCodigo;
    private String nombre;
    private String estado;
    
    public String getViejoCodigo() {
        return viejoCodigo;
    }

    public void setViejoCodigo(String viejoCodigo) {
        this.viejoCodigo = viejoCodigo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
    
        if (getCodigo() == null || getCodigo().length() < 1) {
            errors.add("codigo", new ActionMessage("error.codigo.required"));
        }
        
        if (getNombre() == null || getNombre().length() < 1) {
            errors.add("nombre", new ActionMessage("error.nombre.required"));
        }
        
        return errors;
    }
    
}
