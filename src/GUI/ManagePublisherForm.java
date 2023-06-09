package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.PublisherDAO;
import Model.JTableUtilities;
import Model.Publisher;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTextPane;

public class ManagePublisherForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldFind;
	private JTextPane textPaneAddress;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnAdd;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private static Publisher pu = new Publisher(0, "", "", "");

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ManagePublisherForm frame = new
	 * ManagePublisherForm(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ManagePublisherForm() throws SQLException {
		setTitle("ManagePublisherForm");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1026, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] columnName = { "ID", "Name", "Email", "Address" };
		tableModel.setColumnIdentifiers(columnName);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(356, 66, 644, 242);
		contentPane.add(scrollPane);

		table = new JTable(tableModel);
		table.setRowHeight(50);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();

				TableModel model = table.getModel();
				int value1 = Integer.parseInt((model.getValueAt(index, 0).toString()));
				String value2 = (model.getValueAt(index, 1).toString());
				String value3 = (model.getValueAt(index, 2).toString());
				String value4 = (model.getValueAt(index, 3).toString());

				textFieldName.setText(value2);
				textFieldEmail.setText(value3);
				textPaneAddress.setText(value4);

				pu.setId(value1);
				pu.setName(value2);
				pu.setEmail(value3);
				pu.setAddress(value4);
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);
				btnAdd.setEnabled(false);

			}
		});
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(0, 0, 357, 67);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblManagePublisher = new JLabel("MANAGE PUBLISHER");
		lblManagePublisher.setBackground(Color.WHITE);
		lblManagePublisher.setBounds(0, 0, 321, 67);
		panel.add(lblManagePublisher);
		lblManagePublisher.setIcon(
				new ImageIcon(ManagePublisherForm.class.getResource("/image/baseline_maps_home_work_black_24dp.png")));
		lblManagePublisher.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagePublisher.setFont(new Font("Dialog", Font.PLAIN, 18));

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldName.setColumns(10);
		textFieldName.setBounds(91, 105, 243, 29);
		contentPane.add(textFieldName);

		JLabel lblName = new JLabel("NAME");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblName.setBounds(10, 109, 49, 24);
		contentPane.add(lblName);

		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEmail.setBounds(10, 162, 49, 24);
		contentPane.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(91, 163, 243, 29);
		contentPane.add(textFieldEmail);

		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAddress.setBounds(9, 227, 72, 24);
		contentPane.add(lblAddress);

		textFieldFind = new JTextField();
		textFieldFind.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldFind.setColumns(10);
		textFieldFind.setBounds(458, 26, 395, 29);
		contentPane.add(textFieldFind);

		JButton btnFind = new JButton("FIND");
		btnFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//===
				String text = textFieldFind.getText().trim();
				tableModel.setRowCount(0);
				try {
					updateDataTable(PublisherDAO.getSearchInformation(text));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
					JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.setModel(tableModel);
			}
		});
		btnFind.setIcon(new ImageIcon(ManagePublisherForm.class.getResource("/image/Search.png")));
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFind.setBounds(902, 26, 98, 29);
		contentPane.add(btnFind);

		btnAdd = new JButton("ADD");
		btnAdd.addMouseListener(new MouseAdapter() {

			Component a = null;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFieldisEmpty() == true) {
					String name = textFieldName.getText().trim();
					String email = textFieldEmail.getText().trim();
					String address = textPaneAddress.getText().trim();
					Publisher pub = new Publisher(name, email, address);
					try {
						if (PublisherDAO.CheckPublisherExist(pub) == 0) {
							try {
								PublisherDAO.AddPublisher(pub);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(a, "Complete", "Add User", JOptionPane.INFORMATION_MESSAGE);
							setTextField();
						}
						else {
							JOptionPane.showMessageDialog(a, "Publisher's Information already exists", "WARNING", JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(a, "TextField is Empty", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAdd.setIcon(new ImageIcon(ManagePublisherForm.class.getResource("/image/Create.png")));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(10, 327, 89, 38);
		contentPane.add(btnAdd);

		btnDelete = new JButton("DELETE");
		btnDelete.setEnabled(false);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component a = null;
				int id = pu.getId();
				try {
					PublisherDAO.DeletePublisher(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(a, "Complete", "Delete User", JOptionPane.INFORMATION_MESSAGE);
				setTextField();
			}
		});
		btnDelete.setIcon(new ImageIcon(ManagePublisherForm.class.getResource("/image/Trash.png")));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(282, 327, 113, 38);
		contentPane.add(btnDelete);

		btnEdit = new JButton("EDIT");
		btnEdit.setEnabled(false);
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component a = null;
				if (textFieldisEmpty() == true) {
					int id = pu.getId();
					String name = textFieldName.getText().trim();
					String email = textFieldEmail.getText().trim();
					String address = textPaneAddress.getText().trim();
					Publisher pub = new Publisher(id, name, email, address);
					try {

						PublisherDAO.UpdatePublisher(pub);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(a, "Complete", "Update User", JOptionPane.INFORMATION_MESSAGE);
					setTextField();
				} else {
					JOptionPane.showMessageDialog(a, "TextField is Empty", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEdit.setIcon(new ImageIcon(ManagePublisherForm.class.getResource("/image/Edit.png")));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEdit.setBounds(142, 327, 98, 38);
		contentPane.add(btnEdit);

		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setTextField();
			}
		});
		btnRefresh.setIcon(new ImageIcon(ManagePublisherForm.class.getResource("/image/Refresh.png")));
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRefresh.setBounds(712, 327, 123, 38);
		contentPane.add(btnRefresh);

		textPaneAddress = new JTextPane();
		textPaneAddress.setBounds(91, 231, 243, 77);
		contentPane.add(textPaneAddress);
		
		//============
	
		LoadData();
	}

	public void LoadData() throws SQLException {
		updateDataTable(PublisherDAO.getAllPublisher());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
	}

	public void updateDataTable(ResultSet result) {
		String[] columnName = { "ID", "Name", "Email", "Address" };
		tableModel.setColumnIdentifiers(columnName);
		try {
			while (result.next()) {
				String rows[] = new String[4];
				rows[0] = result.getString(1);
				rows[1] = result.getString(2);
				rows[2] = result.getString(3);
				rows[3] = result.getString(4);
				tableModel.addRow(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setTextField() {
		textFieldName.setText("");
		textFieldEmail.setText("");
		textPaneAddress.setText("");
		textFieldFind.setText("");
		/*
		 * try { updateDataTable(PublisherDAO.getAllPublisher()); } catch (SQLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		tableModel.setRowCount(0);
		try {
			updateDataTable(PublisherDAO.getAllPublisher());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setModel(tableModel);
		
		btnEdit.setEnabled(false);
		btnDelete.setEnabled(false);
		btnAdd.setEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
	}

	public boolean textFieldisEmpty() {
		if (textFieldName.getText().isEmpty() == true || textFieldEmail.getText().isEmpty() == true
				|| textPaneAddress.getText().isEmpty() == true) {
			return false;
		}

		return true;
	}
}
