package tk.d4097.truetree.text;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SkipIterator implements Iterator<String> {
  private final Iterator<String> iterator;
  private String savedString;
  private boolean hasNxt;
  private boolean callToHasNextIsDone;

  public SkipIterator(Iterable<String> txt) {
    this.iterator = txt.iterator();
  }

  @Override
  public boolean hasNext() {
    if (callToHasNextIsDone) {
      return hasNxt;
    }

    callToHasNextIsDone = true;

    if (!iterator.hasNext()) {
      hasNxt = false;
      return false;
    }

    savedString = iterator.next();
    hasNxt = true;

    while (needToSkip(savedString)) {
      if (iterator.hasNext()) {
        savedString = iterator.next();
      } else {
        hasNxt = false;
        break;
      }
    }

    return hasNxt;
  }

  @Override
  public String next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No next item found by SkipIterator.");
    }

    callToHasNextIsDone = false;

    return savedString;
  }

  boolean needToSkip(String str) {
    String trimmed = str.trim();
    return trimmed.isEmpty() || trimmed.startsWith("# ");
  }
}
