package cs3500.pa05.model;

import javafx.scene.layout.HBox;

/**
 * represents display of essential parts
 */
public class EssentialPartDisplay extends HBox {

  private final EssentialPart ep;

  /**
   * @param ep essential part to be displayed
   */
  public EssentialPartDisplay(EssentialPart ep) {
    this.ep = ep;
  }

  /**
   * @return essential part
   */
  public EssentialPart getEssentialPart() {
    return this.ep;
  }

}
