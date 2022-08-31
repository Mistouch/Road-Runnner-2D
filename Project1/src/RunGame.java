/* 
  Start with the name of ALLAH
  Always say alhamdulillah whatever happens!!
  Md. Shahria Sarker Shuvo
  CSE---North South University
  
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.*;

public class RunGame implements ActionListener{
	//First declare all the thing's we need
	
	JFrame frame,msg,logframe,nmsg;
	JPanel logopanel,logpan1,panel1,panel,panel2,panel3,panel4,panel5,panel6,panel7,pan1,
	pan2,pan3,pan4,ob1,ob2,ob3,ob4,cr1,cr2,cr3,sign1,sign2,sign3,sign4,sign5,sign6,sign7,sign8,
	sign9,sign10,sign11,sign12,sign13,sign14,sign15,sign16;
	Controler cnt;
	JButton button,rbutton,Cbutton,gbutton,c1,c2,c3,backbutton,dbutton,hbutton;
	JLabel logo,slabel,highlabel,labelfav,buttonlbl,carlab1,carlab,carlab2,label,label1,label2,label3,label4,label5,
	label6,o1,o2,o3,o4,txt1,pic1,pic2,char1,char2,char3;
	Timer timer;
	BufferedWriter writer;
	Clip clip;
	int diff,highScore=0;
	int speed,bg;
	int x=0,ssp=0;
	int scr=0;//Note, first we set x=0 and score =0 because initially we don't want any value of those
	boolean c=true;
	boolean k=false;
	
	//Then Constructor
	public RunGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		
		speed=15;
		diff=10;
		cnt= new Controler(this);// we need to control from controller class with cnt so add actionlsitener 
		run();
	}
	public int getSSP()
	{
		return this.ssp;
	}
	public void setX(int xx)
	{
		this.x=xx;
	}
	
	//this will be called when they will pass a parameter in Score()
	//in this method file will be create and highest score will found
   public int Score(int x){
	  
	      //we need to use try catch because there can no file,same name or other exception
	       try { 
			
	    	   FileWriter file = new FileWriter("StoreScore.txt",true);// creating a file name StoreScore
	          writer= new BufferedWriter(file); 
	          
	         String s=String.valueOf(x);// this is where we change the score into string from a integer
	         
	         writer.write(""+x+"\n");// and store the score as a string with a new line so we can read by line 
	         
	         writer.close();//we need to close otherwise file can store garbage value
	         
			}
			 catch (Exception e)// if any exception is found it'll be handled
	        {
	           
	        }
	       // now find the highest score from the file
		try {
	        
			String st;
			FileReader file = new FileReader("StoreScore.txt");
			
			BufferedReader input = new BufferedReader(file); 
	         
	        String line;
	     
	        // in this while loop we'll check all value line by line and check that is it greater than highScore or not until EOF
	        
            while((line=input.readLine()) !=null){
            	
               int s=Integer.parseInt(line);// we stored score as a string but we need integer so we convert into integer 
               
                if(s>=highScore){ // if a score is greater than highScore then we update the high Score
                    highScore=s;
                }
       

		}
	}
			 catch (Exception except)
	       {
	            
	       }
			return highScore; // return the high score
			
}
	     //Method Overloading and it'll be called when no parameter is passed
   		 //The mechanism is same as the other Score method
	      public int Score(){
	    	  
	   	   
				try { 
					FileWriter file = new FileWriter("StoreScore.txt",true);
	         
	     
	          writer= new BufferedWriter(file); 
	         writer.close();
	         
			}
			 catch (Exception e)
	        {
	           
	        }
			try {
	        
		
			FileReader file = new FileReader("StoreScore.txt");

		      BufferedReader input = new BufferedReader(file); 
	         
	        String line;
	     
          while((line=input.readLine()) !=null){
             int s=Integer.parseInt(line);
              if(s>=highScore){
                  highScore=s;
              }
     

		}
		}
			 catch (Exception except)
	       {
	            
	       }
			return highScore;
			
}
	      
	     public void SounCheck(int x) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	      {
	    	  File sound = new File("Sound.wav");	
	  		AudioInputStream audio= AudioSystem.getAudioInputStream(sound);
	  	   Clip clip= AudioSystem.getClip();
	  	   clip.open(audio);
	  	   if(getSSP()==1)
	  		   clip.start();
	  	    if(ssp==2) {
	  		   clip.close();
	  		   
	  	    }
	  	   
	  	   System.out.println(x);
	      }
	     
	
	// This is where all things start start
	public void run() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		//ssp=0;
		 File sound = new File("Sound.wav");	
	  		AudioInputStream audio= AudioSystem.getAudioInputStream(sound);
	  	   Clip clip= AudioSystem.getClip();
	  	   clip.open(audio);
	  	   
	  	   
	  	  
		//This is our main frame where other's will be displayed
		frame = new JFrame("Road Runner 2D");
        logframe= new JFrame("Road Runner 2D");//This is our main frame
		frame.setLayout(null);
		logframe.setLayout(null);
		
	     msg=new JFrame(); // this is  our game over frame
		//frame.setBackground(Color.black);
	     
		panel = new JPanel();	
		
		//label is for default character choose and it is set yellow car
	    label = new JLabel(new ImageIcon("yellowcar.png"));
	    label.setVisible(false);
	    
	    //carlab is storing the real character green car
	    carlab = new JLabel(new ImageIcon("GreenCar.png"));
	    carlab.setVisible(false);
	    
	    //carlab1 is storing the real character cartoon
	    carlab1 = new JLabel(new ImageIcon("Rcar.png"));
	    carlab1.setVisible(false);
	    
	    //carlab2 is storing the real character yellow car
	    carlab2 = new JLabel(new ImageIcon("yellowcar.png"));
	    carlab2.setVisible(false);
	    
	    panel4= new JPanel();
	    
	    //pic1 storing instructions picture
	    pic1 = new JLabel(new ImageIcon("How3.png"));
	    pic1.setVisible(true);
	    
	    //pic2 storing press start button text picture in instruction section
	    pic2 = new JLabel(new ImageIcon("stt.png"));
	    pic2.setVisible(true);
	    pic2.setLocation(450,300);
	    
	    //char1 is storing a character which is green car in selection section
	    char1 = new JLabel(new ImageIcon("gcar2.png"));
	    char1.setVisible(true);
	    
	    //char1 is storing a character which is a cartoon character in selection section
	    char2 = new JLabel(new ImageIcon("RRcar.png"));
	    char2.setVisible(true);
	    
	    //char1 is storing a character which is yellow car in selection section
	    char3 = new JLabel(new ImageIcon("ycar.png"));
	    char3.setVisible(true);
	    
	    //logo is storing my game logo
	    logo = new JLabel(new ImageIcon("OriLogo-removebg-preview.png"));
	    logo.setVisible(true);
	    
	    logopanel= new JPanel();
	    
	    
	    
	    
	    //This label1 and button is  our front page or we can say start menu
	    label1= new JLabel();
		label1.setText("Welcome To Road Runner 2D");
	    label1.setFont(new Font("My boli",Font.BOLD,35));
	    label1.setSize(550,100);
	    label1.setLocation(120,40);
	    label1.setForeground(Color.DARK_GRAY);
	  
	    
	   //This is for showing developer information in the bottom
	    label2= new JLabel();
	    label2.setText("Developed by @Mistouch    Contact: shahria.shuvo@northsouth.edu      Mobile: 01641616940");
	    label2.setFont(new Font("My boli",Font.BOLD,13));
	    label2.setSize(580,100);
	    label2.setLocation(10,485);
	    label2.setForeground(Color.DARK_GRAY);
	    
	    //This is showing my game's version
	    label3= new JLabel();
		label3.setText("V-2022");
	    label3.setFont(new Font("My boli",Font.HANGING_BASELINE,15));
	    label3.setSize(100,80);
	    label3.setLocation(550,85);
	    label3.setForeground(Color.DARK_GRAY);
	    
	   //This is a statement text for start the game
	    buttonlbl= new JLabel();
	    buttonlbl.setText("Choose any character to start the game");
	    buttonlbl.setFont(new Font("My boli",Font.BOLD,25));
	    buttonlbl.setSize(550,100);
	    buttonlbl.setLocation(130,300);
	    buttonlbl.setForeground(Color.DARK_GRAY);
	    
	    //For showing instruction text
	    label4= new JLabel();
	    label4.setText("If you're new and don't know how to play click the button");
	    label4.setFont(new Font("My boli",Font.BOLD,15));
	    label4.setSize(550,100);
	    label4.setLocation(10,270);
	    label4.setForeground(Color.DARK_GRAY);
	    label4.setVisible(false);
	    
	    //This is for showing Choose game character text
	    label5= new JLabel();
	    label5.setText("Choose your Game Character ->");
	    label5.setFont(new Font("My boli",Font.BOLD,15));
	    label5.setSize(550,100);
	    label5.setLocation(30,380);
	    label5.setForeground(Color.DARK_GRAY);
	    
	    //This is for showing text see high score
	    highlabel= new JLabel();
	    highlabel.setText("See the highest score-->");
	    highlabel.setFont(new Font("My boli",Font.BOLD,15));
	    highlabel.setSize(550,100);
	    highlabel.setLocation(30,350);
	    highlabel.setForeground(Color.DARK_GRAY);
	    highlabel.setVisible(false);
	    
	  //For showing Choose character text at Character choose section
	    labelfav= new JLabel();
	    labelfav.setText("Choose your favourite Character!");
	    labelfav.setFont(new Font("My boli",Font.BOLD,20));
	    labelfav.setSize(550,100);
	    labelfav.setLocation(180,440);
	    labelfav.setForeground(Color.DARK_GRAY);
	    labelfav.setVisible(false);
	    
	    //For showing or default text before default button
	    label6= new JLabel();
	    label6.setText("Or default ");
	    label6.setFont(new Font("My boli",Font.BOLD,15));
	    label6.setSize(100,100);
	    label6.setLocation(430,380);
	    label6.setForeground(Color.DARK_GRAY);
	    
	    
	    //This is for creating Start button
	    button =new JButton();
	    button.setSize(150,100);
	    button.setLocation(280, 150);
	    button.setBackground(Color.CYAN);
	    button.setText("Start the game");
		button.setFocusable(false);
	    button.addActionListener(this);
	    button.setVisible(false);
	    
	    //This is for creating How to play button
	    rbutton= new JButton();
	    rbutton.setSize(120,50);
	    rbutton.setLocation(450, 295);
	    rbutton.setBackground(Color.MAGENTA);
	    rbutton.setText("How to Play?");
		rbutton.setFocusable(false);
	    rbutton.addActionListener(this);
	    rbutton.setVisible(false);
	   
	   ////This is for creating Character choose button
	   Cbutton= new JButton();
	    Cbutton.setSize(120,50);
	    Cbutton.setLocation(280, 405);
	    Cbutton.setBackground(Color.MAGENTA);
	    Cbutton.setText("Character");
		Cbutton.setFocusable(false);
	   Cbutton.addActionListener(this);
	   Cbutton.setVisible(true);
	   
	 //This is for creating Default character button
	   dbutton= new JButton();
	    dbutton.setSize(100,50);
	    dbutton.setLocation(520, 405);
	    dbutton.setBackground(Color.MAGENTA);
	   dbutton.setText("Default");
		dbutton.setFocusable(false);
	   dbutton.addActionListener(this);
	   dbutton.setVisible(true);
	   
	    //This is for creating High Score button
	    hbutton =new JButton();
	    hbutton.setSize(100,50);
	    hbutton.setLocation(280, 375);
	    hbutton.setBackground(Color.CYAN);
	    hbutton.setText("High Score");
		hbutton.setFocusable(false);
	    hbutton.addActionListener(this);
	    hbutton.setVisible(false);
	   
	   //this is used for showing high score when high score button is pressed
	    slabel= new JLabel();
		slabel.setText("High Score = "+Score());
	    slabel.setFont(new Font("My boli",Font.BOLD,13));
	    slabel.setSize(120,50);
	    slabel.setLocation(450,375);
	    slabel.setForeground(Color.DARK_GRAY);
	    slabel.setVisible(false);
	  
	    //Now create 4 panel to draw the frame like a road with 4 lane
	    //pan1,pan,pan3,pan4,represent 4 lane of road
	  pan1= new JPanel();
	  pan1.setBackground(Color.DARK_GRAY);
	  pan1.setBounds(180,0,5,620);
	  pan1.setVisible(false);
	  
	  pan2= new JPanel();
	  pan2.setBackground(Color.DARK_GRAY);
	  pan2.setBounds(365,0,5,620);
	  pan2.setVisible(false);
	  
	  pan3= new JPanel();
	  pan3.setBackground(Color.DARK_GRAY);
	  pan3.setBounds(550,0,5,620);
	  pan3.setVisible(false);
	    
	  pan4= new JPanel();
	  pan4.setBackground(Color.DARK_GRAY);
	  pan4.setBounds(735,0,05,620);
	  pan4.setVisible(false);
	  
	  
	  
	    
	  //In one panel there also a obstacle which want to block the character, so create 4 obstacle panel 
	  //ob1,ob2,ob3,ob4 is using for obstacle in 4 lane
	  ob1= new JPanel();
	  ob1.setBackground(Color.black);
	  ob1.setSize(180,25);
	  ob1.setLocation(0,0);
	  ob1.setVisible(false);
	  
	  ob2= new JPanel();
	  ob2.setBackground(Color.black);
	  ob2.setSize(185,25);
	  ob2.setLocation(185,355);
	  
	  ob2.setVisible(false);
	  ob3= new JPanel();
	  ob3.setBackground(Color.black);
	  ob3.setSize(185,25);
	  ob3.setLocation( 370,10);
	  ob3.setVisible(false);
	  
	  ob4= new JPanel();
	  ob4.setBackground(Color.black);
	  ob4.setSize(185,25);
	  ob4.setLocation(555,350);
	  ob4.setVisible(false);
	  
	  sign1= new JPanel();
	  sign1.setBackground(Color.gray);
	  sign1.setSize(16,50);
	  sign1.setLocation(80,50);
	  sign1.setVisible(false);
	  
	  sign2= new JPanel();
	  sign2.setBackground(Color.gray);
	  sign2.setSize(16,50);
	  sign2.setLocation( 80,180);
	  sign2.setVisible(false);
	  
	  sign3= new JPanel();
	  sign3.setBackground(Color.gray);
	  sign3.setSize(16,50);
	  sign3.setLocation(80,310);
	  sign3.setVisible(false);

	  
	  sign4= new JPanel();
	  sign4.setBackground(Color.gray);
	  sign4.setSize(16,50);
	  sign4.setLocation(80,440);
	  sign4.setVisible(false);
	  
	  sign5= new JPanel();
	  sign5.setBackground(Color.gray);
	  sign5.setSize(16,50);
	  sign5.setLocation(265,50);
	  sign5.setVisible(false);
	  
	  sign6= new JPanel();
	  sign6.setBackground(Color.gray);
	  sign6.setSize(16,50);
	  sign6.setLocation( 265,180);
	  sign6.setVisible(false);
	  
	  sign7= new JPanel();
	  sign7.setBackground(Color.gray);
	  sign7.setSize(16,50);
	  sign7.setLocation(265,310);
	  sign7.setVisible(false);

	  
	  sign8= new JPanel();
	  sign8.setBackground(Color.gray);
	  sign8.setSize(16,50);
	  sign8.setLocation(265,440);
	  sign8.setVisible(false);
	  
	  sign9= new JPanel();
	  sign9.setBackground(Color.gray);
	  sign9.setSize(16,50);
	  sign9.setLocation(450,50);
	  sign9.setVisible(false);
	  
	  sign10= new JPanel();
	  sign10.setBackground(Color.gray);
	  sign10.setSize(16,50);
	  sign10.setLocation( 450,180);
	  sign10.setVisible(false);
	  
	  sign11= new JPanel();
	  sign11.setBackground(Color.gray);
	  sign11.setSize(16,50);
	  sign11.setLocation(450,310);
	  sign11.setVisible(false);

	  sign12= new JPanel();
	  sign12.setBackground(Color.gray);
	  sign12.setSize(16,50);
	  sign12.setLocation(450,440);
	  sign12.setVisible(false);

	  
	  sign13= new JPanel();
	  sign13.setBackground(Color.gray);
	  sign13.setSize(16,50);
	  sign13.setLocation(635,50);
	  sign13.setVisible(false);
	  
	  sign14= new JPanel();
	  sign14.setBackground(Color.gray);
	  sign14.setSize(16,50);
	  sign14.setLocation(635,180);
	  sign14.setVisible(false);
	  
	  sign15= new JPanel();
	  sign15.setBackground(Color.gray);
	  sign15.setSize(16,50);
	  sign15.setLocation(635,310);
	  sign15.setVisible(false);
	  
	  sign16= new JPanel();
	  sign16.setBackground(Color.gray);
	  sign16.setSize(16,50);
	  sign16.setLocation( 635,440);
	  sign16.setVisible(false);
	  
	  
 
	//i am using panel for my character it'll move while game run
	    panel.setBackground(Color.white);
	    panel.setVisible(false);
		panel.setSize(70,112);
		panel.setLocation(50,470);
		panel.setOpaque(true);
		
		//using this panel i set my game logo
		logopanel.add(logo);
		logopanel.setBackground(Color.white);
		logopanel.setVisible(true);
	    logopanel.setSize(319,165);
	    logopanel.setLocation(200,120);
	    logopanel.setOpaque(false);
		
	  //this is for showing Instructions for game at how to play section 
	     panel4.add(pic1);
	     panel4.setBackground(Color.white);
		 panel4.setVisible(false);
	     panel4.setSize(450,243);
	     panel4.setLocation(0,280);
		
		//to create label how to play text	
		 txt1 = new JLabel("How to play: ");
		 txt1.setFont(new Font("My boli",Font.ITALIC,15));
		 txt1.setSize(300,100);
		 txt1.setLocation(0,350);
		 txt1.setForeground(Color.DARK_GRAY);
		
		//using panel1 i added txt1 label this is how i show how to play text
		panel1=new JPanel();
		panel1.setVisible(true);
		panel1.setSize(750,200);
		panel1.setLocation(0,300);
		panel1.setBackground(Color.LIGHT_GRAY);
		panel1.add(txt1);
	
		panel2=new JPanel();
		panel2.setVisible(false);
		panel2.setSize(280,85);
		panel2.setLocation(0,200);
		panel2.setBackground(Color.black);
		
		panel3=new JPanel();
		panel3.setVisible(false);
		panel3.setSize(350,85);
		panel3.setLocation(430,200);
		panel3.setBackground(Color.black);
		
		//this is for showing press  start button to start the game in how to play section
		panel5=new JPanel();
		panel5.setVisible(false);
		panel5.setSize(300,234);
		panel5.setLocation(450,285);
		panel5.setBackground(Color.black);
		panel5.add(pic2);
		
		//using for show the black line the the up
		panel6=new JPanel();
		panel6.setVisible(true);
		panel6.setSize(750,50);
		panel6.setLocation(0,0);
		panel6.setBackground(Color.black);
		
		////using for show the black line the the bottom
		panel7=new JPanel();
		panel7.setVisible(true);
		panel7.setSize(750,80);
		panel7.setLocation(0,550);
		panel7.setBackground(Color.black);
		
		//cr1 panel i'm using for showing character at choosing section and it's green car
		cr1= new JPanel();
		 cr1.add(char1);
	      cr1.setBackground(Color.white);
		  cr1.setVisible(false);
	     cr1.setSize(180,280);
	    cr1.setLocation(20,60);
	    
	  //cr2 panel i'm using for showing character at choosing section and it's a cartoon character
	    cr2= new JPanel();
		 cr2.add(char2);
	      cr2.setBackground(Color.white);
		  cr2.setVisible(false);
	     cr2.setSize(180,280);
	    cr2.setLocation(250,60);
	  //cr3 panel i'm using for showing character at choosing section and it's yellow car
	    cr3= new JPanel();
		 cr3.add(char3);
	      cr3.setBackground(Color.white);
		  cr3.setVisible(false);
	     cr3.setSize(180,280);
	    cr3.setLocation(500,60);
	    
	  // User can select their favorite character using c1,c2,c3 button
	   c1= new JButton();
	    c1.setSize(20,20);
	    c1.setLocation(100, 375);
	     c1.setBackground(Color.GREEN);
		c1.setFocusable(false);
	   c1.addActionListener(this);
	  c1.setVisible(false);
	  
	  c2= new JButton();
	   c2.setSize(20,20);
	    c2.setLocation(330, 375);
	     c2.setBackground(Color.darkGray);
		c2.setFocusable(false);
	   c2.addActionListener(this);
	  c2.setVisible(false);
	  
	  c3= new JButton();
	   c3.setSize(20,20);
	    c3.setLocation(580, 375);
	     c3.setBackground(Color.yellow);
		c3.setFocusable(false);
	   c3.addActionListener(this);
	  c3.setVisible(false);
			
	  //Now everything add to the main frame
		
	    frame.add(logopanel);
	    frame.add(panel);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(panel4);
		frame.add(panel5);
		frame.add(panel6);
		frame.add(panel7);
		frame.add(pan1);
		frame.add(pan2);
		frame.add(pan3);
		frame.add(pan4);
		frame.add(button);
		frame.add(rbutton);
		frame.add(dbutton);
		frame.add(Cbutton);
		frame.add(hbutton);
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(label4);
		frame.add(label5);
		frame.add(label6);
		frame.add(labelfav);
		frame.add(buttonlbl);
		frame.add(slabel);
		frame.add(ob1);
		frame.add(ob2);
		frame.add(ob3);
		frame.add(ob4);
		frame.add(cr1);
		frame.add(cr2);
		frame.add(cr3);
		frame.add(c1);
		frame.add(c2);
		frame.add(c3);
		frame.add(sign1);
		frame.add(sign2);
		frame.add(sign3);
		frame.add(sign4);
		frame.add(sign5);
		frame.add(sign6);
		frame.add(sign7);
		frame.add(sign8);
		frame.add(sign9);
		frame.add(sign10);
		frame.add(sign11);
		frame.add(sign12);
		frame.add(sign13);
		frame.add(sign14);
		frame.add(sign15);
		frame.add(sign16);
		frame.add(highlabel);
		frame.addKeyListener(cnt);
		frame.setSize(750,620);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setBackground(Color.black);
        frame.setLocationRelativeTo(null);//it shows the frame in the center  of the screen
		 ImageIcon image = new ImageIcon("Character.png");
		frame.setIconImage(image.getImage());
		
		//with the help of ths loop i run my game 
		
	while(true)
	{
		
		if(ssp==1)
			clip.start();
		char o='p';
		char o1='q';
		char o2='r';
		char o3='s';
		//this is for obstacle number one
		if(o=='p')
		{
			int a=ob1.getX();//this will give obstacle 1's x location
			
			int b=ob1.getY();//this will give obstacle 1's y location
			
			//Using this i change the value of moving obstacle x for this obstacle will come faster with the rate of score
			if(b<=580) 
			{
				
			
				if(scr/2>=100)
				{
					if(k)
						setX(5);
				}
				if(scr/2>=300)
				{
					if(k)
						setX(6);
				}
				if(scr/2>=500)
				{
					if(k)
						setX(6);
				}
				if(scr/2>=600)
				{
					if(k)
						setX(7);
				}
				if(scr/2>=1000)
				{
					if(k)
						setX(8);
				}
			ob1.setLocation(a, b+x);
			if(ob1.getY()+24>=panel.getY()&&panel.getX()<=180)// Calculation for when the character touch obstacle 1 if then game over
			{
				System.out.println("Game over");
				//SounCheck(2);
	            ssp=2;
	            clip.stop();
				c=false;//c become false so that we can stop the game runner loop
				
				int x=Score((scr/2));// calling score file to read and store score
				
				//using JOptionPane to show game over custom tex
				JOptionPane.showMessageDialog(msg, "Game Over!!!\nYour Final Score = "+(scr/2)+"\nHigh Score is "+highScore);
				
				//instantiate again for restart
				frame.dispose();
				new RunGame();
			break;
			}
		}
			else
				ob1.setLocation(0,0);// set (0,0) so the obstacle can come after they finish frame
			
		}
		
		//This is for Obstacle 2 and mechanism is same as obstacle one
		if(o1=='q')
		{
			int a=ob2.getX();//this will give obstacle 2's x location
			
			int b=ob2.getY();//this will give obstacle 2's y location
			if(b<=580) {

				if(scr/2>=100)
				{
					if(k)
						setX(5);
				}
				if(scr/2>=300)
				{
					if(k)
						setX(6);
				}
				if(scr/2>=500)
				{
					if(k)
						setX(6);
				}
				if(scr/2>=600)
				{
					if(k)
						setX(7);
				}
				if(scr/2>=1000)
				{
					if(k)
						setX(8);
				}
			ob2.setLocation(a, b+x);
			
			//Calculation for when the character touch obstacle 2
			if(ob2.getY()+24>=panel.getY()&& panel.getX()>=110&&panel.getX()<=370)
			{
				System.out.println("Game over");
				 clip.stop();
				c=false;
				int x=Score((scr/2));
				JOptionPane.showMessageDialog(msg, "Game Over!!!\nYour Final Score = "+(scr/2)+"\nHigh Score is "+highScore);
				frame.dispose();
				new RunGame();
				// rbutton.setVisible(true);
			break;
			}
		}
			else
				ob2.setLocation(180,0);
		}
		
		//This is for Obstacle 3 and mechanism is same as obstacle one
		if(o2=='r')
		{
			int a=ob3.getX();//this will give obstacle 3's x location
			
			int b=ob3.getY();//this will give obstacle 3's x location
			if(b<=580) {

				if(scr/2>=100)
				{
					if(k)
						setX(5);
				}
				if(scr/2>=300)
				{
					if(k)
						setX(6);
				}
				if(scr/2>=500)
				{
					if(k)
						setX(6);
				}
				if(scr/2>=600)
				{
					if(k)
						setX(7);
				}
				if(scr/2>=1000)
				{
					if(k)
						setX(8);
				}
			ob3.setLocation(a, b+x);
			
			//Calculation for when the character touch obstacle 3
			if(ob3.getY()+24>=panel.getY()&& panel.getX()>=300&&panel.getX()<=545)
			{
				System.out.println("Game over");
				 clip.stop();
				c=false;
				int x=Score((scr/2));
				JOptionPane.showMessageDialog(msg, "Game Over!!!\nYour Final Score = "+(scr/2)+"\nHigh Score is "+highScore);
				frame.dispose();
				new RunGame();
			break;
			}
		} 
			else
				ob3.setLocation(370,0);
		}
		
		//This is for Obstacle 4 and mechanism is same as obstacle one
		if(o3=='s')
		{
			int a=ob4.getX();//this will give obstacle 4's x location
			
			int b=ob4.getY();//this will give obstacle 4's x location
			if(b<=580) {

				if(scr/2>=100)
				{
					if(k)
						setX(5);
				}
				if(scr/2>=300)
				{
					if(k)
						setX(6);
				}
				if(scr/2>=500)
				{
					if(k)
						setX(6);
				}
				if(scr/2>=600)
				{
					if(k)
						setX(7);
				}
				if(scr/2>=1000)
				{
					if(k)
						setX(8);
				}
			ob4.setLocation(a, b+x);
			
			//Calculation for when the character touch obstacle 4
			if(ob4.getY()+24>=panel.getY()&& panel.getX()>=480)
			{
				System.out.println("Game over");
				 clip.stop();
				c=false;
				int x=Score((scr/2));
				JOptionPane.showMessageDialog(msg, "Game Over!!!\nYour Final Score = "+(scr/2)+"\nHigh Score is "+highScore);
				frame.dispose();
				new RunGame();
				//rbutton.setVisible(true);
			break;
			}
		}
			else
			ob4.setLocation(555,0);
		}
		int a1,b1;
	      a1=sign1.getX();
	      b1=sign1.getY();
	      if(b1>=550)
	    	  sign1.setLocation(80,0);
	      else
	    	  sign1.setLocation(a1, b1+x);
	      
	      int a2,b2;
	      a2=sign2.getX();
	      b2=sign2.getY();
	      if(b2>=550)
	    	  sign2.setLocation(80,0);
	      else
	    	  sign2.setLocation(a2, b2+x);
	      
	      int a3,b3;
	      a3=sign3.getX();
	      b3=sign3.getY();
	      if(b3>=550)
	    	  sign3.setLocation(80,0);
	      else
	    	  sign3.setLocation(a3, b3+x);
	      
	      int a4,b4;
	      a4=sign4.getX();
	      b4=sign4.getY();
	      if(b4>=550)
	    	  sign4.setLocation(80,0);
	      else
	    	  sign4.setLocation(a4, b4+x);
	      
	      int a5,b5;
	      a5=sign5.getX();
	      b5=sign5.getY();
	      if(b5>=550)
	    	  sign5.setLocation(265,0);
	      else
	    	  sign5.setLocation(a5, b5+x);
	      
	      int a6,b6;
	      a6=sign6.getX();
	      b6=sign6.getY();
	      if(b6>=550)
	    	  sign6.setLocation(265,0);
	      else
	    	  sign6.setLocation(a6, b6+x);
	      
	      int a7,b7;
	      a7=sign7.getX();
	      b7=sign7.getY();
	      if(b7>=550)
	    	  sign7.setLocation(265,0);
	      else
	    	  sign7.setLocation(a7, b7+x);
	      
	      int a8,b8;
	      a8=sign8.getX();
	      b8=sign8.getY();
	      if(b8>=550)
	    	  sign8.setLocation(265,0);
	      else
	    	  sign8.setLocation(a8, b8+x);
	      
	      int a9,b9;
	      a9=sign9.getX();
	      b9=sign9.getY();
	      if(b9>=550)
	    	  sign9.setLocation(450,0);
	      else
	    	  sign9.setLocation(a9, b9+x);


	      int a10,b10;
	      a10=sign10.getX();
	      b10=sign10.getY();
	      if(b10>=550)
	    	  sign10.setLocation(450,0);
	      else
	    	  sign10.setLocation(a10, b10+x);
	      
	      int a11,b11;
	      a11=sign11.getX();
	      b11=sign11.getY();
	      if(b11>=550)
	    	  sign11.setLocation(450,0);
	      else
	    	  sign11.setLocation(a11, b11+x);
	      
	      int a12,b12;
	      a12=sign12.getX();
	      b12=sign12.getY();
	      if(b12>=550)
	    	  sign12.setLocation(450,0);
	      else
	    	  sign12.setLocation(a12, b12+x);
	      
	      int a13,b13;
	      a13=sign13.getX();
	      b13=sign13.getY();
	      if(b13>=550)
	    	  sign13.setLocation(635,0);
	      else
	    	  sign13.setLocation(a13, b13+x);
	      
	      int a14,b14;
	      a14=sign14.getX();
	      b14=sign14.getY();
	      if(b14>=550)
	    	  sign14.setLocation(635,0);
	      else
	    	  sign14.setLocation(a14, b14+x);
	      
	      int a15,b15;
	      a15=sign15.getX();
	      b15=sign15.getY();
	      if(b15>=550)
	    	  sign15.setLocation(635,0);
	      else
	    	  sign15.setLocation(a15, b15+x);
	      
	      int a16,b16;
	      a16=sign16.getX();
	      b16=sign16.getY();
	      if(b16>=550)
	    	  sign16.setLocation(635,0);
	      else
	    	  sign16.setLocation(a16, b16+x);
	      
	      
	      
	
		//If we set a thread sleep the thread maybe interrupted
		//so sleep method have to check exception
		//and we need sleep to we can control the obstacle's speed
		try {
		    Thread.sleep(30);
		} 
		catch (Exception ie) // Can be Interrupted Exception
		{
		    
		}
		
		// if k is true that's mean game isn't over so score will increase
		if(k)
		scr+=1;
		if(!c)
			clip.stop();
		}
	}
	
	
	//This is for ActionListener
	@Override
	public void actionPerformed(ActionEvent e) 
	    {
	
		
	   //if start button is pressed
		if(e.getSource()==button)
		       {
			label.setVisible(false);
			 button.setVisible(false);
			 label1.setVisible(false);
			 label2.setVisible(false);
			 label3.setVisible(false);
			 label5.setVisible(false);
			 
			 pan1.setVisible(true);
			 pan2.setVisible(true);
			 pan3.setVisible(true);
			 pan4.setVisible(true);
			 
			 panel.setVisible(true);
			 panel1.setVisible(false);
			 panel2.setVisible(false);
			 panel3.setVisible(false);
			 panel4.setVisible(false);
			 panel5.setVisible(false);
			 panel6.setVisible(false);
			 panel7.setVisible(false);
			 
			 label4.setVisible(false);
		      rbutton.setVisible(false);
		      Cbutton.setVisible(false);
		      //backbutton.setVisible(false);
			 
			 ob1.setVisible(true);
			 ob2.setVisible(true);
			 ob3.setVisible(true);
			 ob4.setVisible(true);
			 
			 x=4;
			 k=true;
			 
			 carlab.setVisible(true);
			 carlab1.setVisible(true);
			 carlab2.setVisible(true);
			  dbutton.setVisible(false);
		      label6.setVisible(false);
		      
		      highlabel.setVisible(false);
				 hbutton.setVisible(false);
				 slabel.setVisible(false);
				 
				// clip.start();
				 ssp=1;
				/* try {
					SounCheck(ssp);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
		
				 sign1.setVisible(true);
				 sign2.setVisible(true);
				 sign3.setVisible(true);
				 sign4.setVisible(true);
				 sign5.setVisible(true);
				 sign6.setVisible(true);
				 sign7.setVisible(true);
				 sign8.setVisible(true);
				 sign9.setVisible(true);
				 sign10.setVisible(true);
				 sign11.setVisible(true);
				 sign12.setVisible(true);
				 sign13.setVisible(true);
				 sign14.setVisible(true);
				 sign15.setVisible(true);
				 sign16.setVisible(true);
		   }
		 //if start how to play button is pressed
		if(e.getSource()==rbutton)
	       {
		       panel2.setVisible(true);
		       panel3.setVisible(true);
		       panel4.setVisible(true);
		       panel5.setVisible(true);
		       label4.setVisible(false);
		       rbutton.setVisible(false);
		       Cbutton.setVisible(false);
		      // backbutton.setVisible(true);
		       buttonlbl.setVisible(false);
		       button.setVisible(true);
		       panel.add(carlab2);
		       highlabel.setVisible(false);
				 hbutton.setVisible(false);
				 logopanel.setVisible(false);
	       }
		 //if start character choose  button is pressed
		if(e.getSource()==Cbutton)
		{
			 label.setVisible(true);
			 button.setVisible(false);
			 label1.setVisible(false);
			 label2.setVisible(false);
			 label3.setVisible(false);
			 label5.setVisible(false);
			 panel1.setVisible(false);
			 panel2.setVisible(false);
			 panel3.setVisible(false);
			 panel4.setVisible(false);
			 Cbutton.setVisible(false);
			 label4.setVisible(false);
		     rbutton.setVisible(false);
		     cr1.setVisible(true);
		     cr2.setVisible(true);
		     cr3.setVisible(true);
		     c1.setVisible(true);
		     c2.setVisible(true);
	         c3.setVisible(true);
	        dbutton.setVisible(false);
  		      label6.setVisible(false);
		      buttonlbl.setVisible(false);
		      labelfav.setVisible(true);
		      logopanel.setVisible(false);
		}
		 //if any of the character choose button is pressed
		if(e.getSource()==c1||e.getSource()==c2||e.getSource()==c3)
		{
			 label.setVisible(false);
			 button.setVisible(true);
			 label1.setVisible(true);
			 label2.setVisible(true);
			 label3.setVisible(true);
			 label5.setVisible(true);
			 panel1.setVisible(true);
			 panel2.setVisible(false);
			 panel3.setVisible(false);
			 panel4.setVisible(false);
			 Cbutton.setVisible(true);
			 label4.setVisible(true);
		     rbutton.setVisible(true);
		      cr1.setVisible(false);
		      cr2.setVisible(false);
		      cr3.setVisible(false);
		      c1.setVisible(false);
		      c2.setVisible(false);
		      c3.setVisible(false);
		      button.setVisible(true);
			    Cbutton.setVisible(false);
			    label5.setVisible(false);
			    rbutton.setVisible(true);
			    label4.setVisible(true);
			    labelfav.setVisible(false);
			    highlabel.setVisible(true);
			    hbutton.setVisible(true);

		    //If green car is selected
			    if(e.getSource()==c1) {
			    panel.add(carlab);
			    bg=1;
			    panel.setOpaque(false);
		    }
			  
			//If cartoon is selected
		    else if(e.getSource()==c2) {
			    panel.add(carlab1);	
		    }
		    
			  //If yellow car is selected
		    else {
			    panel.add(carlab2);	
			    panel.setOpaque(false);
		    }
		    
		}
		//if default button is pressed
		if(e.getSource()==dbutton)
		{
			panel.add(carlab2);
			Cbutton.setVisible(false);
			dbutton.setVisible(false);
		    label6.setVisible(false);
			label5.setVisible(false);
			button.setVisible(true);
			buttonlbl.setVisible(false);
			label4.setVisible(false);
			rbutton.setVisible(true);
			label4.setVisible(true);
			 highlabel.setVisible(true);
			 hbutton.setVisible(true);
			 logopanel.setVisible(false);
			 panel.setOpaque(false);
		}
		//If high score button is pressed
		if(e.getSource()==hbutton)
		{
			 slabel.setVisible(true);
			 logopanel.setVisible(false);
		}
   }
	
}




		
	
	
	

