/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Departamento;

import Clases.Decanato;
import Clases.Departamento;
import Clases.Usuario;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author jidc28
 */
public class ocultarDepartamento extends org.apache.struts.action.Action {
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

        Departamento u = (Departamento) form;
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        ActionErrors error = new ActionErrors();
        ArrayList<Departamento> [] departamentos = null;

        u.setEstado("oculta");
        boolean actualizo = DBMS.getInstance().actualizarEstadoDepartamento(u);

        if (usuario.getTipousuario().equals("decanato")) {
            departamentos = DBMS.getInstance().listarDepartamentosDecanato(usuario.getUsbid());
        } else if (usuario.getTipousuario().equals("administrador")){
            Decanato decanato = (Decanato) session.getAttribute("decanato_actual");
            departamentos = DBMS.getInstance().listarDepartamentosDecanato(decanato.getCodigo());
        }


        //retorno a pagina de exito
        session.setAttribute("departamentos_visibles", departamentos[0]);
        session.setAttribute("departamentos_ocultos", departamentos[1]);
        request.setAttribute("modificacion", SUCCESS);
        return mapping.findForward(usuario.getTipousuario());
    }
}
