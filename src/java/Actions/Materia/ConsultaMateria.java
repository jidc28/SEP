package Actions.Materia;

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
public class ConsultaMateria extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

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
        ArrayList<Materia> materias = null;

        /**
         * Dependiendo del tipo del usuario se realizan diferentes consultas.
         */
        if (tipousuario.equals("departamento")) {
            materias = DBMS.getInstance().listarMateriasOfertadas(id);

        } else if (tipousuario.equals("coordinacion")) {
            Departamento dpto = (Departamento) form;
            materias = DBMS.getInstance().listarMateriasOfertadas(
                    dpto.getCodigo(), id);
            request.setAttribute("dpto_seleccionado", dpto);
            session.removeAttribute("materias_vinculadas");
        }

        if (materias.isEmpty()) {
            request.setAttribute("vacio", FAILURE);
        }
        request.setAttribute("materias", materias);
        return mapping.findForward(SUCCESS);
    }
}
