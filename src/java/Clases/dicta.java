/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author smaf
 */
public class dicta extends org.apache.struts.action.ActionForm {
    
    private String codigoMateria;
    private String numeroMateria;
    private ArrayList<Profesor> profesores = new ArrayList(0);
    private Profesor primerProfesor;

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
