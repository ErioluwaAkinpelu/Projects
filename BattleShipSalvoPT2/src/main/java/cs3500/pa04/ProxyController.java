package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cs3500.pa03.AiPlayer;
import cs3500.pa03.Coord;
import cs3500.pa03.Ship;
import cs3500.pa03.ShipType;
import cs3500.pa03.ViewImpl;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JoinArgsJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.ReturnVolleyJson;
import cs3500.pa04.json.SetupInfoArgsJson;
import cs3500.pa04.json.ShipAdapterJson;
import cs3500.pa04.json.VolleyJson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class uses the Proxy Pattern to talk to the Server and dispatch methods to the Player.
 */
public class ProxyController {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private AiPlayer player;
  private final ObjectMapper mapper = new ObjectMapper();

  //  private static final JsonNode VOID_RESPONSE =
  //      new ObjectMapper().getNodeFactory().textNode("void");

  /**
   * Construct an instance of a ProxyPlayer.
   *
   * @param server the socket connection to the server
   * @throws IOException if
   */
  public ProxyController(Socket server) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
  }


  /**
   * Listens for messages from the server as JSON in the format of a MessageJSON. When a complete
   * message is sent by the server, the message is parsed and then delegated to the corresponding
   * helper method for each message. This method stops when the connection to the server is closed
   * or an IOException is thrown from parsing malformed JSON.
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
      //REMOVE
      e.printStackTrace();
      System.out.println("Connection to the server was lost!");
    }
  }


  /**
   * Determines the type of request the server has sent ("guess" or "win") and delegates to the
   * corresponding helper method with the message arguments.
   *
   * @param message the MessageJSON used to determine what the server has sent
   */
  public void delegateMessage(MessageJson message) throws IOException {
    String name = message.methodName();
    JsonNode arguments = message.arguments();
    System.out.println("I was here");
    if ("join".equals(name)) {
      handleJoin();
    } else if ("setup".equals(name)) {
      handleSetup(arguments);
    } else if ("take-shots".equals(name)) {
      handleTakeShots();
    } else if ("report-damage".equals(name)) {
      handleReportDamage(arguments);
    } else if ("successful-hits".equals(name)) {
      handleSuccesses();
    } else if ("end-game".equals(name)) {
      handleEndGame();
    } else {
      throw new IllegalStateException("Invalid message name");
    }
  }

  /**
   * Method that handles an end game request
   */
  private void handleEndGame() {
    //    System.out.println("end game was here");
    ObjectNode emptyNode = mapper.createObjectNode();
    MessageJson messageJson = new MessageJson("end-game", emptyNode);
    JsonNode jsonResponse = JsonUtils.serializeRecord(messageJson);
    //    System.out.println(jsonResponse);
    this.out.println(jsonResponse);

    //close the server
    closeServer();
  }

  /**
   * Method that handles a successful shots request
   */
  private void handleSuccesses() {
    System.out.println("successes was here");
    ObjectNode emptyNode = mapper.createObjectNode();
    MessageJson messageJson = new MessageJson("successful-hits", emptyNode);
    JsonNode jsonResponse = JsonUtils.serializeRecord(messageJson);
    this.out.println(jsonResponse);
  }

  /**
   * Method that handles a report damage request
   *
   * @param arguments the arguments given by the MessageJson
   */
  private void handleReportDamage(JsonNode arguments) {
    System.out.println("report damage was here");
    //initialize variables
    List<Coord> coordList = new ArrayList<>();
    List<JsonNode> coordJsons = new ArrayList<>();
    List<Coord> damaged;
    //convert
    VolleyJson damageArgs = this.mapper.convertValue(arguments, VolleyJson.class);

    //get all the coordinates
    for (int i = 0; i < damageArgs.shots().size(); i++) {
      Coord coordinate = new Coord(damageArgs.shots().get(i).x(), damageArgs.shots().get(i).y());
      coordList.add(coordinate);
    }
    //get the shots that landed
    damaged = this.player.reportDamage(coordList);

    //add all the coordinates to a list of Json
    for (int i = 0; i < damaged.size(); i++) {
      CoordJson coordinate = new CoordJson(damaged.get(i).getX(), damaged.get(i).getY());
      JsonNode jsonNode = JsonUtils.serializeRecord(coordinate);
      coordJsons.add(jsonNode);
    }

    JsonNode response = JsonUtils.serializeRecord(new ReturnVolleyJson(coordJsons));

    JsonNode jsonResponse = JsonUtils.serializeRecord(
        new MessageJson("report-damage", response));
    this.out.println(jsonResponse);
  }

  /**
   * Method that handles a take shots request
   */
  private void handleTakeShots() {
    System.out.println("take shots was here");
    //initialize variables
    List<JsonNode> coordJsonList = new ArrayList<>();
    List<Coord> shotsList = this.player.takeShots();
    for (int i = 0; i < shotsList.size(); i++) {
      CoordJson coordinate
          = new CoordJson(shotsList.get(i).getX(),
          shotsList.get(i).getY());
      JsonNode node = JsonUtils.serializeRecord(coordinate);
      coordJsonList.add(node);
    }

    JsonNode response = JsonUtils.serializeRecord(new ReturnVolleyJson(coordJsonList));
    MessageJson response2 = new MessageJson("take-shots", response);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response2);
    this.out.println(jsonResponse);
  }

  /**
   * Method that handles a join request
   */
  private void handleJoin() {
    System.out.println("join was here");
    JsonNode jsonResponse =
        JsonUtils.serializeRecord(new JoinArgsJson("cristhofer-hernandez", GameType.SINGLE));
    MessageJson response = new MessageJson("join", jsonResponse);

    JsonNode response2 = JsonUtils.serializeRecord(response);
    this.out.println(response2);
  }

  /**
   * Method that handles a setup request
   *
   * @param arguments the arguments given by the MessageJson
   */
  private void handleSetup(JsonNode arguments) throws IOException {
    System.out.println("setup was here");
    SetupInfoArgsJson setupArgs = this.mapper.convertValue(arguments, SetupInfoArgsJson.class);

    //initialize temporary variables
    Map<ShipType, Integer> fleetMap = new HashMap<>();

    //get the fleet sizes given
    int carrier = setupArgs.fleetSpecs().carrier();
    fleetMap.put(ShipType.CARRIER, carrier);
    int battleship = setupArgs.fleetSpecs().battleship();
    fleetMap.put(ShipType.BATTLESHIP, battleship);
    int destroyer = setupArgs.fleetSpecs().destroyer();
    fleetMap.put(ShipType.DESTROYER, destroyer);
    int submarine = setupArgs.fleetSpecs().submarine();
    fleetMap.put(ShipType.SUBMARINE, submarine);

    //get the width and height
    int width = setupArgs.width();
    int height = setupArgs.height();
    List<Ship> shipList;
    List<ShipAdapterJson> shipAdapterList = new ArrayList<>();

    //create and setup the player
    this.player = new AiPlayer(height, width);
    this.player.setup(height, width, fleetMap);
    shipList = this.player.returnShips();
    //create a list of ShipAdapters

    for (int i = 0; i < shipList.size(); i++) {
      ShipAdapterJson ship = new ShipAdapterJson(shipList.get(i));
      shipAdapterList.add(ship);
    }

    //Serializing
    JsonNode response = JsonUtils.serializeRecord(new FleetJson(shipAdapterList));
    MessageJson response2 = new MessageJson("setup", response);
    JsonNode jsonResponse = JsonUtils.serializeRecord(response2);
    //    new ViewImpl(System.out).drawBoard(height, width, this.player.makeBoard());
    this.out.println(jsonResponse);

  }

  /**
   * Method that closes the server
   */
  private void closeServer() {
    System.out.println("close server was here");
    try {
      this.server.close();
    } catch (IOException e) {
      System.out.println("The server has shut down!");
    }
  }
}
