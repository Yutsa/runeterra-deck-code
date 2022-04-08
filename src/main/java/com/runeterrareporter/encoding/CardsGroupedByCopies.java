package com.runeterrareporter.encoding;

import java.util.*;
import java.util.stream.Collectors;

import com.runeterrareporter.cards.*;

class CardsGroupedByCopies {

  private final Map<SetRegionPair, List<Card>> setRegionGroup = new HashMap<>();

  public void add(Card card) {
    setRegionGroup.computeIfAbsent(SetRegionPair.fromCard(card), k -> new ArrayList<>()).add(card);
  }

  public List<Card> getCards() {
    return setRegionGroup.values()
                         .stream()
                         .sorted(compareSetRegionGroups())
                         .flatMap(cards -> cards.stream().sorted(Comparator.comparing(Card::getCode)))
                         .toList();
  }

  private Comparator<List<Card>> compareSetRegionGroups() {
    return Comparator.comparing(List<Card>::size)
                     .thenComparing(cards -> cards.get(0).getCode());
  }

  public int numberOfSetRegionCombination() {
    return setRegionGroup.keySet().size();
  }

  public List<SetRegionCardGroup> retrieveGroups() {
    List<SetRegionCardGroup> result = new ArrayList<>();
    List<List<Card>> groupOfCards = setRegionGroup.values()
                                                  .stream()
                                                  .sorted(compareSetRegionGroups())
                                                  .toList();

    for (List<Card> groupOfCard : groupOfCards) {
      List<Card> sortedCards = groupOfCard.stream()
                                          .sorted(Comparator.comparing(Card::getCode))
                                          .toList();
      Card firstCard = sortedCards.get(0);
      result.add(new SetRegionCardGroup(firstCard.getReleaseSet(), firstCard.getRegion(), sortedCards));
    }
    return result;
  }

  @Override
  public String toString() {
    return getCards().stream()
                     .map(Card::getCode)
                     .collect(Collectors.joining(","));
  }

  private static class SetRegionPair {

    private final ReleaseSet set;
    private final Region region;

    public SetRegionPair(final ReleaseSet set, final Region region) {
      this.set = set;
      this.region = region;
    }

    public static SetRegionPair fromCard(Card card) {
      return new SetRegionPair(card.getReleaseSet(), card.getRegion());
    }

    public ReleaseSet getSet() {
      return set;
    }

    public Region getRegion() {
      return region;
    }

    @Override
    public int hashCode() {
      return Objects.hash(set, region);
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final SetRegionPair that = (SetRegionPair) o;
      return set == that.set && region == that.region;
    }
  }
}
