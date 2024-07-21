package lesson_5;



import io.restassured.mapper.ObjectMapper;
import lesson_5.requests.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

        try (ServerSocket server = new ServerSocket(8888)) {
            System.out.println("Server is active");
            while (true) {
                System.out.println("Waiting for users to connect...");
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client, clients);

                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Error occurred while enabling the server: " + e.getMessage());
        }
    }

    public static class ClientHandler implements Runnable {

        private final Socket client;
        private final Scanner in;
        private final PrintWriter out;
        private final Map<String, ClientHandler> clients;
        private String clientLogin;

        public ClientHandler(Socket clientSocket, Map<String, ClientHandler> clients) throws IOException {
            this.client = clientSocket;
            this.clients = clients;

            this.in = new Scanner(client.getInputStream());
            this.out = new PrintWriter(client.getOutputStream(), true);
        }


        @Override
        public void run() {
            System.out.println("New client request received");

            try {
                String loginRequest = in.nextLine();
                LoginRequest request = OBJECT_MAPPER.reader().readValue(loginRequest, LoginRequest.class);
                this.clientLogin = request.getLogin();
            } catch (IOException e) {
                System.err.println("Could not read login [" + clientLogin + "]: " + e.getMessage());
                close();
                return;
            }

            if (clients.containsKey(clientLogin)) {
                String unsuccessfulResponse = createLoginResponse(false);
                out.println(unsuccessfulResponse);
                close();
                return;
            }

            clients.put(clientLogin, this);
            String successfulResponse = createLoginResponse(true);
            out.println(successfulResponse);

            System.out.println("User [" + clientLogin + "] has connected");

            boolean flag = true;

            while(flag) {
                String msgFromClient = in.nextLine();
                final String type;

                try {
                    Request request = OBJECT_MAPPER.reader().readValue(msgFromClient, Request.class);

                    type = request.getType();
                } catch (IOException e) {
                    System.err.println("Could not read the message type: " + e.getMessage());
                    continue;
                }

                if (SendMessageRequest.TYPE.equals(type)) {
                    final SendMessageRequest msgRequest;
                    try {
                        msgRequest = OBJECT_MAPPER.reader().readValue(msgFromClient, SendMessageRequest.class);
                    } catch (IOException e) {
                        System.err.println("Could not read the '" + SendMessageRequest.TYPE + "' type: " + e.getMessage());
                        continue;
                    }

                    ClientHandler recipient = clients.get(msgRequest.getRecipient());
                    try {
                        recipient.sendPrivateMessage(msgRequest.getSender(), msgRequest.getMessage());
                    } catch (NullPointerException e) {
                        sendPrivateMessage("There is no user with such username on the server");
                    }
                } else if (BroadcastMessageRequest.TYPE.equals(type)) {
                    final BroadcastMessageRequest broadcastRequest;
                    try {
                        broadcastRequest = OBJECT_MAPPER.reader().readValue(msgFromClient, BroadcastMessageRequest.class);
                    } catch (IOException e) {
                        System.err.println("Could not read the '" + BroadcastMessageRequest.TYPE + "' type: " + e.getMessage());
                        continue;
                    }

                    broadcastMessage(broadcastRequest.getSender(), broadcastRequest.getMessage());

                } else if (UserListRequest.TYPE.equals(type)) {
                    sendPrivateMessage(clients.keySet().toString());
                } else if (DisconnectRequest.TYPE.equals(type)) {
                    clients.remove(clientLogin);
                    System.out.println("[" + clientLogin + "] has disconnected");
                    broadcastMessage("Server", clientLogin + " has disconnected from the server");
                    flag = false;
                } else {
                    System.err.println("Unknown message type: " + type);
                }
            }

            close();
        }

        public void sendPrivateMessage(String sender, String msg) {
            out.println(sender + ": " + msg);
        }

        public void sendPrivateMessage(String msg) {
            out.println(msg);
        }

        private void close() {
            try {
                in.close();
                out.close();
                client.close();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        private String createLoginResponse(boolean success) {
            LoginResponse response = new LoginResponse();
            response.setConnected(success);
            response.setError("This login has already been taken");
            try {
                return OBJECT_MAPPER.writer().writeValueAsString(response);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Could not create LoginResponse: " + e.getMessage());
            }
        }

        public void broadcastMessage(String sender, String msg) {
            for (ClientHandler client : clients.values()) {
                client.sendPrivateMessage(sender + ": " + msg);
            }
        }
    }
}
