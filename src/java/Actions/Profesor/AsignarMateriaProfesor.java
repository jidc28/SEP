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

public class AsignarMateriaProfesor extends Action {

    private static final String SUCCESS = "success";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        String id_departamento = (String) session.getAttribute("usbid");

        MultiBox m = (MultiBox) form;
        String[] materias_seleccionadas = m.getItemsSeleccionados();

        ArrayList<Materia> materias = 
                DBMS.getInstance().consultarMateriasSeleccionadas(materias_seleccionadas);
        
        request.setAttribute("materias",materias);
        return mapping.findForward(SUCCESS);
    }
}