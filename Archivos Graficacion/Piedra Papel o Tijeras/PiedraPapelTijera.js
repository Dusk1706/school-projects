let clasificador
let video
let flippedVideo
let etiqueta = ""
let texto = ""
let portadaImage
let portadaGame
let portadaFinal
let imageModelURL = './model/'
let objetos=[
  {
    nombre: 'Piedra',
    emoji: '👊',
    gana: 'Tijeras'
  },
  {
    nombre: 'Papel',
    emoji: '✋',
    gana: 'Piedra'
  },
  {
    nombre: 'Tijeras',
    emoji: '✌️',
    gana: 'Papel'
  }
]
let contadorSeg=3
let seleccionPC
let seleccionP1
let estado=0
let puntajeP1=0
let puntajePC=0


function preload() {
  clasificador = ml5.imageClassifier(imageModelURL+'model.json')
  portadaImage = loadImage('images/Portada Juego.jpg')
  portadaGame = loadImage('images/Portada Jugando.jpg')
  portadaFinal = loadImage('images/Portada Final.jpg')
}

function setup() {
  createCanvas(640,480)
  frameRate(1)
  video = createCapture(VIDEO)
  video.size(640,480)
  video.hide()
  flippedVideo = ml5.flipImage(video)
  //Iniciar la clasificación
  classifyVideo()
  textSize(32)
}

function draw() {
  if(estado==0){
    image(portadaImage,0,0)
  }else if(estado==1){
    fill(0)
    textSize(32)
    image(portadaGame,0,0)
    text(puntajeP1,120, 105)
    text(puntajePC,560, 105)
    text(etiqueta,60,150)
   
    text(contadorSeg,width/2,100)
    if(frameCount%1==0){
      contadorSeg--
    }
    
    //Cada 3 segundos determina el ganador de la ronda
    if(contadorSeg%3==0){
      for(let i=0;i<objetos.length;i++){
        if(etiqueta==objetos[i].nombre){
          seleccionP1=objetos[i]
          break
        }
      }
      let ganador=determinarGanador()
      text(seleccionPC.nombre,500,150)
      text(ganador,240,200)
      contadorSeg=3
    }
    //Checa si ya gano alguien
    if(puntajeP1==3 || puntajePC==3){
      estado=2
    }
    if(contadorSeg<0){
      contadorSeg=3
    }
    
  }else{
    image(portadaFinal,0,0)  
    textSize(50)
    if(puntajeP1>puntajePC){
      fill(69, 145, 25)
      text('Has Ganado :)',200,250)
    }else{
      fill(240, 26, 26)
      text('Has Perdido :(',200,250)
    }
    estado=0
    puntajeP1=0
    puntajePC=0
  }
}

function determinarGanador(){
  seleccionPC=random(objetos)
  textSize(100)
  if(seleccionP1.nombre==seleccionPC.nombre){
    text(seleccionP1.emoji,60,300)
    text(seleccionPC.emoji,450,300)
    textSize(32)
    return "EMPATE"
  }else if(seleccionP1.gana==seleccionPC.nombre){
    text(seleccionP1.emoji,60,300)
    text(seleccionPC.emoji,450,300)
    puntajeP1++
    textSize(32)
    return "GANADOR P1"
  }else{
    text(seleccionP1.emoji,60,300)
    text(seleccionPC.emoji,450,300)
    puntajePC++
    textSize(32)
    return "GANADOR PC"
  }
}

function mousePressed() {
  if(estado==0){
    estado=1
  }
}

function classifyVideo() {
  flippedVideo = ml5.flipImage(video)
  clasificador.classify(flippedVideo, gotResult)
  flippedVideo.remove()
}

function gotResult(error, results) {
  if (error) {
    console.log(error)
    return
  }
  console.table(results)
  etiqueta = results[0].label
  classifyVideo()
}
