package views;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import controllers.Data;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


/**
 * Main window of the application.
 * @author ander
 *
 */
public class MainUI {

	private JFrame frame;
	private JTextField txtServer;
	private JTextField txtPort;
	private JButton btnLogin;
	private JButton btnLogout;
	private JButton btnQuery;
	private JButton btnExecute;
	private JTextPane sqlTxt;
	private JScrollPane jspInfo;
	private JTextPane infoTxt;
	private JScrollPane jspNotification;
	private JTextPane notifTxt;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screensize.getWidth();
		double height = screensize.getHeight();
		frame = new JFrame();
<<<<<<< HEAD
		frame.setResizable(false);
		frame.setBounds((int)(width-460)/2, (int)(height-500)/2, 460, 500);
=======
		frame.setBounds(100, 100, 460, 500);
>>>>>>> fa7f81e8273d11a10eca540f57242003b7e1c22d
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtServer = new JTextField();
		txtServer.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtServer.getText().equals("Server address")) {
					txtServer.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtServer.getText().equals("")) {
					txtServer.setText("Server address");
				}
			}
		});
		txtServer.setBounds(12, 12, 180, 18);
		txtServer.setToolTipText("Server address");
		txtServer.setText("Server address");
		panel.add(txtServer);
		txtServer.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtPort.getText().equals("Port")) {
					txtPort.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtPort.getText().equals("")) {
					txtPort.setText("Port");
				}
			}
		});
		txtPort.setToolTipText("Port");
		txtPort.setBounds(204, 12, 73, 18);
		txtPort.setText("Port");
		panel.add(txtPort);
		txtPort.setColumns(10);
		JScrollPane jspSql = new JScrollPane();
		jspSql.setBounds(12, 49, 342, 61);
		panel.add(jspSql);
		
		sqlTxt = new JTextPane();
		sqlTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (sqlTxt.getText().equals("SQL sentence")) {
					sqlTxt.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (sqlTxt.getText().equals("")) {
					sqlTxt.setText("SQL sentence");
				}
			}
		});
		sqlTxt.setToolTipText("SQL sentence");
		jspSql.setViewportView(sqlTxt);
		sqlTxt.setText("SQL sentence");
		
		jspInfo = new JScrollPane();
		jspInfo.setBounds(62, 150, 342, 159);
		panel.add(jspInfo);
		
		infoTxt = new JTextPane();
		infoTxt.setEditable(false);
		infoTxt.setText("Information area");
		jspInfo.setViewportView(infoTxt);
		
		jspNotification = new JScrollPane();
		jspNotification.setBounds(63, 334, 342, 90);
		panel.add(jspNotification);
		
		notifTxt = new JTextPane();
		notifTxt.setEditable(false);
		notifTxt.setText("Notification area");
		jspNotification.setViewportView(notifTxt);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnLogin.setBounds(289, 9, 65, 24);
		panel.add(btnLogin);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(366, 9, 80, 24);
		panel.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
		});
		
		btnQuery = new JButton("Query");
		btnQuery.setBounds(366, 49, 80, 24);
		panel.add(btnQuery);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSelectQuery();
			}
		});
		
		btnExecute = new JButton("Execute");
		btnExecute.setBounds(366, 86, 80, 24);
		panel.add(btnExecute);
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doExecuteQuery();
			}
		});
	}
	
	/**
	 * Open login window.
	 */
	private void login() {
		String server = txtServer.getText(); 
		String port = txtPort.getText();
<<<<<<< HEAD
		new LoginUI(server, port);
//		if (logged) {
//			notifTxt.setText("Success: connection stablished.");
//		} else {
//			notifTxt.setText("Error on login.");
//		}
=======
		LoginUI loginWindow = new LoginUI(server, port);
		while (loginWindow.isActive()) {
			frame.setEnabled(false);
		}
		frame.setEnabled(true);
>>>>>>> fa7f81e8273d11a10eca540f57242003b7e1c22d
	}
	
	/**
	 * Close the connection.
	 */
	private void logout() {
		boolean loggedOut = Data.getInstance().logout();
		if (loggedOut) {
			notifTxt.setText("Success: connection finished.");
		} else {
			notifTxt.setText("Error on logout.");
		}
	}

	/**
	 * Execute a select query.
	 */
	private void doSelectQuery() {
		String query = sqlTxt.getText();
		String[] result = Data.getInstance().selectQuery(query);
		if (result[0].equals("0")) {
			infoTxt.setText(result[1]);
			notifTxt.setText("Select OK.");
		}else if (result[0].equals("1")) {
			infoTxt.setText("ERROR");
			notifTxt.setText(result[1]);
		}
	}
	
	/**
	 * Execute any query that is not a select.
	 */
	private void doExecuteQuery() {
		String query = sqlTxt.getText();
		String[] result = Data.getInstance().executeQuery(query);
		if (result[0].equals("0")) {
			infoTxt.setText("Affected rows: " + result[1]);
			notifTxt.setText("Execute OK.");
		}else if (result[0].equals("1")) {
			infoTxt.setText("ERROR");
			notifTxt.setText(result[1]);
		}
	}
}
