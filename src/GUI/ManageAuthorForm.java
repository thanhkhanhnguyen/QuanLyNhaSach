package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.AuthorDAO;
import Model.Author;
import Model.JTableUtilities;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageAuthorForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private static Author author;
	private static int flag=0;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageAuthorForm frame = new ManageAuthorForm();
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
	public ManageAuthorForm() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 408);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
        String[] columnName = { "ID", "Name", "Gender","Information" };
        tableModel.setColumnIdentifiers(columnName);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 385, 289);
		contentPane.add(scrollPane);
		
		table = new JTable(tableModel);
		table.setRowHeight(40);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();

			     TableModel model = table.getModel();
			     int value1 = Integer.parseInt((model.getValueAt(index, 0).toString()));
			     String value2 = (model.getValueAt(index, 1).toString());
			     String value3 = (model.getValueAt(index, 2).toString());
			     String value4 = (model.getValueAt(index, 3).toString());
			       
			     author = new Author(value1,value2,value3,value4);
			     flag=2;  
			     UpdateAuthorForm f = new UpdateAuthorForm();
			     f.setVisible(true);
			     //setVisible(false);
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(10, 0, 242, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAuthor = new JLabel("MANAGE AUTHOR");
		lblAuthor.setIcon(new ImageIcon(ManageAuthorForm.class.getResource("/image/baseline_face_black_24dp.png")));
		lblAuthor.setBounds(0, 0, 242, 70);
		panel.add(lblAuthor);
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				author = new Author();
				author.setGender("");
				flag=1;
				UpdateAuthorForm f= new UpdateAuthorForm();
				f.setVisible(true);
				f.getBtnDelete().setEnabled(false);
				//setVisible(false);
				
			}
		});
		btnAdd.setIcon(new ImageIcon(ManageAuthorForm.class.getResource("/image/Create.png")));
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(306, 11, 89, 47);
		contentPane.add(btnAdd);
		//=========================
		
		LoadData();
	}
	
	public void LoadData() throws SQLException {
		tableModel.setRowCount(0);
		updateDataTable(AuthorDAO.getAllAuthor());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
	}
	
	public void updateDataTable(ResultSet result){
		String[] columnName = { "ID", "Name", "Gender","Information" };
        tableModel.setColumnIdentifiers(columnName);
        try {
            while(result.next()){ 
                String rows[] = new String[6];
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

	public static Author getAuthor() {
		return author;
	}

	public void setAuthor(Author authors) {
		author = authors;
	}

	public static int getFlag() {
		return flag;
	}

	public static void setFlag(int flag) {
		ManageAuthorForm.flag = flag;
	}
}
