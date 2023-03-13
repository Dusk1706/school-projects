let x=-60 // manecilla pequeña (esas las obtuve de prueba y error)
let y= -27 // manecilla pequeña
let contadorMin=1 //conteo minutos
let contadorSeg=1 //conteo segundos
function setup() {
    createCanvas(800,800)
    textFont('Arial')
    frameRate(1) //repetir una vez por segundo
  }
   
  function draw() {
    //print("X: " + mouseX + "Y: " + mouseY)
    background(120, 70, 3) //fondo cafe
    textSize(50) //tamaño texto
    fill(255, 196, 3) //color amarillo
    strokeWeight(5) //grosor linea
    circle(400,400, 550) //circulo amarillo grande
    textAlign(CENTER, CENTER) //alinear texto
    fill(255) //color blanco 
    text((contadorMin-1) + ":" + (contadorSeg) ,400,80) //tiempo parte central superior
    fill(0) //color negro
    text("12",400,170) 
    line(400,400, 400,190)
    text("6",400,640)
    line(400,400, 400,610)
    text("3",630,400)
    line(400,400, 610,400)
    text("9", 170,400)
    line(400,400, 190,400)
    translate(400,400) // transladar 400 a la derecha y a la izquierda
    fill(238, 223, 178) //color crema
    circle(0,0,400) //circulo central
    
    rotate(PI/6 + frameCount*0.10471975512) 
    //rotar 6 grados mas la cantidad de veces que se ha ejecutado el programa por 2pi entre 60
    line(0,0,-75,-130)//manecilla grande (las posiciones fueron de prueba y error)
    if(frameCount%60 === 0){ //si es multiplo de 60 la cantidad de veces q se ha ejecutado, significa que ya se cumplio un minuto
        rotate(PI/6 + contadorMin*0.10471975512) //se va a rotar la manecilla corta 6 grados
        line(0,0,x, y)
        contadorMin++ //saber cuantos minutos han tranascurrido
        contadorSeg=1 //si se llego a 60 segundos, se reinician los segundos
    }
    else{ //si no es multiplo de 60 significa que aun no se llega al minuto y se debe mostrar la manecilla pequeña fija
        rotate(PI/6 - (frameCount- contadorMin)*0.10471975512) 
        //se rota el lienzo de manera inversa ya que se debe imprimir de tal manera que la aguja pequeña no se mueva
        line(0,0,x, y)
        contadorSeg++// saber cuantos segundos han transcurrido
    }
    

  }
