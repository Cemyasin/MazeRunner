package GUI;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generate {

	public int height, width;
	public int[][] mazee = new int[height][width];

	int startX = 1;
	int startY = 1;
	int endX = 39;
	int endY = 39;
	Point abc = new Point(startX, startY);

	public Generate(int width, int height) {

//		width -= width % 2;
//		width++;
//		height -= height % 2;
//		height++;
		this.width = width;
		this.height = height;
		mazee = new int[height][width];
	}

	
	public int[][] generate() {
		for (int i = 0; i < mazee.length; i++) {
			for (int j = 0; j < mazee[0].length; j++) {
				mazee[i][j] = 1;
			}
		}
		mazee[abc.x][abc.y] = 0;
		int f0 = 0, f1 = 0, f2 = 0, f3 = 0;
		List<Point> yol = new ArrayList<>();
		Random rd = new Random();
		while (abc.x != endX || abc.y != endY) {
			if (f0 + f1 + f2 + f3 == 4) {
				for (Point po : yol) {
					mazee[po.x][po.y] = 1;
				}
//				while(visited.size()>0) {
//					visited.remove(0);
//				}
				yol.clear();
//				visited = new ArrayList<Point>();
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
		int aa ;
		aa=rd.nextInt(yol.size());
		int bb=0;
		while (bb<(height-1)*(width-1)*height) {
			bb++;
			if (f0 + f1 + f2 + f3 == 4) {
//				visited.remove(aa);
				aa=rd.nextInt(yol.size());
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

		return mazee;
	}

}
