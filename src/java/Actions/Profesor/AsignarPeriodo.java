package Actions.Profesor;

import Clases.*;
import DBMS.DBMS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AsignarPeriodo extends Action {

    private static final String LLENAR_PERIODO = "llenar_periodo";
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    private static final String NO_AUTORIZADO = "no_autorizado";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        Profesor profesor = (Profesor) session.getAttribute("profesor");
        String id_departamento = (String) session.getAttribute("usbid");

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tipousuario = usuario.getTipousuario();

        if (tipousuario.equals("departamento")) {
            String[] materias_seleccionadas =
                    (String[]) session.getAttribute("materias_seleccionadas");

            String[] materias_resultantes =
                    new String[materias_seleccionadas.length - 1];

            Materia materia = (Materia) form;

            if (materia.getPeriodoSD() == null && materia.getPeriodoEM() == null
                    && materia.getPeriodoAJ() == null && materia.getPeriodoV() == null) {

                ArrayList<Materia> materias =
                        DBMS.getInstance().consultarMateriasSeleccionadas(materias_seleccionadas);

                request.setAttribute("materias", materias);
                session.setAttribute("materias_seleccionadas", materias_seleccionadas);
                request.setAttribute("periodo_vacio", materia);
                System.out.println(materia.getCodigo());
                return mapping.findForward(FAILURE);
            }

            if (materia.getPeriodoSD() != null) {
                DBMS.getInstance().agregarDicta(profesor, materia.getCodigo(), "SD");
            }
            if (materia.getPeriodoEM() != null) {
                DBMS.getInstance().agregarDicta(profesor, materia.getCodigo(), "EM");
            }
            if (materia.getPeriodoAJ() != null) {
                DBMS.getInstance().agregarDicta(profesor, materia.getCodigo(), "AJ");
            }
            if (materia.getPeriodoV() != null) {
                DBMS.getInstance().agregarDicta(profesor, materia.getCodigo(), "V");
            }

            System.out.println(materia.getCodigo());
            String id_materia = materia.getCodigo();

            int j = 0;
            for (int i = 0; i < materias_seleccionadas.length; i++) {
                if (!id_materia.equals(materias_seleccionadas[i])) {
//                System.out.println(materias_seleccionadas[i]);
                    materias_resultantes[j] = materias_seleccionadas[i];
                    j++;
                }
            }

            for (int i = 0; i < materias_resultantes.length; i++) {
                System.out.println("materias_resultantes: " + materias_resultantes[i]);
            }

            if (materias_resultantes.length == 0) {
                ArrayList<Profesor> profesores =
                        DBMS.getInstance().listarProfesoresDepartamento(id_departamento);

                request.setAttribute("profesores", profesores);
                return mapping.findForward(SUCCESS);
            } else {

                ArrayList<Materia> materias =
                        DBMS.getInstance().consultarMateriasSeleccionadas(materias_resultantes);

                request.setAttribute("materias", materias);
                session.setAttribute("materias_seleccionadas", materias_resultantes);
                return mapping.findForward(LLENAR_PERIODO);
            }
        } else {
            return mapping.findForward(NO_AUTORIZADO);
        }
    }
}