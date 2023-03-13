
let posX = 0
let posY = 0
let despX = 0
let despY = 10
let diametro = 100

function setup() {
  createCanvas(800,800)
}

function draw() {
  //background(220)
  fill(random(256),random(256),random(256))
  circle(posX,posY,diametro)
  posX += despX
  posY += despY
  if (posY > (height + (diametro / 2))) {
    posY = 0
    posX = posX + (diametro / 2)
  }

  if (posX > (width + (diametro / 2))) {
    posX = 0
    posX = 0
  }


}