package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UserDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateMoneyForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMoney;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	static UserMainForm form;
	
	public static UserMainForm getForm() {
		return form;
	}

	public static void setForm(UserMainForm form) {
		UpdateMoneyForm.form = form;
	}

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateMoneyForm frame = new UpdateMoneyForm();
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
	public UpdateMoneyForm() {
		setTitle("UpdateMoney");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 124);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255,99,71));
		panel.setBounds(30, 21, 104, 30);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMoney = new JLabel("MONEY");
		lblMoney.setBounds(0, 0, 104, 30);
		panel.add(lblMoney);
		lblMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoney.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMoney.setBackground(Color.WHITE);
		
		textFieldMoney = new JTextField();
		textFieldMoney.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(! Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textFieldMoney.setBounds(163, 21, 73, 30);
		contentPane.add(textFieldMoney);
		textFieldMoney.setColumns(10);
		
		btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!textFieldMoney.getText().isEmpty()) {
					String id = UserMainForm.getUs().getId();
					int money= Integer.parseInt(textFieldMoney.getText().trim());
					
					//=============
					try {
						UserDAO.UpdateMoney(1,id, money);
						JOptionPane.showMessageDialog(null, "Complete", "Money", JOptionPane.INFORMATION_MESSAGE);
						form.LoadLabel();
						setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "TextField is Empty", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(UpdateMoneyForm.class.getResource("/image/Edit.png")));
		btnNewButton.setBounds(304, 21, 82, 30);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("$");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(245, 21, 22, 30);
		contentPane.add(lblNewLabel);
	}



	

	

}
