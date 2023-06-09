package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UserDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class LoginForm extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField textFieldPass;
	private ButtonGroup bg = new ButtonGroup();
	private static String id_user;
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setTitle("LoginForm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginForm = new JLabel("LOGIN FORM");
		lblLoginForm.setFont(new Font("Dialog", Font.BOLD, 25));
		lblLoginForm.setBounds(208, 31, 175, 24);
		contentPane.add(lblLoginForm);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblId.setBounds(30, 122, 112, 24);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("PassWord");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPassword.setBounds(30, 173, 112, 24);
		contentPane.add(lblPassword);
		
		JTextField textFieldId = new JTextField();
		textFieldId.setToolTipText("username");
		textFieldId.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldId.setColumns(10);
		textFieldId.setBounds(172, 124, 211, 24);
		contentPane.add(textFieldId);
		
		textFieldPass = new JPasswordField();
		textFieldPass.setBounds(172, 176, 211, 25);
		contentPane.add(textFieldPass);
		
		JRadioButton rdbtnUser = new JRadioButton("User",true);
		rdbtnUser.setFont(new Font("Dialog", Font.BOLD, 15));
		rdbtnUser.setBounds(96, 218, 65, 23);
		//rdbtnNewRadioButton.
		contentPane.add(rdbtnUser);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setFont(new Font("Dialog", Font.BOLD, 15));
		rdbtnAdmin.setBounds(264, 218, 71, 23);
		contentPane.add(rdbtnAdmin);
		
		bg.add(rdbtnUser);
		bg.add(rdbtnAdmin);
		
		JLabel lblRegister = new JLabel("Sign Up");
		lblRegister.setIcon(new ImageIcon(LoginForm.class.getResource("/image/Add to basket.png")));
		lblRegister.setForeground(Color.BLUE);
		lblRegister.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblRegister.setBounds(172, 247, 95, 24);
		contentPane.add(lblRegister);
		
		JButton btnSignIn = new JButton("SIGN IN");
		btnSignIn.addMouseListener(new MouseAdapter() {
			Component panel = null;
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textFieldId.getText().isEmpty() || textFieldPass.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog (panel, 
                            "Id Or Password is Empty", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(rdbtnUser.isSelected() == true) {
						String id = textFieldId.getText().trim();
						String pass=textFieldPass.getText().trim();
						try {
							if(UserDAO.CheckUserExist(id,pass)==0) {
								JOptionPane.showMessageDialog (panel, "Id and Password are not correct");
							}
							else
							{	id_user=id;
								setVisible(false);
								UserMainForm f =new UserMainForm();
								UpdateMoneyForm.setForm(f);
								UserInformationForm.setForm(f);
								CartForm.setForm(f);
								f.setVisible(true);
								
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					else if (rdbtnAdmin.isSelected()==true) {
						String id = textFieldId.getText().trim();
						String pass=textFieldPass.getText().trim();
						try {
							if(UserDAO.CheckAdminExist(id,pass)==0) {
								JOptionPane.showMessageDialog (panel, "Id and Password are not correct");
							}
							else
							{
								id_user=id;
								setVisible(false);
								AdminManinForm f =new AdminManinForm();
								f.setVisible(true);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}
					else {
						JOptionPane.showMessageDialog (panel, "User or Admin");
					}
				}
				
			}
		});
		btnSignIn.setIcon(new ImageIcon(LoginForm.class.getResource("/image/Accept.png")));
		btnSignIn.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSignIn.setBounds(261, 290, 122, 37);
		contentPane.add(btnSignIn);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnClose.setIcon(new ImageIcon(LoginForm.class.getResource("/image/Exit.png")));
		btnClose.setFont(new Font("Dialog", Font.BOLD, 14));
		btnClose.setBounds(53, 290, 118, 37);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginForm.class.getResource("/image/login.jpg")));
		lblNewLabel_1.setBounds(10, 11, 138, 99);
		contentPane.add(lblNewLabel_1);
	}


	public static String getId_user() {
		return id_user;
	}


	public static void setId_user(String id_user) {
		LoginForm.id_user = id_user;
	}

	
}
