package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class MainUI {

	private JFrame frame;
	private JTextField txtServer;
	private JTextField txtPort;
	private JButton btnNewButton;
	private JButton btnLogout;
	private JButton btnQuery;
	private JButton btnExecute;
	private JTextPane sqlTxt;
	private JScrollPane jsp;
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
		frame = new JFrame();
//		frame.setBounds(100, 100, 450, 300);
		frame.setBounds(100, 100, 460, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtServer = new JTextField();
		txtServer.setBounds(12, 12, 180, 18);
		txtServer.setText("Server address");
		txtServer.setToolTipText("");
		panel.add(txtServer);
		txtServer.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setBounds(204, 12, 73, 18);
		txtPort.setText("Port");
		panel.add(txtPort);
		txtPort.setColumns(10);
		JScrollPane jspSql = new JScrollPane();
		jspSql.setBounds(12, 49, 342, 61);
		panel.add(jspSql);
		
		sqlTxt = new JTextPane();
		jspSql.setViewportView(sqlTxt);
		sqlTxt.setText("Sql sentence");
		
		jspInfo = new JScrollPane();
		jspInfo.setBounds(62, 150, 342, 159);
		panel.add(jspInfo);
		
		infoTxt = new JTextPane();
		infoTxt.setEditable(false);
		infoTxt.setText("Information area");
		jspInfo.setViewportView(infoTxt);
		
		jspNotification = new JScrollPane();
		jspNotification.setBounds(63, 334, 349, 90);
		panel.add(jspNotification);
		
		notifTxt = new JTextPane();
		notifTxt.setEditable(false);
		notifTxt.setText("Notification area");
		jspNotification.setViewportView(notifTxt);
		
		btnNewButton = new JButton("Login");
		btnNewButton.setBounds(289, 9, 65, 24);
		panel.add(btnNewButton);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(366, 9, 80, 24);
		panel.add(btnLogout);
		
		btnQuery = new JButton("Query");
		btnQuery.setBounds(366, 49, 80, 24);
		panel.add(btnQuery);
		
		btnExecute = new JButton("Execute");
		btnExecute.setBounds(366, 86, 80, 24);
		panel.add(btnExecute);
	}
}
