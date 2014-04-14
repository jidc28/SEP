/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Profesor;

import Clases.*;
import DBMS.DBMS;
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
 * @author jidc28
 */
public class AgregarProfesor extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

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

        Profesor profesor = (Profesor) form;
        HttpSession session = request.getSession(true);
        String id_departamento = (String) session.getAttribute("usbid");

        ActionErrors error = new ActionErrors();
        error = profesor.validateAgregar(mapping, request);


        if (error.size() != 0) {          
            saveErrors(request, error);
            return mapping.findForward(FAILURE);
        } else {

            profesor.setEmail(profesor.getUsbid() + "@usb.ve");
            boolean agregado = 
                    DBMS.getInstance().
                            agregarProfesor(profesor,id_departamento);

            ArrayList<Profesor> profesores =
                    DBMS.getInstance().
                            listarProfesoresDepartamento(id_departamento);

            session.setAttribute("profesores",profesores);

            return mapping.findForward(SUCCESS);
        }
    }
}
