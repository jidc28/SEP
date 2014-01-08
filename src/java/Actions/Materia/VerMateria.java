/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Materia;

import DBMS.DBMS;
import Clases.*;
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
public class VerMateria extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
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

        Materia materia = (Materia) form;

        materia = DBMS.getInstance().obtenerDatosMateria(materia);
        materia.setViejoCodigo(materia.getCodigo());

        String[] caracteres = materia.getCodigo().split("");

        materia.setCod1(caracteres[1] + caracteres[2]);
        
        if (caracteres[3].equals("0") || caracteres[3].equals("1")
                || caracteres[3].equals("2") || caracteres[3].equals("3")
                || caracteres[3].equals("4") || caracteres[3].equals("5")
                || caracteres[3].equals("6") || caracteres[3].equals("7")
                || caracteres[3].equals("8") || caracteres[3].equals("9")) {
            
            materia.setNum1(caracteres[3]);
            materia.setNum2(caracteres[4]);
            materia.setNum3(caracteres[5]);
            materia.setNum4(caracteres[6]);
            
        } else {
            
            materia.setCod2(caracteres[3]);
            materia.setNum1(caracteres[4]);
            materia.setNum2(caracteres[5]);
            materia.setNum3(caracteres[6]);
        }

        request.setAttribute("aprobar","aprobar");
        request.setAttribute("materia", materia);
        return mapping.findForward(SUCCESS);
    }
}
