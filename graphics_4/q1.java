import java.applet.*;
import java.awt.*;
import java.awt.event.*;

// import java.util.*;

public class q1 extends Applet implements ActionListener, MouseWheelListener {

  int gap = 20;
  int temp = 0;
  int temp1 = 0;
  int temp2 = 0;
  int temp3 = 0;
  int spotonLegs = 0;
  Button plusbutton = new Button("+");
  Button minusbutton = new Button("-");
  Button button = new Button("Teeth");
  Button button3 = new Button("Ear");
  Button button4 = new Button("Circles");
  Button button5 = new Button("Tail");
  Button button6 = new Button("SpotonLegs");

  //It is for initialisation purpose
  public void init() {
    add(plusbutton);
    add(minusbutton);
    add(button);
    add(button3);
    add(button4);
    add(button5);
    add(button6);
    plusbutton.setBackground(Color.green);
    minusbutton.setBackground(Color.red);
    plusbutton.setForeground(Color.yellow);
    minusbutton.setForeground(Color.blue);
    button.setBackground(Color.yellow);
    button3.setBackground(Color.blue);
    button4.setBackground(Color.yellow);
    button5.setBackground(Color.blue);

    plusbutton.addActionListener(this);
    minusbutton.addActionListener(this);
    button.addActionListener(this);
    button3.addActionListener(this);
    button4.addActionListener(this);
    button5.addActionListener(this);
    button6.addActionListener(this);
    addMouseWheelListener(this);
    setForeground(Color.green);
    setBackground(Color.black);
  }

  //it is for implementing button function
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == plusbutton) {
      gap += 2;
      repaint();
    }
    if (e.getSource() == minusbutton && gap > 2) {
      gap -= 2;
      repaint();
    }
    if (e.getSource() == button) {
      if (temp == 0) temp = 1; else temp = 0;

      repaint();
    }
    if (e.getSource() == button3) {
      if (temp1 == 0) temp1 = 1; else temp1 = 0;
      repaint();
    }
    if (e.getSource() == button4) {
      if (temp2 == 0) temp2 = 1; else temp2 = 0;
      repaint();
    }
    if (e.getSource() == button5) {
      if (temp3 == 0) temp3 = 1; else if (temp3 == 1) temp3 = 2; else temp3 = 0;
      repaint();
    }
    if (e.getSource() == button6) {
      if (spotonLegs == 0) spotonLegs = 1; else if (
        spotonLegs == 1
      ) spotonLegs = 0;
      repaint();
    }
  }

  public void plotPoint(Graphics g, int x, int y, Color c) {
    g.setColor(c);
    g.fillRect(
      (getX() + getWidth()) / 2 + (x * gap) - (gap / 2),
      (getY() + getHeight()) / 2 - (y * gap) - (gap / 2),
      gap,
      gap
    );
  }

  public int slope(int x1, int x2, int y1, int y2) {
    int x = x2 - x1;
    int y = y2 - y1;
    int m = y / x;
    return m;
  }

  //Round function
  public int round(float n) {
    if (n - (int) n < 0.5) return (int) n;
    return (int) (n + 1);
  }

  //Circle Drawing Algorithm
  public void Circles(Graphics g, int radius, int x1, int y1) {
    int x = 0;
    int y = radius;
    double p = 1.25 - radius;
    plotPoint(g, x + x1, y + y1, Color.green);
    plotPoint(g, x + x1, -y + y1, Color.green);
    plotPoint(g, -x + x1, y + y1, Color.green);
    plotPoint(g, -x + x1, -y + y1, Color.green);
    while (x < y) {
      if (p < 0) {
        x = x + 1;
        p = p + 2 * x + 1;
      } else {
        x = x + 1;
        y = y - 1;
        p = p + 2 * x + 1 - 2 * y;
      }
      plotPoint(g, x + x1, y + y1, Color.green);
      plotPoint(g, y + x1, x + y1, Color.green);
      plotPoint(g, x + x1, -y + y1, Color.green);
      plotPoint(g, -x + x1, y + y1, Color.green);
      plotPoint(g, y + x1, -x + y1, Color.green);
      plotPoint(g, -y + x1, x + y1, Color.green);
      plotPoint(g, -x + x1, -y + y1, Color.green);
      plotPoint(g, -y + x1, -x + y1, Color.green);
    }
  }

  //Line Drawing Algorithm
  //function to return line
  public void DDALine(Graphics g, int x0, int y0, int x1, int y1) {
    //calculate dx and dy
    int dx = x1 - x0;
    int dy = y1 - y0;

    int step;

    //if dx > dy we will take step as dx
    //else we will take step as dy to draw the complete line
    if (Math.abs(dx) > Math.abs(dy)) step = Math.abs(dx); else step =
      Math.abs(dy);

    //calculate x-increment and y-increment for each step
    float x_incr = (float) dx / step;
    float y_incr = (float) dy / step;

    //take the initial points as x and y
    float x = x0;
    float y = y0;

    for (int i = 0; i < step; i++) {
      //putpixel(round(x), round(y), WHITE);
      plotPoint(g, round(x), round(y), Color.green);
      x += x_incr;
      y += y_incr;
      //delay(10);
    }
  }

  //Ellipse drawing algorithm
  public void midptellipse(
    Graphics g,
    float rx,
    float ry,
    float xc,
    float yc,
    Double degree
  ) {
    float dx, dy, d1, d2, x, y;
    x = 0;
    y = ry;
    double radian = Math.toRadians(degree);
    // Initial decision parameter of region 1
    d1 = (ry * ry) - (rx * rx * ry) + (0.25f * rx * rx);
    dx = 2 * ry * ry * x;
    dy = 2 * rx * rx * y;
    // DecimalFormat df = new DecimalFormat("#,###,##0.00000");

    // For region 1
    while (dx < dy) {
      plotPoint(
        g,
        ((int) ((x) * Math.cos(radian) + xc - (y) * Math.sin(radian))),
        ((int) ((x) * Math.sin(radian) + yc + (y) * Math.cos(radian))),
        Color.green
      );
      plotPoint(
        g,
        ((int) ((-x) * Math.cos(radian) + xc - (y) * Math.sin(radian))),
        ((int) ((-x) * Math.sin(radian) + yc + (y) * Math.cos(radian))),
        Color.green
      );
      plotPoint(
        g,
        ((int) ((x) * Math.cos(radian) + xc - (-y) * Math.sin(radian))),
        ((int) ((x) * Math.sin(radian) + yc + (-y) * Math.cos(radian))),
        Color.green
      );
      plotPoint(
        g,
        ((int) ((-x) * Math.cos(radian) + xc - (-y) * Math.sin(radian))),
        ((int) ((-x) * Math.sin(radian) + yc + (-y) * Math.cos(radian))),
        Color.green
      );
      //  plotPoint(g,((int)((-x)*Math.cos(radian)+xc-(y)*Math.sin(radian))),((int)((x)*Math.sin(radian)+yc+(y)*Math.cos(radian))),Color.red);
      //  plotPoint(g,((int)((x)*Math.cos(radian)+xc-(-y)*Math.sin(radian))),((int)((x)*Math.sin(radian)+yc+(y)*Math.cos(radian))),Color.red);
      //  plotPoint(g,((int)((-x)*Math.cos(radian)+xc-(-y)*Math.sin(radian))),((int)((x)*Math.sin(radian)+yc+(y)*Math.cos(radian))),Color.red);

      if (d1 < 0) {
        x++;
        dx = dx + (2 * ry * ry);
        d1 = d1 + dx + (ry * ry);
      } else {
        x++;
        y--;
        dx = dx + (2 * ry * ry);
        dy = dy - (2 * rx * rx);
        d1 = d1 + dx - dy + (ry * ry);
      }
    }

    // Decision parameter of region 2
    d2 =
      ((ry * ry) * ((x + 0.5f) * (x + 0.5f))) +
      ((rx * rx) * ((y - 1) * (y - 1))) -
      (rx * rx * ry * ry);

    // Plotting points of region 2
    while (y >= 0) {
      // printing points based on 4-way symmetry
      // plotPoint(g,(int)(x+xc),(int)(y+yc),Color.red);
      // plotPoint(g,(int)(-x+xc),(int)(y+yc),Color.red);
      // plotPoint(g,(int)(x+xc),(int)(-y+yc),Color.red);
      // plotPoint(g,(int)(-x+xc),(int)(-y+yc),Color.red);

      plotPoint(
        g,
        ((int) ((x) * Math.cos(radian) - (y) * Math.sin(radian) + xc)),
        ((int) ((x) * Math.sin(radian) + yc + (y) * Math.cos(radian))),
        Color.green
      );
      plotPoint(
        g,
        ((int) ((-x) * Math.cos(radian) - (y) * Math.sin(radian) + xc)),
        ((int) ((-x) * Math.sin(radian) + yc + (y) * Math.cos(radian))),
        Color.green
      );
      plotPoint(
        g,
        ((int) ((x) * Math.cos(radian) - (-y) * Math.sin(radian) + xc)),
        ((int) ((x) * Math.sin(radian) + yc + (-y) * Math.cos(radian))),
        Color.green
      );
      plotPoint(
        g,
        ((int) ((-x) * Math.cos(radian) - (-y) * Math.sin(radian) + xc)),
        ((int) ((-x) * Math.sin(radian) + yc + (-y) * Math.cos(radian))),
        Color.green
      );

      //  plotPoint(g,((int)((-x)*Math.cos(radian)-(y)*Math.sin(radian)+xc)),((int)((x)*Math.sin(radian)+yc+(y)*Math.cos(radian))),Color.red);
      //  plotPoint(g,((int)((x)*Math.cos(radian)-(-y)*Math.sin(radian)+xc)),((int)((x)*Math.sin(radian)+yc+(y)*Math.cos(radian))),Color.red);
      //  plotPoint(g,((int)((-x)*Math.cos(radian)-(-y)*Math.sin(radian)+xc)),((int)((x)*Math.sin(radian)+yc+(y)*Math.cos(radian))),Color.red);

      if (d2 > 0) {
        y--;
        dy = dy - (2 * rx * rx);
        d2 = d2 + (rx * rx) - dy;
      } else {
        y--;
        x++;
        dx = dx + (2 * ry * ry);
        dy = dy - (2 * rx * rx);
        d2 = d2 + dx - dy + (rx * rx);
      }
    }
  }

  public void paintGrid(Graphics g, int gap, int originx, int originy) {
    g.setColor(Color.yellow);

    for (int i = gap; i <= getWidth(); i += gap) {
      g.drawLine(
        originx + i,
        originy - getHeight() / 2,
        originx + i,
        originy + getHeight() / 2
      );
      g.drawLine(
        originx - i,
        originy - getHeight() / 2,
        originx - i,
        originy + getHeight() / 2
      );
    }
    for (int i = gap; i <= getHeight(); i += gap) {
      g.drawLine(
        originx - getWidth() / 2,
        originy + i,
        originx + getWidth() / 2,
        originy + i
      );
      g.drawLine(
        originx - getWidth() / 2,
        originy - i,
        originx + getWidth() / 2,
        originy - i
      );
    }
  }

  //It is for mouse wheel operation
  public void mouseWheelMoved(MouseWheelEvent e) {
    int z = e.getWheelRotation();
    gap += z;
    repaint();
  }

  //function for making spotted cirecles
  public void makespot(Graphics g) {
    if (temp2 == 0) {
      Circles(g, 4, 44, 50);
      Circles(g, 4, 24, 50);
      Circles(g, 4, 4, 50);
      Circles(g, 4, -14, 50);
      Circles(g, 4, -34, 50);
      Circles(g, 4, -54, 50);
      Circles(g, 4, -74, 50);

      Circles(g, 4, 44, 80);
      Circles(g, 4, 24, 80);
      Circles(g, 4, 4, 80);
      Circles(g, 4, -14, 80);
      Circles(g, 4, -34, 80);
      Circles(g, 4, -54, 80);
      Circles(g, 4, -74, 80);
      Circles(g, 4, -94, 80);

      Circles(g, 4, 44, 110);
      Circles(g, 4, 24, 110);
      Circles(g, 4, 4, 110);
      Circles(g, 4, -14, 110);
      Circles(g, 4, -34, 110);
      Circles(g, 4, -54, 110);
      Circles(g, 4, -74, 110);
      Circles(g, 4, -94, 110);

      Circles(g, 4, 24, 140);
      Circles(g, 4, 4, 140);
      Circles(g, 4, -14, 140);
      Circles(g, 4, -34, 140);
      Circles(g, 4, -54, 140);
      Circles(g, 4, -74, 140);
      Circles(g, 4, -94, 140);

      Circles(g, 4, -14, 20);
      Circles(g, 4, -34, 20);
      Circles(g, 4, -54, 20);

      Circles(g, 4, -34, 170);
      Circles(g, 4, -14, 170);
      Circles(g, 4, -54, 170);
    }
  }

  public void makeSpotOnLegs(Graphics g) {
    if (spotonLegs == 0) return;
    //front upper
    Circles(g, 4, -50, -70);
    Circles(g, 4, -30, -70);
    Circles(g, 4, -15, -49);
    Circles(g, 4, -30, -31);
    Circles(g, 4, -35, -53);
    Circles(g, 4, -5, -10);
    Circles(g, 4, -8, -29);
    //front lower
    Circles(g, 4, -85, -130);
    Circles(g, 4, -70, -131);
    Circles(g, 4, -75, -153);
    Circles(g, 4, -73, -110);
    Circles(g, 4, -55, -129);
    // //back upper
    Circles(g, 4, 70, -70);
    Circles(g, 4, 50, -70);
    Circles(g, 4, 35, -49);
    Circles(g, 4, 50, -31);
    Circles(g, 4, 55, -53);
    Circles(g, 4, 25, -10);
    Circles(g, 4, 28, -29);
    // //back lower
    Circles(g, 4, 85, -130);
    Circles(g, 4, 70, -131);
    Circles(g, 4, 75, -153);
    Circles(g, 4, 73, -110);
  }

  //function to make hairy tail
  public void makehairytail(Graphics g) {
    if (temp3 == 2) {
      DDALine(g, 50, 35, 120, 90);
      DDALine(g, 50, 35, 120, 60);
      DDALine(g, 50, 35, 130, 70);
      DDALine(g, 50, 35, 120, 50);
      DDALine(g, 50, 35, 130, 40);
      DDALine(g, 50, 35, 120, 30);
      DDALine(g, 50, 35, 130, 20);
    }
  }

  //function to make ellipse tail
  public void maketailellipse(Graphics g) {
    if (temp3 == 0) midptellipse(
      g,
      (float) 45,
      (float) 10,
      (float) 90,
      (float) 50,
      (double) 20
    );
  }

  //function for making legspot
  public void maketailtri(Graphics g) {
    if (temp3 == 1) {
      DDALine(g, 50, 35, 120, 70);
      DDALine(g, 50, 35, 130, 40);
      DDALine(g, 120, 70, 130, 40);
    }
  }

  //function for making triangle
  public void maketriangle(Graphics g) {
    //    DDALine(g,-10,0,10,0);
    //     DDALine(g,-10,0,0,20);
    // DDALine(g,10,0,0,20);
    // DDALine(g,-40,220,-60,270);
    // DDALine(g,-40,260,-50,230);
    if (temp1 == 0) {
      DDALine(g, -70, 240, -60, 270);
      DDALine(g, -70, 240, -38, 235);
      DDALine(g, -60, 270, -38, 235);
    }
  }

  //function for making circle of ear
  public void makeear(Graphics g) {
    if (temp1 == 1) Circles(g, 20, -51, 249);
  }

  //function for teeth making
  public void maketeeth(Graphics g) {
    if (temp == 1) {
      DDALine(g, -200, 190, -200, 197);
      DDALine(g, -180, 190, -180, 197);
      DDALine(g, -160, 190, -160, 197);
      DDALine(g, -150, 190, -150, 197);
      DDALine(g, -160, 190, -160, 197);
      DDALine(g, -170, 190, -170, 197);
      DDALine(g, -180, 190, -180, 197);
      DDALine(g, -190, 190, -190, 197);

      DDALine(g, -201, 190, -201, 197);
      DDALine(g, -181, 190, -181, 197);
      DDALine(g, -161, 190, -161, 197);
      DDALine(g, -151, 190, -151, 197);
      DDALine(g, -161, 190, -161, 197);
      DDALine(g, -171, 190, -171, 197);
      DDALine(g, -181, 190, -181, 197);
      DDALine(g, -191, 190, -191, 197);

      DDALine(g, -200, 210, -200, 203);
      DDALine(g, -180, 210, -180, 203);
      DDALine(g, -160, 210, -160, 203);
      DDALine(g, -150, 210, -150, 203);
      DDALine(g, -160, 210, -160, 203);
      DDALine(g, -170, 210, -170, 203);
      DDALine(g, -180, 210, -180, 203);
      DDALine(g, -190, 210, -190, 203);

      DDALine(g, -201, 210, -201, 203);
      DDALine(g, -181, 210, -181, 203);
      DDALine(g, -161, 210, -161, 203);
      DDALine(g, -151, 210, -151, 203);
      DDALine(g, -161, 210, -160, 203);
      DDALine(g, -171, 210, -171, 203);
      DDALine(g, -181, 210, -181, 203);
      DDALine(g, -191, 210, -191, 203);
    }
  }

  public void animalBody(Graphics g) {
    Circles(g, 50, -100, 200);
    Circles(g, 10, -100, 200);

    DDALine(g, -140, 210, -200, 210);
    DDALine(g, -140, 210, -140, 230);
    DDALine(g, -140, 230, -200, 210);
    DDALine(g, -140, 190, -140, 170);
    DDALine(g, -140, 190, -200, 190);
    DDALine(g, -140, 170, -200, 190);
    midptellipse(
      g,
      (float) 100,
      (float) 80,
      (float) -30,
      (float) 90,
      (double) 135
    );
    midptellipse(
      g,
      (float) 70,
      (float) 20,
      (float) -30,
      (float) -50,
      (double) 65
    );
    midptellipse(
      g,
      (float) 79,
      (float) 25,
      (float) 55,
      (float) -40,
      (double) 125
    );
    midptellipse(
      g,
      (float) 50,
      (float) 20,
      (float) -70,
      (float) -130,
      (double) 55
    );
    midptellipse(
      g,
      (float) 20,
      (float) 50,
      (float) 80,
      (float) -130,
      (double) 0
    );
    midptellipse(
      g,
      (float) 20,
      (float) 10,
      (float) 60,
      (float) -180,
      (double) 0
    );
    midptellipse(
      g,
      (float) 30,
      (float) 10,
      (float) -100,
      (float) -170,
      (double) 0
    );
    midptellipse(
      g,
      (float) 40,
      (float) 10,
      (float) -140,
      (float) 80,
      (double) 40
    );
    midptellipse(
      g,
      (float) 40,
      (float) 10,
      (float) -200,
      (float) 60,
      (double) 170
    );
    midptellipse(
      g,
      (float) 40,
      (float) 10,
      (float) -160,
      (float) 100,
      (double) 10
    );
    midptellipse(
      g,
      (float) 40,
      (float) 10,
      (float) -220,
      (float) 100,
      (double) 165
    );
    Circles(g, 12, -260, 110);
    Circles(g, 12, -237, 70);
  }

  public void paint(Graphics g) {
    g.setColor(Color.blue);
    int originx = getX() + getWidth() / 2;
    int originy = getY() + getHeight() / 2;
    g.drawLine(
      originx - getWidth() / 2,
      originy,
      originx + getWidth() / 2,
      originy
    );
    g.drawLine(
      originx,
      originy - getHeight() / 2,
      originx,
      originy + getHeight() / 2
    );
    // paintGrid(g,gap,originx,originy);
    Color c = new Color(100, 100, 100);
    int i = 0;
    int x1 = 200, y1 = 101;
    //animalbody
    animalBody(g);
    //function for making teeth
    maketeeth(g);
    maketriangle(g);
    makeear(g);
    //   midptellipse(g,(float)45,(float)10,(float)90,(float)50,(double)20);
    makespot(g);
    maketailtri(g);
    maketailellipse(g);
    makeSpotOnLegs(g);
    //Traingle making

    // midptellipse(g,(float)3,(float)6,(float)1,(float)2);
    // paintGrid(g,gap,originx,originy);

    makehairytail(g);
  }
}
