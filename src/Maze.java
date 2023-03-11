import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Maze extends JPanel implements ActionListener {

	Stack<Point> truePath = new Stack<>();
	Stack<Integer> visited = new Stack<>();
	Stack<Point> stack3 = new Stack<>();
	Stack<Point> show = new Stack<>();

	private int width, height, rectWidth, rectHeight;
	private boolean[][] maze;
	static Point current;
	boolean flag = false;
	private boolean solution = false, clear = false;

	Timer timer = new Timer(500, this);

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

	}

	public int[][] generate() {

		clear = false;
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
				if (maze[i][j])
					mazee[i][j] = 1;
				else
					mazee[i][j] = 0;
			}
		}
//		mazee[0][0] =0;
		mazee[height - 1][width - 2] = 9;

		return mazee;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Calculate dimensions of rectangles
		rectWidth = getWidth() / width;
		rectHeight = getHeight() / height;

		if (!clear) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {

					g.setColor(new Color(180, 180, 180));
					g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
					g.setColor(Color.DARK_GRAY);
					g.drawRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);

				}

			}

			repaint();

			if (flag) {
				g.setColor(Color.GREEN);
				g.fillRect(0, 0, rectWidth, rectHeight);
				g.setColor(Color.RED);
				g.fillRect((width - 2) * rectWidth, (height-1 ) * rectHeight, rectWidth, rectHeight);

				for (int i = 0; i < show.size(); i++) {
					if (!maze[show.get(i).y][show.get(i).x]) {
						g.setColor(Color.WHITE);
						g.fillRect(show.get(i).x * rectWidth, show.get(i).y * rectHeight, rectWidth, rectHeight);
						g.setColor(Color.BLACK);
						g.drawRect(show.get(i).x * rectWidth, show.get(i).y * rectHeight, rectWidth, rectHeight);

					} else {
						g.setColor(new Color(0, 0, 0));
						g.fillRect(show.get(i).x * rectWidth, show.get(i).y * rectHeight, rectWidth, rectHeight);
						g.setColor(Color.DARK_GRAY);
						g.drawRect(show.get(i).x * rectWidth, show.get(i).y * rectHeight, rectWidth, rectHeight);
					}
					

					repaint();

				}
				for (int i = 0; i < stack3.size() - 1; i++) {
//					g.fillRect((width - 2) * rectWidth, (height - 1) * rectHeight, rectWidth, rectHeight);
					g.setColor(new Color(0,235,255));
					g.fillRect(stack3.get(i).x * rectWidth, stack3.get(i).y * rectHeight, rectWidth, rectHeight);

					g.setColor(Color.BLACK);
					g.drawRect(stack3.get(i).x * rectWidth, stack3.get(i).y * rectHeight, rectWidth, rectHeight);
					repaint();

				}

			}

			// Draw start and finish
			if (solution) {
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (!maze[i][j]) {
							g.setColor(Color.WHITE);
							g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
							g.setColor(Color.BLACK);
							g.drawRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
							// g.drawRect(j * rectWidth, i * rectHeight, j, i);
						} else {
							g.setColor(new Color(0, 0, 0));
							g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
							g.setColor(Color.DARK_GRAY);
							g.drawRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
						}
					}
				}

				for (int i = 0; i < visited.size() - 1; i++) {
					g.setColor(new Color(0,235,255));
					g.fillRect(visited.get(i) * rectWidth, visited.get(i + 1) * rectHeight, rectWidth, rectHeight);

					g.setColor(Color.BLACK);
					g.drawRect(visited.get(i) * rectWidth, visited.get(++i) * rectHeight, rectWidth, rectHeight);
					repaint();

				}
				for (int j = 0; j < truePath.size(); j++) {
					g.setColor(Color.BLUE);
					g.fillRect(rectWidth * truePath.get(j).x, rectHeight * truePath.get(j).y, rectWidth, rectHeight);
					g.setColor(Color.BLACK);
					g.drawRect(rectWidth * truePath.get(j).x, rectHeight * truePath.get(j).y, rectWidth, rectHeight);

					repaint();
				}
				g.setColor(Color.GREEN);
				g.fillRect(0, 0, rectWidth, rectHeight);
				g.setColor(Color.RED);
				g.fillRect((width - 2) * rectWidth, (height - 1) * rectHeight, rectWidth, rectHeight);

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

	Thread thread;

	@Override
	public void actionPerformed(ActionEvent e) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (!flag) {
						for (int i = 0, j = 0; i < visited.size(); i++, j++) {
							// System.out.println(stack2.get(i));
							if (visited.get(i) > 0 && visited.get(i + 1) > 0 && visited.get(i) < maze.length
									&& visited.get(i + 1) < maze[0].length) {
								show.push(new Point(visited.get(i) - 1, visited.get(i + 1) ));
								show.push(new Point(visited.get(i) , visited.get(i + 1) - 1));
								show.push(new Point(visited.get(i) + 1, visited.get(i + 1) ));
								show.push(new Point(visited.get(i) , visited.get(i + 1) + 1));
							}
							stack3.push(new Point(visited.get(i++), visited.get(i)));

							repaint();
							thread.sleep(50);
						}

					}

				} catch (InterruptedException ex) {
					flag = false;
				}
			}
		}).start();

	}

	public void getSolution() {
		flag = false;
		solution = true;

	}

	public void remove() {
	
		clear = true;
		solution = false;
		truePath.clear();
		visited.clear();
		stack3.clear();
		show.clear();
	}

	public boolean solve(int[][] maze, int x, int y, boolean flag) throws InterruptedException {
		rectHeight = y;
		rectWidth = x;

		Point point = new Point(y, x);

		if (maze[y][x] == 9) {
			point.x = x;
			point.y = y;
			this.flag = flag;
			truePath.push(point);
			return true;
		}

		if (maze[y][x] == 0) {
			current = new Point();
			current.x = x;
			current.y = y;
			visited.push(x);
			visited.push(y);
//			System.out.println(stack2);

			maze[y][x] = 5;

			int dx = -1;
			int dy = 0;
			if (x != 0)
				if (solve(maze, x + dx, y + dy, flag)) {
					point.x = x;
					point.y = y;
					truePath.push(point);
					return true;
				}

			dx = 1;
			dy = 0;
			if (solve(maze, x + dx, y + dy, flag)) {
				point.x = x;
				point.y = y;
				truePath.push(point);
				return true;
			}

			dx = 0;
			dy = -1;
			if (y != 0)
				if (solve(maze, x + dx, y + dy, flag)) {
					point.x = x;
					point.y = y;
					truePath.push(point);
					return true;
				}

			dx = 0;
			dy = 1;
			if (solve(maze, x + dx, y + dy, flag)) {
				point.x = x;
				point.y = y;
				truePath.push(point);
				return true;
			}
		}

		return false;
	}

}