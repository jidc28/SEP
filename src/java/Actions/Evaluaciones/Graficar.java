package Actions.Evaluaciones;

import Clases.*;
import DBMS.DBMS;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Graficar extends Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);

        Rendimiento evaluacion, evaluaciones;

        Profesor profesor = (Profesor) session.getAttribute("profesor");

        Rendimiento r = (Rendimiento) form;
        r.setUsbid_profesor(profesor.getUsbid());

        /* En el caso ne que la evaluación no se haya realizado. */
        if (r.getObservaciones_d() == null) {

            /* Se obtienen los datos sobre la evaluación que se quiere
             * revisar */
            int ano = (Integer) session.getAttribute("ano");
            String trimestre = (String) session.getAttribute("trimestre");

            /* Se obtiene la evaluación del profesor */
            evaluacion =
                    DBMS.getInstance().obtenerEvaluacion(r.getCodigo_materia(),
                    profesor.getUsbid(), r.getAno(), r.getTrimestre());
            /* Se obtiene la evaluacion general */
            evaluaciones =
                    DBMS.getInstance().obtenerEvaluaciones(r.getCodigo_materia(),
                    r.getAno(), r.getTrimestre());

            /* En caso que la evaluación ya ha sido enviada. */
        } else {

            /* Se obtiene la evaluación del profesor */
            evaluacion = DBMS.getInstance().obtenerEvaluacion(r);
            /* Se obtiene la evaluacion general */
            evaluaciones = DBMS.getInstance().obtenerEvaluaciones(r.getCodigo_materia(),
                    evaluacion.getAno(), evaluacion.getTrimestre());
        }

        /* Se calcula la cantidad de aplazados y aprobados del profesor 
         * correspondiente */
        int aplazados = evaluacion.getNota1() + evaluacion.getNota2();
        int aprobados = evaluacion.getNota3() + evaluacion.getNota4()
                + evaluacion.getNota5();

        /* Se calcula la cantidad de aplazados y aprobados de las evaluaciones
         * de los demás profesores */
        int aplazados_general = evaluaciones.getNota1() + evaluaciones.getNota2();
        int aprobados_general = evaluaciones.getNota3() + evaluaciones.getNota4()
                + evaluaciones.getNota5();

        /* Se calcula la nota promedio general */
        float nota_promedio_general = (float) (evaluaciones.getNota1()
                + (evaluaciones.getNota2() * 2) + (evaluaciones.getNota3() * 3)
                + (evaluaciones.getNota4() * 4) + (evaluaciones.getNota5() * 5))
                / (evaluaciones.getTotal_estudiantes() - evaluaciones.getRetirados());

        Materia materia_evaluar = new Materia();
        materia_evaluar.setCodigo(r.getCodigo_materia());
        materia_evaluar = DBMS.getInstance().obtenerDatosMateria(materia_evaluar);
        materia_evaluar.setPeriodo(r.getTrimestre());

        /* Se envian las variables importantes a la vista (graficos) */
        request.setAttribute("EP", evaluacion);
        request.setAttribute("EPS", evaluaciones);
        request.setAttribute("aplazados", aplazados);
        request.setAttribute("aprobados", aprobados);
        request.setAttribute("aplazados_general", aplazados_general);
        request.setAttribute("aprobados_general", aprobados_general);
        request.setAttribute("nota_promedio_general", nota_promedio_general);
        request.setAttribute("materia_evaluar", materia_evaluar);

        return mapping.findForward(SUCCESS);
    }
}