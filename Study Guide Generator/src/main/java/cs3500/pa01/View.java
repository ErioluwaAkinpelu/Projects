package cs3500.pa01;

import java.io.IOException;
import java.util.Scanner;


/**
 * View interface for displaying things to User
 */
public interface View {
  void write(String phrase) throws IOException;

}
