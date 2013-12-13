/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Profesor;

import DBMS.DBMS;
import Forms.ModificarInfoPForm;
import Clases.Profesor;
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
public class modificarInfoP extends org.apache.struts.action.Action {

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


        //retorno a pagina de exito
        session.setAttribute("usbid", info.getUsbid());
        session.setAttribute("email", info.getEmail());

        if (info.getNombre() == null) {
            session.setAttribute("nombre", "");
        } else {
            session.setAttribute("nombre", info.getNombre());
        }
        if (info.getApellido() == null) {
            session.setAttribute("apellido", "");
        } else {
            session.setAttribute("apellido", info.getApellido());
        }

        session.setAttribute("cedula", info.getCedula());

        if (info.getGenero() == null) {
            session.setAttribute("genero", "");
        } else {
            session.setAttribute("genero", info.getGenero());
        }
        if (info.getNivel() == null) {
            session.setAttribute("nivel", "");
        } else {
            session.setAttribute("nivel", info.getNivel());
        }
        if (info.getJubilado() == null) {
            session.setAttribute("jubilado", "");
        } else {
            session.setAttribute("jubilado", info.getJubilado());
        }
        session.setAttribute("lapso_ini", info.getLapso_contractual_inicio());
        session.setAttribute("lapso_fin", info.getLapso_contractual_fin());


        String[] niveles = new String[4];
        String[] niveles_existentes = info.getNiveles();
        String nivel = info.getNivel();
        int j = 0;
        for (int i = 0; i < 5; i++) {
            if (!nivel.equals(niveles_existentes[i])) {
                System.out.println("entro");
                niveles[j] = niveles_existentes[i];
                j++;
            }
        }
        request.setAttribute("niveles", niveles);
        session.setAttribute("profesor", info);

        return mapping.findForward(SUCCESS);
    }
}
