
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Maze extends JPanel implements ActionListener{
	private int width, height, rectWidth, rectHeight;
	private boolean[][] maze;
	
	 Stack<Point> stack = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();
	static Point current;
	boolean flag=false;
	
	public Maze(int width, int height) {
		// Make dimensions odd
		width -= width % 2;
		width++;
		height -= height % 2;
		height++;
		timer.start();
		this.width = width;
		this.height = height;
		maze = new boolean[height][width];
		
//		Timer timer= new Timer(10, this);
//		timer.start();
	}
	
	public int[][] generate() {
		
	int[][] mazee = new int[height][width];
		// Initialize maze
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = !(i % 2 == 1 && j % 2 == 1);
			}
		}

		// Generate maze
		Random random = new Random();
		for (int k = 1; k < width; k += 2) {
			for (int m = 1; m < height; m += 2) {
				boolean south = random.nextInt(2) == 1;

				if (m == height - 2) {
					south = false;
				}
				if (k == width - 2) {
					south = true;
				}
				if (k == width - 2 && m == height - 2) {
					break;
				}

				if (south) {
					maze[m + 1][k] = false;
				} else {
					maze[m][k + 1] = false;
				}
			}
		}

		maze[0][1] = false;
		maze[height - 1][width - 2] = false;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				  if( maze[i][j])
					  mazee[i][j]=1; 
				  else
					  mazee[i][j]=0;
			}
		}
//		mazee[0][0] =0;
		mazee[height - 1][width - 2] = 9;
	
		return mazee;
	}
	ArrayList<Integer> x1= new ArrayList<>();
	ArrayList<Integer> y1= new ArrayList<>();
	Timer timer= new Timer(1,this);

	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);


		// setBackground(Color.BLACK);S

		// Calculate dimensions of rectangles
		rectWidth = getWidth() / width;
		rectHeight = getHeight() / height;

		// Draw maze
		// g.setColor(Color.WHITE);
//		if(!flag) {
			for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (!maze[i][j]) {
					g.setColor(new Color(70, 78, 91));
					g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);

					// g.drawRect(j * rectWidth, i * rectHeight, j, i);
				} else {
					g.setColor(new Color(0,0,0));
					g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
				}
			g.setColor(Color.BLACK);
				g.drawRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
			}
			g.setColor(new Color(70, 78, 91));
			g.fillRect(0, 0, rectWidth, rectHeight);
			g.setColor(Color.RED);
			g.fillRect((width - 2) * rectWidth, (height - 1) * rectHeight, rectWidth, rectHeight);
			
		}
//		}
		
		
		 repaint();
		 
		 if(flag) {
			 
			 
			 for(int i=0;i<x1.size();i++) {
				  g.setColor(Color.MAGENTA);
					g.fillRect(x1.get(i) * rectWidth, y1.get(i) *rectHeight, rectWidth, rectHeight);
					
					g.setColor(Color.BLACK);
					g.drawRect(x1.get(i) * rectWidth, y1.get(i) *rectHeight, rectWidth, rectHeight);
					 repaint();
					 
					 new Thread(new Runnable() {
				            @Override
				            public void run() {
				                try {
				                	
				                	
				                    Thread.sleep(1000);
				                  
									
									
				                } catch (InterruptedException ex) {
				                    ex.printStackTrace();
				                }
				            }
				        }).start();
					
					
			 }
		
		 }
			 
		
		
//		for( i=0;i<Solver.stack2.size();i++) {
//			
//			 	 g.setColor(Color.LIGHT_GRAY);
//			 g.fillRect(rectWidth*Solver.stack2.get(i).x, rectHeight*Solver.stack2.get(i).y, rectWidth, rectHeight);
////			 try {
////				Thread.sleep(500);
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////			 timer.start();
//			
//			repaint(); }
		
		// Draw start and finish	
//      for(int j =0;j<Solver.stack.size();j++) {
//    	   g.setColor(Color.GREEN);
//	       g.fillRect(rectWidth*Solver.stack.get(j).x, rectHeight*Solver.stack.get(j).y, rectWidth, rectHeight);
//		
////		 timer.start();
//		 repaint();
//      }
	 	
     

	}
	int k=0;
	  @Override
	public void actionPerformed(ActionEvent e) {
		  x1.add(0, 0);
		  y1.add(0, 0);
		  
		  if(flag) { 
			 for(int i=0, j=0;i<stack2.size();i++,j++) {
				// System.out.println(stack2.get(i));
				 x1.add(j,this.stack2.get(i++) ) ;
				 y1.add(j,this.stack2.get(i));  
				 
		
			
			repaint();
			
			
			
			  } 
			 
		  }
		  
	}
	  	
	public  boolean solve(int[][] maze, int x, int y,boolean flag) throws InterruptedException {
		rectHeight = y;
		rectWidth = x;
		
		Point point = new Point(y, x);
		
		
		
		
		

//		System.out.println(maze[y][x]);
		if (maze[y][x] == 9) {
			point.x = x;
			point.y = y;
			this.flag=flag;
			stack.push(point);
			return true;
		}

		if (maze[y][x] == 0) {
			current=new Point();
			current.x = x;
			current.y = y;
//			tm.start();
			stack2.push(x);
			stack2.push(y);
			System.out.println(stack2);
			//Thread.sleep(500);
			maze[y][x] = 2;

			int dx = -1;
			int dy = 0;
			if (x != 0)
				if (solve(maze, x + dx, y + dy,flag)) {
					point.x = x;
					point.y = y;
					stack.push(point);
					return true;
				}

			dx = 1;
			dy = 0;
			if (solve(maze, x + dx, y + dy,flag)) {
				point.x = x;
				point.y = y;
				stack.push(point);
				return true;
			}

			dx = 0;
			dy = -1;
			if (y != 0)
				if (solve(maze, x + dx, y + dy,flag)) {
					point.x = x;
					point.y = y;
					stack.push(point);
					return true;
				}

			dx = 0;
			dy = 1;
			if (solve(maze, x + dx, y + dy,flag)) {
				point.x = x;
				point.y = y;
				stack.push(point);
				return true;
			}
		}

		return false;
	}



}
