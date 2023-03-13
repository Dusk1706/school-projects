let r
let g
let b

function setup() {
  createCanvas(800,800)
  background(255)
  noCursor()
}

function draw() {
  r = random(0,256)
  g = random(0,256)
  b = random(0,256)
  //noStroke()
  //fill(r,g,b)
  fill(random(150,220))
  circle(mouseX, mouseY, 100)  
}