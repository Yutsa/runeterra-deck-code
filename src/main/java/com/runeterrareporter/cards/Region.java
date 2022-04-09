package com.runeterrareporter.cards;

import java.util.Arrays;

/**
 * Representation of every region in the game.
 * <p>
 * Each region is encoded with a two letters acronym and each region has a minimum compatible version for the deck code
 * format.
 */
public enum Region {
  DEMACIA("DE", 0, "Demacia", 1),
  FRELJORD("FR", 1, "Freljord", 1),
  IONIA("IO", 2, "Ionia", 1),
  NOXUS("NX", 3, "Noxus", 1),
  PILTOVER_AND_ZAUN("PZ", 4, "Piltover & Zaun", 1),
  SHADOW_ISLES("SI", 5, "Shadow Isles", 1),
  BILGEWATER("BW", 6, "Bilgewater", 2),
  MOUNT_TARGON("MT", 9, "Targon", 2),
  SHURIMA("SH", 7, "Shurima", 3),
  BANDLE_CITY("BC", 10, "Bandle City", 4);

  private final String regionCode;
  private final int id;
  private final String name;
  private final int minimumDeckCodeVersion;

  Region(String regionCode, int id, String name, final int minimumDeckCodeVersion) {
    this.regionCode = regionCode;
    this.id = id;
    this.name = name;
    this.minimumDeckCodeVersion = minimumDeckCodeVersion;
  }


  public static Region fromString(final String regionCode) {
    return Arrays.stream(values())
                 .filter(region -> region.getRegionCode().equals(regionCode))
                 .findFirst()
                 .orElseThrow();
  }

  public static Region fromId(int id) {
    return Arrays.stream(values())
                 .filter(region -> region.getId() == id)
                 .findFirst()
                 .orElseThrow();
  }

  public String getRegionCode() {
    return regionCode;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getMinimumDeckCodeVersion() {
    return minimumDeckCodeVersion;
  }
}
