package tk.d4097.truetree;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class TreeWalkTest {
  @Test
  void walk_whenKnownFiles_thenListIsGood() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resUrl = classLoader.getResource("tree-walk-bare-test");
    Path resPath = Paths.get(resUrl.toURI());
    File topDir = resPath.toFile();
    Set<File> files = new TreeSet<>();
    TreeWalk treeWalk = new TreeWalk(topDir, File::isFile, files::add);
    treeWalk.walk();
    //System.out.println("test TreeWalkTest.walk_whenKnownFiles_thenListIsGood");
    //files.stream().map(File::getPath).forEach(System.out::println);
    assert files.size() == 4;
  }
}