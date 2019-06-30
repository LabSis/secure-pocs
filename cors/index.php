<html>
    <head>
        <!-- No hay control de dominio cruzado si la etiqueta es script. -->
        <script src="https://labsys.frc.utn.edu.ar/wiki-seguridad/js/jquery.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha256-k2WSCIexGzOj3Euiig+TlR8gA0EmPjuc79OEeY5L45g="
            crossorigin="anonymous"></script>
        <script type="text/javascript">
            window.onload = function(){
                function request(url){
                    var http = new XMLHttpRequest();
                    http.onreadystatechange = function handler(r){
                        if (this.readyState == 4 && this.status == 200) {
                            console.log(r);
                        }
                    };

                    http.open('GET', url, true);
                    http.send();
                }
                // Se consulta un script de otro dominio que no tiene Access-Control-Allow-Origin. El navegador bloquea la petición.
                request('https://labsys.frc.utn.edu.ar/wiki-seguridad/js/jquery.js');
                
                // Se consulta un script de otro dominio que tiene Access-Control-Allow-Origin: *. El navegador no bloquea la petición.
                request('https://code.jquery.com/jquery-3.2.1.slim.min.js');
                
                // Se consulta un script local. No hace falta el Access-Control-Allow-Origin.
                request('data.php');
            };
        </script>
    </head>
    <body>
        Ejemplo de solicitudes de recursos. Abrir la consola.
    </body>
</html>
