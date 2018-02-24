package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 
 */

/**
 * @author euiti
 *
 */
public class Data {
	
	Connection conn;
	Statement st;
	
	public Data() {
		//load Mysql driver
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//open connection
		try {
			conn = DriverManager.getConnection(
					// labo sotano
					//"jdbc:mysql://10.227.76.21:8306",
					// wifi ander
//					"jdbc:mysql://10.109.162.113:8306",
					// 192.168.1.128
					"jdbc:mysql://192.168.1.128:8306",
					"dummy", "foobar");
			conn.setAutoCommit(true);
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data myData = new Data();
		System.out.println("MySQL is funny!!!");

	}

}
