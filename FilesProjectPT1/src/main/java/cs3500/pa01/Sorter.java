package cs3500.pa01;
import java.nio.file.Path;
import java.util.ArrayList;

public class Sorter {
  ArrayList<Path> unsortedFiles;
  String Flag;

  Sorter(ArrayList<Path> unsortedFiles, String Flag) {
    this.unsortedFiles = unsortedFiles;
    this.Flag = Flag;
  }

  public ArrayList<Path> sortbythis() {
    sortByName SBN = new sortByName();
    sortByModi SBM = new sortByModi();
    SortByCreation SBC = new SortByCreation();
    if (Flag.equals("filename")) {
      unsortedFiles.sort(SBN);
      return unsortedFiles;
    } else if (Flag.equals("modified")) {
      unsortedFiles.sort(SBM);
      return unsortedFiles;
    } else if (Flag.equals("created")) {
      unsortedFiles.sort(SBC);
      return unsortedFiles;
    } else {
      throw new IllegalArgumentException("You cannot sort this way");
    }
  }
}




