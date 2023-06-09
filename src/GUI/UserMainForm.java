package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.BookDAO;
import DAO.CartDAO;
import DAO.SaleDAO;
import DAO.UserDAO;
import DAO.WorkDAO;
import Model.JTableUtilities;
import Model.Publisher;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Stack;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class UserMainForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int flag = 0;
	private JPanel panelReturn;
	private JPanel panelBorrow;
	private JPanel panelList;
	private JPanel panelSearch;
	private JLabel lblMoney;
	private JLabel lblUser;
	private static User us;
	private JTable table;
	private JTextField textFieldSearch;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private DefaultTableModel tableModelReturn = new DefaultTableModel();
	private JButton buttonSearch;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JLabel lblDate;
	private JLabel lblTime;
	private JLabel lblHello;
	// =====
	private JTextField textFieldId;
	private JTextField textFieldLimitDay;
	private JTextField textFieldPrice;
	private JTextField textFieldAuthor;
	private JTextField textFieldPublisher;
	private JLabel lblBookId;
	private JButton btnFind;
	private JButton btnAddCart;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxName;
	private JLabel lblBookName;
	private JLabel lblAuthor;
	private JLabel lblPublisher;
	private JLabel lblLimitDay;
	private JLabel lblPrice;
	private JLabel lblPrice_1;
	private JButton btnClear;
	// ==========
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxReturnName;
	private JButton btnAddReturn;
	private JLabel lblTotalFee;
	private JButton btnReturn;
	
	private Stack<String> listName = new Stack<String>();
	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { UserMainForm frame = new
	 * UserMainForm(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }/*
	 * 
	 * /** Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public UserMainForm() {
		this.setTitle("Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 907, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 194, 420);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panelLibrary = new JPanel();
		panelLibrary.setBackground(Color.RED);
		panelLibrary.setBounds(0, 0, 194, 67);
		panel.add(panelLibrary);
		panelLibrary.setLayout(null);

		JLabel lblLibrary = new JLabel("LIBRARY");
		lblLibrary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelLibrary.setBackground(new Color(244, 53, 53).brighter());
				flag = 0;
				panelSearch.setBackground(Color.green);
				panelList.setBackground(Color.green);
				panelBorrow.setBackground(Color.green);
				panelReturn.setBackground(Color.green);
				LoadLabel();
				LoadPanel();

			}

			public void mousePressed(MouseEvent e) {
				panelLibrary.setBackground(Color.blue);
			}

			public void mouseExited(MouseEvent e) {
				panelLibrary.setBackground(Color.red);

			}
		});
		lblLibrary.setBounds(0, 0, 194, 67);
		panelLibrary.add(lblLibrary);
		lblLibrary
				.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_library_books_black_24dp.png")));
		lblLibrary.setFont(new Font("Dialog", Font.BOLD, 25));
		lblLibrary.setHorizontalAlignment(SwingConstants.CENTER);

		panelSearch = new JPanel();
		panelSearch.setBackground(new Color(0, 255, 0));
		panelSearch.setBounds(10, 78, 174, 49);
		panel.add(panelSearch);
		panelSearch.setLayout(null);

		JLabel lblSearch = new JLabel("SEARCH");
		lblSearch.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				panelSearch.setBackground(new Color(3, 59, 90));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelSearch.setBackground(new Color(0, 255, 0));
				if (flag == 2) {
					panelSearch.setBackground(Color.blue);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panelSearch.setBackground(new Color(244, 53, 53).brighter());
				flag = 2;
				ChangColor();
				// =============
				LoadPanel();
				try {
					updateTableBook(BookDAO.getAllBook());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panelSearch.setBackground(new Color(64, 64, 237).brighter());

			}

			@Override
			public void mousePressed(MouseEvent e) {
				panelSearch.setBackground(new Color(244, 53, 53).brighter());
			}
		});
		lblSearch.setIcon(
				new ImageIcon(UserMainForm.class.getResource("/image/baseline_content_paste_search_black_24dp.png")));
		lblSearch.setBounds(0, 0, 174, 49);
		panelSearch.add(lblSearch);
		lblSearch.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearch.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSearch.setBackground(Color.WHITE);

		panelList = new JPanel();
		panelList.setBackground(new Color(0, 255, 0));
		panelList.setLayout(null);
		panelList.setBounds(10, 144, 174, 49);
		panel.add(panelList);

		JLabel lblList = new JLabel("BOOK LIST");
		lblList.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				panelList.setBackground(new Color(3, 59, 90));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelList.setBackground(new Color(0, 255, 0));
				if (flag == 3) {
					panelList.setBackground(Color.blue);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panelList.setBackground(new Color(244, 53, 53).brighter());
				flag = 3;
				ChangColor();
				// =========
				LoadPanel();
				try {
					updateTableListBook(WorkDAO.getBookById(us.getId()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panelList.setBackground(new Color(64, 64, 237).brighter());

			}

			@Override
			public void mousePressed(MouseEvent e) {
				panelList.setBackground(new Color(244, 53, 53).brighter());
			}
		});
		lblList.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_fact_check_black_24dp.png")));
		lblList.setHorizontalAlignment(SwingConstants.LEFT);
		lblList.setFont(new Font("Dialog", Font.BOLD, 18));
		lblList.setBackground(Color.WHITE);
		lblList.setBounds(0, 0, 174, 49);
		panelList.add(lblList);

		panelBorrow = new JPanel();
		panelBorrow.setBackground(new Color(0, 255, 0));
		panelBorrow.setLayout(null);
		panelBorrow.setBounds(10, 214, 174, 49);
		panel.add(panelBorrow);

		JLabel lblBorrow = new JLabel("BORROW");
		lblBorrow.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				panelBorrow.setBackground(new Color(3, 59, 90));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelBorrow.setBackground(new Color(0, 255, 0));
				if (flag == 4) {
					panelBorrow.setBackground(Color.blue);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panelBorrow.setBackground(new Color(244, 53, 53).brighter());
				flag = 4;
				ChangColor();
				// =========
				LoadPanel();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panelBorrow.setBackground(new Color(64, 64, 237).brighter());

			}

			@Override
			public void mousePressed(MouseEvent e) {
				panelBorrow.setBackground(new Color(244, 53, 53).brighter());
			}
		});
		lblBorrow.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_archive_black_24dp.png")));
		lblBorrow.setHorizontalAlignment(SwingConstants.LEFT);
		lblBorrow.setFont(new Font("Dialog", Font.BOLD, 18));
		lblBorrow.setBackground(Color.WHITE);
		lblBorrow.setBounds(0, 0, 174, 49);
		panelBorrow.add(lblBorrow);

		panelReturn = new JPanel();
		panelReturn.setBackground(new Color(0, 255, 0));
		panelReturn.setLayout(null);
		panelReturn.setBounds(10, 284, 174, 49);
		panel.add(panelReturn);

		JLabel lblReturn = new JLabel("RETURN");
		lblReturn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				panelReturn.setBackground(new Color(3, 59, 90));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelReturn.setBackground(new Color(0, 255, 0));
				if (flag == 5) {
					panelReturn.setBackground(Color.blue);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panelReturn.setBackground(new Color(244, 53, 53).brighter());
				flag = 5;
				ChangColor();

				// ======
				LoadPanel();
				String[] columnName = { "BookName", "Start_Date", "End_Date", "LimitDay", "Late", "Fee" };
				tableModelReturn.setColumnIdentifiers(columnName);
				table.setModel(tableModelReturn);
				designTableReturnBook();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panelReturn.setBackground(new Color(64, 64, 237).brighter());

			}

			@Override
			public void mousePressed(MouseEvent e) {
				panelReturn.setBackground(new Color(244, 53, 53).brighter());
			}
		});
		lblReturn.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_unarchive_black_24dp.png")));
		lblReturn.setHorizontalAlignment(SwingConstants.LEFT);
		lblReturn.setFont(new Font("Dialog", Font.BOLD, 18));
		lblReturn.setBackground(Color.WHITE);
		lblReturn.setBounds(0, 0, 174, 49);
		panelReturn.add(lblReturn);

		JPanel panel_6_4 = new JPanel();
		panel_6_4.setBackground(new Color(154, 205, 50));
		panel_6_4.setLayout(null);
		panel_6_4.setBounds(0, 379, 194, 41);
		panel.add(panel_6_4);

		JLabel lblNewLabel_1_1_2_4 = new JLabel("LOGOUT");
		lblNewLabel_1_1_2_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to log out?", "LOG OUT",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					setVisible(false);
					LoginForm f = new LoginForm();
					f.setVisible(true);
				}

			}
		});
		lblNewLabel_1_1_2_4
				.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_logout_black_24dp.png")));
		lblNewLabel_1_1_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2_4.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1_1_2_4.setBackground(Color.WHITE);
		lblNewLabel_1_1_2_4.setBounds(0, 0, 194, 41);
		panel_6_4.add(lblNewLabel_1_1_2_4);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 204));
		panel_1.setBounds(203, 71, 678, 349);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// ===================
		lblTotalFee = new JLabel("TotalFee");
		lblTotalFee.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_paid_black_24dp.png")));
		lblTotalFee.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalFee.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTotalFee.setBounds(322, 309, 194, 40);
		panel_1.add(lblTotalFee);

		btnReturn = new JButton("RETURN");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int money = 0;
				try {
					money = UserDAO.getMoney(LoginForm.getId_user());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				int fee = getFee();
				if(fee > money) {
					JOptionPane.showMessageDialog(null, "not enough money");
					return;
				}
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Return Books?",
						"Borrow Book", dialogButton);
				
				if (dialogResult == JOptionPane.YES_OPTION) {
					String end_date =null;
					for(int i=0;i<tableModelReturn.getRowCount();i++) {
						String name_book = tableModelReturn.getValueAt(i, 0).toString();
						end_date = tableModelReturn.getValueAt(i, 2).toString();
						String id_book = BookDAO.getIdByName(name_book);
						int id_work = WorkDAO.getIdWork(LoginForm.getId_user(), id_book);
						comboBoxReturnName.removeItem(name_book);
						
						try {
							WorkDAO.UpdateReturnBook(id_work, end_date);
							BookDAO.UpdateNumberBookToReturn(id_book);
							WorkDAO.UpdateStatusWork(id_work, "YES");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}
					
					try {
						tableModelReturn.setRowCount(0);
						UserDAO.UpdateMoney(2,LoginForm.getId_user() , fee);  //Tru Tien
						SaleDAO.AddSale(end_date, LoginForm.getId_user(), fee);
						JOptionPane.showMessageDialog(null, "Complete");
						loadLabelFee();
						LoadLabel();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnReturn.setFont(new Font("Dialog", Font.BOLD, 15));
		btnReturn.setBounds(551, 309, 117, 40);
		panel_1.add(btnReturn);

		// =============
		lblBookId = new JLabel("BOOK ID");
		lblBookId.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBookId.setBounds(57, 64, 72, 24);
		panel_1.add(lblBookId);

		textFieldId = new JTextField();
		textFieldId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldId.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldId.setColumns(10);
		textFieldId.setBounds(185, 64, 132, 24);
		panel_1.add(textFieldId);

		btnFind = new JButton("FIND");
		btnFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFieldId.getText().isEmpty()) {
					return;
				} else {
					String id = textFieldId.getText();
					try {
						if (BookDAO.CheckIdExist(id) == 0) {
							JOptionPane.showMessageDialog(null, "Can not Find Id");
						} else {
							ResultSet rs = BookDAO.getBookById(id);
							while (rs.next()) {
								comboBoxName.setSelectedItem(rs.getString(2));
								textFieldAuthor.setText(rs.getString(3));
								textFieldPublisher.setText(rs.getString(4));
								textFieldLimitDay.setText(rs.getString(6));
								textFieldPrice.setText(rs.getString(7));
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnFind.setFont(new Font("Dialog", Font.BOLD, 13));
		btnFind.setBounds(385, 64, 89, 29);
		panel_1.add(btnFind);

		btnAddCart = new JButton("ADD CART");
		btnAddCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFieldId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "TextField Id is Empty");
				} else {
					String id_user = LoginForm.getId_user();
					String id_book = textFieldId.getText();
					try {
						if (CartDAO.CheckIdExist(id_user, id_book) != 0) {
							JOptionPane.showMessageDialog(null, "The book is already in Cart");
						} else if (WorkDAO.CheckIdExist(id_user, id_book) != 0) {
							JOptionPane.showMessageDialog(null, "The book is already in Book List");
						} else if (BookDAO.checkNumberBook(id_book) == false) {
							JOptionPane.showMessageDialog(null, "The Book is Not enough quantity");
						} else {
							int dialogButton = JOptionPane.YES_NO_OPTION;
							int dialogResult = JOptionPane.showConfirmDialog(null,
									"Do you want to Add the Book to Cart?", "Add To Cart", dialogButton);
							if (dialogResult == JOptionPane.YES_OPTION) {
								CartDAO.AddCart(id_user, id_book);
								JOptionPane.showMessageDialog(null, "Complete");
								textFieldId.setText("");
								comboBoxName.setSelectedItem("");
								textFieldAuthor.setText("");
								textFieldPublisher.setText("");
								textFieldLimitDay.setText("");
								textFieldPrice.setText("");
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddCart
				.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_playlist_add_black_24dp.png")));
		btnAddCart.setFont(new Font("Dialog", Font.BOLD, 13));
		btnAddCart.setBounds(504, 11, 164, 47);
		panel_1.add(btnAddCart);

		comboBoxName = new JComboBox();
		comboBoxName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxName.getSelectedItem().toString().trim().equals("") == true) {
					return;
				} else {
					String name = comboBoxName.getSelectedItem().toString().trim();
					ResultSet rs = BookDAO.getBookByName(name);
					try {
						while (rs.next()) {
							textFieldId.setText(rs.getString(1));
							textFieldAuthor.setText(rs.getString(3));
							textFieldPublisher.setText(rs.getString(4));
							textFieldLimitDay.setText(rs.getString(6));
							textFieldPrice.setText(rs.getString(7));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		comboBoxName.setBounds(185, 111, 289, 29);
		panel_1.add(comboBoxName);

		// ==
		comboBoxReturnName = new JComboBox();
		comboBoxReturnName.setBounds(185, 28, 289, 29);
		panel_1.add(comboBoxReturnName);

		btnAddReturn = new JButton("ADD");
		btnAddReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addBookToTheRemoveList();
				table.setModel(tableModelReturn);
				designTableReturnBook();

			}
		});
		btnAddReturn.setFont(new Font("Dialog", Font.BOLD, 13));
		btnAddReturn.setBounds(504, 28, 100, 29);
		panel_1.add(btnAddReturn);

		// ===
		lblBookName = new JLabel("BOOK NAME");
		lblBookName.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBookName.setBounds(57, 116, 118, 24);
		panel_1.add(lblBookName);

		lblAuthor = new JLabel("AUTHOR");
		lblAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		lblAuthor.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAuthor.setBounds(57, 169, 72, 24);
		panel_1.add(lblAuthor);

		lblPublisher = new JLabel("PUBLISHER");
		lblPublisher.setHorizontalAlignment(SwingConstants.LEFT);
		lblPublisher.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPublisher.setBounds(57, 214, 98, 24);
		panel_1.add(lblPublisher);

		lblLimitDay = new JLabel("LIMIT DAY");
		lblLimitDay.setHorizontalAlignment(SwingConstants.LEFT);
		lblLimitDay.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLimitDay.setBounds(57, 260, 82, 24);
		panel_1.add(lblLimitDay);

		lblPrice = new JLabel("PRICE");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPrice.setBounds(57, 303, 53, 24);
		panel_1.add(lblPrice);

		btnClear = new JButton("CLEAR");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldId.setText("");
				comboBoxName.setSelectedItem("");
				textFieldAuthor.setText("");
				textFieldPublisher.setText("");
				textFieldLimitDay.setText("");
				textFieldPrice.setText("");
			}
		});
		btnClear.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_clear_all_black_24dp.png")));
		btnClear.setFont(new Font("Dialog", Font.BOLD, 13));
		btnClear.setBounds(504, 95, 164, 47);
		panel_1.add(btnClear);

		textFieldLimitDay = new JTextField();
		textFieldLimitDay.setEditable(false);
		textFieldLimitDay.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldLimitDay.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldLimitDay.setColumns(10);
		textFieldLimitDay.setBounds(185, 261, 79, 24);
		panel_1.add(textFieldLimitDay);

		textFieldPrice = new JTextField();
		textFieldPrice.setEditable(false);
		textFieldPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldPrice.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(185, 300, 79, 27);
		panel_1.add(textFieldPrice);

		lblPrice_1 = new JLabel("$");
		lblPrice_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice_1.setBounds(266, 303, 29, 24);
		panel_1.add(lblPrice_1);

		textFieldAuthor = new JTextField();
		textFieldAuthor.setEditable(false);
		textFieldAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAuthor.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldAuthor.setColumns(10);
		textFieldAuthor.setBounds(185, 169, 289, 24);
		panel_1.add(textFieldAuthor);

		textFieldPublisher = new JTextField();
		textFieldPublisher.setEditable(false);
		textFieldPublisher.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPublisher.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldPublisher.setColumns(10);
		textFieldPublisher.setBounds(185, 214, 289, 24);
		panel_1.add(textFieldPublisher);

		// =========
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 89, 678, 260);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				if (flag == 2) {
					// Them Sach Vao Danh Sach Muon Muon
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Borrow this Book?",
							"Borrow Book", dialogButton);
					if (dialogResult == JOptionPane.YES_OPTION) {
						String id_book = (model.getValueAt(index, 0).toString());
						String name_book = (model.getValueAt(index, 1).toString());
						String author = (model.getValueAt(index, 2).toString());
						String publisher = (model.getValueAt(index, 3).toString());
						String limitDay = (model.getValueAt(index, 5).toString());
						String price = (model.getValueAt(index, 6).toString());
						flag = 4;
						ChangColor();
						// =========
						LoadPanel();

						// =====
						textFieldId.setText(id_book);
						comboBoxName.setSelectedItem(name_book);
						textFieldAuthor.setText(author);
						textFieldPublisher.setText(publisher);
						textFieldLimitDay.setText(limitDay);
						textFieldPrice.setText(price);
					}

				} else if (flag == 3) {
					// Them Sach Vao DS Muon Tra
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Return this Book?",
							"Borrow Book", dialogButton);
					if (dialogResult == JOptionPane.YES_OPTION) {
						String namebook = (model.getValueAt(index, 0).toString());
						flag = 5;
						ChangColor();
						// ======
						LoadPanel();
						
						String[] columnName = { "BookName", "Start_Date", "End_Date", "LimitDay", "Late", "Fee" };
						tableModelReturn.setColumnIdentifiers(columnName);
						table.setModel(tableModelReturn);
						designTableReturnBook();
						
						
						if (!checkToReturnBook(namebook)) {
							
							return;
						} else {
							LocalDateTime now = java.time.LocalDateTime.now();
							String end_date;
							if(now.getDayOfMonth() < 10) {
								end_date = now.getYear() + "-" + now.getMonthValue() + "-0" + now.getDayOfMonth();
							}
							else {
								end_date = now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth();
							}
							
							String id_book = BookDAO.getIdtoNameBook(namebook);

							try {
								ResultSet result = WorkDAO.getBookByIdBook(LoginForm.getId_user(), id_book);
								while (result.next()) {
									String rows[] = new String[6];
									rows[0] = result.getString(4);
									rows[1] = result.getString(5);
									rows[2] = end_date;
									rows[3] = result.getString(9);
									rows[5] = result.getString(8);
									int late = getLateDays(id_book, Integer.parseInt(result.getString(8)));
									rows[4] = String.valueOf(late);

									tableModelReturn.addRow(rows);
								}
							} catch (SQLException | NumberFormatException | ParseException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						loadLabelFee();
						table.setModel(tableModelReturn);
						designTableReturnBook();

					}
				} else if (flag == 5) {
					// Xoa Sach Khoi Danh Sach Tra
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delele the Book form List?",
							"Borrow Book", dialogButton);
					if (dialogResult == JOptionPane.YES_OPTION) {
						tableModelReturn.removeRow(index);
						table.setModel(tableModelReturn);
						loadLabelFee();
						designTableReturnBook();
					}
				}
			}
		});
		scrollPane.setViewportView(table);

		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(127, 28, 319, 37);
		panel_1.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		buttonSearch = new JButton("SEARCH");
		buttonSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFieldSearch.getText().isEmpty()) {
					return;
				} else {
					String text = textFieldSearch.getText();
					try {
						updateTableBook(BookDAO.getSearchInformation(text));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		buttonSearch
				.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_manage_search_black_24dp.png")));
		buttonSearch.setBounds(504, 28, 139, 37);
		panel_1.add(buttonSearch);

		lblDate = new JLabel("DATE");
		lblDate.setBounds(59, 28, 215, 76);
		panel_1.add(lblDate);
		lblDate.setBackground(new Color(255, 255, 255));
		lblDate.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_today_black_24dp.png")));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Dialog", Font.BOLD, 17));

		lblTime = new JLabel("TIME");
		lblTime.setBounds(393, 28, 215, 76);
		panel_1.add(lblTime);
		lblTime.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_punch_clock_black_24dp.png")));
		lblTime.setFont(new Font("Dialog", Font.BOLD, 17));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);

		lblHello = new JLabel("WELLCOME TO LIBRARY");
		lblHello.setBounds(140, 115, 418, 124);
		panel_1.add(lblHello);
		lblHello.setFont(new Font("Dialog", Font.BOLD, 28));
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);

		// =================

		// flag=0
		/*
		 * lblDate.setVisible(false); lblTime.setVisible(false);
		 * lblHello.setVisible(false);
		 * 
		 * //flag =2 scrollPane.setVisible(false); table.setVisible(false);
		 * textFieldSearch.setVisible(false); buttonSearch.setVisible(false);
		 */

		// ==================================

		JPanel panelUser = new JPanel();
		panelUser.setBackground(new Color(255, 0, 255));
		panelUser.setBounds(204, 11, 244, 49);
		contentPane.add(panelUser);
		panelUser.setLayout(null);

		lblUser = new JLabel("WELLCOME :");
		lblUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				panelUser.setBackground(new Color(255, 0, 255));
				UserInformationForm f = new UserInformationForm();
				f.setVisible(true);

			}

			public void mousePressed(MouseEvent e) {
				panelUser.setBackground(Color.blue);
			}

			public void mouseExited(MouseEvent e) {
				panelUser.setBackground(new Color(255, 0, 255));

			}
		});
		lblUser.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_account_box_black_24dp.png")));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUser.setBounds(0, 0, 244, 49);
		panelUser.add(lblUser);

		JPanel panelMoney = new JPanel();
		panelMoney.setBackground(new Color(255, 99, 71));
		panelMoney.setBounds(545, 11, 161, 49);
		contentPane.add(panelMoney);
		panelMoney.setLayout(null);

		lblMoney = new JLabel("MONEY");
		lblMoney.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelMoney.setBackground(new Color(255, 99, 71));
				UpdateMoneyForm f = new UpdateMoneyForm();
				f.setVisible(true);

			}

			public void mousePressed(MouseEvent e) {
				panelMoney.setBackground(Color.blue);
			}

			public void mouseExited(MouseEvent e) {
				panelMoney.setBackground(new Color(255, 99, 71));

			}
		});
		lblMoney.setBackground(new Color(255, 255, 255));
		lblMoney.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/outline_request_quote_black_24dp.png")));
		lblMoney.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoney.setBounds(0, 0, 161, 49);
		panelMoney.add(lblMoney);

		JPanel panelCart = new JPanel();
		panelCart.setBackground(new Color(173, 255, 47));
		panelCart.setBounds(739, 11, 142, 49);
		contentPane.add(panelCart);
		panelCart.setLayout(null);

		JLabel lblCart = new JLabel("CART");
		lblCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelCart.setBackground(new Color(173, 255, 47));
				CartForm f = new CartForm();
				f.setVisible(true);

			}

			public void mousePressed(MouseEvent e) {
				panelCart.setBackground(Color.blue);
			}

			public void mouseExited(MouseEvent e) {
				panelCart.setBackground(new Color(173, 255, 47));

			}
		});
		lblCart.setIcon(new ImageIcon(UserMainForm.class.getResource("/image/baseline_shopping_cart_black_24dp.png")));
		lblCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblCart.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCart.setBounds(0, 0, 142, 49);
		panelCart.add(lblCart);

		/////// ====================================

		LoadLabel();
		LoadPanel();
		ManageWorkForm f = new ManageWorkForm();
		try {
			f.checkLate();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// comboBoxName.removeAllItems();
			updateDataComboBoxBookName(BookDAO.getNameBook());
			updateDataComboBoxReturn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ChangColor() {
		if (flag == 0) {
			return;
		} else if (flag == 2) {
			panelSearch.setBackground(Color.blue);
			panelList.setBackground(Color.green);
			panelBorrow.setBackground(Color.green);
			panelReturn.setBackground(Color.green);
		} else if (flag == 3) {
			panelSearch.setBackground(Color.green);
			panelList.setBackground(Color.blue);
			panelBorrow.setBackground(Color.green);
			panelReturn.setBackground(Color.green);
		} else if (flag == 4) {
			panelSearch.setBackground(Color.green);
			panelList.setBackground(Color.green);
			panelBorrow.setBackground(Color.blue);
			panelReturn.setBackground(Color.green);
		} else if (flag == 5) {
			panelSearch.setBackground(Color.green);
			panelList.setBackground(Color.green);
			panelBorrow.setBackground(Color.green);
			panelReturn.setBackground(Color.blue);
		}

	}

	public void LoadLabel() {
		try {
			ResultSet rs = UserDAO.getUserById(LoginForm.getId_user());
			while (rs.next()) {
				String name = rs.getString("name");
				int money = rs.getInt("money");
				lblUser.setText("WELLCOME: " + name);
				lblMoney.setText("MONEY: " + money + "$");
				us = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static User getUs() {
		return us;
	}

	public static void setUs(User us) {
		UserMainForm.us = us;
	}

	public void LoadPanel() {
		if (flag == 0) {
			lblDate.setVisible(true);
			lblTime.setVisible(true);
			lblHello.setVisible(true);
			LoadDateTime();

			scrollPane.setVisible(false);
			table.setVisible(false);
			textFieldSearch.setVisible(false);
			buttonSearch.setVisible(false);

			textFieldId.setVisible(false);
			textFieldLimitDay.setVisible(false);
			textFieldPrice.setVisible(false);
			textFieldAuthor.setVisible(false);
			textFieldPublisher.setVisible(false);
			lblBookId.setVisible(false);
			btnFind.setVisible(false);
			btnAddCart.setVisible(false);
			comboBoxName.setVisible(false);
			lblBookName.setVisible(false);
			lblAuthor.setVisible(false);
			lblPublisher.setVisible(false);
			lblLimitDay.setVisible(false);
			lblPrice.setVisible(false);
			lblPrice_1.setVisible(false);
			btnClear.setVisible(false);
			// ==
			comboBoxReturnName.setVisible(false);
			btnAddReturn.setVisible(false);
			lblTotalFee.setVisible(false);
			btnReturn.setVisible(false);

		} else if (flag == 2) {
			lblDate.setVisible(false);
			lblTime.setVisible(false);
			lblHello.setVisible(false);

			scrollPane.setVisible(true);
			table.setVisible(true);
			textFieldSearch.setVisible(true);
			buttonSearch.setVisible(true);
			scrollPane.setBounds(0, 89, 678, 260);

			textFieldId.setVisible(false);
			textFieldLimitDay.setVisible(false);
			textFieldPrice.setVisible(false);
			textFieldAuthor.setVisible(false);
			textFieldPublisher.setVisible(false);
			lblBookId.setVisible(false);
			btnFind.setVisible(false);
			btnAddCart.setVisible(false);
			comboBoxName.setVisible(false);
			lblBookName.setVisible(false);
			lblAuthor.setVisible(false);
			lblPublisher.setVisible(false);
			lblLimitDay.setVisible(false);
			lblPrice.setVisible(false);
			lblPrice_1.setVisible(false);
			btnClear.setVisible(false);
			// ==
			comboBoxReturnName.setVisible(false);
			btnAddReturn.setVisible(false);
			lblTotalFee.setVisible(false);
			btnReturn.setVisible(false);

		} else if (flag == 3) {
			lblDate.setVisible(false);
			lblTime.setVisible(false);
			lblHello.setVisible(false);

			scrollPane.setVisible(true);
			table.setVisible(true);
			textFieldSearch.setVisible(false);
			buttonSearch.setVisible(false);
			scrollPane.setBounds(0, 0, 678, 349);

			textFieldId.setVisible(false);
			textFieldLimitDay.setVisible(false);
			textFieldPrice.setVisible(false);
			textFieldAuthor.setVisible(false);
			textFieldPublisher.setVisible(false);
			lblBookId.setVisible(false);
			btnFind.setVisible(false);
			btnAddCart.setVisible(false);
			comboBoxName.setVisible(false);
			lblBookName.setVisible(false);
			lblAuthor.setVisible(false);
			lblPublisher.setVisible(false);
			lblLimitDay.setVisible(false);
			lblPrice.setVisible(false);
			lblPrice_1.setVisible(false);
			btnClear.setVisible(false);

			// ====
			comboBoxReturnName.setVisible(false);
			btnAddReturn.setVisible(false);
			lblTotalFee.setVisible(false);
			btnReturn.setVisible(false);

		} else if (flag == 4) {
			lblDate.setVisible(false);
			lblTime.setVisible(false);
			lblHello.setVisible(false);

			scrollPane.setVisible(false);
			table.setVisible(false);
			textFieldSearch.setVisible(false);
			buttonSearch.setVisible(false);

			textFieldId.setVisible(true);
			textFieldLimitDay.setVisible(true);
			textFieldPrice.setVisible(true);
			textFieldAuthor.setVisible(true);
			textFieldPublisher.setVisible(true);
			lblBookId.setVisible(true);
			btnFind.setVisible(true);
			btnAddCart.setVisible(true);
			comboBoxName.setVisible(true);
			lblBookName.setVisible(true);
			lblBookName.setBounds(57, 116, 118, 24);
			lblAuthor.setVisible(true);
			lblPublisher.setVisible(true);
			lblLimitDay.setVisible(true);
			lblPrice.setVisible(true);
			lblPrice_1.setVisible(true);
			btnClear.setVisible(true);

			// ==
			comboBoxReturnName.setVisible(false);
			btnAddReturn.setVisible(false);
			lblTotalFee.setVisible(false);
			btnReturn.setVisible(false);

		} else if (flag == 5) {
			lblDate.setVisible(false);
			lblTime.setVisible(false);
			lblHello.setVisible(false);

			scrollPane.setVisible(true); //
			table.setVisible(true); //
			scrollPane.setBounds(0, 80, 678, 210); //
			textFieldSearch.setVisible(false);
			buttonSearch.setVisible(false);

			textFieldId.setVisible(false);
			textFieldLimitDay.setVisible(false);
			textFieldPrice.setVisible(false);
			textFieldAuthor.setVisible(false);
			textFieldPublisher.setVisible(false);
			lblBookId.setVisible(false);
			btnFind.setVisible(false);
			btnAddCart.setVisible(false);
			comboBoxName.setVisible(false);
			// lblBookName.setVisible(false);
			lblAuthor.setVisible(false);
			lblPublisher.setVisible(false);
			lblLimitDay.setVisible(false);
			lblPrice.setVisible(false);
			lblPrice_1.setVisible(false);
			btnClear.setVisible(false);

			// ========
			comboBoxReturnName.setVisible(true);
			lblBookName.setVisible(true);
			btnAddReturn.setVisible(true);
			lblBookName.setBounds(57, 28, 118, 29);
			lblTotalFee.setVisible(true);
			btnReturn.setVisible(true);
			addCoboBoxReturnBook();
			loadLabelFee();
		}
	}

	public void LoadDateTime() {
		LocalDateTime now = java.time.LocalDateTime.now();

		lblDate.setText(now.getDayOfMonth() + "-" + now.getMonthValue() + "-" + now.getYear());
		lblTime.setText(now.getHour() + ":" + now.getMinute());
	}

	// ==========
	public void updateTableBook(ResultSet result) {
		tableModel.setRowCount(0);
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
		table.setModel(tableModel);

		designTable();

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

	// =============
	public void updateTableListBook(ResultSet result) {
		// table.set
		tableModel.setRowCount(0);
		String[] columnName = { "BookName", "Start_Date", "LimitDay", "Status", "Total" };
		tableModel.setColumnIdentifiers(columnName);
		try {
			while (result.next()) {
				String rows[] = new String[10];
				rows[0] = result.getString(4);
				rows[1] = result.getString(5);
				rows[2] = result.getString(9);
				rows[3] = result.getString(7);
				rows[4] = result.getString(8);
				/*
				 * rows[5] = result.getString(6); rows[6] = result.getString(7); rows[7] =
				 * result.getString(8);
				 */

				tableModel.addRow(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setModel(tableModel);
		designTableList();

	}

	public void designTableList() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.setRowHeight(50);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);

	}

	// =========
	public void updateTableReturnBook(ResultSet result) {
		// table.set
		tableModel.setRowCount(0);
		String[] columnName = { "BookName", "Start_Date", "End_Date", "LimitDay", "Late(Days)", "Fee" };
		tableModel.setColumnIdentifiers(columnName);

		table.setModel(tableModel);
		designTableList();

	}

	public void designTableReturnBook() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.setRowHeight(40);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);

	}

	// ============
	@SuppressWarnings("unchecked")
	public void updateDataComboBoxBookName(ResultSet rs) throws SQLException {

		comboBoxName.addItem("");
		while (rs.next()) {
			Publisher ts = new Publisher();
			ts.setName((rs.getString("name")));
			comboBoxName.addItem(ts.getName());
		}
	}

	@SuppressWarnings("unchecked")
	public void updateDataComboBoxReturn() throws SQLException {

		ResultSet rs = WorkDAO.getBookById(LoginForm.getId_user());
		comboBoxReturnName.addItem("");
		while (rs.next()) {
			String book_name = rs.getString(4);
			comboBoxReturnName.addItem(book_name);
		}
	}

	public int getLateDays(String id_book, int fee) throws ParseException, IOException {

		// late = fee - price

		int price = BookDAO.getFeeToId(id_book);
		return fee - price;
	}

	public boolean checkToReturnBook(String book_name) {
		for (int i = 0; i < tableModelReturn.getRowCount(); i++) {
			String nameInTable = tableModelReturn.getValueAt(i, 0).toString();
			if (nameInTable.equals(book_name)) {
				return false;
			}
		}
		return true;
	}

	public void addBookToTheRemoveList() {

		LocalDateTime now = java.time.LocalDateTime.now();
		String end_date;
		if(now.getDayOfMonth()< 10) {
			end_date = now.getYear() + "-" + now.getMonthValue() + "-0" + now.getDayOfMonth();
		}else {
			end_date = now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth();
		}
		
		String name = comboBoxReturnName.getSelectedItem().toString().trim();
		if (comboBoxReturnName.getSelectedItem().toString().trim().equals("") == true) {
			return;
		} else if (!checkToReturnBook(name)) {
			return;
		} else {

			String id_book = BookDAO.getIdtoNameBook(name);

			try {
				ResultSet result = WorkDAO.getBookByIdBook(LoginForm.getId_user(), id_book);
				while (result.next()) {
					String rows[] = new String[6];
					rows[0] = result.getString(4);
					rows[1] = result.getString(5);
					rows[2] = end_date;
					rows[3] = result.getString(9);
					rows[5] = result.getString(8);
					int late = getLateDays(id_book, Integer.parseInt(result.getString(8)));
					rows[4] = String.valueOf(late);

					tableModelReturn.addRow(rows);
				}
			} catch (SQLException | NumberFormatException | ParseException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		loadLabelFee();

	}
	
	public int getFee() {
		int fee = 0;
		for (int i = 0; i < tableModelReturn.getRowCount(); i++) {
			fee = fee + Integer.parseInt(tableModelReturn.getValueAt(i, 5).toString());
		}

		return fee;
	}
	
	public void loadLabelFee() {
		int fee = 0;
		for (int i = 0; i < tableModelReturn.getRowCount(); i++) {
			fee = fee + Integer.parseInt(tableModelReturn.getValueAt(i, 5).toString());
		}

		lblTotalFee.setText("TotalFee : " + fee + "$");
	}

	public Stack<String> getListName() {
		return listName;
	}

	public void setListName(Stack<String> listName) {
		this.listName = listName;
	}
	
	@SuppressWarnings("unchecked")
	public void addCoboBoxReturnBook() {
		while(!listName.empty()) {
			comboBoxReturnName.addItem(listName.pop());
		}
	}

	
}
