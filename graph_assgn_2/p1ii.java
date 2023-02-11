import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class p1ii extends Applet {
        int scale=20;

	public void init()
	{
	    	setBackground(Color.black);
 	
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
                g.setColor(Color.yellow);
                g.drawLine(x,0,x,getHeight());
                g.drawLine(0,y,getWidth(),y);
                
                g.setColor(Color.white); 
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
		
		double x1 = -20, y1 = -15, x2 = 15, y2 = 17;

		dx = x2 - x1;
		dy = y2 - y1;

		double m = dy / dx, p;
		Color co = Color.green;
		plotpoint((int)x1,(int)y1,co,g);
		//bresenham's algo
		if (m <= 1) {
		  p = 2*dy-dx;
	          while(x1 != x2){
		       
		       x1++;
		       if(p >= 0){
			 p = p+2*dy-2*dx;
		         y1++;
		       }else{
			 p = p+2*dy;
		       }
		       plotpoint((int)x1,(int)y1,co,g);
		  }
		    
		} else {
		    p = 2*dx-dy;
		    while(y1 != y2){
			y1++;
			if(p >= 0){
			 p = p+2*dx-2*dy;
			 x1++;
		         
		        }else{
			 p = p+2*dx;
		        }
			plotpoint((int)x1,(int)y1,co,g);
		    }
		}

	}

};
                
