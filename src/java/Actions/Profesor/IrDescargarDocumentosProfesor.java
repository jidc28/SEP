/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Profesor;

import Clases.*;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author jidc28
 */
public class IrDescargarDocumentosProfesor extends Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        Profesor profesor = 
                DBMS.getInstance().obtenerInfoProfesor(usuario.getUsbid());
        profesor.setUsbidViejo(profesor.getUsbid());

        ArrayList<String> paths =
                DBMS.getInstance().listarDirectoriosProfesor(profesor.getUsbid());

        ArrayList<String> anos = 
                DBMS.getInstance().listarAnosArchivos(usuario.getUsbid());
        
        request.setAttribute("anos", anos);
        request.setAttribute("paths", paths);
        request.setAttribute("profesor", profesor);
        return mapping.findForward(SUCCESS);
    }
}
