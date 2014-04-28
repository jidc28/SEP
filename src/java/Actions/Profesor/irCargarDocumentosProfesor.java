package Actions.Profesor;

import Clases.*;
import DBMS.DBMS;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author jidc28
 */
public class irCargarDocumentosProfesor extends Action {

    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        /* Se obtiene la información del profesor. */
        Profesor profesor =
                DBMS.getInstance().obtenerInfoProfesor(usuario.getUsbid());
        profesor.setUsbidViejo(profesor.getUsbid());

        /*Se obtiene la fecha actual para calcular los anos. */
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

        request.setAttribute("anos", anos);
        request.setAttribute("profesor", profesor);
        return mapping.findForward(SUCCESS);
    }
}
