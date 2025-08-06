package tk.d4097.truetree.text;

public class TxtToLst {
  private final LstParseBundle bundle;
  private boolean done;

  public TxtToLst(Txt txt) {
    this.bundle = new LstParseBundle(txt);
  }

  public LstParseBundle getBundle() throws Exception {
    go();
    return bundle;
  }

  public void go() throws Exception {
    if (done) {
      return;
    }

    done = true;

    LineParse lineParse = new LstRootLevelStep(bundle);

    while (lineParse.hasNext()) {
      lineParse = lineParse.next();
    }
  }
}
