/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions.Profesor.Archivos;

import Clases.*;
import DBMS.*;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
public class CargarDocumentos extends org.apache.struts.action.Action {

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
        FileUpload fileUploadForm = (FileUpload) form;
        String trimestre = fileUploadForm.getTrimestre();
        int ano = fileUploadForm.getAno();
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

                Profesor profesor =
                DBMS.getInstance().obtenerInfoProfesor(usuario);
        profesor.setUsbidViejo(profesor.getUsbid());

        /*Se obtiene la fecha actual para calcular los anos. */    
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String fecha = dateFormat.format(date).toString();
        String fechaAno = fecha.substring(0, 4);

        int[] anos = new int[4];
        anos[3] = Integer.parseInt(fechaAno);
        int j = 2;
        int tmp = anos[3];

        while (j > -1) {
            tmp--;
            anos[j] = tmp;
            j--;
        }

        request.setAttribute("anos", anos);
        request.setAttribute("profesor", profesor);

        
        if (cantArchivos == 0) {
            request.setAttribute("sin_archivos", FAILURE);
            return mapping.findForward(FAILURE);
        }
                
        String filePath = 
                getServlet().getServletContext().getRealPath("/") +
                "Documentos/" + usuario + "/" + ano + "/"
                + trimestre;
        String documentos = getServlet().getServletContext().getRealPath("/") +
                "Documentos/";
        
        System.out.println(filePath+ " FILEPATH");
        System.out.println(documentos+ " Documentos");
        
        folder = new File(documentos);
        if (!folder.exists()){
            folder.mkdir();
        }
        
        /*Se crea la carpeta donde se guardan los archivos, si ya existe, se 
         * sigue adelante
         */
        folder = new File(filePath);
        if (!folder.exists()){
            folder.mkdirs();
        }
        
        // Para cada archivo
        for (int i=0; i<archivos.size(); i++){
            
            //obtenemos el archivo del form
            String descripcion = fileUploadForm.getDescripcion();
            FormFile file = (FormFile) archivos.get(i);
            //obtenemos el nombre
            String fileName = file.getFileName();
            //obtenemos el arreglo de bytes del archivo
            byte[] fileData = file.getFileData();
            
            cant = file.getFileSize();
            
            if (cant > 3145728) {
                request.setAttribute("archivo_muy_pesado", FAILURE);
                return mapping.findForward(FAILURE);
            }
            
            
            if (!file.getContentType().contains("application/pdf")){
                request.setAttribute("archivo_invalido", FAILURE);
                return mapping.findForward(FAILURE);
            }
            
            DBMS.getInstance().agregarEspecificacionesArchivo(usuario,trimestre,
                    ano, fileName, descripcion);
            
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
        
        request.setAttribute("archivo_cargado",SUCCESS);
        return mapping.findForward(SUCCESS);
    }
}