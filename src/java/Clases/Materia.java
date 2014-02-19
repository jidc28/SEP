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
 * @author jidc28
 */
public class Materia extends org.apache.struts.action.ActionForm {
    
    private String codigo;
    private String viejoCodigo;
    private String nombre;
    private String num2;
    private String num3;
    private String num4;
    private String cod1;
    private String cod2;
    private String creditos;
    private String condicion;
    private String estado;
    private String mensaje;
    private String departamento;
    private String coordinacion;
    private String comentarios;
    private String solicitud;
    private String periodoSD;
    private String periodoEM;
    private String periodoAJ;
    private String periodoV;
    private String periodo;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPeriodoSD() {
        return periodoSD;
    }

    public void setPeriodoSD(String periodoSD) {
        this.periodoSD = periodoSD;
    }

    public String getPeriodoEM() {
        return periodoEM;
    }

    public void setPeriodoEM(String periodoEM) {
        this.periodoEM = periodoEM;
    }

    public String getPeriodoAJ() {
        return periodoAJ;
    }

    public void setPeriodoAJ(String periodoAJ) {
        this.periodoAJ = periodoAJ;
    }

    public String getPeriodoV() {
        return periodoV;
    }

    public void setPeriodoV(String periodoV) {
        this.periodoV = periodoV;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getCoordinacion() {
        return coordinacion;
    }

    public void setCoordinacion(String coordinacion) {
        this.coordinacion = coordinacion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getCod1() {
        return cod1;
    }

    public void setCod1(String cod1) {
        this.cod1 = cod1;
    }

    public String getCod2() {
        return cod2;
    }

    public void setCod2(String cod2) {
        this.cod2 = cod2;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }

    public String getNum4() {
        return num4;
    }

    public void setNum4(String num4) {
        this.num4 = num4;
    }
    
    public String getViejoCodigo() {
        return viejoCodigo;
    }

    public void setViejoCodigo(String viejoCodigo) {
        this.viejoCodigo = viejoCodigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    
        if (getCodigo() == null || getCodigo().length() < 1) {
            errors.add("codigo", new ActionMessage("error.codigo.required"));
        }
        
        if (getNombre() == null || getNombre().length() < 1) {
            errors.add("nombre", new ActionMessage("error.nombre.required"));
        }
        
        return errors;
    }
    
}
