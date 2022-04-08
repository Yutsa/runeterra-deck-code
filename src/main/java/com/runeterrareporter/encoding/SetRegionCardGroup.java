package com.runeterrareporter.encoding;

import com.runeterrareporter.cards.Card;
import com.runeterrareporter.cards.Region;
import com.runeterrareporter.cards.ReleaseSet;

import java.util.List;

class SetRegionCardGroup {
    private final ReleaseSet releaseSet;
    private final Region region;
    private final List<Card> cards;

    public SetRegionCardGroup(ReleaseSet releaseSet, Region region, List<Card> cards) {
        this.releaseSet = releaseSet;
        this.region = region;
        this.cards = cards;
    }

    public ReleaseSet getReleaseSet() {
        return releaseSet;
    }

    public Region getRegion() {
        return region;
    }

    public List<Card> getCards() {
        return cards;
    }
    
    public int numberOfCards() {
        return cards.size();
    }
}
