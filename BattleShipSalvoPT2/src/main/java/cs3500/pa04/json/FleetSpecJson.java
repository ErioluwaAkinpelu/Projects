package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Json file representing the number of each type of ship
 *
 * @param carrier the number of carrier ships
 * @param battleship the number of battleships
 * @param destroyer the number of destroyer ships
 * @param submarine the number of submarines
 */
public record FleetSpecJson(
    @JsonProperty("CARRIER") int carrier,
    @JsonProperty("BATTLESHIP") int battleship,
    @JsonProperty("DESTROYER") int destroyer,
    @JsonProperty("SUBMARINE") int submarine
) {
}
