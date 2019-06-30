# Taller de SQL Injection

En este repositorio se incluyen ejemplos sobre cómo escribir código vulnerable a SQL Injection en 5 tecnologías diferentes: Django, JEE, .NET, NodeJS y PHP. También, se brinda el código correcto para defenderse contra este tipo de ataques.

* El enunciado práctico se encuentra en: https://docs.google.com/document/d/16FjTX9wiTm7AAjxRy2NE1AJ36NdqJsPpVwHet4nRUlk/edit?usp=sharing.
* Más información en: https://labsys.frc.utn.edu.ar/investigacion/index.php?controller=proyecto&action=show&id=78

## JEE ##
Para desplegar este ejemplo práctico en Apache Tomcat se debe seguir los siguientes pasos:

1. Instalar Tomcat 8. Suficiente con apt-get install tomcat8.

1. sudo javac -cp .:/usr/share/tomcat8/lib/servlet-api.jar:/usr/share/tomcat8-examples/examples/jee/backend/lib/mysql-connector-java-5.1.33-bin.jar Proyecto.java AccesoDatos.java FilterVulnerable.java FilterSeguro.java Projects.java
2. Copiar los archivos compilados (.class) y los archivos estáticos al directorio webapps de tomcat.
3. Configurar el archivo web.xml con lo siguiente:

    <servlet>
        <servlet-name>Projects</servlet-name>
        <servlet-class>Projects</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>Projects</servlet-name>
        <url-pattern>/servlets/servlet/Projects</url-pattern>
    </servlet-mapping>
    
    <servlet>
        <servlet-name>FilterVulnerable</servlet-name>
        <servlet-class>FilterVulnerable</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>FilterVulnerable</servlet-name>
        <url-pattern>/servlets/servlet/FilterVulnerable</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>FilterSeguro</servlet-name>
        <servlet-class>FilterSeguro</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>FilterSeguro</servlet-name>
        <url-pattern>/servlets/servlet/FilterSeguro</url-pattern>
    </servlet-mapping>

4. Copiar el driver de MySQL para Java al directorio $CATALINA_HOME/lib. Normalmente este directorio es /usr/share/tomcat8. Para visualizar esta variable de entorno se puede ejecutar ps aux | grep catalina.
5. Reiniciar el Apache Tomcat.


## Vector de ataque ##

El vector de ataque en todos los ejemplos es: 1 OR 1=1 -- ;
