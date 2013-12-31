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
public class IrModificarMateria extends org.apache.struts.action.Action {

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

        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        String codigo_materias = DBMS.getInstance().obtenerDatosDepartamento(usuario.getUsbid());
        Materia materia = (Materia) form;

        //String radio_html = "";
        
        materia = DBMS.getInstance().obtenerDatosMateria(materia);
        materia.setViejoCodigo(materia.getCodigo());
        
        
        /*for (int i = 1; i < 5; i++) {
        
            radio_html = radio_html + "<td style='padding-left: 5px;'>";
            
            for (int j = 1; j < 10; j++) {
                radio_html = radio_html + "<html:radio "
                        + "property='num"+i+"' value="+j+"> "
                        + j + "</html:radio><br>";
            }
            
            radio_html = radio_html + "</td>";
        }*/
        
        /*request.setAttribute("radio_html",radio_html);*/
                
        String [] caracteres = materia.getCodigo().split(codigo_materias);
        
        String [] nums = caracteres[1].split("");
        
        for (int i = 0; i < nums.length; i ++) {
            if (!nums[i].equals("")) {
                if (i == 1) {
                    materia.setNum1(nums[i]);
                } else if (i == 2) {
                    materia.setNum2(nums[i]);
                } else if (i == 3) {
                    materia.setNum3(nums[i]);
                } else {
                    materia.setNum4(nums[i]);
                }
            }
        }
        
        request.setAttribute("codigo_materias",codigo_materias);
        request.setAttribute("materia",materia);
        return mapping.findForward(SUCCESS);
    }
}
