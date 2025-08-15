package tk.d4097.truetree.keep;

public interface DataKeep {
  void addLst(Lst lst);
  boolean hasLst(Lst lst);
  Lst getLstByName(String name, String path);
}
