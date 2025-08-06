package tk.d4097.truetree.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface Txt {
  boolean hasNext();
  String next() throws Exception;
  void backAtBeforeThisLine() throws Exception;

  class Stub implements Txt {
    private final List<String> lines;
    private final Iterator<String> iterator;
    private boolean stayingBack;
    private String savedString;
    private boolean neverRead = true;

    public Stub(List<String> lines) {
      this.lines = lines;
      this.iterator = new SkipIterator(this.lines);
    }

    @Override
    public boolean hasNext() {
      return stayingBack || iterator.hasNext();
    }

    @Override
    public String next() throws Exception {
      if (neverRead) {
        neverRead = false;
      }

      if (stayingBack) {
        stayingBack = false;
      } else {
        savedString = iterator.next();
      }

      return savedString;
    }

    @Override
    public void backAtBeforeThisLine() throws Exception {
      if (neverRead) {
        throw new Exception("Cannot get back since no line was ever read.");
      }

      stayingBack = true;
    }
  }
}
