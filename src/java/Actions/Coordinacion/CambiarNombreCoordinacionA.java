/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Coordinacion;

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
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Langtech
 */
public class CambiarNombreCoordinacionA extends org.apache.struts.action.Action {

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

        Coordinacion u = (Coordinacion) form;
        HttpSession session = request.getSession(true);

        ActionErrors error = new ActionErrors();

        //valido los campos de formulario
        error = u.validate(mapping, request);

        //si los campos no son validos
        if (error.size() != 0) {
            Coordinacion c = DBMS.getInstance().obtenerNombreCoordinacion(u);
            request.setAttribute("codigo", c.getCodigo());
            request.setAttribute("nombre", c.getNombre());
            saveErrors(request, error);
            return mapping.findForward(FAILURE);
            //si los campos son validos
        } else {

            boolean actualizo = DBMS.getInstance().actualizarNombreCoordinacion(u);

            if (actualizo) {
                String codigoDecan = (String) session.getAttribute("codigoDecanatoActual");
                
                ArrayList<Decanato> decanatos = DBMS.getInstance().listarDecanatos();
                request.setAttribute("decanatos", decanatos);
                ArrayList<Coordinacion> coords = DBMS.getInstance().listarCoordinacionesAdscritas(codigoDecan);
                request.setAttribute("coordinaciones", coords);
                request.setAttribute("modificacion", SUCCESS);

                return mapping.findForward(SUCCESS);
            } else {
                return mapping.findForward(ERRORUPDATE);
            }
        }
    }
}