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
	Stack<Point> stack = new Stack<>();

	private int width, height, rectWidth, rectHeight;
	private boolean[][] maze;
	static Point current;
	boolean flag = false;
	private boolean solution = false, clear = false;
	int[][] mazee;
	Timer timer = new Timer(1, this);
	static int START_X;
	static int START_Y;
	static int END_X;
	static int END_Y;

	Random rn = new Random();
	List<Robot> acilanYerler = new ArrayList<Robot>();
	List<Robot> neighbours = new ArrayList<>();

	Robot robot;

	public void load(int width, int height, int[][] mazeee) {
		width -= width % 2;
		width++;
		height -= height % 2;
		height++;
		timer.start();
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

		int nextStep = rn.nextInt(4);
		for (int x = current.x - 1; x <= current.x + 1; x++) {
			for (int y = current.y - 1; y <= current.y + 1; y++) {
				if (x < 0 || x >= mazee.length || y < 0 || y >= mazee[0].length) {
					continue;
				}
				if ((x == current.x && y == current.y) || (x - 1 == current.x && y - 1 == current.y)
						|| (x - 1 == current.x && y + 1 == current.y) || (x + 1 == current.x && y - 1 == current.y)
						|| (x + 1 == current.x && y + 1 == current.y)) {
					continue;
				}
				Point xy = new Point(y, x);
				int cost = robot.cost + 1;
				acilanYerler.add(new Robot(xy, current, stack, cost));

			}

		}

		if (nextStep == 0) { // sola git
			if (mazee[current.y][current.x - 1] == 0) {
				current.x = current.x - 1;
			}
		} else if (nextStep == 1) { // sağa git
			if (mazee[current.y][current.x + 1] == 0) {
				current.x = current.x + 1;

			}
		} else if (nextStep == 2) { // yukarı git
			if (mazee[current.y + 1][current.x] == 0) {
				current.y = current.y + 1;

			}
		} else if (nextStep == 3) { // aşağı gir
			if (mazee[current.y - 1][current.x] == 0) {
				current.y = current.y - 1;

			}
		}

		return false;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Calculate dimensions of rectangles

		if (!clear) {
			for (int i = 0; i < height - 1; i++) {
				for (int j = 0; j < width - 1; j++) {
					if (mazee[i][j] == 0) {
						g.setColor(new Color(70, 78, 91));
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

					g.setColor(Color.GREEN);
					g.fillRect(rectWidth * START_X, rectHeight * START_Y, rectWidth, rectHeight);
					g.setColor(Color.RED);
					g.fillRect(rectWidth * END_X, rectHeight * END_Y, rectWidth, rectHeight);

				}

			}

			repaint();

			if (flag) {

			}

			// Draw start and finish
			if (solution) {

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
