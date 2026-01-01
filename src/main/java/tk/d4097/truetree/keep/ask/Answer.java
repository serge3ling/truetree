package tk.d4097.truetree.keep.ask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Answer {
  private final List<AnswerRec> answerRecs = new ArrayList<>();

  public void add(AnswerRec answerRec) {
    answerRecs.add(answerRec);
  }

  public Iterator<AnswerRec> iterator() {
    return answerRecs.iterator();
  }

  public void prn() {
    System.out.println("Answer:");

    for (AnswerRec answerRec : answerRecs) {
      System.out.println(answerRec);
    }
  }
}
