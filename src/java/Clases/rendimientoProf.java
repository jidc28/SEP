/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author jidc28
 */
public class rendimientoProf extends org.apache.struts.action.ActionForm {
    private String usbid_profesor;
    private String codigo_materia;
    private String nombre_materia;
    private int ano;
    private String trimestre;
    private int total_estudiantes;
    private int aprobados;
    private int aplazados;
    private int retirados;
    private float nota_prom;

    public String getUsbid_profesor() {
        return usbid_profesor;
    }

    public void setUsbid_profesor(String usbid_profesor) {
        this.usbid_profesor = usbid_profesor;
    }

    public String getCodigo_materia() {
        return codigo_materia;
    }

    public void setCodigo_materia(String codigo_materia) {
        this.codigo_materia = codigo_materia;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public int getTotal_estudiantes() {
        return total_estudiantes;
    }

    public void setTotal_estudiantes(int total_estudiantes) {
        this.total_estudiantes = total_estudiantes;
    }

    public int getAprobados() {
        return aprobados;
    }

    public void setAprobados(int aprobados) {
        this.aprobados = aprobados;
    }

    public int getAplazados() {
        return aplazados;
    }

    public void setAplazados(int aplazados) {
        this.aplazados = aplazados;
    }

    public int getRetirados() {
        return retirados;
    }

    public void setRetirados(int retirados) {
        this.retirados = retirados;
    }

    public float getNota_prom() {
        return nota_prom;
    }

    public void setNota_prom(float nota_prom) {
        this.nota_prom = nota_prom;
    }

    
}