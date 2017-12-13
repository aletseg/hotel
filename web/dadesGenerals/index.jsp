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
         <link type="text/css" rel="stylesheet" href="css/custom.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuari Erroni</title>
    </head>
    <body>
    <div class="nav-wrapper deep-purple darken-4 z-depth-5 valign-wrapper">
          <h1 class="deep-purple-text text-lighten-4 marge">Hostal Sorrento</h1>
      </div>

        
            <!-- Modal Structure -->
  <div id="modal1" class="modal">
    <div class="modal-content">
      <h4>${missatge}</h4>
      <p>Torna a fer el login</p>
    </div>
    <div class="modal-footer">
     <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">D'acord</a> 
    </div>
  </div>
           <section>
        <div class="fondo">
            <div class="container">
                <div class="row s12 m6">
                    <div class="card medium z-depth-5">
                        <div class="card-content">
                           <a class="waves-effect waves-light btn modal-trigger red red lighten-1" href="#modal1"><i class="material-icons" style="font-size:22px">announcement</i></a> 
                            <span class="card-title center indigo-text">Login</span>
                            <p class="center-align indigo-text">(Has inserit un usuari o contrasenya incorrecta)</p>
                           
                                <form id="login" method="post" action="dadesGestio">
                                 <input type="hidden" value="login" name="opcio"/>
                                 <label class="indigo-text lletragran"><b>Usuari:</b></label>
                                 <input type="text" id="usuari" name="usuari"/>
                                 <h5><label class="indigo-text lletragran">Contrasenya:</label></h5>
                                 <input type="password" name="contrasenya" />
                                 <button type="submit" class="btn pink darken-3 z-depth-2 right">Enviar</button>
                             </form>
                        </div>
                </div>
                </div>
            </div>
        </div>
          </section>
      
             <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>
    <script type="text/javascript">
         $(document).ready(function(){
            // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
             $('.modal').modal();
         
           });
    </script>
    </body>
 
</html>
