function setup() {
    createCanvas(800,800)
    background(255)
    //rectMode(CENTER)
    noLoop()
  }
  
  function draw() {
    bobesponja()
  }

  function bobesponja(){
    //Cuerpo
    strokeWeight(5)
    fill('Yellow')
    rect(150,200,200,200)
    rect(200,200,200,200)
    //Hoyuelos
    fill(194,255,0)
    circle(175,225,40)
    circle(175,275,40)
    circle(175,325,40)
    //Pantalon
    fill(153,102,51)
    rect(150,375,250,25)
    line(200,400,200,375)
    //Camisa blanca
    fill(255,255,255)
    rect(200,350,200,25)
    rect(150,350,50,25)
    //Ojos
    circle(250,240,45)
    circle(340,240,45)
    //Ojos por dentro izquierdo
    noStroke()
    fill(93,157,229)
    circle(250,240,20)
    fill(0,0,0)
    circle(250,240,10)
    //Ojos por dentro derecho
    fill(93,157,229)
    circle(340,240,20)
    fill(0,0,0)
    circle(340,240,10)
    //Nariz
    stroke('Black')
    fill('Yellow')
    ellipse(320,275,70,15)
    //Boca
    fill('Red')
    ellipse(300,315,70,50)
    strokeWeight(3)
    fill(255,255,255)
    rect(289,291,10,10)
    rect(304,291,10,10)
    strokeWeight(5)
    //Cinturon
    rect(165,385,20,5)
    rect(210,385,20,5)
    rect(250,385,20,5)
    rect(330,385,20,5)
    rect(370,385,20,5)
    fill('Red')
    ellipse(300,375,20,50)
    //Pestañas
    rect(235,210,3,10)
    rect(245,207,3,10)
    rect(258,208,3,10)
    rect(325,210,3,10)
    rect(335,207,3,10)
    rect(348,208,3,10);
    //Cosa que querias disminuye strokeWeight si lo haras muy chico
    fill(255,0,0);
    rect(500,208,100,10);
    
    

  }