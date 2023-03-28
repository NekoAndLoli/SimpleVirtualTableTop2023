package www.polimi.it;

import org.junit.Test;
import org.junit.Assert.*;
import www.polimi.it.Server.Database;

import java.util.HashMap;

public class DatabaseTest {

    @Test
    public void ConnectionTest(){
        Database database = new Database("D:/Documents/UNI/Software/SimpleVirtualTableTop2023/src/main/resources/resources.db");
        database.connect();
        database.close();
        HashMap hashMap = new HashMap();

    }

}
