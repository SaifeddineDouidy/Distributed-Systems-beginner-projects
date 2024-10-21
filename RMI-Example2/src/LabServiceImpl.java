import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class LabServiceImpl extends UnicastRemoteObject implements LabService {

    private static final long serialVersionUID = 2674880711467464646L;
    protected LabServiceImpl() throws RemoteException {
        super();
    }
    @Override
    public String getInformation() throws RemoteException {
        System.out.println("Invocation de la méthode getinformation()");
        return "Bonjour";
    }
}
