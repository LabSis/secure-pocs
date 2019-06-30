<?php

header('Content-Type: text/html; charset=utf-8');
header('Access-Control-Allow-Origin: *');
error_reporting(0);
//variables
$btn_ingresar = filter_input(INPUT_POST, "btnIngresar");
$nombre = filter_input(INPUT_POST, "txtNombre");
$clave = filter_input(INPUT_POST, "txtContrasenia");
if(isset($btn_ingresar)){
	//conexion
	$conexion = new mysqli('localhost', 'fb', '741852963', 'fuerza_bruta');
	if (!is_null($conexion->connect_error)) die ("Error con conexion a base de datos");
	$nombre = mysqli_real_escape_string($conexion, $nombre);
	$clave = mysqli_real_escape_string($conexion, $clave);
	$consulta = ("SELECT id FROM usuarios WHERE (nombre = '$nombre' AND clave = '$clave')");
	$resultados = $conexion->query($consulta);
	if($resultados !== false && $resultados->num_rows > 0){
		echo "Ingreso exitoso! <br/>";
		echo "Felicitaciones, pasaste el desafío";
	}else{
		echo "Datos inválidos";
	}
}
?>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ingreso al sistema</title>
	</head>
	<body>
		<form method="post" action="index.php">
			<h3>Login al sistema<h3>
			<table>
				<tr>
					<td>
						<label>
							Nombre:
						</label>
					</td>
					<td>
						<input type="text" name="txtNombre" placeholder="Nombre de usuario" autocomplete="off"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							Contraseña:
						</label>
					</td>
					<td>
						<input type="password" name="txtContrasenia" placeholder="Contraseña" autocomplete="off"/>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
						<input type="submit" name="btnIngresar" value="Ingresar"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
