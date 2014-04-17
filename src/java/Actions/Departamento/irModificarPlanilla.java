/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Departamento;

import Clases.*;
import DBMS.DBMS;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author jidc28
 */
public class irModificarPlanilla extends org.apache.struts.action.Action {
    /* forward name="success" path="" */

    private static final String SUCCESS = "success";
    private static final String NO_AUTORIZADO = "no_autorizado";

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
        Profesor profesor = (Profesor) session.getAttribute("profesor");
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipousuario = usuario.getTipousuario();

        if (tipousuario.equals("departamento")) {
            rendimientoProf rendimiento = (rendimientoProf) form;
            rendimiento = DBMS.getInstance().obtenerPlanillaEvaluacionProfesor(profesor.getUsbid(), rendimiento.getCodigo_materia());

            rendimiento.setViejoAno(rendimiento.getAno());
            rendimiento.setViejoTrimestre(rendimiento.getTrimestre());

            /*Se obtienen los años correspondientes */
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String fecha = dateFormat.format(date).toString();
            String ano = fecha.substring(0, 4);

            int[] anos = new int[4];
            anos[3] = Integer.parseInt(ano);
            int i = 2;
            int tmp = anos[3];

            while (i > -1) {
                tmp--;
                anos[i] = tmp;
                i--;
            }

            session.setAttribute("profesor", profesor);
            request.setAttribute("rendimientoProf", rendimiento);
            request.setAttribute("anos", anos);
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(NO_AUTORIZADO);
        }

    }
}
