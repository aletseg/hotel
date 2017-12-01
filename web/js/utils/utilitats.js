/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function estatHabitacions(){
    $("i[name='lliure']").addClass("light-green-text text-darken-3") ; 
    $("i[name='ocupat']").addClass("red-text text-darken-3") ;
    $("i[name='noDisponible'").addClass("grey-text text-lighten-1");
}

function obrirForm(url){
     window.open(url, "nuevo", "directories=no, location=no, menubar=no, scrollbars=yes, statusbar=no, tittlebar=no, width=400, height=400");
}

function carregaDadesCheckIn(opcio,numHab){
    $.ajax({
       url:'dadesGestio', 
       type:'GET',
       async: true,
       data:'opcio='+opcio+'&numHab='+numHab,
       success:function(resposta){
          if(resposta===true){
                obrirForm('formCheckIn.jsp');
                // Falta acabar, a saber dades del servlet
          } 
       }
    });//Final ajax
}

function afegeixUrl(){
    $("#Check-In").attr('href',function(){
       return this.href+ invokedOn.text(); 
    });
}

function canviaUrl(numHab){
   $("#Check-In").on('click',function(){
       var str = "dadesGestio?opcio=carregaDades&numHab=";
        $("#Check-In").removeAttr('href');
        $("#Check-In").addAttr('href', str+numHab);
   });
   
//    $("#Check-In").removeAttr('href');
//     $("#Check-In").addAttr('href')
}
