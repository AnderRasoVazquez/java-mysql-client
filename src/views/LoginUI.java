package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.Data;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUI extends JDialog {

	private String server;
	private String port;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField passwordField;


	/**
	 * Create the dialog.
	 */
	public LoginUI(String pServer, String pPort) {
		server = pServer;
		port = pPort;
		setTitle("Login");
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 283, 120);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(149, 12, 114, 19);
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 14, 119, 15);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 45, 70, 15);
		contentPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(149, 43, 114, 19);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton loginButton = new JButton("Login");
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (login()) {
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Error on login.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				loginButton.setActionCommand("");
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("");
				buttonPane.add(cancelButton);
			}
		}
		
		setVisible(true);
	}
	
	private boolean login() {
		String username = txtUser.getText();
		String password = passwordField.getText();
		return Data.getInstance().login(server, port, username, password);
	}
}
