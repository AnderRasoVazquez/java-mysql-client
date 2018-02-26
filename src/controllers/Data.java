package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Data {
	
	private static Data instance = null;
	
	private Connection conn;
	private Statement st;
	private ResultSet resultSet;
	
	private Data() { }
	
	public boolean login(String server, String port, String user, String pass) {
		this.logout();
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//open connection
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + server + ":" + port,
					// TODO preguntarle que usuario tenemos que usar
					user, pass);
//					"admAirdBD", "1234");
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		} 
		
	}
	
    public boolean logout() {
        try {
            if (conn != null) {
                conn.close();
            }
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
    }
    
	public static Data getInstance() {
		if (Data.instance == null) {
			Data.instance = new Data();
		}
		return Data.instance;
	}
	
	public String[] selectQuery(String query) {
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Si no se ha hecho login
		if (conn == null) {
			return new String[] {"1", "You are not logged in."};
		}

		//open connection
		try {
			st = conn.createStatement();
			resultSet = st.executeQuery(query);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			StringBuilder selectResult = new StringBuilder();
			while (resultSet.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					String columnValue = resultSet.getString(i);
					if (i > 1) selectResult.append(" || ");
					selectResult.append(rsmd.getColumnName(i) + ": " + columnValue);
				}
				selectResult.append("\n");
			}
            resultSet.close();
            st.close();
            System.out.println(selectResult);
			return new String[]{"0", selectResult.toString()};
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new String[] {"1", e.getMessage()};
		}
	}
	
	public String[] executeQuery(String query) {
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Si no se ha hecho login
		if (conn == null) {
			return new String[] {"1", "You are not logged in."};
		}

		//open connection
		try {
			st = conn.createStatement();
			int rows = st.executeUpdate(query);
			return new String[]{"0", "" + rows};
		} catch (SQLException e) {
			return new String[] {"1", e.getMessage()};
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Pruebas
		Data.getInstance().login("10.109.162.113", "8306", "admAirdBD", "1234");
//		Data.getInstance().close();
		System.out.println(Data.getInstance().selectQuery("SELECT aId, aName, aPrice FROM AirdBD.Apartment"));
//		System.out.println(Data.getInstance().selectQuery("SELECT * FROM DBer.Driver")[1]);
//		Data.getInstance().executeQuery("INSERT INTO DBer.Driver (dId, dName) VALUES (5, 'Caca')");
//		Data.getInstance().executeQuery("DELETE FROM DBer.Driver WHERE dID = 1");
//		Data.getInstance().executeQuery("UPDATE DBer.Driver SET dName = 'Patata' WHERE dId = 5");
//		System.out.println(Data.getInstance().selectQuery("SELECT * FROM DBer.Driver")[1]);
		
		System.out.println("MySQL is not funny holy hell!!!");

		Data.getInstance().logout();;
		Data.getInstance().login("192.168.0.156", "8306", "admAirdBD", "1234");
//		System.out.println(Data.getInstance().selectQuery("SELECT aId, aName, aPrice FROM AirdBD.Apartment"));
	}

}

	