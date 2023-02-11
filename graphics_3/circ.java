import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class circ extends Applet implements ActionListener{
        int scale=12;
        ArrayList<dr> tot =new ArrayList<dr>();
        dr big= new dr();
        Boolean flag = false;
        
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
	
			
	public int dist(int x1, int y1, int x2, int y2){
		return (int)Math.sqrt(((x1-x2)*(x1-x2)) + ((y1-y2)*(y1-y2)));
	}
	
	public Boolean passed(int r, int x, int y){
		if((big.r-r) < dist(big.x,big.y,x,y))	return false;
		dr p;
		for(int i = 1; i < tot.size(); i++){
			p = tot.get(i);
			if(dist(p.x,p.y,x,y) < (r+p.r))	return false;
			if(Math.abs(p.r-r) >= dist(p.x,p.y,x,y))	return false;
		}
		return true;
	}
	
	public void drawCircle(int r, int x1, int y1, Graphics g) {
	     if(!flag){
	//	if(r != big.r || x1 != big.x || y1 != big.y){
	//		if(!passed(r,x1,y1))	return;
	//	}
		if(!passed(r,x1,y1))	return;
		dr new1 = new dr(r, x1, y1);
		tot.add(new1);
	     }
		int x = 0;
		int y = r;
		double p = (double) 5 / 4 - r;
		Color co = Color.green;
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
	
	
	public void gen(int r, Graphics g){
		
		for(int radius = big.r; radius > 1; radius--){
		   for(int i = 0; i < 2000; i++){
			int x1 = (int)(Math.random()*r);
			int y1 = (int)(Math.random()*(Math.sqrt(r*r-x1*x1)));
			drawCircle(radius,x1,y1,g);
			drawCircle(radius,-x1,y1,g);
			drawCircle(radius,x1,-y1,g);
			drawCircle(radius,-x1,-y1,g);
		   }
		}
	}
	
        public void plotpoint(int x, int y, Color c, Graphics g){
                int newX = (getX()+getWidth())/2;
                int newY = (getY()+getHeight())/2;
          
                g.setColor(c);
                g.fillOval(x*scale-scale/2+newX,newY-y*scale-scale/2,scale,scale);
                //g.fillOval(x*6-3+newX,newY-y*6-3,6,6); 
        }

	public void paint(Graphics g)
	{  
                int x =( getX()+getWidth())/2;
                int y = (getY()+getHeight())/2;
                
                g.setColor(Color.red);
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
                
                g.setColor(Color.yellow);
                if(flag){
	      	  dr q;
	      	  for(int i = 0; i < tot.size(); i++){
	      		q = tot.get(i);
	      		drawCircle(q.r,q.x,q.y,g);
	      	  }
	      	  return;
	        }
                int[][] lists = {{50,0,0},{23,23,14},{23,0,-27}};    //,{22,65,-38},{8,0,0},{22,0,78}
           	
                big.r = lists[0][0];
                for(int i = 0; i < lists.length; i++){
		   	int r1 = lists[i][0], x1 = lists[i][1], y1 = lists[i][2]; 
	
			drawCircle(r1,x1,y1,g);
			
			
			if(x1 != 0){
				drawCircle(r1,-x1,y1,g);
				
			}
			
	        }
	        drawCircle(big.r,big.x,big.y, g);
	        gen(big.r, g);
	        
	}
	
	public void actionPerformed(ActionEvent e) {
                String st = e.getActionCommand();
                if(st.equals("Zoom In"))
                scale += 2;
                else if(scale-2 > 0)
                scale -= 2;
                flag = true;
                repaint();
        }
}
