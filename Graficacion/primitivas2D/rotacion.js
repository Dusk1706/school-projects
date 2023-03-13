
let grados = Math.PI / 180 //P5.JS tiene la variable PI
let x = 0

function setup() {
    createCanvas(800,800)
}

function draw() {
    background(200)
    translate(x,200)
    rotate(grados)
    rect(0,0,60,60)
    grados += 1
    x += 1
    

}