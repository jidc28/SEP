/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Departamento.Materia;

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
public class EliminaMateria extends org.apache.struts.action.Action {

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
        Materia id_materia = (Materia) form;

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipousuario = usuario.getTipousuario();
        boolean eliminado = false;

        String id_departamento = (String) session.getAttribute("usbid");
        ArrayList<Materia> materias = null;

        if (tipousuario.equals("departamento")) {
            eliminado = DBMS.getInstance().eliminarMateria(id_materia.getCodigo());
            if (eliminado) {
                request.setAttribute("materia_eliminada",SUCCESS);
            } else {
                request.setAttribute("materia_no_eliminada",FAILURE);
            }
            materias = DBMS.getInstance().listarMateriasOfertadas(id_departamento);
        } else if (tipousuario.equals("coordinacion")) {
            String cod_coord = usuario.getUsbid();
            eliminado = DBMS.getInstance().desvincularMateriaCoordinacion(cod_coord,id_materia.getCodigo());
            materias = DBMS.getInstance().listarMateriasCoordinacion(cod_coord);
            if (eliminado) {
                request.setAttribute("materia_desvinculada",SUCCESS);
            } else {
                request.setAttribute("materia_falla_desvinculado",FAILURE);
            }
        }
        
        if(materias.isEmpty()){
            request.setAttribute("vacio", FAILURE);
        }

        request.setAttribute("materias_vinculadas", SUCCESS);
        request.setAttribute("materias", materias);
        return mapping.findForward(SUCCESS);
    }
}
