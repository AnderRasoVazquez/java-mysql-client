package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controllers.Data;

class Tests {

	@Test
	void test() {
		Data.getInstance().login("10.109.162.113", "8306", "admAirdBD", "1234");
//		Data.getInstance().close();
		System.out.println(Data.getInstance().selectQuery("SELECT aId, aName, aPrice FROM AirdBD.Apartment"));
		// error testing
		System.out.println(Data.getInstance().executeQuery("sanoterusantoeurasnto"));
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
