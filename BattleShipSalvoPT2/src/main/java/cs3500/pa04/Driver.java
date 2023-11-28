package cs3500.pa04;

import cs3500.pa03.Controller;
import cs3500.pa03.ViewImpl;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Driver class where the program kicks off
 */
public class Driver {

  /**
   * This method connects to the server at the given host and port, builds a proxy referee
   * to handle communication with the server, and sets up a client player.
   *
   * @param server the server
   * @throws IOException if there is a communication issue with the server
   */

  private static void runClient(Socket server)
      throws IOException, IllegalStateException {
    ProxyController proxyController = new ProxyController(server);
    proxyController.run();
  }

  /**
   * The main entrypoint into the code as the Client. Given a host and port as parameters, the
   * client is run. If there is an issue with the client or connecting,
   * an error message will be printed.
   *
   * @param args The expected parameters are the server's host and port
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 2) {
      Socket server = new Socket(args[0], Integer.parseInt(args[1]));
      runClient(server);
    } else {
      ViewImpl view = new ViewImpl(System.out);
      InputStreamReader streamReader = new InputStreamReader(System.in);
      //      Scanner scanner = new Scanner(streamReader);
      Controller control = new Controller(view, streamReader);
      try {
        control.beforeGame();
        control.prepareGame();
        control.gameInPlay();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
