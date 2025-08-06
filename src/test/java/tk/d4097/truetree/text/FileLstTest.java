package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.keep.Lst;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FileLstTest {

  @Test
  void lst_whenResFileTst1_then1Rec() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    // Can be used like this as well:
    // InputStream inputStream = new FileInputStream("resources/dir-1/tst-1.yml")
    try (InputStream inputStream = classLoader.getResourceAsStream("tst-1.yml")) {
      FileLst fileLst = new FileLst(inputStream);
      Lst lst = fileLst.lst();
      assert (lst.size() == 1) && lst.getById("rai").get("country").equals("it");
    }
  }

  @Test
  void test_whenDir1ResAsStream_thenFiles() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    // Cannot be used like this:
    // InputStream inputStream = new FileInputStream("resources/dir-1")
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
    //URL resUrl = classLoader.getResource("resource/dir-1");
    URL resUrl = classLoader.getResource("dir-1");
    Path resPath = Paths.get(resUrl.toURI());
    List<File> files = Files.list(resPath).map(Path::toFile).toList();

    for (File file : files) {
      System.out.println(file);
    }
  }
}