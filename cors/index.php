<html>
    <head>
        <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
        <!-- No hay control de dominio cruzado si la etiqueta es script, salvo que se ponga type module -->
        <script src="https://labsys.frc.utn.edu.ar/wiki-seguridad/js/jquery.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha256-k2WSCIexGzOj3Euiig+TlR8gA0EmPjuc79OEeY5L45g="
            crossorigin="anonymous"></script>
        <script type="text/javascript">
            window.onload = function(){
                function request(name, metodo, url){
                    var http = new XMLHttpRequest();
                    http.onreadystatechange = function handler(r) {
                        if (this.readyState == 4) {
                            console.log("===================");
                            console.log("URL: " + url);
                            console.log("Nombre: " + name);
                            if (this.status == 200) {
                                console.log("Resultado: Éxito");
                            } else {
                                console.log("Resultado: Error");
                            }
                        }
                    };

                    http.open(metodo, url, true);
                    http.send();
                }
                // Se consulta un script de otro dominio que no tiene Access-Control-Allow-Origin. El navegador bloquea la petición.
                request('Dominio cruzado sin header Access-Control-Allow-Origin: *', 'GET', 'https://labsys.frc.utn.edu.ar/wiki-seguridad/js/jquery.js');
                
                // Se consulta un script de otro dominio que tiene Access-Control-Allow-Origin: *. El navegador no bloquea la petición.
                request('Dominio cruzado con header Access-Control-Allow-Origin: *', 'GET', 'https://code.jquery.com/jquery-3.2.1.slim.min.js');
                
                // Se consulta un script local. No hace falta el Access-Control-Allow-Origin.
                request('Script local', 'PUT', 'data.php');
                
                // Se consulta un script local. No hace falta el Access-Control-Allow-Origin.
                request('Script local', 'PUT', 'https://www.pabex.com.ar/demo/cors/data.php');
            };
        </script>
    </head>
    <body>
        Ejemplo de solicitudes de recursos. Abrir la consola.
    </body>
</html>
