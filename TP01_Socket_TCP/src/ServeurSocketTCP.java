import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServeurSocketTCP {
    public static void main(String[] args) {
        try{

            ServerSocket serveur = new ServerSocket(1000);
            System.out.println("Attente de la connexion d'un client");

            Socket socket = serveur.accept();
            System.out.println("Un client a été connecté");

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            while(true){
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line =br.readLine();
                System.out.println(line);

                if(line.equals("exit")){
                    socket.close();
                    System.out.println("Fin de connection");
                    break;
                }

                PrintWriter sortie = new PrintWriter(os);
                Scanner scanner = new Scanner(System.in);

                String valeur =scanner.nextLine();
                sortie.println(valeur);
                sortie.flush();

                if(valeur.equals("exit")){
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
