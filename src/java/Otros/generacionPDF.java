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
import java.util.ArrayList;

/**
 *
 * @author jidc28 Esta clase fue desarrolada gracias a la ayuda de Andrea Balbás
 * y Gustavo Ortega, ambos estudiantes cohorte 09 de la Universidad Simón
 * Bolívar, quienes estuvieron involucrados en la implementación del Sistema de
 * Gestión de Intercambio de la USB.
 */
public class generacionPDF {

    public static Float calcularPorcentaje(int total, int parte) {
        Float resultado = (float) (parte * 100) / total;
        return resultado;
    }

    public static Boolean generarRendimiento(String path, String usbid, String filepath,
            String idOficina, int ano, String trimestre, String tipoUser)
            throws BadElementException, DocumentException {

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
                    + "Resumen del rendimiento del profesor \n"
                    + "Año: " + ano + " Trimestre: " + obtenerTrimestre(trimestre), fontTitulos2);
            ct.setSimpleColumn(campo, 200, 625, 480, 730, 10, Element.ALIGN_RIGHT);
            ct.go();

            // agrega la imagen al pdf
            document.add(imagen);
            campo = new Phrase("                UNIVERSIDAD SIMÓN BOLÍVAR\n"
                    + "      DECANATO DE ESTUDIOS PROFESIONALES\n"
                    + "            Sistema de Evaluación de Profesores", fontTitulos2);
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
            if (info.getConsejoAsesor().equals("si")) {
                campo = new Phrase("Si", fontCampo);
            } else {
                campo = new Phrase("No", fontCampo);
            }
            ct.setSimpleColumn(campo, 360, 470, 450, 480, 10, Element.ALIGN_LEFT);
            ct.go();

            // Proyectos de grado, trabajos de grado o tesis doctorales tutoreados.
            campo = new Phrase("  Número de proyectos de grado, trabajos de grado ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 450, 360, 460, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase("  o tesis doctorales tutoreados  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 440, 360, 450, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getTesisTutoria() + "", fontCampo);
            ct.setSimpleColumn(campo, 360, 440, 450, 450, 10, Element.ALIGN_LEFT);
            ct.go();

            // Proyectos de grado, trabajos de grado o tesis doctorales como jurado.
            campo = new Phrase("  Número de proyectos de grado, trabajos de grado ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 420, 360, 430, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase("  o tesis doctorales evaluadas como jurado  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 410, 360, 420, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getTesisJurado() + "", fontCampo);
            ct.setSimpleColumn(campo, 360, 410, 450, 420, 10, Element.ALIGN_LEFT);
            ct.go();

            // Pasantias cortas tutoreadas.
            campo = new Phrase("  Número de pasantías cortas tutoreadas ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 390, 360, 400, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getPasantiaCorta() + "", fontCampo);
            ct.setSimpleColumn(campo, 360, 390, 450, 400, 10, Element.ALIGN_LEFT);
            ct.go();

            // Pasantias largas tutoreadas.
            campo = new Phrase("  Número de pasantías largas e intermedias tutoreadas ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 370, 360, 380, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getPasantiaLargaTutor() + "", fontCampo);
            ct.setSimpleColumn(campo, 360, 370, 450, 380, 10, Element.ALIGN_LEFT);
            ct.go();

            // Pasantias largas e intermedia jurado.
            campo = new Phrase("  Número de pasantías largas e intermedias ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 350, 360, 360, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase("  evaluadas como jurado  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 340, 360, 350, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getPasantiaLargaJurado() + "", fontCampo);
            ct.setSimpleColumn(campo, 360, 340, 450, 350, 10, Element.ALIGN_LEFT);
            ct.go();

            // Cuadro Contenedor
            canvas.saveState();
            //canvas.roundRectangle(x, y, w, h, r);
            canvas.roundRectangle(70, 330, 455, 175, 6);
            canvas.stroke();
            canvas.restoreState();

//            /* ###########################
//             * #  Cargas Académicas      #
//             * ###########################*/
//
//            // Background area
//            canvas.saveState();
//            canvas.roundRectangle(70, 300, 455, 20, 6);
//            canvas.setColorFill(color);
//            canvas.fill();
//            canvas.stroke();
//            canvas.restoreState();
//
//            // Titulo del area.
//            titulo = new Phrase(" CARGA ACADÉMICA ", fontTitulos);
//            ct.setSimpleColumn(titulo, 70, 300, 560, 315, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            ArrayList<Materia> materias = DBMS.getInstance().listarMateriasDictadasTrimestrePDF(p.getUsbid(), ano, trimestre);
//
//            // Pasantias cortas tutoreadas.
//            int i;
//            for (i = 0; i < materias.size(); i++) {
//                System.out.println(materias.size() + "SIZE");
//                Materia materia = materias.get(i);
//
//                campo = new Phrase("  " + materia.getNombre(), fontCampo2);
//                ct.setSimpleColumn(campo, 70, 280 - (i * 20), 360, 290 - (i * 20), 10, Element.ALIGN_LEFT);
//                ct.go();
//                campo = new Phrase("  " + materia.getCodigo(), fontCampo);
//                ct.setSimpleColumn(campo, 360, 280 - (i * 20), 450, 290 - (i * 20), 10, Element.ALIGN_LEFT);
//                ct.go();
//            }
//
//            // Cuadro Contenedor
//            canvas.saveState();
//            //canvas.roundRectangle(x, y, w, h, r);
//            canvas.roundRectangle(70, 290 - (i * 20), 455, 30 + (i * 20), 6);
//            canvas.stroke();
//            canvas.restoreState();
//
            /* ###########################
             * # Informacion General     #
             * ###########################*/

            document.newPage();
//
//            //Estampado de numero de planilla, fecha y hora de creacion
//            campo = new Phrase("Fecha Creación:  " + creacion, fontCampo);
//            ct.setSimpleColumn(campo, 200, 20, 580, 30, 10, Element.ALIGN_RIGHT);
//            ct.go();
//
//            //Background area
//            canvas.saveState();
//            canvas.roundRectangle(70, 715, 455, 20, 6);
//            canvas.setColorFill(color);
//            canvas.fill();
//            canvas.stroke();
//            canvas.restoreState();
//
//            // Titulo del area.
//            titulo = new Phrase("  INFORMACIÓN GENERAL  ", fontTitulos);
//            ct.setSimpleColumn(titulo, 70, 720, 450, 745, 25, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Subtitulo Area INFORMACION GENERAL
//            titulo = new Phrase(" Información General  ", fontTitulos);
//            ct.setSimpleColumn(titulo, 80, 680, 445, 700, 10, Element.ALIGN_LEFT);
//            ct.go();
//            // Linea Titulo
//            canvas.saveState();
//            canvas.rectangle(80, 685, 103, 0);
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Total estudiantes.
//            campo = new Phrase("  Total estudiantes  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 660, 360, 670, 10, Element.ALIGN_LEFT);
//            ct.go();
//            campo = new Phrase(total + "", fontCampo);
//            ct.setSimpleColumn(campo, 360, 660, 450, 670, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Nota Promedio.
//            campo = new Phrase("  Nota promedio  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 640, 360, 650, 10, Element.ALIGN_LEFT);
//            ct.go();
//            campo = new Phrase(evaluacion.getNota_prom() + "", fontCampo);
//            ct.setSimpleColumn(campo, 360, 640, 450, 650, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Porcentaje Aplazados.
//            campo = new Phrase("  Porcentaje aplazados  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 620, 360, 630, 10, Element.ALIGN_LEFT);
//            ct.go();
//            campo = new Phrase(porcentajeApl + "%", fontCampo);
//            ct.setSimpleColumn(campo, 360, 620, 450, 630, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Porcentaje Aprobados.
//            campo = new Phrase("  Porcentaje aprobados  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 600, 360, 610, 10, Element.ALIGN_LEFT);
//            ct.go();
//            campo = new Phrase(porcentajeApr + "%", fontCampo);
//            ct.setSimpleColumn(campo, 360, 600, 450, 610, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Porcentaje Retirados.
//            campo = new Phrase("  Porcentaje retirados  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 580, 360, 590, 10, Element.ALIGN_LEFT);
//            ct.go();
//            campo = new Phrase(porcentajeR + "%", fontCampo);
//            ct.setSimpleColumn(campo, 360, 580, 450, 590, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Subtitulo Area INFORMACION ESPECIFICA
//            titulo = new Phrase(" Información Específica  ", fontTitulos);
//            ct.setSimpleColumn(titulo, 80, 540, 445, 560, 10, Element.ALIGN_LEFT);
//            ct.go();
//            // Linea Titulo
//            canvas.saveState();
//            canvas.rectangle(80, 545, 115, 0);
//            canvas.stroke();
//            canvas.restoreState();
//
//            //Porcentaje con 1.
//            campo = new Phrase("  Porcentaje de estudiantes con 1  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 520, 360, 530, 10, Element.ALIGN_LEFT);
//            ct.go();
//            campo = new Phrase(porcentaje1 + "%", fontCampo);
//            ct.setSimpleColumn(campo, 360, 520, 450, 530, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Porcentaje con 2.
//            campo = new Phrase("  Porcentaje de estudiantes con 2  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 500, 360, 510, 10, Element.ALIGN_LEFT);
//            ct.go();
//            campo = new Phrase(porcentaje2 + "%", fontCampo);
//            ct.setSimpleColumn(campo, 360, 500, 450, 510, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Porcentaje con 3.
//            campo = new Phrase("  Porcentaje de estudiantes con 3  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 480, 360, 490, 10, Element.ALIGN_LEFT);
//            ct.go();
//            campo = new Phrase(porcentaje3 + "%", fontCampo);
//            ct.setSimpleColumn(campo, 360, 480, 450, 490, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Porcentaje con 4.
//            campo = new Phrase("  Porcentaje de estudiantes con 4  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 460, 360, 470, 10, Element.ALIGN_LEFT);
//            ct.go();
//            campo = new Phrase(porcentaje4 + "%", fontCampo);
//            ct.setSimpleColumn(campo, 360, 460, 450, 470, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            //Porcentaje con 5.
//            campo = new Phrase("  Porcentaje de estudiantes con 5  ", fontCampo2);
//            ct.setSimpleColumn(campo, 70, 440, 360, 450, 10, Element.ALIGN_LEFT);
//            ct.go();
//            campo = new Phrase(porcentaje5 + "%", fontCampo);
//            ct.setSimpleColumn(campo, 360, 440, 450, 450, 10, Element.ALIGN_LEFT);
//            ct.go();
//
//            // Cuadro Contenedor
//            canvas.saveState();
//            //canvas.roundRectangle(x, y, w, h, r);
//            canvas.roundRectangle(70, 430, 455, 305, 6);
//            canvas.stroke();
//            canvas.restoreState();
//



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
//            ArrayList<rendimientoProf> evaluaciones =
//                    DBMS.getInstance().obtenerEvaluacionesEnviadasCoordinaciones(idOficina, p.getUsbid(), ano, trimestre);


//            //Background area
//            canvas.saveState();
//            canvas.roundRectangle(70, 400, 455, 20, 6);
//            canvas.setColorFill(color);
//            canvas.fill();
//            canvas.stroke();
//            canvas.restoreState();
//
//            // Titulo del area.
//            titulo = new Phrase("  COORDINACIONES  ", fontTitulos);
//            ct.setSimpleColumn(titulo, 70, 405, 450, 430, 25, Element.ALIGN_LEFT);
//            ct.go();

            //DECLARACIONES DINAMICAS
            int varY = 375;
            int inicio = 375;

//            for (int iterador = 0; iterador < evaluaciones.size(); iterador++) {
//                rendimientoProf eval = evaluaciones.get(iterador);
//                if (varY <= 130) {
//
//                    // Cuadro Contenedor
//                    canvas.saveState();
//                    //canvas.roundRectangle(x, y, w, h, r);
//                    canvas.roundRectangle(70, varY, 455, inicio - varY + 45, 6);
//                    canvas.stroke();
//                    canvas.restoreState();
//
//                    document.newPage();
//                    varY = 680;
//                    inicio = 690;
//                    //Estampado de numero de planilla, fecha y hora de creacion
//                    campo = new Phrase("Fecha Creación:  " + creacion, fontCampo);
//                    ct.setSimpleColumn(campo, 200, 20, 580, 30, 10, Element.ALIGN_RIGHT);
//                    ct.go();
//
//                    //Background area
//                    canvas.saveState();
//                    canvas.roundRectangle(70, 715, 455, 20, 6);
//                    canvas.setColorFill(color);
//                    canvas.fill();
//                    canvas.stroke();
//                    canvas.restoreState();
//
//                    // Titulo del area.
//                    titulo = new Phrase("  INFORMACIÓN GENERAL  ", fontTitulos);
//                    ct.setSimpleColumn(titulo, 70, 720, 450, 745, 25, Element.ALIGN_LEFT);
//                    ct.go();
//                }
//
//                titulo = new Phrase("  " + eval.getObservaciones_d(), fontTitulos);
//                ct.setSimpleColumn(titulo, 70, varY, 445, varY + 20, 10, Element.ALIGN_LEFT);
//                ct.go();
//
//                varY = varY - 10; //365
//                campo = new Phrase("      Recomendado: ", fontCampo2);
//                ct.setSimpleColumn(campo, 70, varY, 450, varY + 10, 10, Element.ALIGN_LEFT);
//                if (eval.getRecomendado().equals("si")) {
//                    campo = new Phrase("  Si", fontCampo);
//                } else {
//                    campo = new Phrase("  No", fontCampo);
//                }
//                ct.setSimpleColumn(campo, 70, varY, 450, varY + 10, 10, Element.ALIGN_LEFT);
//                ct.go();
//
//                varY = varY - 20; //345
//                campo = new Phrase("      Justificación :", fontCampo2);
//                ct.setSimpleColumn(campo, 70, varY, 450, varY + 10, 10, Element.ALIGN_LEFT);
//                ct.go();
//                int dez = 0;
//                if (eval.getObservaciones_c().length() <= 100) {
//                    dez = 20;
//                } else if (eval.getObservaciones_c().length() <= 200) {
//                    dez = 30;
//                } else if (eval.getObservaciones_c().length() <= 300) {
//                    dez = 40;
//                } else if (eval.getObservaciones_c().length() <= 400) {
//                    dez = 50;
//                } else if (eval.getObservaciones_c().length() > 400) {
//                    dez = 60;
//                }
//
//                campo = new Phrase(eval.getObservaciones_c(), fontCampo);
//                ct.setSimpleColumn(campo, 80, varY - dez, 450, varY, 10, Element.ALIGN_LEFT);
//                ct.go();
//
//                varY = varY - dez - 20;
//            }

//            // Cuadro Contenedor
//            canvas.saveState();
//            //canvas.roundRectangle(x, y, w, h, r);
//            canvas.roundRectangle(70, varY, 455, inicio - varY + 45, 6);
//            canvas.stroke();
//            canvas.restoreState();

            document.close();

        } catch (Exception ex) {
            Logger.getLogger(generacionPDF.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public static Boolean generarRendimientoFinal(String path, String usbid, String filepath,
            String idOficina, int ano, String trimestre, String tipoUser)
            throws BadElementException, DocumentException {

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
                    + "Resumen del rendimiento del profesor \n"
                    + "Año: " + ano + " Trimestre: " + obtenerTrimestre(trimestre), fontTitulos2);
            ct.setSimpleColumn(campo, 200, 625, 480, 730, 10, Element.ALIGN_RIGHT);
            ct.go();

            // agrega la imagen al pdf
            document.add(imagen);
            campo = new Phrase("                UNIVERSIDAD SIMÓN BOLÍVAR\n"
                    + "      DECANATO DE ESTUDIOS PROFESIONALES\n"
                    + "            Sistema de Evaluación de Profesores", fontTitulos2);
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
            if (info.getConsejoAsesor().equals("si")) {
                campo = new Phrase("Si", fontCampo);
            } else {
                campo = new Phrase("No", fontCampo);
            }
            ct.setSimpleColumn(campo, 360, 470, 450, 480, 10, Element.ALIGN_LEFT);
            ct.go();

            // Proyectos de grado, trabajos de grado o tesis doctorales tutoreados.
            campo = new Phrase("  Número de proyectos de grado, trabajos de grado ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 450, 360, 460, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase("  o tesis doctorales tutoreados  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 440, 360, 450, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getTesisTutoria() + "", fontCampo);
            ct.setSimpleColumn(campo, 360, 440, 450, 450, 10, Element.ALIGN_LEFT);
            ct.go();

            // Proyectos de grado, trabajos de grado o tesis doctorales como jurado.
            campo = new Phrase("  Número de proyectos de grado, trabajos de grado ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 420, 360, 430, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase("  o tesis doctorales evaluadas como jurado  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 410, 360, 420, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getTesisJurado() + "", fontCampo);
            ct.setSimpleColumn(campo, 360, 410, 450, 420, 10, Element.ALIGN_LEFT);
            ct.go();

            // Pasantias cortas tutoreadas.
            campo = new Phrase("  Número de pasantías cortas tutoreadas ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 390, 360, 400, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getPasantiaCorta() + "", fontCampo);
            ct.setSimpleColumn(campo, 360, 390, 450, 400, 10, Element.ALIGN_LEFT);
            ct.go();

            // Pasantias largas tutoreadas.
            campo = new Phrase("  Número de pasantías largas e intermedias tutoreadas ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 370, 360, 380, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getPasantiaLargaTutor() + "", fontCampo);
            ct.setSimpleColumn(campo, 360, 370, 450, 380, 10, Element.ALIGN_LEFT);
            ct.go();

            // Pasantias largas e intermedia jurado.
            campo = new Phrase("  Número de pasantías largas e intermedias ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 350, 360, 360, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase("  evaluadas como jurado  ", fontCampo2);
            ct.setSimpleColumn(campo, 70, 340, 360, 350, 10, Element.ALIGN_LEFT);
            ct.go();
            campo = new Phrase(info.getPasantiaLargaJurado() + "", fontCampo);
            ct.setSimpleColumn(campo, 360, 340, 450, 350, 10, Element.ALIGN_LEFT);
            ct.go();

            // Cuadro Contenedor
            canvas.saveState();
            //canvas.roundRectangle(x, y, w, h, r);
            canvas.roundRectangle(70, 330, 455, 175, 6);
            canvas.stroke();
            canvas.restoreState();


            //NUEVA PAGINA
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

            int varY = 680;
            int inicio = 690;

            if (tipoUser.equals("departamento")) {
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
                ArrayList<rendimientoProf> evaluaciones =
                        DBMS.getInstance().obtenerEvaluacionesEnviadasCoordinaciones(idOficina, p.getUsbid(), ano, trimestre);

                // Titulo del area.
                titulo = new Phrase(" EVALUACIÓN DE COORDINACIONES  ", fontTitulos);
                ct.setSimpleColumn(titulo, 70, 745, 450, 430, 25, Element.ALIGN_LEFT);
                ct.go();

                //DECLARACIONES DINAMICAS


                for (int iterador = 0; iterador < evaluaciones.size(); iterador++) {
                    rendimientoProf eval = evaluaciones.get(iterador);
                    if (varY <= 130) {

                        // Cuadro Contenedor
                        canvas.saveState();
                        //canvas.roundRectangle(x, y, w, h, r);
                        canvas.roundRectangle(70, varY, 455, inicio - varY + 45, 6);
                        canvas.stroke();
                        canvas.restoreState();

                        document.newPage();
                        varY = 680;
                        inicio = 690;
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
                        titulo = new Phrase("  EVALUACIÓN DE COORDINACIONES  ", fontTitulos);
                        ct.setSimpleColumn(titulo, 70, 720, 450, 745, 25, Element.ALIGN_LEFT);
                        ct.go();
                    }

                    titulo = new Phrase("  " + eval.getObservaciones_d(), fontTitulos);
                    ct.setSimpleColumn(titulo, 70, varY, 445, varY + 20, 10, Element.ALIGN_LEFT);
                    ct.go();

                    varY = varY - 10; //365
                    campo = new Phrase("      Recomendado: ", fontCampo2);
                    ct.setSimpleColumn(campo, 70, varY, 450, varY + 10, 10, Element.ALIGN_LEFT);
                    if (eval.getRecomendado().equals("si")) {
                        campo = new Phrase("  Si", fontCampo);
                    } else {
                        campo = new Phrase("  No", fontCampo);
                    }
                    ct.setSimpleColumn(campo, 70, varY, 450, varY + 10, 10, Element.ALIGN_LEFT);
                    ct.go();

                    varY = varY - 20; //345
                    campo = new Phrase("      Justificación :", fontCampo2);
                    ct.setSimpleColumn(campo, 70, varY, 450, varY + 10, 10, Element.ALIGN_LEFT);
                    ct.go();
                    int dez = 0;
                    if (eval.getObservaciones_c().length() <= 100) {
                        dez = 20;
                    } else if (eval.getObservaciones_c().length() <= 200) {
                        dez = 30;
                    } else if (eval.getObservaciones_c().length() <= 300) {
                        dez = 40;
                    } else if (eval.getObservaciones_c().length() <= 400) {
                        dez = 50;
                    } else if (eval.getObservaciones_c().length() > 400) {
                        dez = 60;
                    }

                    campo = new Phrase(eval.getObservaciones_c(), fontCampo);
                    ct.setSimpleColumn(campo, 80, varY - dez, 450, varY, 10, Element.ALIGN_LEFT);
                    ct.go();

                    varY = varY - dez - 20;
                }

                // Cuadro Contenedor
                canvas.saveState();
                //canvas.roundRectangle(x, y, w, h, r);
                canvas.roundRectangle(70, varY, 455, inicio - varY + 45, 6);
                canvas.stroke();
                canvas.restoreState();
            }

            /* ###########################
             * # Rendimiento por materia #
             * ###########################*/

            ArrayList<rendimientoProf> evaluaciones_enviadas =
                    DBMS.getInstance().listarEvaluacionesEnviadasDepartamento(idOficina, ano, trimestre);
            if (varY > 130) {
                varY -= 30;
                inicio -= 30;

                if (tipoUser.equals("departamento")) {
                    //Background area
                    canvas.saveState();
                    canvas.roundRectangle(70, varY, 455, 20, 6);
                    canvas.setColorFill(color);
                    canvas.fill();
                    canvas.stroke();
                    canvas.restoreState();

                    // Titulo del area. canvas.roundRectangle(70, 715, 455, 20, 6)
                    titulo = new Phrase("  RENDIMIENTO POR MATERIA  ", fontTitulos);
                    ct.setSimpleColumn(titulo, 70, varY + 5, 450, varY + 30, 25, Element.ALIGN_LEFT);
                    ct.go();
                } else {
                    titulo = new Phrase("  RENDIMIENTO POR MATERIA  ", fontTitulos);
                    ct.setSimpleColumn(titulo, 70, 715, 450, 745, 25, Element.ALIGN_LEFT);
                    ct.go();
                }
            } else {
                // Cuadro Contenedor
                canvas.saveState();
                //canvas.roundRectangle(x, y, w, h, r);
                canvas.roundRectangle(70, varY, 455, inicio - varY + 45, 6);
                canvas.stroke();
                canvas.restoreState();

                document.newPage();
                varY = 680;
                inicio = 690;
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
                titulo = new Phrase("  RENDIMIENTO POR MATERIAS  ", fontTitulos);
                ct.setSimpleColumn(titulo, 70, 720, 450, 745, 25, Element.ALIGN_LEFT);
                ct.go();
            }
            
            //HACER LOOP DE AQUI EN ADELANTE 


            document.close();

        } catch (Exception ex) {
            Logger.getLogger(generacionPDF.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

//    public static void main(String[] args) throws BadElementException, DocumentException {
//
//        String path = "/home/jidc28/NetBeansProjects/SEP/build/web/Documentos/22-90457";
//        String filepath = "/home/jidc28/NetBeansProjects/SEP/web/imagenes/";
//        String usbid = "22-90457";
//        System.out.println(generarRendimiento(path, usbid, filepath, "09-10219", 2005, "AJ"));
//    }
    public static String obtenerTrimestre(String siglas) {

        if (siglas.equals("EM")) {
            return "Enero-Marzo";
        } else if (siglas.equals("AJ")) {
            return "Abril-Julio";
        } else if (siglas.equals("SD")) {
            return "Septiembre-Diciembre";
        } else if (siglas.equals("V")) {
            return "Intensivo";
        }
        return null;
    }
}
