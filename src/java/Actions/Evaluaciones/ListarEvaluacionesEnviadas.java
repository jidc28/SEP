/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Evaluaciones;

import Clases.*;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smaf
 */
public class ListarEvaluacionesEnviadas extends org.apache.struts.action.Action {
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
        String id_coordinacion = (String) session.getAttribute("usbid");

        rendimientoProf rendimiento = (rendimientoProf) form;
        int ano = rendimiento.getAno();
        String trimestre = rendimiento.getTrimestre();

        ArrayList<rendimientoProf> evaluaciones_enviadas;
        evaluaciones_enviadas =
                DBMS.getInstance().listarEvaluacionesEnviadas(id_coordinacion, ano, trimestre);

        if (evaluaciones_enviadas.isEmpty()) {
            request.setAttribute("vacio", SUCCESS);
        }

        session.setAttribute("ano", ano);
        session.setAttribute("trimestre", trimestre);
        request.setAttribute("evaluaciones_enviadas", evaluaciones_enviadas);
        return mapping.findForward(SUCCESS);
    }
}