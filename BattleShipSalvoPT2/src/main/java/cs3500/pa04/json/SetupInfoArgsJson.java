package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Json file that represents initial info of the board given by the server
 *
 * @param width the width of the board
 * @param height the height of the board
 * @param fleetSpecs the number of ships on the fleet and their respective types
 */
public record SetupInfoArgsJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") FleetSpecJson fleetSpecs
) {
}
