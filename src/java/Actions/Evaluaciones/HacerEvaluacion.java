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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipousuario = usuario.getTipousuario();
        String id = usuario.getUsbid();

        if (session.getAttribute("usuario") != null) {

            dicta d = (dicta) form;

            /* Se obtiene el rendimiento del profesor determinado asociado con
             * la materia que maneja la coordinación */
            rendimientoProf evaluacion = DBMS.getInstance().obtenerEvaluacion(d);

            /* Se obtiene toda la informacion del profesor */
            Profesor profesor =
                    DBMS.getInstance().obtenerInfoProfesor(d.getUsbidProfesor());

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

                /* Si existe una informacion previa se envia a la vista, sino, se crea
                 * una instancia vacia de la informacion */
                InformacionProfesorCoord informacion =
                        DBMS.getInstance().listarInformacionProfesorCoordinacion(id, profesor.getUsbid());

                if (informacion == null) {
                    boolean creada =
                            DBMS.getInstance().crearInformacionProfesorCoordinacion(id, profesor.getUsbid(), new InformacionProfesorCoord());
                    informacion =
                            DBMS.getInstance().listarInformacionProfesorCoordinacion(id, profesor.getUsbid());
                }

                session.setAttribute("informacion", informacion);

                /* Si no se ha realizado todavia la evaluacion del profesor por
                 * parte de la coordinacion */
                if (d.getOpcion().equals("pendiente")) {

                    request.setAttribute("evaluar_coordinacion", SUCCESS);
                    request.setAttribute("evaluar", SUCCESS);

                    /* Si ya se realizo la evaluacion del profesor */
                } else if (d.getOpcion().equals("enviada")) {

                    String trimestre = (String) session.getAttribute("trimestre");
                    int ano = (Integer) session.getAttribute("ano");
                    /* Ojo eso debe estar */
//            session.removeAttribute("trimestre");
//            session.removeAttribute("ano");
                    rendimientoProf evaluado =
                            DBMS.getInstance().listarEvaluacionesEnviadasMateria(id, ano, trimestre, d.getCodigoMateria());
                    request.setAttribute("evaluado_coordinacion", evaluado);
                }

                /* Si el usuario que accede a esta funcionalidad es
                 * jefe de departamento */
            } else if (tipousuario.equals("departamento")) {

                /* Si no se ha realizado todavia la evaluacion del profesor por
                 * parte del departamento */
                if (d.getOpcion().equals("pendiente")) {

                    ArrayList<rendimientoProf> evaluacion_coordinaciones =
                            DBMS.getInstance().obtenerEvaluacionCoordinaciones(id, profesor.getUsbid(), d.getCodigoMateria());

                    session.setAttribute("evaluacion_departamento", evaluacion_coordinaciones);
                    request.setAttribute("revisar", SUCCESS);

                } else {
                    /* Se consultan los resultados de las evaluaciones por parte de 
                     * las coordinaciones */
                    ArrayList<rendimientoProf> evaluacion_coordinaciones =
                            DBMS.getInstance().obtenerEvaluacionesEnviadasCoordinaciones(id,
                            profesor.getUsbid(), d.getCodigoMateria());

                    session.setAttribute("evaluacion_departamento", evaluacion_coordinaciones);
                }

                /* Si el usuario que accede a esta funcionalidad decano */
            } else if (tipousuario.equals("decanato")) {

                String id_coordinacion = (String) session.getAttribute("coordinacion");

                /* Si existe una informacion previa se envia a la vista, sino, se crea
                 * una instancia vacia de la informacion */
                InformacionProfesorCoord informacion =
                        DBMS.getInstance().listarInformacionProfesorCoordinacion(id_coordinacion, d.getUsbidProfesor());

                session.setAttribute("informacion", informacion);

                String trimestre = evaluacion.getTrimestre();
                int ano = evaluacion.getAno();

                /* Se obtienen los datos de la evaluacion que realizo la
                 * coordinacion */
                rendimientoProf evaluado =
                        DBMS.getInstance().listarEvaluacionesCoordinacion(id_coordinacion, ano, trimestre, d.getCodigoMateria(), d.getUsbidProfesor());

                request.setAttribute("evaluado_coordinacion", evaluado);
                request.setAttribute("revisar", SUCCESS);
            }

            /* Se envian a la vista los atributos correspondiente */
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