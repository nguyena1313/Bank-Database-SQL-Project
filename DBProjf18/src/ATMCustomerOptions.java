import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ATMCustomerOptions {

	private JFrame frame;
	private static String account;
	private static JTextField balanceField;
	private JTextField wdamount;
	private static String balance;
	private static Connection con;
	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void NewScreen() throws SQLException, ClassNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMCustomerOptions window = new ATMCustomerOptions();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void startup() throws SQLException, ClassNotFoundException{
		makeCon();
		setAccount();
		setBalance();
	}
	private static void makeCon() throws SQLException, ClassNotFoundException{
		 con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/bankdata?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root","password"); 
			 Class.forName("com.mysql.jdbc.Driver");
	}
	
	private static void setBalance() throws SQLException{ //set the balance

			 
				Statement st = con.createStatement();
				String balanceQuery1 = String.format("SELECT balance FROM Accounts a WHERE a.account=%s;" ,account);
				ResultSet getBalance = st.executeQuery(balanceQuery1);
				
				//check the returned value, just need the account number.
				getBalance.beforeFirst();
					while(getBalance.next()) {
						 balance = getBalance.getString("balance"); //get the balance of the account
					}
					balanceField.setText(balance.toString());
	}
	/**
	 * Create the application.
	 */
	public ATMCustomerOptions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 236, 299);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		balanceField = new JTextField();
		balanceField.setEditable(false);
		balanceField.setBounds(90, 16, 86, 20);
		frame.getContentPane().add(balanceField);
		balanceField.setColumns(10);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBalance.setBounds(10, 17, 70, 14);
		frame.getContentPane().add(lblBalance);
		
		JButton withdraw = new JButton("Withdraw");
		withdraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
		});
		withdraw.setFont(new Font("Tahoma", Font.BOLD, 16));
		withdraw.setBounds(44, 76, 132, 23);
		frame.getContentPane().add(withdraw);
		
		JButton deposit = new JButton("Deposit");
		deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		deposit.setFont(new Font("Tahoma", Font.BOLD, 16));
		deposit.setBounds(44, 121, 132, 23);
		frame.getContentPane().add(deposit);
		
		wdamount = new JTextField();
		wdamount.setToolTipText("Enter amount to be withdraw or deposited");
		wdamount.setFont(new Font("Tahoma", Font.ITALIC, 11));
		wdamount.setBounds(90, 182, 86, 20);
		frame.getContentPane().add(wdamount);
		wdamount.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Amount");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 183, 70, 14);
		frame.getContentPane().add(lblNewLabel);
	}
	
	private static void setAccount(){
		account=ATMLogin.getAccount();
	}
}
