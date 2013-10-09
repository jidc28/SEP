/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Decanato;

import com.myapp.struts.DBMS;
import com.myapp.struts.Decanato;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author admin
 */
public class CambiarNombreDecanatoA extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

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

        Decanato u = (Decanato) form;
        HttpSession session = request.getSession(true);

        ActionErrors error = new ActionErrors();

        //valido los campos de formulario
        error = u.validate(mapping, request);
        boolean huboError = false;

        
        if (error.size() != 0) {
            huboError = true;
        }

        //si los campos no son validos
        if (huboError) {
            return mapping.findForward(FAILURE);
            //si los campos son validos
        } else {

            //cambia el nombre del decanato del sistema 
            boolean actualizo = DBMS.getInstance().actualizarNombreDecanato(u);

            if (actualizo) {
                //obtengo una lista de decanatos registrados
                ArrayList<Decanato> decan = DBMS.getInstance().listarDecanatos();

                //si existen decanato registrados

                //retorno a pagina de exito
                session.setAttribute("decanatos", decan);


                return mapping.findForward(SUCCESS);
            } else {
                error.add("actualizacion", new ActionMessage("error.actualizacion.errada"));
                saveErrors(request, error);
                return mapping.findForward(FAILURE);
            }
        }
    }
}
