/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Coordinacion;

import Clases.Coordinacion;
import Clases.Decanato;
import Clases.Usuario;
import DBMS.DBMS;
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
 * @author Langtech
 */
public class RegistrarCoordinacion extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    private static final String YAREGISTRADA = "yaregistrada";

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

        Coordinacion u = (Coordinacion) form;
        HttpSession session = request.getSession(true);
        ActionErrors error = new ActionErrors();

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipousuario = usuario.getTipousuario();

        //valido los campos de formulario
        error = u.validate(mapping, request);
        //si los campos no son validos
        if (error.size() != 0) {

            saveErrors(request, error);
            if (tipousuario.equals("administrador")) {
                ArrayList<Decanato> decanatos = DBMS.getInstance().listarDecanatos();
                request.setAttribute("decanatos", decanatos);
            }
            return mapping.findForward(FAILURE + "_" + tipousuario);
            //si los campos son validos
        } else {

            if (tipousuario.equals("administrador")) {


                boolean registro = DBMS.getInstance().adscribirCoordinacion(u, u.getDecanato(), false);

                if (registro) {
                    ArrayList<Decanato> decanatos = DBMS.getInstance().listarDecanatos();
                    request.setAttribute("decanatos", decanatos);
                    request.setAttribute("success", SUCCESS);
                    return mapping.findForward(tipousuario);
                } else {
                    ArrayList<Decanato> decanatos = DBMS.getInstance().listarDecanatos();
                    request.setAttribute("decanatos", decanatos);
                    error.add("registro", new ActionMessage("error.coordinacion.existente"));
                    saveErrors(request, error);
                    return mapping.findForward(YAREGISTRADA + "_administrador");
                }
            } else if (tipousuario.equals("decanato")) {

                boolean registro = DBMS.getInstance().adscribirCoordinacion(u, usuario.getUsbid(), true);

                if (registro) {
                    ArrayList<Coordinacion> coords = DBMS.getInstance().listarCoordinaciones();
                    request.setAttribute("coordinaciones", coords);
                    request.setAttribute("success", SUCCESS);
                    return mapping.findForward(tipousuario);
                } else {
                    error.add("registro", new ActionMessage("error.coordinacion.existente"));
                    saveErrors(request, error);
                    return mapping.findForward(YAREGISTRADA + "_decanato");
                }
            }
            return mapping.findForward(tipousuario);
        }
    }
}
