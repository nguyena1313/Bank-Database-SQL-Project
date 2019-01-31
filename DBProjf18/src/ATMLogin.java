import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ATMLogin {

	private JFrame frmLogin;
	private JTextField cardNumber;
	private JTextField pinNumber;
	private static String account;
	private static Connection con;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMLogin window = new ATMLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ATMLogin() throws ClassNotFoundException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 386, 192);
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Card Number");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(56, 37, 118, 14);
		frmLogin.getContentPane().add(lblNewLabel);

		cardNumber = new JTextField();
		cardNumber.setBounds(174, 36, 86, 20);
		frmLogin.getContentPane().add(cardNumber);
		cardNumber.setColumns(10);

		JLabel lblPin = new JLabel("Pin");
		lblPin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPin.setBounds(56, 67, 108, 14);
		frmLogin.getContentPane().add(lblPin);

		pinNumber = new JTextField();
		pinNumber.setColumns(10);
		pinNumber.setBounds(174, 67, 86, 20);
		frmLogin.getContentPane().add(pinNumber);

		JButton enter = new JButton("Enter");
		enter.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				boolean working = false;
				try{

					con=DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/bankdata?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root","password"); 
					Class.forName("com.mysql.jdbc.Driver");

					try{
						Statement st = con.createStatement();
						String balanceQuery1 = String.format("SELECT account FROM card c WHERE c.cardnum=%s and c.pin=%s;" ,cardNumber.getText().trim(),pinNumber.getText().trim());
						ResultSet getAccount = st.executeQuery(balanceQuery1);
						

						//check the returned value, just need the account number.
						if(getAccount.first()!=true){
							InvalidLogin il = new InvalidLogin();
							il.NewScreen();
							cardNumber.setText("");
							pinNumber.setText("");
						}
						else{
							getAccount.beforeFirst();
							while(getAccount.next()) {
								account = getAccount.getString("account"); //got the account number, set as global variable
								//clear other info.
								cardNumber.setText("");
								pinNumber.setText("");
								working=true;
								con.close();
								//close screen
								frmLogin.dispose();
								//open new screen
								successfulLogin();
							}
						}
					}
					catch(Exception e){
						if(working==true) ; //do nothing
						else{
							InvalidLogin il = new InvalidLogin();
							il.NewScreen();
							cardNumber.setText("");
							pinNumber.setText("");
						}
					}
				}catch(Exception e){
					e.printStackTrace();
					InvalidLogin il = new InvalidLogin();
					il.NewScreen();
					cardNumber.setText("");
					pinNumber.setText("");

				}
			}
		});
		enter.setBounds(171, 98, 89, 23);
		frmLogin.getContentPane().add(enter);
	}

	private void successfulLogin() throws SQLException, ClassNotFoundException{
		ATMCustomerOptions options = new ATMCustomerOptions();
		options.NewScreen();
		options.startup();
	}

	public static String getAccount(){
		return account;
	}

}
