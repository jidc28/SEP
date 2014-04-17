package Actions.Evaluaciones;

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
 * @author smaf
 */
public class ListarEvaluacionesEnviadas extends org.apache.struts.action.Action {

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

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String id = usuario.getUsbid();
        String tipousuario = usuario.getTipousuario();

        /* Se obtienen los datos requeridos por la consulta */
        rendimientoProf rendimiento = (rendimientoProf) form;
        int ano = rendimiento.getAno();
        String trimestre = rendimiento.getTrimestre();

        ArrayList<rendimientoProf> evaluaciones_enviadas = null;

        /* Dependiendo del tipo de usuario se consultan las evaluaciones
         * enviadas */
        if (tipousuario.equals("coordinacion")) {

            evaluaciones_enviadas =
                    DBMS.getInstance().listarEvaluacionesEnviadasCoordinacion(id, ano, trimestre);
            
        } else if (tipousuario.equals("departamento")) {

            evaluaciones_enviadas =
                    DBMS.getInstance().listarEvaluacionesEnviadasDepartamento(id, ano, trimestre);
        }

        /* En caso en el que no existan evaluaciones enviadas */
        if (evaluaciones_enviadas.isEmpty()) {
            request.setAttribute("vacio", SUCCESS);
        }

        /* Se envian los atributos a la vista */
        session.setAttribute("ano", ano);
        session.setAttribute("trimestre", trimestre);
        request.setAttribute("evaluaciones_enviadas", evaluaciones_enviadas);
        return mapping.findForward(SUCCESS);
    }
}