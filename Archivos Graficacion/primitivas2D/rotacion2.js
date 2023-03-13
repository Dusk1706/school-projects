//Recuerde cambiar en index.html
//el nombre del sketch correspondiente
//<script src="rotacion2.js"></script>

let grados = Math.PI / 180 //P5.JS tiene la variable PI
let x = 0
let rot = grados

function setup() {
    createCanvas(800,800)
    frameRate(1)

}

function draw() {
    background(200)
    translate(400,400)
    fill(0,255,0)
    rect(0,0,60,60)
    fill(255,0,0)
    rotate(grados*315)
    rect(0,0,60,60)
    fill(200)
    rotate(grados*90)
    rect(0,0,60,60)
    fill(0,0,255)
    rotate(grados*135)
    rect(0,0,60,60)
    fill(0,255,255)
    rotate(grados*180)
    rect(0,0,60,60)

    translate(0,200)
    rotate(rot)
    rot = rot + 6 * grados
    line(0,0,60,60)
    translate(0,-200)

}