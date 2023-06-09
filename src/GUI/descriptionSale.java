package GUI;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.SaleDAO;
import Model.JTableUtilities;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class descriptionSale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblDate;
	private DefaultTableModel tableModel = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					descriptionSale frame = new descriptionSale();
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
	public descriptionSale() {
		setTitle("DescriptionSale");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.RED);
		panel_2.setBounds(10, 11, 414, 60);
		contentPane.add(panel_2);
		
		lblDate = new JLabel("DATE :");
		lblDate.setIcon(new ImageIcon(descriptionSale.class.getResource("/image/baseline_date_range_black_24dp.png")));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Dialog", Font.BOLD, 20));
		lblDate.setBackground(Color.WHITE);
		lblDate.setBounds(0, 0, 414, 60);
		panel_2.add(lblDate);
		
		String[] columnName = { "USER_ID","MONEY" };
		tableModel.setColumnIdentifiers(columnName);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 414, 281);
		contentPane.add(scrollPane);
		
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		loadData();
	}
	
	public void loadData() {
		try {
			lblDate.setText("DATE : "+ManageSalesForm.getDate());
			updateAllDataTable(SaleDAO.getMoney(ManageSalesForm.getDate()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setRowHeight(50);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
	}
	
	public void updateAllDataTable(ResultSet result) {
		//table.set
		tableModel.setRowCount(0);
		String[] columnName = { "USER_ID","MONEY" };
		tableModel.setColumnIdentifiers(columnName);
		try {
			while (result.next()) {
				String rows[] = new String[2];
				rows[0] = result.getString(1);
				rows[1] = result.getString(2);
				tableModel.addRow(rows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
