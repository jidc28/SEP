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
public class ConsultarDocumentos extends Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        Archivo archivo = (Archivo) form;
        archivo.setUsbidProfesor(usuario.getUsbid());

        Profesor profesor =
                DBMS.getInstance().obtenerInfoProfesor(usuario.getUsbid());

        profesor.setUsbidViejo(profesor.getUsbid());

        ArrayList<Archivo> archivos =
                DBMS.getInstance().listarArchivosProfesor(archivo);

        ArrayList<String> anos =
                DBMS.getInstance().listarAnosArchivos(usuario.getUsbid());

        ArrayList<String> paths = 
                DBMS.getInstance().listarDirectoriosProfesor(profesor.getUsbid());
        
        request.setAttribute("anos", anos);
        request.setAttribute("paths", paths);
        request.setAttribute("archivos", archivos);
        request.setAttribute("profesor", profesor);
        return mapping.findForward(SUCCESS);
    }
}
