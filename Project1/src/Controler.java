/* 
  Start with the name of ALLAH
  Always say alhamdulillah whatever happens!!
  Md. Shahria Sarker Shuvo
  CSE---North South University
  
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controler implements KeyListener{

	RunGame man;//reference variable
	
	public Controler (RunGame x) //constructor
	{
		this.man= x;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) { // Controlling the Character using keyListener
		//Using this we'll read a key press from user 
		char ch=e.getKeyChar();
		//char xp=e.getKeyChar();
		
		if(man.c) {
			if(ch=='a'||ch=='A')// if a/A that's mean character need to move left
	
		{
			int x=man.panel.getX();
			int y=man.panel.getY();
			if(x-man.speed>=0) {
			man.panel.setLocation(x-man.speed,y);
			}
			System.out.println("Your Score "+(man.scr/2));
			
		}
		if(ch=='d'||ch=='D')// if a/A that's mean character need to move right
		{
			int p=man.frame.getWidth();
			int n=man.panel.getWidth();
			int x=man.panel.getX();
			int y=man.panel.getY();
			if(x+man.speed<=p-n-5) {
			man.panel.setLocation(x+man.speed,y);
			}
			System.out.println("Your Score "+(man.scr/2));
		}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	
}
