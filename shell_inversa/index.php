<?php
    ini_set("display_errors", 1);
    error_reporting(0);
    $ip_maquina = filter_input(INPUT_POST, "ip_maquina");
    if (isset($ip_maquina)) {
        $cadena = shell_exec("ping -c 4 " . $ip_maquina);
    }
?>
<html>
    <head>
        <title>Shell inversa</title>
    </head>
    <body>
        <form method="POST">
            <input type="text" name="ip_maquina" />
            <input type="submit" value="Enviar ping" />
        </form>
        <p>
            Salida de comando
        </p>
        <pre><?php echo $cadena ?></pre>
    </body>
</html>
