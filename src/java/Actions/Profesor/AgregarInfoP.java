/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Profesor;

import Clases.Carrera;
import DBMS.DBMS;
import Forms.ModificarInfoPForm;
import Clases.Profesor;
import Sistemas.CCT;
import Sistemas.SINAI;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author admin
 */
public class AgregarInfoP extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ModificarInfoPForm u = (ModificarInfoPForm) form;
        HttpSession session = request.getSession(true);

        //obtengo informacion del profesor actual del sistema
        Profesor info = DBMS.getInstance().obtenerInfoProfesor(u.getUsbid());
        
        ArrayList<SINAI> sinai = DBMS.getInstance().listarSINAI(u.getUsbid());
        ArrayList<CCT> cct = DBMS.getInstance().listarCCT(u.getUsbid());


        //retorno a pagina de exito
        session.setAttribute("usbid", info.getUsbid());
        session.setAttribute("sinai", sinai);
        session.setAttribute("cct", cct);
        return mapping.findForward(SUCCESS);
    }
}