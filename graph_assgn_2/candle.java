import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class candle extends Applet implements ActionListener{
        int scale=20;
        double[][] lists = {{0,0,8,32},{0,0,7,35},{0,0,6,36},{0,0,3,37},{0,0,4,38},{0,0,2,40},{0,0,1,41},{0,0,0,42},
                		      {0,0,-7,29},{0,0,-7,33},{0,0,-6,35},{0,0,-4,36},{0,0,-3,37},{0,0,-2,39},{0,0,-1,42},
                		       {0,0,4,15},{0,0,3,17},{0,0,3,18},{0,0,1,17},{0,0,2,19},{0,0,1,18},{0,0,0,20},{0,0,0,21},
                			{0,0,-4,15},{0,0,-3,16},{0,0,-3,17},{0,0,-2,18},{0,0,-1,18},{0,0,-1,19},{0,0,0,20}};
   
        boolean b = false;
            			
	public void init()
	{
	    	setBackground(Color.black);
 		Button b1 = new Button("Zoom In");
		b1.setBackground(Color.green);   
		Button b2 = new Button("Zoom Out");
		b2.setBackground(Color.green);
		add(b1);
                add(b2);
                
 		Button l1 = new Button("Light up");
 		l1.setBackground(Color.red);
 		Button l2 = new Button("Put out");
 		l2.setBackground(Color.white);
 		add(l1);
 		add(l2);
 		b1.addActionListener(this);
                b2.addActionListener(this);
 		l1.addActionListener(this);
                l2.addActionListener(this);
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
        
        public void light_candle(Graphics g){
		
		for(int i = 0; i < lists.length; i++){
		   	double x1 = lists[i][0], y1 = lists[i][1], x2 = lists[i][2], y2 = lists[i][3]; 
	
			plottmid(x1,y1,x2,y2,g);
	        }
	        
	}
	
	public void paint(Graphics g)
	{  
                int x =( getX()+getWidth())/2;
                int y = (getY()+getHeight())/2;
              
                g.setColor(Color.green);
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
           
               if(b)
               	light_candle(g);
	       g.setColor(Color.blue);
	       g.fillRect(x-8*scale,y,16*scale,30*scale);
	}
	
	
	public void actionPerformed(ActionEvent e) {
                String st = e.getActionCommand();
                if(st.equals("Zoom In"))
                scale += 2;
                
                else if(st.equals("Zoom Out") && scale-2 > 0)
                scale -= 2;
               
                else if(st.equals("Light up"))
                	b = true;
                else if(st.equals("Put out"))
                	b = false;
                repaint();
        }
}
