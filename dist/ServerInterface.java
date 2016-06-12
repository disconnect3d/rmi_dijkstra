import java.rmi.*;

public interface ServerInterface extends Remote
{
    public TaskData calculate(TaskData data) throws RemoteException;
}
