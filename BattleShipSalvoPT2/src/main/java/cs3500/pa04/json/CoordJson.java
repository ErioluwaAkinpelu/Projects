package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A JSON representing a coordinate
 *
 * @param x the column position of the coordinate
 *
 * @param y the row position of the coordinate
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y
) {
}
