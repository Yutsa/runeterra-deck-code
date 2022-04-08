package com.runeterrareporter.cards;

import java.util.Arrays;

/**
 * Representation of each release set. Each release set is encoded by a two digits number.
 */
public enum ReleaseSet {
  FOUNDATION("01", 1),
  RISING_TIDES("02", 2),
  CALL_OF_THE_MOUNTAIN("03", 3),
  EMPIRE_OF_THE_ASCENDED("04", 4),
  BEYOND_THE_BANDLEWOOD("05", 5);

  private final String releaseSetCode;
  private final int id;

  ReleaseSet(String releaseSetCode, int id) {
    this.releaseSetCode = releaseSetCode;
    this.id = id;
  }

  public static ReleaseSet fromString(final String setCode) {
    return Arrays.stream(values())
                 .filter(releaseSet -> releaseSet.getReleaseSetCode().equals(setCode))
                 .findFirst()
                 .orElseThrow();
  }

  public String getReleaseSetCode() {
    return releaseSetCode;
  }

  public int getId() {
    return id;
  }
}
