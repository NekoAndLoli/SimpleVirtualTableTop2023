package www.polimi.it;

import org.junit.Test;
import org.junit.Assert.*;
import www.polimi.it.Server.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseTest {

    @Test
    public void ConnectionTest(){
        Database database = new Database("src/main/resources/resources.db");
        ResultSet res;
        database.connect();
        try {
            res = database.getMusics();
            System.out.println(res.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.close();
        HashMap hashMap = new HashMap();

    }

}
