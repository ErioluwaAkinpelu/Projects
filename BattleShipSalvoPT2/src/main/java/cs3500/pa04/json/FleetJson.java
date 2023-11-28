package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Json file represent a fleet
 *
 * @param listOfShips the fleet
 */
public record FleetJson(
    @JsonProperty("fleet") List<ShipAdapterJson> listOfShips
) {

}
