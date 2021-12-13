## La Nebulosa de Qwerty

Esta es una aplicación para aprender mecanografía de forma gradual, utilizando como material de aprendizaje y de motivación textos sobre la gran variedad de cuerpos celestes.

Este repositorio corresponde al módulo de backend de _Gestión de Puntajes_ de esta aplicación, desarrollada como proyecto del Ciclo 4 del programa de formación MisiónTIC 2022 Cohorte 2021, por el Equipo 8 del Grupo 11 de Desarrollo Web.

Se hace uso del framework Sprint Boot para el desarrollo de este módulo, también se emplea una API Gateway que permite habilitar la conexión entre los servicios y los clientes, realizando peticiones a distintos microservicios. Estos microservicios poseen una API de tipo REST, así que, la comunicación se realiza a través de peticiones HTTP.

## Software Necesario

Se requiere:
-Java
-Maven
-Spring Initializr
-Docker

## Instrucciones de instalación
Java:
1. Descargar e instalar  
2. Ingresar a la herramienta "Editar las variables de entorno del sistema".
3. Seleccionar la opción "Variables de Entorno" y se debe elegir la variable Path y Editar.
4. Validar si se encuentra la siguiente ruta: C:\Program Files\Common Files\Oracle\Java\javapath, si no está agregarla.
5. Regresar al menú anterior, "Variables de Entorno"
6. En el recuadro "Variables del Sistema" seleccionamos new, ingresamos como nombre JAVA_HOME y como valor C:\Program Files\Java\jdk-17.0.1
7. Pruebas con los siguientes comandos: java --version, javac --version, echo %JAVA_HOME%.

Maven: 
1. Descargar e instalar 
2. Descomprimir y ubicar el archivo en C:\Program Files\
3. Ingresar a la herramienta "Editar las variables de entorno del sistema".
4. Seleccionar la opción "Variables de Entorno" y se debe elegir la variable Path y Editar
5. Se selecciona la opción nuevo, y se copia la ruta del archivo C:\Program Files\apache-maven-3.8.1\bin
6. Pruebas con los siguientes comandos: mvn --version

Sprint Boot: 
1. Ingresar al https://start.spring.io/
2. Se diligencia toda la información necesaria al proyecto
3. Se selecciona la opción Jar, y la versión de java que tengas.
4. Descargar.

Docker
1. Descargar
2. Verificar si están activas las siguientes opciones, Enable Hyper-V
Windows Features y Install required Windows components for WSL 2
3. Instalar
## Instrucciones de uso
Maven: 
1. Comando que te permite iniciar la ejecución de la aplicación.\mvnw spring-boot:run
2. Comando que te permite empaquetar el proyecto 
.\mvnw package

# API

## Puntajes
- `/aprende/puntajes`
  - GET: traer todos los puntajes en las lecciones. Con los parametros de petición `usuario` y/o `nLeccion` se puede filtrar la búsqueda.
  - POST: crea un nuevo puntaje.
  - DELETE: elimina los puntajes identificados por los parámetros `usuario` y/o `nLeccion`; al menos uno de los filtros es necesario.
- `/aprende/puntajes/<id:String>
  - GET: trae el puntaje identificado por el id indicado.
  - DELETE: elimina el puntaje indicado.
  - 
- `/aprende/puntajes/numeros`
  - GET: dado el parametro de peticion `usuario`, retorna los puntajes del usuario indicado. Se puede filtrar la búsqueda con los parámetros de petición `nivel` y/o `nLeccion`.
  - 

### Tipos de datos

```
PuntajeIn {
        usuario     : String!
        precision   : Float!
        cpme        : Int!
        segundos    : Int!
        fecha       : String
        id_leccion  : String!
    }
```
````
PuntajeOut {
        id          : String!
        usuario     : String!
        precision   : Float!
        cpme        : Int!
        segundos    : Int!
        fecha       : String
        id_leccion  : String!
    }
````




 
