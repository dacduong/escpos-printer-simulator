package biz.iteksolutions;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

public class Program {

    /**
     *
     * @param args
     * args[0] - port - require if use file in args[1]
     * args[1] - optional - file path - output to file only
     * args[2] - optional - file size KB
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int clientNumber = 0;
        int port = 9100;
        String path = "";
        int size = 200;
        IPrinterOutput output = null;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        ServerSocket listener = new ServerSocket(port);

        if (args.length > 1) {
            path = args[1];
        }
        if (args.length > 2) {
            size = Integer.parseInt(args[2]);
        }

        if (path.isEmpty()) {
            PrinterOutputImpl pPanel = new PrinterOutputImpl();
            JFrame frame = new JFrame("ESC/POS Printer Simulator - Port " + port);
            frame.setContentPane(pPanel);
            frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
            frame.pack();
            frame.setVisible(true);
            output = pPanel;
        } else {
            output = new FileOutputImpl(path, size);
        }

        try {
            while (true) {
                PrinterServer printerServer = new PrinterServer(output, listener.accept(), clientNumber++);
                printerServer.start();
            }
        } finally {
            listener.close();
        }
    }
}
