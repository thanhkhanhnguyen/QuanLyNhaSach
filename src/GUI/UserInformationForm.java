package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import DAO.UserDAO;
import Model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserInformationForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldPass;
	private JTextField textFieldName;
	private JTextField textFieldPhone;
	private JDatePickerImpl datePicker;
	private JTextPane textFieldAddress;
	private static UserMainForm form;
	private String passWord;
	private String textPass;
	private int flag = 1;
	
	public static UserMainForm getForm() {
		return form;
	}

	public static void setForm(UserMainForm form) {
		UserInformationForm.form = form;
	}

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { UserInformationForm frame = new
	 * UserInformationForm(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public UserInformationForm() {
		setTitle("UserInformation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.MAGENTA);
		panel.setBounds(0, 0, 235, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new AbstractFormatter() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object stringToValue(String text) throws ParseException {
				// TODO Auto-generated method stub
				SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
				return dateFormatter.parseObject(text);
			}

			@Override
			public String valueToString(Object value) throws ParseException {
				// TODO Auto-generated method stub
				if (value != null) {
					Calendar cal = (Calendar) value;
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String strDate = format.format(cal.getTime());
					return strDate;
				}

				return "";
			}

		});
		datePicker.getJFormattedTextField().setFont(new Font("Tahoma", Font.BOLD, 11));
		// springLayout.putConstraint(SpringLayout.WEST,
		// datePicker.getJFormattedTextField(), -181, SpringLayout.EAST, datePicker);
		// springLayout.putConstraint(SpringLayout.EAST,
		// datePicker.getJFormattedTextField(), -30, SpringLayout.EAST, datePicker);
		// springLayout = (SpringLayout) datePicker.getLayout();

		datePicker.setBounds(158, 210, 195, 24);
		contentPane.add(datePicker);
		
		JLabel lblNewLabel = new JLabel("USER INFORMATION");
		lblNewLabel.setIcon(new ImageIcon(UserInformationForm.class.getResource("/image/baseline_account_box_black_24dp.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(0, 0, 235, 85);
		panel.add(lblNewLabel);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setText((String) null);
		textFieldId.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldId.setColumns(10);
		textFieldId.setBounds(355, 61, 142, 24);
		contentPane.add(textFieldId);
		
		JLabel lblUserId = new JLabel("USER ID");
		lblUserId.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUserId.setBounds(245, 61, 72, 24);
		contentPane.add(lblUserId);
		
		JLabel lblPass = new JLabel("PASSWORD");
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPass.setBounds(44, 116, 98, 24);
		contentPane.add(lblPass);
		
		textFieldPass = new JTextField();
		textFieldPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				textPass = textFieldPass.getText();
				
			}
			/*@Override
			public void keyPressed(KeyEvent e) {
				textPass = textFieldPass.getText();
				System.out.print(textPass);
			}*/
			/*@Override
			public void keyTyped(KeyEvent e) {
				textPass = textFieldPass.getText();
				System.out.print(textPass);
			}*/
		});
		
		textFieldPass.setEditable(false);
		textFieldPass.setText((String) null);
		textFieldPass.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldPass.setColumns(10);
		textFieldPass.setBounds(158, 116, 258, 24);
		contentPane.add(textFieldPass);
		
		JLabel lblUserName = new JLabel("USER NAME");
		lblUserName.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUserName.setBounds(44, 159, 98, 24);
		contentPane.add(lblUserName);
		
		textFieldName = new JTextField();
		textFieldName.setText((String) null);
		textFieldName.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldName.setColumns(10);
		textFieldName.setBounds(158, 159, 339, 24);
		contentPane.add(textFieldName);
		
		JLabel lblPhone = new JLabel("PHONE");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPhone.setBounds(44, 252, 66, 24);
		contentPane.add(lblPhone);
		
		textFieldPhone = new JTextField();
		textFieldPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(! Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textFieldPhone.setText((String) null);
		textFieldPhone.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(158, 256, 339, 24);
		contentPane.add(textFieldPhone);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAddress.setBounds(44, 306, 90, 24);
		contentPane.add(lblAddress);
		
		textFieldAddress = new JTextPane();
		textFieldAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldAddress.setText((String) null);
		textFieldAddress.setBounds(158, 306, 339, 66);
		contentPane.add(textFieldAddress);
		
		JLabel lblBirthday = new JLabel("BIRTHDAY");
		lblBirthday.setHorizontalAlignment(SwingConstants.LEFT);
		lblBirthday.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBirthday.setBounds(44, 210, 90, 24);
		contentPane.add(lblBirthday);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component a =null;
				if (textFieldisEmpty()) {
					String id = textFieldId.getText().trim();
					String name = textFieldName.getText().trim();
					String birthday = datePicker.getJFormattedTextField().getText();
					String phone = textFieldPhone.getText().trim();
					String address = textFieldAddress.getText().trim();
					String pass = textPass;
					User us = new User(id, name, birthday, phone, address, pass, 1);
					
					try {
						if (UserDAO.UpdateUser(us) > 0) {
							JOptionPane.showMessageDialog(a, "Complete", "Update User",
									JOptionPane.INFORMATION_MESSAGE);
							form.LoadLabel();
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(a, "Fail", "Update User", JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(a, "TextField is Empty", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
					
					
				
		});
		btnSave.setIcon(new ImageIcon(UserInformationForm.class.getResource("/image/Save.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(388, 11, 109, 38);
		contentPane.add(btnSave);
		
		JButton btnPassWord = new JButton("");
		btnPassWord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(flag == 1) {
					textFieldPass.setText(textPass);
					textFieldPass.setEditable(true);
					flag =2;
				}
				else {
					textFieldPass.setText(setTextFieldPassWord());
					textFieldPass.setEditable(false);
					flag = 1;
				}
			}
		});
		btnPassWord.setIcon(new ImageIcon(UserInformationForm.class.getResource("/image/baseline_visibility_off_black_24dp.png")));
		btnPassWord.setBounds(433, 108, 60, 38);
		contentPane.add(btnPassWord);
		
		LoadData(UserMainForm.getUs());
		
	}
	
	
	//==============
	public void LoadData(User us) {
		textFieldId.setText(us.getId());
		textFieldName.setText(us.getName());
		datePicker.getJFormattedTextField().setText(us.getBirthDay());
		textFieldPhone.setText(us.getPhone());
		textFieldAddress.setText(us.getAddress());
		passWord = us.getPassWord();
		textPass = us.getPassWord();
		textFieldPass.setText(setTextFieldPassWord());
		
	}
	
	public boolean textFieldisEmpty() {
		if (textFieldId.getText().isEmpty() == true || textFieldName.getText().isEmpty() == true
				|| datePicker.getJFormattedTextField().getText().isEmpty() == true
				|| textFieldPhone.getText().isEmpty() == true || textFieldAddress.getText().isEmpty() == true
				|| textFieldPass.getText().isEmpty() == true) {
			return false;
		}
		return true;
	}
	
	public String setTextFieldPassWord() {
		String pass ="*";
		for(int i=1;i<textPass.length();i++)
		{
			pass = pass + "*";
		}
		
		return pass;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
