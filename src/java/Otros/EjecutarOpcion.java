/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;

import Clases.*;
import DBMS.DBMS;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author smaf
 */
public class EjecutarOpcion extends org.apache.struts.action.Action {

    private static final String PROFESOR = "profesor";
    private static final String ADMINISTRADOR = "administrador";
    private static final String DECANATO = "decanato";
    private static final String COORDINACION = "coordinacion";
    private static final String DEPARTAMENTO = "departamento";

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
        Usuario u = (Usuario) session.getAttribute("usuario");

        Usuario usuario = (Usuario) form;
        String opcion = usuario.getOpcion();
        String tipousuario = u.getTipousuario();

        request.setAttribute(opcion, opcion);
        
//        if (tipousuario.equals("departamento")) {
//
//            if (opcion.equals("gestion_materias")) {
//                request.setAttribute("gestion_materias", DEPARTAMENTO);
//            } else if (opcion.equals("gestion_profesores")) {
//                request.setAttribute("gestion_profesores", DEPARTAMENTO);
//            } else if (opcion.equals("gestion_evaluaciones")) {
//                request.setAttribute("gestion_evaluaciones", DEPARTAMENTO);
//            }
//
//            int solicitudes_pendientes =
//                    DBMS.getInstance().contarSolicitudesPendientesDepartamento(u.getUsbid());
//            if (solicitudes_pendientes != 0) {
//                request.setAttribute("solicitud_apertura_materia", solicitudes_pendientes);
//            }
//            return mapping.findForward(DEPARTAMENTO);
//        }
        return mapping.findForward(tipousuario);
    }
}
