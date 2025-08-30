package tk.d4097.truetree.keep.ask;

import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;

public class AnswerRec {
  private final Rec rec;
  private final Lst lst;

  public AnswerRec(Rec rec, Lst lst) {
    this.rec = rec;
    this.lst = lst;
  }

  @Override
  public String toString() {
    try {
      return lst.toString() + "; " + rec.toString();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public Rec getRec() {
    return rec;
  }

  public Lst getLst() {
    return lst;
  }
}
