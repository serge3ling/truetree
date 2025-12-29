package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Keep;
import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;
import tk.d4097.truetree.keep.ask.Ask;
import tk.d4097.truetree.keep.ask.likeness.Likeness;

import java.util.List;

public class StringsToAsk {
  public Ask makeAsk(List<String> keyValStrings, String lstName, Keep keep) {
    StringKeyValArgToLikenesses mapper = new StringKeyValArgToLikenesses();
    mapper.handle(keyValStrings);
    List<Likeness<Lst>> dirLikenesses = mapper.getDirLikenesses();
    List<Likeness<Rec>> likenesses = mapper.getLikenesses();
    return new Ask(dirLikenesses, likenesses, lstName, keep);
  }
}
