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

public class SecondMaze extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	int[][] mazee;
	int width, height, rectWidth, rectHeight;
	boolean flag = false;
	private boolean solution = false, clear = false;
	public int startX = 1;
	public int startY = 1;
	int endX = height - 2;
	int endY = width - 2;
	boolean edge = true;

	public Stack<Point> truePath = new Stack<>();
	Stack<Integer> visited = new Stack<>();
	Stack<Point> stack3 = new Stack<>();
	Stack<Point> show = new Stack<>();

	Point current;
	Timer timer = new Timer(500, this);

	public SecondMaze(int width, int height) {
		width -= width % 2;
		width++;
		height -= height % 2;
		height++;
		timer.start();
		this.width = width;
		this.height = height;
		mazee = new int[height][width];
		Random rd = new Random();
		edge = rd.nextBoolean();

		if (edge) {
			startX = 1;
			startY = 1;
			endX = height - 2;
			endY = width - 2;
		} else {
			startX = 1;
			startY = width - 2;
			endX = height - 2;
			endY = 1;
		}
	}

	public void load(int width, int height) {
		width -= width % 2;
		width++;
		height -= height % 2;
		height++;
		timer.start();
		this.width = width;
		this.height = height;
		mazee = new int[height][width];

		Random rd = new Random();
		edge = rd.nextBoolean();
		if (edge) {
			startX = 1;
			startY = 1;
			endX = height - 2;
			endY = width - 2;
		} else {
			startX = 1;
			startY = width - 2;
			endX = height - 2;
			endY = 1;
		}
	}

	public int[][] generate() {

		clear = false;
		Random rd = new Random();

		Point abc = new Point(startX, startY);
		for (int i = 0; i < mazee.length; i++) {
			for (int j = 0; j < mazee[0].length; j++) {
				mazee[i][j] = 1;
			}
		}
		mazee[abc.x][abc.y] = 0;
		int f0 = 0, f1 = 0, f2 = 0, f3 = 0;
		List<Point> yol = new ArrayList<>();

		while (abc.x != endX || abc.y != endY) {
			if (f0 + f1 + f2 + f3 == 4) {
				for (Point po : yol) {
					mazee[po.x][po.y] = 1;
				}

				yol.clear();
				abc.x = startX;
				abc.y = startY;
				yol.add(abc);
			}
			int go = rd.nextInt(4);
			switch (go) {
			case 0: // yukarı
				if (abc.x > 1 && abc.y > 0 && abc.y < mazee[0].length - 1) {
					if (mazee[abc.x - 1][abc.y] == 1 && mazee[abc.x - 1][abc.y - 1] == 1
							&& mazee[abc.x - 1][abc.y + 1] == 1 && mazee[abc.x - 2][abc.y] == 1
							&& mazee[abc.x - 2][abc.y - 1] == 1 && mazee[abc.x - 2][abc.y + 1] == 1) {
						if (abc.x - 1 >= 1) {
							yol.add(new Point(abc.x - 1, abc.y));
							abc.x = abc.x - 1;
							mazee[abc.x][abc.y] = 0;
							f0 = f1 = f2 = f3 = 0;
						} else {
							f0 = 1;
						}
					} else {
						f0 = 1;
					}
				} else {
					f0 = 1;
				}
				break;

			case 1: // aşağı
				if (abc.y > 0 && abc.x < mazee.length - 2 && abc.y < mazee[0].length - 1) {
					if (mazee[abc.x + 1][abc.y] == 1 && mazee[abc.x + 1][abc.y - 1] == 1
							&& mazee[abc.x + 1][abc.y + 1] == 1 && mazee[abc.x + 2][abc.y] == 1
							&& mazee[abc.x + 2][abc.y - 1] == 1 && mazee[abc.x + 2][abc.y + 1] == 1) {
						if (abc.x + 1 < mazee.length - 1) {
							yol.add(new Point(abc.x + 1, abc.y));
							abc.x = abc.x + 1;
							mazee[abc.x][abc.y] = 0;
							f0 = f1 = f2 = f3 = 0;
						} else {
							f1 = 1;
						}
					} else {
						f1 = 1;
					}
				} else {
					f1 = 1;
				}
				break;

			case 2: // sağa
				if (abc.x > 0 && abc.x < mazee.length - 1 && abc.y < mazee[0].length - 2) {
					if (mazee[abc.x - 1][abc.y + 1] == 1 && mazee[abc.x + 1][abc.y + 1] == 1
							&& mazee[abc.x][abc.y + 1] == 1 && mazee[abc.x][abc.y + 2] == 1
							&& mazee[abc.x - 1][abc.y + 2] == 1 && mazee[abc.x + 1][abc.y + 2] == 1) {
						if (abc.y + 1 < mazee[0].length - 1) {
							yol.add(new Point(abc.x, abc.y + 1));
							abc.y = abc.y + 1;
							mazee[abc.x][abc.y] = 0;
							f0 = f1 = f2 = f3 = 0;
						} else {
							f2 = 1;
						}
					} else {
						f2 = 1;
					}
				} else {
					f2 = 1;
				}
				break;

			case 3: // sola
				if (abc.x > 0 && abc.y > 1 && abc.x < mazee.length - 1) {
					if (mazee[abc.x - 1][abc.y - 1] == 1 && mazee[abc.x + 1][abc.y - 1] == 1
							&& mazee[abc.x][abc.y - 1] == 1 && mazee[abc.x][abc.y - 2] == 1
							&& mazee[abc.x - 1][abc.y - 2] == 1 && mazee[abc.x + 1][abc.y - 2] == 1) {
						if (abc.y - 1 >= 1) {
							yol.add(new Point(abc.x, abc.y - 1));
							abc.y = abc.y - 1;
							mazee[abc.x][abc.y] = 0;
							f0 = f1 = f2 = f3 = 0;
						} else {
							f3 = 1;
						}
					} else {
						f3 = 1;
					}
				} else {
					f3 = 1;
				}
				break;
			default:
				break;
			}
		}
		f0 = 0;
		f1 = 0;
		f2 = 0;
		f3 = 0;
		Point bas = new Point();
		int aa;
		aa = rd.nextInt(yol.size());
		int bb = 0;
		while (bb < (height - 1) * (width - 1) * height) {
			bb++;
			if (f0 + f1 + f2 + f3 == 4) {
				aa = rd.nextInt(yol.size());
				bas = yol.get(aa);
				abc = bas;

			}
			int go = rd.nextInt(4);
			switch (go) {
			case 0: // yukarı
				if (abc.x > 1 && abc.y > 0 && abc.y < mazee[0].length - 1) {
					if (mazee[abc.x - 1][abc.y] == 1 && mazee[abc.x - 1][abc.y - 1] == 1
							&& mazee[abc.x - 1][abc.y + 1] == 1 && mazee[abc.x - 2][abc.y] == 1
							&& mazee[abc.x - 2][abc.y - 1] == 1 && mazee[abc.x - 2][abc.y + 1] == 1) {
						if (abc.x - 1 >= 1) {
							yol.add(new Point(abc.x - 1, abc.y));
							abc.x = abc.x - 1;
							mazee[abc.x][abc.y] = 0;
							f0 = f1 = f2 = f3 = 0;
						} else {
							f0 = 1;
						}
					} else {
						f0 = 1;
					}
				} else {
					f0 = 1;
				}
				break;

			case 1: // aşağı
				if (abc.y > 0 && abc.x < mazee.length - 2 && abc.y < mazee[0].length - 1) {
					if (mazee[abc.x + 1][abc.y] == 1 && mazee[abc.x + 1][abc.y - 1] == 1
							&& mazee[abc.x + 1][abc.y + 1] == 1 && mazee[abc.x + 2][abc.y] == 1
							&& mazee[abc.x + 2][abc.y - 1] == 1 && mazee[abc.x + 2][abc.y + 1] == 1) {
						if (abc.x + 1 < mazee.length - 1) {
							yol.add(new Point(abc.x + 1, abc.y));
							abc.x = abc.x + 1;
							mazee[abc.x][abc.y] = 0;
							f0 = f1 = f2 = f3 = 0;
						} else {
							f1 = 1;
						}
					} else {
						f1 = 1;
					}
				} else {
					f1 = 1;
				}
				break;

			case 2: // sağa
				if (abc.x > 0 && abc.x < mazee.length - 1 && abc.y < mazee[0].length - 2) {
					if (mazee[abc.x - 1][abc.y + 1] == 1 && mazee[abc.x + 1][abc.y + 1] == 1
							&& mazee[abc.x][abc.y + 1] == 1 && mazee[abc.x][abc.y + 2] == 1
							&& mazee[abc.x - 1][abc.y + 2] == 1 && mazee[abc.x + 1][abc.y + 2] == 1) {
						if (abc.y + 1 < mazee[0].length - 1) {
							yol.add(new Point(abc.x, abc.y + 1));
							abc.y = abc.y + 1;
							mazee[abc.x][abc.y] = 0;
							f0 = f1 = f2 = f3 = 0;
						} else {
							f2 = 1;
						}
					} else {
						f2 = 1;
					}
				} else {
					f2 = 1;
				}
				break;

			case 3: // sola
				if (abc.x > 0 && abc.y > 1 && abc.x < mazee.length - 1) {
					if (mazee[abc.x - 1][abc.y - 1] == 1 && mazee[abc.x + 1][abc.y - 1] == 1
							&& mazee[abc.x][abc.y - 1] == 1 && mazee[abc.x][abc.y - 2] == 1
							&& mazee[abc.x - 1][abc.y - 2] == 1 && mazee[abc.x + 1][abc.y - 2] == 1) {
						if (abc.y - 1 >= 1) {
							yol.add(new Point(abc.x, abc.y - 1));
							abc.y = abc.y - 1;
							mazee[abc.x][abc.y] = 0;
							f0 = f1 = f2 = f3 = 0;
						} else {
							f3 = 1;
						}
					} else {
						f3 = 1;
					}
				} else {
					f3 = 1;
				}
				break;
			default:
				break;
			}
		}

		mazee[startX][startY] = 0;
		mazee[endX][endY] = 0;
		return mazee;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
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
				g.fillRect(startY * rectWidth, startX * rectHeight, rectWidth, rectHeight);
				g.setColor(Color.RED);
				g.fillRect(endY * rectWidth, endX * rectHeight, rectWidth, rectHeight);

				for (int i = 0; i < show.size(); i++) {
					if (mazee[show.get(i).y][show.get(i).x] == 0) {
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
					g.setColor(new Color(0, 235, 255));
					g.fillRect(stack3.get(i).x * rectWidth, stack3.get(i).y * rectHeight, rectWidth, rectHeight);
					g.setColor(Color.BLACK);
					g.drawRect(stack3.get(i).x * rectWidth, stack3.get(i).y * rectHeight, rectWidth, rectHeight);
					repaint();
				}
				g.setColor(Color.GREEN);
				g.fillRect(startY * rectWidth, startX * rectHeight, rectWidth, rectHeight);
				g.setColor(Color.RED);
				g.fillRect(endY * rectWidth, endX * rectHeight, rectWidth, rectHeight);
			}

			if (solution) {
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (mazee[i][j] == 0) {
							g.setColor(Color.WHITE);
							g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
							g.setColor(Color.BLACK);
							g.drawRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
						} else {
							g.setColor(new Color(0, 0, 0));
							g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
							g.setColor(Color.DARK_GRAY);
							g.drawRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
						}
					}
				}

				for (int i = 0; i < visited.size() - 1; i++) {
					g.setColor(new Color(0, 235, 255));
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
				g.fillRect(startY * rectWidth, startX * rectHeight, rectWidth, rectHeight);
				g.setColor(Color.RED);
				g.fillRect(endY * rectWidth, endX * rectHeight, rectWidth, rectHeight);
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
						for (int i = 0; i < visited.size(); i++) {
							if (visited.get(i) > 0 && visited.get(i + 1) > 0 && visited.get(i) < mazee.length
									&& visited.get(i + 1) < mazee[0].length) {
								show.push(new Point(visited.get(i) - 1, visited.get(i + 1)));
								show.push(new Point(visited.get(i), visited.get(i + 1) - 1));
								show.push(new Point(visited.get(i) + 1, visited.get(i + 1)));
								show.push(new Point(visited.get(i), visited.get(i + 1) + 1));
							}
							stack3.push(new Point(visited.get(i++), visited.get(i)));
							repaint();
							Thread.sleep(50);
						}
					}
				} catch (InterruptedException ex) {
					flag = false;
				}
			}
		}).start();
		repaint();
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
		truePath = new Stack<>();
		visited = new Stack<>();
		stack3 = new Stack<>();
		show = new Stack<>();
	}

	public boolean solve(int[][] maze, int y, int x, boolean flag) throws InterruptedException {
		rectHeight = x;
		rectWidth = y;

		Point point = new Point(x, y);
		if (x == endX && y == endY) {
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[0].length; j++) {
					if (maze[i][j] == 5)
						maze[i][j] = 0;
				}
			}
			point.x = y;
			point.y = x;
			this.flag = flag;
			truePath.push(point);
			return true;
		}

		if (maze[x][y] == 0) {
			current = new Point();
			current.x = y;
			current.y = x;
			visited.push(y);
			visited.push(x);
			maze[x][y] = 5;

			int nextY = -1;
			int nextX = 0;
			if (y != 0)
				if (solve(maze, y + nextY, x + nextX, flag)) {
					point.x = y;
					point.y = x;
					truePath.push(point);
					return true;
				}

			nextY = 1;
			nextX = 0;
			if (solve(maze, y + nextY, x + nextX, flag)) {
				point.x = y;
				point.y = x;
				truePath.push(point);
				return true;
			}

			nextY = 0;
			nextX = -1;
			if (x != 0)
				if (solve(maze, y + nextY, x + nextX, flag)) {
					point.x = y;
					point.y = x;
					truePath.push(point);
					return true;
				}

			nextY = 0;
			nextX = 1;
			if (solve(maze, y + nextY, x + nextX, flag)) {
				point.x = y;
				point.y = x;
				truePath.push(point);
				return true;
			}
		}
		return false;
	}

}