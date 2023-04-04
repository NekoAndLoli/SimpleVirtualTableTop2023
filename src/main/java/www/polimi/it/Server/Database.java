package www.polimi.it.Server;

import java.net.URI;
import java.sql.*;
import java.util.HashMap;

public class Database {
    private Connection connection;
    private final String path;
    private final HashMap<Statement,PreparedStatement> statements = new HashMap<>();

    /**
     * Constructor
     * @param path Global path where the database is located
     */
    public Database(String path){
        this.path = path;
    }

    /**
     * Builds up the statements
     * @throws SQLException if connection is closed or not accepted
     */
    private void init() throws SQLException {
        statements.put(
                Statement.GET_MUSIC,
                connection.prepareStatement(getQuery("music"))
        );
        statements.put(
                Statement.GET_BG,
                connection.prepareStatement(getQuery("bg"))
        );
        statements.put(
                Statement.GET_TOKEN,
                connection.prepareStatement(getQuery("token"))
        );
    }

    /**
     * Builds a basic query to select resources
     * @param string type of the resource to get
     * @return the query string
     */
    private String getQuery(String string){
        return "SELECT id,name,location,is_local " +
                "FROM Resources JOIN Types " +
                "ON Resources.type = Types.type_id " +
                "WHERE Types.type_name = \"" + string +"\"";
    }

    /**
     * Should be called before a statement
     * @return true if the connection is successful
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

    /**
     * gets the token resources
     * @return a ResultSet containing the tokens in the database
     * @throws SQLException if error occurs on the query
     */
    public ResultSet getTokens() throws SQLException {
        return getter(Statement.GET_TOKEN);
    }

    /**
     * gets the music resources
     * @return a ResultSet containing the BGM in the database
     * @throws SQLException if error occurs on the query
     */
    public ResultSet getMusics() throws SQLException{
        return getter(Statement.GET_MUSIC);
    }

    /**
     * gets the background resources
     * @return a ResultSet containing the background images in the database
     * @throws SQLException if error occurs on the query
     */
    public ResultSet getBGs() throws SQLException {
        return getter(Statement.GET_BG);
    }

    /**
     *
     * @param statement type of statement to call
     * @return a ResultSet in base of the statement called
     * @throws SQLException if error occurs on the query
     */
    private ResultSet getter(Statement statement) throws SQLException {
        return statements.get(statement).executeQuery();
    }

    public void insertMap(URI uri){
        //TODO
    }

    public void insertToken(URI uri){//TODO

    }
    public void insertMusic(URI uri){//TODO

    }
}
