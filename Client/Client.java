package Client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.*;
import java.util.concurrent.*;

import Shared.*;

public class Client
{
    public static void main( String args[] ) throws Exception
    {
        System.out.println("Client started");

        for(int i=0; i<args.length; ++i)
            System.out.println("Args[" + i + "] = " + args[i]);

        if (args.length < 3)
        {
            System.out.println("Usage: <filename> <host> <server_port...>");
            return;
        }

        String mapFilename = args[0];
        String host = args[1];
        String[] serversPorts = new String[args.length - 2];

        for(int i=2; i<args.length; ++i)
            serversPorts[i] = args[i];

        try
        {
            if(System.getSecurityManager() == null)
                System.setSecurityManager(new SecurityManager());

            System.out.println("Getting map");
            // TODO

            System.out.println("Launching Dijkstra");
            new DijkstraClient(serversPorts).run();
        }
        catch(Exception e)
        {
            System.out.println("Sth crashed");
            e.printStackTrace();
        }
    }
}
