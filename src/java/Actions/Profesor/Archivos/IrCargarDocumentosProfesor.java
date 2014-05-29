package Actions.Profesor.Archivos;

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
public class IrCargarDocumentosProfesor extends Action {

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

        int[] anos = new int[2];
        anos[1] = Integer.parseInt(ano);
        anos[0] = anos[1] - 1;

        request.setAttribute("anos", anos);
        request.setAttribute("profesor", profesor);
        return mapping.findForward(SUCCESS);
    }
}
