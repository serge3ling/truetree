package tk.d4097.truetree.keep;

public interface DataKeep {
  void addLst(Lst lst);
  boolean hasLst(Lst lst);
  Lst getLst(String path, String name) throws Exception;
  Iterable<Lst> lsts();
}
