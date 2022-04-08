package com.runeterrareporter.cards;

public class RegionParser {

  public static Region parse(String regionName) {
    switch (regionName) {
      case "Demacia":
        return Region.DEMACIA;
      case "Freljord":
        return Region.FRELJORD;
      case "Ionia":
        return Region.IONIA;
      case "Noxus":
        return Region.NOXUS;
      case "Piltover & Zaun":
        return Region.PILTOVER_AND_ZAUN;
      case "Shadow Isles":
        return Region.SHADOW_ISLES;
      case "Bilgewater":
        return Region.BILGEWATER;
      case "Mount Targon":
        return Region.MOUNT_TARGON;
      case "Shurima":
        return Region.SHURIMA;
      case "Bandle City":
        return Region.BANDLE_CITY;
    }
    return Region.DEMACIA;
  }
}
