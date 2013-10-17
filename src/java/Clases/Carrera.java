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
 * @author admin
 */
public class Carrera extends org.apache.struts.action.ActionForm {
    
    private static final String patronCodigo = "^[0-9]+$";
    private static final String patronNombre = "[a-zA-Z]*$";
    private String codigo;
    private String nombre;
    private String Estado;
    private String errorCodigo;
    private String errorNombre;
    private String errorEstado;
    private String errorCodigoFormato;
    private String errorNombreFormato;
    private Pattern patron;
    private Pattern patron2;
    private Matcher match;
    

    public String getErrorCodigo() {
        return errorCodigo;
    }

    public void setErrorCodigo(String errorCodigo) {
        if (errorCodigo.equals("")) {
            this.errorCodigo = "";
        } else {
            this.errorCodigo = "<span style='color:red'>Por favor introduzca su Codigo de carrera</span>";

        }
    }

    public String getErrorNombre() {
        return errorNombre;
    }

    public void setErrorNombre(String errorNombre) {
        if (errorNombre.equals("")) {
            this.errorNombre = "";
        } else {
            this.errorNombre = "<span style='color:red'>Por favor introduzca el Nombre de la carrera</span>";

        }
    }

    public String getErrorEstado() {
        return errorEstado;
    }

    public void setErrorEstado(String errorEstado) {
        if (errorEstado.equals("")) {
            this.errorEstado = "";
        } else {
            this.errorEstado = "<span style='color:red'>Por favor introduzca el Estado de la carrera</span>";

        }
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
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

    public String getErrorUsbidFormato() {
        return errorCodigoFormato;
    }
    
    public String getErrorCodigoFormato() {
        return errorCodigoFormato;
    }

    public void setErrorCodigoFormato(String errorCodigoFormato) {
        if (errorCodigoFormato.equals("")) {
            this.errorCodigoFormato = "";
        } else {
            this.errorCodigoFormato = "<span style='color:red'>Campo Codigo formato incorrecto (Sugerencia: 0800).</span>";

        }
    }
    
    public String getErrorNombreFormato() {
        return errorNombreFormato;
    }

    public void setErrorNombreFormato(String errorNombreFormato) {
        if (errorNombreFormato.equals("")) {
            this.errorNombreFormato = "";
        } else {
            this.errorNombreFormato = "<span style='color:red'>Campo Nombre formato incorrecto (Sugerencia: sólo letras).</span>";

        }
    }
    
    public Carrera() {
        patron = Pattern.compile(patronCodigo);
        patron2 = Pattern.compile(patronNombre);
    }

    public boolean validate(final String carnet) {

        match = patron.matcher(carnet);
        return match.matches();
    }
    
    public boolean validate2(final String carnet) {

        match = patron2.matcher(carnet);
        return match.matches();
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
        this.setErrorCodigo("");
        this.setErrorNombre("");
        this.setErrorEstado("");
        this.setErrorCodigoFormato("");
        this.setErrorNombreFormato("");

        if (getCodigo() == null || getCodigo().length() < 1) {
            this.setErrorCodigo("error");
            errors.add("codigo", new ActionMessage("error.codigo.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        
        if (getNombre() == null || getNombre().length() < 1) {
            this.setErrorNombre("error");
            errors.add("nombre", new ActionMessage("error.nombre.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        
        
        
        return errors;
    }
}
