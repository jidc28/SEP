package Actions.Evaluaciones;

import Clases.*;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EvaluarCoordinacion extends Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        String id_coordinacion = (String) session.getAttribute("usbid");

        rendimientoProf rendimiento = (rendimientoProf) form;

        boolean evaluado =
                DBMS.getInstance().evaluarCoordinacion(rendimiento, id_coordinacion);

        ArrayList<dicta> evaluaciones_pendientes;
        evaluaciones_pendientes =
                DBMS.getInstance().listarEvaluacionesPendientes(id_coordinacion);

        request.setAttribute("evaluaciones_pendientes", evaluaciones_pendientes);

        return mapping.findForward(SUCCESS);
    }
}