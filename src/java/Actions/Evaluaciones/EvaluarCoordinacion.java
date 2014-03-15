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
    private static final String FAILURE = "failure";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        String id_coordinacion = (String) session.getAttribute("usbid");

        rendimientoProf rendimiento = (rendimientoProf) form;
        if (rendimiento.getObservaciones_c().length() > 500) {
            rendimiento.setObservaciones_c(rendimiento.getObservaciones_c().substring(0, 500));
        }

        if (rendimiento.getRecomendado() == null) {

            request.setAttribute("recomendar", FAILURE);
            return mapping.findForward(FAILURE);
        } else {

            boolean evaluado =
                    DBMS.getInstance().evaluarCoordinacion(rendimiento, id_coordinacion);

            ArrayList<dicta> evaluaciones_pendientes;
            evaluaciones_pendientes =
                    DBMS.getInstance().listarEvaluacionesPendientes(id_coordinacion);

            request.setAttribute("evaluaciones_pendientes", evaluaciones_pendientes);

//            session.removeAttribute("pendiente");
//            session.removeAttribute("informacion");
//            session.removeAttribute("profesor");
//            session.removeAttribute("evaluacion");
//            session.removeAttribute("porcentaje1");
//            session.removeAttribute("porcentaje2");
//            session.removeAttribute("porcentaje3");
//            session.removeAttribute("porcentaje4");
//            session.removeAttribute("porcentaje5");
//            session.removeAttribute("retirados");
//            session.removeAttribute("aplazados");
//            session.removeAttribute("aprobados");

            return mapping.findForward(SUCCESS);
        }
    }
}