package com.runeterrareporter.encoding;

import com.runeterrareporter.decks.Deck;

import org.apache.commons.codec.binary.Base32;

import java.util.ArrayList;
import java.util.List;

public class DeckEncoder {

    private final DeckVarintEncoder deckVarintEncoder;

    public DeckEncoder(DeckVarintEncoder deckVarintEncoder) {
        this.deckVarintEncoder = deckVarintEncoder;
    }
    
    public DeckEncoder() {
        this(new DeckVarintEncoder(new DeckSorter()));
    }

    public String encode(Deck deck) {
        VarInt varInt = deckVarintEncoder.encode(deck);
        List<Integer> values = varInt.getValues();
        byte[] output = new byte[values.size()];
        for (int i = 0; i < values.size(); i++)
        {
            output[i] = values.get(i).byteValue();
        }
        return new Base32().encodeAsString(output).replace("=", "");
    }

    public Deck decode(String deckCode) {
        ArrayList<Integer> bytes = new ArrayList<>();
        byte[] decode = new Base32().decode(deckCode);
        for (byte data : decode) {
            bytes.add((int) data);
        }
        return deckVarintEncoder.decode(bytes);
    }
}
