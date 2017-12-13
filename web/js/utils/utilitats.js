/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function estatHabitacions(){
    $("i[name='lliure']").addClass("light-green-text accent-4") ;
    $("i[name='ocupat']").addClass("red-text text-accent-4") ;
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
}

function validarDNI(dni){
  var numero
  var letr
  var letra
  var expresion_regular_dni
  var correcte
 
  expresion_regular_dni = /^\d{8}[a-zA-Z]$/;
 
  if(expresion_regular_dni.test (dni) === true){
     numero = dni.substr(0,dni.length-1);
     letr = dni.substr(dni.length-1,1);
     numero = numero % 23;
     letra='TRWAGMYFPDXBNJZSQVHLCKET';
     letra=letra.substring(numero,numero+1);
    if (letra!==letr.toUpperCase()) {
      correcte = false;
     }else{
       correcte= true;
     }
  }else{
     correcte = false;
   }
   return correcte;
}


function validarNIE(value){
    var validChars = 'TRWAGMYFPDXBNJZSQVHLCKET';

  var nieRexp = /^[XYZ]{1}[0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]{1}$/i;
  var str = value.toString().toUpperCase();

  if (!nieRexp.test(str)) return false;

  var nie = str
      .replace(/^[X]/, '0')
      .replace(/^[Y]/, '1')
      .replace(/^[Z]/, '2');

  var letter = str.substr(-1);
  var charIndex = parseInt(nie.substr(0, 8)) % 23;

  if (validChars.charAt(charIndex) === letter) return true;

  return false; 
}

/*
 * function validarDocId(value){
    var validChars = 'TRWAGMYFPDXBNJZSQVHLCKET';
  var nifRexp = /^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]{1}$/i;
  var nieRexp = /^[XYZ]{1}[0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]{1}$/i;
  var str = value.toString().toUpperCase();

  if (!nifRexp.test(str) && !nieRexp.test(str)) return false;

  var nie = str
      .replace(/^[X]/, '0')
      .replace(/^[Y]/, '1')
      .replace(/^[Z]/, '2');

  var letter = str.substr(-1);
  var charIndex = parseInt(nie.substr(0, 8)) % 23;

  if (validChars.charAt(charIndex) === letter) return true;

  return false; 
}
 */
 