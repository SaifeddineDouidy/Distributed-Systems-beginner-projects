import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientChatSocket {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1000);
            System.out.println("Connecté au serveur.");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Thread for receiving messages
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Connexion au serveur perdue.");
                }
            });
            receiveThread.start();

            // Thread for sending messages
            Thread sendThread = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    out.println(message);

                    if (message.equalsIgnoreCase("exit")) {
                        try {
                            socket.close();
                            System.exit(0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            sendThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}