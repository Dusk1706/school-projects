
function setup() {
  createCanvas(800,800);
  noLoop();
}

function draw() {
  background(255);
  //Cuerpo
  fill(14,53,210);
  stroke(0);
  strokeWeight(18)
  line(300,650,300,350);
  line(600,650,600,350);
  stroke(14,53,210);
  rect(300, 350, 283, 295);
  fill(255);
  stroke(255);
  rect(425, 600, 55, 50);
  fill(14,53,210);
  stroke(0);
  arc(450,350,300,350,PI,TWO_PI);
  //Mochila
  arc(300, 450, 130, 250, HALF_PI, PI+HALF_PI);
  line(300,650,300,340);
  //Pies
  line(410,650,410,600);
  line(490,650,490,600);
  arc(355,650,110,150,TWO_PI,PI);//Pie izquierdo
  arc(545,650,110,150,TWO_PI,PI);//Pie derecho
  arc(461,595,102,9,TWO_PI,PI);//Linea en medio
  //Mascara
  fill(123,201,219);
  stroke(0);
  ellipse(510,340,240,170);
  //Corona
  fill(255);
  line(325,250,260,165);
  line(480,175,470,70);
  arc(305,160,90,30,TWO_PI,PI);
  arc(370,155,40,130,PI,PI+HALF_PI);
  arc(415,59,110,110,TWO_PI,HALF_PI+QUARTER_PI);
  saveCanvas();
}