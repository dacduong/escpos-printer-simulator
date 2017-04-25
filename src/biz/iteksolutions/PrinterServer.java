package biz.iteksolutions;

import biz.iteksolutions.escpos.parser.*;

import java.io.*;
import java.net.Socket;

public class PrinterServer extends Thread {

    private IPrinterOutput display;
    private Socket socket;
    private int clientNumber;

    public PrinterServer(IPrinterOutput display, Socket socket, int clientNumber) {
        this.display = display;
        this.socket = socket;
        this.clientNumber = clientNumber;
        log("New connection with client# " + clientNumber + " at " + socket);
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), "GBK"));//GBK charset is for textChinese support
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send a welcome message to the client.
            //out.println("Hello, you are client #" + clientNumber + ".");
            //out.println("Enter a line with only a period to quit\n");

            // Get messages from the client, line by line; return them
            String sCurrentLine;
            while ((sCurrentLine = in.readLine()) != null) {
                //out.println(sCurrentLine);
                Printer po = new Printer();
                for (int i = 0; i < sCurrentLine.length(); i++) {
                    //System.out.println(i + ": " + input.charAt(i));
                    po.addChar(sCurrentLine.charAt(i));
                }

                //Explicitly add new line here because in.readLine ignore LF
                po.getCommands().add(new LineFeedCommand());

                //handle QR print - QR command content is 4th
                int qrCount = 0;
                for (Command cmd : po.getCommands()) {
                    if (cmd instanceof PrintQRCommand) {
                        qrCount++;
                        if (qrCount == 4) {
                            ((PrintQRCommand) cmd).setContentFlag(true);
                        }
                        if (qrCount == 5) {
                            //reset value
                            qrCount = 0;
                        }
                    }
                }

                if (po.getCommands().size() > 0) {
                    for (Command obj : po.getCommands()) {
                        if (obj instanceof IContentOutput) {
                            IContentOutput container = (IContentOutput) obj;
                            display.setText(container.getText());
                        }
                    }
                }
            }
        } catch (IOException e) {
            log("Error handling client# " + clientNumber + ": " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log("Couldn't close a socket, what's going on?");
            }
            log("Connection with client# " + clientNumber + " closed");
        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}
