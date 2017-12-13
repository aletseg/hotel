<%-- 
    Document   : formCheckIn
    Created on : 21-nov-2017, 19:18:46
    Author     : Estela
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<!--        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">-->
        <!--Import materialize.css-->
<!--       <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen"/> -->
          <link type="text/css" rel="stylesheet" href="css/custom.css"/>
        <link type="text/css" rel="stylesheet" href="css/w3.css"/>
     
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="w3-container w3-center ">
             <h1>Formulari Client</h1>
        </div>
                <section>
                    <div class="w3-container">
                    <div class="w3-row">
                        <h2>Dades Habitació</h2>
                         <div class="w3-quarter"><b>Habitació: </b>${habitacio.numero}</div> 
                        <div class="w3-quarter"><b>Descripció: </b>${habitacio.tipoHab.descripcio}</div> 
                        <div class="w3-quarter"><b>Preu: </b>${habitacio.tipoHab.preu}.-€</div> 
                        <div class="w3-quarter"><b>Observacions: </b>${habitacio.observacions}</div> 
                     
                    </div>
                            <hr>
                     </div>       
                </section> 
                 <section>
                   <form action="<c:url value='dadesGestio'/>" method="post" id="altaClient" class="w3-container ">
                      <input type="hidden" name="opcio" value="altaEstancia"/>
                      <input type="hidden" name="numHab" value="${habitacio.numero}"/>

                        <div class="w3-row">
                                <h2>Dades Client</h2>
                           </div> 
                      <div class="w3-container">
                           <div class="w3-row">
                                <div class="w3-third">
                                    <select required name="tipoDocument" class="w3-select" id="tipoDoc">
                                        <option value="" disabled selected>Tipo Document*:</option>
                                        <c:forEach var="document" items="${llistaDoc}">
                                            <option value="${document.idDocument}">${document.document}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="w3-third">
                                    <label>Núm. Document*:</label>
                                    <input type="text" name="numDocument" required class="w3-input" id="numDocument"/>
                                </div>
                                <div class="w3-third">
                                    <label>Data Expedicio*:</label>
                                    <input type="date" name="dataExpedicio" required class="w3-input"/>
                                </div>
                   </div>
                  </div>
                                <div class="w3-third">
                                     <label>Nom*:</label>
                                     <input type="text" name="nom" class="w3-input" id="nom" required/>
                                </div>
                                <div class="w3-third">
                                    <label>1er Llinatge*:</label>
                                    <input type="text" name="cognom" class="w3-input" id="cognom" required></div>
                                <div class="w3-third">
                                     <label>2º Llinatge:</label>
                                     <input tyep="text" name="cognom2" class="w3-input" id="congnom2">
                                </div>
                                <div class="w3-third">
                                    <select required name="nacionalitat" class="w3-select" id="nacionalitat">
                                        <option value="" disabled selected>Nacionalitat*:</option>
                                        <c:forEach var="llista" items="${llistaNac}">
                                            <option value="${llista.idNacionalitat}">${llista.nacionalitat}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="w3-third">
                                    <label>Data Naixement*:</label>
                                    <input type="date" name="dataNaixement" class="w3-input" required id="dataNaixement"/>
                                </div>
                              
                                         <div class="w3-third">
                                        <h6>Sexe:</h6>
                                        <input type="radio" name="sexe" value="M" id="home"/>
                                        <label for="home">Home</label>
                                        <input type="radio" name="sexe" value="F" id="dona"/> 
                                        <label for="dona">Dona</label>            
                                         </div>
                                         <div class="w3-third">
                                            <label for="observacions">Observacions:</label>
                                            <textarea id="observacions" class="materialize-textarea" name="observacions"></textarea>
                                          
                                          </div>
                                    <div class="w3-third">
                                        <a class="w3-btn w3-teal" href="<c:url value="dadesGestio?opcio=cancelar"/>">Cancelar</a>
                                        <button class="w3-btn w3-teal" type="submit">Enviar</a>
                                    </div>
                                    
                            </form>
                            

                 

          </section>
                    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<!--                    <script type="text/javascript" src="js/materialize.min.js"></script> -->
                    <script type="text/javascript" src="js/utils/utilitats.js"></script>
                    <script type="text/javascript">
                        function validarDades(){
                                var dadesCorrectes ="Les següets dades són incorrectes:\n";
                                var numDoc = $('#numDocument').val();
                                var validNumDoc = "";
                                var dadesOk = true;
                                var tipoDoc = $('#tipoDoc').val();
                                if(tipoDoc ==='DNI'){
                                  validNumDoc = validarDNI(numDoc); 
                                   if(validNumDoc === false){
                                        dadesOk = false;
                                    }
                                }
                                if(tipoDoc ==='NIE'){
                                    validNumDoc = validarNIE(numDoc);
                                    if(validNumDoc === false){
                                        dadesOk = false;
                                    }
                                }
                                if(validNumDoc === false){
                                    dadesCorrectes += "- Nombre de document incorrecte";
                                    alert(dadesCorrectes);
                                }
                              return dadesOk;      
                        }
                        
                        $(document).ready(function(){
                            $('#altaClient').on('submit', function(e){
                             var validaciOK = validarDades();
                             if(validaciOK === false){
                              e.preventDefault();   
                             }

                            });
                        });
                    </script>
    </body>
</html>
