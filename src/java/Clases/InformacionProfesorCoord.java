/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author smaf
 */
public class InformacionProfesorCoord extends ActionForm {

    public String codigoCoordinacion;
    public String usbidProfesor;
    public String consejoAsesor;
    public int tesisTutoria;
    public int tesisJurado;
    public int pasantiaCorta;
    public int pasantiaLargaTutor;
    public int pasantiaLargaJurado;

    public String getCodigoCoordinacion() {
        return codigoCoordinacion;
    }

    public void setCodigoCoordinacion(String codigoCoordinacion) {
        this.codigoCoordinacion = codigoCoordinacion;
    }

    public String getUsbidProfesor() {
        return usbidProfesor;
    }

    public void setUsbidProfesor(String usbidProfesor) {
        this.usbidProfesor = usbidProfesor;
    }

    public String getConsejoAsesor() {
        return consejoAsesor;
    }

    public void setConsejoAsesor(String consejoAsesor) {
        this.consejoAsesor = consejoAsesor;
    }

    public int getTesisTutoria() {
        return tesisTutoria;
    }

    public void setTesisTutoria(int tesisTutoria) {
        this.tesisTutoria = tesisTutoria;
    }

    public int getTesisJurado() {
        return tesisJurado;
    }

    public void setTesisJurado(int tesisJurado) {
        this.tesisJurado = tesisJurado;
    }

    public int getPasantiaCorta() {
        return pasantiaCorta;
    }

    public void setPasantiaCorta(int pasantiaCorta) {
        this.pasantiaCorta = pasantiaCorta;
    }

    public int getPasantiaLargaTutor() {
        return pasantiaLargaTutor;
    }

    public void setPasantiaLargaTutor(int pasantiaLargaTutor) {
        this.pasantiaLargaTutor = pasantiaLargaTutor;
    }

    public int getPasantiaLargaJurado() {
        return pasantiaLargaJurado;
    }

    public void setPasantiaLargaJurado(int pasantiaLargaJurado) {
        this.pasantiaLargaJurado = pasantiaLargaJurado;
    }
}
