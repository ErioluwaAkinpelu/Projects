package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.GameType;

/**
 * A json file that represents the player's name and whether they are single or in a team
 *
 * @param name the name of the player
 * @param gameType team or single
 */
public record JoinArgsJson(
    @JsonProperty("name") String name,
    @JsonProperty("game-type")GameType gameType) {

}
