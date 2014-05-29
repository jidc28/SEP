/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author smaf
 */
public class Dicta extends org.apache.struts.action.ActionForm {
    
    private String codigoMateria;
    private String numeroMateria;
    private ArrayList<Profesor> profesores = new ArrayList(0);
    private Profesor primerProfesor;
    private String usbidProfesor;
    private String opcion;
    private String periodo;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getUsbidProfesor() {
        return usbidProfesor;
    }

    public void setUsbidProfesor(String usbidProfesor) {
        this.usbidProfesor = usbidProfesor;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }
    
    public void setPrimerProfesor() {
        primerProfesor = profesores.get(0);
        profesores.remove(0);
    }
    
    public Profesor getPrimerProfesor() {
        return primerProfesor;
    }
    
    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }
    
    public void addProfesor(Profesor p){
        this.profesores.add(p);
    }

    public String getNumeroMateria() {
        return numeroMateria;
    }

    public void setNumeroMateria(String numeroMateria) {
        this.numeroMateria = numeroMateria;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }
}
