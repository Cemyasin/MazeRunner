import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;

public class firstMaze extends JPanel implements ActionListener{
	Stack<Point> stack = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();
	Stack<Point> stack3 = new Stack<>();
	
	private int width, height, rectWidth, rectHeight;
	private boolean[][] maze;
	static Point current;
	boolean flag = false;
	private boolean solution = false, clear = false;
	int[][] mazee;
	Timer timer = new Timer(1, this);
	
	public void load(int width, int height,int[][] mazeee) {
		width -= width % 2;
		width++;
		height -= height % 2;
		height++;
		timer.start();
		this.width = width;
		this.height = height;
		maze = new boolean[height][width];
		mazee=new int[height][width];
		mazee = mazeee;
		rectWidth = getWidth() / width;
		rectHeight = getHeight() / height;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Calculate dimensions of rectangles
		

		if (!clear) {
			for (int i = 0; i < height-1; i++) {
				for (int j = 0; j < width-1; j++) {
					if (mazee[i][j]==0) {
						g.setColor(new Color(70, 78, 91));
						g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);

						// g.drawRect(j * rectWidth, i * rectHeight, j, i);
					} else {
						g.setColor(new Color(0, 0, 0));
						g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
					}
					g.setColor(Color.BLACK);
					g.drawRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
				}

			}

			repaint();

			if (flag) {
				g.setColor(Color.GREEN);
				g.fillRect(0, 0, rectWidth, rectHeight);
				g.setColor(Color.RED);
				g.fillRect((width - 2) * rectWidth, (height - 1) * rectHeight, rectWidth, rectHeight);

				for (int i = 0; i < stack3.size() - 1; i++) {
					g.setColor(Color.MAGENTA);
					g.fillRect(stack3.get(i).x * rectWidth, stack3.get(i).y * rectHeight, rectWidth, rectHeight);

					g.setColor(Color.BLACK);
					g.drawRect(stack3.get(i).x * rectWidth, stack3.get(i).y * rectHeight, rectWidth, rectHeight);
					repaint();

				}

			}

			// Draw start and finish
			if (solution) {
				for (int j = 0; j < stack.size(); j++) {
					g.setColor(Color.GREEN);
					g.fillRect(rectWidth * stack.get(j).x, rectHeight * stack.get(j).y, rectWidth, rectHeight);
					g.setColor(Color.BLACK);
					g.drawRect(rectWidth * stack.get(j).x, rectHeight * stack.get(j).y, rectWidth, rectHeight);

					repaint();
				}
			}
		} else {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {

					g.setColor(new Color(70, 78, 91));
					g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);

					g.setColor(Color.BLACK);
					g.drawRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
				}

			}
			repaint();

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
