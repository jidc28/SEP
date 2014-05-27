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

public class HacerEvaluacion extends Action {

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
    @SuppressWarnings("empty-statement")
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipousuario = usuario.getTipousuario();
        String id = usuario.getUsbid();

        Profesor profesor =  (Profesor) session.getAttribute("profesor");
        
        if (session.getAttribute("usuario") != null) {

            dicta d = (dicta) form;
            d.setUsbidProfesor(profesor.getUsbid());

            /* Se obtiene el rendimiento del profesor determinado asociado con
             * la materia que maneja la coordinación */
            rendimientoProf evaluacion = DBMS.getInstance().obtenerEvaluacion(d);
//
//            /* Se obtiene toda la informacion del profesor */
//            Profesor profesor =
//                    DBMS.getInstance().obtenerInfoProfesor(d.getUsbidProfesor());

            /* Se calcula la cantidad de aplazados y la cantidad de aprobados */
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

            /* Si el usuario que accede a esta funcionalidad es el/la 
             * coordinador/coordinadora */
            if (tipousuario.equals("coordinacion")) {

                request.setAttribute("comentarios", SUCCESS);

                /* Si el usuario que accede a esta funcionalidad es
                 * jefe de departamento */
            } else if (tipousuario.equals("departamento")) {

//                ArrayList<rendimientoProf> evaluacion_coordinaciones =
//                        DBMS.getInstance().
//                        obtenerEvaluacionCoordinaciones(id, profesor.getUsbid(), d.getCodigoMateria());
//
//                session.setAttribute("evaluacion_departamento", evaluacion_coordinaciones);

                /* Si el usuario que accede a esta funcionalidad decano */
            } else if (tipousuario.equals("decanato")) {

                /* Se obtiene la coordinacion que realizo la evaluacion. */
                String id_coordinacion = (String) session.getAttribute("coordinacion");

                /* Se obtienen el trimestre y el ano de la evaluacion */
                String trimestre = evaluacion.getTrimestre();
                int ano = evaluacion.getAno();

                /* Se obtienen los datos de la evaluacion que realizo la
                 * coordinacion */
                rendimientoProf evaluado =
                        DBMS.getInstance().listarEvaluacionesCoordinacion(
                        id_coordinacion, ano, trimestre, d.getCodigoMateria(),
                        profesor.getUsbid());

                request.setAttribute("revisar", SUCCESS);
                request.setAttribute("evaluado_coordinacion", evaluado);
            }

            /* Se obtienen los datos de la materia que se quiere evaluar. */
            Materia materia_evaluar = new Materia();
            materia_evaluar.setCodigo(d.getCodigoMateria());
            materia_evaluar = DBMS.getInstance().obtenerDatosMateria(materia_evaluar);
            materia_evaluar.setPeriodo(d.getPeriodo());
            
            /* Se envian a la vista los atributos correspondiente */
            request.setAttribute("materia_evaluar", materia_evaluar);
//            session.setAttribute("profesor", profesor);
            session.setAttribute("evaluacion", evaluacion);
            session.setAttribute("porcentaje1", porcentaje1);
            session.setAttribute("porcentaje2", porcentaje2);
            session.setAttribute("porcentaje3", porcentaje3);
            session.setAttribute("porcentaje4", porcentaje4);
            session.setAttribute("porcentaje5", porcentaje5);
            session.setAttribute("retirados", porcentajeR);
            session.setAttribute("aplazados", porcentajeApl);
            session.setAttribute("aprobados", porcentajeApr);
        }

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