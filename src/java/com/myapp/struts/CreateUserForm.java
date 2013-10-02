/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

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
public class CreateUserForm extends org.apache.struts.action.ActionForm {
    
    private static final String patronUsbid = "^[0-9][0-9]-[0-9][0-9][0-9][0-9][0-9]$";
    private String usbid;
    private String contrasena1;
    private String contrasena2;
    // error message
    private String errorUsbid;
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
    private String errorContrasena;
    private String errorDifierePass;
    
    
    
    
    public CreateUserForm() {
        patron = Pattern.compile(patronUsbid);
    }

    public boolean validate(final String carnet) {

        match = patron.matcher(carnet);
        return match.matches();
    }
    

    public String getErrorUsbid() {
        return errorUsbid;
    }

    public void setErrorUsbid(String errorUsbid) {
        if (errorUsbid.equals("")) {
            this.errorUsbid = "";
        } else {
            this.errorUsbid = "<span style='color:red'>Campo USBID obligatorio.</span>";

        }
    }

    public String getErrorContrasena() {
        return errorContrasena;
    }

    public void setErrorContrasena(String errorContrasena) {
        if (errorContrasena.equals("")) {
            this.errorContrasena = "";
        } else {
            this.errorContrasena = "<span style='color:red'>Campo Contrasena obligatorio.</span>";

        }
    }

    public String getErrorDifierePass() {
        return errorDifierePass;
    }

    public void setErrorDifierePass(String errorDifierePass) {
        if (errorDifierePass.equals("")) {
            this.errorDifierePass = "";
        } else {
            this.errorDifierePass = "<span style='color:red'>Las Contrasenas no coinciden.</span>";

        }
    }
    

    public String getUsbid() {
        return usbid;
    }

    public void setUsbid(String usbid) {
        this.usbid = usbid;
    }

    public String getContrasena1() {
        return contrasena1;
    }

    public void setContrasena1(String contrasena1) {
        this.contrasena1 = contrasena1;
    }

    public String getContrasena2() {
        return contrasena2;
    }

    public void setContrasena2(String contrasena2) {
        this.contrasena2 = contrasena2;
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
        this.setErrorContrasena("");
        this.setErrorUsbid("");
        this.setErrorUsbidFormato("");
        this.setErrorDifierePass("");
        
        if (getUsbid() == null || getUsbid().length() < 1) {
            this.setErrorUsbid("error");
            errors.add("usbid", new ActionMessage("error.usbid.required"));
            // TODO: add 'error.name.required' key to your resources
        }
            
        if (!validate(getUsbid())) {
            this.setErrorUsbidFormato("error");
            errors.add("usbid", new ActionMessage("error.usbid.malformulado"));
        }
        
        if (getContrasena1() == null || getContrasena1().length() < 1 || 
            getContrasena2() == null || getContrasena2().length() < 1) {
            this.setErrorContrasena("error");
            errors.add("contrasena", new ActionMessage("error.contrasena.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        
        if (!(getContrasena1().equals(getContrasena2()))) {
            this.setErrorDifierePass("error");
            errors.add("difierePass", new ActionMessage("error.contrasenasNoCoinciden"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
