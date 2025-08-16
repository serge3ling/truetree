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
      FileLst fileLst = new FileLst(inputStream, "tst-path");
      Lst lst = fileLst.lst();
      assert (lst.size() == 1) && lst.getById("rai").get("country").equals("it");
    }
  }
}