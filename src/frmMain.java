
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class frmMain extends JFrame {

	private JPanel contentPane;
	private Maze maze;
	private JPanel panel ;

	
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
		int width=40;		
		int height=40;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel= new JPanel();
		maze= new Maze(width, height);
		panel = maze;
		panel.setBounds(10, 30, 784, 770);
		contentPane.add(panel);

		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.generate();		
			}
		});
		btnGenerate.setBounds(834, 139, 123, 41);
		contentPane.add(btnGenerate);
		
		JButton btnAnimation = new JButton("Animation");
		btnAnimation.setBounds(834, 200, 123, 41);
		contentPane.add(btnAnimation);
		
		JButton btnNewSolve = new JButton("Solve");
		btnNewSolve.setBounds(834, 250, 123, 41);
		contentPane.add(btnNewSolve);
				
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel= new JPanel();
				panel = maze;
				panel.setBounds(10, 30, 784, 770);
				contentPane.add(panel);
				repaint();
			}
		});
		btnClear.setBounds(834, 300, 123, 41);
		contentPane.add(btnClear);
		
		
	}
}
class Maze extends JPanel {
	private int width, height, rectWidth, rectHeight;
	private boolean[][] maze;
	
	public Maze(int width, int height) {
		// Make dimensions odd
		width -= width % 2;
		width++;
		height -= height % 2;
		height++;

		this.width = width;
		this.height = height;
		maze = new boolean[height][width];
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
		mazee[0][1] = 5;
		mazee[height - 1][width - 2] = 9;
	
		return mazee;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// setBackground(Color.BLACK);S

		// Calculate dimensions of rectangles
		rectWidth = getWidth() / width;
		rectHeight = getHeight() / height;

		// Draw maze
		// g.setColor(Color.WHITE);
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
		
	
		

		// Draw start and finish
		
		
		g.setColor(Color.GREEN);
		g.fillOval(0, 0, rectWidth, rectHeight);
		repaint();
	}

}

