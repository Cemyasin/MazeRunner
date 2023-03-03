import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class frmMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public frmMain() {
		int width = 80;
        int height = 80;
        BinaryTreeMaze maze = new BinaryTreeMaze(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel=maze;
		panel.setBounds(40, 37, 276, 222);
		contentPane.add(panel);
	}
	
	
}

class BinaryTreeMaze extends JPanel {
    private int width, height, rectWidth, rectHeight;
    private boolean[][] maze;

    public BinaryTreeMaze(int width, int height) {
        // Make dimensions odd
        width -= width % 2;
        width++;
        height -= height % 2;
        height++;

        this.width = width;
        this.height = height;
        maze = new boolean[height][width];

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
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        // Calculate dimensions of rectangles
        rectWidth = getWidth() / width;
        rectHeight = getHeight() / height;

        // Draw maze
        g.setColor(Color.WHITE);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!maze[i][j]) {
                    g.clearRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
                    //g.drawRect(j * rectWidth, i * rectHeight, j, i);

                }
            }
        }

        // Draw start and finish
        g.clearRect(rectWidth, 0, rectWidth, rectHeight);
        g.clearRect((width - 2) * rectWidth, (height - 1) * rectHeight, rectWidth, rectHeight);
    }

}