package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Point;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class frmMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public void main(String[] args) {
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

	public frmMain() {
		setLocationByPlatform(true);
		setLocation(new Point(250, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("MAZE ALGORITHM");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFirst = new JButton("First Problem");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFirst first;
				try {
					first = new frmFirst();
					first.main(null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnFirst.setBounds(107, 44, 204, 57);
		contentPane.add(btnFirst);
		
		JButton btnSecond = new JButton("Second Problem");
		btnSecond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSecond second;
				second = new frmSecond();
				second.main(null);
			}
		});
		btnSecond.setBounds(107, 140, 204, 57);
		contentPane.add(btnSecond);
	}
}
