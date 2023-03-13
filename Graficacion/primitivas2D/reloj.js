let x
let y


function setup() {
  createCanvas(800,800)
  textFont("Arial")
  x = width
  y = height / 2
  textSize(96)
}

function draw() {
  background(146,83,116)
  fill(252,238,161)
  noStroke()
  let h = nf(hour(),2)
  let m = nf(minute(),2)
  let s = nf(second(),2)
  translate(400,400)
  textAlign(CENTER, CENTER)
  rotate(PI/6 + frameCount*0.02)
  text(h + ":"+m+":"+s, 100,100)

}