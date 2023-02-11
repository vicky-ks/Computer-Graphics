import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class circle extends Applet implements ActionListener{
        int scale=20;
        
	public void init()
	{
	    	setBackground(Color.black);
 		Button b1 = new Button("Zoom In");
		b1.setBackground(Color.green);   
		Button b2 = new Button("Zoom Out");
		b2.setBackground(Color.green);
		add(b1);
                add(b2);
                b1.addActionListener(this);
                b2.addActionListener(this);
 	
	}
	

	public void drawCircle(int r, int x1, int y1, Graphics g) {
		int x = 0;
		int y = r;
		double p = (double) 5 / 4 - r;
		Color co = Color.red;
		plotpoint(x + x1, y + y1, co, g);
		plotpoint(x + x1, -y + y1, co, g);
		plotpoint(y + x1, x + y1, co, g);
		plotpoint(-y + x1, x + y1, co, g);
		while (x <= y) {
			if (p < 0) {
				x = x + 1;
				p = p + 2 * x + 1;
			} else {
				x = x + 1;
				y = y - 1;
				p = p + (2 * x) + 1 - (2 * y);
			}
			
			plotpoint(x + x1, y + y1, co, g);
			plotpoint(y + x1, x + y1, co, g);
			plotpoint(-x + x1, y + y1, co, g);
			plotpoint(-y + x1, x + y1, co, g);
			plotpoint(x + x1, -y + y1, co, g);
			plotpoint(y + x1, -x + y1, co, g);
			plotpoint(-x + x1, -y + y1, co, g);
			plotpoint(-y + x1, -x + y1, co, g);
		}
	
	} 
	
        public void plotpoint(int x, int y, Color c, Graphics g){
                int newX = (getX()+getWidth())/2;
                int newY = (getY()+getHeight())/2;
          
                g.setColor(c);
                g.fillOval(x*scale-scale/2+newX,newY-y*scale-scale/2,scale,scale); 
        }

	public void paint(Graphics g)
	{  
                int x =( getX()+getWidth())/2;
                int y = (getY()+getHeight())/2;
                g.setColor(Color.red);
                g.drawLine(x,0,x,getHeight());
                g.drawLine(0,y,getWidth(),y);
                
                int[][] lists = {{100,0,0},{46,46,27},{46,0,-54},{8,0,0},{22,0,78},{22,65,-38},{10,33,82},
                		      {10,88,-14},{10,54,-70},{5,50,80},{6,94,3},{6,45,-83},{4,58,76},{3,95,14},
                		       {3,36,-88},{4,0,50},{4,42,-24},{2,65,72},{3,95,22},{2,31,-92},{4,23,92},{4,91,-30},
                			{4,69,-64}};

              for(int i = 0; i < lists.length; i++){
		   	int r = lists[i][0], x1 = lists[i][1], y1 = lists[i][2]; 
	
			drawCircle(r,x1,y1,g);
			if(x1 != 0)	drawCircle(r,-x1,y1,g);
	      }
	}
	
	public void actionPerformed(ActionEvent e) {
                String st = e.getActionCommand();
                if(st.equals("Zoom In"))
                scale += 2;
                else if(scale-2 > 0)
                scale -= 2;
                repaint();
        }
}
