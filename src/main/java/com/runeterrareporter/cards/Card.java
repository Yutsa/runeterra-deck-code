package com.runeterrareporter.cards;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;

/**
 * Representation of a card with its release set, region and the ID of the card.
 */
public class Card {

  private final ReleaseSet releaseSet;
  private final Region region;
  private final int cardNumber;

  public Card(ReleaseSet releaseSet, Region region, int cardNumber) {
    this.releaseSet = releaseSet;
    this.region = region;
    this.cardNumber = cardNumber;
  }

  public static Card fromCode(final String code) {
    String setCode = code.substring(0, 2);
    String regionCode = code.substring(2, 4);
    String cardNumber = code.substring(4);
    return new Card(ReleaseSet.fromString(setCode), Region.fromString(regionCode), Integer.parseInt(cardNumber));
  }

  /**
   * @return The full code of the card made by the release set code, the region code and the card id. For instance
   * "01DE001" would be a card from the first release set (Foundation) from Demacia and the ID 001.
   */
  public String getCode() {
    return releaseSet.getReleaseSetCode() + region.getRegionCode() + formatCardNumber();
  }

  private String formatCardNumber() {
    return StringUtils.leftPad(String.valueOf(this.cardNumber), 3, "0");
  }

  public Region getRegion() {
    return region;
  }

  public int getCardNumber() {
    return cardNumber;
  }

  public ReleaseSet getReleaseSet() {
    return releaseSet;
  }

  @Override
  public int hashCode() {
    return Objects.hash(releaseSet, region, cardNumber);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Card card = (Card) o;
    return cardNumber == card.cardNumber && releaseSet == card.releaseSet && region == card.region;
  }
}
