import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class ellipse extends Applet implements ActionListener
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
	public void plotEllipse(Graphics g,int rx,int ry){
	    int x=0;
	    int y=ry;
	    double p1=(ry*ry)-(rx*rx*ry)+(double)(rx*rx)/4;
	    Color co = Color.red; 
	    plotPoint(g, x, y, co);
	    plotPoint(g, x, -y, co);
	    while(2*ry*ry*x<=2*rx*rx*y){
		if(p1<0){
		    x++;
		    p1=p1+(2*ry*ry*x)+(ry*ry);
		}
		else{
		    x++;
		    y--;
		    p1=p1+(2*ry*ry*x)+(ry*ry)-(2*rx*rx*y);
		}
		plotPoint(g, x, y, co);
		plotPoint(g, -x, y, co);
		plotPoint(g, x, -y, co);
		plotPoint(g, -x, -y, co);
	    }

	    double p2=(ry*ry*(x+0.5)*(x+0.5))+((y-1)*(y-1)*rx*rx)-rx*rx*ry*ry;
	    while(2*ry*ry*x>2*rx*rx*y && (y!=0)){
		if(p2<0){
		    x++;
		    y--;
		    p2=p2+(2*ry*ry*x)-(2*rx*rx*y)+(rx*rx);
		}
		else{
		    
		    y--;
		    p2=p2-(2*rx*rx*y)+(rx*rx);
		}

		plotPoint(g, x, y, co);
		plotPoint(g, -x, y, co);
		plotPoint(g, x, -y, co);
		plotPoint(g, -x, -y, co);
	    }
	  }		
		
	
        public void plotPoint( Graphics g, int x, int y, Color c){
                int newX = (getX()+getWidth())/2;
                int newY = (getY()+getHeight())/2;
          
                g.setColor(c);
                //g.fillOval(x*scale-scale/2+newX,newY-y*scale-scale/2,scale,scale);
                g.fillOval(x*scale-scale/4+newX,newY-y*scale-scale/4,scale/2,scale/2); 
        }

	public void paint(Graphics g)
	{  
                int x =( getX()+getWidth())/2;
                int y = (getY()+getHeight())/2;
                g.setColor(Color.red);
                g.drawLine(x,0,x,getHeight());
                g.drawLine(0,y,getWidth(),y);
                
                g.setColor(Color.green); 
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
                
                int x1 = 0, y1 = 0, rx = 25, ry = 32;
		plotEllipse(g,ry,rx);       
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
