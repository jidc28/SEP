package Actions.Departamento.CargaAcademica;

import Clases.*;
import DBMS.DBMS;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class IrAsignarMateriaProfesor extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        String id_departamento = (String) session.getAttribute("usbid");

        Profesor profesor = (Profesor) form;

        if (profesor == null) {
            profesor = (Profesor) session.getAttribute("profesor");
        }

        profesor = DBMS.getInstance().obtenerInfoProfesor(profesor.getUsbid());

        MultiBox materias = new MultiBox();
        materias.getMaterias(id_departamento, profesor.getUsbid());
        request.setAttribute("materias", materias);

        session.setAttribute("profesor", profesor);
        return mapping.findForward("success");
    }
}