import java.lang.Math;

public class SimInsert{
    public static void main(String args[]){
	String nombres[] = {"Andres","Pedro","Carlos","Renier","Michelle","Gabriel","Jose","Alejandra","Horacio","Martin","Isis","Jaime","Igor","Alejandro","Daniela","Daniel","Bruno","Christian","Tamara","Dario","Miguel","Alberto","Luciano","Ricardo","Adriana","Lucia","Luz","Maria","Carmen","Petra","Edgar","Abraham","Humerto","Mercedes","Valentina","Ana","Angela","Melania","Jackeline","Luis","Carlo","Rebecca","Ismael","Ernesto","Alan","Maritza","Pablo"};
	String materias[] = {"PS6116","PS1115","CI5311"};
	String apellidos[] = {"Salas","Riera","Delgado","Cabeza","Sergehiev","Cuervo","Colas","Pires","Machado","Marcano","Sotillo","Lairet","Farias","Cala","Gomez","Zambrano","Trias","Materano","Dorante","Trujillo","Alvarado","Villamizar","Torino","Potro","Vallenilla","Calzada","Martinez","Zubillaga","Rosciano","Ramos","Gil","Aristeguieta","Bolaffi","Luna","Pereda","Campos","Ramirez","Marquez","Rowling","Celli","Echezuria","Obando","Silva","Chiabrera","Marco","Bello","Tovar","Negrette","Gonzalez"};
	String dptos[] = {"Departamento de Administracion de Personal"
,"Departamento de Administracion y Almacen"
,"Departamento de Admision y Control de Estudios"
,"Departamento de Admision y Control de Estudios (Litoral)"
,"Departamento de Adquisicion y Reproduccion"
,"Departamento de Adquisicion y Reproduccion (Litoral)"
,"Departamento de Archivo y Estadistica (Litoral)"
,"Departamento de Atencion al Usuario"
,"Departamento de Bienes Nacionales"
,"Departamento de Bioingenieria"
,"Departamento de Biologia Celular"
,"Departamento de Biologia Celular y Organismos"
,"Departamento de Biologia de Organismos"
,"Departamento de Ciencias de la Tierra"
,"Departamento de Ciencias de los Materiales"
,"Departamento de Ciencias Economicas y Administrativas"
,"Departamento de Ciencias Politicas"
,"Departamento de Ciencias Sociales"
,"Departamento de Ciencia y Tecnologia del Comportamiento"
,"Departamento de Compras y Suministros"
,"Departamento de Computacion (Litoral)"
,"Departamento de Computacion y Tecnologia de la Informacion"
,"Departamento de Computo Cientifico y Estadistica"
,"Departamento de Contabilidad"
,"Departamento de Conversion y Transporte de Energia"
,"Departamento de Correspondencia"
,"Departamento de Desarrollo de Personal"
,"Departamento de Diseño Arquitectura y Artes Plasticas"
,"Departamento de Ejecucion"
,"Departamento de Electronica y Circuitos"
,"Departamento de Estudios Ambientales"
,"Departamento de Filosofia"
,"Departamento de Finanzas"
,"Departamento de Finanzas (Litoral)"
,"Departamento de Fisica"
,"Departamento de Formacion General (Litoral)"
,"Departamento de Formacion General y Ciencias Basicas"
,"Departamento de Gestion del Capital Humano"
,"Departamento de Idiomas"
,"Departamento de Informacion y Documentacion"
,"Departamento de Informacion y Medios"
,"Departamento de Ingenieria Electrica"
,"Departamento de Ingenieria y Mantenimiento"
,"Departamento de Ingenieria y Mantenimiento (Litoral)"
,"Departamento de Lengua y Literatura"
,"Departamento de Matematicas Puras y Aplicadas"
,"Departamento de Mecanica"
,"Departamento de Musica"
,"Departamento de Planificacion Urbana"
,"Departamento de Planta Fisica"
,"Departamento de Procesos Biologicos y Bioquimicos"
,"Departamento de Procesos y Sistemas"
,"Departamento de Produccion de Impresos"
,"Departamento de Quimica y Procesos"
,"Departamento de Recursos Humanos"
,"Departamento de Recursos Humanos (Litoral)"
,"Departamento de Redes"
,"Departamento de Registro y Control Administrativo"
,"Departamento de Registro y Control Financiero"
,"Departamento de Relaciones Laborales"
,"Departamento de Seguridad y Servicios"
,"Departamento de Servicios Audiovisuales"
,"Departamento de Servicios de Red"
,"Departamento de Servicios Generales"
,"Departamento de Servicios Telefonicos"
,"Departamento de Servicos Generales"
,"Departamento de Soporte de Operaciones y Sistemas"
,"Departamento de Tecnologia de Procesos Biologicos y Bioquimicos"
,"Departamento de Tecnologia de Servicios"
,"Departamento de Tecnologia Industrial"
,"Departamento de Tecnologia Informatica"
,"Departamento de Termodinamica y Fenomenos de Transferencia"
,"Departamento de Tesoreria"
,"Departamento de Urbanismo"};

	String carreras[] = {"Ingeniería Eléctrica"
,"Ingeniería Mecánica"
,"Ingeniería Química"
,"Ingeniería Electrónica"
,"Ingeniería de Materiales"
,"Ingeniería de la Computación"
,"Ingeniería Geofísica"
,"Ingeniería de Producción"
,"Ingeniería de Mantenimiento"
,"Ingeniería de Telecomunicaciones"
,"Licenciatura en Química"
,"Licenciatura en Matemáticas"
,"Licenciatura en Física"
,"Licenciatura en Biología"
,"Arquitectura"
,"Urbanismo"
,"Licenciatura en Comercio Internacional"
,"Licenciatura en Gestión de la Hospitalidad"};

	int USBID[] = new int[7];
	String genero[] = {"M","F"};
	String usbid = "";
	String rango[] = {"ayudanteAcad","asistente","agregado","asociado","titular"};
	int lapso[] = {1,3,5};
	String tipo[] = {"coop","proy"};
	String trimestres[] = {"EM","AJ","SD"};
	String r3_S;
	String r9_S;
	int r1;
	int r2;
	int r3;
	int a = 0;
	
	while(true){
	    usbid = "";
	    r1 = (int) Math.round(Math.random()*(nombres.length-1));
	    r2 = (int) Math.round(Math.random()*(apellidos.length-1));
	    r3 = (int) Math.round(Math.random()*74);
	    if (r3 < 10) {
              r3_S = "0"+r3;
	    } else {
              r3_S = ""+r3;
	    }
	    int r4 = (int) Math.round(Math.random()*(1));
	    int r5 = (int) Math.round(Math.random()*(4));
	    int r6 = (int) Math.round(Math.random()*(1));
	    int r7 = (int) Math.round(Math.random()*(carreras.length-1));
	    int r8 = (int) Math.round(Math.random()*2);
	    int r9 = (int) Math.round(Math.random()*52);
            if (r9 < 10) {
              r9_S = "0"+r9;
            } else {
              r9_S = ""+r9;
            }
	    int cedula = (int) Math.round(Math.random()*(20000000));
	    
	    int ano = (int) Math.round(Math.random()*(2040));
	    int mes = (int) Math.round(Math.random()*(11)+1);
	    int dia = (int) Math.round(Math.random()*(28)+1);
	    int sem = (int) Math.round(Math.random()*(52));
	    
	    
	    for (int i=0; i<USBID.length;i++){
		USBID[i] = (int) Math.round(Math.random()*9);
	    }
	    usbid = usbid+USBID[0]+USBID[1]+"-"+USBID[2]+USBID[3]+USBID[4]+USBID[5]+USBID[6];
	    System.out.println("INSERT INTO USUARIO VALUES ('"+usbid+"','profesor','admin','D-20"+r3_S+"');");
	    System.out.println("INSERT INTO PROFESOR VALUES ('"+usbid+"','"+nombres[r1]+"','"+apellidos[r2]+"','"+cedula+"','"+genero[r4]+"','"+nombres[r1]+apellidos[r2]+"@usb.ve','"+nombres[r1]+apellidos[r2]+"@gmail.com','"+rango[r5]+"','no','11-11-2011','11-11-2013');");
	    System.out.println("INSERT INTO pertenece VALUES('"+usbid+"','D-20"+r3_S+"');");
	    System.out.println("INSERT INTO maneja VALUES('C-00"+r9_S+"','"+materias[r8]+"');");
	    System.out.println("INSERT INTO dicta VALUES('"+usbid+"','"+materias[r8]+"');");
	    System.out.println("INSERT INTO CCT VALUES ('"+usbid+"','"+a+"','Prueba "+a+"','"+ano+"-"+mes+"-"+dia+"','"+(ano+1)+"-"+mes+"-"+dia+"','"+carreras[r7]+"','"+tipo[r6]+"');");
	    
	    if(tipo[r6]=="coop"){
		r1 = (int) Math.round(Math.random()*(nombres.length-1));
		r2 = (int) Math.round(Math.random()*(apellidos.length-1));
		System.out.println("INSERT INTO COOP VALUES ('"+a+"','"+usbid+"','"+nombres[r1]+"','"+apellidos[r2]+"','"+sem+"');");
	    } else {
		r1 = (int) Math.round(Math.random()*(nombres.length-1));
		r2 = (int) Math.round(Math.random()*(apellidos.length-1));
		System.out.println("INSERT INTO PROY VALUES ('"+a+"','"+usbid+"','"+nombres[r1]+"','"+apellidos[r2]+"');");
		r1 = (int) Math.round(Math.random()*(nombres.length-1));
		r2 = (int) Math.round(Math.random()*(apellidos.length-1));
		System.out.println("INSERT INTO PROY VALUES ('"+a+"','"+usbid+"','"+nombres[r1]+"','"+apellidos[r2]+"');");
	    }
	    
	    System.out.println("INSERT INTO SINAI VALUES ('"+usbid+"','SINAI "+a+"','"+ano+"-"+mes+"-"+dia+"','"+(ano+2)+"-"+mes+"-"+dia+"');");
	    
	    for (int b=0; b<5; b++){
		int azartrim = (int) Math.round(Math.random()*(2));
		int sumar = (int) Math.round(Math.random()*(20));
		int azarano = 1990 + sumar;
		int rnota1 = (int) Math.round(Math.random()*(100));
		int rnota2 = (int) Math.round(Math.random()*(100));
		int rnota3 = (int) Math.round(Math.random()*(100));
		int rnota4 = (int) Math.round(Math.random()*(100));
		int rnota5 = (int) Math.round(Math.random()*(100));
		int rnotar = (int) Math.round(Math.random()*(100));
		System.out.println("INSERT INTO DACE VALUES ('"+usbid+"','PS-"+b+"','Sistemas "+b+"','"+trimestres[azartrim]+"','"+azarano+"','"+rnota1+"','"+rnota2+"','"+rnota3+"','"+rnota4+"','"+rnota5+"','"+rnotar+"');");
	    }
	    a++;
	    
	}
    }
}
