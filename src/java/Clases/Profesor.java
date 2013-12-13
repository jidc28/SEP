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
public class Profesor extends org.apache.struts.action.ActionForm {

    private static final String patronEmail = "^([a-zA-Z0-9]+(-|_)*[a-zA-Z0-9]*@[a-zA-Z]+.[a-zA-Z]+)*$";
    private String usbid;
    private String nombre;
    private String apellido;
    private String cedula;
    private String genero;
    private String email;
    private String email_personal;
    private String nivel;
    private String[] niveles = {"Ayudante Academico", "Asistente", "Agregado", "Asociado", "Titular"};
    private String jubilado;
    private String lapso_contractual_inicio;
    private String lapso_contractual_fin;
    private String errorEmailFormato;
    private Pattern patron;
    private Matcher match;

    public String[] getNiveles() {
        return niveles;
    }

    public String getErrorEmailFormato() {
        return errorEmailFormato;
    }

    public void setErrorEmailFormato(String errorEmailFormato) {
        if (errorEmailFormato.equals("")) {
            this.errorEmailFormato = "";
        } else {
            this.errorEmailFormato = "<span style='color:red'>Campo Email con formato incorrecto (Sugerencia: nombre@dominio.com).</span>";

        }
    }

    public String getEmail_personal() {
        return email_personal;
    }

    public void setEmail_personal(String email_personal) {
        this.email_personal = email_personal;
    }

    public Profesor() {
        patron = Pattern.compile(patronEmail);
    }

    public boolean validate(final String carnet) {

        match = patron.matcher(carnet);
        return match.matches();
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getJubilado() {
        return jubilado;
    }

    public void setJubilado(String jubilado) {
        this.jubilado = jubilado;
    }

    public String getLapso_contractual_inicio() {
        return lapso_contractual_inicio;
    }

    public void setLapso_contractual_inicio(String lapso_contractual_inicio) {
        this.lapso_contractual_inicio = lapso_contractual_inicio;
    }

    public String getLapso_contractual_fin() {
        return lapso_contractual_fin;
    }

    public void setLapso_contractual_fin(String lapso_contractual_fin) {
        this.lapso_contractual_fin = lapso_contractual_fin;
    }

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();


        this.setErrorEmailFormato("");

        try {
            Integer.parseInt(getCedula());
        } catch (Exception e) {
            e.printStackTrace();
            errors.add("cedula", new ActionMessage("error.cedula.malformulada"));

        }

        if (!validate(getEmail())) {
            errors.add("email", new ActionMessage("error.email.malformulado"));
        }

        if (!validate(getEmail_personal())) {
            errors.add("email", new ActionMessage("error.email.malformulado"));
        }

        return errors;
    }
}
