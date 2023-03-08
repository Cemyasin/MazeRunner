import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmFirstProblem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JPanel panel = new JPanel();
	firstMaze m;
	int[][] matrix = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmFirstProblem frame = new frmFirstProblem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public frmFirstProblem() throws IOException {
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
				String uurl = textField.getText();
				String url1 = "http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt";
				String url2 = "http://bilgisayar.kocaeli.edu.tr/prolab2/url2.txt";
				URL url;
				char[][] input = new char[20][20];
				try {
					url = new URL(uurl);
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

					String inputLine;

					int i = 0, j = 0, k = 0;
					boolean flag = true;
					while ((inputLine = in.readLine()) != null) {
						if (flag) {
							input = new char[inputLine.length()][inputLine.length()];
							matrix = new int[input.length + 4][input.length + 4];
							flag = false;
						}
						input[i] = inputLine.toCharArray();

						System.out.println(input[i]);
						i++;
					}

					in.close();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i < input.length + 4; i++) {
					for (int j = 0; j < input[0].length + 4; j++) {
						if (i == 0 || j == 0 || i == input.length + 3 || j == input.length + 3) {
							matrix[i][j] = 1;
							continue;
						}
						if (i == 1 || j == 1) {
							matrix[i][j] = 0;
							continue;
						}
						if (i < input.length + 2 && j < input.length + 2)
							matrix[i][j] = Character.getNumericValue(input[i - 2][j - 2]);
					}

				}

			}

		});
		btnGo.setBounds(1242, 46, 52, 23);
		contentPane.add(btnGo);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int width = matrix[0].length;
				int height = matrix.length;
				m.load(width, height, matrix);
			}
		});
		btnNewButton.setBounds(878, 91, 101, 33);
		contentPane.add(btnNewButton);

		JButton btnStart = new JButton("Solve");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

	}
}
