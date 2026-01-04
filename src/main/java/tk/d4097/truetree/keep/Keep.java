package tk.d4097.truetree.keep;

import tk.d4097.truetree.Cfg;
import tk.d4097.truetree.TreeWalk;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Keep implements DataKeep {
  private final Cfg cfg;
  private final List<Lst> lsts = new ArrayList<>();
  private final List<Rec> dirProps = new ArrayList<>();

  public Keep(Cfg cfg) {
    this.cfg = cfg;
  }

  public void go() throws Exception {
    lsts.clear();
    dirProps.clear();
    Predicate<File> lstPredicate = new TreeWalkLstPredicate();
    Consumer<File> lstConsumer = new TreeWalkLstConsumer(this);
    TreeWalk lstTreeWalk = new TreeWalk(cfg.topDirFile(), lstPredicate, lstConsumer);
    lstTreeWalk.walk();

    Predicate<File> dirPropPredicate = new TreeWalkDirPropPredicate();
    Consumer<File> dirPropConsumer = new TreeWalkDirPropConsumer(this);
    TreeWalk dirPropTreeWalk
        = new TreeWalk(cfg.topDirFile(), dirPropPredicate, dirPropConsumer);
    dirPropTreeWalk.walk();
  }

  @Override
  public void addLst(Lst lst) {
    lsts.add(lst);
  }

  @Override
  public boolean hasLst(Lst lst) {
    return lsts.contains(lst);
  }

  @Override
  public Lst getLst(String path, String name) throws Exception {
    boolean found = false;
    Iterator<Lst> iterator = lsts.iterator();
    Lst against = new Lst(path, name);
    against.putProp(against.nameName(), name);
    Lst lstFound = against;

    while (iterator.hasNext()) {
      lstFound = iterator.next();

      if (lstFound.equals(against)) {
        found = true;
        break;
      }
    }

    if (!found) {
      throw new Exception("Not found a Lst with path \"" + path + "\" and name \"" + name + "\".");
    }

    return lstFound;
  }

  @Override
  public Collection<Lst> lsts() {
    return lsts;
  }

  public void addDirPropRec(Rec rec) {
    dirProps.add(rec);
  }

  public Iterator<Rec> dirPropRecIterator() {
    return dirProps.iterator();
  }
}
