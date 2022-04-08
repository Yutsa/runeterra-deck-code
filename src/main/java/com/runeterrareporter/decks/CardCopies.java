package com.runeterrareporter.decks;

import java.util.Objects;

import com.runeterrareporter.cards.Card;

/**
 * Representation of a {@link Card card} and the number of copies of it in a deck.
 */
public class CardCopies {

  private final int numberOfCopies;
  private final Card card;

  public CardCopies(final int numberOfCopies, final Card card) {
    this.numberOfCopies = numberOfCopies;
    this.card = card;
  }

  public static CardCopies fromString(String cardCopies) {
    String[] countAndCard = cardCopies.split(":");
    if (countAndCard.length < 2) {
      throw new IllegalArgumentException();
    }
    return new CardCopies(Integer.parseInt(countAndCard[0]), Card.fromCode(countAndCard[1]));
  }

  public int getNumberOfCopies() {
    return numberOfCopies;
  }

  public Card getCard() {
    return card;
  }

  public String cardCode() {
    return card.getCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(card);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final CardCopies that = (CardCopies) o;
    return card.equals(that.card);
  }

  @Override
  public String toString() {
    return "" + numberOfCopies + "x" + card.getCode();
  }
}
