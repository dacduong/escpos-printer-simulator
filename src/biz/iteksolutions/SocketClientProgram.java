package biz.iteksolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientProgram {

    public static void main(String[] args) throws IOException {

        String hostName = "192.168.1.7";
        int portNumber = 9100;

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in))
        ) {
            char[] arr = new char[] { 0x10, 0x05, 0x01};
            out.write(arr);
            out.flush();

            String resp;
            while ((resp = in.readLine()) != null) {
                System.out.println(resp);
            }

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                //System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }

}
