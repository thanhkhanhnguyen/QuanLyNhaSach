package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.AuthorDAO;
import DAO.BookDAO;
import DAO.PublisherDAO;
import Model.Author;
import Model.Book;
import Model.JTableUtilities;
import Model.Publisher;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ManageBookForm extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textBookId;
	private JTextField textBookName;
	private JTextField textNumber;
	private JTextField textFind;
	private JTextField textLimit;
	private JTextField textPrice;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxAuthor;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxPublisher;
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private static Book bo = new Book("", "", 0, 0, 0, 0, 0);
	@SuppressWarnings("rawtypes")
	public ManageBookForm() {
		setTitle("ManageBookForm");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/*
		 * setUndecorated(true); getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		 */
		setBounds(100, 100, 1246, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.GRAY);
		contentPane_1.setBounds(0, 0, 1230, 428);
		contentPane.add(contentPane_1);

		JLabel lblManageBook = new JLabel("MANAGE BOOK");
		lblManageBook
				.setIcon(new ImageIcon(ManageBookForm.class.getResource("/image/baseline_menu_book_black_24dp.png")));
		lblManageBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageBook.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblManageBook.setBounds(31, 11, 231, 39);
		contentPane_1.add(lblManageBook);

		JLabel lblBookId = new JLabel("BOOK ID");
		lblBookId.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBookId.setBounds(31, 60, 72, 24);
		contentPane_1.add(lblBookId);

		textBookId = new JTextField();
		textBookId.setHorizontalAlignment(SwingConstants.CENTER);
		textBookId.setFont(new Font("Dialog", Font.BOLD, 12));
		textBookId.setColumns(10);
		textBookId.setBounds(159, 60, 132, 24);
		contentPane_1.add(textBookId);

		textBookName = new JTextField();
		textBookName.setHorizontalAlignment(SwingConstants.CENTER);
		textBookName.setFont(new Font("Dialog", Font.BOLD, 12));
		textBookName.setColumns(10);
		textBookName.setBounds(159, 104, 239, 24);
		contentPane_1.add(textBookName);

		JLabel lblBookName = new JLabel("BOOK NAME");
		lblBookName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBookName.setBounds(31, 104, 98, 24);
		contentPane_1.add(lblBookName);

		JLabel lblAuthor = new JLabel("AUTHOR");
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAuthor.setBounds(31, 148, 72, 24);
		contentPane_1.add(lblAuthor);

		JLabel lblPublisher = new JLabel("PUBLISHER");
		lblPublisher.setHorizontalAlignment(SwingConstants.CENTER);
		lblPublisher.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPublisher.setBounds(31, 193, 98, 24);
		contentPane_1.add(lblPublisher);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component a = null;
				if (textFieldisEmpty() == true) {
					String id = textBookId.getText().trim();
					String name = textBookName.getText().trim();
					String author = comboBoxAuthor.getSelectedItem().toString().trim();
					int id_author = 0;
					int id_publisher = 0;				
					String publisher = comboBoxPublisher.getSelectedItem().toString().trim();
					int number = Integer.parseInt(textNumber.getText().trim());
					int limit = Integer.parseInt(textLimit.getText().trim());
					int price = Integer.parseInt(textPrice.getText().trim());
					
					try {
						id_author = AuthorDAO.getIdtoNameAuthor(author);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						id_publisher = PublisherDAO.getIdtoNamePublisher(publisher);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					bo.setId(id);
					bo.setName(name);
					bo.setAuthorId(id_author);
					bo.setPublisherId(id_publisher);
					bo.setNumber(number);
					bo.setLimitDay(limit);
					bo.setPrice(price);
					
					try {
						if (BookDAO.CheckIdExist(bo.getId()) == 0) {
							
							BookDAO.AddBook(bo);
							JOptionPane.showMessageDialog(a, "Complete", "Add Book",
									JOptionPane.INFORMATION_MESSAGE);
							setTextField();
							
						} 
						else {
							JOptionPane.showMessageDialog(a, "Book Id  already exists", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(a, "TextField is Empty", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnAdd.setIcon(new ImageIcon(ManageBookForm.class.getResource("/image/Add.png")));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(31, 381, 89, 38);
		contentPane_1.add(btnAdd);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component a =null;
				if (textBookId.getText().isEmpty() == false) {
					String id = textBookId.getText().trim();
					try {
						if (BookDAO.CheckIdExist(id) != 0) {
							BookDAO.DeleteBook(id);
							JOptionPane.showMessageDialog(a, "Complete", "Delete Book",
									JOptionPane.INFORMATION_MESSAGE);
							
							setTextField();
						} else {
							JOptionPane.showMessageDialog(a, "Id Book does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(a, "TextField Id  is Empty", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(ManageBookForm.class.getResource("/image/Delete.png")));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(322, 381, 123, 38);
		contentPane_1.add(btnDelete);

		JButton btnSave = new JButton("SAVE");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component a = null;
				if (textFieldisEmpty() == true) {
					String id = textBookId.getText().trim();
					String name = textBookName.getText().trim();
					String author = comboBoxAuthor.getSelectedItem().toString().trim();
					int id_author = 0;
					int id_publisher = 0;				
					String publisher = comboBoxPublisher.getSelectedItem().toString().trim();
					int number = Integer.parseInt(textNumber.getText().trim());
					int limit = Integer.parseInt(textLimit.getText().trim());
					int price = Integer.parseInt(textPrice.getText().trim());
					
					try {
						id_author = AuthorDAO.getIdtoNameAuthor(author);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						id_publisher = PublisherDAO.getIdtoNamePublisher(publisher);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					bo.setId(id);
					bo.setName(name);
					bo.setAuthorId(id_author);
					bo.setPublisherId(id_publisher);
					bo.setNumber(number);
					bo.setLimitDay(limit);
					bo.setPrice(price);
					
					try {
						if (BookDAO.CheckIdExist(bo.getId()) != 0) {
							
							try {
								BookDAO.UpdateBook(bo);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(a, "Complete", "Save Book",
									JOptionPane.INFORMATION_MESSAGE);
							setTextField();
							
						} 
						else {
							JOptionPane.showMessageDialog(a, "Book Id does not exists", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(a, "TextField is Empty", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSave.setIcon(new ImageIcon(ManageBookForm.class.getResource("/image/Save.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(171, 381, 98, 38);
		contentPane_1.add(btnSave);

		JLabel lblNumber = new JLabel("NUMBER");
		lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNumber.setBounds(31, 238, 72, 24);
		contentPane_1.add(lblNumber);

		textNumber = new JTextField();
		textNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(! Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		textNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		textNumber.setColumns(10);
		textNumber.setBounds(159, 239, 79, 24);
		contentPane_1.add(textNumber);

		textFind = new JTextField();
		textFind.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFind.setColumns(10);
		textFind.setBounds(647, 59, 450, 29);
		contentPane_1.add(textFind);

		JButton btnFind = new JButton("FIND");
		btnFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textFind.getText().trim();
				tableModel.setRowCount(0);
				try {
					updateDataTable(BookDAO.getSearchInformation(text));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.setModel(tableModel);
			}
		});
		btnFind.setIcon(new ImageIcon(ManageBookForm.class.getResource("/image/Search.png")));
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFind.setBounds(1122, 59, 98, 29);
		contentPane_1.add(btnFind);

		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setTextField();
			}
		});
		btnRefresh.setIcon(new ImageIcon(ManageBookForm.class.getResource("/image/Refresh.png")));
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRefresh.setBounds(636, 381, 123, 38);
		contentPane_1.add(btnRefresh);

		comboBoxAuthor = new JComboBox();
		comboBoxAuthor.setBounds(159, 151, 239, 29);
		contentPane_1.add(comboBoxAuthor);

		comboBoxPublisher = new JComboBox();
		comboBoxPublisher.setBounds(159, 196, 239, 29);
		contentPane_1.add(comboBoxPublisher);

		JLabel lblLimitDay = new JLabel("LIMIT DAY");
		lblLimitDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblLimitDay.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLimitDay.setBounds(31, 284, 82, 24);
		contentPane_1.add(lblLimitDay);

		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPrice.setBounds(31, 327, 53, 24);
		contentPane_1.add(lblPrice);

		textLimit = new JTextField();
		textLimit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(! Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		textLimit.setFont(new Font("Dialog", Font.BOLD, 12));
		textLimit.setColumns(10);
		textLimit.setBounds(159, 288, 79, 24);
		contentPane_1.add(textLimit);

		textPrice = new JTextField();
		textPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(! Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		textPrice.setFont(new Font("Dialog", Font.BOLD, 12));
		textPrice.setColumns(10);
		textPrice.setBounds(159, 327, 79, 27);
		contentPane_1.add(textPrice);

		JButton btnExit = new JButton("EXIT");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnExit.setIcon(new ImageIcon(ManageBookForm.class.getResource("/image/Exit.png")));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(880, 381, 123, 38);
		contentPane_1.add(btnExit);

		String[] columnName = { "ID", "BookName", "AuthorName", "PublisherName", "Number", "LimitDay", "Price" };
		tableModel.setColumnIdentifiers(columnName);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(408, 104, 812, 266);
		contentPane_1.add(scrollPane);

		table = new JTable(tableModel);
		table.setRowHeight(50);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();

				TableModel model = table.getModel();
				String value1 = (model.getValueAt(index, 0).toString());
				String value2 = (model.getValueAt(index, 1).toString());
				String value3 = (model.getValueAt(index, 2).toString());
				String value4 = (model.getValueAt(index, 3).toString());
				String value5 = model.getValueAt(index, 4).toString();
				String value6 = model.getValueAt(index, 5).toString();
				String value7 = model.getValueAt(index, 6).toString();

				textBookId.setText(value1);
				textBookName.setText(value2);
				comboBoxAuthor.setSelectedItem(value3);
				comboBoxPublisher.setSelectedItem(value4);
				textNumber.setText(value5);
				textLimit.setText(value6);
				textPrice.setText(value7);

			}
		});
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);

		JLabel lblPrice_1 = new JLabel("$");
		lblPrice_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice_1.setBounds(240, 330, 29, 24);
		contentPane_1.add(lblPrice_1);

		// ============
		try {
			LoadData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void LoadData() throws SQLException {
		updateDataTable(BookDAO.getAllBook());
		updateDataComboBoxAuthor(BookDAO.getNameAuthor());
		updateDataComboBoxPublisher(BookDAO.getNamePublisher());

	}

	public void updateDataTable(ResultSet result) {
		String[] columnName = { "ID", "BookName", "AuthorName", "PublisherName", "Number", "LimitDay", "Price" };
		tableModel.setColumnIdentifiers(columnName);
		try {
			while (result.next()) {
				String rows[] = new String[7];
				rows[0] = result.getString(1);
				rows[1] = result.getString(2);
				rows[2] = result.getString(3);
				rows[3] = result.getString(4);
				rows[4] = result.getString(5);
				rows[5] = result.getString(6);
				rows[6] = result.getString(7);
				tableModel.addRow(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		designTable();

	}

	@SuppressWarnings("unchecked")
	public void updateDataComboBoxAuthor(ResultSet rs) throws SQLException {
		comboBoxAuthor.addItem("");
		while (rs.next()) {
			Author ts = new Author();
			ts.setName((rs.getString("name")));
			comboBoxAuthor.addItem(ts.getName());
		}
	}

	@SuppressWarnings("unchecked")
	public void updateDataComboBoxPublisher(ResultSet rs) throws SQLException {
		comboBoxPublisher.addItem("");
		while (rs.next()) {
			Publisher ts = new Publisher();
			ts.setName((rs.getString("name")));
			comboBoxPublisher.addItem(ts.getName());
		}
	}

	public void setTextField() {
		textBookId.setText("");
		textBookName.setText("");
		textNumber.setText("");
		textLimit.setText("");
		textPrice.setText("");
		comboBoxAuthor.setSelectedItem("");
		comboBoxPublisher.setSelectedItem("");

		tableModel.setRowCount(0);
		try {
			updateDataTable(BookDAO.getAllBook());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setModel(tableModel);
		designTable();

	}

	public boolean textFieldisEmpty() {
		if (textBookId.getText().isEmpty() == true || textBookName.getText().isEmpty() == true
				|| textNumber.getText().isEmpty() == true || textLimit.getText().isEmpty() == true
				|| textPrice.getText().isEmpty() == true
				|| comboBoxAuthor.getSelectedItem().toString().equals("") == true
				|| comboBoxPublisher.getSelectedItem().toString().equals("") == true) {
			return false;
		}
		return true;
	}
	
	public void designTable() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setPreferredWidth(10);
		table.getColumnModel().getColumn(6).setPreferredWidth(10);
		table.setRowHeight(50);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
		
	}
	
}
