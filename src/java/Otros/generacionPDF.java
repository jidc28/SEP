/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Otros;

import Clases.*;
import DBMS.DBMS;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.html.WebColors;

/**
 *
 * @author jidc28 Esta función fue desarrolada gracias a la ayuda de Andrea
 * Balbás y Gustavo Ortega, ambos estudiantes cohorte 09 de la Universidad Simón
 * Bolívar, quienes estuvieron involucrados en la implementación del Sistema de
 * Gestión de Intercambio de la USB.
 */
public class generacionPDF {
    
    public static Float calcularPorcentaje(int total, int parte) {
        Float resultado = (float) (parte * 100) / total;
        return resultado;
    }

    public static Boolean generarRendimiento(String path, String usbid, String filepath) throws BadElementException, DocumentException {

        Profesor p = DBMS.getInstance().obtenerInfoProfesor(usbid);
        /* Se obtiene el rendimiento del profesor determinado asociado con
         * la materia que dicta el prof */
        rendimientoProf evaluacion = DBMS.getInstance().obtenerEvaluacionPDF(p.getUsbid());
        InformacionProfesorCoord info = DBMS.getInstance().resumenInformacionProfesor(p.getUsbid());
        
        int total = evaluacion.getTotal_estudiantes();
        String porcentaje1 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota1()));
        String porcentaje2 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota2()));
        String porcentaje3 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota3()));
        String porcentaje4 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota4()));
        String porcentaje5 = String.format("%.2f", calcularPorcentaje(total, evaluacion.getNota5()));
        String porcentajeR = String.format("%.2f", calcularPorcentaje(total, evaluacion.getRetirados()));

        int aplazados = evaluacion.getNota1() + evaluacion.getNota2();
        int aprobados = evaluacion.getNota3() + evaluacion.getNota4()
                    + evaluacion.getNota5();
        String porcentajeApr = String.format("%.2f", calcularPorcentaje(total, aprobados));
        String porcentajeApl = String.format("%.2f", calcularPorcentaje(total, aplazados));

        Document document = new Document(PageSize.LETTER); // Pdf de tamano carta
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Empece a generar");

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String creacion = dateFormat.format(cal.getTime());
            PdfWriter salida = PdfWriter.getInstance(document,
                    new FileOutputStream(path + "/Rendimiento" + usbid + ".pdf"));

            document.addTitle("Memo de Rendimiento."); //Titulo del PDF.
            document.addCreationDate(); // Fecha de cracion del PDF.
            document.addCreator("Langtech"); // Creador del PDF.
            document.setMargins(36, 72, 108, 180);

            // FONTS
            Font fontTitulos1 = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK);
            //PAra titulos de bloque
            Font fontTitulos = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
            //PAra Subtitulos de bloque
            Font fontTitulos2 = new Font(FontFamily.HELVETICA, 7, Font.BOLD, BaseColor.BLACK);
            //PAra Opciones
            Font fontTitulos3 = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
            //Para input
            Font fontCampo = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
            //Para labels
            Font fontCampo2 = new Font(FontFamily.HELVETICA, 8, Font.BOLDITALIC, BaseColor.BLACK);
            //para textos pequeños
            Font fontPequena = new Font(FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.BLACK);

            // Color Areas
            BaseColor color = WebColors.getRGBColor("B0C2CB"); //gris clarito

            //Apertura Documento
            document.open();

            PdfContentByte canvas = salida.getDirectContent();


            /* ###########################################################
             * #  Header del pdf, titulo, foto del aplicante y logo USB. #
             * ###########################################################*/
            ColumnText ct = new ColumnText(canvas);

            //Estampado de fecha y hora de creacion
            Phrase campo = new Phrase("Fecha Creacion: " + creacion, fontCampo);
            ct.setSimpleColumn(campo, 200, 70, 580, 80, 10, Element.ALIGN_RIGHT);
            ct.go();

            // TITULO PRINCIPAL
            Phrase titulo = new Phrase("RESUMEN DE RENDIMIENTO DE PROFESOR", fontTitulos1);
            //ct.setSimpleColumn(titulo, 30, 660, 600, 690, 25, Element.ALIGN_CENTER);
            ct.setSimpleColumn(titulo, 68, 660, 600, 690, 25, Element.ALIGN_LEFT);
            ct.go();

            //IMAGEN APLICANTE
            //Cambiar Path
            //CEBOLLA
            Image imagen = Image.getInstance(filepath + "cebollaUSB.jpg");
            imagen.setAbsolutePosition(125f, 730f);
            imagen.scalePercent(20f);


            campo = new Phrase("                                             \n"
                    + "TITULO 1\n"
                    + "TITULO 1 PARTE 2", fontTitulos2);
            ct.setSimpleColumn(campo, 200, 625, 480, 730, 10, Element.ALIGN_RIGHT);
            ct.go();

            // agrega la imagen al pdf
            document.add(imagen);
            campo = new Phrase("                UNIVERSIDAD SIMÓN BOLÍVAR\n"
                    + "                                RECTORADO\n"
                    + "Dirección de Relaciones Internacionales y de Cooperación", fontTitulos2);
            ct.setSimpleColumn(campo, 60, 625, 340, 730, 10, Element.ALIGN_LEFT);
            ct.go();

            /* #################################
             * #      Datos Personales         #
             * #################################*/

            // Background area
            canvas.saveState();
            canvas.roundRectangle(70, 630, 455, 20, 6);
            canvas.setColorFill(color);
            canvas.fill();
            canvas.stroke();
            canvas.restoreState();
            // Titulo del area
            campo = new Phrase("  DATOS PERSONALES", fontTitulos);
            ct.setSimpleColumn(campo, 70, 630, 560, 645, 10, Element.ALIGN_LEFT);
            ct.go();

            //Nombre
            campo = new Phrase("    Nombre Completo:  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 610, 300, 620, 10, Element.ALIGN_LEFT);
            campo = new Phrase(p.getNombre() + " " + p.getApellido(), fontCampo);
            ct.setSimpleColumn(campo, 70, 610, 300, 620, 10, Element.ALIGN_LEFT);
            ct.go();

            // Sexo
            campo = new Phrase("   Sexo:  ", fontCampo2);
            ct.setSimpleColumn(campo, 350, 570, 600, 580, 10, Element.ALIGN_LEFT);
            if (p.getGenero().equals("M")) {
                campo = new Phrase("Masculino", fontCampo);
            } else {
                campo = new Phrase("Femenino", fontCampo);
            }
            ct.setSimpleColumn(campo, 350, 570, 600, 580, 10, Element.ALIGN_LEFT);
            ct.go();

            // Email institucional
            campo = new Phrase("    Email Institucional:  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 570, 250, 580, 10, Element.ALIGN_LEFT);
            campo = new Phrase(p.getEmail(), fontCampo);
            ct.setSimpleColumn(campo, 70, 570, 250, 580, 10, Element.ALIGN_LEFT);
            ct.go();


            //USBID 
            campo = new Phrase("    USBID:  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 590, 560, 600, 10, Element.ALIGN_LEFT);
            campo = new Phrase(p.getUsbid(), fontCampo);
            ct.setSimpleColumn(campo, 70, 590, 350, 600, 10, Element.ALIGN_LEFT);
            ct.go();

            //Cédula de identidad
            campo = new Phrase("   Cédula de Identidad:  ", fontCampo2);
            ct.setSimpleColumn(campo, 350, 590, 560, 600, 10, Element.ALIGN_LEFT);
            campo = new Phrase(p.getCedula(), fontCampo);
            ct.setSimpleColumn(campo, 350, 590, 560, 600, 10, Element.ALIGN_LEFT);
            ct.go();

            //Email Personal
            campo = new Phrase("    Email Personal:  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 550, 300, 560, 10, Element.ALIGN_LEFT);
            campo = new Phrase(p.getEmail_personal(), fontCampo);
            ct.setSimpleColumn(campo, 70, 550, 300, 560, 10, Element.ALIGN_LEFT);
            ct.go();

            // Jubilacion
            campo = new Phrase("   ¿Ha sido jubilado en el último año de una institución pública?: ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 530, 600, 540, 10, Element.ALIGN_LEFT);
            if (p.getJubilado().equals("S")) {
                campo = new Phrase("Si", fontCampo);
            } else if (p.getJubilado().equals("N")) {
                campo = new Phrase("No", fontCampo);
            } else {
                campo = new Phrase("", fontCampo);
            }
            ct.setSimpleColumn(campo, 70, 530, 600, 540, 10, Element.ALIGN_LEFT);
            ct.go();

            // Nivel
            campo = new Phrase("   Nivel:  ", fontCampo2);
            ct.setSimpleColumn(campo, 350, 550, 600, 560, 10, Element.ALIGN_LEFT);
            campo = new Phrase(p.getNivel(), fontCampo);
            ct.setSimpleColumn(campo, 350, 550, 600, 560, 10, Element.ALIGN_LEFT);
            ct.go();

            // Cuadro Contenedor
            canvas.saveState();
            //canvas.roundRectangle(x, y, w, h, r);
            canvas.roundRectangle(70, 515, 455, 135, 6);
            canvas.stroke();
            canvas.restoreState();


            /* ##################################################
             * #  Evaluacion General                            #
             * ##################################################*/

            // Background area
            canvas.saveState();
            canvas.roundRectangle(70, 485, 455, 20, 6);
            canvas.setColorFill(color);
            canvas.fill();
            canvas.stroke();
            canvas.restoreState();

            // Titulo del area.
            titulo = new Phrase(" INFORMACIÓN PROPORCIONADA POR LAS COORDINACIONES ", fontTitulos);
            ct.setSimpleColumn(titulo, 70, 510, 450, 420, 20, Element.ALIGN_LEFT);
            ct.go();

            // Miembro consejo asesor.
            campo = new Phrase("  ¿Ha sido miembro del consejo asesor?  ", fontCampo2);
//          ct.setSimpleColumn(titulo, llx, lly, urx, ury, leading, alignment);
            ct.setSimpleColumn(campo, 70, 470, 360, 480, 10, Element.ALIGN_LEFT);
            ct.go();
            if (info.getConsejoAsesor().equals("si")){
                campo = new Phrase("Si", fontCampo);
            } else {
                campo = new Phrase("No", fontCampo);
            }
            ct.setSimpleColumn(campo, 360, 470, 450, 480, 10, Element.ALIGN_LEFT);
            ct.go();
            
            // Proyectos de grado, trabajos de grado o tesis doctorales tutoreados.
            campo = new Phrase("  Numero de proyectos de grado, trabajos de grado ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 450, 360, 460, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase("  o tesis doctorales tutoreados  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 440, 360, 450, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase((info.getTesisTutoria()+info.getPasantiaLargaTutor())+"", fontCampo);
            ct.setSimpleColumn(campo, 360, 440, 450, 450, 10, Element.ALIGN_LEFT);
            ct.go();

            // Proyectos de grado, trabajos de grado o tesis doctorales como jurado.
            campo = new Phrase("  Numero de proyectos de grado, trabajos de grado ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 420, 360, 430, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase("  o tesis doctorales evaluadas como jurado  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 410, 360, 420, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase((info.getTesisJurado()+info.getPasantiaLargaJurado())+"", fontCampo);
            ct.setSimpleColumn(campo, 360, 410, 450, 420, 10, Element.ALIGN_LEFT);
            ct.go();

            // Pasantias cortas tutoreadas.
            campo = new Phrase("  Numero de pasantias cortas tutoreadas ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 390, 360, 400, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getPasantiaCorta()+"", fontCampo);
            ct.setSimpleColumn(campo, 360, 390, 450, 400, 10, Element.ALIGN_LEFT);
            ct.go();

            // Pasantias largas tutoreadas.
            campo = new Phrase("  Numero de pasantias largas e intermedias tutoreadas ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 370, 360, 380, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getPasantiaLargaTutor()+"", fontCampo);
            ct.setSimpleColumn(campo, 360, 370, 450, 380, 10, Element.ALIGN_LEFT);
            ct.go();
            
            // Cuadro Contenedor
            canvas.saveState();
            //canvas.roundRectangle(x, y, w, h, r);
            canvas.roundRectangle(70, 360, 455, 145, 6);
            canvas.stroke();
            canvas.restoreState();
            
            /* ###########################
             * #  Cargas Académicas      #
             * ###########################*/
            
            // Background area
            canvas.saveState();
            canvas.roundRectangle(70, 330, 455, 20, 6);
            canvas.setColorFill(color);
            canvas.fill();
            canvas.stroke();
            canvas.restoreState();
            
            // Titulo del area.
            titulo = new Phrase(" CARGA ACADÉMICA ", fontTitulos);
            ct.setSimpleColumn(titulo, 70, 330, 560, 345, 10, Element.ALIGN_LEFT);
            ct.go();
            
            // Pasantias cortas tutoreadas.
            int i;
            int limite = 4;
            for (i=0; i<limite;i++) {
                campo = new Phrase("  Numero de pasantias cortas tutoreadas ", fontCampo2);
                ct.setSimpleColumn(campo, 70, 310-(i*20), 360, 320-(i*20), 10, Element.ALIGN_LEFT);
                ct.go();
                campo = new Phrase("RELLENO", fontCampo);
                ct.setSimpleColumn(campo, 360, 310-(i*20), 450, 320-(i*20), 10, Element.ALIGN_LEFT);
                ct.go();
            }
            
            // Cuadro Contenedor
            canvas.saveState();
            //canvas.roundRectangle(x, y, w, h, r);
            canvas.roundRectangle(70, 320-(i*20), 455, 30+(i*20), 6);
            canvas.stroke();
            canvas.restoreState();

            /* ###########################
             * # Informacion General     #
             * ###########################*/
            
            document.newPage();

            //Estampado de numero de planilla, fecha y hora de creacion
            campo = new Phrase("Fecha Creación:  " + creacion, fontCampo);
            ct.setSimpleColumn(campo, 200, 20, 580, 30, 10, Element.ALIGN_RIGHT);
            ct.go();

            //Background area
            canvas.saveState();
            canvas.roundRectangle(70, 715, 455, 20, 6);
            canvas.setColorFill(color);
            canvas.fill();
            canvas.stroke();
            canvas.restoreState();

            // Titulo del area.
            titulo = new Phrase("  INFORMACIÓN GENERAL  ", fontTitulos);
            ct.setSimpleColumn(titulo, 70, 720, 450, 745, 25, Element.ALIGN_LEFT);
            ct.go();
            
            //Subtitulo Area INFORMACION GENERAL
            titulo = new Phrase(" Información General  ", fontTitulos);
            ct.setSimpleColumn(titulo, 80, 680, 445, 700, 10, Element.ALIGN_LEFT);
            ct.go();
            // Linea Titulo
            canvas.saveState();
            canvas.rectangle(80, 685, 103, 0);
            canvas.stroke();
            canvas.restoreState();

            //Total estudiantes.
            campo = new Phrase("  Total estudiantes  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 660, 360, 670, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(total+"", fontCampo);
            ct.setSimpleColumn(campo, 360, 660, 450, 670, 10, Element.ALIGN_LEFT);
            ct.go();

            //Nota Promedio.
            campo = new Phrase("  Nota promedio  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 640, 360, 650, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(evaluacion.getNota_prom()+"", fontCampo);
            ct.setSimpleColumn(campo, 360, 640, 450, 650, 10, Element.ALIGN_LEFT);
            ct.go();

            //Porcentaje Aplazados.
            campo = new Phrase("  Porcentaje aplazados  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 620, 360, 630, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(porcentajeApl+"%", fontCampo);
            ct.setSimpleColumn(campo, 360, 620, 450, 630, 10, Element.ALIGN_LEFT);
            ct.go();

            //Porcentaje Aprobados.
            campo = new Phrase("  Porcentaje aprobados  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 600, 360, 610, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(porcentajeApr+"%", fontCampo);
            ct.setSimpleColumn(campo, 360, 600, 450, 610, 10, Element.ALIGN_LEFT);
            ct.go();
            
            //Porcentaje Retirados.
            campo = new Phrase("  Porcentaje retirados  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 580, 360, 590, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(porcentajeR+"%", fontCampo);
            ct.setSimpleColumn(campo, 360, 580, 450, 590, 10, Element.ALIGN_LEFT);
            ct.go();
            
            //Subtitulo Area INFORMACION ESPECIFICA
            titulo = new Phrase(" Información Específica  ", fontTitulos);
            ct.setSimpleColumn(titulo, 80, 540, 445, 560, 10, Element.ALIGN_LEFT);
            ct.go();
            // Linea Titulo
            canvas.saveState();
            canvas.rectangle(80, 545, 115, 0);
            canvas.stroke();
            canvas.restoreState();
            
            //Porcentaje con 1.
            campo = new Phrase("  Porcentaje de estudiantes con 1  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 520, 360, 530, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(porcentaje1+"%", fontCampo);
            ct.setSimpleColumn(campo, 360, 520, 450, 530, 10, Element.ALIGN_LEFT);
            ct.go();
            
            //Porcentaje con 2.
            campo = new Phrase("  Porcentaje de estudiantes con 2  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 500, 360, 510, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(porcentaje2+"%", fontCampo);
            ct.setSimpleColumn(campo, 360, 500, 450, 510, 10, Element.ALIGN_LEFT);
            ct.go();
            
            //Porcentaje con 3.
            campo = new Phrase("  Porcentaje de estudiantes con 3  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 480, 360, 490, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(porcentaje3+"%", fontCampo);
            ct.setSimpleColumn(campo, 360, 480, 450, 490, 10, Element.ALIGN_LEFT);
            ct.go();
            
            //Porcentaje con 4.
            campo = new Phrase("  Porcentaje de estudiantes con 4  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 460, 360, 470, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(porcentaje4+"%", fontCampo);
            ct.setSimpleColumn(campo, 360, 460, 450, 470, 10, Element.ALIGN_LEFT);
            ct.go();
            
            //Porcentaje con 5.
            campo = new Phrase("  Porcentaje de estudiantes con 5  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 440, 360, 450, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(porcentaje5+"%", fontCampo);
            ct.setSimpleColumn(campo, 360, 440, 450, 450, 10, Element.ALIGN_LEFT);
            ct.go();
            
            // Cuadro Contenedor
            canvas.saveState();
            //canvas.roundRectangle(x, y, w, h, r);
            canvas.roundRectangle(70, 430, 455, 305, 6);
            canvas.stroke();
            canvas.restoreState();
            
            /* ###########################
             * # Comentarios Coordinacion#
             * ###########################*/
            //GENERACION DINÁMICA DE RECUADROS E INFORMACIÓN
            //LOREM IPSUM OCUPA 60 CORDS
            String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing "
                    + "elit. Donec suscipit aliquam accumsan. Mauris sit amet "
                    + "ligula felis. Vestibulum ante ipsum primis in faucibus "
                    + "orci luctus et ultrices posuere cubilia Curae; Maecenas "
                    + "dapibus, odio vel ullamcorper ullamcorper, odio velit "
                    + "suscipit ipsum, ac vehicula felis augue a quam. "
                    + "Suspendisse potenti. Vivamus feugiat lacinia nunc quis "
                    + "cursus. In sed luctus velit. Fusce non urna accumsan sem "
                    + "hendrerit fermentum sed vel nibh. Vivamus adipiscing urna"
                    + " quis metus.";
            
            //Background area
            canvas.saveState();
            canvas.roundRectangle(70, 400, 455, 20, 6);
            canvas.setColorFill(color);
            canvas.fill();
            canvas.stroke();
            canvas.restoreState();

            // Titulo del area.
            titulo = new Phrase("  COORDINACIONES  ", fontTitulos);
            ct.setSimpleColumn(titulo, 70, 405, 450, 430, 25, Element.ALIGN_LEFT);
            ct.go();

            //DECLARACIONES DINAMICAS
            int varY = 375;
            
            //Subtitulo Area NOMBRE COORDINACION
            if (varY <= 20) {
                document.newPage();
            }
            titulo = new Phrase(" Coordinacion de envios de Email con SPAM intenso para alargar titulo  ", fontTitulos);
            ct.setSimpleColumn(titulo, 70, varY, 445, varY+20, 10, Element.ALIGN_LEFT);
            ct.go();
            
            campo = new Phrase("  Recomendado: ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 365, 450, 375, 10, Element.ALIGN_LEFT);
            campo = new Phrase("NO SI LO QUE SEA", fontCampo);
            ct.setSimpleColumn(campo, 70, 365, 450, 375, 10, Element.ALIGN_LEFT);
            ct.go();
            
            campo = new Phrase("  Justificación :", fontCampo2);
            ct.setSimpleColumn(campo, 70, 345, 450, 355, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(lorem, fontCampo);
            ct.setSimpleColumn(campo, 80, 285, 450, 345, 10, Element.ALIGN_LEFT);
            ct.go();
//
//            //Carrera
//            campo = new Phrase("  - Carrera que estudia en la USB: ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 670, 600, 680, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 70, 670, 600, 680, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            // Índice Académico
//            campo = new Phrase("  - Índice Académico a la Fecha de Postulación: ", fontCampo2);
//            ct.setSimpleColumn(campo, 310, 650, 600, 660, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 310, 650, 600, 660, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            // Área de Estudio
//            campo = new Phrase("  - Área de Estudio: ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 650, 350, 660, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 70, 650, 350, 660, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Tablas de materias
//            canvas.saveState();
//            canvas.rectangle(70, 420, 455, 191);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Rectangulo pequeño (linea)
//            canvas.saveState();
//            canvas.rectangle(70, 580, 455, 30);
//            canvas.setLineWidth(0.9f);
//            BaseColor color3 = WebColors.getRGBColor("B0C2CB");
//            canvas.setColorFill(color3);
//            canvas.fill();
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Línea
//            canvas.saveState();
//            canvas.rectangle(70, 580, 455, 0);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            // Tabla de la seccion 20
//            // Lineas verticales
//            canvas.saveState();
//            canvas.rectangle(130, 420, 0, 190);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            canvas.saveState();
//            canvas.rectangle(257, 420, 0, 190);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            canvas.saveState();
//            canvas.rectangle(297, 420, 0, 190);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//
//
//            // Tabla de la seccion 21
//            // Lineas verticales
//            canvas.saveState();
//            canvas.rectangle(298, 420, 0, 190);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            canvas.saveState();
//            canvas.rectangle(357, 420, 0, 190);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            canvas.saveState();
//            canvas.rectangle(485, 420, 0, 190);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//
//            // Plan de Estudio
//            campo = new Phrase("  - Asignaturas del Plan de Estudio USB que aspira \n "
//                    + "sean convalidadas u otorgadas en equivalencia:  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 620, 350, 640, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            // Vaceado de Materias
//            campo = new Phrase("Código", fontCampo2);
//            ct.setSimpleColumn(campo, 80, 590, 115, 610, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            int vacea = 0;
//            int topeAr = 10;
//            int valorH = 570;
//            while (vacea != topeAr && valorH != 430) {
//                System.out.println("Aqui esta vacea " + vacea);
//                System.out.println("Aqui esta topeAr " + topeAr);
//                //Lleno por fila
//                // Codigo USB    
//                System.out.println("Aqui esta codUSB " + "" + topeAr);
//
//                campo = new Phrase("" + topeAr, fontCampo);
//                ct.setSimpleColumn(campo, 80, valorH, 140, (valorH + 10), 10, Element.ALIGN_LEFT);
//                ct.go();
//                //Nombre USB
//                campo = new Phrase("" + topeAr, fontCampo);
//                ct.setSimpleColumn(campo, 140, valorH, 256, (valorH + 10), 10, Element.ALIGN_LEFT);
//                ct.go();
//                // Creditos USB
//                campo = new Phrase("" + topeAr, fontCampo);
//                ct.setSimpleColumn(campo, 256, valorH, 288, (valorH + 10), 10, Element.ALIGN_CENTER);
//                ct.go();
//                // Codigo Ext
//                campo = new Phrase("" + topeAr, fontCampo);
//                ct.setSimpleColumn(campo, 308, valorH, 358, (valorH + 10), 10, Element.ALIGN_LEFT);
//                ct.go();
//                // Nombre Ext
//                campo = new Phrase("" + topeAr, fontCampo);
//                ct.setSimpleColumn(campo, 368, valorH, 485, (valorH + 10), 10, Element.ALIGN_LEFT);
//                ct.go();
//                // Creditos Ext    
//                campo = new Phrase("" + topeAr, fontCampo);
//                ct.setSimpleColumn(campo, 485, valorH, 525, (valorH + 10), 10, Element.ALIGN_CENTER);
//                ct.go();
//
//                valorH = valorH - 20;
//                vacea = vacea + 1;
//
//            }
//
//
//            campo = new Phrase("Denominación", fontCampo2);
//            ct.setSimpleColumn(campo, 115, 590, 256, 610, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            campo = new Phrase("Créditos \n USB", fontCampo2);
//            ct.setSimpleColumn(campo, 258, 590, 298, 610, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            campo = new Phrase("Código", fontCampo2);
//            ct.setSimpleColumn(campo, 288, 590, 358, 610, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//
//
//            // Asignaturas a cursar
//            campo = new Phrase("  - Asignaturas a cursar en la Universidad de Destino:  ", fontCampo2);
//            ct.setSimpleColumn(campo, 310, 620, 600, 640, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            campo = new Phrase("Denominación", fontCampo2);
//            ct.setSimpleColumn(campo, 358, 590, 485, 610, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            campo = new Phrase("Créditos \n Ext", fontCampo2);
//            ct.setSimpleColumn(campo, 485, 590, 525, 610, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            //Cuadro de Aprobación de la Coordinación
//
//            /*canvas.saveState();
//             //canvas.rectangle(70, 390, 455, 30);
//             canvas.roundRectangle(80, 390, 439, 30,6);
//             canvas.setLineWidth(0.9f);
//             canvas.stroke();
//             canvas.restoreState();*/
//
//            // Mensaje Aprobación
//            campo = new Phrase("Aprobación Coordinación de la Carrera  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 400, 300, 410, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            // línea firma
//            canvas.saveState();
//            canvas.rectangle(310, 400, 170, 0);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            // cuadro contenedor
//            canvas.saveState();
//            canvas.roundRectangle(70, 385, 455, 350, 6);
//            canvas.stroke();
//            canvas.restoreState();
//
//
//            /* ##############################
//             * #  FUENTE DE FINANCIAMIENTOO #
//             * ##############################*/
//
//            // Background del area
//            canvas.saveState();
//            canvas.roundRectangle(70, 355, 455, 20, 6);
//            canvas.setColorFill(color);
//            canvas.fill();
//            canvas.stroke();
//            canvas.restoreState();
//
//            // Titulo del area.
//            titulo = new Phrase("  FUENTE DE FINANCIAMIENTO DEL ESTUDIANTE:  ", fontTitulos);
//            ct.setSimpleColumn(titulo, 70, 350, 500, 370, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Fuente de Ingresos
//            campo = new Phrase("  - Principal Fuente de Ingresos: ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 330, 320, 350, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 70, 330, 320, 350, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            titulo = new Phrase("  * Otro: ", fontCampo2);
//            ct.setSimpleColumn(titulo, 320, 330, 500, 350, 10, Element.ALIGN_LEFT);
//            titulo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(titulo, 320, 330, 500, 350, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Becado o no
//            campo = new Phrase("  - ¿Recibe ayuda económica por \n parte de la universidad u otro organismo?  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 310, 320, 330, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 70, 310, 320, 330, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            campo = new Phrase("  * Especifique:  ", fontCampo2);
//            ct.setSimpleColumn(campo, 320, 310, 550, 330, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 320, 310, 550, 330, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Cuadro contenedor
//
//            canvas.saveState();
//            canvas.roundRectangle(71, 300, 454, 75, 6);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//
//
//            /* ##############################
//             * #  CONOCIMIENTO DE IDIOMAS   #
//             * ##############################*/
//
//            // Background area
//            canvas.saveState();
//            canvas.roundRectangle(70, 270, 455, 20, 6);
//            canvas.setColorFill(color);
//            canvas.fill();
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Background Subtitulos
//            // Cuadro nivel sufi
//            canvas.saveState();
//            canvas.rectangle(161, 254, 364, 14);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Relleno
//            canvas.saveState();
//            canvas.rectangle(71, 235, 453, 20);
//            canvas.setColorFill(color3);
//            canvas.fill();
//            canvas.stroke();
//            canvas.restoreState();
//
//
//            // Titulo del area.
//            titulo = new Phrase("  CONOCIMIENTO DE IDIOMAS  ", fontTitulos);
//            ct.setSimpleColumn(titulo, 70, 280, 450, 300, 20, Element.ALIGN_LEFT);
//            ct.go();
//
//            // Lineas verticales
//            canvas.saveState();
//            canvas.rectangle(161, 155, 0, 100);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            canvas.saveState();
//            canvas.rectangle(252, 155, 0, 100);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            canvas.saveState();
//            canvas.rectangle(343, 155, 0, 100);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            canvas.saveState();
//            canvas.rectangle(434, 155, 0, 100);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//
//            //Primera línea horizontal del cuadro
//            canvas.saveState();
//            canvas.rectangle(70, 255, 455, 0);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            campo = new Phrase("Nivel de Suficiencia", fontCampo2);
//            ct.setSimpleColumn(campo, 160, 260, 520, 270, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            //Segunda linea horizontal
//            canvas.saveState();
//            canvas.rectangle(70, 235, 455, 0);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Titulos Tabla
//            campo = new Phrase("Idioma a emplear ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 240, 160, 250, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            campo = new Phrase("Verbal ", fontCampo2);
//            ct.setSimpleColumn(campo, 160, 240, 250, 250, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            campo = new Phrase("Oral ", fontCampo2);
//            ct.setSimpleColumn(campo, 250, 240, 340, 250, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            campo = new Phrase("Auditivo ", fontCampo2);
//            ct.setSimpleColumn(campo, 340, 240, 430, 250, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            campo = new Phrase("Escrito ", fontCampo2);
//            ct.setSimpleColumn(campo, 430, 240, 520, 250, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            // Vaceado de Idiomas
//            int vacea2 = 0;
//            int topeAr2 = 10;
//            int valorH2 = 220; //Este es el valor para ir bajando
//            while (vacea2 != topeAr2 && valorH2 != 140) {
//                System.out.println("Aqui esta vacea " + vacea2);
//
//                //Lleno por fila
//                // Nombre Idioma    
//                System.out.println("Aqui esta idioma " + topeAr2);
//                campo = new Phrase(topeAr2 + "", fontCampo);
//                ct.setSimpleColumn(campo, 70, valorH2, 160, (valorH2 + 10), 10, Element.ALIGN_CENTER);
//                ct.go();
//                //Nivel verbal
//                campo = new Phrase(topeAr2 + "", fontCampo);
//                ct.setSimpleColumn(campo, 160, valorH2, 250, (valorH2 + 10), 10, Element.ALIGN_CENTER);
//                ct.go();
//                // Nivel oral
//                campo = new Phrase(topeAr2 + "", fontCampo);
//                ct.setSimpleColumn(campo, 250, valorH2, 340, (valorH2 + 10), 10, Element.ALIGN_CENTER);
//                ct.go();
//                // Nivel Auditivo
//                campo = new Phrase(topeAr2 + "", fontCampo);
//                ct.setSimpleColumn(campo, 340, valorH2, 430, (valorH2 + 10), 10, Element.ALIGN_CENTER);
//                ct.go();
//                // Nivel Escrito
//                campo = new Phrase(topeAr2 + "", fontCampo);
//                ct.setSimpleColumn(campo, 430, valorH2, 520, (valorH2 + 10), 10, Element.ALIGN_CENTER);
//                ct.go();
//
//                valorH2 = valorH2 - 20;
//                vacea2 = vacea2 + 1;
//            }
//
//            /* Cuadro contenedor idiomas*/
//            canvas.saveState();
//            canvas.roundRectangle(70, 155, 455, 135, 6);
//            canvas.setLineWidth(0.9f);
//            canvas.stroke();
//            canvas.restoreState();
//
//            /* ##############################
//             * #  	INFO CONTACTO       #
//             * ##############################*/
//
//            // Background del area
//            canvas.saveState();
//            canvas.roundRectangle(70, 125, 455, 20, 6);
//            canvas.setColorFill(color);
//            canvas.fill();
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Titulo del area.
//            titulo = new Phrase("  DATOS DE CONTACTO EN CASO DE EMERGENCIA  ", fontTitulos);
//            ct.setSimpleColumn(titulo, 70, 130, 450, 150, 20, Element.ALIGN_LEFT);
//            ct.go();
//
//            campo = new Phrase("  - Nombre Contacto:  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 110, 500, 120, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO" + " " + "RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 70, 110, 500, 120, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Domicilio Contacto
//            campo = new Phrase("  - Tlf. Habitación Contacto: ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 90, 300, 100, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 70, 90, 300, 100, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            campo = new Phrase("  - Tlf. Celular Contacto:  ", fontCampo2);
//            ct.setSimpleColumn(campo, 320, 90, 600, 100, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 320, 90, 600, 100, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            campo = new Phrase("  - Relación con el estudiante:  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 70, 300, 80, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 70, 70, 300, 80, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            campo = new Phrase("  - Email Contacto:  ", fontCampo2);
//            ct.setSimpleColumn(campo, 320, 70, 600, 80, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 320, 70, 600, 80, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            campo = new Phrase("  - Domicilio Contacto:  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 50, 600, 60, 10, Element.ALIGN_LEFT);
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 70, 50, 600, 60, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Cuadro contenedor
//            canvas.saveState();
//            canvas.roundRectangle(70, 45, 455, 100, 6);
//            canvas.stroke();
//            canvas.restoreState();
//
//
//            //NUEVA PAGINA
//
//            document.newPage();
//
//            //Estampado de numero de planilla, fecha y hora de creacion
//            campo = new Phrase("Fecha Creacion:  " + creacion, fontCampo);
//            ct.setSimpleColumn(campo, 200, 20, 580, 30, 10, Element.ALIGN_RIGHT);
//            ct.go();
//
//
//            // CUADRITO 
//            canvas.saveState();
//            canvas.roundRectangle(60, 590, 470, 120, 7);
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Línea
//            canvas.saveState();
//            canvas.rectangle(170, 670, 100, 0);
//            canvas.stroke();
//            canvas.restoreState();
//
//
//            /* ################################
//             * #  Firma y Fecha de Solicitud  # 
//             * ################################*/
//            // Firma Solicitante
//            campo = new Phrase("Firma del Solicitante:  ", fontCampo);
//            ct.setSimpleColumn(campo, 70, 670, 200, 690, 15, Element.ALIGN_CENTER);
//            ct.go();
//
//            dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//            cal = Calendar.getInstance();
//            creacion = dateFormat.format(cal.getTime());
//
//            //FECHA SOLICITUD
//            campo = new Phrase("Fecha de Solicitud:  " + creacion, fontCampo);
//            ct.setSimpleColumn(campo, 320, 670, 500, 690, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Declaracion de veracidad
//            campo = new Phrase("El estudiante firmante declara que los datos y documentos suministrados son verídicos"
//                    + " y asume cumplir cabalmente con las normas del programa de intercambio estudiantil.", fontPequena);
//            ct.setSimpleColumn(campo, 120, 620, 500, 640, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            /* ##########################
//             * #  Campos Coordinacion   #
//             * ##########################*/
//
//            //Cuadro del area
//            canvas.saveState();
//            canvas.roundRectangle(60, 410, 470, 130, 7);
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Titulo del area.
//            titulo = new Phrase("**Esta sección debe ser llenada exclusivamente por la Coordinación Docente**", fontTitulos);
//            ct.setSimpleColumn(titulo, 60, 550, 530, 560, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            //Opinion 
//            campo = new Phrase("Opinión de la Coordinación Docente sobre esta solicitud (explicación breve):  ", fontCampo2);
//            ct.setSimpleColumn(campo, 90, 520, 500, 530, 10, Element.ALIGN_CENTER);
//            ct.go();
//            campo = new Phrase("RELLENO", fontCampo);
//            ct.setSimpleColumn(campo, 90, 500, 500, 510, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            /* ##########################
//             * #      Campos DRIC       #
//             * ##########################*/
//
//            //Cuadro del area
//            canvas.saveState();
//            canvas.roundRectangle(60, 230, 470, 130, 7);
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Titulo del area.
//            titulo = new Phrase("**Esta sección debe ser llenada exclusivamente por la DRIC**", fontTitulos);
//            ct.setSimpleColumn(titulo, 60, 370, 530, 380, 10, Element.ALIGN_CENTER);
//            ct.go();
//
//            //opinion
//            campo = new Phrase("Opinión de la DRIC sobre esta solicitud (explicación breve):  ", fontCampo2);
//            ct.setSimpleColumn(campo, 90, 340, 500, 350, 10, Element.ALIGN_CENTER);
//            ct.go();


            document.close();

        } catch (Exception ex) {
            Logger.getLogger(generacionPDF.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws BadElementException, DocumentException {

        String path = "/home/jidc28/NetBeansProjects/SEP/build/web/Documentos/22-90457";
        String filepath = "/home/jidc28/NetBeansProjects/SEP/web/imagenes/";
        String usbid = "22-90457";
        System.out.println(generarRendimiento(path, usbid, filepath));
    }
}
