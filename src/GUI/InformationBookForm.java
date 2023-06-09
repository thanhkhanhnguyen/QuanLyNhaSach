package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.WorkDAO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class InformationBookForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUserId;
	private JTextField textUserName;
	private JTextField textUserPhone;
	private JTextField textUserBirthDay;
	private JTextField textBookId;
	private JTextField textBookName;
	private JTextField textLimit;
	private JTextField textPrice;
	private JTextField textBookAuthor;
	private JTextField textBookPublisher;
	private JTextPane textFieldAddress;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformationBookForm frame = new InformationBookForm();
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
	public InformationBookForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 965, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 485, 364);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUserId = new JLabel("USER ID");
		lblUserId.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUserId.setBounds(28, 77, 72, 24);
		panel.add(lblUserId);
		
		textUserId = new JTextField();
		textUserId.setHorizontalAlignment(SwingConstants.CENTER);
		textUserId.setEditable(false);
		textUserId.setText((String) null);
		textUserId.setFont(new Font("Dialog", Font.BOLD, 12));
		textUserId.setColumns(10);
		textUserId.setBounds(142, 77, 90, 24);
		panel.add(textUserId);
		
		JLabel lblUserName = new JLabel("USER NAME");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUserName.setBounds(28, 121, 98, 24);
		panel.add(lblUserName);
		
		textUserName = new JTextField();
		textUserName.setHorizontalAlignment(SwingConstants.CENTER);
		textUserName.setEditable(false);
		textUserName.setText((String) null);
		textUserName.setFont(new Font("Dialog", Font.BOLD, 12));
		textUserName.setColumns(10);
		textUserName.setBounds(142, 121, 302, 24);
		panel.add(textUserName);
		
		JLabel lblBirthday = new JLabel("BIRTHDAY");
		lblBirthday.setHorizontalAlignment(SwingConstants.CENTER);
		lblBirthday.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBirthday.setBounds(28, 167, 90, 24);
		panel.add(lblBirthday);
		
		JLabel lblPhone = new JLabel("PHONE");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPhone.setBounds(28, 214, 66, 24);
		panel.add(lblPhone);
		
		textUserPhone = new JTextField();
		textUserPhone.setHorizontalAlignment(SwingConstants.CENTER);
		textUserPhone.setEditable(false);
		textUserPhone.setText((String) null);
		textUserPhone.setFont(new Font("Dialog", Font.BOLD, 12));
		textUserPhone.setColumns(10);
		textUserPhone.setBounds(142, 218, 302, 24);
		panel.add(textUserPhone);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAddress.setBounds(28, 268, 90, 24);
		panel.add(lblAddress);
		
		textFieldAddress = new JTextPane();
		textFieldAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldAddress.setEditable(false);
		textFieldAddress.setText((String) null);
		textFieldAddress.setBounds(142, 268, 302, 66);
		panel.add(textFieldAddress);
		
		textUserBirthDay = new JTextField();
		textUserBirthDay.setHorizontalAlignment(SwingConstants.CENTER);
		textUserBirthDay.setEditable(false);
		textUserBirthDay.setText((String) null);
		textUserBirthDay.setFont(new Font("Dialog", Font.BOLD, 12));
		textUserBirthDay.setColumns(10);
		textUserBirthDay.setBounds(142, 171, 200, 24);
		panel.add(textUserBirthDay);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(484, 0, 465, 367);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBookId = new JLabel("BOOK ID");
		lblBookId.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBookId.setBounds(34, 76, 72, 24);
		panel_1.add(lblBookId);
		
		textBookId = new JTextField();
		textBookId.setEditable(false);
		textBookId.setHorizontalAlignment(SwingConstants.CENTER);
		textBookId.setFont(new Font("Dialog", Font.BOLD, 12));
		textBookId.setColumns(10);
		textBookId.setBounds(162, 76, 132, 24);
		panel_1.add(textBookId);
		
		JLabel lblBookName = new JLabel("BOOK NAME");
		lblBookName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBookName.setBounds(34, 120, 98, 24);
		panel_1.add(lblBookName);
		
		textBookName = new JTextField();
		textBookName.setEditable(false);
		textBookName.setHorizontalAlignment(SwingConstants.CENTER);
		textBookName.setFont(new Font("Dialog", Font.BOLD, 12));
		textBookName.setColumns(10);
		textBookName.setBounds(162, 120, 239, 24);
		panel_1.add(textBookName);
		
		JLabel lblAuthor = new JLabel("AUTHOR");
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAuthor.setBounds(34, 164, 72, 24);
		panel_1.add(lblAuthor);
		
		JLabel lblPublisher = new JLabel("PUBLISHER");
		lblPublisher.setHorizontalAlignment(SwingConstants.CENTER);
		lblPublisher.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPublisher.setBounds(34, 209, 98, 24);
		panel_1.add(lblPublisher);
		
		JLabel lblLimitDay = new JLabel("LIMIT DAY");
		lblLimitDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblLimitDay.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLimitDay.setBounds(34, 262, 82, 24);
		panel_1.add(lblLimitDay);
		
		textLimit = new JTextField();
		textLimit.setEditable(false);
		textLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		textLimit.setFont(new Font("Dialog", Font.BOLD, 12));
		textLimit.setColumns(10);
		textLimit.setBounds(162, 263, 79, 24);
		panel_1.add(textLimit);
		
		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPrice.setBounds(34, 314, 53, 24);
		panel_1.add(lblPrice);
		
		textPrice = new JTextField();
		textPrice.setEditable(false);
		textPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		textPrice.setFont(new Font("Dialog", Font.BOLD, 12));
		textPrice.setColumns(10);
		textPrice.setBounds(162, 314, 79, 27);
		panel_1.add(textPrice);
		
		textBookAuthor = new JTextField();
		textBookAuthor.setEditable(false);
		textBookAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		textBookAuthor.setFont(new Font("Dialog", Font.BOLD, 12));
		textBookAuthor.setColumns(10);
		textBookAuthor.setBounds(162, 168, 239, 24);
		panel_1.add(textBookAuthor);
		
		textBookPublisher = new JTextField();
		textBookPublisher.setEditable(false);
		textBookPublisher.setHorizontalAlignment(SwingConstants.CENTER);
		textBookPublisher.setFont(new Font("Dialog", Font.BOLD, 12));
		textBookPublisher.setColumns(10);
		textBookPublisher.setBounds(162, 213, 239, 24);
		panel_1.add(textBookPublisher);
		
		JButton btnNewButton = new JButton("DELETE");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete IdWork = " +ManageWorkForm.getIdWork(),
						"Delete", dialogButton);
				
				if (dialogResult == JOptionPane.YES_OPTION) {
					try {
						WorkDAO.DeleteWork(ManageWorkForm.getIdWork());
						setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(InformationBookForm.class.getResource("/image/Delete.png")));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton.setBounds(302, 11, 132, 48);
		panel_1.add(btnNewButton);
	}

	public JTextField getTextUserId() {
		return textUserId;
	}

	public void setTextUserId(JTextField textUserId) {
		this.textUserId = textUserId;
	}

	public JTextField getTextUserName() {
		return textUserName;
	}

	public void setTextUserName(JTextField textUserName) {
		this.textUserName = textUserName;
	}

	public JTextField getTextUserPhone() {
		return textUserPhone;
	}

	public void setTextUserPhone(JTextField textUserPhone) {
		this.textUserPhone = textUserPhone;
	}

	public JTextField getTextUserBirthDay() {
		return textUserBirthDay;
	}

	public void setTextUserBirthDay(JTextField textUserBirthDay) {
		this.textUserBirthDay = textUserBirthDay;
	}

	public JTextField getTextBookId() {
		return textBookId;
	}

	public void setTextBookId(JTextField textBookId) {
		this.textBookId = textBookId;
	}

	public JTextField getTextBookName() {
		return textBookName;
	}

	public void setTextBookName(JTextField textBookName) {
		this.textBookName = textBookName;
	}

	public JTextField getTextLimit() {
		return textLimit;
	}

	public void setTextLimit(JTextField textLimit) {
		this.textLimit = textLimit;
	}

	public JTextField getTextPrice() {
		return textPrice;
	}

	public void setTextPrice(JTextField textPrice) {
		this.textPrice = textPrice;
	}

	public JTextField getTextBookAuthor() {
		return textBookAuthor;
	}

	public void setTextBookAuthor(JTextField textBookAuthor) {
		this.textBookAuthor = textBookAuthor;
	}

	public JTextField getTextBookPublisher() {
		return textBookPublisher;
	}

	public void setTextBookPublisher(JTextField textBookPublisher) {
		this.textBookPublisher = textBookPublisher;
	}

	public JTextPane getTextFieldAddress() {
		return textFieldAddress;
	}

	public void setTextFieldAddress(JTextPane textFieldAddress) {
		this.textFieldAddress = textFieldAddress;
	}
}
