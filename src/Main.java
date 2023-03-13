
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int width = 10;
        int height = 10;

        // create the maze
        boolean[][] maze = new boolean[width][height];

        // select a starting cell
        Random rand = new Random();
        int startX = rand.nextInt(width);
        int startY = rand.nextInt(height);
        maze[startY][startX] = true;

        // explore the maze
        int x = startX;
        int y = startY;
        int counter0=0;
        int counter1=0;
        int counter2=0;
        int counter3=0;
        while (true) {
        	if(counter0+counter1+counter2+counter3!=4) {
        		 int dir = rand.nextInt(4);
            if (dir == 0) { // go up
                if (y > 0 && !maze[y-1][x]) {
                    y--;
                    counter0=0;
                } else {
                	counter0=1;
                    continue;
                }
            } else if (dir == 1) { // go right
                if (x < width - 1 && !maze[y][x+1]) {
                    x++;
                    counter1=0;
                } else {
                	counter1=1;
                    continue;
                }
            } else if (dir == 2) { // go down
                if (y < height - 1 && !maze[y+1][x]) {
                    y++;
                    counter2=0;
                } else {
                	counter2=1;
                    continue;
                }
            } else if (dir == 3) { // go left
                if (x > 0 && !maze[y][x-1]) {
                    x--;
                    counter3=0;
                } else {
                	counter3=1;
                    continue;
                }
            }
        	}else {
        		maze[y][x]=true;
        	}
            // select a random direction to move in
           

            // check if the current cell has been visited
            if (maze[y][x]) {
                break;
            }

            // mark the current cell as visited
            maze[y][x] = true;
        }

        // follow the path back and mark all cells as visited
        while (true) {
            // mark the current cell as visited
            maze[y][x] = true;

            // go back to the previous cell
            int dir = rand.nextInt(4);
            if (dir == 0) { // go up
                if (y > 0 && maze[y-1][x]) {
                    y--;
                }
            } else if (dir == 1) { // go right
                if (x < width - 1 && maze[y][x+1]) {
                    x++;
                }
            } else if (dir == 2) { // go down
                if (y < height - 1 && maze[y+1][x]) {
                    y++;
                }
            } else if (dir == 3) { // go left
                if (x > 0 && maze[y][x-1]) {
                    x--;
                }
            }

            // check if we have reached the starting cell
            if (x == startX && y == startY) {
                break;
            }
        }

        // select a cell on the top row as the exit point
        int exitX = rand.nextInt(width);
        int exitY = rand.nextInt(height);
        maze[exitY][exitX] = true;

        // print the maze
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[j][i]) {
                    System.out.print("  ");
                } else {
                    System.out.print("[]");
                }
            }
            System.out.println();
        }
    }
}
