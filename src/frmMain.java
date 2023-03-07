import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class frmMain extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private Maze maze;
	private JPanel panel;
	private int[][] mazeArray;
	private JTextField textField;
	private JTextField textField_1;
	int width = 40;
	int height = 40;

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

		mazeArray = new int[height + 1][width + 1];

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel = new JPanel();

		maze = new Maze(width, height);
		panel = maze;
		panel.setVisible(true);
		panel.setBounds(10, 30, 784, 770);
		contentPane.add(panel);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mazeArray = maze.generate();
			}
		});
		btnGenerate.setBounds(837, 241, 123, 41);
		contentPane.add(btnGenerate);

		JButton btnAnimation = new JButton("Animation");
		btnAnimation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					maze.solve(mazeArray, 1, 0, true);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnAnimation.setBounds(837, 302, 123, 41);
		contentPane.add(btnAnimation);

		JButton btnNewSolve = new JButton("Solve");
		btnNewSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.getSolution();
			}
		});
		btnNewSolve.setBounds(837, 352, 123, 41);
		contentPane.add(btnNewSolve);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.remove();
				panel.removeAll(); // Panelin içeriğini temizleme
				panel.revalidate(); // Paneli yeniden boyutlandırma ve yeniden yerleştirme
				panel.repaint(); // Paneli yeniden çizme
			}
		});
		btnClear.setBounds(837, 402, 123, 41);
		contentPane.add(btnClear);

	}
}
