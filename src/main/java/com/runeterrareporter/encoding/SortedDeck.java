package com.runeterrareporter.encoding;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class SortedDeck {

  private final CardsGroupedByCopies threeOfs;
  private final CardsGroupedByCopies twoOfs;
  private final CardsGroupedByCopies oneOfs;

  public SortedDeck(final CardsGroupedByCopies threeOfs, final CardsGroupedByCopies twoOfs, final CardsGroupedByCopies oneOfs) {
    this.threeOfs = threeOfs;
    this.twoOfs = twoOfs;
    this.oneOfs = oneOfs;
  }

  public CardsGroupedByCopies getThreeOfs() {
    return threeOfs;
  }

  public CardsGroupedByCopies getTwoOfs() {
    return twoOfs;
  }

  public CardsGroupedByCopies getOneOfs() {
    return oneOfs;
  }

  @Override
  public String toString() {
    return Stream.of(threeOfs.toString(), twoOfs.toString(), oneOfs.toString())
                 .filter(group -> !group.isEmpty())
                 .collect(Collectors.joining(","));
  }
}
