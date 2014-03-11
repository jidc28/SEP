DROP TABLE COORDINACION CASCADE;
DROP TABLE DECANATO CASCADE;
DROP TABLE PROFESOR CASCADE;
DROP TABLE USUARIO CASCADE;
DROP TABLE DACE CASCADE;
DROP TABLE CCT CASCADE;
DROP TABLE COOP CASCADE;
DROP TABLE PROY CASCADE;
DROP TABLE SINAI CASCADE;
DROP TABLE DEPARTAMENTO CASCADE;
DROP TABLE MATERIA CASCADE;
DROP TABLE TITULOS_PROF CASCADE;
DROP TABLE CONTRATACION_PROF CASCADE;
DROP TABLE RENDIMIENTO CASCADE;
DROP TABLE dicta CASCADE;
DROP TABLE oferta CASCADE;
DROP TABLE pertenece CASCADE;
DROP TABLE suscribe CASCADE;
DROP TABLE se_adscribe CASCADE;
DROP TABLE maneja CASCADE;
DROP TABLE evaluar CASCADE;
DROP TABLE solicita_apertura CASCADE;

CREATE TABLE COORDINACION (
    CODIGO VARCHAR(10) NOT NULL,
    NOMBRE VARCHAR(100) NOT NULL UNIQUE,
    
    CONSTRAINT PK_COORDINACION PRIMARY KEY (CODIGO)
);

CREATE TABLE DECANATO (
    CODIGO VARCHAR(10) NOT NULL,
    NOMBRE VARCHAR(100) NOT NULL UNIQUE,
    
    CONSTRAINT PK_DECANATO PRIMARY KEY (CODIGO)
);

CREATE TABLE PROFESOR (
    USBID VARCHAR(15) NOT NULL,
    NOMBRE VARCHAR(30) NOT NULL,
    APELLIDO VARCHAR(30) NOT NULL,
    CEDULA VARCHAR(9) NOT NULL,
    GENERO VARCHAR(9) NOT NULL,
    EMAIL VARCHAR(50) ,
    EMAIL_PERSONAL VARCHAR(50),
    NIVEL VARCHAR(20) ,
    JUBILADO VARCHAR(1) DEFAULT 'N',
    LAPSO_CONTRACTUAL_INICIO VARCHAR(10) ,
    LAPSO_CONTRACTUAL_FIN VARCHAR(10) ,
    
    CONSTRAINT PK_PROFESOR PRIMARY KEY (USBID)
);

CREATE TABLE USUARIO(
    USBID VARCHAR(15) NOT NULL,
    TIPOUSUARIO VARCHAR(20) DEFAULT 'profesor' NOT NULL,
    CONTRASENA VARCHAR(30) NOT NULL,

    CONSTRAINT PK_USUARIO PRIMARY KEY (USBID)
);

CREATE TABLE DACE (
    USBID VARCHAR(15) NOT NULL,
    CODIGO VARCHAR(6) NOT NULL,
    NOMBRE VARCHAR(25) NOT NULL,
    TRIMESTRE VARCHAR(10) NOT NULL,
    ANO NUMERIC(4) NOT NULL,
    UNO NUMERIC(3) DEFAULT 0 NOT NULL,
    DOS NUMERIC(3) DEFAULT 0 NOT NULL,
    TRES NUMERIC(3) DEFAULT 0 NOT NULL,
    CUATRO NUMERIC(3) DEFAULT 0 NOT NULL,
    CINCO NUMERIC(3) DEFAULT 0 NOT NULL,
    RETIRADOS NUMERIC(3) DEFAULT 0 NOT NULL,
    
    CONSTRAINT PK_DACE PRIMARY KEY (USBID,CODIGO,TRIMESTRE,ANO)
);

CREATE TABLE CCT(
    USBID VARCHAR(15) NOT NULL,
    IDENT VARCHAR(10) NOT NULL,
    TITULO VARCHAR(50) NOT NULL,
    FECHA_INIC DATE NOT NULL,
    FECHA_FIN DATE NOT NULL,
    CARRERA VARCHAR(50) NOT NULL,
    TIPO VARCHAR(15) NOT NULL,
    
    CONSTRAINT PK_CCT PRIMARY KEY (IDENT)
);

CREATE TABLE COOP(
    IDENT VARCHAR(10) NOT NULL,
    USBID VARCHAR(15) NOT NULL,
    NOMBRE VARCHAR(20) NOT NULL,
    APELLIDO VARCHAR(20) NOT NULL,
    DURACION NUMERIC(3) NOT NULL,
    
    CONSTRAINT PK_COOP PRIMARY KEY (IDENT)
);

CREATE TABLE PROY (
    IDENT VARCHAR(10) NOT NULL,
    USBID VARCHAR(15) NOT NULL,
    NOMBRE VARCHAR(20) NOT NULL,
    APELLIDO VARCHAR(20) NOT NULL,
    
    CONSTRAINT PK_PROY PRIMARY KEY (IDENT,NOMBRE,APELLIDO)
);

CREATE TABLE SINAI (
    USBID VARCHAR(15) NOT NULL,
    NOMBRE VARCHAR(40) NOT NULL,
    FECHA_INIC DATE NOT NULL,
    FECHA_FIN DATE NOT NULL,
    
    CONSTRAINT PK_SINAI PRIMARY KEY (USBID,NOMBRE)
);

CREATE TABLE DEPARTAMENTO (
    CODIGO VARCHAR(10) NOT NULL,
    NOMBRE VARCHAR(100) NOT NULL UNIQUE,
    CONDICION VARCHAR(11) DEFAULT 'activo' NOT NULL,

    CONSTRAINT PK_DEPARTAMENTO PRIMARY KEY (CODIGO)
);

CREATE TABLE MATERIA (
    CODIGO VARCHAR(10) NOT NULL,
    NOMBRE VARCHAR(100) NOT NULL UNIQUE,
    CREDITOS VARCHAR(2) NOT NULL,
    CONDICION VARCHAR(11) DEFAULT 'activo' NOT NULL,
    ESTADO VARCHAR(7) DEFAULT 'visible' NOT NULL,
    SOLICITUD VARCHAR(2) DEFAULT 'no' NOT NULL,
    
    CONSTRAINT PK_MATERIA PRIMARY KEY (CODIGO)
);

CREATE TABLE TITULOS_PROF (
    USBID_PROFESOR VARCHAR(15) REFERENCES PROFESOR (USBID) ON DELETE CASCADE ON UPDATE CASCADE,
    TITULO VARCHAR(30) NOT NULL,
    INSTITUCION VARCHAR(30) NOT NULL,

    CONSTRAINT PK_TITULOS_PROF PRIMARY KEY (USBID_PROFESOR, TITULO)
);

CREATE TABLE CONTRATACION_PROF (
    USBID_PROFESOR VARCHAR(15) REFERENCES PROFESOR (USBID) ON DELETE CASCADE ON UPDATE CASCADE,
    FECHA_INIC DATE NOT NULL,
    FECHA_FIN DATE NOT NULL,
    NIVEL VARCHAR(15) NOT NULL,
    DEDICACION VARCHAR(15) NOT NULL,

    CONSTRAINT PK_CONTRATACION_PROF PRIMARY KEY (USBID_PROFESOR,FECHA_INIC,FECHA_FIN,NIVEL,DEDICACION)
);

CREATE TABLE RENDIMIENTO (
    USBID_PROFESOR VARCHAR(15) REFERENCES PROFESOR (USBID) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_MATERIA VARCHAR(10) REFERENCES MATERIA (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    TRIMESTRE VARCHAR(2) NOT NULL,
    ANO NUMERIC(4,0) NOT NULL,
    TOTAL_ESTUDIANTES NUMERIC(3,0) NOT NULL,
    NOTA_PROM NUMERIC(5,2) NOT NULL,
    NOTA1 NUMERIC(3,0) NOT NULL,
    NOTA2 NUMERIC(3,0) NOT NULL,
    NOTA3 NUMERIC(3,0) NOT NULL,
    NOTA4 NUMERIC(3,0) NOT NULL,
    NOTA5 NUMERIC(3,0) NOT NULL,
    RETIRADOS NUMERIC(3,0) NOT NULL,

    CONSTRAINT PK_RENDIMIENTO PRIMARY KEY (USBID_PROFESOR,CODIGO_MATERIA,TRIMESTRE,ANO)    
);

CREATE TABLE dicta (
    USBID_PROFESOR VARCHAR(15) REFERENCES PROFESOR (USBID) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_MATERIA VARCHAR(10) REFERENCES MATERIA (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    PERIODO        VARCHAR(2),
    PLANILLA_LLENA VARCHAR(1) DEFAULT 'N' NOT NULL,

    CONSTRAINT PK_dicta PRIMARY KEY (USBID_PROFESOR,CODIGO_MATERIA,PERIODO)
);

CREATE TABLE se_adscribe (
    CODIGO_COORDINACION VARCHAR(10) REFERENCES COORDINACION (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_DECANATO VARCHAR(10) REFERENCES DECANATO (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT PK_seadscribe PRIMARY KEY (CODIGO_COORDINACION,CODIGO_DECANATO)
);

CREATE TABLE oferta (
    CODIGO_MATERIA VARCHAR(10) REFERENCES MATERIA (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_DEPARTAMENTO VARCHAR(10) NOT NULL REFERENCES DEPARTAMENTO (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT PK_oferta PRIMARY KEY (CODIGO_MATERIA,CODIGO_DEPARTAMENTO)
);

CREATE TABLE solicita_apertura (
    CODIGO_MATERIA VARCHAR(10) REFERENCES MATERIA (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_COORDINACION VARCHAR(10) REFERENCES COORDINACION (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_DEPARTAMENTO VARCHAR(10) REFERENCES DEPARTAMENTO (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    MENSAJE VARCHAR(200) NOT NULL,

    CONSTRAINT PK_colicita_apertura PRIMARY KEY (CODIGO_MATERIA,CODIGO_COORDINACION)
); 

CREATE TABLE pertenece ( 
    USBID_PROFESOR VARCHAR(10) REFERENCES PROFESOR (USBID) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_DEPARTAMENTO VARCHAR (10) REFERENCES DEPARTAMENTO (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    EVALUADO VARCHAR(1) DEFAULT 'N',

    CONSTRAINT PK_pertenece PRIMARY KEY (USBID_PROFESOR,CODIGO_DEPARTAMENTO)
);

CREATE TABLE suscribe (
    CODIGO_DEPARTAMENTO VARCHAR(10) REFERENCES DEPARTAMENTO (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_DECANATO VARCHAR(10) NOT NULL REFERENCES DECANATO (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT PK_suscribe PRIMARY KEY (CODIGO_DEPARTAMENTO)
);

CREATE TABLE maneja (
    CODIGO_COORDINACION VARCHAR(10) REFERENCES COORDINACION (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_MATERIA VARCHAR(10) REFERENCES MATERIA (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT PK_maneja PRIMARY KEY (CODIGO_COORDINACION,CODIGO_MATERIA)
);

CREATE TABLE evaluar (
    CODIGO_COORDINACION VARCHAR(10) REFERENCES COORDINACION (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    USBID_PROFESOR VARCHAR(10) REFERENCES PROFESOR (USBID) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_MATERIA VARCHAR(10) REFERENCES MATERIA (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,
    CODIGO_DEPARTAMENTO VARCHAR(10) REFERENCES DEPARTAMENTO (CODIGO) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT PK_evaluar PRIMARY KEY (CODIGO_COORDINACION,USBID_PROFESOR,CODIGO_MATERIA,CODIGO_DEPARTAMENTO)
);

ALTER TABLE PROFESOR ADD CONSTRAINT FK_profesor_usuario
    FOREIGN KEY (USBID) REFERENCES USUARIO (USBID) ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE DACE ADD CONSTRAINT FK_dace_profesor
    FOREIGN KEY (USBID) REFERENCES PROFESOR (USBID);
    
ALTER TABLE CCT ADD CONSTRAINT FK_cct_profesor
    FOREIGN KEY (USBID) REFERENCES PROFESOR (USBID);
    
ALTER TABLE COOP ADD CONSTRAINT FK_coop_cct
    FOREIGN KEY (IDENT) REFERENCES CCT (IDENT);
    
ALTER TABLE PROY ADD CONSTRAINT FK_proy_cct
    FOREIGN KEY (IDENT) REFERENCES CCT (IDENT);
    
ALTER TABLE SINAI ADD CONSTRAINT FK_sinai_profesor
    FOREIGN KEY (USBID) REFERENCES PROFESOR (USBID);