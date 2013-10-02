/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Langtech
 */
public class CambiarNombreNucleoUniversitarioA extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    private static final String ERRORUPDATE = "errorupdate";

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

        NucleoUniversitario u = (NucleoUniversitario) form;
        HttpSession session = request.getSession(true);

        ActionErrors error = new ActionErrors();

        //valido los campos de formulario
        error = u.validate(mapping, request);
        boolean huboError = false;

        
        if (error.size() != 0) {
            huboError = true;
        }

        //si los campos no son validos
        if (huboError) {
            return mapping.findForward(FAILURE);
            //si los campos son validos
        } else {

            //elimina usuario del sistema 
            boolean actualizo = DBMS.getInstance().actualizarNombreNucleoUniversitario(u);

            if (actualizo) {
                
                ArrayList<NucleoUniversitario> nucleos = DBMS.getInstance().listarNucleosUniversitarios();
                session.setAttribute("nucleos", nucleos);


                return mapping.findForward(SUCCESS);
            } else {
                return mapping.findForward(ERRORUPDATE);
            }
        }
    }
}