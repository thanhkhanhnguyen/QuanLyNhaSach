package GUI;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.AuthorDAO;
import Model.Author;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateAuthorForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextPane textFieldInfo;
	private JRadioButton radioButtonFemale;
	private JRadioButton radioButtonMale;
	private ButtonGroup bg = new ButtonGroup();
	private JButton btnDelete;
	static ManageAuthorForm form;
	
	public static ManageAuthorForm getForm() {
		return form;
	}

	public static void setForm(ManageAuthorForm form) {
		UpdateAuthorForm.form = form;
	}
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAuthorForm frame = new UpdateAuthorForm();
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
	public UpdateAuthorForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldName = new JTextField();
		textFieldName.setText((String) null);
		textFieldName.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldName.setColumns(10);
		textFieldName.setBounds(125, 46, 278, 25);
		contentPane.add(textFieldName);
		
		JLabel lblAuthorName = new JLabel("NAME");
		lblAuthorName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAuthorName.setBounds(22, 46, 48, 24);
		contentPane.add(lblAuthorName);
		
		JLabel lblGender = new JLabel("GENDER");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setFont(new Font("Dialog", Font.BOLD, 15));
		lblGender.setBounds(22, 104, 65, 24);
		contentPane.add(lblGender);
		
		JLabel lblInformate = new JLabel("INFORMATION");
		lblInformate.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformate.setFont(new Font("Dialog", Font.BOLD, 15));
		lblInformate.setBounds(6, 166, 109, 24);
		contentPane.add(lblInformate);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addMouseListener(new MouseAdapter() {
			Component a= null;
			@Override
			public void mouseClicked(MouseEvent e) {
				if(TextFieldisEmpty()== true) {
					int id = ManageAuthorForm.getAuthor().getId();
					String name=textFieldName.getText().trim();
					String info = textFieldInfo.getText().trim();
					String gender;
					if(radioButtonMale.isSelected() == true) {
						gender="Male";
					}
					else
					{
						gender="Female";
					}
					
					Author au= new Author(id,name,gender,info);
					if(ManageAuthorForm.getFlag() ==1) {
						try {
							AuthorDAO.AddAuthor(au);
							JOptionPane.showMessageDialog(a, "Complete", "Add User",
									JOptionPane.INFORMATION_MESSAGE);
							/*ManageAuthorForm f = new ManageAuthorForm();
							f.setVisible(true);*/
							setVisible(false);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						try {
							if (AuthorDAO.UpdateAuthor(au) > 0) {
								JOptionPane.showMessageDialog(a, "Complete", "Update User",
										JOptionPane.INFORMATION_MESSAGE);
								
								/*ManageAuthorForm f = new ManageAuthorForm();
								f.setVisible(true);*/
								setVisible(false);
								
								
							} else {
								JOptionPane.showMessageDialog(a, "Fail", "Update User", JOptionPane.ERROR_MESSAGE);
							}
						} catch (HeadlessException | IOException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else {
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
		btnSave.setIcon(new ImageIcon(UpdateAuthorForm.class.getResource("/image/Save.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(22, 288, 109, 38);
		contentPane.add(btnSave);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addMouseListener(new MouseAdapter() {
			Component a = null;
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					AuthorDAO.DeleteAuthor(ManageAuthorForm.getAuthor().getId());
					JOptionPane.showMessageDialog(a, "Complete", "Delete Author",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				/*ManageAuthorForm f;
				try {
					f = new ManageAuthorForm();
					f.setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
				setVisible(false);
				try {
					form.LoadData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(UpdateAuthorForm.class.getResource("/image/Delete.png")));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(148, 288, 127, 38);
		contentPane.add(btnDelete);
		
		radioButtonMale = new JRadioButton("MALE");
		radioButtonMale.setFont(new Font("Dialog", Font.BOLD, 12));
		radioButtonMale.setBounds(137, 106, 109, 23);
		contentPane.add(radioButtonMale);
		
		radioButtonFemale = new JRadioButton("FEMALE");
		radioButtonFemale.setFont(new Font("Dialog", Font.BOLD, 12));
		radioButtonFemale.setBounds(279, 106, 109, 23);
		contentPane.add(radioButtonFemale);
		
		bg.add(radioButtonFemale);
		bg.add(radioButtonMale);
		
		textFieldInfo = new JTextPane();
		textFieldInfo.setBounds(125, 166, 278, 71);
		contentPane.add(textFieldInfo);
		
		JButton btnExit = new JButton("CLOSE");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*ManageAuthorForm f;
				try {
					f = new ManageAuthorForm();
					f.setVisible(true);
					setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				setVisible(false);
			}
		});
		btnExit.setIcon(new ImageIcon(UpdateAuthorForm.class.getResource("/image/Log out.png")));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(294, 288, 109, 38);
		contentPane.add(btnExit);
		
		LoadData(ManageAuthorForm.getAuthor());
		
		
	}
	
	public void LoadData(Author au) {
		//textFieldId.setText(au.getId());
		textFieldName.setText(au.getName());
		String gender = au.getGender();
	
		if(gender.equals("Male")) {
				radioButtonMale.setSelected(true);
		}
		else if(gender.equals("Female")) {
				radioButtonFemale.setSelected(true);
		}
		
		
		
		textFieldInfo.setText(au.getInformation());
	}
	
	public boolean TextFieldisEmpty() {
		if(textFieldName.getText().isEmpty()==true || textFieldInfo.getText().isEmpty()==true || (radioButtonMale.isSelected()==false && radioButtonFemale.isSelected()==false ) ) {
			return false;
		}
		
		return true;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
}
