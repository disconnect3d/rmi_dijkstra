package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Shared.*;

public class Server extends UnicastRemoteObject implements ServerInterface {
    public Server() throws RemoteException {
        super();
    }

    public static void main(String args[]) throws Exception {
        if (args.length < 2) {
            System.out.println("Server usage: <host> <ports>...");
            return;
        }

        String host = args[0];

        for(int i=1; i<args.length; ++i) {
            try {
                String port = args[i];
                System.setProperty("java.rmi.server.hostname", host);
                Registry reg = LocateRegistry.createRegistry(Integer.parseInt(port));
                reg.rebind("server", new Server());
                System.out.println("Server started on " + host + ":" + port);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
