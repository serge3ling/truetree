package tk.d4097.truetree;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class AnyTest {
  @Test
  void iteratorNextWhereNoNext() {
    java.util.List<String> emptyList = new java.util.ArrayList<>();
    Iterator<String> iterator = emptyList.iterator();
    try {
      iterator.next();
    } catch (Exception e) {
      System.out.println("iterator.next where no next, exception message: " + e.getLocalizedMessage());
    }
  }

  @Test
  void test_whenDir1ResAsStream_thenFiles() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    // Cannot be used like this: InputStream inputStream = new FileInputStream("resources/dir-1")
    try (InputStream inputStream = classLoader.getResourceAsStream("dir-1")) {
      assert inputStream != null;
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String s = reader.readLine();

      while (s != null) {
        System.out.println(s);
        s = reader.readLine();
      }
    }
  }

  @Test
  void test_whenDir1Res_thenFiles() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resUrl = classLoader.getResource("dir-1");
    Path resPath = Paths.get(resUrl.toURI());
    Files.list(resPath).map(Path::toFile).forEach(System.out::println);

    File topDir = resPath.toFile();
    File[] files = topDir.listFiles();

    for (File file : files) {
      System.out.println(file);
    }
  }

  @Test
  void test_whenDir1_thenFiles() throws Exception {
    File topDir = new File("");//("src/test/resources/dir-1");
    System.out.println("dir: " + topDir.getAbsolutePath());
    File[] files = topDir.listFiles();
    if (files == null) {
      System.out.println("\"items\" is null.");
      return;
    }

    for (File file : files) {
      System.out.println(file);
    }
  }
}
