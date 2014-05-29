package Actions.Departamento;

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
 * @author jidc28
 */
public class EliminarDepartamento extends org.apache.struts.action.Action {

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
        ArrayList<Departamento> departamentos;
        Departamento departamento = (Departamento) form;

        boolean desactivado = DBMS.getInstance().eliminarDepartamento(departamento);

        /**
         * Dependiendo si el departamento fue o no eliminado exitosamente se
         * envían diferentes atributos a la vista de consultar departamento.
         */
        if (desactivado) {
            request.setAttribute("departamento_desactivado", SUCCESS);
        } else {
            request.setAttribute("departamento_no_desactivado", SUCCESS);
        }

        departamentos = DBMS.getInstance().listarDepartamentos();
        request.setAttribute("departamentos", departamentos);
        return mapping.findForward(SUCCESS);
    }
}