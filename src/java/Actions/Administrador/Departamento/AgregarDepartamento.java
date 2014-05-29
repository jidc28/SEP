package Actions.Administrador.Departamento;

import Clases.Departamento;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author admin
 */
public class AgregarDepartamento extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    private static final String YAREGISTRADA = "yaregistrada";

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

        Departamento d = (Departamento) form;
        HttpSession session = request.getSession(true);

        ActionErrors error;

        /* Se validan los campos del formulario */
        error = d.validate(mapping, request);

        if (error.size() != 0) {
            saveErrors(request, error);
            return mapping.findForward(FAILURE);
        } else {

            /* Se registra el nuevo departamento */
            boolean registro = DBMS.getInstance().registrarDepartamento(d);

            if (registro) {

                /* Se obtiene el listado de los departamentos */
                ArrayList<Departamento> departamentos =
                        DBMS.getInstance().listarDepartamentos();

                session.setAttribute("departamentos", departamentos);
                request.setAttribute("success", SUCCESS);
                return mapping.findForward(SUCCESS);

                /* En el caso que haya un error */
            } else {
                error.add("registro", new ActionMessage("error.codigoDepartamento.existente"));
                saveErrors(request, error);
                return mapping.findForward(YAREGISTRADA);
            }
        }
    }
}