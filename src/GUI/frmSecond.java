package GUI;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entities.SecondMaze;
import logging.Logging;

public class frmSecond extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private SecondMaze maze;
	private JPanel panel;
	private int[][] mazeArray;
	private JTextField textField;
	private JTextField textField_1;
	int width = 40;
	int height = 40;
	private long time;
	private JTextField textWidth;
	private JTextField textHeight;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmSecond frame = new frmSecond();
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
	public frmSecond() {

		mazeArray = new int[height + 1][width + 1];

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel = new JPanel();

		maze = new SecondMaze(width, height);
		panel = maze;
		panel.setVisible(true);
		panel.setBounds(10, 30, 784, 770);
		contentPane.add(panel);

		textField = new JTextField();
		textField.setOpaque(false);
		textField.setBounds(818, 477, 156, 32);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setOpaque(false);
		textField_1.setBounds(818, 534, 156, 32);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnGo = new JButton("START");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				width=Integer.parseInt(textWidth.getText());
				height=Integer.parseInt(textHeight.getText());
				
//				maze = new Maze(width, height);
//				panel = maze;
//				panel.setVisible(true);
//				panel.setBounds(10, 30, 784, 770);
//				contentPane.add(panel);
				maze.load(width,height) ;
				repaint();
			}
		});
		btnGo.setBounds(845, 131, 110, 25);
		contentPane.add(btnGo);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mazeArray = maze.generate();
			}
		});
		btnGenerate.setBounds(839, 191, 123, 41);
		contentPane.add(btnGenerate);

		JButton btnAnimation = new JButton("Animation");
		btnAnimation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long start = System.nanoTime();
					maze.solve(mazeArray, 1, 0, true);
					long finish = System.nanoTime();
					time = (finish - start) / 1000000;
					System.out.println(time);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnAnimation.setBounds(839, 252, 123, 41);
		contentPane.add(btnAnimation);
		textField.setText("Solving Time : ");
		textField_1.setText("Number of Steps : ");
		JButton btnNewSolve = new JButton("Solve");
		btnNewSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.getSolution();
				textField.setText("Solving Time : " + time + "ms");
				textField_1.setText("Number of Steps : " + maze.truePath.size());

			}
		});
		btnNewSolve.setBounds(839, 302, 123, 41);
		contentPane.add(btnNewSolve);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.remove();
				panel.removeAll(); // Panelin içeriğini temizleme
				panel.revalidate(); // Paneli yeniden boyutlandırma ve yeniden yerleştirme
				panel.repaint(); // Paneli yeniden çizme
				textField.setText("Solving Time : ");
				textField_1.setText("Number of Steps : ");
			}
		});
		btnClear.setBounds(839, 352, 123, 41);
		contentPane.add(btnClear);

		JButton btnLogging = new JButton("Logging");
		btnLogging.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder builder = new StringBuilder();

				for (int i = 0; i < mazeArray.length; i++) {
					builder.append("{");
					for (int j = 0; j < mazeArray[0].length; j++) {
						builder.append(mazeArray[i][j] + ",");
					}
					builder.append("} ");
					builder.append("\n");
				}
				builder.append("\n\n\n\n");
				builder.append("----------Solution---------- \n");
				builder.append("Step Number : " + maze.truePath.size());
				builder.append("\n");
				builder.append("Solition Time : " + time + "ms");
				builder.append("\n");
				for (Point is : maze.truePath) {
					builder.append("(" + is.y + "," + is.x + ")" + "=>");
				}

				String data = builder.toString();
				Logging logging = new Logging();
				logging.log(data);

			}
		});
		btnLogging.setBounds(839, 413, 123, 41);
		contentPane.add(btnLogging);

		textWidth = new JTextField();
		textWidth.setToolTipText("Width");
		textWidth.setBounds(853, 90, 32, 20);
		contentPane.add(textWidth);
		textWidth.setColumns(10);

		JLabel lblW = new JLabel("W:");
		lblW.setOpaque(true);
		lblW.setBounds(837, 93, 15, 14);
		contentPane.add(lblW);

		JLabel lblH = new JLabel("H:");
		lblH.setOpaque(true);
		lblH.setBounds(896, 93, 15, 14);
		contentPane.add(lblH);

		textHeight = new JTextField();
		textHeight.setToolTipText("Height");
		textHeight.setColumns(10);
		textHeight.setBounds(911, 89, 32, 20);
		contentPane.add(textHeight);

	}
}