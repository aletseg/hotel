<%-- 
    Document   : home
    Created on : 12-nov-2017, 18:35:53
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
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div>
            <h1>Benvingut ${empleat.nom}</h1>
            <h2>ID: ${empleat.idEmpleat}</h2>
        </div>
        <section class="col s12">
        <div class="container z-depth-5" id="habitacions">
            <h2>Habitacions</h2>
            <table class="centered bordered" id="taulaHabitacions">
                <tr>
                    <c:set var="numPis" scope="session" value="1"/>
                        <c:forEach var="habitacio" items="${llistaHabitacions}" >
                            <c:choose>
                                <c:when test="${habitacio.estat=='Lliure'}">
                                    <c:set var="estat" value="lliure"/>
                                </c:when>
                                <c:when test="${habitacio.estat == 'Ocupat'}">
                                    <c:set var='estat' value="ocupat"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var='estat' value="noDisponible"/>
                                </c:otherwise>
                                
                            </c:choose>
                            <c:if test="${habitacio.pis gt numPis}">
                                 <c:set var="numPis" value="${habitacio.pis}"/>
                                </tr>
                                <tr>
                             </c:if> 
                             <c:choose>
                                 <c:when test="${habitacio.tipoHab.idTipo=='DB'}">
                                     <td>
                                         <h2>${habitacio.numero} </h2></br>
                                         <p><i class="glyphicon glyphicon-bed" style="font-size: 34px"  name="${estat}"></i></p>
                                       
                                     </td>
                                 </c:when>
                                <c:when test="${habitacio.tipoHab.idTipo=='DS'}">
                                    
                                        <td>
                                           <h2>${habitacio.numero}</h2>
                                            <p><i class="fa fa-bed" style="font-size: 24px"  name="${estat}"></i></br>
                                            <i class="fa fa-bed" style="font-size: 24px"  name="${estat}"></i></p>
                                           
                                            </td>                                  
                                 </c:when>
                                 <c:otherwise>
                                       <td>
                                          
                                         <h2>${habitacio.numero} </h2></br>
                                         <p name="${estat}"><i class="fa fa-bed" style="font-size: 34px"  name="${estat}"></i></p>
                                       
                                     </td>      
                                 </c:otherwise>        
                             </c:choose>                             
                                </c:forEach>
                   </table> 
                       
                        <div id="menu" class="collection" style="display:none">
                            <ul>
                                <li id="menu_altaClient" class="collection-item">Check-in Client</li>
                                <li id="menu_afegirClient" class="collection-item">Check-out Client</li>
                                <li id="menu_afegirMesClients" class="collection-item">Afegeix un altre client</li>
                                <li id="menu_veureFitxa" class="collection-item">Veure fitxa client</li>
                                <li id="menu_noDisponible" class="collection-item">Marcar com no disponible</li>
                            </ul>
                        </div>
                      
                </div>
                </section>        
                        <section id="checkIn" class="checkIn indigo lighten-4 col s12">
                    <div class="container">
                        
                        <div class="row" id="formAlta">
                            <form action="<c:url value='dadesGestio'/>" method="post" id="" class="">
<!--                                
                                <div class="input-field col s6">
                                <select name="tipoDocument">
                                    <option value="" disabled selected>Tipo document*:</option>
                                    <option value="">Proba</option>
                                    <option>Proba3</option>
                                    
                                </select>
                                </div>-->
                                
                                <div class="input-field col s12">
    <select>
      <option value="" disabled selected>Choose your option</option>
      <option value="1">Option 1</option>
      <option value="2">Option 2</option>
      <option value="3">Option 3</option>
    </select>
    <label>Materialize Select</label>
  </div>

                                

                                <label>Núm. Document*:</label><input type="text" name="numDocument"/>
                                <label>Nom*:</label><input type="text" name="nom"/>
                                <label>1er Llinatge*:</label><input type="text" name="cognom">
                                <label>2º Llinatge:</label><input tyep="text" name="cognom2">
                                <label></label><input type="" name=""/>
                                    
                            </form>
                            
                        </div>
                    </div>
          </section>
                        
                                <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
                               <script type="text/javascript" src="js/materialize.min.js"></script> 
                               <script type="text/javascript" src="js/utils/utilitats.js"></script>
                               <script type="text/javascript">
                                    $(document).ready(function(){
                                        estatHabitacions();
                                        $('select').material_select();
                                    var menu = $('#menu');
                                    $('#habitacions').on('click',function(){
////                                      menu.style.top = event.clientY + 'px';
////                                      menu.style.left = event.clientX + 'px';
                                   menu.show();
                                    }); 
                                  
                                  
                                    });//final del document ready
                                
                            
                               </script>
                               
                                </body>
                                </html>
