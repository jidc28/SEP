package Actions.Profesor;

import Clases.*;
import java.io.File;
import java.io.FileInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Langtech
 */
public class DescargarDocumentos extends org.apache.struts.action.Action {

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
//        String id_usuario = (String) session.getAttribute("usbid");

        Archivo archivo = (Archivo) form;

        String filePath =
                getServlet().getServletContext().getRealPath("/")
                + "Documentos/" + archivo.getUsbidProfesor() + "/" 
                + archivo.getAno() + "/" + archivo.getTrimestre();
        
        String OUTPUTFILE = filePath + "/" + archivo.getNombre();
        
        System.out.println(OUTPUTFILE);

        /*Descarga de archivos obtenida de 
         * http://www.mkyong.com/struts/struts-download-file-from-website-example/
         */
        response.setContentType("application/octet-stream");

        response.setHeader("Content-Disposition", "attachment;filename=" + archivo.getNombre());

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