package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;

public class LstParseBundle {
  private final Txt lines;
  private Rec rec = new Rec();
  private final Lst lst = new Lst();
  private boolean recClosed = true;
  private final LstStringAcceptors stringAcceptors;

  public LstParseBundle(Txt lines) {
    this(lines, new LstStringAcceptors());
  }

  public LstParseBundle(Txt lines, LstStringAcceptors stringAcceptors) {
    this.lines = lines;
    this.stringAcceptors = stringAcceptors;
  }

  public Txt lines() {
    return lines;
  }

  public Rec rec() {
    return rec;
  }

  public Lst lst() {
    return lst;
  }

  public void openNewRec() {
    recClosed = false;
  }

  public void closeRec() throws Exception {
    if (recClosed) {
      return;
    }

    recClosed = true;

    //if (rec.hasId()) {
      lst.addRec(rec);
    //}

    rec = new Rec();
  }

  public LstStringAcceptors getStringAcceptors() {
    return stringAcceptors;
  }
}
