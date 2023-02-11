import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class draw extends Applet implements ActionListener, MouseWheelListener {

  int originX, originY;
  int height, width;
  int gap = 20;
  int temp = 0;
  static final int INSIDE = 0; // 0000
  static final int LEFT = 1; // 0001
  static final int RIGHT = 2; // 0010
  static final int BOTTOM = 4; // 0100
  static final int TOP = 8; // 1000

  static final int x_max = 100;
  static final int y_max = 100;
  static final int x_min = -100;
  static final int y_min = -100;

  Button b1 = new Button(" + ");
  Button b2 = new Button(" - ");
  Button b3 = new Button(" Draw ");

  public void init() {
    setBackground(Color.BLACK);
    b1.setBackground(new Color(31, 70, 144));
    b2.setBackground(new Color(255, 229, 180));
    b3.setBackground(new Color(255, 229, 180));
    add(b1);
    add(b2);
    add(b3);
    addMouseWheelListener(this);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
  }

  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    height = getHeight();
    width = getWidth();
    originX = (getX() + width) / 2;
    originY = (getY() + height) / 2;

    int k = -4;
    int[] x1 = { -15*k ,  -30*k, -45*k, -60*k};
    int[] y1 = {  0*k , -50*k, -50*k,   0*k};
    int[] x2 = { 15*k ,  1*k, -4*k, -9*k};
    int[] y2 = {  10*k , -10*k, -10*k,   0*k};
    int[] x3 = {-9*k , -11*k, -13*k,-15*k};
    int[] y3 = {  0*k ,  5*k,  5*k,   0*k};
    // // plotPoints(x,y);
    drawGrid(g);
    if(temp == 1){
        bezierCurve(x1, y1);
        bezierCurve(x2, y2);
        bezierCurve(x3, y3);
    }
    //drawCurve(g, ptsX, ptsY);

  }

  //Function to draw origin
  public void drawOriginCircle(Graphics g) {
    g.setColor(Color.RED);
    g.fillOval(originX - 5, originY - 5, 10, 10);
  }

  //Function for plotting points
  public void plotPoint(Graphics g, int x, int y, Color c) {
    g.setColor(c);
    g.fillRect(
      originX + (x * gap) - gap * 2,
      originY - (y * gap) - gap * 2,
      gap * 4,
      gap * 4
    );
  }

  public void plotPoint2(Graphics g, int x, int y, Color c) {
    g.setColor(c);
    g.fillRect(
      originX + (x * gap) - gap * 2,
      originY - (y * gap) - gap * 2,
      gap * 4,
      gap * 4
    );
  }

  //Function to draw X-axis
  public void drawXaxis(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect(0, originY - 2, width, 4);
  }

  //Function to draw Y-axis
  public void drawYaxis(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect(originX - 2, 0, 4, height);
  }

  // Function to draw the Grid
  public void drawGrid(Graphics g) {
    drawHorizontalLines(g);
    drawVeritcalLines(g);
    drawXaxis(g);
    drawYaxis(g);
  }

  //Function to draw the horizontal lines of the grid
  public void drawHorizontalLines(Graphics g) {
    g.setColor(Color.blue);
    for (int i = originX; i <= width; i += gap) {
      g.drawLine(i, 0, i, height);
    }
    for (int i = originX; i >= 0; i -= gap) {
      g.drawLine(i, 0, i, height);
    }
  }

  //Function to draw the vertical lines of the grid
  public void drawVeritcalLines(Graphics g) {
    g.setColor(Color.blue);
    for (int i = originY; i <= height; i += gap) {
      g.drawLine(0, i, width, i);
      // add coordinate text
    }
    for (int i = originY; i >= 0; i -= gap) {
      g.drawLine(0, i, width, i);
    }
  }

  //Function for the buttons
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == b1) zoom(10);
    if (e.getSource() == b2) zoom(-10);
    if (e.getSource() == b3) {
      if (temp == 0) temp = 1; else temp = 0;
      repaint();
    }
  }

  //Function for the mousewheel
  public void mouseWheelMoved(MouseWheelEvent e) {
    int z = e.getWheelRotation();
    zoom(z);
  }

  //Function for the zoom in feature
  public void zoom(int i) {
    if (gap + i >= 1 && gap + i <= 300) {
      gap += i;
      repaint();
    }
  }

  public long ncr(int n, int r) {
    long result = 1;

    for (int i = 1; i <= r; i++) {
      result *= n - r + i;
      result /= i;
    }

    return result;
  }

  public void bezierCurve(int[] x, int[] y) {
    double xu = 0.0, yu = 0.0, u = 0.0;

    for (u = 0.0; u <= 1.0; u += 0.0001) {
      xu =
        Math.pow(1 - u, 3) *
        x[0] +
        3 *
        u *
        Math.pow(1 - u, 2) *
        x[1] +
        3 *
        Math.pow(u, 2) *
        (1 - u) *
        x[2] +
        Math.pow(u, 3) *
        x[3];
      yu =
        Math.pow(1 - u, 3) *
        y[0] +
        3 *
        u *
        Math.pow(1 - u, 2) *
        y[1] +
        3 *
        Math.pow(u, 2) *
        (1 - u) *
        y[2] +
        Math.pow(u, 3) *
        y[3];
      plotPoint(getGraphics(), (int) xu, (int) yu, Color.orange);
    }
  }

  //bspline
  public void drawBSplineCurve(
    ArrayList<Integer> polyx,
    ArrayList<Integer> polyy
  ) {
    int n, d;
    d = 3;
    n = polyx.size();
    ArrayList<Double> uVec = new ArrayList<>();
    int i;
    for (i = 0; i < n + d; i++) {
      uVec.add(((double) i / (n + d - 1)));
    }
    double x, y, basis, u;
    for (u = 0; u <= 1; u += 0.0001) {
      x = 0;
      y = 0;
      for (i = 0; i < polyx.size(); i++) {
        basis = blend(uVec, u, i, d);
        x += basis * polyx.get(i);
        y += basis * polyy.get(i);
      }
      // putpixel(Math.round(x), Math.round(y), YELLOW);
      plotPoint(getGraphics(), (int) x, (int) y, Color.YELLOW);
    }
  }

  public double blend(ArrayList<Double> uVec, double u, int k, int d) {
    if (d == 1) {
      if (uVec.get(k) <= u && u < uVec.get(k + 1)) return 1;
      return 0;
    }
    double b;
    b =
      (
        (u - uVec.get(k)) /
        (uVec.get(k + d - 1) - uVec.get(k)) *
        blend(uVec, u, k, d - 1)
      ) +
      (
        (uVec.get(k + d) - u) /
        (uVec.get(k + d) - uVec.get(k + 1)) *
        blend(uVec, u, k + 1, d - 1)
      );
    return b;
  }
}