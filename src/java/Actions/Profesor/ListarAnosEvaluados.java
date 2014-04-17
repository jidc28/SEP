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
public class ListarAnosEvaluados extends org.apache.struts.action.Action {

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

        Profesor profesor = (Profesor) form;

        ArrayList<rendimientoProf> evaluaciones = null;
        /* Se obtiene la informacion del profesor */
        profesor = DBMS.getInstance().obtenerInfoProfesor(profesor.getUsbid());

        /* Dependiendo del tipo de usuario se consultan las evaluaciones enviadas */
        if (tipousuario.equals("coordinacion")) {
            evaluaciones = 
                    DBMS.getInstance().listarAnoEvaluacionesEnviadasCoordinacion(id, profesor.getUsbid());
        } else if (tipousuario.equals("departamento")) {
            evaluaciones = 
                    DBMS.getInstance().listarAnoEvaluacionesEnviadasDepartamento(id, profesor.getUsbid());
        }

        session.setAttribute("evaluaciones", evaluaciones);
        session.setAttribute("profesor", profesor);
        return mapping.findForward(SUCCESS);
    }
}
