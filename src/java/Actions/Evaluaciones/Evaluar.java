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

public class Evaluar extends Action {

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        String id = usuario.getUsbid();
        String tipousuario = usuario.getTipousuario();

        Profesor profesor = (Profesor) session.getAttribute("profesor");

        rendimientoProf rendimiento = (rendimientoProf) form;

        ArrayList<Profesor> profesores = null;

        /* Se trunca el mensaje en el caso que sea demasiado largo (mayor a 
         * 500 caracteres) */
        if (rendimiento.getObservaciones_c().length() > 500) {
            rendimiento.setObservaciones_c(rendimiento.getObservaciones_c().substring(0, 500));
        }

        /* Si la coordinacion no ha seleccionado si recomienda o no al profesor 
         * (es un campo obligatorio) */
        if (rendimiento.getRecomendado() == null) {

            if (tipousuario.equals("coordinacion")) {

                /* Si existe una informacion previa se envia a la vista, sino, se crea
                 * una instancia vacia de la informacion */
                InformacionProfesorCoord informacion =
                        DBMS.getInstance().
                        listarInformacionProfesorCoordinacion(id, profesor.getUsbid());

                if (informacion == null) {
                    boolean creada =
                            DBMS.getInstance().crearInformacionProfesorCoordinacion(id,
                            profesor.getUsbid(), new InformacionProfesorCoord());

                    informacion =
                            DBMS.getInstance().
                            listarInformacionProfesorCoordinacion(id, profesor.getUsbid());
                }

                session.setAttribute("informacion", informacion);
                request.setAttribute("evaluar_coordinacion", SUCCESS);

            }

            request.setAttribute("recomendar", FAILURE);
            request.setAttribute("evaluar", SUCCESS);

            return mapping.findForward(FAILURE);

            /* La coordinacion selecciono si recomienda o no al profesor */
        } else {

            ArrayList<dicta> evaluaciones_pendientes = null;
            boolean evaluado = false;

            /* En caso que la evaluación dla este realizando la coordinacion */
            if (tipousuario.equals("coordinacion")) {

                /* Se guarda la evaluación */
                evaluado =
                        DBMS.getInstance().evaluarCoordinacion(rendimiento, id);

                /* Se consultan las evaluaciones que quedan pendientes de la
                 * coordinacion */
                evaluaciones_pendientes =
                        DBMS.getInstance().listarEvaluacionesPendientes(id, "no");

                profesores =
                        DBMS.getInstance().listarProfesoresPorEvaluarCoordinacion(id);

                /* En caso que la evaluación dla este realizando el departamento */
            } else if (tipousuario.equals("departamento")) {

                /* Se guarda la evaluación */
                evaluado =
                        DBMS.getInstance().evaluarDepartamento(rendimiento, id);

                /* Se consultan las evaluaciones que quedan pendientes del
                 * departamento */
                evaluaciones_pendientes =
                        DBMS.getInstance().listarEvaluadosPorCoordinacion(id);
            }

            request.setAttribute("evaluaciones_pendientes", evaluaciones_pendientes);
            request.setAttribute("profesores", profesores);

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
            session.removeAttribute("evaluacion_departamento");

            return mapping.findForward(SUCCESS);
        }
    }
}
