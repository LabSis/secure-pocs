const express = require('express');
const app = express()

app.use(express.static(__dirname + '/../../frontend'));

app.get('/', function (req, res) {
	res.sendFile(__dirname + '/../../frontend/index.html');
})

app.get('/projects', function (req, res) {
	db.consultar("SELECT p.codigo AS code, p.nombre AS name, a.nombre AS owner, t.nombre AS type, n.nombre AS level " +
				"FROM proyectos AS p " +
				"INNER JOIN agentes AS a ON a.id = p.id_agente " +
				"INNER JOIN niveles AS n ON n.id = p.id_nivel " +
				"INNER JOIN tipos AS t ON t.id = p.id_tipo " +
				"WHERE n.id >= 2 " + 
				"ORDER BY p.codigo " +
				";", 
		function(filas){
			var proyectos = filas;
			res.json({ 'status': 'ok', 'data': {'projects': proyectos } });
		},
		function(error){
			res.json({ 'status': 'error' });
		}
	);
})

app.get('/projects/filter', function (req, res) {
	var tipo = req.query.type;
	db.consultar("SELECT p.codigo AS code, p.nombre AS name, a.nombre AS owner, t.nombre AS type, n.nombre AS level " +
				"FROM proyectos AS p " +
				"INNER JOIN agentes AS a ON a.id = p.id_agente " +
				"INNER JOIN niveles AS n ON n.id = p.id_nivel " +
				"INNER JOIN tipos AS t ON t.id = p.id_tipo " +
				"WHERE n.id >= 2 AND t.id = " + mysql.escape(tipo) + " " +
				"ORDER BY p.codigo " +
				";", 
		function(filas){
			var proyectos = filas;
			res.json({ 'status': 'ok', 'data': {'projects': proyectos } });
		},
		function(error){
			res.json({ 'status': 'error' });
		}
	);
})

app.listen(3000, function () {
  console.log('Running on localhost:3000')
})

// Conexion a BD
var mysql = require('mysql');
var conexion = mysql.createConnection({
	host     : 'localhost',
	user     : 'root',
	password : '',
	database : 'nsa'
});
var db = {
	conectar: function(){
		conexion.connect();
	},
	consultar: function(consulta, onSuccess, onError){		
		conexion.query(consulta, function (error, filas, columnas) {
			if (error){
				if(onError !== undefined){
					onError(error);
				}else{
					console.error(error);
				}
			}else{
				onSuccess(filas);
			}
		});
	},
	desconectar: function(){
		conexion.end();
	}
};

db.conectar();
