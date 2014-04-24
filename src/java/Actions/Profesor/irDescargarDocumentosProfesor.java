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
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author jidc28
 */
public class irDescargarDocumentosProfesor extends Action {
    private static final String SUCCESS = "success";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Profesor profesor = (Profesor) form;

        profesor = DBMS.getInstance().obtenerInfoProfesor(profesor.getUsbid());
        profesor.setUsbidViejo(profesor.getUsbid());
        
        ArrayList<String> paths = DBMS.getInstance().
                listarArchivosProfesor(profesor.getUsbid());
        
        request.setAttribute("paths", paths);
        request.setAttribute("profesor", profesor);
        return mapping.findForward(SUCCESS);
    }
}
