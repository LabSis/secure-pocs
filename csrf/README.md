## Cross Site Request Forgery (CSRF)


### Ataque por GET
La demo consiste de 2 páginas web:
- Un sitio bueno donde te permite iniciar sesión.
- Un sitio malicioso que te desloguea del sitio bueno simplemente con entrar al mismo.

### Ataque por POST
La demo consiste de 2 páginas web:
- Un sitio bueno donde te permite iniciar sesión.
- Un sitio malicioso que te desloguea del sitio bueno cuando se hace clic en el "botón mágico". No se puede realizar automáticamente por si hiciéramos un XHR fallaría por CORS.


Por ejemplo, si agregamos el siguiente código JavaScript:
```
<script>
    var formData = new FormData();

    formData.append("username", "Groucho");
    formData.append("accountnum", 123456);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://815e-201-235-19-245.ngrok.io/LabSis/poc/csrf/ataque_por_post/process.php?action=logout");
    xhr.send(formData);
</script>
```

