package Actions.Profesor;

import Clases.*;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ModificarProfesor extends Action {

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        HttpSession session = request.getSession(true);
        String id_departamento = (String) session.getAttribute("usbid");
        
        Profesor profesor = (Profesor) form;
        
        ActionErrors error = new ActionErrors();
        error = profesor.validateAgregar(mapping, request);


        if (error.size() != 0) {          
            saveErrors(request, error);
            String USBID = profesor.getUsbidViejo();
            profesor = new Profesor();
            profesor = DBMS.getInstance().obtenerInfoProfesor(USBID);
            profesor.setUsbidViejo(USBID);
        
            request.setAttribute("profesor", profesor);
            return mapping.findForward(FAILURE);
        } else {
        
            profesor.setEmail(profesor.getUsbid()+"@usb.ve");

            boolean modificado = DBMS.getInstance().modificarProfesor(profesor);
            
            if (!modificado) {
                String USBID = profesor.getUsbidViejo();
                profesor = new Profesor();
                profesor = DBMS.getInstance().obtenerInfoProfesor(USBID);
                profesor.setUsbidViejo(USBID);

                request.setAttribute("profesor", profesor);
                request.setAttribute("usbid_existente",FAILURE);
                return mapping.findForward(FAILURE);
            }

            ArrayList<Profesor> profesores =
                    DBMS.getInstance().listarProfesoresDepartamento(id_departamento);

            request.setAttribute("profesores", profesores);
            return mapping.findForward(SUCCESS);
        }
    }
}