import java.net.UnknownHostException;
import java.rmi.*;
import java.net.*;
public class StartClient {
    public static void main(String[] args) {
        System.out.println("Lancement du client");
        try {
            Remote r = Naming.lookup("rmi://127.0.0.1/RMI");
            System.out.println(r);
            String s = ((LabService) r).getInformation();
            System.out.println("Chaine renvoyée = " + s);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
