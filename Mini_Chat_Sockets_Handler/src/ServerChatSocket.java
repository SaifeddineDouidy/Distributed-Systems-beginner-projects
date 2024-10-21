import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerChatSocket {
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serveur = new ServerSocket(1000);
            System.out.println("Serveur en attente de connexions...");

            // Thread for server input to broadcast messages to all clients
            Thread serverInputThread = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    broadcastMessage("Server: " + message, null);
                }
            });
            serverInputThread.start();

            while (true) {
                Socket socket = serveur.accept();
                System.out.println("Un client a été connecté !");

                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Broadcast message to all connected clients except the sender
    public static void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            // Skip broadcasting to the sender
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("User" + clientSocket.getPort()+ " :" + message );
                    // Broadcast the message to all other clients and not the one that sent it
                    broadcastMessage("User " + clientSocket.getPort() + ": " + message, this);

                    if (message.equalsIgnoreCase("exit")) {
                        broadcastMessage("User " + clientSocket.getPort() + " a quitté la conversation ", this);
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Client déconnecté");
            } finally {
                try {
                    clients.remove(this);
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}