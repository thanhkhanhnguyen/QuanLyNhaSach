package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.SaleDAO;
import Model.JTableUtilities;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ManageSalesForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private static String date = "";

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ManageSalesForm frame = new
	 * ManageSalesForm(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public ManageSalesForm() {
		setTitle("ManageSales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 937, 451);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(0, 62, 442, 350);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTable = new JLabel("TABLE");
		lblTable.setBackground(Color.WHITE);
		lblTable.setIcon(
				new ImageIcon(ManageSalesForm.class.getResource("/image/outline_request_quote_black_24dp.png")));
		lblTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblTable.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTable.setBounds(113, 0, 194, 53);
		panel.add(lblTable);

		String[] columnName = { "DATE", "TOTAL-MONEY" };
		tableModel.setColumnIdentifiers(columnName);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 422, 259);
		panel.add(scrollPane);

		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				date = (model.getValueAt(index, 0).toString());

				setVisible(true);
				descriptionSale f = new descriptionSale();
				f.setVisible(true);
			}
		});
		table.setRowHeight(50);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(452, 62, 469, 350);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCharts = new JLabel("CHARTS");
		lblCharts
				.setIcon(new ImageIcon(ManageSalesForm.class.getResource("/image/outline_leaderboard_black_24dp.png")));
		lblCharts.setHorizontalAlignment(SwingConstants.CENTER);
		lblCharts.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCharts.setBounds(152, 0, 208, 53);
		panel_1.add(lblCharts);
		
		ChartPanel chartPanel = new ChartPanel(createChart());
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		chartPanel.setBounds(10, 66, 449, 256);
		panel_1.add(chartPanel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 0, 0));
		panel_2.setBounds(310, 0, 252, 60);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblSales = new JLabel("MANAGE SALES");
		lblSales.setBounds(0, 0, 252, 60);
		panel_2.add(lblSales);
		lblSales.setBackground(new Color(255, 255, 255));
		lblSales.setIcon(new ImageIcon(ManageSalesForm.class.getResource("/image/baseline_receipt_black_24dp.png")));
		lblSales.setHorizontalAlignment(SwingConstants.CENTER);
		lblSales.setFont(new Font("Dialog", Font.BOLD, 20));

		// =======================

		try {
			updateAllDataTable(SaleDAO.getTotalMoney());
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//===============================

	public void updateAllDataTable(ResultSet result) {
		// table.set
		tableModel.setRowCount(0);
		String[] columnName = { "DATE", "TOTAL-MONEY" };
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

	public static String getDate() {
		return date;
	}

	public void setDate(String Date) {
		date = Date;
	}

	

	public static JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart("Sales", "Day", "$", createDataset(),
				PlotOrientation.VERTICAL, false, false, false);
		return barChart;
	}

	public static CategoryDataset createDataset() {
		ResultSet rs = null;
		try {
			 rs = SaleDAO.getTotalMoney();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int flag=7;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			while(rs.next() && flag != 0) {
				String date = rs.getString(1);
				int total = rs.getInt(2);
                               int beginIndex = 0;
                           if (date.substring(7, 8).equals("-")) {
                                 beginIndex = 8;
} else {
    beginIndex = 7;
}
				dataset.addValue(total, "$", date.substring(5));
				flag--;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataset;
	}
}
