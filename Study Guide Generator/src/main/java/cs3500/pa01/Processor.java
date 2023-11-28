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
  private ArrayList<String> lines = new ArrayList<>();
  private ArrayList<Question> questions = new ArrayList<>();

  public ArrayList<String> filter(ArrayList<Path> sortedList) throws IOException{


    for (Path path : sortedList) {

        Scanner scan = new Scanner(path.toFile());

        while (scan.hasNextLine()) {
          String currentLine = scan.nextLine();

          // Important info filtering
          if (currentLine.contains("[[") && currentLine.contains("]]")) {
            int start = currentLine.indexOf("[[");
            int end = currentLine.indexOf("]]");

            String content = currentLine.substring(start + 2, end);
            lines.add(content);

          } else if (currentLine.startsWith("#")) {
            lines.add(currentLine);
          }

          else if (currentLine.contains("[[") && !currentLine.contains("]]")) {
            int bracket = currentLine.indexOf("[[");

            String Important = currentLine.substring(bracket + 2, currentLine.length());
            lines.add(Important);

            while (scan.hasNextLine()) {
              String nextLine = scan.nextLine();
              if (nextLine.contains("]]") && !nextLine.contains("[[")) {
                lines.add(nextLine.substring(0, nextLine.indexOf("]]")));

//              } else if (nextLine.contains("]]") && nextLine.contains("[[")) {
//                lines.add(nextLine.substring(nextLine.indexOf("[[") + 2, nextLine.indexOf("]]")));
//
//              } else {
                continue;
              }
            }

          }


        }


    }

    return lines;
  }
  public ArrayList<Question> filterQ(Path questionBank) throws IOException {



      Scanner scan = new Scanner(questionBank.toFile());

      while (scan.hasNextLine()) {

          String ask = "";
          String Ans = "";
          Question.Diff diff = Question.Diff.HARD;
          String currentQ = scan.nextLine();
          if (currentQ.startsWith("Question")) {
            int askStart = currentQ.indexOf(":");
            ask = currentQ.substring(askStart + 1, currentQ.length() );
          }
          String currentA = scan.nextLine();
          if (currentA.startsWith("Answer")) {
            int AnsStart = currentA.indexOf(":");
             Ans = currentA.substring(AnsStart + 1, currentA.length() );
          }
        String currentD = scan.nextLine();
          if (currentD.startsWith("Hard")) {
            int AnsStart = currentD.indexOf(":");
             diff = Question.Diff.HARD;
          }

          else if (currentD.startsWith("Easy")) {
            int AnsStart = currentD.indexOf(":");
            diff = Question.Diff.EASY;

          }
          Question q = new Question(ask, Ans, diff);
          questions.add(q);
        }


   return questions;
  }



  public ArrayList<Question> getQ() {
    return questions;
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
