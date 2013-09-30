/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Langtech
 */
public class Usuario extends org.apache.struts.action.ActionForm {

    private static final String patronUsbid = "^[0-9][0-9]-[0-9][0-9][0-9][0-9][0-9]$";
    private String usbid;
    private String tipousuario;
    private String contrasena;
    private String departamento;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    // error message
    private String errorUsbid;
    private String errorContrasena;
    private String errorUsbidFormato;
    private Pattern patron;
    private Matcher match;

    public String getErrorUsbidFormato() {
        return errorUsbidFormato;
    }

    public void setErrorUsbidFormato(String errorUsbidFormato) {
        if (errorUsbidFormato.equals("")) {
            this.errorUsbidFormato = "";
        } else {
            this.errorUsbidFormato = "<span style='color:red'>Campo USBID formato incorrecto (Sugerencia: 09-10927).</span>";

        }
    }
    
    public Usuario() {
        patron = Pattern.compile(patronUsbid);
    }

    public boolean validate(final String carnet) {

        match = patron.matcher(carnet);
        return match.matches();
    }

    @Override
    public String toString() {
        return "" + "Usbid: " + usbid + ", Tipo de Usuario: " + tipousuario ;
    }

    public String getErrorContrasena() {
        return errorContrasena;
    }

    public void setErrorContrasena(String errorContrasena) {
        if (errorContrasena.equals("")) {
            this.errorContrasena = "";
        } else {
            this.errorContrasena = "<span style='color:red'>Por favor introduzca su Contrasena</span>";

        }
    }

    public String getErrorUsbid() {
        return errorUsbid;
    }

    public void setErrorUsbid(String errorUsbid) {
        if (errorUsbid.equals("")) {
            this.errorUsbid = "";
        } else {
            this.errorUsbid = "<span style='color:red'>Por favor introduzca su Usbid</span>";
        }
    }

    public String getUsbid() {
        return usbid;
    }

    public void setUsbid(String usbid) {
        this.usbid = usbid;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        this.setErrorContrasena("");
        this.setErrorUsbid("");
        this.setErrorUsbidFormato("");

        if (getUsbid() == null || getUsbid().length() < 1) {
            this.setErrorUsbid("error");
            errors.add("usbid", new ActionMessage("error.usbid.required"));
            // TODO: add 'error.name.required' key to your resources
        }

        if (!validate(getUsbid())) {
            this.setErrorUsbidFormato("error");
            errors.add("usbid", new ActionMessage("error.usbid.malformulado"));
        }
        
        if (getContrasena() == null || getContrasena().length() < 1) {
            this.setErrorContrasena("error");
            errors.add("contrasena", new ActionMessage("error.contrasena.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
