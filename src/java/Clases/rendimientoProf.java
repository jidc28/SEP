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
    private int nota1;
    private int nota2;
    private int nota3;
    private int nota4;
    private int nota5;
    private int retirados;
    private float nota_prom;
    private int viejoAno;
    private String viejoTrimestre;

    public int getViejoAno() {
        return viejoAno;
    }

    public void setViejoAno(int viejoAno) {
        this.viejoAno = viejoAno;
    }

    public String getViejoTrimestre() {
        return viejoTrimestre;
    }

    public void setViejoTrimestre(String viejoTrimestre) {
        this.viejoTrimestre = viejoTrimestre;
    }
    
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

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

    public int getNota4() {
        return nota4;
    }

    public void setNota4(int nota4) {
        this.nota4 = nota4;
    }

    public int getNota5() {
        return nota5;
    }

    public void setNota5(int nota5) {
        this.nota5 = nota5;
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