package com.runeterrareporter.decks;

import java.util.*;
import java.util.stream.Collectors;

import com.runeterrareporter.cards.*;

/**
 * Class representing a deck made of different {@link CardCopies cards in 1, 2 or 3 copies}.
 */
public class Deck {

  private final List<CardCopies> cards = new ArrayList<>();

  public void addCard(final List<CardCopies> cardCopies) {
    cards.addAll(cardCopies);
  }

  public void addCard(final CardCopies... cardCopies) {
    cards.addAll(Arrays.asList(cardCopies));
  }

  public List<CardCopies> getCards() {
    return cards;
  }

  /**
   * @return All the cards in 1 copy.
   */
  public List<Card> oneOfs() {
    return retrieveCardsInXCopies(1);
  }

  /**
   * @return All the cards in 2 copies.
   */
  public List<Card> twoOfs() {
    return retrieveCardsInXCopies(2);
  }

  /**
   * @return All the cards in 3 copies.
   */
  public List<Card> threeOfs() {
    return retrieveCardsInXCopies(3);
  }

  private List<Card> retrieveCardsInXCopies(int copies) {
    return cards.stream()
                .filter(cardCopies -> cardCopies.getNumberOfCopies() == copies)
                .map(CardCopies::getCard)
                .toList();
  }

  /**
   * @return The different regions used in the deck.
   */
  public Set<Region> getRegions() {
    return cards
      .stream()
      .map(CardCopies::getCard)
      .map(Card::getRegion)
      .collect(Collectors.toSet());
  }

  @Override
  public int hashCode() {
    return Objects.hash(cards);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Deck deck)) {
      return false;
    }
    return cards.equals(deck.cards);
  }
}
