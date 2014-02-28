/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        String tipousuario = usuario.getTipousuario();
        //obtengo una lista de departamentos registrados
        //si existen departamentos registrados

        departamentos = DBMS.getInstance().listarDepartamentos();
        session.setAttribute("departamentos", departamentos);
        //retorno a pagina de exito
        if (tipousuario.equals("coordinacion") || tipousuario.equals("administrador")) {
            return mapping.findForward(tipousuario);
        } else {
            return mapping.findForward(NO_AUTORIZADO);
        }
    }
}
