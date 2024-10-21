import java.rmi.*;

public interface LabService extends Remote{
    public String getInformation() throws RemoteException;
}
