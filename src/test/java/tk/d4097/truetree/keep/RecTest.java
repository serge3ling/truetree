package tk.d4097.truetree.keep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecTest {
  @Test
  void putAndHas_whenSimpleKeyValue_thenIsPutAndHas() {
    Rec rec = new Rec();
    String key1 = "key1";
    String val1 = "val1";
    rec.put(key1, val1);
    boolean has = rec.hasField(key1);
    assert has : "Key was put but is not there";
  }

  @Test
  void putAndGet_whenSimpleKeyValue_thenIsPutAndGot() throws Exception {
    Rec rec = new Rec();
    String key1 = "key1";
    String val1 = "val1";
    rec.put(key1, val1);
    Object got = rec.get(key1);
    assert got.equals(val1) : "Value put is other than value got";
  }

  @Test
  void putAndGet_whenNotThere_thenException() {
    Rec rec = new Rec();
    String key1 = "key1";
    rec.put(key1, "val1");
    String key2 = "key2";
    assertThrowsExactly(Exception.class, () -> rec.get(key2), "Getting a key that is not there must raise an exception");
    /*try {
      Object val2 = rec.get(key2);
      assert false : "Getting a key that is not there must raise an exception";
    } catch (Exception ex) {
    }*/
  }

  @Test
  void noId_whenThereIs_thenFalse() {
    Rec rec = new Rec();
    rec.put(rec.idName(), "id-value");
    assert !rec.noId();
  }

  @Test
  void noId_whenNotThere_thenTrue() {
    Rec rec = new Rec();
    assert rec.noId();
  }

  @Test
  void id_whenThereIs_thenTheId() throws Exception {
    Rec rec = new Rec();
    String idValue = "id-value";
    rec.put(rec.idName(), idValue);
    assert rec.id().equals(idValue);
  }
}