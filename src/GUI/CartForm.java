package GUI;



import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.BookDAO;
import DAO.CartDAO;
import DAO.WorkDAO;
import Model.JTableUtilities;
import Model.Work;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Stack;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class CartForm extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private static UserMainForm form ;
	
	public static UserMainForm getForm() {
		return form;
	}

	public static void setForm(UserMainForm form) {
		CartForm.form = form;
	}

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { CartForm frame = new CartForm();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public CartForm() {
		setTitle("Cart");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 233);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String id_user = LoginForm.getId_user();
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete this Book From Cart?",
						"Borrow Book", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					String name = (model.getValueAt(index, 0).toString());
					String id = BookDAO.getIdByName(name);
					try {
						CartDAO.DeleteCart(id_user, id);
						updateTable(CartDAO.getCartById(LoginForm.getId_user()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		scrollPane.setViewportView(table);

		JButton btnBorrow = new JButton("BORROW");
		btnBorrow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Stack<String> a = form.getListName();
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Borrow?", "Borrow Book",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					try {
						ResultSet rs = CartDAO.getCartById(LoginForm.getId_user());
						String id_user = LoginForm.getId_user();
						LocalDateTime now = java.time.LocalDateTime.now();
						String start_date;
						if(now.getDayOfMonth() < 10) {
							start_date = now.getYear() + "-" + now.getMonthValue() + "-0" + now.getDayOfMonth();
						}else {
							start_date = now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth();
						}
						
						while (rs.next()) {
							String id_book = rs.getString("id");
							int price = rs.getInt("price");
							//Them
							Work wk = new Work(id_user, id_book, start_date, " ", "NO", price);
							WorkDAO.AddWork(wk);
							//Update So Luong - 1
							try {
								BookDAO.UpdateNumberBook(id_book);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//form.updateDataComboBoxReturn();
							
							a.push(BookDAO.getNameBookById(id_book));
							
						}
						form.setListName(a);
						JOptionPane.showMessageDialog(null, "Complete , You can check in BookList");
						CartDAO.DeleteAllCart(id_user);
						updateTable(CartDAO.getCartById(LoginForm.getId_user()));
						//
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		btnBorrow.setFont(new Font("Dialog", Font.BOLD, 15));
		btnBorrow.setBounds(152, 255, 120, 47);
		contentPane.add(btnBorrow);

		
		try {
			updateTable(CartDAO.getCartById(LoginForm.getId_user()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateTable(ResultSet result) {
		// table.set
		tableModel.setRowCount(0);
		String[] columnName = { "Book_Name", "Price" };
		tableModel.setColumnIdentifiers(columnName);
		try {
			while (result.next()) {
				String rows[] = new String[2];
				rows[0] = result.getString(1);
				rows[1] = result.getString(2);
				if(BookDAO.checkNumberBook(result.getString(3))) {
					tableModel.addRow(rows);
				}
				else {
					CartDAO.DeleteCart(LoginForm.getId_user(), result.getString(3));
				}

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(50);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
	}

}
