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
        Profesor profesor = (Profesor) session.getAttribute("profesor");

        rendimientoProf rendimiento = (rendimientoProf) form;

        /* Se trunca el mensaje en el caso que sea demasiado largo (mayor a 
         * 500 caracteres) */
        if (rendimiento.getObservaciones_c().length() > 500) {
            rendimiento.setObservaciones_c(rendimiento.getObservaciones_c().substring(0, 500));
        }

        /* Si la coordinacion no ha seleccionado si recomienda o no al profesor 
         * (es un campo obligatorio) */
        if (rendimiento.getRecomendado() == null) {

            /* Si existe una informacion previa se envia a la vista, sino, se crea
             * una instancia vacia de la informacion */
            InformacionProfesorCoord informacion =
                    DBMS.getInstance().
                    listarInformacionProfesorCoordinacion(id_coordinacion, profesor.getUsbid());

            if (informacion == null) {
                boolean creada =
                        DBMS.getInstance().
                        crearInformacionProfesorCoordinacion(id_coordinacion, profesor.getUsbid(), new InformacionProfesorCoord());
                informacion =
                        DBMS.getInstance().
                        listarInformacionProfesorCoordinacion(id_coordinacion, profesor.getUsbid());
            }

            session.setAttribute("informacion", informacion);
            request.setAttribute("evaluar_coordinacion", SUCCESS);
            request.setAttribute("evaluar", SUCCESS);
            request.setAttribute("recomendar", FAILURE);
            return mapping.findForward(FAILURE);

            /* La coordinacion recomendo o no recomendo al profesor */
        } else {

            /* Se guarda la evaluación */
            boolean evaluado =
                    DBMS.getInstance().evaluarCoordinacion(rendimiento, id_coordinacion);

            /* Se consultan las evaluaciones que quedan pendientes */
            ArrayList<dicta> evaluaciones_pendientes;
            evaluaciones_pendientes =
                    DBMS.getInstance().listarEvaluacionesPendientes(id_coordinacion, "no");

            request.setAttribute("evaluaciones_pendientes", evaluaciones_pendientes);

            /* Se eliminan los atributos utilizados en la evaluacion de la
             * sesion */
            session.removeAttribute("pendiente");
            session.removeAttribute("informacion");
            session.removeAttribute("profesor");
            session.removeAttribute("evaluacion");
            session.removeAttribute("porcentaje1");
            session.removeAttribute("porcentaje2");
            session.removeAttribute("porcentaje3");
            session.removeAttribute("porcentaje4");
            session.removeAttribute("porcentaje5");
            session.removeAttribute("retirados");
            session.removeAttribute("aplazados");
            session.removeAttribute("aprobados");

            return mapping.findForward(SUCCESS);
        }
    }
}