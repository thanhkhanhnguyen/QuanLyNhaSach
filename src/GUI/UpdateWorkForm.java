package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import DAO.BookDAO;
import DAO.UserDAO;
import DAO.WorkDAO;
import Model.Work;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateWorkForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldWorkId;
	private JTextField textFieldUserId;
	
	private JDatePickerImpl datePicker;
	private JTextField textFieldBookId;
	private JButton btnDelete;
	private JButton btnAdd;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateWorkForm frame = new UpdateWorkForm();
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
	public UpdateWorkForm() {
		setTitle("UpdateWork");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 462, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWorkId = new JLabel("WORK ID");
		lblWorkId.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblWorkId.setBounds(10, 24, 72, 24);
		contentPane.add(lblWorkId);
		
		textFieldWorkId = new JTextField();
		textFieldWorkId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(! Character.isDigit(c)) {
					e.consume();
				}
			}
			
		});
		textFieldWorkId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldWorkId.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldWorkId.setColumns(10);
		textFieldWorkId.setBounds(138, 24, 116, 24);
		contentPane.add(textFieldWorkId);
		
		JLabel lblUserId = new JLabel("USER ID");
		lblUserId.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUserId.setBounds(10, 82, 72, 24);
		contentPane.add(lblUserId);
		
		textFieldUserId = new JTextField();
		textFieldUserId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUserId.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldUserId.setColumns(10);
		textFieldUserId.setBounds(138, 82, 116, 24);
		contentPane.add(textFieldUserId);
		
		JLabel lblBookName = new JLabel("BOOK ID");
		lblBookName.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBookName.setBounds(10, 139, 102, 23);
		contentPane.add(lblBookName);
		
		JLabel lblBookId_1_1 = new JLabel("DATE");
		lblBookId_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookId_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBookId_1_1.setBounds(10, 204, 72, 24);
		contentPane.add(lblBookId_1_1);
		
		
		
		JButton btnFind = new JButton("FIND");
		btnFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textFieldWorkId.getText().isEmpty()) {
					return;
				}
				int id = Integer.parseInt(textFieldWorkId.getText());
				if(WorkDAO.checkIdWork(id) == 0) {
					JOptionPane.showMessageDialog(null, "Can not Find WorkId");
				}
				else {
					ResultSet rs = WorkDAO.getWorkById(id);
					try {
						while(rs.next()) {
							textFieldUserId.setText(rs.getString("id_user"));
							textFieldBookId.setText(rs.getString("id_book"));
							datePicker.getJFormattedTextField().setText(rs.getString("start_date"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					btnDelete.setEnabled(true);
					btnAdd.setEnabled(false);
				}
				
				
			}
		});
		btnFind.setFont(new Font("Dialog", Font.BOLD, 11));
		btnFind.setBounds(294, 24, 89, 24);
		contentPane.add(btnFind);
		
		btnAdd = new JButton("ADD");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( textFieldUserId.getText().isEmpty() || textFieldBookId.getText().isEmpty() || datePicker.getJFormattedTextField().getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Text Field is Empty");
				} else
					try {
						if(BookDAO.CheckIdExist(textFieldBookId.getText()) == 0){
							JOptionPane.showMessageDialog(null, "Can not Find Book Id");
						}
						else if(UserDAO.CheckIdExist(textFieldUserId.getText()) ==0 ) {
							JOptionPane.showMessageDialog(null, "Can not Find User Id");
						}
						else {
							String user_id = textFieldUserId.getText();
							String book_id =  textFieldBookId.getText();
							String start_date = datePicker.getJFormattedTextField().getText();
							int price = BookDAO.getFeeToId(book_id);
							Work wk = new Work(user_id,book_id,start_date," ","NO",price);
							WorkDAO.AddWork(wk);
							textFieldWorkId.setText("");
							textFieldUserId.setText("");
							textFieldBookId.setText("");
							datePicker.getJFormattedTextField().setText(null);
							JOptionPane.showMessageDialog(null, "Complete");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAdd.setBounds(347, 84, 89, 49);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = Integer.parseInt(textFieldWorkId.getText());
				try {
					WorkDAO.DeleteWork(id);
					textFieldWorkId.setText("");
					textFieldUserId.setText("");
					textFieldBookId.setText("");
					datePicker.getJFormattedTextField().setText(null);
					btnDelete.setEnabled(false);
					btnAdd.setEnabled(true);
					JOptionPane.showMessageDialog(null, "Complete");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 14));
		btnDelete.setBounds(347, 179, 89, 49);
		contentPane.add(btnDelete);
		
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

		datePicker.setBounds(138, 204, 160, 24);
		contentPane.add(datePicker);
		
		textFieldBookId = new JTextField();
		textFieldBookId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBookId.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldBookId.setColumns(10);
		textFieldBookId.setBounds(138, 142, 116, 24);
		contentPane.add(textFieldBookId);
		
		//========
		
	}
	
	
	

}
