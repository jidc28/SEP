/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Carrera;

import Clases.Carrera;
import Clases.Usuario;
import Clases.Decanato;
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
public class ConsultaCarreraA extends org.apache.struts.action.Action {

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
        ArrayList<Carrera> [] carreras = null;
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipousuario = usuario.getTipousuario();
        //obtengo una lista de carreras registradas
        if (tipousuario.equals("administrador")){
            Decanato d = (Decanato) form;
            carreras = DBMS.getInstance().listarCarrerasDecanato(d.getCodigo());
            d = DBMS.getInstance().obtenerNombreDecanato(d);
            request.setAttribute("decanato",d);
        } else if (tipousuario.equals("decanato")){
            carreras = DBMS.getInstance().listarCarrerasDecanato(usuario.getUsbid());
        }

        //si existen carreras registradas

            //retorno a pagina de exito
         session.setAttribute("carreras_visibles", carreras[0]);
         session.setAttribute("carreras_ocultas", carreras[1]);
         return mapping.findForward(SUCCESS);
    }
}
