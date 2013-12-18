/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Materia;

import DBMS.DBMS;
import Clases.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smaf
 */
public class agregaMateria extends org.apache.struts.action.Action {

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
        
        if (tipousuario.equals("coordinacion")){
            Materia m = (Materia) form;
            boolean vincular = DBMS.getInstance().vincularMateriaCoordinacion(usuario.getUsbid(),m.getCodigo());
            ArrayList<Materia> materias = DBMS.getInstance().listarMateriasCoordinacion(usuario.getUsbid());
            if (vincular) {
                session.removeAttribute("dpto_seleccionado");
                request.setAttribute("materias", materias);
                request.setAttribute("materias_vinculadas", SUCCESS);
                request.setAttribute("materia_vinculada", SUCCESS);
                return mapping.findForward(SUCCESS);
            } else {
                request.setAttribute("materias", materias);
                request.setAttribute("materias_vinculadas", SUCCESS);
                request.setAttribute("materia_falla_vinculado", FAILURE);
                return mapping.findForward(FAILURE);
            }
        }
        
        return mapping.findForward(FAILURE);
    }
}
