package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.UserDAO;
import Model.JTableUtilities;
import Model.User;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ManageUserForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private static  User us;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private static int flag=0;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageUserForm frame = new ManageUserForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ManageUserForm() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 418);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//String[][] data = {
				//{ "1911", "Nguyen", "2001/09/14","nguyen@gmail.com","TPHCM","123456" },
				//{ "1912", "Phat", "456","phat@yahoo.com","HN","123456" }
                
        //};
        // Column Names
        String[] columnName = { "ID", "Name", "BirthDay","Phone","Address","Password" };
        tableModel.setColumnIdentifiers(columnName);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 561, 300);
		contentPane.add(scrollPane);
		
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		table.setRowHeight(40);
		table.addMouseListener(new MouseAdapter() {
			//@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();

			       TableModel model = table.getModel();
			       String value1 = (model.getValueAt(index, 0).toString());
			       String value2 = (model.getValueAt(index, 1).toString());
			       String value3 = (model.getValueAt(index, 2).toString());
			       String value4 = (model.getValueAt(index, 3).toString());
			       String value5 =  model.getValueAt(index, 4).toString();
			       String value6 =  model.getValueAt(index, 5).toString();
			       
			       
			       us = new User(value1,value2,value3,value4,value5,value6);
			       
			       
			       flag=2;
			       UpdateUserForm f = new UpdateUserForm();
			       f.setVisible(true);
			       f.getTextFieldId().setEnabled(false);
			       //setVisible(false);
			       
			      
			       
			       
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(10, 0, 264, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblManageUser = new JLabel("MANAGE USER");
		lblManageUser.setBounds(0, 0, 264, 68);
		panel.add(lblManageUser);
		lblManageUser.setIcon(new ImageIcon(ManageUserForm.class.getResource("/image/baseline_account_circle_black_24dp.png")));
		lblManageUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageUser.setFont(new Font("Dialog", Font.PLAIN, 22));
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				flag = 1;
				us = new User();
		    	UpdateUserForm window = new UpdateUserForm ();
				window.setVisible(true);
				//setVisible(false);
			}
		});
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setIcon(new ImageIcon(ManageUserForm.class.getResource("/image/Add.png")));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(438, 23, 133, 45);
		contentPane.add(btnAdd);
		//====================
		
		LoadData();
	}

	public static User getUs() {
		return us;
	}

	public void setUs(User Us) {
		us = Us;
	}
	
	public static int getFlag() {
		return flag;
	}

	public void setFlag(int Flag) {
		flag = Flag;
	}
	
	public void LoadData() throws SQLException {
		tableModel.setRowCount(0);
		updateDataTable(UserDAO.getAllUser());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
	}
	
	public void updateDataTable(ResultSet result){
		String[] columnName = { "ID", "Name", "BirthDay","Phone","Address","Password" };
        tableModel.setColumnIdentifiers(columnName);
        try {
            while(result.next()){ 
                String rows[] = new String[6];
                rows[0] = result.getString(1); 
                rows[1] = result.getString(2); 
                rows[2] = result.getString(3);
                rows[3] = result.getString(4);
                rows[4] = result.getString(5);
                rows[5] = result.getString(6);
                tableModel.addRow(rows); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }

	
}
