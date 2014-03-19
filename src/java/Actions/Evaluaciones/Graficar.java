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

public class Graficar extends Action {

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        String id_coordinacion = (String) session.getAttribute("usbid");

        dicta d = (dicta) form;

        rendimientoProf evaluacion = DBMS.getInstance().obtenerEvaluacion(d);
        rendimientoProf evaluaciones = DBMS.getInstance().obtenerEvaluaciones(d);
        
        Profesor profesor = DBMS.getInstance().obtenerInfoProfesor(d.getUsbidProfesor());

        int aplazados = evaluacion.getNota1() + evaluacion.getNota2();
        int aprobados = evaluacion.getNota3() + evaluacion.getNota4()
                + evaluacion.getNota5();

        int aplazados_general = evaluaciones.getNota1() + evaluaciones.getNota2();
        int aprobados_general = evaluaciones.getNota3() + evaluaciones.getNota4()
                + evaluaciones.getNota5();

        float nota_promedio_general = (float) (evaluaciones.getNota1()
                + (evaluaciones.getNota2() * 2) + (evaluaciones.getNota3() * 3)
                + (evaluaciones.getNota4() * 4) + (evaluaciones.getNota5() * 5))
                / (evaluaciones.getTotal_estudiantes() - evaluaciones.getRetirados());

        System.out.println("------------------------------------------------------------------------->" + d.getUsbidProfesor());
        System.out.println("------------------------------------------------------------------------->" + d.getCodigoMateria());

        request.setAttribute("EP", evaluacion);
        request.setAttribute("EPS", evaluaciones);
        request.setAttribute("profesor", profesor);
        request.setAttribute("aplazados", aplazados);
        request.setAttribute("aprobados", aprobados);
        request.setAttribute("aplazados_general", aplazados_general);
        request.setAttribute("aprobados_general", aprobados_general);
        request.setAttribute("nota_promedio_general", nota_promedio_general);

        return mapping.findForward(SUCCESS);
    }
}