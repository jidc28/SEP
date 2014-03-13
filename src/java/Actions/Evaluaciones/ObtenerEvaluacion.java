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

public class ObtenerEvaluacion extends Action {

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

        dicta d = (dicta) form;
        /* Se obtiene el rendimiento del profesor determinado asociado con
         * la materia que maneja la coordinación */
        rendimientoProf evaluacion = DBMS.getInstance().obtenerEvaluacion(d);

        /* Se obtiene toda la informacion del profesor */
        Profesor profesor =
                DBMS.getInstance().obtenerInfoProfesor(d.getUsbidProfesor());

        int total = evaluacion.getTotal_estudiantes();
        int aplazados = evaluacion.getNota1() + evaluacion.getNota2();
        int aprobados = evaluacion.getNota3() + evaluacion.getNota4()
                + evaluacion.getNota5();

        /* Se obtienen los porcentajes de cada rubro y se transforman a 
         * dos decimales */
        String porcentaje1 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota1()));
        String porcentaje2 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota2()));
        String porcentaje3 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota3()));
        String porcentaje4 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota4()));
        String porcentaje5 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota5()));
        String porcentajeR = String.format("%.2f", calcularPorcentaje(total, evaluacion.getRetirados()));

        String porcentajeApr = String.format("%.2f", calcularPorcentaje(total, aprobados));
        String porcentajeApl = String.format("%.2f", calcularPorcentaje(total, aplazados));

        /* Si existe una informacion previa se envia a la vista, sino, se crea
         * una instancia vacia de la informacion */
        InformacionProfesorCoord informacion =
                DBMS.getInstance().listarInformacionProfesorCoordinacion(id_coordinacion, profesor.getUsbid());

        if (informacion == null) {
            informacion = new InformacionProfesorCoord();
        }

        /* Se envian a la vista los atributos correspondiente */
        session.setAttribute("informacion", informacion);
        session.setAttribute("profesor", profesor);
        session.setAttribute("evaluacion", evaluacion);
        session.setAttribute("porcentaje1", porcentaje1);
        session.setAttribute("porcentaje2", porcentaje2);
        session.setAttribute("porcentaje3", porcentaje3);
        session.setAttribute("porcentaje4", porcentaje4);
        session.setAttribute("porcentaje5", porcentaje5);
        session.setAttribute("retirados", porcentajeR);
        session.setAttribute("aplazados", porcentajeApl);
        session.setAttribute("aprobados", porcentajeApr);

        return mapping.findForward(SUCCESS);
    }

    /**
     * Calculo de porcentaje.
     *
     * @param total total de los estudiantes
     * @param parte parte de los estudiantes
     * @return entero con el valor del porcentaje
     */
    public Float calcularPorcentaje(int total, int parte) {
        Float resultado = (float) (parte * 100) / total;
        return resultado;
    }
}