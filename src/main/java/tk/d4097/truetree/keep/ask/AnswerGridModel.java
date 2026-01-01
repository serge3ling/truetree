package tk.d4097.truetree.keep.ask;

import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;

import java.util.*;

public class AnswerGridModel {
  private final Answer answer;
  private final Set<String> colNames = new HashSet<>();
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

  public Set<String> getColNames() throws Exception {
    make();
    return colNames;
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

    array = new String[rowCnt][colNames.size()];

    Iterator<AnswerRec> step2Iterator = answer.iterator();
    int rowIndex = 0;

    while (step2Iterator.hasNext()) {
      AnswerRec answerRec = step2Iterator.next();
      //Lst lst = answerRec.getLst();
      Rec rec = answerRec.getRec();
      int colIndex = 0;

      for (String colName : colNames) {
        array[rowIndex][colIndex] = rec.hasField(colName) ? rec.get(colName) : "";
        colIndex++;
      }

      rowIndex++;
    }
  }
}
