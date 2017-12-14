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
        <title>Fitxa Client</title>
    </head>
    <body class="indigo lighten-5 ">
        <div class="nav-wrapper deep-purple darken-4 z-depth-4 valign-wrapper">
             <h1 class="deep-purple-text text-lighten-4 marge">Fitxa Client</h1> 
        </div>
        <section class="col s12">
            <div class="container indigo-text" id="habitacions">
                 <div class="row">
                     <h3>Habitació</h3>
                     <div class="col s2"><b>Habitació:</b> ${habitacio.numero}</div>
                     <div class="col s4"><b>Descripció:</b> ${habitacio.tipoHab.descripcio}</div>
                     <div class="col s2"><b>Pis:</b> ${habitacio.pis} </div>
                    <div class="col s2"><b>Preu:</b> ${habitacio.tipoHab.preu}.-€</div>
                </div>
            </div>
                <hr>
        </section>
                <section class="container espais ">
                    <div class="card large z-depth-4 rodo">
                        <div class="card-content">
                         <span class="card-title indigo-text margeAll"><b>Client</b></span>  
<!--                        <h3>Client</h3>-->
                    <div class="container">
                        <div class="row">
                            <div class="col s4 lletraTitol">
                                <b>Nom:</b> ${client.nom}
                            </div>
                            <div class="col s4 lletraTitol">
                                <b>Llinatge:</b> ${client.cognom1}
                            </div>
                              <div class="col s4 lletraTitol">
                                <b>2º Llinatge:</b> ${client.cognom2}
                            </div>
                            <hr>
                        </div>
                            <div class="row">
                            <div class="col s4 lletragran">
                                <b>Data naixement:</b>&nbsp; <fmt:formatDate type="date" value="${client.dataNaixement}"/> 
                            </div>
                            
                              <div class="col s4 lletragran">
                                  <b>Nacionalitat:</b> ${client.idNacio.nacionalitat}
                            </div>
                            <div class="col s4 lletragran">
                                <b>Sexe:</b>
                                <c:choose>
                                      <c:when test="${client.sexe eq 'F'}">
                                      Dona
                                  </c:when>
                                  <c:otherwise>
                                      Home
                                  </c:otherwise>
                                  </c:choose>
                            </div>
                        </div>
                            
                              <div class="row">
                              <div class="col s4 lletragran">
                                  <b>Tipo Document:</b> ${client.tipoDocument.document}
                            </div>
                            <div class="col s4 lletragran">
                                <b>Sexe:</b> 
                               
                            </div>
                            <div class="col s4 lletragran">
                                <b>Data Exp. Document:</b>&nbsp; <fmt:formatDate type="date" value="${client.dataExpedicioDoc}"/> 
                            </div>
                        </div>
                           
                    </div>
                            
                            </div>
                             <div class="container marginBaix"
                            <div class="row">
                            <div class="col s6 right">
                             <a class="btn pink darken-3 z-depth-2 right" href="<c:url value="dadesGestio?opcio=cancelar"/>">Tornar Habitacions</a>
                             </div>
                            </div> 
                        </div>
                          
                        

                </section>
                 
    </body>
</html>
