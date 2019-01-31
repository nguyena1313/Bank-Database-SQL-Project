import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class InvalidLogin {

	private JFrame frmError;
	private JTextField txtInvalidLoginInformation;

	/**
	 * Launch the application.
	 */
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvalidLogin window = new InvalidLogin();
					window.frmError.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InvalidLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmError = new JFrame();
		frmError.setTitle("Error");
		frmError.setBounds(100, 100, 424, 145);
		frmError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmError.getContentPane().setLayout(null);
		
		txtInvalidLoginInformation = new JTextField();
		txtInvalidLoginInformation.setHorizontalAlignment(SwingConstants.CENTER);
		txtInvalidLoginInformation.setForeground(Color.RED);
		txtInvalidLoginInformation.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtInvalidLoginInformation.setText("Invalid login information. \r\nReenter Information");
		txtInvalidLoginInformation.setBounds(0, 11, 414, 102);
		frmError.getContentPane().add(txtInvalidLoginInformation);
		txtInvalidLoginInformation.setColumns(10);
	}
}
