package Clases;

/**
 *
 * @author smaf
 */
public class Archivo extends org.apache.struts.action.ActionForm {
    private String usbidProfesor;
    private String nombre;
    private String trimestre;
    private int ano;

    public String getUsbidProfesor() {
        return usbidProfesor;
    }

    public void setUsbidProfesor(String usbidProfesor) {
        this.usbidProfesor = usbidProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
}
