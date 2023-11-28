package cs3500.pa01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * filters md files for important content and headers and makes studyguide file
 */
public class Processor {
  ArrayList<String> lines = new ArrayList<>();


  public ArrayList<String> filter(ArrayList<Path> sortedList) {


    for (Path path : sortedList) {
      try {
        Scanner scan = new Scanner(path.toFile());

        while (scan.hasNextLine()) {
          String currentLine = scan.nextLine();

          // takes important info when it has brackets
          if (currentLine.contains("[[") && currentLine.contains("]]")) {
            int start = currentLine.indexOf("[[");
            int end = currentLine.indexOf("]]");

            String content = currentLine.substring(start + 2, end);
            lines.add(content);

            // takes only the heading if there is a hashtag
          } else if (currentLine.startsWith("#")) {
            lines.add(currentLine);
            //System.out.println(Heading);
          } else if (currentLine.contains("[[") && !currentLine.contains("]]")) {
            int bracket = currentLine.indexOf("[[");

            String Important = currentLine.substring(bracket + 2, currentLine.length());
            lines.add(Important);

            while (scan.hasNextLine()) {
              String nextLine = scan.nextLine();
              if (nextLine.contains("]]") && !nextLine.contains("[[")) {
                lines.add(nextLine.substring(0, nextLine.indexOf("]]")));

              }
              else if (nextLine.contains("]]") && nextLine.contains("[[")) {
                lines.add(nextLine.substring(nextLine.indexOf("[[") + 2, nextLine.indexOf("]]")));

              } else {
                continue;
              }
            }
          }
        }

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }


    }

    return lines;
  }


  public File fileWriter(ArrayList<String> content,  File endFile) throws IOException {


    FileWriter writer = null;

      writer = new FileWriter(endFile);


    for(int l = 0; l < content.size(); l++) {
  String line = content.get(l);
if(line.contains("#")) {
    writer.write(line + "\n");

} else {

    writer.write("- " + line);

}


}
    writer.close();
  return endFile;
  }

}
