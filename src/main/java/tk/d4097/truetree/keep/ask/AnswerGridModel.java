package tk.d4097.truetree.keep.ask;

import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;

import java.util.*;

public class AnswerGridModel {
  private final Answer answer;
  private final Set<String> colNames = new HashSet<>();
  private final String lstPathColName = "List Path";
  private final String lstNameColName = "List Name";
  private final List<String> extraColNames = List.of(lstPathColName, lstNameColName);
  private final List<String> allColNames = new ArrayList<>();
  private boolean made;
  private int rowCnt;
  private String[][] array = new String[0][0];

  public AnswerGridModel(Answer answer) {
    this.answer = answer;
  }

  public String[][] getArray() throws Exception {
    make();
    return array;
  }

  public Collection<String> getColNames() throws Exception {
    make();
    return allColNames;
  }

  private void make() throws Exception {
    if (made) {
      return;
    }

    made = true;

    Iterator<AnswerRec> step1Iterator = answer.iterator();

    while (step1Iterator.hasNext()) {
      rowCnt++;
      AnswerRec answerRec = step1Iterator.next();
      //Lst lst = answerRec.getLst();
      Rec rec = answerRec.getRec();
      Set<String> fieldNames = rec.fieldNames();
      colNames.addAll(fieldNames);
    }

    array = new String[rowCnt][colNames.size() + extraColNames.size()];

    Iterator<AnswerRec> step2Iterator = answer.iterator();
    int rowIndex = 0;

    while (step2Iterator.hasNext()) {
      AnswerRec answerRec = step2Iterator.next();
      Lst lst = answerRec.getLst();
      String lstPath = lst.getPath();
      String lstName = lst.hasName() ? lst.name() : "";
      Rec rec = answerRec.getRec();
      int colIndex = 0;

      for (String colName : colNames) {
        array[rowIndex][colIndex] = rec.hasField(colName) ? rec.get(colName) : "";
        colIndex++;
      }

      //array[rowIndex][colNames.size()] = lst.getPath();
      //array[rowIndex][colNames.size() + 1] = lst.name();
      int rowLength = array[rowIndex].length;
      array[rowIndex][rowLength - 2] = lstPath;
      array[rowIndex][rowLength - 1] = lstName;
      rowIndex++;
    }

    allColNames.addAll(colNames);
    allColNames.addAll(extraColNames);
  }
}
