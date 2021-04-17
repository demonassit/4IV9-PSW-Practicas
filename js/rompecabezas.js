//lo primero que vamos a obtener es todo el grupo de piezas

var piezas = document.getElementsByClassName('movil');

//vamos a necesitar variables ancho y largo para redimencionar las imagenes

var tamWidth = [134, 192, 134, 163, 134, 163, 134, 192, 134];
var tamHeight = [163, 134, 163, 134, 192, 134, 163, 134, 163];

for(var i = 0; i < piezas.length; i++){
    piezas[i].setAttribute("width", tamWidth[i]);
    piezas[i].setAttribute("height", tamHeight[i]);
    piezas[i].setAttribute("x", Math.floor((Math.random()*10)+1));
    piezas[i].setAttribute("y", Math.floor((Math.random()*409)+1));
    piezas[i].setAttribute("onmousedown", "seleccionarElemento(evt)");
}


var elementoSeleccionado = 0;
var currentX = 0;
var currentY = 0;
var currentPosX = 0;
var currentPosY = 0;

function seleccionarElemento(evt){
    //saber la pieza
    elementoSeleccionado = reordenar(evt);

    currentX = evt.clientX;
    currentY = evt.clientY;

    alert(currentX);
    alert(currentY);

    

    currentPosX = parseFloat(elementoSeleccionado.getAttribute("x"));
    currentPosY = parseFloat(elementoSeleccionado.getAttribute("y"));

    elementoSeleccionado.setAttribute("onmousemove", "moverElemento(evt)");
}


function moverElemento(evt){
    var dx = evt.clientX - currentX;
    var dy = evt.clientY - currentY;

    currentPosX = currentPosX + dx;
    currentPosY = currentPosY + dy;

    elementoSeleccionado.setAttribute("x", currentPosX);
    elementoSeleccionado.setAttribute("y", currentPosY);
    //para saber en que posicion lo suelto
    currentX = evt.clientX;
    currentY = evt.clientY;

    elementoSeleccionado.setAttribute("onmouseout", "deseleccionarElemento(evt)");
    elementoSeleccionado.setAttribute("onmouseup", "deseleccionarElemento(evt)");

    //para traer las piezas
    iman();
}

function deseleccionarElemento(evt){
    //saber si esta donde de estar
    testing();
    if(elementoSeleccionado != 0){
        elementoSeleccionado.removeAttribute("onmousemove");
        elementoSeleccionado.removeAttribute("onmouseout");
        elementoSeleccionado.removeAttribute("onmouseup");
        elementoSeleccionado = 0;
    }
}

var entorno = document.getElementById('entorno');

function reordenar(evt){
    var padre = evt.target.parentNode;
    alert('padre'+padre);
    var clone = padre.cloneNode(true);
    alert('clone'+clone);
    var id = padre.getAttribute("id");

    entorno.removeChild(document.getElementById(id));
    entorno.appendChild(clone);
    alert(entorno);
    return entorno.lastChild.firstChild;

}


//saber donde deben terminar el origen de las imag

var orginX = [200, 304, 466, 200, 333, 437, 200, 304, 466];

var orignY = [100, 100, 100, 233, 204, 233, 337, 366, 337];

function iman(){
    for(var i = 0; i < piezas.length; i++){
        if(Math.abs(currentPosX - orginX[i]) < 15 && Math.abs(currentPosY - orginY[i]) < 15){
            elementoSeleccionado.setAttribute("x", orginX[i]);
            elementoSeleccionado.setAttribute("y", orginY[i]);
        }
    }
}

//saber si la pieza esta donde debe de estar

function testing(){
    var bien_ubicada = 0;
    var padres = document.getElementsByClassName('padre');

    for(var i = 0; i < piezas.length; i++){
        var posx = parseFloat(padres[i].firstChild.getAttribute("x"));
        var posy = parseFloat(padres[i].firstChild.getAttribute("y"));

        ide = padres[i].getAttribute("id");

        if(orginX[ide] == posx && orignY[ide] == posy){
            bien_ubicada = bien_ubicada + 1;
        }
    }
    if(bien_ubicada == 9){
        //ganeeeee wiiiiii
        alert("Ganaste");
    }
}