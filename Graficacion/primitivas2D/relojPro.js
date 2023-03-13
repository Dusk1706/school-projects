function setup() {
    createCanvas(800,800);
  }

  function draw(){
    background(128)

    fill(255,255,0)
    circle(400,400,425)
    fill(255)
    circle(400,400,350)
    fill(255,0,0)
    textSize(32)
    textAlign(CENTER,CENTER)
    text('12', 400, 250);
    text('6', 400, 550);
    text('3', 550, 400);
    text('9',250, 400);
    line(400,400,450,400)
    line(400,400,400,300)

    text('Tiempo: '+frameCount/1000,200,200)
  }