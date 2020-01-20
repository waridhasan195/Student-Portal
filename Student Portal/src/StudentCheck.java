import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class StudentCheck {

	private JFrame frame;
	private JTextField search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentCheck window = new StudentCheck();
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
	public StudentCheck() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection con = null;
	private JTable table;
	private JTextField ruclas;
	private JTextField asid;
	private JTextField afnam;
	private JTextField alnam;
	private JTextField adob;
	private JTextField asadd;
	private JTextField acon;
	private String Gender;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private void initialize() {
		con = Connect.dbConnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 102, 153));
		frame.setBounds(100, 100, 1100, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblStudentCheck = new JLabel("Student Check");
		lblStudentCheck.setForeground(new Color(153, 255, 51));
		lblStudentCheck.setFont(new Font("Serif", Font.BOLD, 17));
		lblStudentCheck.setBounds(445, 11, 137, 41);
		frame.getContentPane().add(lblStudentCheck);
		
		JButton btnResult = new JButton("Result");
		btnResult.setBounds(45, 75, 89, 23);
		frame.getContentPane().add(btnResult);
		
		JButton btnRoutine = new JButton("Routine");
		btnRoutine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					String rutin = "select Co_name,Room_num,Class_time from Course inner join Room on Course.Co_id = Room.Course_idnam where class_id = ' "+ruclas.getText()+" '  ";
					PreparedStatement rups = con.prepareStatement(rutin);
					
					ResultSet rurs = rups.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rurs));
					
					
				}catch(Exception e14)
				{
					JOptionPane.showMessageDialog(null, e14);
				}
				
				
			}
		});
		btnRoutine.setBounds(243, 141, 89, 23);
		frame.getContentPane().add(btnRoutine);
				
				JComboBox comboBoxSec = new JComboBox();
				comboBoxSec.setBounds(942, 104, 74, 20);
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
				search.setBounds(766, 104, 141, 20);
				frame.getContentPane().add(search);
				search.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(535, 193, 481, 458);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		ruclas = new JTextField();
		ruclas.setBounds(135, 142, 86, 20);
		frame.getContentPane().add(ruclas);
		ruclas.setColumns(10);
		
		JLabel lblClassId = new JLabel("Class ID");
		lblClassId.setForeground(Color.ORANGE);
		lblClassId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClassId.setBounds(54, 145, 59, 19);
		frame.getContentPane().add(lblClassId);
		
		JInternalFrame internalFrame = new JInternalFrame("Addtional Student Information");
		internalFrame.setClosable(true);
		internalFrame.getContentPane().setBackground(new Color(0, 153, 255));
		internalFrame.setBounds(10, 201, 515, 450);
		frame.getContentPane().add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel lblAdditionalInformations = new JLabel("Additional Informations");
		lblAdditionalInformations.setForeground(new Color(255, 255, 0));
		lblAdditionalInformations.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
		lblAdditionalInformations.setBounds(171, 11, 174, 31);
		internalFrame.getContentPane().add(lblAdditionalInformations);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setForeground(new Color(0, 255, 0));
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentId.setBounds(10, 126, 83, 15);
		internalFrame.getContentPane().add(lblStudentId);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(new Color(102, 255, 51));
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setBounds(10, 152, 72, 14);
		internalFrame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(new Color(102, 255, 51));
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(10, 177, 72, 14);
		internalFrame.getContentPane().add(lblLastName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setForeground(new Color(102, 255, 51));
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDateOfBirth.setBounds(10, 202, 83, 14);
		internalFrame.getContentPane().add(lblDateOfBirth);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(new Color(0, 255, 0));
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGender.setBounds(10, 227, 57, 14);
		internalFrame.getContentPane().add(lblGender);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(new Color(0, 255, 0));
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(10, 262, 57, 14);
		internalFrame.getContentPane().add(lblAddress);
		
		JLabel lblPhoneNo = new JLabel("Contact No");
		lblPhoneNo.setForeground(new Color(0, 255, 0));
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhoneNo.setBounds(10, 299, 72, 14);
		internalFrame.getContentPane().add(lblPhoneNo);
		
		asid = new JTextField();
		asid.setBounds(103, 124, 116, 17);
		internalFrame.getContentPane().add(asid);
		asid.setColumns(10);
		
		afnam = new JTextField();
		afnam.setBounds(103, 150, 116, 16);
		internalFrame.getContentPane().add(afnam);
		afnam.setColumns(10);
		
		alnam = new JTextField();
		alnam.setBounds(102, 175, 117, 16);
		internalFrame.getContentPane().add(alnam);
		alnam.setColumns(10);
		
		adob = new JTextField();
		adob.setToolTipText("YY/MM/DD");
		adob.setBounds(103, 200, 116, 16);
		internalFrame.getContentPane().add(adob);
		adob.setColumns(10);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setForeground(new Color(0, 0, 0));
		rdbtnMale.setBackground(new Color(0, 153, 255));
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gender = "Male";
			}
		});
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(73, 227, 72, 14);
		internalFrame.getContentPane().add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBackground(new Color(0, 153, 255));
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gender = "Female";
			}
		});
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(159, 227, 60, 14);
		internalFrame.getContentPane().add(rdbtnFemale);
		
		asadd = new JTextField();
		asadd.setBounds(103, 260, 137, 31);
		internalFrame.getContentPane().add(asadd);
		asadd.setColumns(10);
		
		acon = new JTextField();
		acon.setBounds(103, 297, 125, 20);
		internalFrame.getContentPane().add(acon);
		acon.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String asq ="insert into Student_Additional(StudentID,FirstName,LastName,DOB,Gender,Address,ContactNO) values (?,?,?,?,?,?,?) ";
					PreparedStatement adp = con.prepareStatement(asq);
					
					adp.setString(1, asid.getText());
					adp.setString(2, afnam.getText());
					adp.setString(3, alnam.getText());
					adp.setString(4, adob.getText());
					adp.setString(5, Gender);
					adp.setString(6, asadd.getText());
					adp.setString(7, acon.getText());
					
					adp.execute();
					btnSave.setEnabled(true);
					
					JOptionPane.showMessageDialog(null, "Data Saved");
					
					adp.close();
					
				}catch(Exception e15)
				{
					JOptionPane.showMessageDialog(null, e15);
				}
				
				
			}
		});
		btnSave.setBounds(326, 347, 89, 23);
		internalFrame.getContentPane().add(btnSave);
		internalFrame.setVisible(true);
	}
}
