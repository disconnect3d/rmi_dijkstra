import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.*;

public class Client
{
    private static void loadMap(String fileName) throws IOException
    {
        System.out.println("TODO: Load map");
    }

    public static void main( String args[] ) throws Exception
    {
        System.out.println("Client started");

        for(int i=0; i<args.length; ++i)
            System.out.println("Args[" + i + "] = " + args[i]);

        if (args.length < 3)
        {
            System.out.println("Usage: <host> <filename> <servers_count>");
            return;
        }

        String host = args[0];
        int serversCount = new Integer(args[1]);
        loadMap(args[2]);

        try
        {
            if(System.getSecurityManager() == null)
                System.setSecurityManager(new SecurityManager());


            ServerInterface[] servers = new ServerInterface[serversCount];
            for(int i=0; i<serversCount; ++i)
                servers[i] = (ServerInterface) Naming.lookup("//" + host + ":14331/dijkstra" + (i+1));

            System.out.println("Dividing work");
            
            TaskData data = new TaskData();
            data.a = 1;
            data.b = 123;
            for(int i=0; i<serversCount; ++i)
            {
                data = servers[i].calculate(data);
                System.out.println("Worker " + i + " a=" + data.a + ", b=" + data.b);
            }

        }
        catch(Exception e)
        {
            System.out.println("Sth crashed");
            e.printStackTrace();
        }
    }
}
