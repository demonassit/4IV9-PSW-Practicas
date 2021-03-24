/*
Quiero una funcion que se encargue de validar
la entrada de los datos para que el usuario deba
de escribir mas de 5 caracteres dentro del campo nombre
*/

function validar(formulario){

    if(formulario.nombre.value.length < 5){
        alert("Escriba por lo menos 5 caracteres dentro del campo de nombre");
        formulario.nombre.focus();
        return false;
    }

    /*
    quiero validar que sea unicamente letras en el
    campo de nombre
    */

    var checkOK = "QWERTYUIOPASDFGHJKLÑZXCVBNM" 
    + "qwertyuiopasdfghjklñzxcvbnm";

    var checkStr = formulario.nombre.value;

    var allvalid = true;

    for(var i = 0; i < checkStr.length; i++){
        var ch = checkStr.charAt(i);
        for(var j = 0; j < checkOK.length; j++)
        if(ch == checkOK.charAt(j))
            break;

        if(j == checkOK.length){
            allvalid = false;
            break;
        }
    }

    if(!allvalid){
        alert("Escribe solo letras en el campo nombre");
        formulario.nombre.focus();
        return false;
    }

    /*
    debemos validar el campo de edad para que unicamente
    acepte numeros
    */

    var checkOK = "1234567890";

    var checkStr = formulario.edad.value;

    var allvalid = true;

    for(var i = 0; i < checkStr.length; i++){
        var ch = checkStr.charAt(i);
        for(var j = 0; j < checkOK.length; j++)
        if(ch == checkOK.charAt(j))
            break;

        if(j == checkOK.length){
            allvalid = false;
            break;
        }
    }

    if(!allvalid){
        alert("Escribe solo numeros en el campo edad");
        formulario.edad.focus();
        return false;
    }

    /*
    Necesitamos una expresion regular para poder validar
    la entrada de un correo electronico
    algo@algo.algo
    algo.algo@algo.algo.algo
    */ 

    var txt = formulario.email.value;

    var b = /^[^@\s]+@[^@\.\s]+(\.[^@\.\s]+)+$/;

    alert("Email "+(b.test(txt)?"":" no ")+" valido");
    
    return b.test(txt);


}