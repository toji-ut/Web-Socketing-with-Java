import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 4999);
            System.out.println("Connected to server.");

            // Create input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String response = "";
            Scanner scanner = new Scanner(System.in);

            while (!response.equals("exit")) {
                response = in.readLine();
                System.out.println("Server: " + response);

                if (response.contains("Would you like to know the time?")) {
                    System.out.print("Enter your response (yes/no/exit): ");
                    String request = scanner.nextLine();
                    out.println(request);
                }
            }

            // Close streams and socket
            in.close();
            out.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Host not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}

