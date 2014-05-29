package Actions.Departamento.Profesor;

import Clases.*;
import DBMS.DBMS;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class IrModificarProfesor extends Action {

    private static final String SUCCESS = "success";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Profesor profesor = (Profesor) form;

        profesor = DBMS.getInstance().obtenerInfoProfesor(profesor.getUsbid());
        profesor.setUsbidViejo(profesor.getUsbid());
        
        request.setAttribute("profesor", profesor);
        return mapping.findForward(SUCCESS);
    }
}