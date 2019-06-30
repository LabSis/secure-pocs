<?php
ini_set("display_errors", 1);

session_start();
$conexion = new mysqli("localhost","root","12345678","xss_directo");
	if (!is_null($conexion->connect_error)) {
		die("Error al tratar de conectarme con la base de datos");
	}
$conexion->set_charset("utf-8");
