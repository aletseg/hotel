<%-- 
    Document   : dadesClient.
    Created on : 06-dic-2017, 18:28:29
    Author     : Estela
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
       <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen"/> 
       <link type="text/css" rel="stylesheet" href="css/custom.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dades Client</title>
    </head>
    <body>
       
        <section class="col s12">
            <div class="container z-depth-5" id="habitacions">
                <h1>Dades Client</h1>
                 <div class="row">
                     <div class="col s2"><b>Habitació:</b> ${habitacio.numero}</div>
                     <div class="col s4"><b>Descripció:</b> ${habitacio.tipoHab.descripcio}</div>
                     <div class="col s2"><b>Pis:</b> ${habitacio.pis} </div>
                    <div class="col s2"><b>Preu:</b> ${habitacio.tipoHab.preu}.-€</div>
                </div>
            </div>
        </section>
    </body>
</html>
