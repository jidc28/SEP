package Actions.Coordinacion;

import Clases.*;
import DBMS.DBMS;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GuardarInformacionCoordinacion extends Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        String id_coordinacion = (String) session.getAttribute("usbid");
        String usbid_profesor = 
                ((Profesor) session.getAttribute("profesor")).getUsbid();
        
        InformacionProfesorCoord informacion = (InformacionProfesorCoord) form;

        boolean procesado = DBMS.getInstance().
                actualizarInformacionProfesorCoordinacion(id_coordinacion,usbid_profesor,informacion);

        request.setAttribute("informacion", informacion);
        request.setAttribute("informacion_coordinacion",SUCCESS);
        
        return mapping.findForward(SUCCESS);
    }
}