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
public class ConsultaDepartamentoA extends org.apache.struts.action.Action {

    private static final String NO_AUTORIZADO = "no_autorizado";
    private static final String SESION_EXPIRADA = "sesion_expirada";

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
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        /* En caso de haber expirado la sesion se direcciona a la vista que
         * le indica al usuario que debe volver a iniciar sesion. */
        if (usuario == null) {
            return mapping.findForward(SESION_EXPIRADA);
        }

        String tipousuario = usuario.getTipousuario();

        /* Si el tipo de usuario es coordinacion o administrador */
        if (tipousuario.equals("coordinacion") || tipousuario.equals("administrador")) {

            /* Se obtiene el listado de los departamentos */
            departamentos = DBMS.getInstance().listarDepartamentos();
            session.setAttribute("departamentos", departamentos);
            return mapping.findForward(tipousuario);
            /* En caso contrario */
        } else {
            /* se direccione a la vista que le comunica
             * al usuario que no esta autorizado para realizar esta accion */
            return mapping.findForward(NO_AUTORIZADO);
        }
    }
}