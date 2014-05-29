package Actions.Departamento.Evaluaciones;

import Clases.MultiBox;
import Clases.Profesor;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EnviarMemoEvaluarProfesores extends Action {

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        String id_departamento = (String) session.getAttribute("usbid");

        MultiBox m = (MultiBox) form;
        String[] profesores_seleccionados = m.getItemsSeleccionados();
        
        /*System.out.println("Ahora vienen los profs");
        for (int i = 0; i < profesores_seleccionados.length; i++) {
            System.out.println("usbid: " + profesores_seleccionados[i]);
        }*/
        
        if (profesores_seleccionados.length == 0) {
            request.setAttribute("no_seleccionado", FAILURE);
        } else {
            request.setAttribute("enviado_memo", SUCCESS);
        }
        DBMS.getInstance().enviarMemoEvaluarProfesor(profesores_seleccionados,id_departamento);
        m.getProfesores(id_departamento);
        request.setAttribute("profesores", m);
        return mapping.findForward(SUCCESS);
    }
}