import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class AdminStudent {

	private JFrame frame;
	private JTextField adstuname;
	
	

	/**
	 * Launch the application.
	 */
	public static void ADSmain(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminStudent window = new AdminStudent();
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
	public AdminStudent() {
		initialize();
	}
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	Connection con = null;
	private JTextField depclass;
	private JTextField deptsec;
	private JTextField deptc1;
	private JTextField deptc2;
	private JTextField deptc3;
	private JTextField deptc4;
	private JTextField adsid;
	private JTable table;
	private JTextField search;
	private JButton btnHome;
	private JButton btnResultEntry;
	private JButton btnExamFees;
	private JTextField fsid;
	private JTextField fsnam;
	private JLabel lblPaymentDate;
	private JTextField fstym;
	private JLabel lblExamFees;
	private JLabel lblAmount;
	private JTextField fsama;
	private JButton btnConfrim;
	private JButton btnWow;
	private JTextField rc1;
	private JTextField rc2;
	private JTextField rc3;
	private JTextField rc4;
	private JTextField cal;
	private JTextField rsid;
	private JTextField rcla;
	private JTextField mr1;
	private JTextField mr2;
	private JTextField mr3;
	private JTextField mr4;
	private String Dpnam;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
	
	private void initialize() {
		con = Connect.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 204, 0));
		frame.setBounds(100, 100, 1100, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdminStudent = new JLabel("Admin Student");
		lblAdminStudent.setBounds(423, 11, 112, 28);
		frame.getContentPane().add(lblAdminStudent);
		
		JButton btnShowData = new JButton("Refresh Table");
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String showad = "select S_id,S_name,Dep_name,Class,Section from StudentInfo inner join Department on StudentInfo.S_Dept = Department.Dep_id";
					PreparedStatement pshd = con.prepareStatement(showad);
					
					ResultSet adrs = pshd.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(adrs));
				}catch(Exception shed)
				{
					JOptionPane.showMessageDialog(null, shed);
				}
			}
		});
		btnShowData.setBounds(949, 39, 112, 28);
		frame.getContentPane().add(btnShowData);
		
		JScrollPane scrollPaneAd = new JScrollPane();
		scrollPaneAd.setBounds(556, 203, 518, 467);
		frame.getContentPane().add(scrollPaneAd);
		
		table = new JTable();
		scrollPaneAd.setViewportView(table);
		
		JComboBox comboBoxSec = new JComboBox();
		comboBoxSec.setBounds(770, 119, 74, 20);
		comboBoxSec.setModel(new DefaultComboBoxModel(new String[] {"S_name","S_id","Class","Section","S_Dept"}));
		frame.getContentPane().add(comboBoxSec);
		
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0)
			{
				try
				{
					String selt = (String)comboBoxSec.getSelectedItem();
					String sec = "select S_name,S_id,Class,Section,Dep_name from StudentInfo left join Department on StudentInfo.S_Dept = Department.Dep_id where "+selt+"=? ";
			        
					PreparedStatement spct = con.prepareStatement(sec);
					spct.setString(1, search.getText());
					
					ResultSet ssr = spct.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(ssr));
					
					spct.close();
					
				}catch(Exception sht)
				{
					JOptionPane.showMessageDialog(null, sht);
				}
				
			}
			});
		search.setBounds(897, 119, 141, 20);
		frame.getContentPane().add(search);
		search.setColumns(10);
		
		
		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginHome ob1 = new LoginHome();
				ob1.main(null);
				frame.dispose();
			}
		});
		btnHome.setBounds(28, 14, 89, 23);
		frame.getContentPane().add(btnHome);
		
		btnResultEntry = new JButton("Result Entry");
		btnResultEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnResultEntry.setBounds(127, 13, 112, 25);
		frame.getContentPane().add(btnResultEntry);
		
		btnExamFees = new JButton("Exam Fees");
		btnExamFees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			
				JInternalFrame internalFrame_fees = new JInternalFrame("Exam Fees Payment");
				internalFrame_fees.getContentPane().setBackground(new Color(51, 102, 153));
				internalFrame_fees.setClosable(true);
				internalFrame_fees.setBounds(10, 104, 500, 410);
				frame.getContentPane().add(internalFrame_fees);
				internalFrame_fees.getContentPane().setLayout(null);
				
				fsid = new JTextField();
				fsid.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						
						try
						{
							
							String fssn = "select S_name from StudentInfo where S_id=? ";
							PreparedStatement fspt = con.prepareStatement(fssn);
							
							fspt.setString(1, fsid.getText());
							ResultSet fsrs = fspt.executeQuery();
							
							while(fsrs.next())
							{
								fsnam.setText(fsrs.getString("S_name"));
								
							}
							
							fspt.close();
						}catch(Exception e10)
						{
							JOptionPane.showMessageDialog(null, e10);
						}
						
					}
				});
				fsid.setBounds(116, 80, 120, 24);
				internalFrame_fees.getContentPane().add(fsid);
				fsid.setColumns(10);
				
				JLabel lblStudentId_1 = new JLabel("Student ID");
				lblStudentId_1.setForeground(new Color(255, 153, 51));
				lblStudentId_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblStudentId_1.setBounds(10, 81, 78, 23);
				internalFrame_fees.getContentPane().add(lblStudentId_1);
				
				JLabel lblStudentName_1 = new JLabel("Student Name");
				lblStudentName_1.setForeground(new Color(255, 153, 0));
				lblStudentName_1.setBackground(new Color(0, 102, 153));
				lblStudentName_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblStudentName_1.setBounds(10, 126, 88, 26);
				internalFrame_fees.getContentPane().add(lblStudentName_1);
				
				fsnam = new JTextField();
				fsnam.setBounds(116, 129, 120, 24);
				internalFrame_fees.getContentPane().add(fsnam);
				fsnam.setColumns(10);
				
				lblPaymentDate = new JLabel("Payment Date");
				lblPaymentDate.setForeground(new Color(255, 153, 51));
				lblPaymentDate.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblPaymentDate.setBounds(10, 181, 115, 26);
				internalFrame_fees.getContentPane().add(lblPaymentDate);
				
				fstym = new JTextField();
				fstym.setBounds(116, 185, 120, 24);
				internalFrame_fees.getContentPane().add(fstym);
				fstym.setColumns(10);
				
				lblExamFees = new JLabel("Exam Fees");
				lblExamFees.setForeground(new Color(255, 153, 51));
				lblExamFees.setFont(new Font("Segoe UI", Font.BOLD, 16));
				lblExamFees.setBounds(184, 11, 97, 23);
				internalFrame_fees.getContentPane().add(lblExamFees);
				
				lblAmount = new JLabel("Amount");
				lblAmount.setForeground(new Color(255, 153, 0));
				lblAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblAmount.setBounds(10, 237, 88, 23);
				internalFrame_fees.getContentPane().add(lblAmount);
				
				fsama = new JTextField();
				fsama.setBounds(116, 237, 120, 23);
				internalFrame_fees.getContentPane().add(fsama);
				fsama.setColumns(10);
				
				btnConfrim = new JButton("Confrim");
				btnConfrim.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int action=JOptionPane.showConfirmDialog(null, "Confirm Payment ?","Confirm",JOptionPane.YES_NO_OPTION);
						if(action==0)
							try
						{
								String fes = "insert into Exam_fees(student_id,Fees_date,FAmount) values(?,?,?) ";
								PreparedStatement ffs = con.prepareStatement(fes);
								
								ffs.setString(1, fsid.getText());
								ffs.setString(2, fstym.getText());
								ffs.setString(3, fsama.getText());
								
								ffs.execute();
								
								JOptionPane.showMessageDialog(null, "Payment Sucessfull");
								ffs.close();
						}catch(Exception e12)
						{
							JOptionPane.showMessageDialog(null, e12);
						}
						
					}
				});
				btnConfrim.setBounds(172, 307, 89, 23);
				internalFrame_fees.getContentPane().add(btnConfrim);
				internalFrame_fees.setVisible(true);
			}
		});
		btnExamFees.setBounds(262, 13, 102, 25);
		frame.getContentPane().add(btnExamFees);
		
		
		
		JButton btnAdmit = new JButton("Register");
		btnAdmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame internalFrame_sf = new JInternalFrame("Student Information");
				internalFrame_sf.setBounds(10, 210, 542, 460);
				frame.getContentPane().add(internalFrame_sf);
				internalFrame_sf.setClosable(true);
				internalFrame_sf.setBorder(new CompoundBorder());
				internalFrame_sf.getContentPane().setBackground(SystemColor.inactiveCaption);
				internalFrame_sf.setResizable(true);
				internalFrame_sf.getContentPane().setLayout(null);
				
				JLabel lblStudentName = new JLabel("Student Name");
				lblStudentName.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblStudentName.setBounds(22, 82, 100, 32);
				internalFrame_sf.getContentPane().add(lblStudentName);
				
				JLabel lblNewLabel = new JLabel("Department");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel.setBounds(341, 64, 89, 23);
				internalFrame_sf.getContentPane().add(lblNewLabel);
				
				JLabel lblClass = new JLabel("Class");
				lblClass.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblClass.setBounds(32, 133, 46, 14);
				internalFrame_sf.getContentPane().add(lblClass);
				
				JLabel lblSection = new JLabel("Section");
				lblSection.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblSection.setBounds(32, 175, 46, 14);
				internalFrame_sf.getContentPane().add(lblSection);
				
				JLabel lblCourse = new JLabel("Course");
				lblCourse.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblCourse.setBounds(32, 216, 46, 14);
				internalFrame_sf.getContentPane().add(lblCourse);
				
				adstuname = new JTextField();
				adstuname.setBounds(134, 88, 120, 26);
				internalFrame_sf.getContentPane().add(adstuname);
				adstuname.setColumns(10);
				
				depclass = new JTextField();
				depclass.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						try
						{
							String shcr = "select course1,course2,course3,course4 from Class where Class_id=? ";
							PreparedStatement crps = con.prepareStatement(shcr);
							
							crps.setString(1, depclass.getText());
							ResultSet crsr = crps.executeQuery();
							
							while(crsr.next())
								{
								     deptc1.setText(crsr.getString("course1"));
								     deptc2.setText(crsr.getString("course2"));
								     deptc3.setText(crsr.getString("course3"));
								     deptc4.setText(crsr.getString("course4"));
								
								}
							crps.close();
						}catch(Exception e11)
						{
							JOptionPane.showMessageDialog(null, e11);
						}
						
						
					}
				});
				depclass.setBounds(134, 131, 120, 23);
				internalFrame_sf.getContentPane().add(depclass);
				depclass.setColumns(10);
				
				deptsec = new JTextField();
				deptsec.setBounds(134, 173, 120, 23);
				internalFrame_sf.getContentPane().add(deptsec);
				deptsec.setColumns(10);
				
				deptc1 = new JTextField();
				deptc1.setBounds(134, 213, 120, 23);
				internalFrame_sf.getContentPane().add(deptc1);
				deptc1.setColumns(10);
				
				deptc2 = new JTextField();
				deptc2.setBounds(134, 248, 120, 23);
				internalFrame_sf.getContentPane().add(deptc2);
				deptc2.setColumns(10);
				
				deptc3 = new JTextField();
				deptc3.setBounds(134, 282, 120, 23);
				internalFrame_sf.getContentPane().add(deptc3);
				deptc3.setColumns(10);
				
				deptc4 = new JTextField();
				deptc4.setBounds(134, 316, 120, 23);
				internalFrame_sf.getContentPane().add(deptc4);
				deptc4.setColumns(10);
				
				JButton deptSave = new JButton("Save");
				deptSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							String dpsqre = "insert into StudentInfo(S_name,Class,Section,S_Dept,Course1,Course2,Course3,Course4) values (?,?,?,?,?,?,?,?) ";
							PreparedStatement dpsts = con.prepareStatement(dpsqre);
							
							dpsts.setString(1, adstuname.getText());
							dpsts.setString(2, depclass.getText());
							dpsts.setString(3, deptsec.getText());
							dpsts.setString(4, Dpnam);
							
							dpsts.setString(5, deptc1.getText());
							dpsts.setString(6, deptc2.getText());
							dpsts.setString(7, deptc3.getText());
							dpsts.setString(8, deptc4.getText());
							
							
							
							dpsts.execute();
							
							JOptionPane.showMessageDialog(null, "Data Saved");
							
							dpsts.close();
							
							
						}catch(Exception dps)
						{
							JOptionPane.showMessageDialog(null, dps);
						}
						
						
					}
				});
				deptSave.setForeground(SystemColor.textHighlight);
				deptSave.setFont(new Font("Rockwell", Font.BOLD, 11));
				deptSave.setBounds(372, 248, 89, 23);
				internalFrame_sf.getContentPane().add(deptSave);
				
				JButton btnUpdate = new JButton("Update");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try
						{
							String stup = "update StudentInfo  set S_name=' "+adstuname.getText()+" ', Class= ' "+depclass.getText()+" ', Section = ' "+deptsec.getText()+" ', S_Dept = ' "+Dpnam+" ' ,Course1 = ' "+deptc1.getText()+" ', Course2 = ' "+deptc2.getText()+" ',Course3 = ' "+deptc3.getText()+" ', Course4 = ' "+deptc4.getText()+" ' where S_id = ' "+adsid.getText()+" ' "; 
						    
							PreparedStatement pstup = con.prepareStatement(stup);
							
							pstup.execute();
							
							JOptionPane.showMessageDialog(null, "Updated");
							
							pstup.close();
						}catch(Exception stdup)
						{
							JOptionPane.showMessageDialog(null, stdup);
						}
						
					}
				});
				btnUpdate.setForeground(SystemColor.textHighlight);
				btnUpdate.setFont(new Font("Rockwell", Font.BOLD, 11));
				btnUpdate.setBounds(372, 293, 89, 23);
				internalFrame_sf.getContentPane().add(btnUpdate);
				
				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int action=JOptionPane.showConfirmDialog(null, "Do you really wanted to delete?","Deleted",JOptionPane.YES_NO_OPTION);
						if(action==0)
						{
							try {
								String sdqr = "delete from StudentInfo where S_id =' "+adsid.getText()+" ' ";
								PreparedStatement psdt = con.prepareStatement(sdqr);
								
								psdt.execute();
								JOptionPane.showMessageDialog(null, "Deleted");
								
								psdt.close();
							}catch(Exception dps)
							{
								JOptionPane.showMessageDialog(null, dps);
							}
						}
						
					}
				});
				btnDelete.setToolTipText("Provide Student ID to delete");
				btnDelete.setForeground(SystemColor.textHighlight);
				btnDelete.setFont(new Font("Rockwell", Font.BOLD, 11));
				btnDelete.setBounds(372, 343, 89, 23);
				internalFrame_sf.getContentPane().add(btnDelete);
				
				adsid = new JTextField();
				adsid.setToolTipText("Provide Student ID for only Delete and Update Purpose");
				adsid.setBounds(134, 39, 120, 27);
				internalFrame_sf.getContentPane().add(adsid);
				adsid.setColumns(10);
				
				JLabel lblStudentId = new JLabel("Student ID");
				lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblStudentId.setBounds(22, 43, 89, 23);
				internalFrame_sf.getContentPane().add(lblStudentId);
				
				JLabel lblNewLabel_1 = new JLabel("* For adding new student data don't provide Student ID");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
				lblNewLabel_1.setForeground(new Color(255, 0, 0));
				lblNewLabel_1.setBounds(264, 39, 257, 14);
				internalFrame_sf.getContentPane().add(lblNewLabel_1);
				
				JRadioButton rdbtnCse = new JRadioButton("CSE");
				rdbtnCse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Dpnam = "CSE";
					}
				});
				buttonGroup.add(rdbtnCse);
				rdbtnCse.setForeground(new Color(0, 51, 255));
				rdbtnCse.setFont(new Font("Tahoma", Font.BOLD, 11));
				rdbtnCse.setBackground(SystemColor.inactiveCaption);
				rdbtnCse.setBounds(338, 94, 109, 23);
				internalFrame_sf.getContentPane().add(rdbtnCse);
				
				JRadioButton rdbtnSwe = new JRadioButton("SWE");
				rdbtnSwe.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Dpnam = "SWE";
					}
				});
				buttonGroup.add(rdbtnSwe);
				rdbtnSwe.setForeground(new Color(0, 51, 255));
				rdbtnSwe.setFont(new Font("Tahoma", Font.BOLD, 11));
				rdbtnSwe.setBackground(SystemColor.inactiveCaption);
				rdbtnSwe.setBounds(338, 130, 109, 23);
				internalFrame_sf.getContentPane().add(rdbtnSwe);
				
				JRadioButton rdbtnEee = new JRadioButton("EEE");
				rdbtnEee.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Dpnam = "EEE";
						
					}
				});
				buttonGroup.add(rdbtnEee);
				rdbtnEee.setForeground(new Color(0, 51, 255));
				rdbtnEee.setFont(new Font("Tahoma", Font.BOLD, 11));
				rdbtnEee.setBackground(SystemColor.inactiveCaption);
				rdbtnEee.setBounds(338, 166, 109, 23);
				internalFrame_sf.getContentPane().add(rdbtnEee);
				
				internalFrame_sf.setVisible(true);
				
				

	
			}
		});
		
		btnAdmit.setBounds(28, 63, 89, 23);
		frame.getContentPane().add(btnAdmit);
		
		btnWow = new JButton("Wow");
		btnWow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					String wow = "select S_id,S_name,Class from StudentInfo,Department where S_dept in(select Dep_id from Department where Dep_name ='EEE') ";
					PreparedStatement wps = con.prepareStatement(wow);
					
					ResultSet wrs = wps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(wrs));
				}catch(Exception e13)
				{
					JOptionPane.showMessageDialog(null, e13);
				}
				
			}
		});
		btnWow.setBounds(405, 63, 89, 23);
		frame.getContentPane().add(btnWow);
		
		JInternalFrame internalFrame_rs = new JInternalFrame("Result Entry");
		internalFrame_rs.setEnabled(false);
		internalFrame_rs.setResizable(true);
		internalFrame_rs.setClosable(true);
		internalFrame_rs.getContentPane().setBackground(new Color(255, 102, 153));
		internalFrame_rs.setBounds(328, 611, 207, 59);
		frame.getContentPane().add(internalFrame_rs);
		internalFrame_rs.getContentPane().setLayout(null);
		
		JLabel lblResultSheet = new JLabel("Result Sheet");
		lblResultSheet.setForeground(new Color(255, 255, 0));
		lblResultSheet.setFont(new Font("Segoe UI Black", Font.BOLD, 17));
		lblResultSheet.setBounds(200, 22, 137, 24);
		internalFrame_rs.getContentPane().add(lblResultSheet);
		
		JLabel lblStudentId_2 = new JLabel("Student ID");
		lblStudentId_2.setForeground(new Color(0, 0, 0));
		lblStudentId_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentId_2.setBounds(10, 114, 93, 24);
		internalFrame_rs.getContentPane().add(lblStudentId_2);
		
		JLabel lblClass_1 = new JLabel("Class");
		lblClass_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClass_1.setBounds(10, 163, 57, 14);
		internalFrame_rs.getContentPane().add(lblClass_1);
		
		JLabel lblCourse_1 = new JLabel("Course 1:");
		lblCourse_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblCourse_1.setBounds(10, 252, 57, 24);
		internalFrame_rs.getContentPane().add(lblCourse_1);
		
		JLabel lblCourse_2 = new JLabel("Course 2:");
		lblCourse_2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblCourse_2.setBounds(10, 287, 57, 24);
		internalFrame_rs.getContentPane().add(lblCourse_2);
		
		JLabel lblCourse_3 = new JLabel("Course 3:");
		lblCourse_3.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblCourse_3.setBounds(10, 322, 57, 14);
		internalFrame_rs.getContentPane().add(lblCourse_3);
		
		JLabel lblCourse_4 = new JLabel("Course 4:");
		lblCourse_4.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblCourse_4.setBounds(10, 355, 57, 14);
		internalFrame_rs.getContentPane().add(lblCourse_4);
		
		rc1 = new JTextField();
		rc1.setBounds(111, 255, 86, 20);
		internalFrame_rs.getContentPane().add(rc1);
		rc1.setColumns(10);
		
		rc2 = new JTextField();
		rc2.setBounds(111, 290, 86, 20);
		internalFrame_rs.getContentPane().add(rc2);
		rc2.setColumns(10);
		
		rc3 = new JTextField();
		rc3.setBounds(111, 320, 86, 20);
		internalFrame_rs.getContentPane().add(rc3);
		rc3.setColumns(10);
		
		rc4 = new JTextField();
		rc4.setBounds(111, 353, 86, 20);
		internalFrame_rs.getContentPane().add(rc4);
		rc4.setColumns(10);
		
		cal = new JTextField();
		cal.setBounds(383, 290, 86, 20);
		internalFrame_rs.getContentPane().add(cal);
		cal.setColumns(10);
		
		JButton btnCal = new JButton("GPA");
		btnCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnCal.setBounds(383, 252, 89, 23);
		internalFrame_rs.getContentPane().add(btnCal);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String rqe = "insert into Result(student_id,marks1,marks2,marks3,marks4) values (?,?,?,?,?) ";
					PreparedStatement rpst = con.prepareStatement(rqe);
					
					rpst.setString(1, rsid.getText());
					rpst.setString(2, mr1.getText());
					rpst.setString(3, mr2.getText());
					rpst.setString(4, mr3.getText());
					rpst.setString(5, mr4.getText());
					
					rpst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Saved");
					
					rpst.close();
				}catch(Exception e15)
				{
					JOptionPane.showMessageDialog(null, e15);
				}
				
			}
		});
		btnSave.setBounds(380, 390, 89, 23);
		internalFrame_rs.getContentPane().add(btnSave);
		
		rsid = new JTextField();
		rsid.setBounds(111, 117, 86, 20);
		internalFrame_rs.getContentPane().add(rsid);
		rsid.setColumns(10);
		
		rcla = new JTextField();
		rcla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try				
				{
					String sub = "select course1,course2,course3,course4 from Class where Class_id=?";
					PreparedStatement sbps = con.prepareStatement(sub);
					sbps.setString(1, rcla.getText());
					
					ResultSet rbrs = sbps.executeQuery();
					while(rbrs.next())
					{
						rc1.setText(rbrs.getString("course1"));
						rc2.setText(rbrs.getString("course2"));
						rc3.setText(rbrs.getString("course3"));
						rc4.setText(rbrs.getString("course4"));
						
					}
					rbrs.close();
				}catch(Exception e14)
				{
					JOptionPane.showMessageDialog(null, e14);
				}
				
				
			}
		});
		rcla.setBounds(111, 161, 86, 20);
		internalFrame_rs.getContentPane().add(rcla);
		rcla.setColumns(10);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseName.setBounds(111, 211, 86, 24);
		internalFrame_rs.getContentPane().add(lblCourseName);
		
		JLabel lblMarks = new JLabel("Marks");
		lblMarks.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMarks.setBounds(234, 216, 46, 14);
		internalFrame_rs.getContentPane().add(lblMarks);
		
		mr1 = new JTextField();
		mr1.setBounds(219, 255, 86, 20);
		internalFrame_rs.getContentPane().add(mr1);
		mr1.setColumns(10);
		
		mr2 = new JTextField();
		mr2.setBounds(219, 290, 86, 20);
		internalFrame_rs.getContentPane().add(mr2);
		mr2.setColumns(10);
		
		mr3 = new JTextField();
		mr3.setBounds(219, 320, 86, 20);
		internalFrame_rs.getContentPane().add(mr3);
		mr3.setColumns(10);
		
		mr4 = new JTextField();
		mr4.setBounds(219, 353, 86, 20);
		internalFrame_rs.getContentPane().add(mr4);
		mr4.setColumns(10);
		internalFrame_rs.setVisible(true);
		
		
		
	
		
		
	}
}
