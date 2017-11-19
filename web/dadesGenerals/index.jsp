<%-- 
    Document   : index
    Created on : 11-nov-2017, 20:31:52
    Author     : Estela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <div class="fondo">
            <h1>${missatge}</h1> 
            <div>
                <form id="login" method="post" action="dadesGestio">
                    <input type="hidden" value="login" name="opcio"/>
                    <label>Usuari:</label>
                    <input type="text" id="usuari" name="usuari"/>
                    <label>Contrasenya:</label>
                    <input type="password" name="contrasenya" />
                    <button type="submit">Enviar</button>

                </form>
            </div>
        </div>
             <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
