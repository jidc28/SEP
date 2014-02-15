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

public class ObtenerEvaluacion extends Action {

    private static final String SUCCESS = "success";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        String id_coordinacion = (String) session.getAttribute("usbid");

        dicta d = (dicta) form;

        ArrayList<String> departamentosFaltantes =
                DBMS.getInstance().obtenerDepartamentosQueNoEvaluaron(d.getCodigoMateria());

        rendimientoProf evaluacion = DBMS.getInstance().obtenerEvaluacion(d);
        System.out.println("evaluacion: " + evaluacion);

        int total = evaluacion.getTotal_estudiantes();
        int aplazados = evaluacion.getNota1() + evaluacion.getNota2();
        int aprobados = evaluacion.getNota3() + evaluacion.getNota4()
                + evaluacion.getNota5();

        Float porcentaje1 = calcularPorcentaje(total, evaluacion.getNota1());
        Float porcentaje2 = calcularPorcentaje(total, evaluacion.getNota2());
        Float porcentaje3 = calcularPorcentaje(total, evaluacion.getNota3());
        Float porcentaje4 = calcularPorcentaje(total, evaluacion.getNota4());
        Float porcentaje5 = calcularPorcentaje(total, evaluacion.getNota5());
        Float porcentajeR = calcularPorcentaje(total, evaluacion.getRetirados());

        Float porcentajeApr = calcularPorcentaje(total, aprobados);
        Float porcentajeApl = calcularPorcentaje(total, aplazados);

        int profEvaluados =
                DBMS.getInstance().cantidadEvaluados(d.getCodigoMateria(), id_coordinacion);

        int profPorEvaluar =
                DBMS.getInstance().cantidadProfesoresDictanMateria(d.getCodigoMateria());

        Float porcentajeProf = calcularPorcentaje(profPorEvaluar,profEvaluados);

        request.setAttribute("evaluacion", evaluacion);
        request.setAttribute("porcentaje1", porcentaje1);
        request.setAttribute("porcentaje2", porcentaje2);
        request.setAttribute("porcentaje3", porcentaje3);
        request.setAttribute("porcentaje4", porcentaje4);
        request.setAttribute("porcentaje5", porcentaje5);
        request.setAttribute("retirados", porcentajeR);
        request.setAttribute("aplazados", porcentajeApl);
        request.setAttribute("aprobados", porcentajeApr);
        request.setAttribute("departamentosFaltantes", departamentosFaltantes);
        request.setAttribute("porcentajeProf", porcentajeProf);


        return mapping.findForward(SUCCESS);
    }

    public Float calcularPorcentaje(int total, int parte) {
        Float resultado = (float) (parte * 100) / total;
        return resultado;
    }
}