package Actions.Evaluaciones;

import Clases.*;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smaf
 */
public class ListarEvaluacionesPendientes extends org.apache.struts.action.Action {

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
        /* Se obtiene el id del usuario y el tipo del usuario */
        String id = usuario.getUsbid();
        String tipousuario = usuario.getTipousuario();

        ArrayList<dicta> evaluaciones_pendientes = null;

        /* Se obtiene el usbid del profesor del form */
        String id_profesor = ((Coordinacion) form).getCodigo();
        
        /* Se obtienen los datos del profesor. */
        Profesor profesor = DBMS.getInstance().obtenerInfoProfesor(id_profesor);
        request.setAttribute("profesor", profesor);

        /* Dependiendo del tipo de usuario se realizan distintas operaciones */
        if (tipousuario.equals("coordinacion")) {
            /* Se listan las evaluaciones pendientes por materia */
            evaluaciones_pendientes = DBMS.getInstance().listarEvaluacionesPendientes(id, id_profesor);
        } else if (tipousuario.equals("departamento")) {
            /* Se listan las evaluaciones pendientes por profesor */
            evaluaciones_pendientes = DBMS.getInstance().listarEvaluadosPorCoordinacion(id, id_profesor);
            session.setAttribute("id_profesor", id_profesor);
        } else if (tipousuario.equals("decanato")) {
            /* Se listan las evaluaciones pendientes por coordinacion */
            Coordinacion coordinacion = new Coordinacion();
            coordinacion.setCodigo((String) session.getAttribute("coordinacion"));
            
            evaluaciones_pendientes = DBMS.getInstance().listarEvaluacionesPendientes(coordinacion, id_profesor);
            request.setAttribute("solo_lectura", SUCCESS);
        }

        /* Si el listado de evaluaciones pendientes está vacío */
        if (evaluaciones_pendientes.isEmpty()) {
            request.setAttribute("vacio", SUCCESS);
        }

        /* Se envian los atributos correspondientes a la vista */
        request.setAttribute("evaluaciones_pendientes", evaluaciones_pendientes);
        return mapping.findForward(SUCCESS);
    }
}