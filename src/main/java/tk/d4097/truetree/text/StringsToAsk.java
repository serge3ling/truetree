package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Keep;
import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.Rec;
import tk.d4097.truetree.keep.ask.Ask;
import tk.d4097.truetree.keep.ask.likeness.Likeness;

import java.util.List;

public class StringsToAsk {
  private final StringKeyValArgToLikenesses mapper;

  public StringsToAsk() {
    this(new StringKeyValArgToLikenesses());
  }

  public StringsToAsk(StringKeyValArgToLikenesses mapper) {
    this.mapper = mapper;
  }

  public Ask makeAsk(List<String> keyValStrings, Keep keep) {
    mapper.handle(keyValStrings);
    List<Likeness<Lst>> dirLikenesses = mapper.getDirLikenesses();
    List<Likeness<Lst>> pathLikenesses = mapper.getPathLikenesses();
    List<Likeness<Rec>> likenesses = mapper.getLikenesses();
    String lstName = mapper.getLstName();
    return new Ask(dirLikenesses, pathLikenesses, likenesses, lstName, keep);
  }
}
