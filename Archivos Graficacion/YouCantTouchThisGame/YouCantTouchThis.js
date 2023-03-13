let imagenFondo
let imagenPersonaje
let imagenInicio
let imagenObstaculo
let musicaFondo
let musicaRecord
let estado = 0 //0 = pantalla inicial; 1 = jugando
let x = 0
let posY = 50
let dx = 0
let dy = 3
let record = 0
let puntaje = 0
let wx = [600, 900]
let wy = [400, 600]

function preload() {
  imagenFondo = loadImage('images/Nivel 1.jpg')
  imagenInicio = loadImage('images/Fondo Inicio.jpg')
  imagenPersonaje = loadImage('images/ironman.gif')
  imagenObstaculo = loadImage('images/Obstaculo.png')
  fuenteTexto = loadFont('fonts/Minecraft.ttf')
  musicaFondo = loadSound('sound/SonidoFondo.mp3')
  musicaRecord = loadSound('sound/SonidoRecord.mp3')
}

function setup() {
  createCanvas(600,800)
  textSize(40)
  textFont('Minecraft')
}

function draw() {
  if (estado === 1) {
    imageMode(CORNER)
    image(imagenFondo,x,0)
    image(imagenFondo,x+imagenFondo.width,0)
    x -= 6 //Desplazamiento negativo para el fondo
    dy += 1
    posY += dy 
    if (abs(x)>imagenFondo.width) {
      x = 0
    }

    
    for (let i=0; i < 2; i++) {
      imageMode(CENTER)
      image(imagenObstaculo, wx[i], wy[i]-(imagenObstaculo.height/2+150))
      image(imagenObstaculo, wx[i], wy[i]+(imagenObstaculo.height/2+150))  
      //Generar nuevas coordenadas para los obstáculos
      if (wx[i] < 0) {
        wx[i] = width
        wy[i] = random(200, height-200)
      }
      //Acumulamos los puntos logrados
      if (wx[i] === width / 2 ) {
          puntaje++
      }      
      //Validamos que el personaje no choque
        if (posY > height || posY < 0 || (abs(width/2 - wx[i])<50 && abs(posY - wy[i]) > 150)) {
          estado = 0
          musicaFondo.stop()
          if(puntaje>record){
            record=puntaje
            musicaRecord.play();            
          }
          if(frameCount%150==0){
            musicaRecord.stop()
          }
          cursor()
        }
      wx[i] -=6 //Desplazamiento de los obstáculos
    }
    imageMode(CENTER)
    image(imagenPersonaje,width/2, posY,imagenPersonaje.width/3,imagenPersonaje.height/3 )   
    text("Puntaje: " + puntaje, width/2-50, 50)
  } else {
    fill(255)
    stroke(0)
    strokeWeight(7)
    imageMode(CENTER)
    image(imagenInicio, width/2, height/2)
    text(puntaje, 290, 340)
    text(record, 290, 420)
  }

}

function mousePressed() {
  dy = -15
  if (estado === 0) {
    estado = 1
    x = 0
    posY = 50
    godY=50
    dy = 3
    puntaje = 0
    noCursor()
    recordAnterior = record
    wx = [600, 900]
    wy = [400, 600]
    musicaFondo.play()    
  }
}

function touchStarted() {
  dy = -15
}





