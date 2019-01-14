import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.border.Border;

import java.lang.*;


class SnakeLadder extends Canvas implements ActionListener , MouseListener
{
  JFrame fr;
  JPanel pa;
  JButton b1,b2,b3,b4,b5;
  JTextField t1,t2,t4,t5,t6;
  static JTextField t3; 
  ImageIcon snakeimage;
  ImageIcon titleimg;
  ImageIcon homer,homey,homeb,homeg; //images at home position
  Graphics gr;
 
  JLabel label2 ;//dice image

  JLabel l1,l2,l3,l4,l5;
  JLabel lb1,lb2;
  Thread th= new Thread();
  Border bd;

  private int delay=100000;//to create a delay when snake bit and take ladder 
  
  private int open_status[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
  private int syn=1;//this is used to set the synchronization between the button b5 and b1,b2,b3 and b4 
  private int bp=0;//button pressed
  private int chance=0;// it tell which color piece chance is this
  private Random random= new Random();
  private int dice=0;//to store the random no.
  private int flag[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
  	 //red(0-3) yellow(4-7) blue(8-11) green(12-15)
  private int rc=27,yc=26,bc=25,gc=24;
  
  private int open_areax[]={30,90,150,210,270,330,390,450,510,570,630,690,750,810,870,930};
  private int open_areay=690;
  
  private int x[]={36,36,133,230,327,424,521,618,715,812,909,909,812,715,618,521,424,327,230,133,36,36,133,230,327,424,521,618,715,812,909,909,812,715,618,521,424,327,230,133,36,36,133,230,327,424,521,618,715,812,909,909,812,715,618,521,424,327,230,133,36,36,133,230,327,424,521,618,715,812,909,909,812,715,618,521,424,327,230,133,36,36,133,230,327,424,521,618,715,812,909,909,812,715,618,521,424,327,230,133,36};
  
  private int y[]={690,634,634,634,634,634,634,634,634,634,634,566,566,566,566,566,566,566,566,566,566,498,498,498,498,498,498,498,498,498,498,430,430,430,430,430,430,430,430,430,430,362,362,362,362,362,362,362,362,362,362,294,294,294,294,294,294,294,294,294,294,226,226,226,226,226,226,226,226,226,226,158,158,158,158,158,158,158,158,158,158,90,90,90,90,90,90,90,90,90,90,22,22,22,22,22,22,22,22,22,22};
	  
     public SnakeLadder() 
      {
          Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
	
                 	//image of dice
		  label2 = new JLabel();
          label2.setIcon(new ImageIcon("snakeimage\\04dice.gif")); //your image here
          label2.setBounds(250,210,125,125); 

	           // for getting the size of screen
	      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//It returns the size of the windown on which program is running 
	   
	     int screenHeight = screenSize.height;//768
         int screenWidth = screenSize.width;//1366
         
        
		
		bd=BorderFactory.createEmptyBorder();
      
   	    fr= new JFrame("Snake Ladder");
		fr.setResizable(false);    
        pa= new JPanel();
	    b1= new JButton("1");
		b2= new JButton("2");
		b3= new JButton("3");
		b4= new JButton("4");
		b5= new JButton("dice");

		t1=new JTextField(15);// written dice
		
		t2=new JTextField(15);// textfield for home 
		
		t3=new JTextField(15);//text field for next chance piece name display
		
		t4=new JTextField(15);// textfield for home 
		
		t5=new JTextField(15);// textfield for home 
		
		t6=new JTextField(15);// textfield for home 
		
		
		
		
		
		
		
		
		
		l1= new JLabel("LEADER BOARD",SwingConstants.CENTER);		
		l2= new JLabel();	//red	
		l3= new JLabel();	//yellow	
		l4= new JLabel();	//blue	
		l5= new JLabel();   //green
		l2.setIcon(new ImageIcon("snakeimage\\red.png"));
		l3.setIcon(new ImageIcon("snakeimage\\yellow.png"));
		l4.setIcon(new ImageIcon("snakeimage\\blue.png"));
		l5.setIcon(new ImageIcon("snakeimage\\green.png"));
		
		lb1= new JLabel("Choose",SwingConstants.CENTER);		
		lb2= new JLabel("Piece no.",SwingConstants.CENTER);
      
		
		

		
	    fr.setSize(screenWidth,screenHeight-20);
	    pa.setLayout(null);
	    fr.add(pa);
	    
		
		//b6.setBounds(25,275,100,25);
		b1.setBounds(175,360,100,50);
		b2.setBounds(275,360,100,50);
		b3.setBounds(175,410,100,50);
		b4.setBounds(275,410,100,50);
		b5.setBounds(25,175,200,70);
		
		
		
		 lb1.setBounds(5,370,175,30);
         lb2.setBounds(5,400,175,30);	     
		 lb1.setFont(new Font("Comic Sans MS",1,30));
		 lb2.setFont(new Font("Comic Sans MS",1,30));
		 t1.setBounds(100,250,50,75);
		 t1.setFont(new Font("Comic Sans MS",1,50));
		 t1.setBorder(bd);
         t3.setBackground(Color.white);
	   	 t3.setFont(new Font("Comic Sans MS",1,24));
		 t3.setBorder(bd); 
		//t2.setBounds(25,75,150,25);
		 t3.setBounds(20,480,370,30);
		//t4.setBounds(25,175,150,25);
         l1.setBounds(75,520,250,30);
		 l1.setBorder(bd);
		 l2.setBorder(bd);
		 l3.setBorder(bd);
		 l4.setBorder(bd);
		 l5.setBorder(bd);
		 l1.setFont(new Font("Comic Sans MS",1,24));
		
		 l2.setBounds(25,580,40,40);
		 l3.setBounds(210,580,40,40);
		 l4.setBounds(25,650,40,40);
		 l5.setBounds(210,650,40,40);
		
		
		 t2.setBounds(70,580,130,40);
		 t4.setBounds(70,650,130,40);
		 t5.setBounds(255,580,130,40);
		 t6.setBounds(255,650,130,40);
	   

	     t2.setEnabled(false);
	     t4.setEnabled(false);
	     t5.setEnabled(false);
	     t6.setEnabled(false);
		 t2.setText("hello");
		
		 t2.setBorder(bd);
		 t4.setBorder(bd);
		 t5.setBorder(bd);
		 t6.setBorder(bd);
		
		 t2.setFont(new Font("Comic Sans MS",1,24));
		 t4.setFont(new Font("Comic Sans MS",1,24));
		 t5.setFont(new Font("Comic Sans MS",1,24));
		 t6.setFont(new Font("Comic Sans MS",1,24));
		
		
		
		
		
	 	
		
		
		
		
         pa.add(l1);
		 pa.add(l2);
		 pa.add(l3);
		 pa.add(l4);	
		 pa.add(l5);
		//pa.add(l6);
		//pa.add(l7);
		//pa.add(l8);	    
        //for(int j=0;j<12;j++)
	    // pa.add(tf[j]);
	
		 b1.addActionListener(this);
		 b2.addActionListener(this);
		 b3.addActionListener(this);
		 b4.addActionListener(this);
		
         b5.addActionListener(this);
		
		 pa.add(b1);
		 pa.add(b2);
		 pa.add(b3);
		 pa.add(b4);	
		 pa.add(b5);
		 pa.add(lb1);
	     pa.add(lb2);
		
	   //pa.add(b6);
	 	 pa.add(t1);
		 pa.add(t2);
		 pa.add(t3);
		 pa.add(t4);
		 pa.add(t5);
		 pa.add(t6);
		
		 pa.add(this);
	     pa.setBackground(Color.white);
		
	     titleimg=new ImageIcon("snakeimage\\img6.jpg");
	    
	     JLabel label = new JLabel();
         label.setIcon(new ImageIcon("snakeimage\\titleimg2.jpg")); //your image here
         label.setBounds(80,10,236,155); 
	     pa.add(label);
        
	     pa.add(label2);
	     //label2.setVisible(false);
	     //pa.add(lb2);
	 

	     b1.setBorder(bd);
	     b2.setBorder(bd);
	     b3.setBorder(bd);
	     b4.setBorder(bd);
	     b5.setBorder(bd);
 
         b1.setBackground(Color.white);
         b2.setBackground(Color.white);  
         b3.setBackground(Color.white);	
         b4.setBackground(Color.white);
         b5.setBackground(Color.white);
         Font f= new Font("Calibri",0,26);
         b5.setFont(new Font("Bauhaus 93",1,44));
 
	  
	  
	     b1.setFocusPainted(false);
	     b2.setFocusPainted(false);
	     b3.setFocusPainted(false);
	     b4.setFocusPainted(false);
	     b5.setFocusPainted(false);
	 


	     b1.addMouseListener(this);
	     b2.addMouseListener(this);
	     b3.addMouseListener(this);
	     b4.addMouseListener(this);
	     b5.addMouseListener(this);
	     
	  
	  
	  
	
         b1.setFont(f);
         b2.setFont(f);  
         b3.setFont(f);
         b4.setFont(f);
         b5.setFont(new Font("Bauhaus 93",1,44));
 


	
	     fr.setVisible(true);
	     fr.setDefaultCloseOperation(2);
	 }
	 
	 
	 

  	  public void paint(Graphics g)
	  {
                
				for(int a=0;a<16;a++)
    			{   if(a<4)
    			    g.setColor(Color.red);
					if(a>=4&&a<8)
    			    g.setColor(Color.yellow);
					if(a>=8&&a<12)
    			    g.setColor(Color.blue);
					if(a>=12)
    			    g.setColor(Color.green);
					
					
					g.drawOval(open_areax[a],open_areay,20,20);
			    
				}
				
				
                int p=chance;
		        p++;
		        if(p>4)
		        {
			       p-=4;
		        }
          
		        switch(p)
		        {   case 1:  t3.setForeground(Color.red);
		                     t3.setText("RED PLAYER NEXT TURN");
		  	                 break;
                    case 2:  t3.setForeground(Color.yellow);
		                     t3.setText("YELLOW PLAYER NEXT TURN");
		  	                 break;
                    case 3:  t3.setForeground(Color.blue);
		                     t3.setText("BLUE PLAYER NEXT TURN");
		  	                 break;
                    case 4:  t3.setForeground(Color.green);
		                     t3.setText("GREEN PLAYER NEXT TURN");
		  	                 break;
          
		  
		        }
		 
		   
	     //this is use for using images on canvas	  
	            snakeimage=new ImageIcon("snakeimage\\img6.jpg");
	            snakeimage.paintIcon(this,g,0,0);
	     
		   
		 
			    g.setColor(Color.red);				
                set(g,0,rc);
			
		        g.setColor(Color.yellow);				
			    set(g,4,yc);
			
			    g.setColor(Color.blue);
			    set(g,8,bc);
			
			    g.setColor(Color.green);
			    set(g,12,gc);
			
		        for_ladder_snake();//this fun works in case of snake bit and taking ladder
           
		        beat();
 
	
	  }
	 
	  
	  
	  

	
   


      public void actionPerformed(ActionEvent e)
	  {	
		  if(e.getSource()==b5)
	  	  {  
	          chance++;
			
			  //at the first call of paint function it becomes 0 so it prevent the call of below function without the hiting the button that's the reason behing so I initialis chance to -1  
              if(chance>4)
			  chance-=4;	
        	  syn=0;	 
			  dice=random.nextInt(6);
			  dice++;
	          t1.setText(String.valueOf(dice));		
		 
		  } 
		  press(e);			
	 
	 
	  }
	 
	
	
	
	public void check()
	{
		 
		 if(chance==1)
		 {     	
	            if(open_status[bp]==1)   
		        flag[0+bp]+=dice;
		        else{
					  if(open_status[bp]==0&&dice==6)
					  {open_status[bp]=1;
				      }
					  else{if(open_status[bp]==0&&dice!=6)
					       syn=0;
					      }
			  	    }
		}  
		 if(chance==2)
		{     	if(open_status[4+bp]==1)   
		          flag[4+bp]+=dice;
		        else{
					  if(open_status[4+bp]==0&&dice==6)
			            open_status[4+bp]=1;
	         	      else{if(open_status[bp]==0&&dice!=6)
			               syn=0;
				          }          
				}
		}     
	    if(chance==3)
		 {     	if(open_status[8+bp]==1)   
		          flag[8+bp]+=dice;
		        else{
					if(open_status[8+bp]==0&&dice==6)
			           open_status[8+bp]=1;
			        else{ if(open_status[bp]==0&&dice!=6)
			              syn=0;
					    }
				    }						
		}  
		if(chance==4)
         {     	if(open_status[12+bp]==1)   
		          flag[12+bp]+=dice;
		        else{
					if(open_status[12+bp]==0&&dice==6)
			           open_status[12+bp]=1;
			        else{if(open_status[bp]==0&&dice!=6)
			             syn=0;
					    }
				    }
		}  	
	
	}
	
	
	
	
	
	
	public void set(Graphics g,int num,int r)
	{      for(int a=0;a<=3;a++)
			{       if(open_status[a+num]==1&&(flag[a+num]!=0))
			      	   g.fillOval(x[flag[a+num]],y[flag[a+num]],r,r);	
                    if(open_status[a+num]==1&&(flag[a+num]==0))		
				       g.fillOval(open_areax[a+num],open_areay,r,r);	
                    if(open_status[a+num]==1&&(flag[a+num]==100))
				    {  g.fillOval(x[flag[a+num]],y[flag[a+num]],r,r);	
                       try
					      {
						    Thread.sleep(10000);
						
					      }
				        catch(InterruptedException e)
				          {}
				        open_status[a+num]=2;
				    }
                    if(open_status[a+num]==2)
					{ g.fillOval(open_areax[a+num],open_areay,20,20);
				      
					}
				  
				   
				  
				  
			}	
	}		

	
	
	public void for_ladder_snake()
	{   
			
			
	for(int pseudo=0;pseudo<16;pseudo=pseudo+4)		
	{
		switch(flag[pseudo+bp])
		{
			
			
			case 23: flag[pseudo+bp]=17;
			          System.out.print("enter in for_ladder_snake method");
					  try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;
			case 45: flag[pseudo+bp]=5;
			System.out.print("enter in for_ladder_snake method");
			          try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;
			case 52: flag[pseudo+bp]=33;
			System.out.print("enter in for_ladder_snake method");
			           try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;	
			
			case 67: flag[pseudo+bp]=23;
			System.out.print("enter in for_ladder_snake method");
			           try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;
			case 90: flag[pseudo+bp]=50;
			System.out.print("enter in for_ladder_snake method");
			           try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;
			case 99: flag[pseudo+bp]=24;
			System.out.print("enter in for_ladder_snake method");
			           try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;
			case 8: flag[pseudo+bp]=29;
			System.out.print("enter in for_ladder_snake method");
			           try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;
			case 22: flag[pseudo+bp]=6;
			System.out.print("enter in for_ladder_snake method");
			          try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;
			case 54: flag[pseudo+bp]=68;
			System.out.print("enter in for_ladder_snake method");
			          try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;
			case 65: flag[pseudo+bp]=97;
			System.out.print("enter in for_ladder_snake method");
			           try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;
			case 72: flag[pseudo+bp]=93;
			System.out.print("enter in for_ladder_snake method");
			             try{
                            th.sleep(1000);
		                    }
		                    catch(InterruptedException e)
		                    {}
					   repaint();
					   break;
		}
		
	 }
	
	}
	
	
	
	public void beat()
	{
		
		
		if(chance==1&&(flag[0+bp]!=0))
		{   for(int a=4;a<16;a++)
			{	if(flag[0+bp]==flag[a])
					{
						flag[a]=0;
						open_status[a]=0;
						 repaint();
   	                     chance--;
			        }	
			}
			
	    }
		
		
		if(chance==2&&(flag[4+bp]!=0))
		{   for(int a=0;a<16;a++)
			{	
		           if(a==4||a==5||a==6||a==7)
					   continue;
		          
				   if(flag[4+bp]==flag[a])
					{
						flag[a]=0;
						open_status[a]=0;
			                	 repaint();
                                 chance--;
					        
					}
	        }		
	
    	 
		}
		
		if(chance==3&&(flag[8+bp]!=0))
		{   for(int a=0;a<16;a++)
			{	
		           if(a==8||a==9||a==10||a==11)
					   continue;
		          
				   if(flag[8+bp]==flag[a])
					{
						flag[a]=0;
						open_status[a]=0;
			            repaint();
					    chance--;
					 }
	        }		
	
    	   
		}
		
		if(chance==4&&(flag[12+bp]!=0))
		{   for(int a=0;a<12;a++)
			{	
		           
				   if(flag[12+bp]==flag[a])
					{
						flag[a]=0;
						open_status[a]=0;
			            repaint();
					    chance--;
					
					}
	        }		
	
 
		}
		
		
		
		
		
	}
	
		
  


    public static void main(String str[])
    {
	      SnakeLadder sn= new SnakeLadder();
	      sn.setBounds(396,0,970,720);//this is the bound for Canvas
		  sn.setBackground(Color.pink);//this sets the color of Canvas
			 
	  
	
	}
	
	
	public void mouseEntered(MouseEvent ms)
	{
		if(ms.getSource()==b1)
		b1.setBackground(Color.lightGray);
        if(ms.getSource()==b2)
	    b2.setBackground(Color.lightGray);  
        if(ms.getSource()==b3)     
	    b3.setBackground(Color.lightGray);	
        if(ms.getSource()==b4)
	    b4.setBackground(Color.lightGray);
        if(ms.getSource()==b5)
	    b5.setBackground(Color.lightGray);
	
	}
	
	
	public void mouseExited(MouseEvent ms)
	{
		      
      b1.setBackground(Color.white);
      b2.setBackground(Color.white);  
      b3.setBackground(Color.white);	
      b4.setBackground(Color.white);
      b5.setBackground(Color.white);
      
	}
	 public void mousePressed(MouseEvent ms)
	{
		
      
	} 
	 public void mouseClicked(MouseEvent ms)
	{   if(ms.getSource()==b5)
		{ 
	      label2.setVisible(true);
	       
	
		}
	
	}   
	
	
	public void mouseReleased(MouseEvent ms)
	{
		
	}
	
	  public void press(ActionEvent e)
	   {
		   
		   
		   int s;
		   s=(chance-1)*4;
		   if((open_status[0+s]!=0)||(open_status[1+s]!=0)||(open_status[2+s]!=0)||(open_status[3+s]!=0)||(dice==6))
	    	{	 
		      if(e.getSource()==b1&&syn==0)
				{
				  syn=1;
				  bp=0;
				  check();
				  repaint();
                  if(dice==6)
					  chance--;
				}
				
				if(e.getSource()==b2&&syn==0)
				{
				syn=1;
				bp=1;
				check();
				      
				repaint();
    	        if(dice==6)
					  chance--;
				
				}	
				
				if(e.getSource()==b3&&syn==0)
				{
				syn=1;
				bp=2;
				check();
				     
				repaint();
				if(dice==6)
					  chance--;
    	        
				}	
				
				
				if(e.getSource()==b4&&syn==0)
				{
				
				syn=1;
				bp=3;
				check();
			         
				repaint();
				if(dice==6)
                       chance--;
				
    	        }			
			} 
		   else
		   {
			   repaint();
		   }
	
	
	}
	
	
	
	
	
	
	
	}





