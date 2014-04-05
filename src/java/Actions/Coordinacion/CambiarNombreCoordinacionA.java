package Actions.Coordinacion;

import Clases.*;
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
public class CambiarNombreCoordinacionA extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
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

        Coordinacion u = (Coordinacion) form;
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        /* En caso de haber expirado la sesion se direcciona a la vista que
         * le indica al usuario que debe volver a iniciar sesion. */
        if (usuario == null) {
            return mapping.findForward(SESION_EXPIRADA);
        }

        String tipousuario = usuario.getTipousuario();

        ActionErrors error;

        /* Se validan los campos insertados en el formulario */
        error = u.validate(mapping, request);

        /* En el caso en que los campos no sean validos */
        if (error.size() != 0) {
            Coordinacion c = DBMS.getInstance().obtenerNombreCoordinacion(u);
            request.setAttribute("codigo", c.getCodigo());
            request.setAttribute("nombre", c.getNombre());
            saveErrors(request, error);
            return mapping.findForward(FAILURE + "_" + tipousuario);
            /* En el caso en que los campos son validos*/
        } else {

            boolean actualizo = DBMS.getInstance().actualizarNombreCoordinacion(u);

            /* El caso en que se actualizaron los datos de manera exitosa en
             * la base de datos */
            if (actualizo) {
                String codigo_decanato;

                if (tipousuario.equals("administrador")) {
                    codigo_decanato = (String) session.getAttribute("codigoDecanatoActual");
                    ArrayList<Decanato> decanatos = DBMS.getInstance().listarDecanatos();
                    request.setAttribute("decanatos", decanatos);
                } else {
                    codigo_decanato = (String) session.getAttribute("usbid");
                }
                ArrayList<Coordinacion> coords =
                        DBMS.getInstance().listarCoordinacionesAdscritas(codigo_decanato, null);

                /* Sen envian a la vista las variables correspondientes*/
                request.setAttribute("coordinaciones", coords);
                request.setAttribute("modificacion", SUCCESS);

                return mapping.findForward(tipousuario);

                /* El caso en que NO se actualizaron los datos de manera exitosa en
                 * la base de datos */
            } else {
                Coordinacion c = DBMS.getInstance().obtenerNombreCoordinacion(u);
                error.add("registro", new ActionMessage("error.coordinacion.existente"));
                request.setAttribute("codigo", c.getCodigo());
                request.setAttribute("nombre", c.getNombre());
                saveErrors(request, error);
                return mapping.findForward(FAILURE + "_" + tipousuario);
            }
        }
    }
}