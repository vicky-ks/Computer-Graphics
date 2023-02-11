import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class p1i extends Applet implements ActionListener
{
        int scale=20;

	public void init()
	{
	    	setBackground(Color.white);
 		Button b1 = new Button("Zoom In");   
		Button b2 = new Button("Zoom Out");
		add(b1);
                add(b2);
                b1.addActionListener(this);
                b2.addActionListener(this);
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
                
                g.setColor(Color.black); 
                for(int i=x+scale; i<getWidth(); i+=scale){
                g.drawLine(i,0,i,getHeight());
                }
                
                for(int i=scale; i<getWidth(); i+=scale){
                g.drawLine(x-i,0,x-i,getHeight());
                }
                
                for(int i=y+scale; i<getHeight(); i+=scale){
                g.drawLine(0,i,getWidth(),i);
                }
                
                for(int i=scale; i<getHeight(); i+=scale){
                g.drawLine(0,y-i,getWidth(),y-i);
                }
                
		double dx, dy, X, Y;
		
		double xc, yc;
		double x1 = -12, y1 = -15, x2 = 9, y2 = 17;

		dx = x2 - x1;
		dy = y2 - y1;

		double m = dy / dx;

		if (m <= 1) {
		    xc = 1;
		    yc = m;
		} else {
		    xc = 1 / m;
		    yc = 1;
		}

		X = x1;
		Y = y1;

		//DDA Algo
		plotpoint((int)X,(int)Y,Color.red,g);
		for(int k = 1; k <= Math.max((int)dx,(int)dy); k++){
		   X += xc;
		   Y += yc;
		   plotpoint((int)X,(int)Y,Color.red,g);
		}
               // plotpoint(10,11,192,192,192,g);
                
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
