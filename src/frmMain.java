
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Panel;
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
	private int[][] mazeArray;
	
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
		mazeArray=new int[height+1][width+1];
		
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
			mazeArray=maze.generate();		
			}
		});
		btnGenerate.setBounds(834, 139, 123, 41);
		contentPane.add(btnGenerate);
		
		JButton btnAnimation = new JButton("Animation");
		btnAnimation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Solver solver= new Solver(mazeArray,width,height);
				try {
					maze.solve(mazeArray, 1, 0,true);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//panel=solver;
//				panel.updateUI();
//				repaint();
//				try {
//					solver.solve(mazeArray, 1, 0);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				int x=solver.stack.size();
//				for(int i=0;i<x;i++)
//				System.out.println(solver.stack.pop());
			}
		});
		btnAnimation.setBounds(834, 200, 123, 41);
		contentPane.add(btnAnimation);
		
		JButton btnNewSolve = new JButton("Solve");
		btnNewSolve.setBounds(834, 250, 123, 41);
		contentPane.add(btnNewSolve);
				
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); // Panelin içeriğini temizleme
		        panel.revalidate(); // Paneli yeniden boyutlandırma ve yeniden yerleştirme
		        panel.repaint(); // Paneli yeniden çizme
			}
		});
		btnClear.setBounds(834, 300, 123, 41);
		contentPane.add(btnClear);
		
		
	}
	

}

