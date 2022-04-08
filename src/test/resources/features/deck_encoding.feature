Feature: Encoding a deck to a deck code and decoding a deck code to a deck.

  Scenario Outline: A deck is made of a cards in three, two or one copies
    Given a deck
    And the deck contains the following "<cards>"
    Then the cards in the 3ofs should be "<threeOfs>"
    And the cards in the 2ofs should be "<twoOfs>"
    And the cards in the 1ofs should be "<oneOfs>"
    Examples:
      | cards                                   | threeOfs        | twoOfs          | oneOfs          |
      | 3:01NX004                               | 01NX004         |                 |                 |
      | 3:02NX004,2:01DE005                     | 02NX004         | 01DE005         |                 |
      | 3:02NX004,2:01DE005,1:04MT456           | 02NX004         | 01DE005         | 04MT456         |
      | 3:02NX004,3:01DE001,2:01DE005,1:04MT456 | 02NX004,01DE001 | 01DE005         | 04MT456         |
      | 3:02NX004,2:01DE005,2:01DE001,1:04MT456 | 02NX004         | 01DE005,01DE001 | 04MT456         |
      | 3:02NX004,2:01DE005,1:04MT456,1:01DE001 | 02NX004         | 01DE005         | 04MT456,01DE001 |

  Scenario Outline: The cards are sorted by number of copies, creating 3 groups.
  The first group is the one with 3 copies, then 2 copies and 1 copies.
  In those groups the cards are then grouped by set/region pair.
  The set/region groups are sorted by increasing length.
  If two set/region groups have the same length, they are ordered by alphanumerically by their set/region pair.
  Cards in each group are sorted alphanumerically.
    Given a deck
    And the deck contains the following "<cards>"
    When the deck is sorted
    Then the sorted deck should be "<sortedDeck>"
    Examples:
      | cards                                                       | sortedDeck                                      |
      | 3:01NX004                                                   | 01NX004                                         |
      | 3:01NX004,3:01NX005                                         | 01NX004,01NX005                                 |
      | 2:01NX004,3:01NX005                                         | 01NX005,01NX004                                 |
      | 2:01NX004,3:01NX005,1:01NX006                               | 01NX005,01NX004,01NX006                         |
      | 1:01NX006,2:01NX004,3:01NX005                               | 01NX005,01NX004,01NX006                         |
      | 3:01NX005,3:01NX006,3:02NX001                               | 02NX001,01NX005,01NX006                         |
      | 3:01NX005,3:01NX006,3:02NX001,3:03NX001                     | 02NX001,03NX001,01NX005,01NX006                 |
      | 3:01NX005,3:01DE006,3:02NX001,3:03NX001                     | 01DE006,01NX005,02NX001,03NX001                 |
      | 3:01NX006,3:01NX003,3:01NX005                               | 01NX003,01NX005,01NX006                         |
      | 3:01NX005,3:01DE006,3:01NX001,3:03NX001,2:01DE001,1:01DE002 | 01DE006,03NX001,01NX001,01NX005,01DE001,01DE002 |

  Scenario Outline: Encoding the deck into the varint
    Given a deck
    And the deck contains the following "<cards>"
    When the deck is encoded to a varint
    Then the varint should contain the following "<values>"
    Examples:
      | cards                                   | values                             |
      | 3:01DE001                               | 17,1,1,1,0,1,0,0                   |
      | 3:01DE001,3:01DE002                     | 17,1,2,1,0,1,2,0,0                 |
      | 3:01DE001,3:01DE002,3:01NX009           | 17,2,1,1,3,9,2,1,0,1,2,0,0         |
      | 2:01DE001                               | 17,0,1,1,1,0,1,0                   |
      | 2:01DE001,2:01DE002                     | 17,0,1,2,1,0,1,2,0                 |
      | 2:01DE001,2:01DE002,2:01NX009           | 17,0,2,1,1,3,9,2,1,0,1,2,0         |
      | 1:01DE001                               | 17,0,0,1,1,1,0,1                   |
      | 1:01DE001,1:01DE002                     | 17,0,0,1,2,1,0,1,2                 |
      | 1:01DE001,1:01DE002,1:01NX009           | 17,0,0,2,1,1,3,9,2,1,0,1,2         |
      | 3:01DE001,2:01DE002                     | 17,1,1,1,0,1,1,1,1,0,2,0           |
      | 3:01DE001,2:01DE002,1:01DE003           | 17,1,1,1,0,1,1,1,1,0,2,1,1,1,0,3   |
      | 3:01DE001,2:01MT002                     | 18,1,1,1,0,1,1,1,1,9,2,0           |
      | 3:01DE001,3:01DE002,2:01MT001,2:02MT002 | 18,1,2,1,0,1,2,2,1,1,9,1,1,2,9,2,0 |

  Scenario Outline: Encoding a deck to a deck code.
    Given a deck
    And the deck contains the following "<cards>"
    When the deck code is computed
    Then the deck code is "<deckCode>"
    Examples:
      | cards                                                                                                                                                                               | deckCode                                                         |
      | 3:01SI015,3:01SI044,3:01SI048,3:01SI054,3:01FR003,3:01FR012,3:01FR020,3:01FR024,3:01FR033,3:01FR036,3:01FR039,3:01FR052,2:01SI005,2:01FR004                                         | CEBAIAIFB4WDANQIAEAQGDAUDAQSIJZUAIAQCAIEAEAQKBIA                 |
      | 3:01FR004,3:01FR039,3:01DE002,3:01DE023,3:01DE040,3:01DE044,3:01DE045,3:01DE053,2:01FR046,2:01DE007,2:01DE009,2:01DE015,2:01DE029,2:01DE032,2:01DE043,1:01FR005,1:01FR006           | CEBAEAIBAQTQMAIAAILSQLBNGUBACAIBFYDACAAHBEHR2IBLAEBACAIFAY       |
      | 3:01DE022,3:01DE023,3:01SI001,3:01SI011,3:01SI015,3:01SI030,3:01SI035,3:01SI043,3:01SI044,3:01SI054,2:01SI049,2:01DE002,2:01DE015,2:01DE033,1:01SI045,1:01DE007                     | CEBAEAIACYLQQAIFAEFQ6HRDFMWDMAQBAECTCAYBAABA6IICAEAQABYBAECS2    |
      | 3:01DE009,3:01DE032,3:01DE052,3:01DE053,3:01IO010,3:01IO016,3:01IO019,3:01IO032,3:01IO048,2:01IO049,2:01DE002,2:01DE007,2:01DE042,1:01IO043,1:01IO045,1:01DE006,1:01DE029,1:01DE045 | CEBAIAIABEQDINIFAEBAUEATEAYAEAIBAIYQGAIAAIDSUAQCAEBCWLIDAEAAMHJN |
      | 3:01NX012,3:01NX017,3:01NX038,3:01NX042,3:01NX046,3:01NX049,3:01DE002,3:01DE009,3:01DE021,3:01DE029,3:01DE051,3:01DE053,2:01DE019,2:01NX004                                         | CEBAMAIAAIERKHJTGUDACAYMCETCULRRAIAQCAATAEAQGBAA                 |

  Scenario Outline: Decoding a deck code to a deck
    Given a deck code "<deckCode>"
    When the code is decoded
    Then the resulting deck should contain the following "<cards>"
    Examples:
      | cards                                                                                                                                                                               | deckCode                                                         |
      | 3:01SI015,3:01SI044,3:01SI048,3:01SI054,3:01FR003,3:01FR012,3:01FR020,3:01FR024,3:01FR033,3:01FR036,3:01FR039,3:01FR052,2:01SI005,2:01FR004                                         | CMBAIAIFB4WDANQIAEAQGDAUDAQSIJZUAIAQCAIEAEAQKBIA                 |
      | 3:01FR004,3:01FR039,3:01DE002,3:01DE023,3:01DE040,3:01DE044,3:01DE045,3:01DE053,2:01FR046,2:01DE007,2:01DE009,2:01DE015,2:01DE029,2:01DE032,2:01DE043,1:01FR005,1:01FR006           | CMBAEAIBAQTQMAIAAILSQLBNGUBACAIBFYDACAAHBEHR2IBLAEBACAIFAY       |
      | 3:01DE022,3:01DE023,3:01SI001,3:01SI011,3:01SI015,3:01SI030,3:01SI035,3:01SI043,3:01SI044,3:01SI054,2:01SI049,2:01DE002,2:01DE015,2:01DE033,1:01SI045,1:01DE007                     | CMBAEAIACYLQQAIFAEFQ6HRDFMWDMAQBAECTCAYBAABA6IICAEAQABYBAECS2    |
      | 3:01DE009,3:01DE032,3:01DE052,3:01DE053,3:01IO010,3:01IO016,3:01IO019,3:01IO032,3:01IO048,2:01IO049,2:01DE002,2:01DE007,2:01DE042,1:01IO043,1:01IO045,1:01DE006,1:01DE029,1:01DE045 | CMBAIAIABEQDINIFAEBAUEATEAYAEAIBAIYQGAIAAIDSUAQCAEBCWLIDAEAAMHJN |
      | 3:01NX012,3:01NX017,3:01NX038,3:01NX042,3:01NX046,3:01NX049,3:01DE002,3:01DE009,3:01DE021,3:01DE029,3:01DE051,3:01DE053,2:01DE019,2:01NX004                                         | CMBAMAIAAIERKHJTGUDACAYMCETCULRRAIAQCAATAEAQGBAA                 |

  Scenario Outline: Decoding and rencoding a deck should result in the same deck code
    Given a deck code "<deckCode>"
    When the code is decoded
    And the deck code is computed
    Then the deck code should be "<deckCode>"
    Examples:
      | deckCode                                                                                      |
      | CMCACAIABEAQGAAOAECQADAEAQDRINTHQIAQGAICAAAQCBAAAICAIB25QAAYCAMKAEBQCBAAAMBAIBYNHMCACAAGB4NB2 |
      | CQBQCAQGFYBAGCJD3EAQOBIKCQQDDJQBW4A5EAOZAEBACAYJGMBAKCRIWEAQEAQDBFEVKAQFBIJZCAI               |
      | CIDACAYABYAQIAACAIBAMFR6AIBAAAQGAICQACQMAQAQABQJDUZQEAIBAABQCAQAA4AA                          |