package tk.d4097.truetree.text;

public interface LineParse {
  boolean hasNext();
  LineParse next() throws Exception;
}
