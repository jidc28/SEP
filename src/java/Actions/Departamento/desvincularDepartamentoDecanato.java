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
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author jidc28
 */
public class desvincularDepartamentoDecanato extends org.apache.struts.action.Action {
    
    private static final String FAILURE = "failure";
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
        Decanato decanato = (Decanato) session.getAttribute("decanato_actual");
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipousuario = usuario.getTipousuario();
        ActionErrors error = new ActionErrors();
        
        boolean desvincular = DBMS.getInstance().desvincularDepartamentoDecanato(u);
        
        if (!desvincular){
            request.setAttribute("noEliminado",FAILURE);
            return mapping.findForward(FAILURE);
        } else {
            request.setAttribute("eliminado",SUCCESS);
            ArrayList<Departamento>[] departamentos = null;
            
            if(tipousuario.equals("administrador")){
                Decanato d = (Decanato) session.getAttribute("decanato_actual");
                departamentos = DBMS.getInstance().listarDepartamentosDecanato(d.getCodigo());
                d = DBMS.getInstance().obtenerNombreDecanato(d);
            } else if (tipousuario.equals("decanato")) {
                departamentos = DBMS.getInstance().listarDepartamentosDecanato(usuario.getUsbid());
            }
            
            request.setAttribute("success",SUCCESS);
            session.setAttribute("departamentos_visibles", departamentos[0]);
            session.setAttribute("departamentos_ocultos", departamentos[1]);
            return mapping.findForward(tipousuario);
        }
        
    }
}
