package cs3500.pa03;

import java.util.ArrayList;

/**
 * represents the possible results of a game
 */
public class GameResult {

 enum result {
    WIN,
    LOSE,
    DRAW,

   INPLAY;
  }
  GameResult() {

  }

  /**
   * @param p1Ships the ships of the manual player
   * @param aiShips the ships of the AI player
   * @return whether the player has won lost, drew, or the game is still in play
   */
public result returnResult(ArrayList<Ship> p1Ships, ArrayList<Ship> aiShips) {
  boolean p1Lost = false;
  int p1Sunked = 0;
  boolean aiLost = false;
  int aiSunked = 0;
  for (int i = 0; i < p1Ships.size(); i++) {
    if (p1Ships.get(i).isSunk()) {
      p1Sunked += 1;
    }
    if (aiShips.get(i).isSunk()) {
      aiSunked += 1;
    }
  }
  if (p1Sunked == p1Ships.size() && aiSunked == aiShips.size()) {
    return result.DRAW;
  }
  else if(p1Sunked == p1Ships.size() && !(aiSunked == aiShips.size())){
    return result.LOSE;
  }
  else if(!(p1Sunked == p1Ships.size()) && (aiSunked == aiShips.size())) {
    return result.WIN;
  }
  else {
    return result.INPLAY;
  }
}
}
