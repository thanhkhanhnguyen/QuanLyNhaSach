package GUI;

import java.awt.Component;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import DAO.UserDAO;
import Model.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

public class UpdateUserForm extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldName;
	private JDatePickerImpl datePicker;
	private JTextField textFieldPhone;
	private JTextPane textFieldAddress;
	// private SpringLayout springLayout;
	private JTextField textFieldPass;
	private JButton btnDelete;
	private String textPass;
	private int flag=1;
	private JPasswordField passwordField;
	static ManageUserForm form;
	
	public static ManageUserForm getForm() {
		return form;
	}

	public static void setForm(ManageUserForm form) {
		UpdateUserForm.form = form;
	}
	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { UpdateUserForm frame = new
	 * UpdateUserForm(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * } }); }
	 */

	/**
	 * Create the frame.
	 */
	public UpdateUserForm() {
		setTitle("UpdateUserForm");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// JDatePickerImpl datePicker;
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
		// springLayout.putConstraint(SpringLayout.WEST,
		// datePicker.getJFormattedTextField(), -181, SpringLayout.EAST, datePicker);
		// springLayout.putConstraint(SpringLayout.EAST,
		// datePicker.getJFormattedTextField(), -30, SpringLayout.EAST, datePicker);
		// springLayout = (SpringLayout) datePicker.getLayout();

		datePicker.setBounds(124, 115, 195, 24);
		contentPane.add(datePicker);

		JLabel lblUserId = new JLabel("USER ID");
		lblUserId.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUserId.setBounds(10, 25, 72, 24);
		contentPane.add(lblUserId);

		textFieldId = new JTextField();
		textFieldId.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldId.setColumns(10);
		textFieldId.setBounds(124, 25, 90, 24);
		contentPane.add(textFieldId);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldName.setColumns(10);
		textFieldName.setBounds(124, 69, 328, 24);
		contentPane.add(textFieldName);

		JLabel lblUserName = new JLabel("USER NAME");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUserName.setBounds(10, 69, 98, 24);
		contentPane.add(lblUserName);

		JLabel lblBirthday = new JLabel("BIRTHDAY");
		lblBirthday.setHorizontalAlignment(SwingConstants.CENTER);
		lblBirthday.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBirthday.setBounds(10, 115, 90, 24);
		contentPane.add(lblBirthday);

		JLabel lblPhone = new JLabel("PHONE");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPhone.setBounds(10, 162, 66, 24);
		contentPane.add(lblPhone);

		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAddress.setBounds(10, 216, 90, 24);
		contentPane.add(lblAddress);

		textFieldAddress = new JTextPane();
		textFieldAddress.setBounds(124, 216, 405, 66);
		contentPane.add(textFieldAddress);

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
		textFieldPhone.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(124, 166, 328, 24);
		contentPane.add(textFieldPhone);

		JButton btnSave = new JButton("SAVE");
		btnSave.addMouseListener(new MouseAdapter() {
			Component a = null;

			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(datePicker.getJFormattedTextField().getText());
				// datePicker.getJFormattedTextField().setText("2021-11-09");
				if (textFieldisEmpty()) {
					String id = textFieldId.getText().trim();
					String name = textFieldName.getText().trim();
					String birthday = datePicker.getJFormattedTextField().getText();
					String phone = textFieldPhone.getText().trim();
					String address = textFieldAddress.getText().trim();
					String pass = textPass;
					User us = new User(id, name, birthday, phone, address, pass, 1);

					if (ManageUserForm.getFlag() == 1) {
						try {
							if (UserDAO.CheckIdExist(id) == 0) {

								try {
									UserDAO.AddUser(us);
									JOptionPane.showMessageDialog(a, "Complete", "Add User",
											JOptionPane.INFORMATION_MESSAGE);

								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								/*try {
									ManageUserForm f = new ManageUserForm();
									f.setVisible(true);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}*/
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(a, "Id already exists", "Add User",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {
						// JOptionPane.showMessageDialog(a, "Update");
						try {
							if (UserDAO.UpdateUser(us) > 0) {
								JOptionPane.showMessageDialog(a, "Complete", "Update User",
										JOptionPane.INFORMATION_MESSAGE);
								/*try {
									ManageUserForm f = new ManageUserForm();
									f.setVisible(true);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}*/
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
				} else {
					JOptionPane.showMessageDialog(a, "TextField is Empty", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
				
				try {
					
					form.LoadData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			

		});
		btnSave.setIcon(new ImageIcon(UpdateUserForm.class.getResource("/image/Save as.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(10, 300, 109, 38);
		contentPane.add(btnSave);

		btnDelete = new JButton("DELETE");
		btnDelete.addMouseListener(new MouseAdapter() {
			Component a = null;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFieldId.getText().isEmpty() == false) {
					String id = textFieldId.getText().trim();
					try {
						if (UserDAO.CheckIdExist(id) != 0) {
							UserDAO.DeleteUser(id);
							JOptionPane.showMessageDialog(a, "Complete", "Delete User",
									JOptionPane.INFORMATION_MESSAGE);
							/*try {
								ManageUserForm f = new ManageUserForm();
								f.setVisible(true);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}*/
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(a, "Id does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(a, "TextField Id  is Empty", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
				
				try {
					
					form.LoadData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnDelete.setIcon(new ImageIcon(UpdateUserForm.class.getResource("/image/Delete.png")));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(223, 300, 123, 38);
		contentPane.add(btnDelete);

		JLabel lblPass = new JLabel("PASS");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPass.setBounds(231, 25, 49, 24);
		contentPane.add(lblPass);

		textFieldPass = new JTextField();
		textFieldPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				textPass = textFieldPass.getText();
			}
		});
		textFieldPass.setText((String) null);
		textFieldPass.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldPass.setColumns(10);
		textFieldPass.setBounds(290, 25, 162, 24);
		contentPane.add(textFieldPass);
		
		JButton btnExit = new JButton("CLOSE");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				setVisible(false);

			}
		});
		btnExit.setIcon(new ImageIcon(UpdateUserForm.class.getResource("/image/Exit.png")));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(420, 300, 109, 38);
		contentPane.add(btnExit);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(flag == 1) {
					passwordField.setVisible(false);
					textFieldPass.setVisible(true);
					textFieldPass.setText(textPass);
					flag=2;
				}else {
					textFieldPass.setVisible(false);
					passwordField.setVisible(true);
					passwordField.setText(textPass);
					flag=1;
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(UpdateUserForm.class.getResource("/image/baseline_visibility_off_black_24dp.png")));
		btnNewButton.setBounds(461, 11, 68, 48);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {
				textPass = passwordField.getText();
			}
		});
		passwordField.setBounds(290, 25, 162, 24);
		contentPane.add(passwordField);
		// ========================================================================
		
		LoadData(ManageUserForm.getUs());
		
		textFieldPass.setVisible(false);
		
	}

	public void LoadData(User us) {
		textFieldId.setText(us.getId());
		textFieldName.setText(us.getName());
		datePicker.getJFormattedTextField().setText(us.getBirthDay());
		textFieldPhone.setText(us.getPhone());
		textFieldAddress.setText(us.getAddress());
		textPass = us.getPassWord();
		textFieldPass.setText(textPass);
		passwordField.setText(textPass);
	}

	public boolean textFieldisEmpty() {
                if (textFieldId.getText().isEmpty()) return false;
                if (textFieldName.getText().isEmpty()) return false;
                if (datePicker.getJFormattedTextField().getText().isEmpty()) return false;
                if (textFieldPhone.getText().isEmpty()) return false;
                if (textFieldAddress.getText().isEmpty()) return false;
                return true;
	}
        

	public JTextField getTextFieldId() {
		return textFieldId;
	}

	public void setTextFieldId(JTextField textFieldId) {
		this.textFieldId = textFieldId;
	}

	public JTextField getTextFieldName() {
		return textFieldName;
	}

	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(JDatePickerImpl datePicker) {
		this.datePicker = datePicker;
	}

	public JTextField getTextFieldPhone() {
		return textFieldPhone;
	}

	public void setTextFieldPhone(JTextField textFieldPhone) {
		this.textFieldPhone = textFieldPhone;
	}

	public JTextPane getTextFieldAddress() {
		return textFieldAddress;
	}

	public void setTextFieldAddress(JTextPane textFieldAddress) {
		this.textFieldAddress = textFieldAddress;
	}

	public JTextField getTextFieldPass() {
		return textFieldPass;
	}

	public void setTextFieldPass(JTextField textFieldPass) {
		this.textFieldPass = textFieldPass;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
}
