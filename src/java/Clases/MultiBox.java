package Clases;

import Clases.Profesor;
import DBMS.DBMS;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

public class MultiBox extends ActionForm {

    private static final long serialVersionUID = 1L;
    private ArrayList<Profesor> profesores;
    private String[] profesoresSeleccionados = {};

    public ArrayList<Profesor> getProfesores(String id_departamento) {
        profesores = DBMS.getInstance().listarProfesoresDepartamento(id_departamento);
        return profesores;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }
    
    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    public String [] getProfesoresSeleccionados() {
        return profesoresSeleccionados;
    }

    public void setProfesoresSeleccionados(String[] profesoresSeleccionados) {
        this.profesoresSeleccionados = profesoresSeleccionados;
    }
}