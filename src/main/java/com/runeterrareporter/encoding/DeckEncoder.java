package com.runeterrareporter.encoding;

import java.util.*;

import org.apache.commons.codec.binary.Base32;

import com.runeterrareporter.decks.Deck;

/**
 * Allows encoding and decoding of a deck to the deck code format.
 */
public class DeckEncoder {

  private final DeckVarintEncoder deckVarintEncoder;

  public DeckEncoder(DeckVarintEncoder deckVarintEncoder) {
    this.deckVarintEncoder = deckVarintEncoder;
  }

  public DeckEncoder() {
    this(new DeckVarintEncoder(new DeckSorter()));
  }

  /**
   * Encodes a {@link Deck deck} to a deck code.
   *
   * @param deck The deck to be encoded.
   * @return The compatible deck code.
   */
  public String encode(Deck deck) {
    VarInt varInt = deckVarintEncoder.encode(deck);
    List<Integer> values = varInt.getValues();
    byte[] output = new byte[values.size()];
    for (int i = 0; i < values.size(); i++) {
      output[i] = values.get(i).byteValue();
    }
    return new Base32().encodeAsString(output).replace("=", "");
  }

  /**
   * Decodes a deck code to a {@link Deck deck}.
   *
   * @param deckCode The deck code to be decoded.
   * @return An instnace of {@link Deck} with all the cards from the deck code.
   */
  public Deck decode(String deckCode) {
    ArrayList<Integer> bytes = new ArrayList<>();
    byte[] decode = new Base32().decode(deckCode);
    for (byte data : decode) {
      bytes.add((int) data);
    }
    return deckVarintEncoder.decode(bytes);
  }
}
