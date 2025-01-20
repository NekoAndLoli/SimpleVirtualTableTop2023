package www.polimi.it;

import www.polimi.it.Communication.Server;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ServerApp
{
    public static void main( String[] args )
    {
        Server server;
        System.out.println( "Hello World!" );
        try {
            server = new Server(6666);
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
