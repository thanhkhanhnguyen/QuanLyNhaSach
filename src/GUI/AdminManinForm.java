package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import DAO.MY_DB;
import DAO.UserDAO;

import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class AdminManinForm extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JLabel lblWellcome;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MY_DB.myConnection();
					AdminManinForm frame = new AdminManinForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminManinForm() {
		//AdminMainForm f=new AdminMainForm();
		setTitle("AdminMainForm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 439);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(0, 0, 297, 328);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 0, 0));
		panel_1.setBounds(0, 0, 297, 72);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MANAGE LIBRARY");
		lblNewLabel.setIcon(new ImageIcon(AdminManinForm.class.getResource("/image/baseline_library_books_black_24dp.png")));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 277, 50);
		panel_1.add(lblNewLabel);
		
		JPanel panelUser = new JPanel();
		
		
		panelUser.setBackground(new Color(0, 255, 0));
		panelUser.setBounds(22, 102, 252, 49);
		panel.add(panelUser);
		panelUser.setLayout(null);
		
		JLabel lblUser = new JLabel("MANAGE USER");
		lblUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelUser.setBackground(new Color(3, 59, 90));
				
		    }

		    @Override
		    public void mousePressed(MouseEvent e) {
		        // Not working :(
		    	panelUser.setBackground(new Color(244, 53, 53).brighter());
		    	
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	panelUser.setBackground(new Color(0,255,0));
		    	
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	panelUser.setBackground(new Color(64, 64, 237).brighter());
		    	
		    }

		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	panelUser.setBackground(new Color(244, 53, 53).brighter());
		    	
		    	//setVisible(true);
		    	ManageUserForm window;
				try {
					window = new ManageUserForm ();
					window.setVisible(true);
					UpdateUserForm.setForm(window);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		    }
		});
		lblUser.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//panelUser.setBackground(Color.blue);
			}
		});
		lblUser.setIcon(new ImageIcon(AdminManinForm.class.getResource("/image/baseline_account_circle_black_24dp.png")));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUser.setBounds(0, 0, 252, 49);
		panelUser.add(lblUser);
		
		JPanel panelBook = new JPanel();
		panelBook.setLayout(null);
		panelBook.setBackground(Color.GREEN);
		panelBook.setBounds(22, 180, 252, 49);
		panel.add(panelBook);
		
		JLabel lblBook = new JLabel("MANAGE BOOK");
		lblBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelBook.setBackground(new Color(3, 59, 90));
		    }

		    @Override
		    public void mousePressed(MouseEvent e) {
		        // Not working :(
		    	panelBook.setBackground(new Color(244, 53, 53).brighter());
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	panelBook.setBackground(new Color(0,255,0));
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	panelBook.setBackground(new Color(64, 64, 237).brighter());
		    }

		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	panelBook.setBackground(new Color(244, 53, 53).brighter());
		    	//setVisible(true);
		    	ManageBookForm window = new ManageBookForm ();
				window.setVisible(true);
				
				
		    }
		});
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setIcon(new ImageIcon(AdminManinForm.class.getResource("/image/baseline_menu_book_black_24dp.png")));
		lblBook.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBook.setBounds(0, 0, 252, 49);
		panelBook.add(lblBook);
		
		JPanel panelAuthor = new JPanel();
		panelAuthor.setLayout(null);
		panelAuthor.setBackground(Color.GREEN);
		panelAuthor.setBounds(22, 255, 252, 49);
		panel.add(panelAuthor);
		
		JLabel lblAuthor = new JLabel("MANAGE AUTHOR");
		lblAuthor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelAuthor.setBackground(new Color(3, 59, 90));
		    }

		    @Override
		    public void mousePressed(MouseEvent e) {
		        // Not working :(
		    	panelAuthor.setBackground(new Color(244, 53, 53).brighter());
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	panelAuthor.setBackground(new Color(0,255,0));
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	panelAuthor.setBackground(new Color(64, 64, 237).brighter());
		    }

		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	panelAuthor.setBackground(new Color(244, 53, 53).brighter());
		    	ManageAuthorForm window;
				try {
					window = new ManageAuthorForm ();
					window.setVisible(true);
					UpdateAuthorForm.setForm(window);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		    }
		});
		lblAuthor.setIcon(new ImageIcon(AdminManinForm.class.getResource("/image/baseline_face_black_24dp.png")));
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAuthor.setBounds(0, 0, 252, 49);
		panelAuthor.add(lblAuthor);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(47, 79, 79));
		panel_2.setBounds(296, 72, 317, 256);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panelWork = new JPanel();
		panelWork.setLayout(null);
		panelWork.setBackground(Color.GREEN);
		panelWork.setBounds(36, 107, 252, 49);
		panel_2.add(panelWork);
		
		JLabel lblWork = new JLabel("MANAGE WORK");
		lblWork.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelWork.setBackground(new Color(3, 59, 90));
		    }

		    @Override
		    public void mousePressed(MouseEvent e) {
		        // Not working :(
		    	panelWork.setBackground(new Color(244, 53, 53).brighter());
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	panelWork.setBackground(new Color(0,255,0));
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	panelWork.setBackground(new Color(64, 64, 237).brighter());
		    }

		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	panelWork.setBackground(new Color(244, 53, 53).brighter());
		    	ManageWorkForm window = new ManageWorkForm ();
				window.setVisible(true);
		    }
		});
		lblWork.setIcon(new ImageIcon(AdminManinForm.class.getResource("/image/baseline_work_black_24dp.png")));
		lblWork.setHorizontalAlignment(SwingConstants.CENTER);
		lblWork.setFont(new Font("Dialog", Font.BOLD, 15));
		lblWork.setBounds(0, 0, 252, 49);
		panelWork.add(lblWork);
		
		JPanel panelSales = new JPanel();
		panelSales.setBounds(36, 182, 252, 49);
		panel_2.add(panelSales);
		panelSales.setLayout(null);
		panelSales.setBackground(Color.GREEN);
		
		JLabel lblSales = new JLabel("MANAGE SALES");
		lblSales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelSales.setBackground(new Color(3, 59, 90));
		    }

		    @Override
		    public void mousePressed(MouseEvent e) {
		        // Not working :(
		    	panelSales.setBackground(new Color(244, 53, 53).brighter());
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	panelSales.setBackground(new Color(0,255,0));
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	panelSales.setBackground(new Color(64, 64, 237).brighter());
		    }

		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	panelSales.setBackground(new Color(244, 53, 53).brighter());
		    	ManageSalesForm window = new ManageSalesForm();
		    	//System.out.print("gg");
				window.setVisible(true);
		    }
		});
		lblSales.setIcon(new ImageIcon(AdminManinForm.class.getResource("/image/baseline_receipt_black_24dp.png")));
		lblSales.setHorizontalAlignment(SwingConstants.CENTER);
		lblSales.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSales.setBounds(0, 0, 252, 49);
		panelSales.add(lblSales);
		
		JPanel panelPublisher = new JPanel();
		panelPublisher.setLayout(null);
		panelPublisher.setBackground(Color.GREEN);
		panelPublisher.setBounds(36, 31, 252, 49);
		panel_2.add(panelPublisher);
		
		JLabel lblPublisher = new JLabel("MANAGE PUBLISHER");
		lblPublisher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				panelPublisher.setBackground(new Color(3, 59, 90));
		    }

		    @Override
		    public void mousePressed(MouseEvent e) {
		        // Not working :(
		    	panelPublisher.setBackground(new Color(244, 53, 53).brighter());
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	panelPublisher.setBackground(new Color(0,255,0));
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	panelPublisher.setBackground(new Color(64, 64, 237).brighter());
		    }

		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	panelPublisher.setBackground(new Color(244, 53, 53).brighter());
		    	//setVisible(true);
		    	ManagePublisherForm window;
				try {
					window = new ManagePublisherForm ();
					window.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		    }
		});
		lblPublisher.setIcon(new ImageIcon(AdminManinForm.class.getResource("/image/baseline_maps_home_work_black_24dp.png")));
		lblPublisher.setHorizontalAlignment(SwingConstants.CENTER);
		lblPublisher.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPublisher.setBounds(0, 0, 252, 49);
		panelPublisher.add(lblPublisher);
		
		lblWellcome = new JLabel("WELLCOME :");
		lblWellcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWellcome.setBackground(new Color(0, 255, 255));
		lblWellcome.setIcon(new ImageIcon(AdminManinForm.class.getResource("/image/User.png")));
		lblWellcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWellcome.setBounds(347, 11, 194, 37);
		contentPane.add(lblWellcome);
		
		JButton btnLogout = new JButton("LOG OUT");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				LoginForm f= new LoginForm();
				f.setVisible(true);
			}
		});
		btnLogout.setIcon(new ImageIcon(AdminManinForm.class.getResource("/image/Log out.png")));
		btnLogout.setFont(new Font("Dialog", Font.BOLD, 13));
		btnLogout.setBounds(460, 338, 126, 51);
		contentPane.add(btnLogout);
		
		//=============
		LoadLabel();
	}
	
	public void LoadLabel() {
		try {
			ResultSet rs = UserDAO.getUserById(LoginForm.getId_user());
			while(rs.next()) {
				String name = rs.getString("name");
				lblWellcome.setText("WellCome: "+name);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
