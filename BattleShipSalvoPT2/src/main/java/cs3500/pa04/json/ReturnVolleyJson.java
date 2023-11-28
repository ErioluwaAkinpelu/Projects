package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

/**
 * A List of JsonNode in which a volley is converted to so that the server can understand it
 *
 * @param jsonNodeList the list of JsonNode
 */
public record ReturnVolleyJson(
    @JsonProperty("coordinates") List<JsonNode> jsonNodeList
) {
}
