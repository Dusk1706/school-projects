let letra = 'A'
let x = 10
let y = 200
let posX = 800
let posY = 300
let avance = -3
let puntos = 0
let jugando = 0 // 0 = jugando

function setup() {
  createCanvas(800,800)
  textSize(20)
  letra = char(random(65, 91)) // genera una letra en minúscula
}

function draw() {
  background(255)
  text("X: " + mouseX, 10,40)
  text("Y: " + mouseY, 10,60)
  text("posX: " + posX, 10,80)
  text("posY: " + posY, 10,100)  
  text("Puntos: " + puntos, 10, 120)
  rect(x,y,30,30)

  text(letra, posX, posY)
  posX = posX + avance


  if (keyIsPressed) {
    //text("Presionaste una tecla", 10,200)
    if (keyCode === RIGHT_ARROW) {
      x = x + 2 // x += 2  
    } else if (keyCode === LEFT_ARROW) {
      x = x - 2
    } else if (keyCode === UP_ARROW) {
      y = y - 2
    } else if (keyCode === DOWN_ARROW) {
      y = y + 2
    } else if (key.toUpperCase() === letra) {
      if (jugando === 0) {
        avance = 0
        puntos = puntos + posX 
        jugando = 1 //Ya no estamos jugando
      }
      
    }
  }
}

function keyPressed() {
  return false // previene comportamiento por defecto
}