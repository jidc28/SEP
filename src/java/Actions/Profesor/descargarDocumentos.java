/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Profesor;

import Clases.*;
import DBMS.DBMS;

import java.io.File;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Langtech
 */
public class descargarDocumentos extends org.apache.struts.action.Action {

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
        String usuario = (String) session.getAttribute("usbid");

        String filePath =
                getServlet().getServletContext().getRealPath("/")
                + "Documentos/" + usuario;
        String OUTPUTFILE = filePath + "/000_128298051.jpg";

        /*Descarga de archivos obtenida de 
         * http://www.mkyong.com/struts/struts-download-file-from-website-example/
         */
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=000_128298051.jpg");

        try {
            //Get it from file system
            FileInputStream in =
                    new FileInputStream(new File(OUTPUTFILE));

            ServletOutputStream out = response.getOutputStream();

            byte[] outputByte = new byte[4096];
            //copy binary content to output stream
            while (in.read(outputByte, 0, 4096) != -1) {
                out.write(outputByte, 0, 4096);
            }
            in.close();
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward(FAILURE);
        }

        return mapping.findForward(SUCCESS);

    }
}