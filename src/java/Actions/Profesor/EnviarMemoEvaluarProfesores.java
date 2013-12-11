package Actions.Profesor;

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
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        HttpSession session = request.getSession(true);
        String id_departamento = (String) session.getAttribute("usbid");
        
        MultiBox m = (MultiBox) form;
        String [] profesores_seleccionados = m.getProfesoresSeleccionados();
        System.out.println("Ahora vienen los profs");
        for (int i=0; i < profesores_seleccionados.length; i++) {
            System.out.println("usbid: "+profesores_seleccionados[i]);
        }
        DBMS.getInstance().enviarMemoEvaluar(profesores_seleccionados);
        m.getProfesores(id_departamento);
        request.setAttribute("profesores",m);
        request.setAttribute("enviado_memo", SUCCESS);
        return mapping.findForward(SUCCESS);
    }
}