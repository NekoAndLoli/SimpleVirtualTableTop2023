package www.polimi.it.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;
    private String path;

    /**
     * Constructor
     * @param path Global path where the database is located
     */
    public Database(String path){
        this.path = path;
    }

    /**
     * Should be called before a statement
     * @return
     */
    public boolean connect(){
        String url = "jdbc:sqlite:"+path;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Should be called when the database is no more needed
     */
    public void close(){
        if(connection != null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}