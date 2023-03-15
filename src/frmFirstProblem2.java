import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class frmFirstProblem2 extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;
	JPanel panel = new JPanel();
	firstMaze m;
	int[][] absMaze = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmFirstProblem2 frame = new frmFirstProblem2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmFirstProblem2() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1320, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		m = new firstMaze();
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
				m.clear = false;
				int width = absMaze[0].length;
				int height = absMaze.length;
				m.load(width, height, absMaze);
				m.repaint();

			}
		});
		btnNewButton.setBounds(878, 91, 101, 33);
		contentPane.add(btnNewButton);

		JButton btnStart = new JButton("Solve");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.running=true; 

			}
		});
		btnStart.setBounds(878, 149, 101, 33);
		contentPane.add(btnStart);

		JButton btnShowPath = new JButton("Show Path");
		btnShowPath.setBounds(878, 211, 101, 33);
		contentPane.add(btnShowPath);

		btnShowPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(878, 331, 101, 33);
		contentPane.add(btnClear);
		
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
				String data = builder.toString();
				Logging logging = new Logging();
				logging.log(data);
			}
		});
		btnLogging.setBounds(878, 273, 101, 33);
		contentPane.add(btnLogging);

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.running = false;
				m.clear = true;
				m.visited2.clear();
				m.visited.clear();
				m.acilanYerler.clear();
				m.visited2 = new ArrayList<>();
				m.visited = new Stack<>();
				m.acilanYerler = new ArrayList<>();
			}
		});

	}
}
