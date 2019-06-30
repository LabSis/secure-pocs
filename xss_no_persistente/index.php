<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XSS Indirecto</title>
    </head>
    <body>
        <form action="#" method="GET">
            <label>Buscar libro:</label>
            <input type="text" size="20" name="txtLibro">
            <input type="submit" value="Buscar">
        </form>
        <?php
        if(isset($_GET['txtLibro'])){
            if($_GET['txtLibro'] == "El principito"){                
                $html = "<h2>El principito</h2><br />";
                $html .= "Precio: $80<br />";
                $html .= "<input type=\"button\" value=\"Comprar\">";
                echo $html;
            }else{            
                $html = "<h2>No se ha encontrado el libro \"" . $_GET['txtLibro'] . "\"</h2>";
                echo $html;
            }
        }
        ?>
    </body>
</html>
