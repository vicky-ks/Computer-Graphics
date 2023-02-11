import java.io.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class clipping extends Applet implements ActionListener, MouseWheelListener{
    int scale = 2;
	// Defining region codes
	static final int INSIDE = 0; // 0000
	static final int LEFT = 1; // 0001
	static final int RIGHT = 2; // 0010
	static final int BOTTOM = 4; // 0100
	static final int TOP = 8; // 1000
    boolean clipped = false;

	static final int x_max = 100;
	static final int y_max = 50;
	static final int x_min = -100;
	static final int y_min = -50;

    public void init()
	{
        setBackground(Color.black);
        Button b1 = new Button("Zoom In");   
        Button b2 = new Button("Zoom Out");
        Button b3 = new Button("Clip");
        add(b1);
        add(b2);
        add(b3);
        b1.setBackground(Color.blue);
        b2.setBackground(Color.red);
        b3.setBackground(Color.white);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
	}
	// Function to compute region code for a point(x, y)
	public int computeCode(double x, double y)
	{
		// initialized as being inside
		int code = INSIDE;

		if (x < x_min) // to the left of rectangle
			code |= LEFT;
		else if (x > x_max) // to the right of rectangle
			code |= RIGHT;
		if (y < y_min) // below the rectangle
			code |= BOTTOM;
		else if (y > y_max) // above the rectangle
			code |= TOP;

		return code;
	}

	// Implementing Cohen-Sutherland algorithm
	// Clipping a line from P1 = (x2, y2) to P2 = (x2, y2)
	public void cohenSutherlandClip(double x1, double y1,
									double x2, double y2,Graphics g)
	{
		int code1 = computeCode(x1, y1);
		int code2 = computeCode(x2, y2);

		boolean accept = false;

		while (true) {
			if ((code1 == 0) && (code2 == 0)) {
				accept = true;
				break;
			}
			else if ((code1 & code2) != 0) {
				// If both endpoints are outside rectangle,
				// in same region
				break;
			}
			else {
				// Some segment of line lies within the
				// rectangle
				int code_out;
				double x = 0, y = 0;

				// At least one endpoint is outside the
				// rectangle, pick it.
				if (code1 != 0)
					code_out = code1;
				else
					code_out = code2;

				// Find intersection point;
				// using formulas y = y1 + slope * (x - x1),
				// x = x1 + (1 / slope) * (y - y1)
				if ((code_out & TOP) != 0) {
					// point is above the clip rectangle
					x = x1
						+ (x2 - x1) * (y_max - y1)
							/ (y2 - y1);
					y = y_max;
				}
				else if ((code_out & BOTTOM) != 0) {
					// point is below the rectangle
					x = x1
						+ (x2 - x1) * (y_min - y1)
							/ (y2 - y1);
					y = y_min;
				}
				else if ((code_out & RIGHT) != 0) {
					// point is to the right of rectangle
					y = y1
						+ (y2 - y1) * (x_max - x1)
							/ (x2 - x1);
					x = x_max;
				}
				else if ((code_out & LEFT) != 0) {
					// point is to the left of rectangle
					y = y1
						+ (y2 - y1) * (x_min - x1)
							/ (x2 - x1);
					x = x_min;
				}

				// Now intersection point x, y is found
				// We replace point outside rectangle
				// by intersection point
				if (code_out == code1) {
					x1 = x;
					y1 = y;
					code1 = computeCode(x1, y1);
				}
				else {
					x2 = x;
					y2 = y;
					code2 = computeCode(x2, y2);
				}
			}
		}
		if (accept) {

			// System.out.println("Line accepted from " + x1
			// 				+ ", " + y1 + " to " + x2
			// 				+ ", " + y2);
            DDALine(g,(int)x1,(int)y1,(int)x2,(int)y2,Color.red);
		}
		// else
			// System.out.println("Line rejected");
	}
    public void DDALine(Graphics g,int x0, int y0, int x1, int y1, Color c) {
     
      //calculate dx and dy
        int dx = x1 - x0;
        int dy= y1 - y0;
        
        int step;
        
        //if dx > dy we will take step as dx
        //else we will take step as dy to draw the complete line
        if (Math.abs(dx) > Math.abs(dy))
            step = Math.abs(dx);
        else
            step = Math.abs(dy);
        
        //calculate x-increment and y-increment for each step
        float x_incr = (float)dx / step;
        float y_incr = (float)dy / step;
        
        //take the initial points as x and y
        float x = x0;
        float y = y0;
        
        for (int i = 0; i <= step; i ++) {
            //putpixel(round(x), round(y), WHITE);
            plotPoint((int)(x), (int)(y), c, g);
            x += x_incr;
            y += y_incr;
        }
    }
    public void plotPoint(int x, int y, Color c, Graphics g){
            int newX = (getX()+getWidth())/2;
            int newY = (getY()+getHeight())/2;
        
            g.setColor(c);
            g.fillOval(x*scale-scale/2+newX,newY-y*scale-scale/2,scale,scale); 
    }
    public void paintGrid(Graphics g,int gap,int originx,int originy)
    {
        g.setColor(Color.white);
        
        for(int i = gap;i<=getWidth();i+=gap)
        {
            g.drawLine(originx+i, originy-getHeight()/2, originx+i, originy+getHeight()/2);
            g.drawLine(originx-i, originy-getHeight()/2, originx-i, originy+getHeight()/2);
        }
        for(int i = gap;i<=getHeight();i+=gap)
        {
            g.drawLine(originx-getWidth()/2, originy+i, originx+getWidth()/2, originy+i);
            g.drawLine(originx-getWidth()/2, originy-i, originx+getWidth()/2, originy-i);

        }
    }
	public void paint(Graphics g)
	{
        int newX = (getX()+getWidth())/2;
        int newY = (getY()+getHeight())/2;
        // paintGrid(g,scale,newX,newY);
        g.drawLine(newX,0,newX,getHeight());
        g.drawLine(0,newY,getWidth(),newY);
        DDALine(g,100,50,100,-50,Color.green);
        DDALine(g,-100,50,-100,-50,Color.green);
        DDALine(g,-100,-50,100,-50,Color.green);
        DDALine(g,-100,50,100,50,Color.green);

		if(clipped == true){
            cohenSutherlandClip(140,40,90,70,g);
            cohenSutherlandClip(90,70,-130,10,g);
            cohenSutherlandClip(-130,10,-10,-70,g);
            cohenSutherlandClip(-10,-70,10,10,g);
            cohenSutherlandClip(10,10,140,40,g);
            // cohenSutherlandClip(0,60,110,0,g);
            // cohenSutherlandClip(110,0,0,-60,g);
            // cohenSutherlandClip(0,-60,-110,0,g);
            // cohenSutherlandClip(-110,0,0,60,g);

        }else{
            DDALine(g,140,40,90,70,Color.red);
            DDALine(g,90,70,-130,10,Color.red);
            DDALine(g,-130,10,-10,-70,Color.red);
            DDALine(g,-10,-70,10,10,Color.red);
            DDALine(g,10,10,140,40,Color.red);
            // DDALine(g,0,60,110,0,Color.red);
            // DDALine(g,110,0,0,-60,Color.red);
            // DDALine(g,0,-60,-110,0,Color.red);
            // DDALine(g,-110,0,0,60,Color.red);
        }
	}
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        int z=e.getWheelRotation();
        scale+=z;
        repaint();
    }
    public void actionPerformed(ActionEvent e) {
        String st = e.getActionCommand();
        if(st.equals("Zoom In"))
        scale++;
        else if(st.equals("Zoom Out") && scale > 2)
        scale--;
        else if(st.equals("Clip")){
            if(clipped == true) clipped = false;
            else    clipped = true;
        }
        repaint();
    }

}
