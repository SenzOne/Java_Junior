package lesson_5;


import io.restassured.mapper.ObjectMapper;
import lesson_5.requests.BroadcastMessageRequest;
import lesson_5.requests.DisconnectRequest;
import lesson_5.requests.SendMessageRequest;
import lesson_5.requests.UserListRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChatClient {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String clientLogin = console.nextLine();

        try (Socket server = new Socket("localhost", 8888)) {

            try (PrintWriter out = new PrintWriter(server.getOutputStream(), true)) {
                Scanner in = new Scanner(server.getInputStream());

                String loginRequest = createLoginRequest(clientLogin);
                out.println(loginRequest);

                String loginResponse = in.nextLine();

                if(!checkLoginResponse(loginResponse)) {
                    System.out.println("Could not connect to the server");
                    return;
                }

                System.out.println("Connected successfully");


                new Thread(() -> {
                    boolean active = true;
                    while (active) {
                        try {
                            String msgFromServer = in.nextLine();
                            System.out.println(msgFromServer);
                        } catch (NoSuchElementException e) {
                            active = false;
                            System.out.println("Server issues occurred");
                        }
                    }
                }).start();

                boolean connected = true;
                while (connected) {
                    String msg = console.nextLine();

                    /*
                      To send private message for particular @recipient type @login message
                     */
                    if (msg.contains("@")) {
                        SendMessageRequest sendMessageRequest = new SendMessageRequest();

                        String recipient = msg.substring(1, msg.indexOf(' '));

                        sendMessageRequest.setMessage(msg.substring(msg.indexOf(' ') + 1));

                        sendMessageRequest.setSender(clientLogin);

                        sendMessageRequest.setRecipient(recipient);

                        String msgRequest = OBJECT_MAPPER.writer().writeValueAsString(sendMessageRequest);

                        out.println(msgRequest);

                        /*
                        type /users in the chat to see the list of all the users on the server
                         */
                    } else if (msg.equals("/users")) {
                        UserListRequest userListRequest = new UserListRequest();

                        String listRequest = OBJECT_MAPPER.writer().writeValueAsString(userListRequest);

                        out.println(listRequest);

                        /*
                        type /exit in the chat to disconnect from the server
                         */
                    } else if (msg.equals("/exit")) {
                        connected = false;

                        DisconnectRequest disconnectRequest = new DisconnectRequest();

                        String endSession = OBJECT_MAPPER.writer().writeValueAsString(disconnectRequest);

                        out.println(endSession);
                    } else {
                        BroadcastMessageRequest broadcastMessageRequest = new BroadcastMessageRequest();
                        broadcastMessageRequest.setSender(clientLogin);
                        broadcastMessageRequest.setMessage(msg);

                        String broadcastRequest = OBJECT_MAPPER.writer().writeValueAsString(broadcastMessageRequest);

                        out.println(broadcastRequest);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error while connecting to te server: " + e.getMessage());
        }

        System.out.println("Disconnected from the server");
    }

    private static boolean checkLoginResponse(String loginResponse) {
        try {
            LoginResponse response = OBJECT_MAPPER.reader().readValue(loginResponse, LoginResponse.class);
            if (!response.isConnected()) System.out.println(response.getError());
            return response.isConnected();
        } catch (IOException e) {
            System.err.println("JSON reading error: " + e.getMessage());
            return false;
        }
    }

    private static String createLoginRequest(String clientLogin) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(clientLogin);

        try {
            return OBJECT_MAPPER.writer().writeValueAsString(loginRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON error: " + e.getMessage());
        }
    }
}