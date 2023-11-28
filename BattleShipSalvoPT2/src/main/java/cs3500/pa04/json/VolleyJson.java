package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A Json file representing the list of shots
 *
 * @param shots the list of CoordJson for shots
 */
public record VolleyJson(
    @JsonProperty("coordinates") List<CoordJson> shots
){
}
