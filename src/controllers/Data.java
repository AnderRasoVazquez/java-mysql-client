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
	
	public boolean login(String server, String port) {
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
					"admDBer", "1234");
//					"admAirdBD", "1234");
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		} 
		
	}
	
	public static Data getInstance() {
		if (Data.instance == null) {
			Data.instance = new Data();
		}
		return Data.instance;
	}
	
	public String selectQuery(String query) {
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Si no se ha hecho login
		if (conn == null) {
			return "No connection stablished.";
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
			return selectResult.toString();
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
	
	public void executeQuery(String query) {
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Si no se ha hecho login
		if (conn == null) {
			System.out.println("No connection stablished.");
		}

		//open connection
		try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// pruebas
		Data.getInstance().login("192.168.0.156", "8306");
//		Data.getInstance().close();
//		System.out.println(Data.getInstance().selectQuery("SELECT aId, aName, aPrice FROM AirdBD.Apartment"));
		System.out.println(Data.getInstance().selectQuery("SELECT * FROM DBer.Driver"));
		Data.getInstance().executeQuery("INSERT INTO DBer.Driver (dId, dName) VALUES (5, 'Caca')");
//		Data.getInstance().executeQuery("DELETE FROM DBer.Driver WHERE dID = 1");
		Data.getInstance().executeQuery("UPDATE DBer.Driver SET dName = 'Cacota' WHERE dId = 5");
		System.out.println(Data.getInstance().selectQuery("SELECT * FROM DBer.Driver"));
		
		System.out.println("MySQL is not funny holy hell!!!");

		Data.getInstance().logout();;
		Data.getInstance().login("192.168.0.156", "8306");
//		System.out.println(Data.getInstance().selectQuery("SELECT aId, aName, aPrice FROM AirdBD.Apartment"));
	}

}

	