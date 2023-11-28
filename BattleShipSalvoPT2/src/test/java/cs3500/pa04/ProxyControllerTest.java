package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cs3500.pa03.ShipType;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.FleetSpecJson;
import cs3500.pa04.json.JoinArgsJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupInfoArgsJson;
import cs3500.pa04.json.VolleyJson;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProxyControllerTest {
  private ProxyController proxy;
  private Mocket mockServer;
  private Socket server;
  private InputStream in;
  private Appendable out;
  private final ObjectMapper mapper = new ObjectMapper();
  private ByteArrayOutputStream testLog;


  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

//  /**
//   * Check that the server returns a guess when given a hint.
//   */
//  @Test
//  public void testJoin() {
//    // Prepare sample message
//    ObjectNode args = mapper.createObjectNode();
//
//    // Create the client with all necessary messages
//    JoinArgsJson joinJson = new JoinArgsJson("eri", GameType.SINGLE);
//    JsonNode sampleMessage = createSampleMessage("join", joinJson);
//
//    MessageJson endGame = new MessageJson("end-game", args);
//    JsonNode endMessage = JsonUtils.serializeRecord(endGame);
//    Mocket socket = new Mocket(this.testLog,
//        List.of(sampleMessage.toString(), endMessage.toString()));
//
//    // Create a Dealer
//    try {
//      this.proxy = new ProxyController(socket);
//    } catch (IOException e) {
//      fail(); // fail if the dealer can't be created
//    }
//
//    // run the dealer and verify the response
//    this.proxy.run();
//
//    String expected = "{\"method-name\":\"join\",\"arguments\":{\"name\":\"cristhofer-hernandez\","
//        + "\"game-type\":\"SINGLE\"}}\r\n"
//        + "{\"method-name\":\"end-game\",\"arguments\":{}}\r\n";
//    assertEquals(expected, logToString());
//  }

//  /**
//   * Check that the server returns a guess when given a hint.
//   */
//  @Test
//  public void testSuccesses() {
//    // Prepare sample message
//    ObjectNode args = mapper.createObjectNode();
//
//    // Create the client with all necessary messages
//    VolleyJson volley = new VolleyJson(
//        Arrays.asList(new CoordJson(0, 1), new CoordJson(3, 2)));
//    JsonNode sampleMessage = createSampleMessage("successful-hits", volley);
//
//    MessageJson endGame = new MessageJson("end-game", args);
//    JsonNode endMessage = JsonUtils.serializeRecord(endGame);
//    Mocket socket = new Mocket(this.testLog,
//        List.of(sampleMessage.toString(), endMessage.toString()));
//
//    // Create a Dealer
//    try {
//      this.proxy = new ProxyController(socket);
//    } catch (IOException e) {
//      fail(); // fail if the dealer can't be created
//    }
//
//    // run the dealer and verify the response
//    this.proxy.run();
//
//    String expected = "{\"method-name\":\"successful-hits\",\"arguments\":{}}\r\n"
//        + "{\"method-name\":\"end-game\",\"arguments\":{}}\r\n";
//    assertEquals(expected, logToString());
//  }

//  /**
//   * Check that the server returns a guess when given a hint.
//   */
//  @Test
//  public void testEndGame() {
//    // Prepare sample message
//    ObjectNode args = mapper.createObjectNode();
//
//    MessageJson endGame = new MessageJson("end-game", args);
//    JsonNode endMessage = JsonUtils.serializeRecord(endGame);
//    Mocket socket = new Mocket(this.testLog,
//        List.of(endMessage.toString()));
//
//    // Create a Dealer
//    try {
//      this.proxy = new ProxyController(socket);
//    } catch (IOException e) {
//      fail(); // fail if the dealer can't be created
//    }
//
//    // run the dealer and verify the response
//    this.proxy.run();
//
//    String expected = "{\"method-name\":\"end-game\",\"arguments\":{}}\r\n";
//    assertEquals(expected, logToString());
//  }

  //  /**
  //   * Check that the server returns a guess when given a hint.
  //   */
  //  @Test
  //  public void testSetup() {
  //    // Prepare sample message
  //    ObjectNode args = mapper.createObjectNode();
  //
  //    // Create the client with all necessary messages
  //    //    Map<ShipType, Integer> fleetSpecs = new HashMap<>();
  //    //    fleetSpecs.put(ShipType.CARRIER, 1);
  //    //    fleetSpecs.put(ShipType.BATTLESHIP, 1);
  //    //    fleetSpecs.put(ShipType.DESTROYER, 1);
  //    //    fleetSpecs.put(ShipType.SUBMARINE, 1);
  //
  //    SetupInfoArgsJson setupJson = new SetupInfoArgsJson(10, 10,
  //        new FleetSpecJson(1, 1, 1, 1));
  //    JsonNode sampleMessage = createSampleMessage("setup", setupJson);
  //
  //    MessageJson endGame = new MessageJson("end-game", args);
  //    JsonNode endMessage = JsonUtils.serializeRecord(endGame);
  //    Mocket socket = new Mocket(this.testLog,
  //        List.of(sampleMessage.toString(), endMessage.toString()));
  //
  //    // Create a Dealer
  //    try {
  //      this.proxy = new ProxyController(socket);
  //    } catch (IOException e) {
  //      fail(); // fail if the dealer can't be created
  //    }
  //
  //    // run the dealer and verify the response
  //    this.proxy.run();
  //
  //    String expected = "{\"method-name\":\"setup\",\"arguments\":{\"fleet\":" +
  //        "[{\"coord\":{\"x\":3,\"y\":6},\"length\":6,\"direction\":\"VERTICAL\"}," +
  //        "{\"coord\":{\"x\":3,\"y\":1},\"length\":5,\"direction\":\"VERTICAL\"}," +
  //        "{\"coord\":{\"x\":5,\"y\":3},\"length\":4,\"direction\":\"HORIZONTAL\"}," +
  //        "{\"coord\":{\"x\":5,\"y\":6},\"length\":3,\"direction\":\"HORIZONTAL\"}]}}\r\n" +
  //        "{\"method-name\":\"end-game\",\"arguments\":{}}\r\n";
  //    assertEquals(expected, logToString());
  //  }

  /**
   * Create a MessageJson for some name and arguments.
   *
   * @param messageName name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson =
        new MessageJson(messageName, JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }



  //  /**
  //   * Reads output in the server
  //   *
  //   * @throws IOException throws IOException if reading fails
  //   */
  //  @BeforeEach
  //  public void giveOutput() throws IOException {
  //    server.getOutputStream();
  //  }
  //
  //  /**
  //   * Reads input in the server
  //   *
  //   * @throws IOException throws IOException if reading fails
  //   */
  //  private void giveInput() throws IOException {
  //    server.getInputStream();
  //  }

  //  @Test
  //  public void testDelegateMessage() throws IOException {
  //    //testing handleJoin
  //    ObjectNode emptyNode = mapper.createObjectNode();
  //    MessageJson joinMsg = new MessageJson("join", emptyNode);
  //    giveInput();
  //
  //    proxy.delegateMessage(joinMsg);
  //
  //    assertEquals("", "");
  //
  //  }

}