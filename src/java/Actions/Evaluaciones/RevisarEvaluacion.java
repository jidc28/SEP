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

public class RevisarEvaluacion extends Action {

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

        ArrayList<Profesor> profesores = null;
        rendimientoProf rendimiento = (rendimientoProf) form;
        boolean revisado = false;

        /* Si el usuario que accede a esta funcionalidad es de tipo
         * departamento */
        if (tipousuario.equals("departamento")) {

            revisado = DBMS.getInstance().revisadoDepartamento(rendimiento, id);

            String id_profesor = (String) session.getAttribute("id_profesor");
//            evaluaciones_pendientes = DBMS.getInstance().listarEvaluadosPorCoordinacion(id,id_profesor);
            profesores = DBMS.getInstance().listarProfesoresAEvaluarDepartamento(id);

            /* Si el usuario que accede a esta funcionalidad decano */
        } else if (tipousuario.equals("decanato")) {
            String coordinacion = (String) session.getAttribute("coordinacion");
            revisado = DBMS.getInstance().revisadoDecanato(rendimiento, coordinacion);
            profesores = 
                    DBMS.getInstance().listarProfesoresPorEvaluarDecanato(coordinacion);
//            evaluaciones_pendientes =
//                    DBMS.getInstance().listarEvaluacionesPendientes(coordinacion);
            request.setAttribute("solo_lectura", SUCCESS);
        }

        request.setAttribute("profesores", profesores);
        return mapping.findForward(SUCCESS);
    }
}