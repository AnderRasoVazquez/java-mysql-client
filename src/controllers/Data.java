package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * MySQL connection manager.
 * @author ander
 *
 */
public class Data {
	
	private static Data instance = null;
	
	private Connection conn;
	private Statement st;
	private ResultSet resultSet;
	
	private Data() { }
	
	/**
	 * Create a conexion with MySQL.
	 * @param server
	 * @param port
	 * @param user
	 * @param pass
	 * @return
	 */
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
					user, pass);
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		} 
		
	}
	
	/**
	 * Close the connection with MySQL.
	 * @return true if not error, false otherwise.
	 */
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
     * Get the instance of the MySQL connection manager.
     * @return the instance
     */
	public static Data getInstance() {
		if (Data.instance == null) {
			Data.instance = new Data();
		}
		return Data.instance;
	}
	
	/**
	 * Executes a SELECT query.
	 * @param query
	 * @return String array
	 * 		   Index[0] -> "1" if error, "0" otherwise
	 *         Index[1] -> Query result or error.
	 */
	public String[] selectQuery(String query) {
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Si no se ha hecho login
		if (conn == null) {
			return new String[] {"1", "No connection stablished."};
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
	
	/**
	 * Execute a not SELECT query.
	 * @param query
	 * @return
	 */
	public String[] executeQuery(String query) {
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// if not logged
		if (conn == null) {
			System.out.println("No connection stablished.");
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

}

	