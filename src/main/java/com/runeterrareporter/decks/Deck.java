package com.runeterrareporter.decks;

import com.runeterrareporter.cards.Card;
import com.runeterrareporter.cards.Region;
import com.runeterrareporter.encoding.DeckEncoder;

import java.util.*;
import java.util.stream.Collectors;

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

  public List<Card> oneOfs() {
    return retrieveCardsInXCopies(1);
  }

  public List<Card> twoOfs() {
    return retrieveCardsInXCopies(2);
  }

  public List<Card> threeOfs() {
    return retrieveCardsInXCopies(3);
  }

  private List<Card> retrieveCardsInXCopies(int copies) {
    return cards.stream()
                .filter(cardCopies -> cardCopies.getNumberOfCopies() == copies)
                .map(CardCopies::getCard)
                .collect(Collectors.toList());
  }

  public Set<Region> getRegions() {
    return cards
            .stream()
            .map(CardCopies::getCard)
            .map(Card::getRegion)
            .collect(Collectors.toSet());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Deck)) return false;
    Deck deck = (Deck) o;
    return cards.equals(deck.cards);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cards);
  }
}
