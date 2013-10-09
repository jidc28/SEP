/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author admin
 */
public class EliminarUserForm extends org.apache.struts.action.ActionForm {
    
    private String usbid;
    private String tipousuario;
    private String departamento;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String getUsbid() {
        return usbid;
    }

    public void setUsbid(String usbid) {
        this.usbid = usbid;
    }
    

    
    /**
     *
     */
    public EliminarUserForm() {
        super();
        // TODO Auto-generated constructor stub
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
        if (getUsbid() == null || getUsbid().length() < 1) {
            errors.add("usbid", new ActionMessage("error.usbid.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}
