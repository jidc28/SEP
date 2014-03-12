/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ConsultaProfesorDepartamento extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
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
        String id = (String) session.getAttribute("usbid");

        ArrayList<Profesor> profesores = new ArrayList<Profesor>(0);
        String return_mapping = "no_autorizado";

        if (tipousuario.equals("departamento")) {
            profesores =
                    DBMS.getInstance().listarProfesoresActivosDepartamento(id);
            return_mapping = "departamento";
        } else if (tipousuario.equals("coordinacion")){
            profesores =
                    DBMS.getInstance().listarProfesoresCoordinacion(id);
            return_mapping = "coordinacion";
        }

        if (profesores.isEmpty()) {
            request.setAttribute("vacio", SUCCESS);
        }

        session.setAttribute("profesores", profesores);
        System.out.println(return_mapping);
        return mapping.findForward(return_mapping);
    }
}
