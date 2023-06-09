package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.BookDAO;
import DAO.UserDAO;
import DAO.WorkDAO;
import Model.JTableUtilities;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ManageWorkForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldFind;
	private int flag = 0;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private static int idWork;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ManageWorkForm frame = new
	 * ManageWorkForm(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public ManageWorkForm() {
		setTitle("ManageWorkForm");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1176, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 223, 375);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 223, 59);
		panel_1.setBackground(Color.RED);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblWork = new JLabel("MANAGE WORK");
		lblWork.setIcon(new ImageIcon(ManageWorkForm.class.getResource("/image/baseline_work_black_24dp.png")));
		lblWork.setBounds(0, 0, 223, 59);
		lblWork.setHorizontalAlignment(SwingConstants.CENTER);
		lblWork.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_1.add(lblWork);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 255, 0));
		panel_2.setBounds(29, 85, 167, 45);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblAll = new JLabel("ALL");
		lblAll.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				panel_2.setBackground(new Color(3, 59, 90));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel_2.setBackground(new Color(0, 255, 0));
				if (flag == 2) {
					panel_2.setBackground(Color.blue);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panel_2.setBackground(new Color(244, 53, 53).brighter());
				flag = 2;
				try {
					updateAllDataTable(WorkDAO.getAllWork());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				designTable();
				ChangColor();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2.setBackground(new Color(64, 64, 237).brighter());

			}

			@Override
			public void mousePressed(MouseEvent e) {
				panel_2.setBackground(new Color(244, 53, 53).brighter());
			}
		});
		lblAll.setIcon(new ImageIcon(ManageWorkForm.class.getResource("/image/outline_view_list_black_24dp.png")));
		lblAll.setBounds(0, 0, 167, 45);
		panel_2.add(lblAll);
		lblAll.setHorizontalAlignment(SwingConstants.LEFT);
		lblAll.setFont(new Font("Dialog", Font.BOLD, 15));

		panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 255, 0));
		panel_3.setBounds(29, 149, 167, 45);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblReturnedBook = new JLabel("RETURNED");
		lblReturnedBook.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				panel_3.setBackground(new Color(3, 59, 90));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel_3.setBackground(new Color(0, 255, 0));
				if (flag == 3) {
					panel_3.setBackground(Color.blue);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panel_3.setBackground(new Color(244, 53, 53).brighter());
				flag = 3;
				try {
					updateAllDataTable(WorkDAO.getStatusBook("YES"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				designTable();
				ChangColor();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3.setBackground(new Color(64, 64, 237).brighter());

			}

			@Override
			public void mousePressed(MouseEvent e) {
				panel_3.setBackground(new Color(244, 53, 53).brighter());
			}
		});
		lblReturnedBook.setIcon(
				new ImageIcon(ManageWorkForm.class.getResource("/image/outline_move_to_inbox_black_24dp.png")));
		lblReturnedBook.setBackground(new Color(255, 255, 255));
		lblReturnedBook.setBounds(0, 0, 167, 45);
		panel_3.add(lblReturnedBook);
		lblReturnedBook.setHorizontalAlignment(SwingConstants.LEFT);
		lblReturnedBook.setFont(new Font("Dialog", Font.BOLD, 15));

		panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 255, 0));
		panel_4.setBounds(29, 225, 167, 45);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNotReturn = new JLabel("BORROWED");
		lblNotReturn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				panel_4.setBackground(new Color(3, 59, 90));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel_4.setBackground(new Color(0, 255, 0));
				if (flag == 4) {
					panel_4.setBackground(Color.blue);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panel_4.setBackground(new Color(244, 53, 53).brighter());
				flag = 4;
				try {
					updateAllDataTable(WorkDAO.getStatusBook("NO"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				designTable();
				ChangColor();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panel_4.setBackground(new Color(64, 64, 237).brighter());

			}

			@Override
			public void mousePressed(MouseEvent e) {
				panel_4.setBackground(new Color(244, 53, 53).brighter());
			}
		});
		lblNotReturn
				.setIcon(new ImageIcon(ManageWorkForm.class.getResource("/image/outline_unarchive_black_24dp.png")));
		lblNotReturn.setBounds(0, 0, 167, 45);
		panel_4.add(lblNotReturn);
		lblNotReturn.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotReturn.setFont(new Font("Dialog", Font.BOLD, 15));

		panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 255, 0));
		panel_5.setBounds(29, 293, 167, 45);
		panel.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblLated = new JLabel("LATED");
		lblLated.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				panel_5.setBackground(new Color(3, 59, 90));

			}

			public void mouseExited(MouseEvent e) {
				panel_5.setBackground(new Color(0, 255, 0));
				if (flag == 5) {
					panel_5.setBackground(Color.blue);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				panel_5.setBackground(new Color(244, 53, 53).brighter());
				flag = 5;
				try {
					updateAllDataTable(WorkDAO.getStatusBook("LATE"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				designTable();
				ChangColor();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panel_5.setBackground(new Color(64, 64, 237).brighter());

			}

			@Override
			public void mousePressed(MouseEvent e) {
				panel_5.setBackground(new Color(244, 53, 53).brighter());
			}
		});
		lblLated.setIcon(
				new ImageIcon(ManageWorkForm.class.getResource("/image/outline_assignment_late_black_24dp.png")));
		lblLated.setBounds(0, 0, 167, 45);
		panel_5.add(lblLated);
		lblLated.setHorizontalAlignment(SwingConstants.LEFT);
		lblLated.setFont(new Font("Dialog", Font.BOLD, 15));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(233, 60, 917, 292);
		contentPane.add(scrollPane);

		String[] columnName = { "WordID", "UserId", "UserName", "BookName", "Start_Date", "End_Date", "Status",
				"Total" };
		tableModel.setColumnIdentifiers(columnName);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = table.getSelectedRow();

				TableModel model = table.getModel();
				idWork = Integer.parseInt(model.getValueAt(index, 0).toString());
				String id_user = (model.getValueAt(index, 1).toString());
				String value2 = (model.getValueAt(index, 3).toString());
				String id_book = BookDAO.getIdtoNameBook(value2);
				InformationBookForm f = new InformationBookForm();
				f.setVisible(true);
				try {
					ResultSet rsUser = UserDAO.getUserById(id_user);
					ResultSet rsBook = BookDAO.getBookById(id_book);
					while (rsUser.next()) {
						f.getTextUserId().setText(rsUser.getString(1));
						f.getTextUserName().setText(rsUser.getString(2));
						f.getTextUserBirthDay().setText(rsUser.getString(3));
						f.getTextUserPhone().setText(rsUser.getString(4));
						f.getTextFieldAddress().setText(rsUser.getString(5));
					}

					while (rsBook.next()) {
						f.getTextBookId().setText(rsBook.getString(1));
						f.getTextBookName().setText(rsBook.getString(2));
						f.getTextBookAuthor().setText(rsBook.getString(3));
						f.getTextBookPublisher().setText(rsBook.getString(4));
						f.getTextLimit().setText(rsBook.getString(6));
						f.getTextPrice().setText(rsBook.getString(7));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		/*
		 * table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 * table.getColumnModel().getColumn(1).setPreferredWidth(100);
		 * table.setRowHeight(50);
		 * table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		 */
		// table.getRow
		scrollPane.setViewportView(table);

		textFieldFind = new JTextField();
		textFieldFind.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldFind.setColumns(10);
		textFieldFind.setBounds(606, 20, 393, 29);
		contentPane.add(textFieldFind);

		JButton btnFind = new JButton("FIND");
		btnFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textFieldFind.getText().trim();
				try {
					updateAllDataTable(WorkDAO.findInformation(text));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				designTable();
				panel_3.setBackground(Color.green);
				panel_2.setBackground(Color.green);
				panel_4.setBackground(Color.green);
				panel_5.setBackground(Color.green);
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFind.setBounds(1052, 23, 98, 29);
		contentPane.add(btnFind);
		
		JButton btnNewButton = new JButton("EDIT");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateWorkForm f = new UpdateWorkForm();
				f.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 13));
		btnNewButton.setBounds(258, 11, 113, 43);
		contentPane.add(btnNewButton);

		// ==========
		// =======================
		// Check Late
		try {
			checkLate();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void ChangColor() {
		if (flag == 0) {
			return;
		} else if (flag == 2) {
			panel_2.setBackground(Color.blue);
			panel_3.setBackground(Color.green);
			panel_4.setBackground(Color.green);
			panel_5.setBackground(Color.green);
		} else if (flag == 3) {
			panel_3.setBackground(Color.blue);
			panel_2.setBackground(Color.green);
			panel_4.setBackground(Color.green);
			panel_5.setBackground(Color.green);
		} else if (flag == 4) {
			panel_4.setBackground(Color.blue);
			panel_3.setBackground(Color.green);
			panel_2.setBackground(Color.green);
			panel_5.setBackground(Color.green);
		} else if (flag == 5) {
			panel_5.setBackground(Color.blue);
			panel_3.setBackground(Color.green);
			panel_4.setBackground(Color.green);
			panel_2.setBackground(Color.green);
		}

	}

	public void updateAllDataTable(ResultSet result) {
		// table.set
		tableModel.setRowCount(0);
		String[] columnName = { "WordID", "UserId", "UserName", "BookName", "Start_Date", "End_Date", "Status",
				"Total" };
		tableModel.setColumnIdentifiers(columnName);
		try {
			while (result.next()) {
				String rows[] = new String[10];
				rows[0] = result.getString(1);
				rows[1] = result.getString(2);
				rows[2] = result.getString(3);
				rows[3] = result.getString(4);
				rows[4] = result.getString(5);
				rows[5] = result.getString(6);
				rows[6] = result.getString(7);
				rows[7] = result.getString(8);

				tableModel.addRow(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void designTable() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		// table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.setRowHeight(50);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);

	}

	public void checkLate() throws ParseException, IOException {
		try {
			ResultSet rs = WorkDAO.getWork();
			while (rs.next()) {
				int id = rs.getInt("id");
				String start_date = rs.getString("start_date");
				int limitDay = BookDAO.getLimitDayToId(rs.getString("id_book"));
				// ======================
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
				java.util.Date now = new java.util.Date();
				long diff = now.getTime() - date.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);

				// =======================
				if (diffDays > limitDay) {
					WorkDAO.UpdateStatusWork(id, "LATE");
					//lay gia tien
					int fee = BookDAO.getFeeToId(rs.getString("id_book"));
					
					//update
					int newFee = (int) (fee+ diffDays - limitDay);
					WorkDAO.UpdateMoney(id, newFee);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getIdWork() {
		return idWork;
	}

	public void setIdWork(int IdWork) {
		idWork = IdWork;
	}
}
