import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import java.awt.SystemColor;
import javax.swing.UIManager;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class LoginHome {

	private JFrame frame;
	private JTextField Aduser;
	private JPasswordField Adpass;
	private JInternalFrame internalFrame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginHome window = new LoginHome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	public LoginHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection Con = null;
	private JTextField adusernam;
	private JTextField adpass;
	private JTextField teausa;
	private JTextField teapass;
	private JTextField csid;
	private JTextField lu;
	private JPasswordField lpa;
	private JTextField ylog;
	private JPasswordField ypas;
	private JTextField tusa;
	private JPasswordField tpas;
	private JTextField tcid;
	private JTextField tlusa;
	private JPasswordField tlpass;
	private void initialize() {
		Con = Connect.dbConnector();
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JInternalFrame AdminFrame = new JInternalFrame("Admin Login");
				AdminFrame.getContentPane().setBackground(SystemColor.inactiveCaption);
				AdminFrame.setClosable(true);
				AdminFrame.setBounds(204, 69, 394, 234);
				frame.getContentPane().add(AdminFrame);
				AdminFrame.getContentPane().setLayout(null);
				JLabel lblUserName = new JLabel("User Name");
				lblUserName.setBounds(90, 37, 80, 24);
				AdminFrame.getContentPane().add(lblUserName);
				
				JLabel lblPassword = new JLabel("Password");
				lblPassword.setBounds(90, 72, 80, 24);
				AdminFrame.getContentPane().add(lblPassword);
				Aduser = new JTextField();
				Aduser.setBounds(190, 39, 89, 20);
				AdminFrame.getContentPane().add(Aduser);
				Aduser.setColumns(10);
				
				Adpass = new JPasswordField();
				Adpass.setBounds(190, 70, 89, 20);
				AdminFrame.getContentPane().add(Adpass);
				
				JButton AdminbtnLogin = new JButton("LogIn");
				AdminbtnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent agr0)
					{
						try {
						String aqure = "select * from Admin_Logs where Auser=? and Apass=?";
						PreparedStatement apst = Con.prepareStatement(aqure);
						apst.setString(1, Aduser.getText());
						apst.setString(2, Adpass.getText());
						
						ResultSet ars = apst.executeQuery();
						
						int count=0; 
						
						while(ars.next())
						{
							count++;
						}
						if(count==1)
						{
							JOptionPane.showMessageDialog(null, "Succesfully Logged In");
							AdminHome ob = new AdminHome();
							ob.admain(null);
							frame.dispose();
							
						}
						else if(count<1)
						{
							JOptionPane.showMessageDialog(null, "Worng User Name or Password");
						}
						
						ars.close();
						apst.close();
						
						}catch(Exception ae)
						{
							JOptionPane.showMessageDialog(null, ae);
						}
					}
				});
				AdminbtnLogin.setBounds(86, 113, 78, 24);
				AdminFrame.getContentPane().add(AdminbtnLogin);
				
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setBounds(174, 114, 78, 23);
				AdminFrame.getContentPane().add(btnCancel);
				
				JLabel lblNewLabel = new JLabel("Forget Password? Click Here to ");
				lblNewLabel.setBounds(54, 170, 289, 23);
				AdminFrame.getContentPane().add(lblNewLabel);
				
				JButton btnReset = new JButton("Reset");
				btnReset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent agr0)	
					{
						JInternalFrame internalFrameReset = new JInternalFrame("Reset Admin Password");
						internalFrameReset.setBackground(new Color(102, 153, 153));
						internalFrameReset.setClosable(true);
						internalFrameReset.setBounds(401, 272, 460, 284);
						frame.getContentPane().add(internalFrameReset);
						internalFrameReset.getContentPane().setLayout(null);
						adusernam = new JTextField();
						adusernam.setBounds(147, 77, 139, 31);
						internalFrameReset.getContentPane().add(adusernam);
						adusernam.setColumns(10);
						
						JLabel lblUserName_1 = new JLabel("User Name");
						lblUserName_1.setForeground(new Color(51, 255, 51));
						lblUserName_1.setFont(new Font("Georgia", Font.BOLD, 13));
						lblUserName_1.setBounds(42, 76, 109, 32);
						internalFrameReset.getContentPane().add(lblUserName_1);
						
						JLabel lblNewPassword = new JLabel("New Password");
						lblNewPassword.setForeground(new Color(51, 255, 51));
						lblNewPassword.setFont(new Font("Georgia", Font.BOLD, 13));
						lblNewPassword.setBounds(22, 131, 113, 32);
						internalFrameReset.getContentPane().add(lblNewPassword);
						
						adpass = new JTextField();
						adpass.setBounds(148, 138, 138, 25);
						internalFrameReset.getContentPane().add(adpass);
						adpass.setColumns(10);
						
						JButton btnSaveChanges = new JButton("Save");
						btnSaveChanges.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								try
								{
									String adur = "update Admin_Logs set Apass =' "+adpass.getText()+" ' where Auser =' "+adusernam.getText()+" '  ";
									PreparedStatement adups = Con.prepareStatement(adur);
									
									adups.execute();
									
									JOptionPane.showMessageDialog(null, "Password Changed");
									
									adups.close();
									
								}catch(Exception adp)
								{
									JOptionPane.showMessageDialog(null, adp);
								}
							}
						});
						btnSaveChanges.setBackground(new Color(255, 255, 255));
						btnSaveChanges.setForeground(new Color(102, 204, 153));
						btnSaveChanges.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
						btnSaveChanges.setBounds(115, 194, 109, 31);
						internalFrameReset.getContentPane().add(btnSaveChanges);
						internalFrameReset.setVisible(true);
						
					}
				});
				
				btnReset.setBounds(250, 171, 100, 22);
				AdminFrame.getContentPane().add(btnReset);
				AdminFrame.setVisible(true);
				
			}
		});
		btnAdmin.setBounds(31, 250, 89, 23);
		frame.getContentPane().add(btnAdmin);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JInternalFrame internalFrame = new JInternalFrame("Student Login");
				internalFrame.setResizable(true);
				internalFrame.setIconifiable(true);
				internalFrame.setClosable(true);
				internalFrame.setBackground(new Color(102, 153, 153));
				internalFrame.setBounds(188, 144, 430, 469);
				frame.getContentPane().add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
			
				
				JLabel lblUsername = new JLabel("UserName");
				lblUsername.setForeground(new Color(204, 255, 0));
				lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblUsername.setBounds(45, 93, 76, 29);
				internalFrame.getContentPane().add(lblUsername);
				
				JLabel lblPassword_2 = new JLabel("Password");
				lblPassword_2.setForeground(new Color(204, 255, 51));
				lblPassword_2.setBackground(new Color(0, 153, 153));
				lblPassword_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblPassword_2.setBounds(45, 153, 76, 20);
				internalFrame.getContentPane().add(lblPassword_2);
				
				csid = new JTextField();
				csid.setToolTipText("Student ID");
				csid.setBounds(49, 357, 138, 28);
				internalFrame.getContentPane().add(csid);
				csid.setColumns(10);
				
				JButton btnNew = new JButton("Create New Accaount");
				btnNew.setForeground(new Color(255, 51, 0));
				btnNew.setFont(new Font("Segoe UI", Font.BOLD, 11));
				btnNew.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							String lqr = "select S_id from StudentInfo where S_id=?";
							PreparedStatement lps = Con.prepareStatement(lqr);
							lps.setString(1, csid.getText());
							
							ResultSet lrs = lps.executeQuery();
							int co = 0;
							while(lrs.next())
							{
								co++;
							}
							if(co==1)
							{
								JInternalFrame internalFrame_Ex = new JInternalFrame("Create Student Login Account");
								internalFrame_Ex.getContentPane().setBackground(new Color(102, 102, 153));
								internalFrame_Ex.setBounds(575, 144, 439, 416);
								frame.getContentPane().add(internalFrame_Ex);
								internalFrame_Ex.getContentPane().setLayout(null);
								internalFrame_Ex.setClosable(true);
								
								JLabel lblUsername_1 = new JLabel("User Name");
								lblUsername_1.setForeground(new Color(204, 102, 102));
								lblUsername_1.setFont(new Font("Tahoma", Font.BOLD, 12));
								lblUsername_1.setBounds(24, 107, 68, 24);
								internalFrame_Ex.getContentPane().add(lblUsername_1);
								
								JLabel lblPassword_3 = new JLabel("Password");
								lblPassword_3.setForeground(new Color(204, 102, 102));
								lblPassword_3.setFont(new Font("Tahoma", Font.BOLD, 12));
								lblPassword_3.setBounds(24, 161, 68, 15);
								internalFrame_Ex.getContentPane().add(lblPassword_3);
								
								lu = new JTextField();
								lu.setBounds(113, 110, 86, 20);
								internalFrame_Ex.getContentPane().add(lu);
								lu.setColumns(10);
								
								lpa = new JPasswordField();
								lpa.setBounds(113, 159, 86, 20);
								internalFrame_Ex.getContentPane().add(lpa);
								
								JButton btnCreate = new JButton("Confrim");
								btnCreate.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										int action=JOptionPane.showConfirmDialog(null, "Do you really wanted to Create a Account ?","Account Confirmed",JOptionPane.YES_NO_OPTION);
										if(action==0)
										try
										{
											String ipu = "insert into StudentLogs(Suser,Spass) values(?,?) ";
											PreparedStatement ipst = Con.prepareStatement(ipu);
											
											ipst.setString(1, lu.getText());
											ipst.setString(2, lpa.getText());
											
											ipst.execute();
											JOptionPane.showMessageDialog(null, "Account Added");
											
											ipst.close();
										}catch(Exception e16)
										{
											JOptionPane.showMessageDialog(null, e16);
										}
									}
								});
								btnCreate.setBounds(199, 243, 89, 30);
								internalFrame_Ex.getContentPane().add(btnCreate);
								internalFrame_Ex.setVisible(true);
								internalFrame.setVisible(true);
								
							}
							else if(co<1)
							{
								JOptionPane.showMessageDialog(null, "Wrong Student ID");
							}
							
						}catch(Exception e17)
						{
							JOptionPane.showMessageDialog(null, e17);
						}
					}
				});
				btnNew.setBounds(197, 356, 163, 29);
				internalFrame.getContentPane().add(btnNew);
				
				ylog = new JTextField();
				ylog.setBounds(145, 93, 115, 24);
				internalFrame.getContentPane().add(ylog);
				ylog.setColumns(10);
				
				ypas = new JPasswordField();
				ypas.setBounds(145, 154, 115, 20);
				internalFrame.getContentPane().add(ypas);
				
				JButton btnSignIn_1 = new JButton("Sign In");
				btnSignIn_1.setForeground(new Color(0, 51, 102));
				btnSignIn_1.setFont(new Font("Verdana", Font.BOLD, 11));
				btnSignIn_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try
						{
							String fre = "select * from StudentLogs where Suser=? and Spass=?";
							PreparedStatement fpat = Con.prepareStatement(fre);
							
							fpat.setString(1, ylog.getText());
							fpat.setString(2, ypas.getText());
							
							ResultSet frs = fpat.executeQuery();
							int coun = 0;
							
							while(frs.next())
							{
								coun++;
							}
							if(coun==1)
							{
								JOptionPane.showMessageDialog(null, "Welcome to Student Page");
								StudentCheck ob3 = new StudentCheck();
								ob3.main(null);
								frame.dispose();
							}
							else if(coun<1)
							{
								
								JOptionPane.showMessageDialog(null, "Wrong User Name or Password");
								
							}
							frs.close();
							fpat.close();
						}catch(Exception e18)
						{
							JOptionPane.showMessageDialog(null, e18);
						}
						
						
					}
				});
				btnSignIn_1.setBounds(165, 213, 89, 23);
				internalFrame.getContentPane().add(btnSignIn_1);
				
				JLabel lblStudentLogin = new JLabel("Sign in to view profiles");
				lblStudentLogin.setForeground(new Color(0, 255, 153));
				lblStudentLogin.setFont(new Font("Forte", Font.PLAIN, 20));
				lblStudentLogin.setBounds(109, 11, 229, 29);
				internalFrame.getContentPane().add(lblStudentLogin);
				
				JLabel lblNew = new JLabel("New Admitted?");
				lblNew.setForeground(new Color(255, 255, 153));
				lblNew.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
				lblNew.setBounds(22, 284, 89, 20);
				internalFrame.getContentPane().add(lblNew);
				
				JLabel lblPress = new JLabel("*Provide Student ID to Create a New Account");
				lblPress.setForeground(new Color(0, 0, 0));
				lblPress.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
				lblPress.setBounds(22, 316, 265, 14);
				internalFrame.getContentPane().add(lblPress);
				
				internalFrame.setVisible(true);
			}
		});
		btnStudent.setBounds(31, 284, 89, 23);
		frame.getContentPane().add(btnStudent);
		
		JButton btnTeacher = new JButton("Teacher");
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				JInternalFrame internalFrame = new JInternalFrame("Teacher Login");
				internalFrame.getContentPane().setBackground(new Color(51, 153, 204));
				internalFrame.setClosable(true);
				internalFrame.setBounds(194, 53, 506, 328);
				frame.getContentPane().add(internalFrame);
				internalFrame.getContentPane().setLayout(null);
				
				JLabel lblTeachersLogin = new JLabel("Teachers Login");
				lblTeachersLogin.setForeground(new Color(255, 255, 0));
				lblTeachersLogin.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 15));
				lblTeachersLogin.setBounds(196, 11, 133, 27);
				internalFrame.getContentPane().add(lblTeachersLogin);
				
				JLabel lblUserName_2 = new JLabel("User Name");
				lblUserName_2.setForeground(new Color(255, 153, 51));
				lblUserName_2.setFont(new Font("Georgia", Font.BOLD, 13));
				lblUserName_2.setBounds(66, 103, 97, 27);
				internalFrame.getContentPane().add(lblUserName_2);
				
				JLabel lblPassword_1 = new JLabel("Password");
				lblPassword_1.setForeground(new Color(255, 153, 51));
				lblPassword_1.setFont(new Font("Georgia", Font.BOLD, 13));
				lblPassword_1.setBounds(66, 150, 77, 27);
				internalFrame.getContentPane().add(lblPassword_1);
				
				teausa = new JTextField();
				teausa.setBounds(173, 104, 155, 27);
				internalFrame.getContentPane().add(teausa);
				teausa.setColumns(10);
				
				teapass = new JPasswordField();
				teapass.setBounds(173, 154, 156, 20);
				internalFrame.getContentPane().add(teapass);
				teapass.setColumns(10);
				
				JButton btnSignIn = new JButton("Sign In");
				btnSignIn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
							String tqre = "select * from TeacherLogs where Tuser=? and Tpass=?";
							PreparedStatement tepst = Con.prepareStatement(tqre);
							
							tepst.setString(1, teausa.getText());
							tepst.setString(2, teapass.getText());
							
							ResultSet ters = tepst.executeQuery();
							
							int ct = 0;
							while(ters.next())
							{
								ct++;
							}
							if(ct==1)
							{
								JOptionPane.showMessageDialog(null, "Login Sucessfull");
							}
							else if(ct<1)
							{
								JOptionPane.showMessageDialog(null, "Worng Username or Password");
							}
							
							ters.close();
							tepst.close();
							
						}catch(Exception cte)
						{
							JOptionPane.showMessageDialog(null, cte);
						}
						
					}
				});
				btnSignIn.setForeground(new Color(255, 255, 255));
				btnSignIn.setBackground(new Color(0, 51, 255));
				btnSignIn.setFont(new Font("Segoe UI Emoji", Font.BOLD, 12));
				btnSignIn.setBounds(187, 214, 89, 23);
				internalFrame.getContentPane().add(btnSignIn);
				internalFrame.setVisible(true);
				
			}
		});
		btnTeacher.setBounds(31, 318, 89, 23);
		frame.getContentPane().add(btnTeacher);
		
		JInternalFrame internalFrame_1 = new JInternalFrame("Teacher Login");
		internalFrame_1.getContentPane().setBackground(new Color(51, 0, 102));
		internalFrame_1.setBounds(155, 124, 457, 517);
		frame.getContentPane().add(internalFrame_1);
		internalFrame_1.getContentPane().setLayout(null);
		
		JLabel lblSignInTo = new JLabel("Sign In to View Profiles");
		lblSignInTo.setForeground(new Color(255, 255, 0));
		lblSignInTo.setFont(new Font("Footlight MT Light", Font.BOLD, 17));
		lblSignInTo.setBounds(123, 26, 220, 47);
		internalFrame_1.getContentPane().add(lblSignInTo);
		
		JLabel lblUsername_2 = new JLabel("UserName");
		lblUsername_2.setForeground(new Color(0, 204, 0));
		lblUsername_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername_2.setBounds(82, 137, 84, 28);
		internalFrame_1.getContentPane().add(lblUsername_2);
		
		JLabel lblPassword_4 = new JLabel("Password");
		lblPassword_4.setForeground(new Color(51, 204, 51));
		lblPassword_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword_4.setBounds(84, 199, 70, 21);
		internalFrame_1.getContentPane().add(lblPassword_4);
		
		tusa = new JTextField();
		tusa.setBounds(184, 141, 130, 23);
		internalFrame_1.getContentPane().add(tusa);
		tusa.setColumns(10);
		
		tpas = new JPasswordField();
		tpas.setBounds(184, 200, 130, 20);
		internalFrame_1.getContentPane().add(tpas);
		
		JButton btnSignIn_2 = new JButton("Sign In");
		btnSignIn_2.setForeground(new Color(51, 0, 102));
		btnSignIn_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSignIn_2.setBounds(188, 261, 89, 23);
		internalFrame_1.getContentPane().add(btnSignIn_2);
		
		tcid = new JTextField();
		tcid.setBounds(69, 415, 130, 28);
		internalFrame_1.getContentPane().add(tcid);
		tcid.setColumns(10);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setForeground(new Color(255, 0, 153));
		btnNewButton.setBounds(250, 415, 130, 28);
		internalFrame_1.getContentPane().add(btnNewButton);
		
		JLabel lblIfYouDont = new JLabel("If you Don't have a account ........");
		lblIfYouDont.setForeground(new Color(153, 255, 204));
		lblIfYouDont.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		lblIfYouDont.setBounds(20, 328, 178, 21);
		internalFrame_1.getContentPane().add(lblIfYouDont);
		
		JLabel lblProvideTeacherId = new JLabel("Provide Teacher ID to create a Accaount");
		lblProvideTeacherId.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 12));
		lblProvideTeacherId.setForeground(new Color(51, 255, 204));
		lblProvideTeacherId.setBounds(20, 360, 294, 21);
		internalFrame_1.getContentPane().add(lblProvideTeacherId);
		
		JInternalFrame internalFrame_2 = new JInternalFrame("Create Teacher Login Accaount");
		internalFrame_2.setBackground(new Color(153, 102, 204));
		internalFrame_2.setClosable(true);
		internalFrame_2.setBounds(638, 124, 425, 376);
		frame.getContentPane().add(internalFrame_2);
		internalFrame_2.getContentPane().setLayout(null);
		
		JLabel lblUserName_3 = new JLabel("User Name");
		lblUserName_3.setForeground(new Color(0, 255, 0));
		lblUserName_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUserName_3.setBounds(43, 83, 76, 21);
		internalFrame_2.getContentPane().add(lblUserName_3);
		
		JLabel lblPassword_5 = new JLabel("Password");
		lblPassword_5.setForeground(new Color(0, 255, 0));
		lblPassword_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword_5.setBounds(47, 146, 72, 21);
		internalFrame_2.getContentPane().add(lblPassword_5);
		
		tlusa = new JTextField();
		tlusa.setBounds(148, 80, 123, 30);
		internalFrame_2.getContentPane().add(tlusa);
		tlusa.setColumns(10);
		
		tlpass = new JPasswordField();
		tlpass.setBounds(148, 147, 123, 20);
		internalFrame_2.getContentPane().add(tlpass);
		
		JButton btnConfrim = new JButton("Confrim");
		btnConfrim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String ltr = "insert into TeacherLogs(Tuser,Tpass) values(?,?) ";
					PreparedStatement lpst = Con.prepareStatement(ltr);
					
					lpst.setString(1, tlusa.getText());
					lpst.setString(2, tlpass.getText());
					
					lpst.execute();
					JOptionPane.showMessageDialog(null, "Accaount Confrimed");
					lpst.close();
					
				}catch(Exception e18)
				{
					JOptionPane.showMessageDialog(null, e18);
				}
			}
		});
		btnConfrim.setBackground(new Color(51, 153, 255));
		btnConfrim.setForeground(new Color(255, 255, 255));
		btnConfrim.setFont(new Font("Sitka Text", Font.BOLD, 13));
		btnConfrim.setBounds(228, 216, 107, 30);
		internalFrame_2.getContentPane().add(btnConfrim);
		internalFrame_2.setVisible(true);
		internalFrame_1.setVisible(true);
		
		
		
		
		
		
		
		
	
		
	
		
	}
}
