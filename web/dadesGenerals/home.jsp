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
        
       <!-- Dropdown Structure -->
<ul id="dropdown1" class="dropdown-content">
  <li><a href="#!">Afegir Empleat</a></li>
  <li><a href="#!">Eliminar Empleat</a></li>
  <li class="divider"></li>
  <li><a href="#!">Canviar Preus Habitacions</a></li>
</ul>
        <nav>
        <div class="nav-wrapper  deep-purple darken-4 z-depth-5 ">
            <h1 class="brand-logo marge">Recepció</h1>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="dadesGestio?opcio=canviarUsuari" class="lletragran">Cambiar Usuari</a></li>
                <li><a href="#" class="lletragran">Crear fitxa Policia</a></li>
                <c:if test="${empleat.tipo=='administrador'}">
                 <li><a class="dropdown-button lletragran" href="#!" data-activates="dropdown1">Administrador<i class="material-icons right">arrow_drop_down</i></a></li>
                 </c:if>
            </ul>
        </div>
        </nav> 
      
        <section class="col s12">
        <div class="container z-depth-5" id="habitacions">
            <h2>Habitacions</h2>
            <table class="centered bordered bottomMargin" id="taulaHabitacions">
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
                                     <td id="${habitacio.numero}">
                                         <h2>${habitacio.numero} </h2></br>
                                         <p name="${habitacio.numero}"><i class="glyphicon glyphicon-bed" style="font-size: 34px"  name="${estat}"></i></p>
                                       
                                     </td>
                                 </c:when>
                                <c:when test="${habitacio.tipoHab.idTipo=='DS'}">
                                    
                                        <td id="${habitacio.numero}">
                                           <h2>${habitacio.numero}</h2>
                                            <p><i class="fa fa-bed" style="font-size: 24px"  name="${estat}"></i></br>
                                            <i class="fa fa-bed" style="font-size: 24px"  name="${estat}"></i></p>
                                           
                                            </td>                                  
                                 </c:when>
                                 <c:otherwise>
                                       <td id="${habitacio.numero}">
                                          
                                         <h2>${habitacio.numero} </h2></br>
                                         <p name="${estat}"><i class="fa fa-bed" style="font-size: 34px"  name="${estat}"></i></p>
                                       
                                     </td>      
                                 </c:otherwise>        
                             </c:choose>                             
                                </c:forEach>
                   </table> 
                     
        <ul id="contextMenu" class="dropdown-menu" role="menu" style="display:none" >
            <li><a  id="Check-In" tabindex="-1" href="dadesGestio?opcio=carregaDades&numHab=">Check-In</a></li>
           <li><a id="Check-Out" tabindex="-1" href="dadesGestio?opcio=carregaEstanciaBaixa&numHab=">Check-Out</a></li>
           <li><a id="mostraFitxa" tabindex="-1" href="dadesGestio?opcio=mostraFitxa&numHab=">Veure Fitxa</a></li>
            <li class="divider"></li>
           <li><a tabindex="-1" href="#">Habitació No Disponible</a></li>
          
           
</ul>
        
                      
                </div>
                        
                        
                </section>        
   <div class="container">
          <h5>Empleat: ${empleat.nom}</h5>
          </div> 
                        
                                <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
                               <script type="text/javascript" src="js/materialize.min.js"></script> 
                               <script type="text/javascript" src="js/utils/utilitats.js"></script>
                               <script type="text/javascript">
(function ($, window) {

    $.fn.contextMenu = function (settings) {
       
        return this.each(function () {
               
            // Open context menu
            $(this).on("contextmenu", function (e) {
               
                // return native menu if pressing control
                if (e.ctrlKey) return;
                
                //open menu
                var $menu = $(settings.menuSelector)
                    .data("invokedOn", $(e.target))
                    .show()
                    .css({
                        position: "absolute",
                        left: getMenuPosition(e.clientX, 'width', 'scrollLeft'),
                        top: getMenuPosition(e.clientY, 'height', 'scrollTop')
                    })
                    .off('click')
                    .on('click', 'a', function (e) {
                        $menu.hide();
                
                        var $invokedOn = $menu.data("invokedOn");
                        var $selectedMenu = $(e.target);
                        
                        settings.menuSelected.call(this, $invokedOn, $selectedMenu);
                    });
                
                return false;
            });

            //make sure menu closes on any click
            $('body').click(function () {
                $(settings.menuSelector).hide();
            });
        });
        
        function getMenuPosition(mouse, direction, scrollDir) {
            var win = $(window)[direction](),
                scroll = $(window)[scrollDir](),
                menu = $(settings.menuSelector)[direction](),
                position = mouse + scroll;
                        
            // opening menu would pass the side of the page
            if (mouse + menu > win && menu < mouse) 
                position -= menu;
            
            return position;
        }    

    };
})(jQuery, window);

$("#taulaHabitacions td").contextMenu({
        menuSelector: "#contextMenu",
        menuSelected: function (invokedOn ,selectedMenu) {
       
       var num = invokedOn.text();
        var msg = "You selected the menu item '" + selectedMenu.text() +
            "' on the value '" + invokedOn.text() + "'";
       // alert(msg);
         var opcioMenu = selectedMenu.text();
          if(invokedOn.text()===""){
              alert("Error al seleccionar l'habitació! \n - Has de clicar sobre el nombre d'habitació" ); 
           
          }        
         switch(opcioMenu) {
             case 'Check-In':
                 var numHab = invokedOn.text();
               // alert('nhab:' + numHab);   
                    $("#Check-In").attr('href',function(){
                        return this.href+ invokedOn.text(); 
                     }); 
                break;
            case 'Check-Out':
                $('#Check-Out').attr('href',function(){
                    return this.href + invokedOn.text();  
                });
                break;
            case 'Veure Fitxa':
                $('#mostraFitxa').attr('href',function(){
                     return this.href + invokedOn.text();
                });
         }
            
   
            
        
    }
    
    });
                                   $(document).ready(function(){
                                        estatHabitacions();
                                        $(".dropdown-button").dropdown();
                                          
                                   });// Final document ready
                                  
//                                
                                 
                            
                               </script>
                               
                                </body>
                                </html>
