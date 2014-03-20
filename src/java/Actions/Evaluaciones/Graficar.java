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

        rendimientoProf evaluacion, evaluaciones;

        dicta d = (dicta) form;

        /* En el caso ne que la evaluación no se haya realizado. */
        if (d.getOpcion() == null) {

            /* Se obtiene la evaluación del profesor */
            evaluacion = DBMS.getInstance().obtenerEvaluacion(d);
            /* Se obtiene la evaluacion general */
            evaluaciones = DBMS.getInstance().obtenerEvaluaciones(d.getCodigoMateria(),
                    evaluacion.getAno(), evaluacion.getTrimestre());

            /* Se obtienen los datos del profesor */
            Profesor profesor = DBMS.getInstance().obtenerInfoProfesor(d.getUsbidProfesor());

            request.setAttribute("profesor", profesor);

            /* En caso que la evaluación ya ha sido enviada. */
        } else {

            /* Se obtienen los datos sobre la evaluación que se quiere
             * revisar */
            int ano = (Integer) session.getAttribute("ano");
            String trimestre = (String) session.getAttribute("trimestre");
            Profesor profesor = (Profesor) session.getAttribute("profesor");
            
            /* Se obtiene la evaluación del profesor */
            evaluacion =
                    DBMS.getInstance().obtenerEvaluacion(d.getCodigoMateria(),
                    profesor.getUsbid(), ano, trimestre);
            /* Se obtiene la evaluacion general */
            evaluaciones =
                    DBMS.getInstance().obtenerEvaluaciones(d.getCodigoMateria(),
                    ano, trimestre);

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

        /* Se envian las variables importantes a la vista (graficos) */
        request.setAttribute("EP", evaluacion);
        request.setAttribute("EPS", evaluaciones);
        request.setAttribute("aplazados", aplazados);
        request.setAttribute("aprobados", aprobados);
        request.setAttribute("aplazados_general", aplazados_general);
        request.setAttribute("aprobados_general", aprobados_general);
        request.setAttribute("nota_promedio_general", nota_promedio_general);

        return mapping.findForward(SUCCESS);
    }
}