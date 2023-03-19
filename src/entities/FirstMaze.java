package entities;
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

public class FirstMaze extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	Stack<Point> stack = new Stack<>();

	int width, height, rectWidth, rectHeight;
	boolean[][] maze;
	static Point current;
	boolean flag2 = true;

	boolean solution = false;
	public boolean clear = false;
	int[][] mazee;
	Timer timer = new Timer(10, this);
	static int START_X;
	static int START_Y;
	static int END_X;
	static int END_Y;
	public boolean running = false;
	boolean found = false;

	Random rn = new Random();
	List<Robot> acilanYerler = new ArrayList<Robot>();
	List<Robot> neighbours = new ArrayList<>();
	Stack<Point> visited = new Stack<>();
	List<Point> visited2 = new ArrayList<>();
	List<Robot> visited3 = new ArrayList<Robot>();
	List<Robot> path = new ArrayList<>();
	Stack<Point> acik = new Stack<>();
	Robot robot;
	Robot firstRobot;

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

		this.robot = new Robot(current, null, null, 0);
		firstRobot = robot;
		acilanYerler.add(robot);
		visited.add(current);
		visited3.add(robot);

	}

	public boolean solve() {

		running = true;
		flag2 = false;

		if (!path.contains(robot))
			path.add(robot);

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

				Robot neighbour = null;
				boolean f = false;
				for (Robot robot : acilanYerler) {
					if (xy.x == robot.point.x && xy.y == robot.point.y) {
						neighbour = robot;
						f = true;
						break;

					}
				}
				if (!f) {
					neighbour = new Robot(xy, null, null, cost);
					acilanYerler.add(neighbour);

				}
				if (mazee[y][x] == 0) {
					visited3.add(neighbour);
					if (!path.contains(neighbour)) {
						path.add(neighbour);
					}
					if (robot.neighbours != null) {
						neighbours = robot.neighbours;
					}
					if (!neighbours.contains(neighbour))
						neighbours.add(neighbour);
					robot.neighbours = neighbours;
					List<Robot> nb;
					if (neighbour.neighbours == null) {
						nb = new ArrayList<>();
						nb.add(robot);
						neighbour.neighbours = nb;
					} else if (!neighbour.neighbours.contains(robot)) {
						neighbour.neighbours.add(robot);
					}
				}

				if (mazee[y][x] == 0) {
					if (neighbour.neighbours != null)
						for (Robot robot : path) {
							if ((neighbour.point.x + 1 == robot.point.x && neighbour.point.y == robot.point.y)) {
								if (!neighbour.neighbours.contains(robot)) {
									neighbour.neighbours.add(robot);
								}
								if (!robot.neighbours.contains(neighbour)) {
									robot.neighbours.add(neighbour);
								}
							} else if ((neighbour.point.x - 1 == robot.point.x && neighbour.point.y == robot.point.y)) {
								if (!neighbour.neighbours.contains(robot)) {
									neighbour.neighbours.add(robot);
								}
								if (!robot.neighbours.contains(neighbour)) {
									robot.neighbours.add(neighbour);
								}
							} else if ((neighbour.point.x == robot.point.x && neighbour.point.y + 1 == robot.point.y)) {
								if (!neighbour.neighbours.contains(robot)) {
									neighbour.neighbours.add(robot);
								}
								if (!robot.neighbours.contains(neighbour)) {
									robot.neighbours.add(neighbour);
								}
							} else if ((neighbour.point.x == robot.point.x && neighbour.point.y - 1 == robot.point.y)) {
								if (!neighbour.neighbours.contains(robot)) {
									neighbour.neighbours.add(robot);
								}
								if (!robot.neighbours.contains(neighbour)) {
									robot.neighbours.add(neighbour);
								}
							}

						}
				}
			}

		}
//		System.out.println(robot.point + "---" + robot.cost + "*** " );
//		for (Robot robot : path) {
//			System.out.println(robot.point+"---"+robot.cost+"// ");
//		}
//		System.out.println("**********");

		if (current.x == END_X && current.y == END_Y) {
			visited.add(current);
			visited2.add(current);
//			running = false;
			getSolution();
			found = true;
			return false;
		}
		if ((current.x + 1 == END_X && current.y == END_Y) || (current.x - 1 == END_X && current.y == END_Y)
				|| (current.x == END_X && current.y == END_Y + 1) || (current.x == END_X && current.y == END_Y - 1)) {
			visited.add(current);
			visited2.add(current);
			timer.stop();
//			running = false;
			getSolution();
			found = true;
			return false;
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

						flag = true;
					}
				}

			} else if (nextStep == 1 && !visited.contains(current)) { // sağa git
				if (current.x < mazee[0].length - 2) {
					if (mazee[current.y][current.x + 1] == 0) {
						visited.add(current);
						visited2.add(current);
						current = new Point(current.x + 1, current.y);

						flag = true;

					}
				}

			} else if (nextStep == 2 && !visited.contains(current)) { // yukarı git
				if (current.y > 1) {
					if (mazee[current.y - 1][current.x] == 0) {
						visited.add(current);
						visited2.add(current);
						current = new Point(current.x, current.y - 1);
						flag = true;

					}
				}

			} else if (nextStep == 3 && !visited.contains(current)) { // aşağı gir
				if (current.y < mazee.length - 2) {
					if (mazee[current.y + 1][current.x] == 0) {
						visited.add(current);
						visited2.add(current);
						current = new Point(current.x, current.y + 1);
						flag = true;
					}
				}
			} else {
				visited.pop();
			}

		}

		for (Robot ro : robot.neighbours) {

			if (current.x == ro.point.x && current.y == ro.point.y) {
				if (robot.cost == 0) {
					robot.next = ro;
				}
				robot = ro;

			}
		}
//		repaint();
		return false;

	}

	public void getSolution() {
		for (int i = 0; i < path.size() - 1; i++) {
			for (Robot robot : path) {

				for (Robot neighbour : robot.neighbours) {
					if (robot.cost < neighbour.cost) {
						neighbour.cost = robot.cost + 1;
					} else if (robot.cost > neighbour.cost) {
						robot.cost = neighbour.cost + 1;
					}
				}

			}
		}
		solution=true;

	}

	public void getClear() {
		for (Robot po : path) {
			System.out.println(po.point);

		}

		running = false;
		flag2 = true;
		clear = true;
		found = false;
		solution = false;
		robot = new Robot();
		visited2.clear();
		visited.clear();
		path.clear();
		acilanYerler.clear();
		path = new ArrayList<>();
		visited2 = new ArrayList<>();
		visited = new Stack<>();
		acilanYerler = new ArrayList<>();
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
				g.setColor(Color.BLUE);
				g.fillOval(current.x * rectWidth, current.y * rectHeight, rectWidth, rectHeight);
				for (Point robot : visited2) {

					g.setColor(Color.ORANGE);
					g.fillRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);
					g.setColor(Color.BLACK);
					g.drawRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);

				}
				for (Point robot : visited) {

					g.setColor(Color.MAGENTA);
					g.fillRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);
					g.setColor(Color.BLACK);
					g.drawRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);

				}
				g.setColor(Color.BLUE);
				g.fillOval(current.x * rectWidth, current.y * rectHeight, rectWidth, rectHeight);
			} else {
				for (Robot robot : acilanYerler) {
					if (robot != null) {
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
					if (robot != null) {
						g.setColor(Color.ORANGE);
						g.fillRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);
						g.setColor(Color.BLACK);
						g.drawRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);
					}
				}
				for (Point robot : visited) {

					g.setColor(Color.MAGENTA);
					g.fillRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);
					g.setColor(Color.BLACK);
					g.drawRect(robot.x * rectWidth, robot.y * rectHeight, rectWidth, rectHeight);

				}

				

			}
			if (solution) {
					Robot rob;
					rob = path.get(path.size() - 1);
					while (rob.cost > 0) {
						g.setColor(Color.cyan);
						g.fillRect(rob.point.x * rectWidth, rob.point.y * rectHeight, rectWidth, rectHeight);
						g.setColor(Color.BLACK);
						g.drawRect(rob.point.x * rectWidth, rob.point.y * rectHeight, rectWidth, rectHeight);
//					if(rob.neighbours!=null)
//						if(rob.cost==1)
//								break;
						for (Robot neig : rob.neighbours) {
							
							if (neig.cost < rob.cost) {
								rob = neig;
							}

						}
						repaint();
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
	int i = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
		if (running) {

//			while (running) {
			if(!found)
			solve();
			repaint();
			
			System.out.println("girdi" + i++);
//						thread.sleep(100);
//				}
//					solve();
			
			if (solution) {
			System.out.println("solution" + i++);
			running=false;
			repaint();
		}
		}

//				} catch (InterruptedException ex) {
//
//				}
//			}
//		}).start();
		
		repaint();
	}
}