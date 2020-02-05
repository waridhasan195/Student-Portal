import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void admain(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome window = new AdminHome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public AdminHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 51, 102));
		frame.setBounds(100, 100, 1100, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdminHome = new JLabel("Admin Home");
		lblAdminHome.setBounds(485, 34, 109, 33);
		frame.getContentPane().add(lblAdminHome);
		
		JButton btnAddNewStudent = new JButton("Add New Student");
		btnAddNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminStudent ob2 = new AdminStudent();
				ob2.ADSmain(null);
				frame.dispose();
			}
		});
		btnAddNewStudent.setBounds(50, 229, 150, 40);
		frame.getContentPane().add(btnAddNewStudent);
		
		JButton btnNewButton = new JButton("Teacher Info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminTeacher ob3 = new AdminTeacher();
				ob3.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(50, 299, 128, 40);
		frame.getContentPane().add(btnNewButton);
	}
}
