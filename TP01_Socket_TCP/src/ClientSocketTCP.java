import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSocketTCP{
    public static void main(String[] args) {
        try{
            InetAddress address = InetAddress.getByName("localhost");
            Socket socket = new Socket(address, 1000);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            while(true){
                System.out.println("Message : ");
                Scanner scanner = new Scanner(System.in);

                String valeur =scanner.nextLine();
                PrintWriter sortie = new PrintWriter(os);
                sortie.println(valeur);
                sortie.flush();

                if(valeur.equals("exit")){
                    socket.close();
                    System.out.println("Fin de connection");
                    break;
                }

                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line =br.readLine();
                System.out.println(line);

                if(line.equals("exit")){
                    socket.close();
                    System.out.println("Fin de connection");
                    break;
                }
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
