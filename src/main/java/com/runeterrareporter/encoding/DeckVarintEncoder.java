package com.runeterrareporter.encoding;

import java.util.List;

import com.runeterrareporter.cards.*;
import com.runeterrareporter.decks.*;
import com.runeterrareporter.utils.StringUtils;

class DeckVarintEncoder {

  private final DeckSorter deckSorter;
  private VarInt varInt;

  public DeckVarintEncoder(DeckSorter deckSorter) {
    this.deckSorter = deckSorter;
  }

  public VarInt encode(Deck deck) {
    varInt = new VarInt();
    encodeFormatAndVersion(deck);
    SortedDeck sortedDeck = deckSorter.sort(deck);
    encodeXOfs(sortedDeck.getThreeOfs());
    encodeXOfs(sortedDeck.getTwoOfs());
    encodeXOfs(sortedDeck.getOneOfs());
    return varInt;
  }

  private void encodeFormatAndVersion(Deck deck) {
    int format = 1;
    int version = computeMinimumDeckVersion(deck);
    // The first 4 bits are the format and the last 4 are the version.
    int versionAndFormat = ((format << 4) | (version & 0xF));
    varInt.add(versionAndFormat);
  }

  private int computeMinimumDeckVersion(Deck deck) {
    return deck.getRegions().stream()
               .mapToInt(Region::getMinimumDeckCodeVersion)
               .max()
               .orElseThrow(() -> new IllegalArgumentException("The deck should contain card from at least one region"));
  }

  private void encodeXOfs(CardsGroupedByCopies cards) {
    varInt.add(cards.numberOfSetRegionCombination());
    cards.retrieveGroups().forEach(this::encodeSetRegionGroup);
  }

  private void encodeSetRegionGroup(SetRegionCardGroup cardGroup) {
    varInt.add(cardGroup.numberOfCards());
    varInt.add(cardGroup.getReleaseSet().getId());
    varInt.add(cardGroup.getRegion().getId());
    cardGroup.getCards().forEach(card -> varInt.add(card.getCardNumber()));
  }

  public Deck decode(List<Integer> bytes) {
    Deck deck = new Deck();
    varInt = new VarInt(bytes);
    varInt.pop();
    decodeXOfs(varInt, deck, 3);
    decodeXOfs(varInt, deck, 2);
    decodeXOfs(varInt, deck, 1);
    return deck;
  }

  private void decodeXOfs(VarInt varInt, Deck deck, int xOfs) {
    int numberOfSetRegionGroups = varInt.pop();
    for (int i = 0; i < numberOfSetRegionGroups; i++) {
      int numberOfCards = varInt.pop();
      String releaseSetId = formatReleaseSet(varInt.pop());
      String regionId = formatRegionId(varInt.pop());
      for (int j = 0; j < numberOfCards; j++) {
        String cardCode = formatCardCode(varInt.pop());
        deck.addCard(new CardCopies(xOfs, Card.fromCode(releaseSetId + regionId + cardCode)));
      }
    }
  }

  private String formatCardCode(int cardCode) {
    return StringUtils.leftPad(String.valueOf(cardCode), 3, "0");
  }

  private String formatRegionId(int regionId) {
    return Region.fromId(regionId).getRegionCode();
  }

  private String formatReleaseSet(int releaseSetId) {
    return StringUtils.leftPad(String.valueOf(releaseSetId), 2, "0");
  }
}
