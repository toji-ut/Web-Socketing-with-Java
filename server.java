import java.io.*;
import java.net.*;
import java.util.Date;

public class server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4999);
            System.out.println("Server started. Waiting for clients...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Create input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String response = "yes";
            while (!response.equals("exit")) {
                out.println("Would you like to know the time? (yes/no)");
                response = in.readLine();

                if (response.equalsIgnoreCase("yes")) {
                    // Get current time
                    String time = new Date().toString();
                    out.println("Current time: " + time);
                } else if (response.equalsIgnoreCase("no")) {
                    out.println("Sure! A no is a no. Have a good day!");
                    break;
                } else {
                    out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            }

            // Close streams and socket
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

