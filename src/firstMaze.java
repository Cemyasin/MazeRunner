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

public class firstMaze extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	Stack<Point> stack = new Stack<>();

	int width, height, rectWidth, rectHeight;
	boolean[][] maze;
	static Point current;
	static boolean flag2 = false;

	private boolean solution = false;
	boolean clear = false;
	int[][] mazee;
	Timer timer = new Timer(300, this);
	static int START_X;
	static int START_Y;
	static int END_X;
	static int END_Y;
	boolean running = false;
	boolean found = false;

	Random rn = new Random();
	List<Robot> acilanYerler = new ArrayList<Robot>();
	List<Robot> neighbours = new ArrayList<>();
	Stack<Point> visited = new Stack<>();
	List<Point> visited2 = new ArrayList<>();
	Stack<Point> visited3 = new Stack<>();
	Stack<Point> acik = new Stack<>();
	Robot robot;

	public void load(int width, int height, int[][] mazeee) {
		width -= width % 2;
		width++;
		height -= height % 2;
		height++;

		this.width = width;
		this.height = height;
		maze = new boolean[height][width];
		mazee = new int[height][width];
		mazee = mazeee;
		rectWidth = getWidth() / width;
		rectHeight = getHeight() / height;

		do {
			START_X = rn.nextInt(width - 2);
			START_Y = rn.nextInt(height - 2);
		} while (mazee[START_Y][START_X] != 0);

		do {
			END_X = rn.nextInt(width - 2);
			END_Y = rn.nextInt(height - 2);
		} while (mazee[END_Y][END_X] != 0 || (END_Y == START_Y && END_X == START_X));
		current = new Point(START_X, START_Y);

		robot = new Robot(current, null, null, 0);
		acilanYerler.add(robot);

	}

	public boolean solve() {

		running = true;

		if (current.x == END_X && current.y == END_Y) {
			visited.add(current);
			visited2.add(current);
			running = false;
			flag2 = true;
			found = true;
			return false;
		}

		List<Robot> neighbours = new ArrayList<>();
		for (int x = current.x - 1; x <= current.x + 1; x++) {
			for (int y = current.y - 1; y <= current.y + 1; y++) {
				if (x < 0 || x >= mazee[0].length || y < 0 || y >= mazee.length) {
					continue;
				}
				if ((x == current.x && y == current.y) || (x - 1 == current.x && y - 1 == current.y)
						|| (x - 1 == current.x && y + 1 == current.y) || (x + 1 == current.x && y - 1 == current.y)
						|| (x + 1 == current.x && y + 1 == current.y)) {
					continue;
				}
				Point xy = new Point(x, y);
				int cost = robot.cost + 1;

				Robot neighbour = new Robot(xy, robot, null, cost);

				acilanYerler.add(neighbour);

				if (mazee[y][x] == 0) {
					neighbours.add(neighbour);
					robot.neighbours = neighbours;
				}
			}

		}

		boolean flag = false;
		while (!flag) {

			int nextStep = rn.nextInt(4);
			if (nextStep == 0 && !visited.contains(current)) { // sola git
				if (current.x > 1) {
					if (mazee[current.y][current.x - 1] == 0) {
						visited.add(current);
						visited2.add(current);
						current = new Point(current.x - 1, current.y);
						if ((current.x+1 == END_X && current.y == END_Y)||(current.x-1 == END_X && current.y == END_Y)||
								(current.x == END_X && current.y == END_Y+1)||(current.x == END_X && current.y == END_Y-1)) {
							visited.add(current);
							visited2.add(current);
							running = false;
							flag2 = true;
							found = true;
							return false;
						}
						
						flag = true;
					}
				}

			} else if (nextStep == 1 && !visited.contains(current)) { // sağa git
				if (current.x < mazee[0].length - 2) {
					if (mazee[current.y][current.x + 1] == 0) {
						visited.add(current);
						visited2.add(current);
						current = new Point(current.x + 1, current.y);
						if ((current.x+1 == END_X && current.y == END_Y)||(current.x-1 == END_X && current.y == END_Y)||
								(current.x == END_X && current.y == END_Y+1)||(current.x == END_X && current.y == END_Y-1)) {
							visited.add(current);
							visited2.add(current);
							running = false;
							flag2 = true;
							found = true;
							return false;
						}
						flag = true;

					}
				}

			} else if (nextStep == 2 && !visited.contains(current)) { // yukarı git
				if (current.y > 1) {
					if (mazee[current.y - 1][current.x] == 0) {
						visited.add(current);
						visited2.add(current);
						current = new Point(current.x, current.y - 1);
						if ((current.x+1 == END_X && current.y == END_Y)||(current.x-1 == END_X && current.y == END_Y)||
								(current.x == END_X && current.y == END_Y+1)||(current.x == END_X && current.y == END_Y-1)) {
							visited.add(current);
							visited2.add(current);
							running = false;
							flag2 = true;
							found = true;
							return false;
						}
						flag = true;

					}
				}

			} else if (nextStep == 3 && !visited.contains(current)) { // aşağı gir
				if (current.y < mazee.length - 2) {
					if (mazee[current.y + 1][current.x] == 0) {
						visited.add(current);
						visited2.add(current);
						current = new Point(current.x, current.y + 1);
						if ((current.x+1 == END_X && current.y == END_Y)||(current.x-1 == END_X && current.y == END_Y)||
								(current.x == END_X && current.y == END_Y+1)||(current.x == END_X && current.y == END_Y-1)) {
							visited.add(current);
							visited2.add(current);
							running = false;
							flag2 = true;
							found = true;
							return false;
						}
						flag = true;
					}
				}
			} else {
				visited.pop();
			}

		}
//		repaint();
		return false;

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		timer.start();
		if (!clear) {
			if (mazee != null)
				for (int i = 0; i < mazee.length; i++) {
					for (int j = 0; j < mazee[0].length; j++) {

						g.setColor(Color.GRAY);
						g.fillRect(j * rectWidth, i * rectHeight, rectWidth, rectHeight);
						g.setColor(Color.LIGHT_GRAY);
						g.drawRect(j * rectWidth, i * rectHeight, rectWidth, rectHeight);
					}
				}


			if (running) {
				for (Robot robot : acilanYerler) {
					if (robot.point.x == START_X && robot.point.y == START_Y)
						continue;
					if (mazee[robot.point.y][robot.point.x] == 0) {

						g.setColor(Color.WHITE);
						g.fillRect(robot.point.x * rectWidth, robot.point.y * rectHeight, rectWidth, rectHeight);
						g.setColor(Color.BLACK);
						g.drawRect(robot.point.x * rectWidth, robot.point.y * rectHeight, rectWidth, rectHeight);

					} else {
						g.setColor(Color.BLACK);
						g.fillRect(robot.point.x * rectWidth, robot.point.y * rectHeight, rectWidth, rectHeight);
						g.setColor(Color.LIGHT_GRAY);
						g.drawRect(robot.point.x * rectWidth, robot.point.y * rectHeight, rectWidth, rectHeight);
					}
				}

				for (Point robot : visited2) {

					g.setColor(Color.ORANGE);
					g.fillRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);
					g.setColor(Color.BLACK);
					g.drawRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);

				}
			}else {
				for (Robot robot : acilanYerler) {
					if(robot!=null) {
					if (robot.point.x == START_X && robot.point.y == START_Y)
						continue;
					if (mazee[robot.point.y][robot.point.x] == 0) {

						g.setColor(Color.WHITE);
						g.fillRect(robot.point.x * rectWidth, robot.point.y * rectHeight, rectWidth, rectHeight);
						g.setColor(Color.BLACK);
						g.drawRect(robot.point.x * rectWidth, robot.point.y * rectHeight, rectWidth, rectHeight);

					} else {
						g.setColor(Color.BLACK);
						g.fillRect(robot.point.x * rectWidth, robot.point.y * rectHeight, rectWidth, rectHeight);
						g.setColor(Color.LIGHT_GRAY);
						g.drawRect(robot.point.x * rectWidth, robot.point.y * rectHeight, rectWidth, rectHeight);
					}	
					}
					
					
				}

				for (Point robot : visited2) {
					if(robot!=null) {
						g.setColor(Color.ORANGE);
					g.fillRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);
					g.setColor(Color.BLACK);
					g.drawRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);
					}
					

				}
				
			}
			g.setColor(Color.GREEN);
			g.fillRect(START_X * rectWidth, START_Y * rectHeight, rectWidth, rectHeight);
			g.setColor(Color.BLACK);
			g.drawRect(START_X * rectWidth, START_Y * rectHeight, rectWidth, rectHeight);

			g.setColor(Color.RED);
			g.fillRect(END_X * rectWidth, END_Y * rectHeight, rectWidth, rectHeight);
			g.setColor(Color.BLACK);
			g.drawRect(END_X * rectWidth, END_Y * rectHeight, rectWidth, rectHeight);


			if (solution) {

			}


		} else {
			if (mazee != null)
				for (int i = 0; i < mazee.length; i++) {
					for (int j = 0; j < mazee[0].length; j++) {

						g.setColor(Color.GRAY);
						g.fillRect(j * rectWidth, i * rectHeight, rectWidth, rectHeight);
						g.setColor(Color.LIGHT_GRAY);
						g.drawRect(j * rectWidth, i * rectHeight, rectWidth, rectHeight);
					}
				}

		}
//		repaint();
	}

	Thread thread;

//
	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
//		solve();
					int i = 0;
					while (running) {
						solve();
						repaint();
						System.out.println("girdi" + i++);
						thread.sleep(500);

					}

				} catch (InterruptedException ex) {

				}
			}
		}).start();

	
		repaint();
	}
}