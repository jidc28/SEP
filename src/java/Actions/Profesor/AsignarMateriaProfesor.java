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
    private static final String FAILURE = "failure";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        String id_departamento = (String) session.getAttribute("usbid");
        Profesor profesor = (Profesor) session.getAttribute("profesor");

        MultiBox m = (MultiBox) form;
        String[] materias_seleccionadas = m.getItemsSeleccionados();

        if (materias_seleccionadas.length == 0) {
            MultiBox materias = new MultiBox();
            materias.getMaterias(id_departamento,profesor.getUsbid());
            request.setAttribute("materias", materias);
            request.setAttribute("no_seleccionado", FAILURE);
            return mapping.findForward(FAILURE);
        } else {
            ArrayList<Materia> materias =
                    DBMS.getInstance().consultarMateriasSeleccionadas(materias_seleccionadas);

            session.setAttribute("materias_seleccionadas", materias_seleccionadas);
            request.setAttribute("materias", materias);
            return mapping.findForward(SUCCESS);
        }
    }
}