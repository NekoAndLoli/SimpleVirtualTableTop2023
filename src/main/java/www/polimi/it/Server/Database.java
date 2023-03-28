package www.polimi.it.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private Connection connection;
    private String path;

    private HashMap<Statement,PreparedStatement> statements = new HashMap<>();

    /**
     * Constructor
     * @param path Global path where the database is located
     */
    public Database(String path){
        this.path = path;
    }

    private void init() throws SQLException {
        statements.put(
                Statement.GET_MUSIC,
                connection.prepareStatement(
                        "SELECT name,location,is_local " +
                                "FROM Resources JOIN Types " +
                                "ON Resources.type = Types.type_id " +
                                "WHERE Types.type_name = \"music\"")
        );
        statements.put(
                Statement.GET_BG,
                connection.prepareStatement(
                        "SELECT name,location,is_local " +
                                "FROM Resources JOIN Types " +
                                "ON Resources.type = Types.type_id " +
                                "WHERE Types.type_name = \"bg\"")
        );
        statements.put(
                Statement.GET_TOKEN,
                connection.prepareStatement(
                        "SELECT name,location,is_local " +
                                "FROM Resources JOIN Types " +
                                "ON Resources.type = Types.type_id " +
                                "WHERE Types.type_name = \"token\"")
        );
    }

    /**
     * Should be called before a statement
     * @return
     */
    public boolean connect(){
        String url = "jdbc:sqlite:"+path;
        try {
            connection = DriverManager.getConnection(url);
            init();
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

    public ResultSet getTokens() throws SQLException {
        return getter(Statement.GET_TOKEN);
    }

    public ResultSet getMusics() throws SQLException{
        return getter(Statement.GET_MUSIC);
    }

    public ResultSet getBGs() throws SQLException {
        return getter(Statement.GET_BG);
    }

    public ResultSet getter(Statement statement) throws SQLException {
        return statements.get(statement).executeQuery();
    }

}
