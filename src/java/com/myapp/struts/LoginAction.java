/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;


/**
 *
 * @author Langtech
 */
public class LoginAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String FAILURE = "failure";
    private static final String PROFESOR = "profesor";
    private static final String ADMINISTRADOR = "administrador";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Usuario u = (Usuario) form;
        HttpSession session = request.getSession(true);

        ActionErrors error = new ActionErrors();
        
        //valido los campos de formulario
        error = u.validate(mapping, request);
        boolean huboError = false;

        //sino los campos no son validos
        if (error.size()!=0) {
            huboError = true;
        }
        
        //si los campos no son validos
        if (huboError) {
            return mapping.findForward(FAILURE);
        //si los campos son validos
        } else {
            //verifica un usuario en el sistema CAS
            Usuario tmp = DBMS.getInstance().verificarCas(u);

            if (tmp.getUsbid() != null) {
                //si un usuario es profesor
                if (tmp.getTipousuario().equals("profesor")) {
                    request.setAttribute("usuario", tmp);
                    session.setAttribute("usbid", tmp.getUsbid());
                    return mapping.findForward(PROFESOR);
                //si un usuario es administrador
                } else if (tmp.getTipousuario().equals("administrador")) {
                    request.setAttribute("usuario", tmp);
                    session.setAttribute("usbid", tmp.getUsbid());
                    return mapping.findForward(ADMINISTRADOR);
                //sino no se posee un tipo de usuario especifico
                } else {
                    return mapping.findForward(FAILURE);
                }

            } else {
                error.add("contrasena", new ActionMessage("error.contrasena.missmatch"));
                return mapping.findForward(FAILURE);
            }

        }
    }
}
