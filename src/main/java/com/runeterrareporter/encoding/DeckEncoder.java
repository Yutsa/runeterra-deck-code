package com.runeterrareporter.encoding;

import java.util.*;

import com.runeterrareporter.decks.Deck;
import com.runeterrareporter.utils.Base32;
import com.runeterrareporter.utils.Base32.DecodingException;

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
    return Base32.encode(output).replace("=", "");
  }

  /**
   * Decodes a deck code to a {@link Deck deck}.
   *
   * @param deckCode The deck code to be decoded.
   * @return An instnace of {@link Deck} with all the cards from the deck code.
   */
  public Deck decode(String deckCode) {
    ArrayList<Integer> bytes = new ArrayList<>();
    byte[] decode = new byte[0];
    try {
      decode = Base32.decode(deckCode);
    } catch (DecodingException e) {
      e.printStackTrace();
    }
    for (byte data : decode) {
      bytes.add((int) data);
    }
    return deckVarintEncoder.decode(bytes);
  }
}
