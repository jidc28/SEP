/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Coordinacion;

import Clases.*;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Langtech
 */
public class ConsultaCoordinacionA extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String FAILURE = "failure";
    private static final String SESION_EXPIRADA = "sesion_expirada";

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

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        /* En caso de haber expirado la sesion se direcciona a la vista que
         * le indica al usuario que debe volver a iniciar sesion. */
        if (usuario == null) {
            return mapping.findForward(SESION_EXPIRADA);
        }

        String tipousuario = usuario.getTipousuario();

        ArrayList<Coordinacion> coords = null;
        ArrayList<Decanato> decanatos;

        /* Se obtiene la lista de coordinaciones registradas de acuerdo
         * al tipo de usuario que solicita el listado de coordinaciones*/
        if (tipousuario.equals("administrador")) {

            Decanato decanato = (Decanato) form;
            if (decanato.getNombre() != null) {
                System.out.println(decanato.getNombre());
                decanato.setCodigo(DBMS.getInstance().obtenerCodigoDecanato(decanato));
                coords = DBMS.getInstance().listarCoordinacionesAdscritas(decanato.getCodigo(), null);
                session.setAttribute("codigoDecanatoActual", decanato.getCodigo());
            }

            decanatos = DBMS.getInstance().listarDecanatos();
            request.setAttribute("decanatos", decanatos);
        } else if (tipousuario.equals("decanato")) {

            coords = DBMS.getInstance().listarCoordinacionesAdscritas(usuario.getUsbid(), null);
        }

        /* Envio del listado de coordinaciones a la vista correspondiente */
        request.setAttribute("coordinaciones", coords);
        
        /* Si el tipo del usuario es administrador o decanato se le autoriza
         * la realizacion de esta operacion, en caso contrario se direcciona
         * a la vista que le notifica que no esta autorizado para realizar
         * dicha operacion */
        if (tipousuario.equals("administrador") || tipousuario.equals("decanato")) {
            return mapping.findForward(tipousuario);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}