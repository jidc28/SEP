/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Materia;

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
 * @author admin
 */
public class FinalizarSolicitudApertura extends org.apache.struts.action.Action {

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

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Materia materia = (Materia) form;

        Materia vieja_materia = new Materia();
        vieja_materia.setCodigo(materia.getViejoCodigo());
        vieja_materia = DBMS.getInstance().obtenerDatosMateria(vieja_materia);

        String[] caracteres = vieja_materia.getCodigo().split("");

        vieja_materia.setCod1(caracteres[1] + caracteres[2]);
        vieja_materia.setCod2(caracteres[3]);
        vieja_materia.setNum2(caracteres[4]);
        vieja_materia.setNum3(caracteres[5]);
        vieja_materia.setNum4(caracteres[6]);
        
        vieja_materia.setMensaje(materia.getMensaje());

        boolean finalizada;

        ArrayList<Materia> materias;

        if (materia.getNombre() == null || materia.getCreditos() == null
                || materia.getNombre().equals("") || materia.getCreditos().equals("")) {
            request.setAttribute("campos_vacios", FAILURE);
            request.setAttribute("materia", vieja_materia);
            return mapping.findForward(FAILURE);
        }

        if (!materia.getCreditos().matches("\\d+(.\\d+)?")) {
            request.setAttribute("creditos_incorrecto", materia.getCreditos());
            request.setAttribute("materia", vieja_materia);
            return mapping.findForward(FAILURE);
        }

        if (materia.getCod1() == null) {
            materia.setCod1("");
        }

        if (materia.getCod2() == null) {
            materia.setCod2("");
        }

        if (materia.getNum2() == null) {
            materia.setNum2("");
        }

        if (materia.getNum3() == null) {
            materia.setNum3("");
        }

        if (materia.getNum4() == null) {
            materia.setNum4("");
        }

        materia.setCodigo(materia.getCod1() + materia.getCod2() + materia.getNum2() + materia.getNum3() + materia.getNum4());

        if (materia.getCodigo().length() > 6 || materia.getCodigo().length() < 6) {
            request.setAttribute("codigo_incorrecto", materia.getCodigo());
            materia.setCod1(null);
            materia.setCod2(null);
            materia.setNum2(null);
            materia.setNum3(null);
            materia.setNum4(null);
            request.setAttribute("materia", vieja_materia);
            return mapping.findForward(FAILURE);

        } else {
            Correo email = new Correo();
            email.setAsunto("SEP - Solicitud Apertura Materia");
            if (materia.getSolicitud().equals("no")) {
                email.setMensaje("Se ha solicitado la apertura de una materia a través del"
                        + "\n Sistema de Evaluación de Profesores de la Universidad Simón Bolívar."
                        + "\n\n Por favor, ingrese al sistema mediante el siguiente link:"
                        + "\n\n LINK \n\n");
                finalizada = DBMS.getInstance().aprobarSolicitudMateria(materia, usuario.getUsbid());
            } else {
                email.setMensaje("Se ha solicitado la apertura de una materia a través del"
                        + "\n Sistema de Evaluación de Profesores de la Universidad Simón Bolívar."
                        + "\n\n Por favor, ingrese al sistema mediante el siguiente link:"
                        + "\n\n LINK \n\n");
                finalizada = DBMS.getInstance().negarSolicitudMateria(materia);
            }

            if (finalizada) {
                request.setAttribute("solicitud_procesada", SUCCESS);
            } else {
                request.setAttribute("materia", vieja_materia);
                request.setAttribute("solicitud_no_procesada", FAILURE);
            }

            email.enviarNotificacion(usuario.getUsbid()+"usb.ve");
            materias = DBMS.getInstance().listarMateriasSolicitadasDepartamento(usuario.getUsbid());

            if (materias.isEmpty()) {
                request.setAttribute("vacio", SUCCESS);
            }

            request.setAttribute("materias", materias);
            return mapping.findForward(SUCCESS);
        }
    }
}