package GUI;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entities.FirstMaze;
import logging.Logging;

public class frmFirst extends JFrame {
	private static final long serialVersionUID = 1L;

	FirstMaze m;
	JPanel panel = new JPanel();

	int[][] absMaze = null;
	public static JLabel lblTime;
	public static JLabel lblStep;
	private JPanel contentPane;
	private JTextField textField;

	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmFirst frame = new frmFirst();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmFirst() throws IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("---Maze Runner---");
		setBounds(100, 100, 1320, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		m = new FirstMaze();
		panel = m;
		panel.setBounds(23, 43, 784, 631);
		contentPane.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(878, 43, 354, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("URL :");
		lblNewLabel.setBounds(837, 43, 66, 28);
		contentPane.add(lblNewLabel);

		lblTime = new JLabel("  Solving Time :");
		lblTime.setBounds(1054, 171, 218, 28);
		contentPane.add(lblTime);

		lblStep = new JLabel("  Step Number :");
		lblStep.setBounds(1054, 292, 218, 28);
		contentPane.add(lblStep);

		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URL url;
				BufferedReader read;
				String uurl = textField.getText();
				String line;
				ArrayList<Integer> mazeList = new ArrayList<Integer>();

				int arrayY = 0, arrayX = 0;
				int counter = 0;

				try {
					url = new URL(uurl);
					read = new BufferedReader(new InputStreamReader(url.openStream()));

					while ((line = read.readLine()) != null) {
						for (int j = 0; j < line.length(); j++) {
							mazeList.add(Integer.parseInt(String.valueOf(line.charAt(j))));
							arrayY = line.length();
						}
						arrayX += 1;
					}
					read.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				int[][] maze = new int[arrayX][arrayY];
				for (int j = 0; j < arrayX; j++) {
					for (int k = 0; k < arrayY; k++) {
						maze[j][k] = mazeList.get(counter++);
					}
				}

				absMaze = new int[arrayX + 2][arrayY + 2];

				for (int i = 0; i < arrayX + 2; i++) {
					for (int j = 0; j < arrayY + 2; j++) {
						if ((i == 0 || i == arrayX + 1) || (j == 0 || j == arrayY + 1)) {
							absMaze[i][j] = 1;
						}
					}
				}
				for (int i = 0; i < arrayX; i++) {
					for (int j = 0; j < arrayY; j++) {
						absMaze[i + 1][j + 1] = maze[i][j];
					}
				}
				for (int i = 0; i < arrayX + 2; i++) {
					for (int j = 0; j < arrayY + 2; j++) {
						System.out.print(absMaze[i][j]);
					}
					System.out.println();
				}
			}

		});
		btnGo.setBounds(1242, 46, 52, 23);
		contentPane.add(btnGo);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.getClear();
				m.clear = false;
				int width = absMaze[0].length;
				int height = absMaze.length;
				m.load(width, height, absMaze);
				m.repaint();

			}
		});
		btnNewButton.setBounds(878, 126, 101, 33);
		contentPane.add(btnNewButton);

		JButton btnStart = new JButton("Solve");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.running = true;
			}
		});
		btnStart.setBounds(879, 257, 101, 33);
		contentPane.add(btnStart);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(879, 396, 101, 33);
		contentPane.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				m.getClear();
			}
		});

		JButton btnLogging = new JButton("Logging");
		btnLogging.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < absMaze.length; i++) {
					builder.append("{");
					for (int j = 0; j < absMaze[0].length; j++) {
						builder.append(absMaze[i][j] + ",");
					}
					builder.append("} ");
					builder.append("\n");
				}
				builder.append("Step Number : " + m.road.size());
				builder.append("\n");
				for (Point is : m.road) {
					builder.append("(" + is.x + "," + is.y + ")");
					builder.append("\n");
				}
				builder.append("Solving Time : " + String.format("%.3f", m.time));
				builder.append("\n");
				String data = builder.toString();
				Logging logging = new Logging();
				logging.log(data);
			}
		});
		btnLogging.setBounds(879, 323, 101, 33);
		contentPane.add(btnLogging);

		JButton btnClouding = new JButton("Clouding");
		btnClouding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.cloud = true;
			}
		});
		btnClouding.setBounds(878, 198, 101, 33);
		contentPane.add(btnClouding);

	}
}
