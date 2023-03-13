//Recuerde cambiar en index.html
//el nombre del sketch correspondiente
//<script src="escalamiento.js"></script>
let factorE = 1.01 //Factor de escalamiento
let x1 = 400
let y1 = 350
let x2 = 350
let y2 = 450
let x3 = 450
let y3 = 450
let radio = 10

function setup() {
    createCanvas(800,800)
}

function draw() {

    //background(220)
    //fill(random(256),random(256),random(256))
    //translate mueve el origen del sistema de coordenadas
    //a la posición indicada
    translate(width/2, height/2) //mueve el origen al centro de la pantalla
    //fill(255)
    //triangle(0,-50, -50,0, 50, 0)
    //fill(234)
    //triangle(0,-50 * -1, -50,0 * -1, 50, 0 * -1)
    //fill(255,0,0)
    //triangle(0*-1+50,-50, -50*-1+50,0, 50*-1+50, 0)

    circle(0,0,radio)
    radio = radio * factorE
    if (radio > height) {
        factorE = 2 - factorE //
    }

    if (radio < 10) {
        factorE = 2 - factorE //
    }



    triangle(x1,y1,x2,y2,x3,y3)
    x1 *= factorE // x1 = x1 * factorE
    y1 *= factorE
    x2 *= factorE
    y2 *= factorE
    x3 *= factorE
    y3 *= factorE
}