import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame mainmenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.mainmenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainmenu = new JFrame();
		mainmenu.setTitle("Main Menu");
		mainmenu.setBounds(100, 100, 255, 446);
		mainmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainmenu.getContentPane().setLayout(null);
		
		Button atm = new Button("Customer: ATM");
		atm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		atm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ATMLogin atm;
				try {
					atm = new ATMLogin();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ATMLogin.NewScreen();
			}
		});
		atm.setFont(new Font("Dialog", Font.BOLD, 18));
		atm.setBounds(39, 35, 160, 80);
		mainmenu.getContentPane().add(atm);
		
		Button online = new Button("Customer: Online");
		online.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		online.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Online online = new Online();
				online.NewScreen();
			}
		});
		online.setFont(new Font("Dialog", Font.BOLD, 18));
		online.setBounds(39, 146, 160, 80);
		mainmenu.getContentPane().add(online);
		
		Button employee = new Button("Employee");
		employee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		employee.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Employee employee = new Employee();
				employee.NewScreen();
			}
		});
		employee.setFont(new Font("Dialog", Font.BOLD, 18));
		employee.setBounds(39, 273, 160, 80);
		mainmenu.getContentPane().add(employee);
	}
}
