package com.runeterrareporter.cards;

public class ReleaseSetParser {
    public static ReleaseSet parse(String releaseSet) {
        switch (releaseSet) {
            case "Foundation":
                return ReleaseSet.FOUNDATION;
            case "Rising Tides":
                return ReleaseSet.RISING_TIDES;
            case "Call of the Mountain":
                return ReleaseSet.CALL_OF_THE_MOUNTAIN;
            case "Empire of the Ascended":
                return ReleaseSet.EMPIRE_OF_THE_ASCENDED;
            case "Beyond the Bandlewood":
                return ReleaseSet.BEYOND_THE_BANDLEWOOD;
        }
        return ReleaseSet.FOUNDATION;
    }
}
