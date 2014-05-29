package Actions.Departamento;

import Clases.*;
import DBMS.DBMS;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author jidc28
 */
public class VerPlanillasLlenas extends org.apache.struts.action.Action {
    /* forward name="success" path="" */

    private static final String SUCCESS = "success";

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

        HttpSession session = request.getSession(true);
        String id_departamento = (String) session.getAttribute("usbid");

        Profesor profesor = (Profesor) form;
        String id_profesor = profesor.getUsbid();
        profesor.setNombre(profesor.getApellido() + ", " + profesor.getNombre());

        /* Se obtiene el listado de las planillas llenas no evaluadas por la 
         * coordinacion */
        ArrayList<Rendimiento> rendimiento =
                DBMS.getInstance().obtenerPlanillasLlenas(id_profesor, id_departamento);

        session.setAttribute("profesor", profesor);
        request.setAttribute("rendimiento", rendimiento);
        return mapping.findForward(SUCCESS);
    }
}
