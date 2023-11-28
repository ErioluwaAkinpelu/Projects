package cs3500.pa05.model;

import static cs3500.pa05.model.Category.NONE;

import java.util.ArrayList;
import java.util.List;

/**
 * The CategoryFilter class provides methods to filter events and tasks by category.
 * It allows filtering a list of events and tasks based on a selected category.
 */
public class CategoryFilter {

  private Category selectedCategory;

  /**
   * Gets the selected category.
   *
   * @return The selected category.
   */
  public Category getSelectedCategory() {
    return selectedCategory;
  }

  /**
   * Sets the selected category.
   *
   * @param selectedCategory The category to set as selected.
   */
  public void setSelectedCategory(Category selectedCategory) {
    this.selectedCategory = selectedCategory;
  }

  /**
   * @param essentialParts events or tasks
   * @param category category events or tasks belong to
   * @return list of essential parts
   */
  public List<EssentialPart> filterPartsByCategory(ArrayList<EssentialPart> essentialParts,
                                                   Category category) {
    List<EssentialPart> filteredParts = new ArrayList<>();
    for (EssentialPart ep : essentialParts) {
      if (category == NONE || ep.getCategory() == category) {
        filteredParts.add(ep);
      }
    }
    return filteredParts;
  }
}
