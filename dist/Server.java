import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class Server extends UnicastRemoteObject implements ServerInterface
{
    public Server() throws RemoteException
    {
        super();
    }

    public TaskData calculate(TaskData data) throws RemoteException
    {
        System.out.println("Server data a=" + data.a + ", b=" + data.b);
        data.a = data.a + 10;
        data.b = data.b + 10;
        return data;
    }

    public static void main(String args[]) throws Exception
    {
        String host = InetAddress.getLocalHost().getHostName();

        String bindAddr = "//" + host + ":14331/dijkstra" + args[0];

        if (System.getSecurityManager() == null)
            Naming.rebind(bindAddr, new Server());

        System.out.println("Server started " + bindAddr);
    }
}
