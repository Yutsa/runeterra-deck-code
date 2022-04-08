package com.runeterrareporter.encoding;

import com.runeterrareporter.cards.Card;
import com.runeterrareporter.decks.Deck;

import java.util.List;

class DeckSorter {

  public SortedDeck sort(final Deck deck) {
    CardsGroupedByCopies threeOfs = sortCardsGroupedByCopies(deck.threeOfs());
    CardsGroupedByCopies twoOfs = sortCardsGroupedByCopies(deck.twoOfs());
    CardsGroupedByCopies oneOfs = sortCardsGroupedByCopies(deck.oneOfs());
    return new SortedDeck(threeOfs, twoOfs, oneOfs);
  }

  private CardsGroupedByCopies sortCardsGroupedByCopies(final List<Card> cards) {
    CardsGroupedByCopies cardsGroupedByCopies = new CardsGroupedByCopies();
    cards.forEach(cardsGroupedByCopies::add);
    return cardsGroupedByCopies;
  }
}
