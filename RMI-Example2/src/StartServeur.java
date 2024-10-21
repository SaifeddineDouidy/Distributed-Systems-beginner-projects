import java.net.*;
import java.net.UnknownHostException;
import java.rmi.*;
import java.rmi.registry.*;


@SuppressWarnings("deprecation")
public class StartServeur {

    public static void main(String[] args) {
        try{
            LocateRegistry LocaleRegistry = null;

            LocateRegistry.createRegistry(1099);

//            if(System.getSecurityManager() == null){
//                System.setSecurityManager(new RMISecurityManager());
//            }

            LabServiceImpl informationImpl = new LabServiceImpl();

            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/RMI";
            System.out.println("Enregistrement de l'objet avec l'url : "+url);
            Naming.rebind(url, informationImpl);
            System.out.println("Serveur Lancée");

        }catch (RemoteException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}