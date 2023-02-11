import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class fire extends Applet implements ActionListener{
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
	
	public Color getcolor(double x1, double y1, double x2, double y2){
		double b = x1*x1+y1*y1;
		double y = x2*x2+y2*y2;
		double p = b/y*100;
		int val = (int)p;
		if(val <= 16)	return Color.white;
		if(val <= 30)	return Color.yellow;
		if(val <= 70)	return Color.orange;
		return Color.red;
	}
	
	public void plottmid(double x1, double y1, double x2, double y2, Graphics g){
		double dx, dy;

		dx = x2 - x1;
		dy = y2 - y1;

		double m = dy / dx, p;
		Color co = Color.white;
		plotpoint((int)x1,(int)y1,co,g);

		//midpoint algo
	    if(m > 0)
		if (m <= 1) {
		  p = .5 - m;
	          while(x1 != x2){
		       
		       x1++;
		       if(p >= 0){
			 p = p-m;
		        
		       }else{
			 p = p-m+1;
			 y1++;
		       }
		       co = getcolor(x1,y1,x2,y2);
		       plotpoint((int)x1,(int)y1,co,g);
		  }
		    
		} else {
		    p = 1 - m/2;
		    while(y1 != y2){
			y1++;
			if(p >= 0){
			 p = p-m+1;
			 x1++;
		         
		        }else{
			 p = p+1;
		        }
		        co = getcolor(x1,y1,x2,y2);
			plotpoint((int)x1,(int)y1,co,g);
		    }
		}
	    else
		if (Math.abs(m) <= 1) {
		  p = .5 + m;
	          while(x1 != x2){
		       
		       x1--;
		       if(p >= 0){
			 p = p+m;
		        
		       }else{
			 p = p+m+1;
			 y1++;
		       }
		       co = getcolor(x1,y1,x2,y2);
		       plotpoint((int)x1,(int)y1,co,g);
		  }
		    
		} else {
		    p = 1 + m/2;
		    while(y1 != y2){
			y1++;
			if(p >= 0){
			 p = p+m+1;
			 x1--;
		         
		        }else{
			 p = p+1;
		        }
		        co = getcolor(x1,y1,x2,y2);
			plotpoint((int)x1,(int)y1,co,g);
		    }
		}
	    
	    plotpoint((int)x2,(int)y2,Color.red,g);
		
	
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
                
               // g.setColor(Color.white); 
               // for(int i=x+scale; i<getWidth(); i+=scale){
               // g.drawLine(i,0,i,getHeight());
               // }
                
               // for(int i=scale; i<getWidth(); i+=scale){
               // g.drawLine(x-i,0,x-i,getHeight());
               // }
                
               // for(int i=y+scale; i<getHeight(); i+=scale){
               // g.drawLine(0,i,getWidth(),i);
               // }
                
               // for(int i=scale; i<getHeight(); i+=scale){
               // g.drawLine(0,y-i,getWidth(),y-i);
               // }
                //double[][] lists = {{0,0,7,9},{0, 0, 6, 11},{0, 0, 5, 11},{0, 0, 4, 13},{0, 0, 3, 12},{0, 0, 2, 14},{0,0,2,3},{0,0,0,13},
                			//{0,0,-6,7},{0, 0, -6, 10},{0, 0, -4, 11},{0, 0, -4, 10},{0, 0, -3, 13},{0,0,-1,15}, {0,0,-2,3}};
                double[][] lists = {{0,0,8,32},{0,0,7,35},{0,0,6,36},{0,0,3,37},{0,0,4,38},{0,0,2,40},{0,0,1,41},{0,0,0,42},
                		      {0,0,-7,29},{0,0,-7,33},{0,0,-6,35},{0,0,-4,36},{0,0,-3,37},{0,0,-2,39},{0,0,-1,42},
                		       {0,0,4,15},{0,0,3,17},{0,0,3,18},{0,0,1,17},{0,0,2,19},{0,0,1,18},{0,0,0,20},{0,0,0,21},
                			{0,0,-4,15},{0,0,-3,16},{0,0,-3,17},{0,0,-2,18},{0,0,-1,18},{0,0,-1,19},{0,0,0,20}};
               // double[][] lists = {{0,0,16,32},{0,0,13,35},{0,0,10,36},{0,0,6,37},{0,0,8,38},{0,0,4,40},{0,0,3,41},{0,0,0,42},
                //		      {0,0,-14,29},{0,0,-13,33},{0,0,-11,35},{0,0,-8,36},{0,0,-6,37},{0,0,-3,39},{0,0,-2,42}};
                		
		//{0, 0, 2, 13},,{0, 0, -2, 13},{0,0,-1,15}
		//double x1 = -12, y1 = -15, x2 = 9, y2 = 17;
              for(int i = 0; i < lists.length; i++){
		   	double x1 = lists[i][0], y1 = lists[i][1], x2 = lists[i][2], y2 = lists[i][3]; 
	
			plottmid(x1,y1,x2,y2,g);
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
