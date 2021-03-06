package Actions.Profesor;

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
 * @author admin
 */
public class ConsultaProfesoresEvaluados extends org.apache.struts.action.Action {

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
        String id = usuario.getUsbid();
        String tipousuario = usuario.getTipousuario();

        ArrayList<Profesor> profesores = null;

        if (tipousuario.equals("departamento")) {
            profesores =
                    DBMS.getInstance().listarProfesoresEvaluadosDepartamento(id);

        } else if (tipousuario.equals("coordinacion")) {
            profesores =
                    DBMS.getInstance().listarProfesoresEvaluadosCoordinacion(id);
            
        } else if (tipousuario.equals("decanato")) {
            String id_coordinacion = ((Coordinacion) form).getCodigo();
            profesores =
                    DBMS.getInstance().listarProfesoresEvaluadosCoordinacion(id_coordinacion);
            session.setAttribute("coordinacion", id_coordinacion);
        }

        if (profesores.isEmpty()) {
            request.setAttribute("vacio", SUCCESS);
        }

        session.setAttribute("profesores", profesores);
        return mapping.findForward(SUCCESS);
    }
}