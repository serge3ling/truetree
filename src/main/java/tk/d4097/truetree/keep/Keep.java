package tk.d4097.truetree.keep;

import tk.d4097.truetree.Cfg;
import tk.d4097.truetree.TreeWalk;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Keep implements DataKeep {
  private final Cfg cfg;
  private final List<Lst> lsts = new ArrayList<>();

  public Keep(Cfg cfg) {
    this.cfg = cfg;
  }

  public void go() throws Exception {
    Predicate<File> predicate = new TreeWalkPredicate();
    Consumer<File> consumer = new TreeWalkConsumer(this);
    TreeWalk treeWalk = new TreeWalk(new File(cfg.topDir()), predicate, consumer);
    treeWalk.walk();
  }

  @Override
  public void addLst(Lst lst) {
    lsts.add(lst);
  }

  @Override
  public boolean hasLst(Lst lst) {
    return lsts.contains(lst); // toDo: write the "equals" method on Lst with name and path
  }

  @Override
  public Lst getLstByName(String name, String path) {
    return null; // toDo: do
  }
}
