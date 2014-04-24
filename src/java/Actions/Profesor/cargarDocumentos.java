/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Profesor;

import Clases.*;
import DBMS.DBMS;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Langtech
 */
public class cargarDocumentos extends org.apache.struts.action.Action {

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
        
        String usuario = (String) session.getAttribute("usbid");
        FileUpload fileUploadForm = (FileUpload) form;
        Profesor user;
        
        File folder; 
        
        // Obtenemos los archivos del array list
        ArrayList archivos = fileUploadForm.getListArchivos();
        ArrayList tam = fileUploadForm.getListArchivos();
        int cant = 0;
        int cantArchivos = 0;
        
        // Obtenemos al usuario
        user = DBMS.getInstance().obtenerInfoProfesor(usuario);
        
        // Revisamos que haya al menos un archivo
        for (int i=0; i<tam.size(); i++){
            FormFile f = (FormFile) tam.get(i);
            //aqui puede ir la condicion de archivo
            if (!f.getFileName().equalsIgnoreCase("")){
                cantArchivos++;
            }
        }
        
        //podemos agregar condiciones de tamano..
        
        String filePath = 
                getServlet().getServletContext().getRealPath("/") +
                "Documentos/" + usuario;
        String documentos = getServlet().getServletContext().getRealPath("/") +
                "Documentos/";
        
        // Guardamos el path de los archivos relacionados con un usuario en BD
        if (!DBMS.getInstance().pathArchivos(usuario, filePath))
            System.out.println("No funciona el insertar");
        
        folder = new File(documentos);
        if (!folder.exists()){
            folder.mkdir();
        }
        
        /*Se crea la carpeta donde se guardan los archivos, si ya existe, se 
         * sigue adelante
         */
        folder = new File(filePath);
        if (!folder.exists()){
            folder.mkdir();
        }
        
        // Para cada archivo
        for (int i=0; i<archivos.size(); i++){
            //obtenemos el archivo del form
            FormFile file = (FormFile) archivos.get(i);
            //obtenemos el nombre
            String fileName = file.getFileName();
            //obtenemos el arreglo de bytes del archivo
            byte[] fileData = file.getFileData();
            
            if (!fileName.equals("")){
                //Crea el archivo
                File newFile;
                newFile = new File(filePath, fileName);
                //Si existe lo sobreescribe
                FileOutputStream fos = new FileOutputStream(newFile);
                fos.write(file.getFileData());
                fos.flush();
                fos.close();
            }
        }
        
        return mapping.findForward(SUCCESS);
    }
}